MVN=mvn
RM=rm
JAVA=java
CP=cp
RUN_JAR=$(JAVA) -jar

DEFINITION_BUILDER_DIR=cdtp-definition-builder
DEFINITION_BUILDER_JAR="$(DEFINITION_BUILDER_DIR)/target/cdtp-definition-builder.jar"

JAVA_CLIENT_DIR=cdtp-java-client
JAVA_CLIENT_PACKAGE=com/github/kklisura/cdtp/protocol

PACKAGE_NAME=com.github.kklisura.cdtp.protocol
PROTOCOL_JSON_FILE=./protocol.json

build-cdtp-definition-builder:
	# Building cdtp-definition-builder project...
	$(CP) $(PROTOCOL_JSON_FILE) "./$(DEFINITION_BUILDER_DIR)/src/test/resources/protocol.json"
	$(MVN) --file "$(DEFINITION_BUILDER_DIR)/" clean package

compile-cdtp-java-client:
	# Compiling cdtp-java-client project...
	$(MVN) --file "$(JAVA_CLIENT_DIR)/" clean compile

clean-cdtp-definition-builder:
	# Cleaning cdtp-definition-builder project...
	$(MVN) --file "$(DEFINITION_BUILDER_DIR)/" clean

clean-cdtp-java-client:
	# Cleaning cdtp-java-client project...
	$(MVN) --file "$(JAVA_CLIENT_DIR)/" clean

clean-previous-protocol-definition:
	# Cleaning previous protocol definition...
	$(RM) -rf $(JAVA_CLIENT_DIR)/src/main/java/$(JAVA_CLIENT_PACKAGE)/types
	$(RM) -rf $(JAVA_CLIENT_DIR)/src/main/java/$(JAVA_CLIENT_PACKAGE)/events
	$(RM) -rf $(JAVA_CLIENT_DIR)/src/main/java/$(JAVA_CLIENT_PACKAGE)/commands

upgrade-protocol-definition: build-cdtp-definition-builder clean-previous-protocol-definition
	$(RUN_JAR) $(DEFINITION_BUILDER_JAR) --base-package="$(PACKAGE_NAME)" \
		--output=./$(JAVA_CLIENT_DIR)/ \
		--protocol=$(PROTOCOL_JSON_FILE)

update-protocol-definition: upgrade-protocol-definition compile-cdtp-java-client
	# Updated protocol definition on cdtp-java-client
