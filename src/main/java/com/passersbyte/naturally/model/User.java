package com.passersbyte.naturally.model;

import java.util.Objects;

import javax.persistence.*;

@Entity // This tells Hibernate to make a table out of this class
public class User {
  @Id
  @GeneratedValue(strategy=GenerationType.AUTO)
  private Integer id;

  private String name;

  private String email;
  
  private String password;
  
  // for jdbc queries
  public User() {
		super();
  }
	
  //for jdbc queries
  public User(Integer id, String name, String email, String password) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.password = password;
  }
	
  public static User createUser(String name, String email, String password) {
		User n = new User();
		n.setName(name);
		n.setEmail(email);
		n.setPassword(password);
		return n;
  }

  public Integer getId() {
	return id;
  }
	
  public void setId(Integer userId) {
		this.id = userId;
  }

  public String getName() {
	    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }
  
  public String getPassword() {
		return password;
  }

  public void setPassword(String password) {
		this.password = password;
  }
  
  @Override
  public int hashCode() {
	 	return Objects.hash(email, name, password, id);
  }
	
  @Override
  public boolean equals(Object obj) {
	 	if (this == obj)
	 		return true;
	 	if (obj == null)
	 		return false;
	 	if (getClass() != obj.getClass())
	 		return false;
	 	User other = (User) obj;
	 	return Objects.equals(id, other.id);
  }

  @Override
  public String toString() {
		return "User [userId=" + id + ", name=" + name + ", email=" + email + ", password=" + password + "]";
  }

}
