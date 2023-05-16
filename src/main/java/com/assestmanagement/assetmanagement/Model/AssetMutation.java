package com.assestmanagement.assetmanagement.Model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class AssetMutation {
 @Id
 @GeneratedValue(strategy=GenerationType.AUTO)
private int id;
private String assetName;
private int quantity;
private String notes;
@Column(columnDefinition="longBlob")
private String assetphoto;
@ManyToOne()
private User userList;
 public AssetMutation() {
}

public AssetMutation(int id, String assetName, int quantity, String notes, User userList,String assetphoto) {
    this.id = id;
    this.assetName = assetName;
    this.quantity = quantity;
    this.notes = notes;
    this.assetphoto=assetphoto;
    this.userList = userList;
}

public AssetMutation(String assetName, int quantity, String notes, User userList,String assetphoto) {
    this.assetName = assetName;
    this.quantity = quantity;
    this.notes = notes;
    this.assetphoto=assetphoto;
    this.userList = userList;
}

public User getUserList() {
    return userList;
}

public void setQuantity(int quantity) {
    this.quantity = quantity;
}

   public String getNotes() {
    return notes;
}
public void setNotes(String notes) {
    this.notes = notes;
}
public int getId() {
    return id;
}
public void setId(int id) {
    this.id = id;
}
public String getAssetName() {
    return assetName;
}
public int getQuantity() {
    return quantity;
}
public String setAssetphoto(){
    return assetphoto;
}
public String getAssetphoto(){
    return assetphoto;
}
}