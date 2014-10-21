package com.namnguyen.object;

public class Product {
	
	private String name;
    private int id;
    private int unitPrice;
    private int quantity;
    private int total;
    
    public Product() {}
    
    public Product(String name,int id, int unitPrice, int quantity, int total) {
        this.name = name;
        this.id = id;
        this.unitPrice = unitPrice;
        this.quantity = quantity;
        this.total = total;
    }

    public String getName() 			{ return name; }
    public int getID() 					{ return id; }
    public int getUnitPrice() 			{ return unitPrice; }
    public int getQuantity() 			{ return quantity; }
    public int getTotal() 				{ return total; }
    
    public void setName(String value) 	{ name = value; }
    public void setID(int value) 		{ id = value; }
    public void setUnitPrice(int value) { unitPrice = value; }
    public void setQuantity(int value) 	{ quantity = value; }
    public void setTotal(int value) 	{ total = value; }
}
