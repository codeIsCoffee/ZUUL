/**
 * Class Player - a player in an adventure game.
 *
 * This class is part of the "The Nature Place" application. 
 * "The Nature Place" is a very simple, text based adventure game.  
 *
 * A "Player" represents one player in the game. 
 * 
 * @author  Tahseen Haque
 * @version 2023.05.17
 */

import java.util.ArrayList;

public class Player {

  private static final double MAX_WEIGHT = 1000;
  private static final int MAX_ITEMS = 5;
  private ArrayList<Item> playerItems; // stores items of this player
  private double currentWeight; // stores the current load of the player
  private boolean saltified; // stores whether the player used the saltify command
  private boolean snakeAttacked; // stores whether the player used the snakeAttack command

   /**
    * Create a player. Initially, it has no load.
    * There is also no playerItems and use of the correspondent
    * two commands initially. 
    */
  public Player() {
    currentWeight = 0;
    playerItems = new ArrayList<Item>();
    saltified = false;
    snakeAttacked = false;
  }

   /**
    * Adds an eligible item to the player.
    * @param item  The item that is to be added to the player if eligible.
    */
  public void addItem(Item item) {
    double weight = getTotalWeight() + item.getWeight();
    if (playerItems.size() < MAX_ITEMS && weight < MAX_WEIGHT) {
      playerItems.add(item);
    }
    else {
     System.out.println("There are too many items or too much weight!");
    }
  }

   /**
    * Gets the number of items in playerItems.
    * @return the number of items in playerItems.
    */
  public int getNumItems() {
    return playerItems.size();
  }

   /**
    * Removes an item from the player ArrayList of items. 
    * @param item  The item that is to be removed to the player if eligible.
    */
  public void removeItem(Item item) {
    if (playerItems.contains(item)) {
      playerItems.remove(item);
    }
  }
   
   /**
    * Returns the total weight of all the items that the player has.
    * @return the weight of all the items that the player has. 
    */
  public double getTotalWeight() {
   double weight = 0.0;
   for (int i = 0; i < playerItems.size(); i++) {
      Item item = playerItems.get(i);
      weight += item.getWeight();
    }
    return weight;
  }

    /**
     * Returns whether this player has or does not have a certain item.
     * @param an item object that is presumably with this player.
     * @return whether the player has the item.
     */
  public boolean hasItem(Item item) {
    for (Item aItem : playerItems) {
      if (aItem == item) {
        return true;
      }
    }
    return false;
  }

    /**
     * Sets the status of saltification.
     * @param check the new status of saltification.
     */
  public void setSaltified(boolean check) {
    saltified = check;
  }

    /**
     * Returns whether this player saltified the blobfish.
     * @return the status of saltification.
     */
  public boolean hasSaltified() {
    return saltified;
  }

    /**
     * Sets the status of the snakeAttack.
     * @param check the new status of the snakeAttack.
     */
  public void setSnakeAttacked(boolean check) {
    snakeAttacked = check;
  }

    /**
     * Returns whether this player snakeAttacked the cardinals.
     * @return the status of the snakeAttack.
     */
  public boolean hasSnakeAttacked() {
    return snakeAttacked;
  }

    /**
     * Returns an ArrayList of the items the player has. 
     * @return The items with the player.
     */
  public ArrayList<String> getPlayerItems() {
    ArrayList<String> itemNames = new ArrayList<String>();
    for (Item item : playerItems) {
      itemNames.add(item.getName());
    }
    return itemNames;
  }
}
  