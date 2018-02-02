
MVN=mvn

SONAR_HOST=http://localhost:9999

sonar-analysis:
	# Running sonar analysis
	$(MVN) clean test && $(MVN) sonar:sonar -Dsonar.host.url=$(SONAR_HOST) \
		-Dsonar.tests="src/test"

clean:
	$(MVN) clean

verify:
	$(MVN) clean test

build:
	# Building...
	$(MVN) clean package
