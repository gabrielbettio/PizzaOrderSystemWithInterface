package application;
// Assignment 3 | Gabriel Bettio | 20010878 | 15gb24@queensu.ca | March 9 2018
import java.io.Serializable;

/**
 * A class to store information about pizza.
 * <p>
 * The size, quantity of cheese, quantity of mushrooms, and quantity of pepperoni are all recorded. This class
 * is used in conjunction with LineItem and IllegalPizza to function as a pizza ordering system.
 * <p>
 * This version demonstrates the implementation of  and Serializable (for filing).  Also, 
 * the mutators for mushrooms and pepperoni have been combined, so both attributes have 
 * to be set at the same time.  In this way they cannot be set to an illegal value independently.
 * 
 * @author gabrielbettio
 */

public class Pizza implements Serializable {

	private static final long serialVersionUID = -5310676159045578486L;
	private String size;
    private String cheese;
    private String mushroom;
    private String pepperoni;
    
    /**
     * Full parameter constructor.
     * @param sz The size of the Pizza (small, medium, large)
     * @param chse The quantity of cheese (single, double, triple)
     * @param mshrms The quantity of mushrooms (none, single, double)
     * @param roni The quantity of pepperoni (none, single, double)
     * @throws IllegalPizza If arguments are not legal.
     */
    // 4 parameter constructor invokes mutators
    public Pizza(String sz, String chs, String mshrm, String roni) throws IllegalPizza {
        setSize(sz);
        setCheese(chs);
        setToppings(mshrm,roni);
    } // end Pizza 4 parameter constructor
    
    /**
     * Constructor of generic Small Pepperoni Pizza.
     * @throws IllegalPizza if arguments are not legal.
     */
    // Generates generic small pepperoni pizza 
    public Pizza() throws IllegalPizza {
        this.size = "small";
        this.cheese = "single";
        this.pepperoni = "single";
        this.mushroom = "none";
    } // end Pizza 0 parameter constructor
    
    /**
     * Sets the size of the pizza as either "small", "medium", or "large".
     * @param sz The size of the pizza.
     * @throws IllegalPizza if the size is not "small", "medium", or "large".
     */
    public void setSize(String sz) throws IllegalPizza {
    	if(sz == null)
    		throw new IllegalPizza("Null pointer not allowed.");
    	if(!sz.equalsIgnoreCase("small") && !sz.equalsIgnoreCase("medium") && 
    			!sz.equalsIgnoreCase("large"))
    		throw new IllegalPizza("Illegal Pizza size: " + sz);
    this.size = sz.toLowerCase();
    } // end setSize mutator
    
    /**
     * Sets the quantity of cheese on the pizza to either "single", "double", or "triple".
     * @param chs The amount of cheese desired on the pizza.
     * @throws IllegalPizza if the string is not "single", "double", or "triple".
     */
    public void setCheese(String chs) throws IllegalPizza {
    	if(chs == null)
    		throw new IllegalPizza("Null pointer not allowed.");
    	if((!chs.equalsIgnoreCase("single") && !chs.equalsIgnoreCase("double") && 
    			!chs.equalsIgnoreCase("triple")) || chs == null)
    		throw new IllegalPizza("Illegal cheese amount: " + chs);
    	this.cheese = chs.toLowerCase();
    } // end setCheese mutator
    
    /**
     * Sets the amount of pepperoni and mushrooms on a pizza. Mushrooms can only be
     * put on a pizza if there are also pepperoni on the pizza.
     * @param mshrm The quantity of mushrooms (none, single, double)
     * @param roni The quantity of pepperoni (none, single, double)
     * @throws IllegalPizza if the pepperoni string is not "none", "single", or "double", or
     * if the mushroom string is not "none", "single", or "double".
     */
    public void setToppings(String mshrm, String roni) throws IllegalPizza {
    	if(roni == null || mshrm == null)
    		throw new IllegalPizza("Null pointer not allowed.");
    	if((!roni.equalsIgnoreCase("none") && !roni.equalsIgnoreCase("single") && 
    			!roni.equalsIgnoreCase("double")) || roni == null)
    		throw new IllegalPizza("Illegal pepperoni amount: " + roni);
    	this.pepperoni = roni.toLowerCase();
    	if((!mshrm.equalsIgnoreCase("none") && !mshrm.equalsIgnoreCase("single") && 
    			!mshrm.equalsIgnoreCase("double")) || mshrm == null)
    		throw new IllegalPizza("Illegal mushroom amount: " + mshrm);
    	else if(!roni.equalsIgnoreCase("single") && !roni.equalsIgnoreCase("double") && !mshrm.equalsIgnoreCase("none"))
    		throw new IllegalPizza("Cannot have mushrooms without pepperoni!");
    	else 
    		this.mushroom = mshrm.toLowerCase();
    } // end setToppings mutator
    	
    /**
     * Gets the cost of a pizza given the components on the pizza. A small with single cheese is $7.00,
     * medium is $9.00, and large is $11.00. Each additional topping is $1.50.
     * @return The total cost of a pizza.
     */
    public double getCost() {
    	double cost = 0.00;
    	if(size.equals("small"))
    		cost = 7.00;
    	else if(size.equals("medium"))
    		cost = 9.00;
    	else
    		cost = 11.00;
    	if(cheese.equals("double"))
    		cost += 1.50;
    	else if(cheese.equals("triple"))
    		cost += 3.00;
    	if(pepperoni.equals("single"))
    		cost += 1.50;
    	else if(pepperoni.equals("double"))
    		cost += 3.00;
    	if(mushroom.equals("single"))
    		cost += 1.50;
    	else if(mushroom.equals("double"))
    		cost += 3.00;
    	return cost;
    } // end getCost	accessor
    
    /**
     * A String representation of the current object.
     * @return A String representation of the contents of the object containing the values of
     * all the attributes.
     */
    // Overrides (replaces) the toString method of the Object class.
    @Override
    public String toString() {
    	String msh = null, pep = null;
    	if(mushroom.equals("none"))
    		msh = "no";
    	else
    		msh = mushroom;
    	if(pepperoni.equals("none"))
    		pep = "no";
    	else 
    		pep = pepperoni;
    	String p = size + " pizza, " + cheese + " cheese, " + msh + " mushrooms, " + pep + " pepperoni.";
	p += " Cost: $" + getCost() + "0 each.";
    	return p;
    } // end toString
    
    /**
     * Tests two pizzas for equality.
     * @return <code>true</code> if all the attributes of both objects are exactly equal, <code>false</code>
     * otherwise.
     * @param otherObject The other Pizza object.
     */
    // Overrides the equals method of the Object class.
    @Override
    public boolean equals(Object otherObject) {
    	if (otherObject instanceof Pizza) {
    		Pizza otherPizza = (Pizza)otherObject;
    		if(size.equals(otherPizza.size))
    			if(cheese.equals(otherPizza.cheese))
    				if(pepperoni.equals(otherPizza.pepperoni))
    					if(mushroom.equals(otherPizza.mushroom))
    						return true;
    		}
    	return false;
    } // end equals
    
    /**
     * Returns a copy of the of the current Pizza object.
     * @return A copy of the current object.
     */
    // Overrides the clone method in the Object class.
    @Override
    public Pizza clone() {
    	Pizza zaCopy = null;
    	try {
			zaCopy = new Pizza(size, cheese, mushroom, pepperoni);
		} catch (IllegalPizza e) {
			// should never get here.
			return null;
		} // end try/catch
    	return zaCopy;
    } // end clone
} // end Pizza 
