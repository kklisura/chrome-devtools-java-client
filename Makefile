MVN=mvn
RM=rm
JAVA=java
CP=cp
RUN_JAR=$(JAVA) -jar

DEFINITION_BUILDER_DIR=cdt-definition-builder
DEFINITION_BUILDER_JAR="$(DEFINITION_BUILDER_DIR)/target/cdt-definition-builder.jar"

JAVA_CLIENT_DIR=cdt-java-client
JAVA_CLIENT_PACKAGE=com/github/kklisura/cdt/protocol

PACKAGE_NAME=com.github.kklisura.cdt.protocol
PROTOCOL_JSON_FILE=./protocol.json

build-cdt-definition-builder:
	# Building cdt-definition-builder project...
	$(CP) $(PROTOCOL_JSON_FILE) "./$(DEFINITION_BUILDER_DIR)/src/test/resources/protocol.json"
	$(MVN) --file "$(DEFINITION_BUILDER_DIR)/" clean package

compile-cdt-java-client:
	# Compiling cdt-java-client project...
	$(MVN) --file "$(JAVA_CLIENT_DIR)/" clean compile

clean-cdt-definition-builder:
	# Cleaning cdt-definition-builder project...
	$(MVN) --file "$(DEFINITION_BUILDER_DIR)/" clean

clean-cdt-java-client:
	# Cleaning cdt-java-client project...
	$(MVN) --file "$(JAVA_CLIENT_DIR)/" clean

clean-previous-protocol-definition:
	# Cleaning previous protocol definition...
	$(RM) -rf $(JAVA_CLIENT_DIR)/src/main/java/$(JAVA_CLIENT_PACKAGE)/types
	$(RM) -rf $(JAVA_CLIENT_DIR)/src/main/java/$(JAVA_CLIENT_PACKAGE)/events
	$(RM) -rf $(JAVA_CLIENT_DIR)/src/main/java/$(JAVA_CLIENT_PACKAGE)/commands

upgrade-protocol-definition: build-cdt-definition-builder clean-previous-protocol-definition
	$(RUN_JAR) $(DEFINITION_BUILDER_JAR) --base-package="$(PACKAGE_NAME)" \
		--output=./$(JAVA_CLIENT_DIR)/ \
		--protocol=$(PROTOCOL_JSON_FILE)

update-protocol-definition: upgrade-protocol-definition compile-cdt-java-client
	# Updated protocol definition on cdt-java-client

sonar-analysis:
	# Running sonar analysis
	cd $(DEFINITION_BUILDER_DIR)/ && make sonar-analysis
	cd $(JAVA_CLIENT_DIR)/ && make sonar-analysis
