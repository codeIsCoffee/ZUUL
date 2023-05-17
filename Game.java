import java.util.ArrayList;
import java.util.HashMap;
/**
 *  This class is the main class of the "World of Zuul" application. 
 *  "World of Zuul" is a very simple, text based adventure game.  Users 
 *  can walk around some scenery. That's all. It should really be extended 
 *  to make it more interesting!
 * 
 *  To play this game, create an instance of this class and call the "play"
 *  method.
 * 
 *  This main class creates and initialises all the others: it creates all
 *  rooms, creates the parser and starts the game.  It also evaluates and
 *  executes the commands that the parser returns.
 * 
 * @author  Michael Kölling and David J. Barnes
 * @version 2016.02.29
 */

public class Game 
{
    private Parser parser;
    private Room currentRoom;
    private Player player;
    private HashMap<String, Item> itemMap;
    private Room winningRoom;
        
    /**
     * Create the game and initialise its internal map.
     */
    public Game() 
    {
        itemMap = new HashMap<String, Item>();
        createItems();
        createRooms();
        parser = new Parser();
        player = new Player();
    }

    private void createItems() 
    {
        Item tahseenMud = new Item("mud", 8.33, false);
        itemMap.put("mud", tahseenMud);
      
        Item tahseenSpecialCard = new Item("special card", 0.06, false);
        itemMap.put("special card", tahseenSpecialCard);
      
        Item rhinoReed = new Item("reed", 0.22, false);
        itemMap.put("reed", rhinoReed);
      
        Item rhinoBeetle = new Item("beetles", 0.22, false);
        itemMap.put("beetles", rhinoBeetle);
      
        Item denMouse = new Item("mouse", 0.05, false);
        itemMap.put("mouse", denMouse);
      
        Item denSalt = new Item("bucket of salt", 100, false);
        itemMap.put("bucket of salt", denSalt);
      
        Item blobfishPer = new Item("A perpendicular sign that is upside down and upside down again (right side up) with Pranav’s face attached to it", 337, false);
        itemMap.put("A perpendicular sign that is upside down and upside down again (right side up) with Pranav’s face attached to it", blobfishPer);
      
        Item blobfishRadio = new Item("radioactive slime", 25, false);
        itemMap.put("radioactive slime", blobfishRadio);
      
        Item perchWater = new Item("watermelon", 10, false);
        itemMap.put("watermelon", perchWater);
      
        Item perchSnake = new Item("snake", 60, false);
        itemMap.put("snake", perchSnake);
      
        Item howlerNuts = new Item("barrel of nuts", 10, false);
        itemMap.put("barrel of nuts", howlerNuts);
      
        Item howlerEggs = new Item("eggs", 3, false);
        itemMap.put("eggs", howlerEggs);
      
        Item wildcatVole = new Item("vole", 0.06, false);
        itemMap.put("vole", wildcatVole);
      
        Item wildcatStatue = new Item("Mini John Calipari Statue", 100, false);
        itemMap.put("Mini John Calipari Statue", wildcatStatue);
      
        Item ocelotCard = new Item("cardinal", 0.1, false);
        itemMap.put("cardinal", ocelotCard);
      
        Item ocelotShoot = new Item("Bamboo shoot", 2.2, false);
        itemMap.put("Bamboo shoot", ocelotShoot);
      
        Item orangutanInsects = new Item("insects", 0.001, false);
        itemMap.put("insects", orangutanInsects);
      
        Item orangutanFruits = new Item("fruits", 1.5, false);
        itemMap.put("fruits", orangutanFruits);
      
        Item noInternetGrass = new Item("grass", 30, false);
        itemMap.put("grass", noInternetGrass);
      
        Item noInternetPerson = new Item("A person who screams \"There is no WIFI!\"", 8.33, false);
        itemMap.put("A person who screams \"There is no WIFI!\"", noInternetPerson);
    }

