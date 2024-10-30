package model;


public class Product{
    
    private Long id;
    private String name;
    private double price;
    

    // Constructors
    public Product() {}


    public Product(String name2, double price2, model.Category byId) {
		
    	this.name = name2;
        this.price = price2;
        
	}

	// Getters and Setters
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

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

}
