package com.onlineinteract.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;

@Entity
@XmlRootElement
public class Customer {

    @Id
    private ObjectId id;
	
    @XmlElement
	private String forname;
    @XmlElement
	private String surname;
    @XmlElement
	private String SIN;
    @XmlElement
	private String customerId;
	
	// Note: important - needs empty constructor.
	public Customer(){}
	
	public Customer(String customerId, String forname, String surname, String sIN) {
		this.customerId = customerId;
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
	
	@Override
	public String toString() {
		return "blah";
	}

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}
}
