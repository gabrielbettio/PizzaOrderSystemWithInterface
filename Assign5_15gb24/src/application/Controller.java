// Gabriel Bettio || 20010878 || 15gb24@queensu.ca || Assignment 5 || April 6 2018
// The controller class is used to make the interface generated by SceneBuilder 
// interactive. It is used to make buttons, text fields, text, areas and other 
// objects interactive.

package application;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.beans.property.StringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.TextField;

public class Controller {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ToggleGroup sizeGroup;

    @FXML
    private ToggleGroup cheeseGroup;

    @FXML
    private RadioButton pNone;

    @FXML
    private ToggleGroup pepGroup;

    @FXML
    private RadioButton pSingle;

    @FXML
    private RadioButton pDouble;

    @FXML
    private RadioButton mNone;

    @FXML
    private ToggleGroup mushGroup;

    @FXML
    private RadioButton mSingle;

    @FXML
    private RadioButton mDouble;

    @FXML
    private TextField Quantity;
    
    @FXML
    private TextArea textArea;

    @FXML
    private Button calcCost;
    
    @FXML
    private TextField toCost;

    @FXML
    private Button Add;

    @FXML
    private TextField dispCost;

    @FXML
    private TextField singleCost;
    
    private Pizza pizza;
    
    private LineItem lineItem;
    
    private double totalCost;
    
	private static ArrayList<LineItem> orders = new ArrayList<>();
    
	// Makes button to calculate cost editable.
    @FXML
    void allowCost(ActionEvent event) {
		if(!Quantity.getText().equals(null) && calcCost.isDisabled())
			calcCost.setDisable(false);
	   } // end allowCost
    
    // Creates a pizza and LineItem in order to display the cost of a single pizza as well as
    // a line item of that pizza.
    @FXML
    void orderPizza(ActionEvent event) {
    		if(Quantity.getLength() == 0)
    			return;
		RadioButton button1 = (RadioButton) sizeGroup.getSelectedToggle();
		String size = button1.getText();
		RadioButton button2 = (RadioButton) cheeseGroup.getSelectedToggle();
		String cheese = button2.getText();
		RadioButton button3 = (RadioButton) mushGroup.getSelectedToggle();
		String mush = button3.getText();
		RadioButton button4 = (RadioButton) pepGroup.getSelectedToggle();
		String pep = button4.getText();
    		try {
    			pizza = new Pizza(size, cheese, mush, pep);
    			lineItem = new LineItem(Integer.parseInt(Quantity.getText()), pizza);
    			singleCost.setText("$" + Double.toString(pizza.getCost()) + "0");
    			dispCost.setText("$" + Double.toString(lineItem.getCost()) + "0");
    			if(Add.isDisabled()) 
    				Add.setDisable(false);
    		} catch (IllegalPizza e) {
    			e.printStackTrace();
    		}
    } // end orderPizza
    
    // Prints the line item onto the textArea. Also calculates the total cost of the order.
	@FXML
    void printItem(ActionEvent event) {
		RadioButton button1 = (RadioButton) sizeGroup.getSelectedToggle();
		String size = button1.getText();
		RadioButton button2 = (RadioButton) cheeseGroup.getSelectedToggle();
		String cheese = button2.getText();
		RadioButton button3 = (RadioButton) mushGroup.getSelectedToggle();
		String mush = button3.getText();
		RadioButton button4 = (RadioButton) pepGroup.getSelectedToggle();
		String pep = button4.getText();
		try {
			pizza = new Pizza(size, cheese, mush, pep);
			lineItem = new LineItem(Integer.parseInt(Quantity.getText()), pizza);
			totalCost += lineItem.getCost();
			toCost.setText("$" + totalCost + "0");
			orders.add(lineItem);
			textArea.appendText(lineItem.toString() + " $" + 
					Double.toString(lineItem.getCost()) + "0 total.\n");
			
		} catch (IllegalPizza e) {
			e.printStackTrace();
		}
    } // end printItem
    
	// Allows user to enter a number of pizzas only after mushrooms and pepperoni are selected.
    @FXML
    void pizzaClick(ActionEvent event) {
    		if(!Quantity.isEditable())
    			Quantity.setEditable(true);
    } // end pizzaClick

    // If none is selected for pepperonis, none for mushrooms is automatically chosen, and
    // the quantity field becomes editable for user input.
    @FXML
    void pepClick1(ActionEvent event) {
		mNone.setDisable(true); 
		mSingle.setDisable(true);
		mDouble.setDisable(true);
		if(!Quantity.isEditable())
			Quantity.setEditable(true);
		mushGroup.selectToggle(mNone);
    } // end pepClick1

    // If single or double is selected for pepperoni, then any mushroom selection becomes 
    // available.
    @FXML
    void pepClick2(ActionEvent event) {
		mNone.setDisable(false); 
		mSingle.setDisable(false);
		mDouble.setDisable(false);
    } // end pepClick2
    
    // Included a restriction for the Quantity textfield so that only integers from 1 to 100
    // are acceptable.
    @FXML
    void initialize() {
    		Quantity.textProperty().addListener((observableValue, oldText, newText) ->
    		{
    			if (newText != null && !newText.isEmpty()) {
    				try {
    					int aVal = Integer.parseInt(newText);
    					if(aVal < 1 || aVal > 100)
    						throw new NumberFormatException();
    				} catch (NumberFormatException e) {
    					((StringProperty)observableValue).setValue(oldText);
    				}
    			}
    		});
        assert sizeGroup != null : "fx:id=\"sizeGroup\" was not injected: check your FXML file 'FXMLbig.fxml'.";
        assert cheeseGroup != null : "fx:id=\"cheeseGroup\" was not injected: check your FXML file 'FXMLbig.fxml'.";
        assert pepGroup != null : "fx:id=\"pepGroup\" was not injected: check your FXML file 'FXMLbig.fxml'.";
        assert mNone != null : "fx:id=\"mNone\" was not injected: check your FXML file 'FXMLbig.fxml'.";
        assert mushGroup != null : "fx:id=\"mushGroup\" was not injected: check your FXML file 'FXMLbig.fxml'.";
        assert mSingle != null : "fx:id=\"mSingle\" was not injected: check your FXML file 'FXMLbig.fxml'.";
        assert mDouble != null : "fx:id=\"mDouble\" was not injected: check your FXML file 'FXMLbig.fxml'.";
        assert singleCost != null : "fx:id=\"singleCost\" was not injected: check your FXML file 'FXMLbig.fxml'.";
        assert calcCost != null : "fx:id=\"Cost\" was not injected: check your FXML file 'FXMLbig.fxml'.";
        assert dispCost != null : "fx:id=\"dispCost\" was not injected: check your FXML file 'FXMLbig.fxml'.";
        assert textArea != null : "fx:id=\"textArea\" was not injected: check your FXML file 'FXMLbig.fxml'.";
        assert Quantity != null : "fx:id=\"Quantity\" was not injected: check your FXML file 'FXMLbig.fxml'.";
        assert toCost != null : "fx:id=\"toCost\" was not injected: check your FXML file 'FXMLbig.fxml'.";
        assert Add != null : "fx:id=\"Add\" was not injected: check your FXML file 'FXMLbig.fxml'.";
    } // end initialize
} // end Controller class