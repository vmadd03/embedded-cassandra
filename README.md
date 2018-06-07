# embedded-cassandra

Goal(s): Spring Boot app with a rest endpoint to connect to real cassandra instance. Spring Boot component tests to connect to an embedded cassandra (These can be integrated and run as part of our CI)

1. Create a standalong spring boot maven project using SPRING INITIALIZR at https://start.spring.io/
2. Add all the spring boot dependencies required. Critical ones are listed below
	 		spring-boot-starter-data-cassandra - 	spring boot jpa for cassandra to support CRUD operations
			cassandra-driver-core							 - 	Cassandra driver to connect to real cassandra or embedded cassandra
			cassandra-unit-spring							 -	To start an embedded cassandra
			cassandra-unit-shaded							 -  To start an embedded cassandra
2. Add a rest endpoint which inserts the data into a real cassandra instance by defining the controller(s), repositories(s)
3. 
