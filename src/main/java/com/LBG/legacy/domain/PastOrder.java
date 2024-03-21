package com.LBG.legacy.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class PastOrder {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)

	private Integer id;
	private String customer;
//	@JsonManagedReference(value = "orderMade")
//	@OneToMany(mappedBy = "pastOrder")
//	private List<Item> items;

	private String purchased;

	public PastOrder() {
		super();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCustomer() {
		return customer;
	}

	public void setCustomer(String customer) {
		this.customer = customer;
	}

	public String getPurchased() {
		return purchased;
	}

	public void setPurchased(String purchased) {
		this.purchased = purchased;
	}

//	public List<Item> getItems() {
//		return items;
//	}
//
//	public void setItems(List<Item> items) {
//		this.items = items;
//	}

}