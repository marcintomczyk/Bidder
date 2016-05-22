This is a sample project for BB - 'Bidder Simulator' :)

Just another simple training project (the base for doing more complex things etc.). This time uses the following key technologies:

Struts2
JQuery
Freemarker templates
Maven (with profiles)
JPA (Hibernate)
JSON
DBunit
Apache Derby
Mockito

Additional notes:

1. Does not require setting up a datasource on Tomcat server
2. Name of the mysql database: 'bidder'
3. Credentials to be changed as needed (my local mysql instance is probably configured a bit differently than yours)
4. I'm using hbm2ddl's "create-drop", but in real production's system that should be changed to 'update' or replaced with another more sophisticated solution (update is ok for rather simple solutions than complex ones) 
