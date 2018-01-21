package com.github.kklisura.cdtp.definition.builder;

import com.github.javaparser.printer.PrettyPrinter;
import com.github.javaparser.printer.PrettyPrinterConfiguration;
import com.github.javaparser.utils.SourceRoot;
import com.github.kklisura.cdtp.definition.builder.protocol.DevToolsProtocol;
import com.github.kklisura.cdtp.definition.builder.protocol.types.Domain;
import com.github.kklisura.cdtp.definition.builder.support.java.builder.Builder;
import com.github.kklisura.cdtp.definition.builder.support.java.builder.JavaBuilderFactory;
import com.github.kklisura.cdtp.definition.builder.support.java.builder.JavaClassBuilder;
import com.github.kklisura.cdtp.definition.builder.support.java.builder.JavaEnumBuilder;
import com.github.kklisura.cdtp.definition.builder.support.java.builder.JavaInterfaceBuilder;
import com.github.kklisura.cdtp.definition.builder.support.java.builder.impl.JavaClassBuilderImpl;
import com.github.kklisura.cdtp.definition.builder.support.java.builder.impl.JavaEnumBuilderImpl;
import com.github.kklisura.cdtp.definition.builder.support.java.builder.impl.JavaInterfaceBuilderImpl;
import com.github.kklisura.cdtp.definition.builder.support.protocol.builder.CommandBuilder;
import com.github.kklisura.cdtp.definition.builder.support.protocol.builder.EventBuilder;
import com.github.kklisura.cdtp.definition.builder.support.protocol.builder.TypesBuilder;
import com.github.kklisura.cdtp.definition.builder.utils.DevToolsProtocolUtils;
import org.kohsuke.args4j.CmdLineException;
import org.kohsuke.args4j.CmdLineParser;
import org.kohsuke.args4j.ParserProperties;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import static com.github.kklisura.cdtp.definition.builder.support.utils.DomainUtils.devToolsProtocolResolver;
import static com.github.kklisura.cdtp.definition.builder.support.utils.StringUtils.buildPackageName;

/**
 * Application
 *
 * @author Kenan Klisura
 */
public class Application {
	private static final String TYPES_PACKAGE = "types";
	private static final String EVENTS_PACKAGE = "events";
	private static final String COMMANDS_PACKAGE = "commands";
	private static final String ANNOTATIONS_PACKAGE = "annotations";

	private static final String FACTORY_PACKAGE = "factory";

	private static final String COMMAND_FACTORY_NAME = "CommandFactory";

	private static final String SRC_MAIN = "src/main/java";

	/**
	 * Applications main entry point.
	 *
	 * @param args Arguments.
	 */
	public static void main(String[] args) throws IOException {
		final ParserProperties parserProperties = ParserProperties.defaults()
				.withUsageWidth(120);

		final Configuration configuration = new Configuration();
		final CmdLineParser parser = new CmdLineParser(configuration, parserProperties);

		try {
			parser.parseArgument(args);
		} catch (CmdLineException ex) {
			System.err.println(ex.getMessage());
			parser.printUsage(System.err);
			System.exit(1);
		}

		final String typesPackageName = buildPackageName(configuration.getBasePackage(), TYPES_PACKAGE);
		final String eventPackageName = buildPackageName(configuration.getBasePackage(), EVENTS_PACKAGE);
		final String commandPackageName = buildPackageName(configuration.getBasePackage(), COMMANDS_PACKAGE);
		final String annotationsPackageName = buildPackageName(configuration.getBasePackage(), ANNOTATIONS_PACKAGE);

		final InputStream inputStream = new FileInputStream(configuration.getProtocolFile());
		final  DevToolsProtocol protocol = DevToolsProtocolUtils.readJson(inputStream);

		Path outputLocation = configuration.getOutputProjectLocation().toPath().resolve(SRC_MAIN);
		SourceRoot sourceRoot = new SourceRoot(outputLocation);

		JavaBuilderFactory javaBuilderFactory = new JavaBuilderFactory() {
			@Override
			public JavaClassBuilder createClassBuilder(String packageName, String className) {
				return new JavaClassBuilderImpl(packageName, className, annotationsPackageName);
			}

			@Override
			public JavaEnumBuilder createEnumBuilder(String packageName, String enumName) {
				return new JavaEnumBuilderImpl(packageName, enumName);
			}

			@Override
			public JavaInterfaceBuilder createInterfaceBuilder(String packageName, String interfaceName) {
				return new JavaInterfaceBuilderImpl(packageName, interfaceName, annotationsPackageName);
			}
		};

		final TypesBuilder typesBuilder = new TypesBuilder(typesPackageName, javaBuilderFactory);
		final EventBuilder eventBuilder = new EventBuilder(eventPackageName, javaBuilderFactory, typesPackageName);
		final CommandBuilder commandBuilder = new CommandBuilder(commandPackageName, javaBuilderFactory, typesPackageName, eventPackageName);

		List<Builder> builderList = new ArrayList<>();

		// Create domain type builders
		for (Domain domain : protocol.getDomains()) {
			builderList.addAll(typesBuilder.build(domain, devToolsProtocolResolver(protocol)));
			builderList.addAll(eventBuilder.build(domain, devToolsProtocolResolver(protocol)));
			builderList.add(commandBuilder.build(domain, devToolsProtocolResolver(protocol)));
		}

		// Build command factory
		builderList.add(buildCommandFactory(protocol.getDomains(), configuration.getBasePackage()));

		// Build all items
		for (Builder builder : builderList) {
			builder.build(sourceRoot);
		}

		PrettyPrinterConfiguration prettyPrinterConfiguration = new PrettyPrinterConfiguration();
		prettyPrinterConfiguration.setIndent("\t");
		prettyPrinterConfiguration.setPrintComments(true);
		prettyPrinterConfiguration.setPrintJavadoc(true);
		prettyPrinterConfiguration.setOrderImports(true);

		PrettyPrinter prettyPrinter = new PrettyPrinter(prettyPrinterConfiguration);

		sourceRoot.setPrinter(prettyPrinter::print);
		sourceRoot.saveAll(outputLocation);
	}

	private static Builder buildCommandFactory(List<Domain> domains, String basePackage) {
		String commandsPackage = basePackage + "." + COMMANDS_PACKAGE;
		String factoryPackage = commandsPackage + "." + FACTORY_PACKAGE;

		JavaInterfaceBuilder factoryInterfaceBuilder = new JavaInterfaceBuilderImpl(factoryPackage, COMMAND_FACTORY_NAME,
				null);

		for (Domain domain : domains) {
			String description = String.format("Returns the %s command.", domain.getDomain());

			factoryInterfaceBuilder.addImport(commandsPackage, domain.getDomain());
			factoryInterfaceBuilder.addMethod("get" + domain.getDomain(), description, null, domain.getDomain());
		}

		return factoryInterfaceBuilder;
	}
}
