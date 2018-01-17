#EhCache spring and hibernate with java config How to

In this example you will be shown to use built-in EhCache support in recent cache abstraction API in spring 4.2 and higher. We will be using an annotation-based approach and will follow the Java configuration.

Using mysql 5.1.31 version to support hibernate 4.3.1 session factory

Build Dependencies
___________________

| Requirement 	    | Version     |
|------------------|:----------: |
| Apache Maven     | 3.x         |
| Java JDK 	       | >= 8        |
| Eclipse / STS    | >= Helios   |
| Mysql 	          | = 5.1.31    |
| EhCache 	       | = 2.10.0    |
| Gson 	          | = 1.7.1     |

Building the Project
_____________________

   1. Compile: mvn clean jetty:run / run using server (Apache/pivotal)
   2. GoTo http://localhost:(-Port-)/ehcache-spring-hibernate-write-behind/
   3. Create Super Hero - Click on Add hero
   4. Gets the Hero by Id: Fill Id in textbox and Clicks on View button

<html>
<body>
	<form action="#">
		<h2>Super Heros</h2>
		<p>
		</p><h4>Menu:</h4>
		<p></p>		
			<li>   1) Add Hero</li>
			<br>
			<li>   2) View Heros</li>
			<br>
			<li>   3) View Heros By Id</li>		
		<br><br>			
		<div class="view_hero" style="display: block;">			
			<table style="border-collapse: collapse;" cellspacing="8" cellpadding="8" border="1">
				<thead>
					<tr>
						<th>Hero ID</th>
						<th>Hero Name</th>
						<th>Movie</th>
						<th>Hero Rank</th>
						<th>Hero Description</th>
						<th>Status</th>
					</tr>
				</thead>
				<tbody id="fill_content"><tr><td>1</td><td>Iron Man</td><td>IRONMAN - 1,2,3</td><td>1</td><td>na</td><td>Active</td></tr></tbody>
			</table>
      </div>
   </form>
</body>
</html>
