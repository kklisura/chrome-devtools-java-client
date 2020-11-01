
MVN=mvn

SONAR_HOST=http://localhost:9999
SONAR_COVERAGE_EXCLUSIONS="**/com/github/kklisura/cdt/protocol/**/*,**/com/github/kklisura/cdt/App.java"

sonar-analysis:
	# Running sonar analysis
	$(MVN) clean test -P coverage && \
	$(MVN) org.jacoco:jacoco-maven-plugin:restore-instrumented-classes
	$(MVN) sonar:sonar -Dsonar.host.url=$(SONAR_HOST) \
		-Dsonar.tests="src/test" \
		-Dsonar.exclusions="$(SONAR_COVERAGE_EXCLUSIONS)" \
		-Dsonar.coverage.exclusions="$(SONAR_COVERAGE_EXCLUSIONS)"

clean:
	$(MVN) clean

verify:
	# Running tests
	$(MVN) clean test

build:
	# Building...
	$(MVN) clean package

deploy:
	# Deploying
	GPG_TTY=$$(tty) $(MVN) clean compile deploy -P release

snapshot:
	$(MVN) versions:set -DnewVersion=${version}-SNAPSHOT
	$(MVN) versions:commit

release:
	$(MVN) versions:set -DnewVersion=${version}
	$(MVN) versions:commit
