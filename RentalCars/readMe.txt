- Start MySQL
- Create MySQL schema using script

To execute application
- launch registry
- mvn compile
- mvn exec:java -Pserver
- mvn exec:java -Pclient

To test (do not forget to stop the RMIRegistry) 
- mvn test

(Mock tests simulation of DAO Layer)
(RMI integration tests)

To generate cobertura reports
- mvn cobertura:cobertura