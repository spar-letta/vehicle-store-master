package com.simiyu.enock.enock.model;

import javax.persistence.*;

@Entity
public class Vehicle {

	private @Id @GeneratedValue Long id;
	private String name;
	private String model;
	private String company;	
	private  @Column(columnDefinition = "text") String info;
	private double price;
	private double numberInStock;
	private double total;
	private boolean inCart;
	private String picPath;
	public Vehicle() {}	
	public Vehicle(String company,boolean inCart,String info, String model, String name,double numberInStock,String picPath, double price,double total) {
		this.company = company;
		this.inCart = inCart;
		this.info = info;		
		this.model = model;
		this.name = name;
		this.numberInStock = numberInStock;
		this.picPath = picPath;
		this.price = price;
		this.total = total;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getModel() {
		return model;
	}
	public void setModel(String model) {
		this.model = model;
	}
	public String getCompany() {
		return company;
	}
	public void setCompany(String company) {
		this.company = company;
	}
	public String getPicPath() {
		return picPath;
	}
	public void setPicPath(String picPath) {
		this.picPath = picPath;
	}	
	public String getInfo() {
		return info;
	}
	public void setInfo(String info) {
		this.info = info;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public double getTotal() {
		return total;
	}
	public void setTotal(double total) {
		this.total = total;
	}
	
	public boolean isInCart() {
		return inCart;
	}
	public void setInCart(boolean inCart) {
		this.inCart = inCart;
	}
	public double getNumberInStock() {
		return numberInStock;
	}
	public void setNumberInStock(double numberInStock) {
		this.numberInStock = numberInStock;
	}
	@Override
	public String toString() {
		return "Vehicle [id=" + id + ", company=" + company + ", inCart=" + inCart + ", info=" + info + ", model=" + model
				+ ", name=" + name + ", numberInStock=" + numberInStock + ", picPath=" + picPath + ", price=" + price
				+ ", total=" + total + "]";
	}
	
	
	
	
}
