package com.passersbyte.naturally.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.springframework.stereotype.Service;

import com.passersbyte.naturally.model.User;

@Service
public class DBUtilityService {
	
	
	String url = "jdbc:mysql://localhost:3306/db_naturally";
	String username = "springuser";
	String password = "ThePassword";
	String driver= "com.mysql.cj.jdbc.Driver";
  
  public void executeRawQuery() {
	  
	  
	  
      Connection con = null;

      try {

          Class.forName(driver);
          con = DriverManager.getConnection(url,username, password);

    	  PreparedStatement statement =con.prepareStatement("SELECT user_id, name, email, password FROM user WHERE name = ?");
    	  statement.setString(1, "Josh");
  
        	  ResultSet rs = statement.executeQuery();
              
        	  while (rs.next()) {
        		  User u = new User(rs.getInt("user_id"), rs.getString("name"), rs.getString("email"),rs.getString("password"));
    		  System.out.println(u.toString());
          }
          } catch (Exception ex) {
              System.out.println(ex);
          }
  }
	
}
