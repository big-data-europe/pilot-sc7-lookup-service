# pilot-sc7-lookup-service
LookupService is a Web Service that accepts a list with location names and returns a list with geometries.

Usage:
- download the dataset from here: https://github.com/big-data-europe/docker-lookup-service/blob/master/lookup-service/gadm28.zip
- unzip it
- Clone the repository
- Enter in pilot-sc7-lookup-service directory and change the private "final static String filePath" to point the unzipped gadm28.csv filepath in your machine
- $ mvn clean package
- copy the created .war from the pilot-sc7-lookup-service/target directory to /tomcat/webapps in a tomcat installation.
- Test the Web Service:
  Through Linux CLI: $ curl -X POST -d @location_names.json http://localhost:8080/lookupservice/geocode -H "Content-Type:application/json" -H "Accept: application/json"
- The location_names.json can have one location name like ["Greece"], or more.
- The service will return a (multi)polygon of the location.
