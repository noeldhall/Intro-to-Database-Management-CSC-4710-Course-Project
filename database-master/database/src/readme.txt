Group Members: Noel Hall gw0094, Elias Azar gx8641

To import the project war file into eclipse
-------------------------------------------
1. Click on File. Then click on export
2.Select War file under Web Category
3.Browse War file and click on finish. You will see the project on explorer.

Setting up the database
-----------------------
-connection setting used, automatically called during database operations:
connect = (Connection) DriverManager
            		 .getConnection("jdbc:mysql://127.0.0.1:3306/testdb?"
            				    + "user=john&password=pass1234");

-Initial_Registered_Users.txt contains the properties and login information of the 10 users created when the database is initialized
-The username and password of the root user for the web app are "root@wayne.edu" and "pass1234"
