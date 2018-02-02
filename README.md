# DevTools Protocol Java Client

## Description

TBD

## Development

### Update protocol definition

To upgrade protocol definition from current `1.3` version to `latest` version, download new `protocol.json` file to root dir and run:

```
make update-protocol-definition
```

By running, this will:

 - Build `cdt-definition-builder` project.
 
 - Clean previous protocol definition (remove packages `commands`, `events`, `types` from `cdt-java-client` project).
 
 - Run `cdt-definition-builder` and parse `protocol.json` file. Output classess will be written to `cdt-java-client` project in `src/main/java/com/github/kklisura/cdt/protocol` directory.

 - Compile `cdt-java-client` in order to check if project compiles successfully.

## License

TBD
