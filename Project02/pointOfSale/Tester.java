package pointOfSale;

import java.util.ArrayList;
import java.util.Iterator;

import javax.sound.midi.SysexMessage;

public class Tester {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Product product1 = new Product();
		Product product2 = new Product("Jelly", 123, 3.0, 4.0, 5 , "jelly");
		Product product3 = new Product("Vanilla Jelly", 234, 5.0, 4.0, 5 , "jelly");
		Product product4 = new Product("Ribeye", 567, 16.0, 10.0, 10 , "meat");
		
		//testing toString methods of Product.java
		System.out.println(product1.toString());
		System.out.println(product2.toString());
		//all good here
		
		//testing various getters and setters
		product1.set_productID(456);
		System.out.println(product1.get_productID());
		product1.set_productName("Ham");
		product1.set_productDiscountCode("mrspiggy2022");
		System.out.println(product1.get_productDiscountCode());
		//all goooooood
		
		//testing addProduct method
		System.out.println("adding");
		ProductCollection collection = new ProductCollection();
		collection.addProduct(product1);
		collection.addProduct(product2);
		collection.addProduct(product3);
		collection.addProduct(product4);
		System.out.println(collection.toString());
		//all good
		
		//testing retrieveProduct method
		System.out.println("retrieving");
		System.out.println(collection.retrieveProduct(123));
		//working
		
		//testing removeProduct method
		System.out.println("removing");
		collection.removeProduct(123);
		System.out.println(collection.toString());
		//all good
		
		//testing retrieveCollection method
		product1.set_productCategory("meat");
		System.out.println("retrieving collection");
		System.out.println(collection.retrieveCollection("meat"));
		//logic error
		
		//testing productUpdate method
		System.out.println("updating quantity");
		collection.productUpdate(234, 5);
		System.out.println(collection.toString());
		//all good
		
		//testing applyDiscount method
		System.out.println("applying discount");
		product1.set_productPrice(10.00);
		product1.set_productDiscountPercent(.15);
		System.out.println(collection.applyDiscount(456, "mrspiggy2022"));
		//works
		
		//testing productSuggestion
		System.out.println("suggesting product");
		System.out.println(collection.productSuggestion("meat"));
		
		//testing the writeFile method
		ProductCollection testWrite_Read = new ProductCollection("./pointOfSale/data.txt");
		//testWrite_Read.writeFile(testWrite_Read.get_fileName());
		
		// testing the iterator
		System.out.println("\ntesting iterator");
		Iterator<Product> iter = testWrite_Read.getIterator();
		while (iter.hasNext()) {
			System.out.println(iter.next());
		}
		System.out.println();
		
		// testing getCatagories
		System.out.println("\ntesting get all catagores");
		ArrayList<String> cats = testWrite_Read.getCatagories();
		System.out.println(cats);
		
		testWrite_Read.writeFile("./pointOfSale/testwrite.txt");
	}

}