package application;
// Assignment 3 | Gabriel Bettio | 20010878 | 15gb24@queensu.ca | March 9 2018

/**
 * Exception thrown by Pizza object if parameters are not legal.
 * <ul>
 * <li> The Pizza order must be between 1 and 100 inclusive.</li>
 * <li> The size of the pizza is either "small", "medium", or "large".</li>
 * <li> The amount of cheese is either "single", "double", or "triple".</li>
 * <li> The amount of cheese is either "single", "double", or "triple".</li>
 * <li> The amount of mushrooms is either "none", "single", or "double".</li>
 * </ul>
 * @author gabrielbettio
 *
 */

public class IllegalPizza extends Exception {

	/**
	 * Supplies a default message.
	 */
	public IllegalPizza() {
		super("Illegal parameter value supplied to Pizza object.");
	}

	/**
	 * Passes along the message supplied to the exception.
	 * @param message A more specific message.
	 */
	public IllegalPizza(String message) {
		super(message);
	}

} // end IllegalPizza