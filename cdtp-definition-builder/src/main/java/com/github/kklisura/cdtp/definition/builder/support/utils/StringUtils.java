package com.github.kklisura.cdtp.definition.builder.support.utils;

import lombok.experimental.UtilityClass;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Kenan Klisura on 12/01/2018.
 *
 * @author Kenan Klisura
 */
@UtilityClass
public class StringUtils {
	private static final Map<String, String> ENUM_CONSTANT_VALUE_OVERRIDES = new HashMap<>();

	static {
		ENUM_CONSTANT_VALUE_OVERRIDES.put("NaN", "NAN");
		ENUM_CONSTANT_VALUE_OVERRIDES.put("-Infinity", "NEGATIVE_INFINITY");
		ENUM_CONSTANT_VALUE_OVERRIDES.put("-0", "NEGATIVE_0");
	}

	private static final Pattern GETTER_NAME_PATTERN = Pattern.compile("^get(.*)");

	/**
	 * Converts input string to java enum constant.
	 *
	 * Converts value to UPPERCASE.
	 * Converts special - string to lowercase.
	 * Converts camelCase to CAMEL_CASE constant.
	 *
	 * @param value Input string
	 * @return Enum constant.
	 */
	public static String toEnumConstant(String value) {
		if (ENUM_CONSTANT_VALUE_OVERRIDES.get(value) != null) {
			return ENUM_CONSTANT_VALUE_OVERRIDES.get(value);
		}

		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < value.length(); i++) {
			char c = value.charAt(i);
			if ((Character.isUpperCase(c) && i > 0 && Character.isLowerCase(value.charAt(i - 1))) ||
					(Character.isDigit(c) && i > 0 && Character.isLetter(value.charAt(i - 1))) ||
					(Character.isUpperCase(c) && i > 1 && i < value.length() - 1 &&
							Character.isUpperCase(value.charAt(i - 1))) && Character.isLowerCase(value.charAt(i + 1))) {
				sb.append("_");
			}

			if (Character.isLetterOrDigit(c)) {
				sb.append(Character.toUpperCase(c));
			} else {
				sb.append("_");
			}
		}

		return sb.toString();
	}

	/**
	 * Builds a package name based on base package and new package.
	 *
	 * @param basePackageName Base package name.
	 * @param packageName     Package name.
	 * @return Package name.
	 */
	public static String buildPackageName(String basePackageName, String packageName, String... subPackageNames) {
		StringBuilder stringBuilder = new StringBuilder(basePackageName + "." + packageName);

		if (subPackageNames != null) {
			for (String subPackageName : subPackageNames) {
				stringBuilder.append(".");
				stringBuilder.append(subPackageName);
			}
		}

		return stringBuilder.toString();
	}

	/**
	 * Capitalizes string.
	 *
	 * @param value Input value.
	 * @return Capitalized input value.
	 */
	public static String capitalize(String value) {
		return org.apache.commons.lang3.StringUtils.capitalize(value);
	}

	/**
	 * Generates a enum/class name given a value. Just capitalizes string for now.
	 *
	 * @param value Input value.
	 * @return Enum/class name value.
	 */
	public static String toEnumClass(String value) {
		return capitalize(value);
	}

	/**
	 * Returns return type name from a getter i.e. getFrameWithManifest -> FrameWithManifest
	 *
	 * If nothing found then return is as follows: xxYyyyy -> XxYyyy
	 *
	 * @param getterName Getter name.
	 * @return Return type name or getter name if nothing found.
	 */
	public static String getReturnTypeFromGetter(String getterName) {
		Matcher matcher = GETTER_NAME_PATTERN.matcher(getterName);

		if (matcher.matches()) {
			return toEnumClass(matcher.group(1));
		}

		return toEnumClass(getterName);
	}
}
