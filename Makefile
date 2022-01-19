run: compile
	java Frontend routes-tiny.csv

compile: Frontend.java Backend.java DataReader.java Airport.java Flight.java
	javac Frontend.java

test: testData testBackend testFrontend compile
	java -jar junit5.jar -cp . --scan-classpath

testFrontend: TestFrontend.java
	javac -cp .:junit5.jar TestFrontend.java

testBackend: TestBackend.java
	javac -cp .:junit5.jar TestBackend.java
	
testData: TestDataReader.java
	javac -cp .:junit5.jar TestDataReader.java

clean:
	$(RM) *.class
