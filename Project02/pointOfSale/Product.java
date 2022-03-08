package pointOfSale;

import java.text.DecimalFormat;

public class Product {
	private String productName;
	private int productID;
	private double productPrice; 		//BigDecimal is used in order to reduce rounding errors
	private double productWeight;	 		//please measure in ounces
	private int productQuantity;			
	private String productDiscountCode;		//code should be added after product object has been created
	private double productDiscountPercent;	//to calculate, divide percentage of discount by 100: ie... 15% = 15/100= .15
	private String productCategory;
	private static final DecimalFormat df = new DecimalFormat("0.00"); // formats doubles to two decimal places

	//default constructor
	public Product() {
		productName = "";
		productID = 0;
		productPrice = 0;
		productWeight = 0.0; 
		productQuantity = 0;
		productDiscountCode = "";
		productDiscountPercent = 0.0;
		productCategory = "";
	}

	//constructor specifically for remove method
	public Product(int id) {
		productName = "";
		productID = id;
		productPrice = 0;
		productWeight = 0.0; 
		productQuantity = 0;
		productDiscountCode = "";
		productDiscountPercent = 0.0;
		productCategory = "";
	}

	//parameterized constructor
	//productDiscountCode will be added later
	//productDiscountPercent will be added later
	public Product(String name, int id, double price, double weight, int quantity, String category) {
		productName = name;
		productID = id;
		productPrice = price;
		productWeight = weight; 
		productQuantity = quantity;
		productDiscountCode = "";
		productDiscountPercent = 0.0;
		productCategory = category;
	}

	//constructor used to read from file
	public Product(String name, int id, double price, double weight, int quantity, String code, double percent, String category) {
		productName = name;
		productID = id;
		productPrice = price;
		productWeight = weight; 
		productQuantity = quantity;
		productDiscountCode = code;
		productDiscountPercent = percent;
		productCategory = category;
	}

	//starting getters and setters
	public String get_productName() {return productName;}
	public void set_productName(String name) {productName = name;}

	public int get_productID() {return productID;}
	public void set_productID(int id) {productID = id;}

	public double get_productPrice() {return (1.0*Math.round(productPrice*100)/100);}
	public void set_productPrice(double price) {productPrice = price;}

	public double get_productWeight() {return productWeight;}
	public void set_productWeight(double weight) {productWeight = weight;}

	public int get_productQuantity() {return productQuantity;}
	public void set_productQuantity(int quantity) {productQuantity = quantity;}

	public String get_productDiscountCode() {return productDiscountCode;}
	public void set_productDiscountCode(String code) {productDiscountCode = code;}

	public double get_productDiscountPercent() {return productDiscountPercent;}
	public void set_productDiscountPercent(double discount) {productDiscountPercent = discount;}

	public String get_productCategory() {return productCategory;}
	public void set_productCategory(String category) {productCategory = category;}
	//ending getters and setters

	public String toString() {

		String toReturn = "Product: " + get_productName() + " (ID: " + get_productID() + ") " + "costs: $" + 
				df.format(get_productPrice()) + ". There are " + 	get_productQuantity() + " in stock." +
				" current discount percent: "+ this.get_productDiscountPercent()*100+"%";
		return toReturn;
	}

	//method to allow removeProduct to remove a product while taking an ID as a parameter
	public boolean equals(Object o) {
		//first type casts 'Object o' to a 'Product'
		//then compares by ID
		//returns true if IDs match
		return ((Product)o).get_productID() == this.productID;
	}

}