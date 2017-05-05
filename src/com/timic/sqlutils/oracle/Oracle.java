package com.timic.sqlutils.oracle;

import java.sql.Connection;
import java.sql.DriverManager;

import com.timic.sqlutils.Database;

public class Oracle extends Database {
	private final String hostname;
	private final int port;
	private final String database;
	private final String username;
	private final String password;
	
	public Oracle(String hostname, String database, String username, String password) {
		this(hostname, 1521, database, username, password);
	}
	
	public Oracle(String hostname, int port, String database, String username, String password) {
		this.hostname = hostname;
		this.port = port;
		this.database = database;
		this.username = username;
		this.password = password;
	}
	
	@Override
	public Connection open() throws Exception {
		if(check())
			return connection;
		
		String connectionURL = "jdbc:oracle:thin:@"+this.hostname+":"+this.port;
		if(database != null)
			connectionURL += "/"+this.database;
		
		Class.forName("oracle.jdbc.driver.OracleDriver");
		connection = DriverManager.getConnection(connectionURL, this.username, this.password);
		
		return connection;
	}
	
}
