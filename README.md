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

 - Build `cdtp-definition-builder` project.
 
 - Clean previous protocol definition (remove packages `commands`, `events`, `types` from `cdtp-java-client` project).
 
 - Run `cdtp-definition-builder` and parse `protocol.json` file. Output classess will be written to `cdtp-java-client` project in `src/main/java/com/github/kklisura/cdtp/protocol` directory.

 - Compile `cdtp-java-client` in order to check if project compiles successfully.

## License

TBD
