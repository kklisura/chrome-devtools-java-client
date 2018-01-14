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

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import static com.github.kklisura.cdtp.definition.builder.support.utils.DomainUtils.devToolsProtocolResolver;

/**
 * Application
 *
 * @author Kenan Klisura
 */
public class Application {
	/**
	 * Applications main entry point.
	 *
	 * @param args Arguments.
	 */
	public static void main(String[] args) throws IOException {
		final String typesPackageName = "com.github.kklisura.cdtp.protocol.types";
		final String eventPackageName = "com.github.kklisura.cdtp.protocol.events";
		final String commandPackageName = "com.github.kklisura.cdtp.protocol.commands";
		final String annotationsPackageName = "com.github.kklisura.cdtp.protocol.annotations";

		final InputStream inputStream = Application.class.getClassLoader().getResourceAsStream("protocol.json");
		final  DevToolsProtocol protocol = DevToolsProtocolUtils.readJson(inputStream);

		Path rootPath = new File("/Users/kenanklisura/development/playground/dev-tools-protocol-java-generator/cdtp-java-client/src/main/java").toPath();
		SourceRoot sourceRoot = new SourceRoot(rootPath);

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

		// Build all items
		for (Builder builder : builderList) {
			builder.build(sourceRoot);
		}

		PrettyPrinterConfiguration prettyPrinterConfiguration = new PrettyPrinterConfiguration();
		prettyPrinterConfiguration.setIndent("\t");
		prettyPrinterConfiguration.setPrintComments(true);
		prettyPrinterConfiguration.setPrintJavaDoc(true);

		PrettyPrinter prettyPrinter = new PrettyPrinter(prettyPrinterConfiguration);

		sourceRoot.setPrinter(prettyPrinter::print);
		sourceRoot.saveAll(rootPath);
	}
}
