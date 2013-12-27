angularPattern
=========

- Spring (Spring MVC, Spring Data)
- Angular.js 
- Hibernate (Oracle)
- MongoDb <br>
# => SAHM Stack

This app is deployed as below.<br>
http://goo.gl/RFMd3L

web app with angular.js, bootstrap and Spring restful

This is made with these traits.
	
	0. purpose : UI patten driven development support for angular.js application
	1. support UI pattern
	
	# 1-1. P1 Single Detail,
	  - image : images/1.png,
	  - desc : Perform on a screen for CRUD actions.,
	  - sample : apps/pattern1/index.html
	
	# 1-2. P2 Multi Detail (Edit),
	  - image : images/2.png,
	  - desc : Retrieve list and perform CRUD actions for multi-rows on a screen.,
	  - sample : apps/pattern2/index.html
	
	# 1-3. P3 Multi Detail (List to Edit),
	  - image : images/3.png,
	  - desc : Retrieve list and perform CRUD actions for 1 row using by two screens.,
	  - sample : apps/pattern3/index.html
	
	# 1-4. P4 Master / Detail [1:n],
	  - image : images/4.png,
	  - desc : Retrieve single master data and perform CRUD actions for multi detail data on a screen.,
	  - sample : apps/pattern4/index.html
	
	# 1-5. P5 Master / Detail [n:1]</h4>,
	  - image : images/5.png,
	  - desc : Retrieve multi master data and perform CRUD actions for sigle detail data on a screen.,
	  - sample : /
	
	# 1-6. P6 Master / Detail [n:n],
	  - image : images/6.png,
	  - desc : Retrieve multi master data and perform CRUD actions for multi detail data on a screen.,
	  - sample : /

	2. switching database
	/angularPattern/src/main/resources/environment-config.properties
	
	3. hibernate,mongodb
	com.tz.db.type=hibernate
	
* base context setting
1. /angularPattern/src/main/webapp/index.html <br>
  < head > <br>
  	< base href="http://topzone.dyndns.org:9080/angularPattern/" >
2. /angularPattern/src/main/webapp/scripts/app.js <br>
	url : 'http://topzone.dyndns.org\\:9080/rest',
	
	
	

