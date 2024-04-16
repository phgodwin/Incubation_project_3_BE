Project Name: LP shopping cart (BE)
Project Description: A simple back end for a shopping cart app. 
Note: You can find the coexisting front end here https://github.com/lucy-yates/FrontEnd_ReactLegacy
 
 
Features: 
-Create, read, update and delete items & carts. 
-Create & read past orders.
-Testing completed on full stack app on testingfix branch (coverage x%)
 
Project Limitations: 
-Many to one relationship between Item and cart - unable to add an Item to more than one cart.
-Past order has no relationship with item or cart, the front end pulls the information to push to past order 
-Item quantity does not yet reduce with orders.
 
 
Installation steps:
 
#Clone the repository
git clone https://github.com/lucy-yates/BackEnd_SpringLegacy.git
cd BackEnd_SpringLegacy
 
#Create your database
create legacy_shop schema in SQL workbench and update application.properties file to "create"
once complete change to update
 
#Run your application
start boot dashboard

Contributions welcome!
