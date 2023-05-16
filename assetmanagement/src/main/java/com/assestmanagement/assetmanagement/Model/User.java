package com.assestmanagement.assetmanagement.Model;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    
    private String email;
   private String name;
   @OneToMany(mappedBy = "userList")
   private List<AssetMutation>assetmutation;
    public String getName() {
    return name;
}
    private String userName;
    
    private String password;

    public User(int id, String email, String name, String userName, String password) {
        this.id = id;
        this.email = email;
        this.name = name;
        this.userName = userName;
        this.password = password;
    }

    public User(String email, String name, String userName, String password) {
        this.email = email;
        this.name = name;
        this.userName = userName;
        this.password = password;
    }

    public User() {
    }
 
    public String getPassword() {
        return password;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getEmail() {
        return email;
    }
    public String getUserName() {
        return userName;
    }
}
