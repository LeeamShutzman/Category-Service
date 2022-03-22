package com.example.demo.models;

import javax.persistence.*;

@Entity
@Table(name="CATEGORIES")
public class Category {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@JoinColumn(name="category_id", referencedColumnName="CATEGORYID")
	private long categoryID;
	@Column(name="CATEGORYNAME")
	private String categoryName;
	@Column(name="DESCRIPTION")
	private String description;
	public Category() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Category(long categoryID, String categoryName, String description) {
		super();
		this.categoryID = categoryID;
		this.categoryName = categoryName;
		this.description = description;
	}
	public long getCategoryID() {
		return categoryID;
	}
	public void setCategoryID(long categoryID) {
		this.categoryID = categoryID;
	}
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	@Override
	public String toString() {
		return "Category [categoryID=" + categoryID + ", categoryName=" + categoryName + ", description=" + description
				+ "]";
	}
	
	

}
