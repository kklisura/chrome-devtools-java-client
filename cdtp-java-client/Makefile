
MVN=mvn

SONAR_HOST=http://localhost:9999

SONAR_COVERAGE_EXCLUSIONS="**/com/github/kklisura/cdtp/protocol/**/*,**/com/github/kklisura/cdtp/App.java"

sonar-analysis:
	# Running sonar analysis
	$(MVN) clean test && \
	$(MVN) org.jacoco:jacoco-maven-plugin:restore-instrumented-classes
	$(MVN) sonar:sonar -Dsonar.host.url=$(SONAR_HOST) \
		-Dsonar.tests="src/test" \
		-Dsonar.exclusions="$(SONAR_COVERAGE_EXCLUSIONS)" \
		-Dsonar.coverage.exclusions="$(SONAR_COVERAGE_EXCLUSIONS)"
