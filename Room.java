import java.util.Set;
import java.util.HashMap;
import java.util.ArrayList;

/**
 * Class Room - a room in an adventure game.
 *
 * This class is part of the "World of Zuul" application. 
 * "World of Zuul" is a very simple, text based adventure game.  
 *
 * A "Room" represents one location in the scenery of the game.  It is 
 * connected to other rooms via exits.  For each existing exit, the room 
 * stores a reference to the neighboring room.
 * 
 * @author  Michael Kölling and David J. Barnes
 * @version 2016.02.29
 */

public class Room 
{
  
    private String description;
    private HashMap<String, Room> exits; // stores exits of this room.
    private ArrayList<Item> items = new ArrayList<Item>(); //stores items of this room
  
    /**
     * Create a room described "description". Initially, it has
     * no exits. "description" is something like "a kitchen" or
     * "an open court yard".
     * @param description The room's description.
     */
    public Room(String description) 
    {
        this.description = description;
        exits = new HashMap<>();
    }

    /**
     * Define an exit from this room.
     * @param direction The direction of the exit.
     * @param neighbor  The room to which the exit leads.
     */
    public void setExit(String direction, Room neighbor) 
    {
        exits.put(direction, neighbor);
    }
  
     /** 
      * Adds an item to the ArrayList of items, which are in this room.
      * @param item  a new item object to be added to the room
      */
    public void addItem(Item item) {
        
        items.add(item);
    }
  
     /** 
      * Removes an item from the ArrayList of items, which are in this room.
      * @param item  a new item object to be removed to the room
      */
    public void removeItem(Item item) {
      if (items.contains(item)) {
        items.remove(item);
      }
    }

    /**
     * @return The short description of the room
     * (the one that was defined in the constructor).
     */
    public String getShortDescription()
    {
        return description;
    }

    /**
     * Return a description of the room in the form:
     *     You are in the kitchen.
     *     Exits: north west
     * @return A long description of this room
     */
    public String getLongDescription()
    {
        return "You are " + description + ".\n" + getExitString();
    }

    /**
     * Return a string describing the room's exits, for example
     * "Exits: north west".
     * @return Details of the room's exits.
     */
    private String getExitString()
    {
        String returnString = "Exits:";
        Set<String> keys = exits.keySet();
        for(String exit : keys) {
            returnString += " " + exit;
        }
        return returnString;
    }

    /**
     * Return the room that is reached if we go from this room in direction
     * "direction". If there is no room in that direction, return null.
     * @param direction The exit's direction.
     * @return The room in the given direction.
     */
    public Room getExit(String direction) 
    {
        return exits.get(direction);
    }

    /**
     * Returns an ArrayList of the items in the room. 
     * @return The items in the currentRoom.
     */
    public ArrayList<Item> getItems() {
      return items;
    }

    /**
     * Returns whether this room has or does not have a certain item.
     * @param an item object that is presumably in this room.
     * @return whether the room has the item.
     */
    public boolean hasItem(Item item) {
      for (Item aItem : items) {
        if (aItem == item) {
          return true;
        } 
      }
      return false;
  }
}