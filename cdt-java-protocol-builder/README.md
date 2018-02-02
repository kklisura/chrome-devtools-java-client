# Chrome DevTools Java Protocol Builder

## Description

Chrome DevTools Java Protocol Builder parses DevTools `protocol.json` - a protocol definition file and outputs the java classes and interfaces.
 
## Building

To build jar file either run:

`make build` or `mvn clean package`

## Running

```
java -jar target/cdt-java-protocol-builder.jar --base-package="com.github.kklisura.cdt.protocol" \
  --output=../cdt-java-client \
  --protocol=../protocol.json
```

This would parse `./protocol.json` file and it would create classes, interfaces, enums in `../cdt-java-client` with a package name of `com.github.kklisura.cdt.protocol`.

## Running unit tests

`make verify`

## Sonar analysis

`make sonar-analysis`

## License

Chrome DevTools Java Protocol Builder is licensed under the Apache License, Version 2.0. See [LICENSE](LICENSE.txt) for the full license text.
