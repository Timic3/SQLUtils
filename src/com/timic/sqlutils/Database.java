package com.timic.sqlutils;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public abstract class Database {
	protected Connection connection;
	
	protected Database() {
		this.connection = null;
	}
	
	// TODO: More specific exceptions in methods
	public abstract Connection open() throws Exception;
	
	public boolean check() throws Exception {
		return connection != null && !connection.isClosed();
	}
	
	public Connection get() {
		return connection;
	}
	
	public boolean close() throws Exception {
		if(connection == null)
			return false;
		
		connection.close();
		return true;
	}
	
	public ResultSet query(String query) throws Exception {
		if(!check())
			open();
		
		return connection.createStatement().executeQuery(query);
	}
	
	public int update(String query) throws Exception {
		if(!check())
			open();
		
		Statement statement = connection.createStatement();
		int result = statement.executeUpdate(query);
		statement.close();
		return result;
	}
	
}
