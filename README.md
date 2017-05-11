# SQLUtils
SQL utilities for Java, supporting both, Oracle and MySQL backends

# Importing as library
Obviously, you'll have to import this library into your IDE
**I cannot provide download link to this library due to license restrictions**

## Eclipse
- Go to Project > Properties
- Choose 'Java Build Path'
- Click 'Libraries' tab
- Click on 'Add External JARs...'
- Add JAR there, then press 'Apply' and/or 'Ok'

## More IDEs later

# Documentation
I'll write more detailed documentation later. For now, here are some examples:

**MySQL/Oracle instance**
```java
// Initiate it only once, unless you need to connect to more databases
MySQL cMySQL = new MySQL("localhost", "rp", "root", "");
// To specify a port (default 3306 on MySQL), use:
MySQL cMySQL = new MySQL("localhost", 3306, "rp", "root", "");

Oracle cOracle = new Oracle("127.0.0.1", "pdbers", "root", "");
// Same as MySQL (default 1521 on Oracle), to use port:
Oracle cOracle = new Oracle("127.0.0.1", 1521, "pdbers", "root", "");
```

**Opening, closing, updating, fetching...**
```java
// To connect to MySQL/Oracle, you can use:
connection.open();
// and to close:
connection.close();
// Note that you can close and open connection multiple times,
// but it's best if you open it once, then close it when you exit the program

// To fetch data from table, use connection.query(query);
// DO NOT USE IT FOR UPDATING ROWS OR SUCH! ONLY USE IT FOR FETCHING DATA!
ResultSet result = connection.query("SELECT * FROM something");
// You can loop through it:
while(result.next()) {
	System.out.println(result.getString("row_name"));
}

// To update a row in the table or something:
connection.update("UPDATE something SET other='hello' WHERE world='bye'");
```

**Full example**
```java
// Don't forget the imports
public class Test {
	public static void main(String[] args) {
		MySQL cMySQL = new MySQL("localhost", "rp", "root", "");
		try {
			cMySQL.open();
			cMySQL.update("UPDATE accounts SET account_name='hello' WHERE account_name='world'");
			ResultSet result = cMySQL.query("SELECT * FROM accounts");
			while(result.next()) {
				System.out.println(result.getString("account_name"));
			}
			cMySQL.close();
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		Oracle cOracle = new Oracle("127.0.0.1", "pdbers", "tm", "");
		try {
			cOracle.open();
			cOracle.update("UPDATE avto SET znamka='Audi2' WHERE znamka='Audi'");
			ResultSet result = cOracle.query("SELECT * FROM avto");
			while(result.next()) {
				System.out.println(result.getString("znamka"));
			}
			cOracle.close();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}
```
