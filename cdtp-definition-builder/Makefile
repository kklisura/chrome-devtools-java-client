
MVN=mvn

SONAR_HOST=http://localhost:9999

sonar-analysis:
	# Running sonar analysis
	$(MVN) clean test && $(MVN) sonar:sonar -Dsonar.host.url=$(SONAR_HOST) \
		-Dsonar.tests="src/test"
