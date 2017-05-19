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

## NetBeans
- Go to File > Project Properties
- Click 'Libraries' tab
- Click on 'Add JAR/Folder'
- Add JAR there, then press 'Ok'

# Documentation
I'll write more detailed documentation later. For now, here are some examples:

**Syntax**
```java
// Initiate
MySQL/Oracle(String host, [ int port = 3306, ] String database, String username, String password);

// Connect to database
connection.open();

// Disconnect from database
connection.close();

// Execute/update
connection.update(String query);

// Query (returns ResultSet)
connection.query(String query);
```

**MySQL/Oracle instance**
```java
// Initiate it only once, unless you need to connect to more databases
// MySQL/Oracle(String host, [ int port, ] String database, String username, String password);
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
ResultSet result = connection.query("SELECT * FROM table");
// You can loop through it:
while(result.next()) {
	System.out.println(result.getString("row_name"));
}

// To update a row in the table or something:
connection.update("UPDATE table SET row='something' WHERE row='something_else'");
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
		
		Oracle cOracle = new Oracle("127.0.0.1", "pdbers", "root", "");
		try {
			cOracle.open();
			cOracle.update("UPDATE accounts SET account_name='hello' WHERE account_name='world'");
			ResultSet result = cOracle.query("SELECT * FROM accounts");
			while(result.next()) {
				System.out.println(result.getString("account_name"));
			}
			cOracle.close();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}
```
