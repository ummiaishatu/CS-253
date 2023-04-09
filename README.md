# Data-representation-markup-languages-and-web-services-CW2-REST-Mountain-database-

For this assessment, you are being asked to build a simple web-service-based ferry database application.

- Grade : 86.5%

Task Specification: You need to write a REST Web Service application that provides the database functionality (see below); and a client application (either Java or web-page-based) to show that the Web Service is working.

You need to implement the following REST Web Service methods which implement a simple database containing information about mountains, mountain ranges and countries.

1. Add a mountain - a method that adds a new mountain (a name, the mountain range it belongs to, the country that mountain range is in, it's height in metres, and it's hemisphere (Northern or Southern). For example, a REST client in Java might look like addMountain("Snowden", "Snowdonia", "Wales", 1080, "Northern"). If the country and/or mountain range do not already exist, the call should create then. If a mountain of that name, in that range and country, already exists, then your code should do something sensible.
2. Delete a mountain - remove a mountain by name, range and country (that is: you must specify all of the parameters: mountain name; mountain range; country). If there is no such mountain, again your code should do something sensible.
3. Update a mountain's height - by specifying it's name, range and country (as in 2 above) and a new height, update the height of the a mountain. For example, upDateMountain("Snowden", "Snowdonia", "Wales", 1085) - note you don't need to specify if it's in the Northern or Southern Hemisphere here. Once again, do something sensible if there is no such mountain.
4. Get mountain height by name, range, country - return the height of the named mountain, in the named country and range. Again, do something sensible if there is no such mountain.
5. Get all mountains by range and country - return all mountains in the specified country and range (the data returned should be name and height).
6. Get all mountains by country - return all mountains in the specified country  (the data returned should be name and height).
7. Get all mountains by hemisphere - return all mountains in the specified hemisphere.
