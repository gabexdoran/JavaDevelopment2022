package pointOfSale;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Iterator;

public class ProductCollection {
	private ArrayList<Product> products;
	private Iterator<Product> iter;
	private String fileName;

	//default
	public ProductCollection() {
		products = new ArrayList<Product>();
		fileName = null;
	}

	//with fileName
	public ProductCollection(String fn) {
		products = new ArrayList<Product>();
		fileName = fn;
		readFile();
	}

	//returns the private member fileName
	public String get_fileName() {return fileName;}

	//adds product to the end of the arraylist
	public void addProduct(Product p) {
		products.add(p);
	}

	//takes in an ID as a parameter and removes the object from the collection
	public boolean removeProduct(int id) {
		return products.remove(new Product(id));

	}

	//applies a discount to the product (given by id)
	public double applyDiscount(int id, String discountCode) {
		double price = 0;
		iter = products.iterator();
		while (iter.hasNext()) {
			Product p = iter.next();
			if (p.get_productID() == id && p.get_productDiscountCode().equals(discountCode)) {
				//first multiplies productPrice by productDiscountPercent
				//then subtracts the product from productPrice
				//this will work even if productDiscountPercent is 0
				price = p.get_productPrice()-(p.get_productPrice()*p.get_productDiscountPercent());
			}
		}
		//.setScale takes in two parameters
		//@param 1 is the scale, in this case to the hundreth place
		//@param 2 is the rounding mode, in this case it rounds to the nearest neighbor unless equal, then rounds up
		return price;
	}

	//takes in an ID as a parameter and returns the object
	public Product retrieveProduct(int id) {
		iter = products.iterator();
		while (iter.hasNext()) {
			Product p = iter.next();
			if (p.get_productID() == id) {
				return p;
			}
		}
		return null;
	}

	//takes in an ID as a parameter and subtracts the quantity by quantityBought
	public void productUpdate(int id, int quantityBought) {
		iter = products.iterator();
		while (iter.hasNext()) {
			Product p = iter.next();
			if (p.get_productID() == id) {
				if (p.get_productQuantity() >= quantityBought) {
					p.set_productQuantity(p.get_productQuantity() - quantityBought);
				}
				else {
					System.out.println("Not enough in stock.");

				}
			}
		}
	}

	//takes in an identifier as a parameter and returns similar items
	public ArrayList<Product> retrieveCollection(String category) {
		//collection is used to store similar products based off of the parameter of category
		ArrayList<Product> collection = new ArrayList<Product>();
		iter = products.iterator();
		while (iter.hasNext()) {
			Product p = iter.next();
			//next line checks if the product's category is the same as the parameter
			if (p.get_productCategory().equals(category)) {
				collection.add(p);
			}
		}
		return collection;
	}

	public ArrayList<Product> productSuggestion(String name) {
		//collection is used to store similar products based off of the parameter of name
		ArrayList<Product> collection = new ArrayList<Product>();
		iter = products.iterator();
		while (iter.hasNext()) {
			Product p = iter.next();
			//checks equivalence of name and category to the parameter of name
			if (p.get_productName().contains(name) || p.get_productCategory().contains(name)) {
				collection.add(p);
			}
		}
		return collection;
	}
	public Iterator<Product> getIterator () {
		// returns the 
		return products.iterator();
	}

	public ArrayList<String> getCatagories () {
		//collection is used to store add types of catagories
		ArrayList<String> collection = new ArrayList<String>();
		iter = products.iterator();
		while (iter.hasNext()) {
			Product p = iter.next();
			//checks equivalence of name and category to the parameter of name
			if (!collection.contains(p.get_productCategory())) {
				collection.add(p.get_productCategory());
			}
		}
		return collection;		
	}
	//method to read from file
	protected void readFile() {
		// data file format:
		// productName,id,price,quantityOnHand,weight,code,catagory
		//Name,123,2.99,12,2.0,5,Category

		BufferedReader lineReader = null;
		try {
			FileReader fr = new FileReader(fileName);
			lineReader = new BufferedReader(fr);
			String line = null;
			while ((line = lineReader.readLine()) != null) {
				String[] tokens = line.split(",");
				//				String id = lineReader.readLine();
				//				String price = lineReader.readLine();
				//				String weight = lineReader.readLine();
				//				String quantity = lineReader.readLine();
				//				String code = lineReader.readLine();					//if no code, please enter "nocode"
				//				String discountPercent = lineReader.readLine();			//if no code, please enter 0.0
				//				String category = lineReader.readLine();
				addProduct(new Product(tokens[0], Integer.parseInt(tokens[1]), Double.parseDouble(tokens[2]), 
						Double.parseDouble(tokens[3]), Integer.parseInt(tokens[4]), tokens[5], 
						Double.parseDouble(tokens[6]), tokens[7]));
			}
			//			System.err.println(this);
		}
		catch (Exception e) {
			System.err.println("there was a problem with the first file reader, try different read type.");
			e.printStackTrace();
			try {
				lineReader = new BufferedReader(new InputStreamReader(this.getClass().getResourceAsStream(fileName.substring(1))));
				String line = null;
				while ((line = lineReader.readLine()) != null) {
					String[] tokens = line.split(",");
					//					String id = lineReader.readLine();
					//					String price = lineReader.readLine();
					//					String weight = lineReader.readLine();
					//					String quantity = lineReader.readLine();
					//					String code = lineReader.readLine();					//if no code, please enter "nocode"
					//					String discountPercent = lineReader.readLine();			//if no code, please enter 0.0
					//					String category = lineReader.readLine();
					addProduct(new Product(tokens[0], Integer.parseInt(tokens[1]), Double.parseDouble(tokens[2]), 
							Double.parseDouble(tokens[3]), Integer.parseInt(tokens[4]), tokens[5], 
							Double.parseDouble(tokens[6]), tokens[7]));
				}
				//System.err.println(this.products);
			}
			catch (Exception e2) {
				System.err.println("there was a problem with the second file reader, try again.  either no such file or format error");
				e2.printStackTrace();
			} finally {
				if (lineReader != null)
					try {
						lineReader.close();
					} catch (IOException e2) {
						System.err.println("could not close BufferedReader");
					}
			}			
		} finally {
			if (lineReader != null)
				try {
					lineReader.close();
				} catch (IOException e) {
					System.err.println("could not close BufferedReader");
				}
		}
	}

	//method to write contents of products to a file
	//@param fn refers to file name
	public void writeFile(String fn) {
		try {
			FileWriter fw = new FileWriter(fn);
			BufferedWriter myOutfile = new BufferedWriter(fw);

			iter = products.iterator();
			while (iter.hasNext()) {
				Product p = iter.next();
				//starts to write contents of product to a file
				myOutfile.write(p.get_productName()+",");
				myOutfile.write(p.get_productID()+",");
				myOutfile.write(p.get_productPrice()+","); 
				myOutfile.write(p.get_productWeight()+",");
				myOutfile.write(p.get_productQuantity()+",");
				myOutfile.write(p.get_productDiscountCode()+",");
				myOutfile.write(p.get_productDiscountPercent()+",");
				myOutfile.write(p.get_productCategory()+"\n");
			}
			myOutfile.flush();
			myOutfile.close();		
		}
		catch (Exception e) {
			e.printStackTrace();
			System.err.println("Didn't save to " + fn);
		}
	}

	public String toString() {
		String toReturn = "";
		for (int i = 0; i < products.size(); i++) {
			toReturn += products.get(i).toString() + "\n";
		}
		return toReturn;
	}

}
