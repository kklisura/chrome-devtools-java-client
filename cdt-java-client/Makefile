
MVN=mvn

SONAR_HOST=http://localhost:9999
SONAR_COVERAGE_EXCLUSIONS="**/com/github/kklisura/cdt/protocol/**/*,**/com/github/kklisura/cdt/App.java"

sonar-analysis:
	# Running sonar analysis
	$(MVN) clean test && \
	$(MVN) org.jacoco:jacoco-maven-plugin:restore-instrumented-classes
	$(MVN) sonar:sonar -Dsonar.host.url=$(SONAR_HOST) \
		-Dsonar.tests="src/test" \
		-Dsonar.exclusions="$(SONAR_COVERAGE_EXCLUSIONS)" \
		-Dsonar.coverage.exclusions="$(SONAR_COVERAGE_EXCLUSIONS)"

clean:
	$(MVN) clean

verify:
	$(MVN) clean test

build:
	# Building...
	$(MVN) clean package
