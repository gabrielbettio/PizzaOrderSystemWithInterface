package application;
// Assignment 3 | Gabriel Bettio | 20010878 | 15gb24@queensu.ca | March 9 2018

import java.io.Serializable;

/**
 * A class to store a information about an order of a certain style of pizza.
 * <p>
 * The style of pizza, as well as the number of pizzas desired are recorded. This class
 * is used in conjunction with Pizza and IllegalPizza to function as a pizza ordering system.
 * <p>
 * This version demonstrates the implementation of Comparable (for sorting) and 
 * Serializable (for filing). 
 * 
 * @author gabrielbettio
 */
public class LineItem implements Comparable<LineItem>, Serializable {

	private static final long serialVersionUID = -7001047868729328001L;
	private Pizza Pizzam;
	private int numPizza;

	/**
	 * Full parameter constructor.
	 * @param pCount Number of pizzas being ordered.
	 * @param pizza1 Type of pizza being ordered.
	 * @throws IllegalPizza If the pizza provided does not contain legal arguements.
	 */
	public LineItem(int pCount, Object pizza1) throws IllegalPizza {
		setNumber(pCount);
		if(!(pizza1 instanceof Pizza))
			throw new IllegalPizza("Illegal Pizza argument:" + pizza1);
		Pizzam = (Pizza) pizza1;
	} // end 2 parameter LineItem constructor
	
	/**
	 * Constructor for a single pizza.
	 * @param pizza1 Type of pizza being ordered.
	 * @throws IllegalPizza If the pizza provided does not contain legal arguments.
	 */
	public LineItem(Object pizza1) throws IllegalPizza {
		numPizza = 1;
		if(!(pizza1 instanceof Pizza))
			throw new IllegalPizza("Illegal Pizza argument:" + pizza1);
		Pizzam = (Pizza) pizza1;
	} // end 1 parameter LineItem constructor

	/**
	 * Sets the number of pizzas of a certain type to be ordered. 1 to 100 pizzas inclusive.
	 * @param pCount Number of pizzas being ordered.
	 * @throws IllegalPizza if less than 1 or more than 100 pizzas are ordered.
	 */
	public void setNumber(int pCount) throws IllegalPizza {
		if(pCount < 1 || pCount > 100)
			throw new IllegalPizza("Illegal number of Pizzas: " + pCount);
		this.numPizza = pCount;
	} // end setNumber mutator
	
	/**
	 * Returns the style of pizza in the order.
	 * @return Size, quantity of cheese, pepperoni, and mushrooms.
	 */
	public Object getPizza() {
		return Pizzam;
	} // end getPizza accessor
	
	/**
	 * Returns the number of pizzas being ordered.
	 * @return Number of pizzas in order.
	 */
	public int getNumber() {
		return numPizza;
	} // end getNumber accessor
	
	/**
	 * Returns the cost of the total order for "x" number of pizzas. Considers discounts off total
	 * price if over 10 pizzas are ordered.
	 * @return Total cost of order.
	 */
	public double getCost() {
		double cost = Pizzam.getCost();
		cost *= numPizza;
		if(numPizza > 9 && numPizza < 21)
			return cost *= .9;
		else if(numPizza > 20)
			return cost *= .85;
		return cost;
	} // end getCost accessor
	
	/**
	 * A string representation of the current object.
	 * @return A String representation of the contents of the object containing the values of
     * all the attributes.
	 */
	@Override
    public String toString() {
    	String p = null;
    	if(numPizza < 10) {
    		p = " ";
    		p += numPizza + " " + Pizzam.toString(); 
    		return p;
    	}
    	return p = numPizza + " " + Pizzam.toString();
    } // end toString
	
	/**
	 * Compares LineItem objects on the basis of their cost only.
     * @param otherPizza The other LineItem object.
     * @return A negative <code>int</code> if the supplied object costs more, zero if they cost the same
     * and a positive number if the current object costs more.
	 */
	@Override
	public int compareTo(LineItem otherPizza) {
		double compPizza = ((LineItem)otherPizza).getCost();
		double diff = compPizza - this.getCost();
		if(diff < 1 && diff > -1)
			return 0;
		else if(diff > .99)
			return 1;
		return -1;
	} // end compareTo
} // end LineItem