    /**
     * Create all the rooms and link their exits together.
     */
    private void createRooms()
    {
        Room ocelot, lewin, perch, blobfish, den, tahseen, orangutan, howler, rhino, nope;
  
        // create the rooms
        ocelot = new Room("at the ocelot's hideout");
        lewin = new Room("at Mr. Lewin's Wildcat Kingdom");
        winningRoom = lewin;
        perch = new Room("at the cardinal's perch");
        blobfish = new Room("at Pranav's blobfish kingdom");
        den = new Room("at the gorilla's den");
        tahseen = new Room("at Tahseen's funny hippo location");
        orangutan = new Room("at the orangutan's hidden forest");
        howler = new Room("at the Howler's Howling Zone");
        rhino = new Room("at rhino's grassland");
        nope = new Room("The Land With NO Internet Connection");
    
        
        // initialise room exits
        tahseen.setExit("east", rhino);
        tahseen.setExit("south", howler);
        tahseen.setExit("west", den);
        tahseen.setExit("north", orangutan);
      
        rhino.setExit("west", tahseen);
        rhino.setExit("east", perch); 
        rhino.setExit("south", howler);

        perch.setExit("south", lewin);
        perch.setExit("north", nope);

        nope.setExit("east", perch);
        nope.setExit("west", orangutan);

        orangutan.setExit("east", nope);
        orangutan.setExit("west", ocelot);
        orangutan.setExit("south", tahseen);

        ocelot.setExit("east", orangutan);
        ocelot.setExit("south", den);
        ocelot.setExit("west", blobfish);
        ocelot.setExit("special path", perch);

        den.setExit("east", tahseen);
        den.setExit("north", ocelot);

        blobfish.setExit("north", ocelot);
        blobfish.setExit("east", howler);

        howler.setExit("west", blobfish);
        howler.setExit("north", tahseen);
        howler.setExit("east", rhino);

        lewin.setExit("north", perch);

        currentRoom = tahseen;  // start game at Tahseen's Funny Hippo Location 

        // initialize room items

        tahseen.addItem(itemMap.get("mud"));
        tahseen.addItem(itemMap.get("special card"));
        rhino.addItem(itemMap.get("beetles"));
        rhino.addItem(itemMap.get("reed"));
        perch.addItem(itemMap.get("watermelon"));
        perch.addItem(itemMap.get("snake"));
        den.addItem(itemMap.get("snake"));
        den.addItem(itemMap.get("bucket of salt"));
        blobfish.addItem(itemMap.get("radioactive slime"));
        blobfish.addItem(itemMap.get("A perpendicular sign that is upside down and upside down again (right side up) with Pranav’s face attached to it"));
        lewin.addItem(itemMap.get("Mini John Calipari Statue"));
        lewin.addItem(itemMap.get("vole"));
        ocelot.addItem(itemMap.get("cardinal"));
        ocelot.addItem(itemMap.get("Bamboo shoot"));
        orangutan.addItem(itemMap.get("insects"));
        orangutan.addItem(itemMap.get("fruits"));
        howler.addItem(itemMap.get("barrel of nuts"));
        howler.addItem(itemMap.get("eggs"));
        nope.addItem(itemMap.get("grass"));
        nope.addItem(itemMap.get("A person who screams \"There is no WIFI!\""));
    }

    /**
     *  Main play routine.  Loops until end of play.
     */
    public void play() 
    {            
        printWelcome();

        // Enter the main command loop.  Here we repeatedly read commands and
        // execute them until the game is over.
                
        boolean finished = false;
        while (! finished) {
            Command command = parser.getCommand();
            finished = processCommand(command);
            if (currentRoom == winningRoom) {
              System.out.println("You won by reaching Mr. Lewin's Wildcat Kingdom!");
              finished = true;
            }
            // create other winning situations
        }
        System.out.println("Thank you for playing. Good bye.");
    }

    /**
     * Print out the opening message for the player.
     */
    private void printWelcome()
    {
        System.out.println();
        System.out.println("Welcome to the World of Zuul!");
        System.out.println("World of Zuul is a new, incredibly fun adventure game.");
        System.out.println("Apparently, this world is a mess, and I hear that you need to get back to Kentucky. You need to collect items in various rooms to enter other rooms. The inhabitants of the other rooms need these items to preoccupy themselves while you quietly collect more items to enter more rooms. Once you reach Mr. Lewin's Wildcat Kingdom, Mr. Lewin will lead you to a portal, which will get you back ot Kentucky safely. Currently, your in my \"Funny Hippo Location\" where we will help you get started on the treacherous journey \n                                                                                                              - Tahseen");
        System.out.println("Type 'help' if you need help.");
        System.out.println();
        System.out.println(currentRoom.getLongDescription());
    }

