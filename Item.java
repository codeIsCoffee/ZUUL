/**
 * Class Item - an item in an adventure game.
 *
 * This class is part of the "The Nature Place" application. 
 * "The Nature Place" is a very simple, text based adventure game.  
 *
 * An "Item" represents one item in the game. 
 * 
 * @author  Tahseen Haque
 * @version 2023.05.17
 */

public class Item {

  private String itemName;
  private double weight; // stores the weight of the item.
  private boolean picked; // stores whether the item has been picked.

   /**
    * Create an item. Initially, it has whatever name, weight,
    * and picked given to it in the Game class. 
    * @param itemName  the name of the item.
    * @param weight  the weight of the item.
    * @param picked  whether the item has been picked.
    */
  public Item(String itemName, double weight, boolean picked) {
    this.itemName = itemName;
    this.weight = weight;
    this.picked = picked;
  }

  /**
    * Gets name of the item.
    * @return the item's name.
    */
  public String getName() {
    return itemName;
  }
  
   /**
    * Gets weight of the item.
    * @return the item's weight.
    */
  public double getWeight() {
    return weight;
  }

   /**
    * Gets wheter the item has been picked.
    * @return whether the item has been picked.
    */
  public boolean getHold() {
    return picked;
  }
}


