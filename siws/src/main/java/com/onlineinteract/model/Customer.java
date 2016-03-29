package com.onlineinteract.model;

import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;

@Entity
public class Customer {

    @Id
    private ObjectId id;
	
	private String forname;
	private String surname;
	private String SIN;
	
	public Customer(String forname, String surname, String sIN) {
		this.forname = forname;
		this.surname = surname;
		SIN = sIN;
	}
	public String getForname() {
		return forname;
	}
	public void setForname(String forname) {
		this.forname = forname;
	}
	public String getSurname() {
		return surname;
	}
	public void setSurname(String surname) {
		this.surname = surname;
	}
	public String getSIN() {
		return SIN;
	}
	public void setSIN(String sIN) {
		SIN = sIN;
	}
}