    /**
     * Given a command, process (that is: execute) the command.
     * @param command The command to be processed.
     * @return true If the command ends the game, false otherwise.
     */
    private boolean processCommand(Command command) 
    {
        boolean wantToQuit = false;

        if(command.isUnknown()) {
            System.out.println("I don't know what you mean...");
            return false;
        }

        String commandWord = command.getCommandWord();
        if (commandWord.equals("help")) {
            printHelp();
        }
        else if (commandWord.equals("go")) {
            goRoom(command);
        }
        else if (commandWord.equals("quit")) {
            wantToQuit = quit(command);
        }
        else if (commandWord.equals("look")) {
            lookInRoom(command);
        }
        else if (commandWord.equals("pick")) {
            pickItem(command);
        }
        else if (commandWord.equals("drop")) {
            dropItem(command);
        }
        // else command not recognised.
        return wantToQuit;
    }

    // implementations of user commands:

    /**
     * Print out some help information.
     * Here we print some stupid, cryptic message and a list of the 
     * command words.
     */
    private void printHelp() 
    {
        System.out.println("You are lost. You are alone. You wander");
        System.out.println("around the \"nature place\".");
        System.out.println();
        System.out.println("Your command words are:");
        parser.showCommands();
    }

    /** 
     * Try to in to one direction. If there is an exit, enter the new
     * room, otherwise print an error message.
     */
    private void goRoom(Command command) 
    {
        if(!command.hasSecondWord()) {
            // if there is no second word, we don't know where to go...
            System.out.println("Go where?");
            return;
        }

        String direction = command.getSecondWord();

        // Try to leave current room.
        Room nextRoom = currentRoom.getExit(direction);

        if (nextRoom == null) {
            System.out.println("You can't enter this land.");
        }
        // check conditions 
        else {
            currentRoom = nextRoom;
            System.out.println(currentRoom.getLongDescription());
        }
    }
    private void lookInRoom(Command command) {
      String intro = "Here are the items: ";
      ArrayList<Item> theOfficialItems = currentRoom.getItems();

      // Alternative?
      for (int i = 0; i < theOfficialItems.size(); i++) {
        if (i == theOfficialItems.size() - 1) {
          intro += theOfficialItems.get(i).getName();
        }
        else {
          intro += theOfficialItems.get(i).getName() + ", ";
        }
       }
      System.out.println(intro);
    }

    private void pickItem(Command command) {
      if (command.hasSecondWord()) {
        // get the item name from the command
        String itemName = command.getSecondWord();
        if (itemMap.containsKey(itemName)) {
          Item item = itemMap.get(itemName);
          
          // remove the item name from the current room
          currentRoom.removeItem(item);

          // add the item to the player
          player.addItem(item);
        }
        else {
          System.out.println("Item does not exist.");
        }
        
      }
      else {
        System.out.println("What Item?");
      }
    }

    private void dropItem(Command command) {
      if (command.hasSecondWord()) {
        // get the item name from the command
        String itemName = command.getSecondWord();
        if (itemMap.containsKey(itemName)) {
          Item item = itemMap.get(itemName);
          
          // remove the item name from the current room
          currentRoom.addItem(item);

          // add the item to the player
          player.removeItem(item);
        }
        else {
          System.out.println("Item does not exist.");
        }
        
      }
      else {
        System.out.println("What Item?");
      }
    }
  
    /** 
     * "Quit" was entered. Check the rest of the command to see
     * whether we really quit the game.
     * @return true, if this command quits the game, false otherwise.
     */
    private boolean quit(Command command) 
      {
        if(command.hasSecondWord()) {
            System.out.println("Quit what?");
            return false;
        }
        else {
            return true;  // signal that we want to quit
        }
    }

   
}
