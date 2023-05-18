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
    private HashMap<String, Item> itemMap; // map of itemNames for each item
    private HashMap<Room, Item> roomMap; // map of required items to enter each room
    private Room winningRoom; // the room where the players wins
    private Room snakeRoom; // the room where the player grabs the snake
    private Room blobfishRoom; // the room where the player uses the saltify command
        
    /**
     * Create the game and initialise its internal map.
     */
    public Game() 
    {
        itemMap = new HashMap<String, Item>();
        roomMap = new HashMap<Room, Item>();
        createItems();
        createRooms();
        parser = new Parser();
        player = new Player();
    }
  
    /**
     * Create all the items.
     */
    private void createItems() 
    {
        Item tahseenMud = new Item("mud", 8.33, false);
        itemMap.put("mud", tahseenMud);
      
        Item tahseenSpecialCard = new Item("aSpecialCard", 0.06, false);
        itemMap.put("aSpecialCard", tahseenSpecialCard);
      
        Item rhinoReed = new Item("reed", 0.22, false);
        itemMap.put("reed", rhinoReed);
      
        Item rhinoBeetle = new Item("beetles", 0.22, false);
        itemMap.put("beetles", rhinoBeetle);
      
        Item denMouse = new Item("mouse", 0.05, false);
        itemMap.put("mouse", denMouse);
      
        Item denSalt = new Item("salt", 100, false);
        itemMap.put("salt", denSalt);
      
        Item blobfishPer = new Item("sign", 337, false);
        itemMap.put("sign", blobfishPer);
      
        Item blobfishRadio = new Item("slime", 25, false);
        itemMap.put("slime", blobfishRadio);
      
        Item perchUnknown = new Item("unknown", 10, false);
        itemMap.put("unknown", perchUnknown);
      
        Item perchSnake = new Item("snake", 60, false);
        itemMap.put("snake", perchSnake);
      
        Item howlerNuts = new Item("nuts", 10, false);
        itemMap.put("nuts", howlerNuts);
      
        Item howlerEggs = new Item("eggs", 3, false);
        itemMap.put("eggs", howlerEggs);
      
        Item wildcatVole = new Item("vole", 0.06, false);
        itemMap.put("vole", wildcatVole);
      
        Item wildcatStatue = new Item("miniJohnCalipariStatue", 100, false);
        itemMap.put("miniJohnCalipariStatue", wildcatStatue);
      
        Item ocelotCard = new Item("cardinal", 0.1, false);
        itemMap.put("cardinal", ocelotCard);
      
        Item ocelotShoot = new Item("bambooShoot", 2.2, false);
        itemMap.put("bambooShoot", ocelotShoot);
      
        Item orangutanInsects = new Item("insects", 0.001, false);
        itemMap.put("insects", orangutanInsects);
      
        Item orangutanGrass = new Item("grass", 1.5, false);
        itemMap.put("grass", orangutanGrass);
      
        Item noInternetWatermelon = new Item("watermelon", 30, false);
        itemMap.put("watermelon", noInternetWatermelon);
      
        Item noInternetPerson = new Item("aPersonWhoScreams\"ThereIsNoWIFI!\"", 8.33, false);
        itemMap.put("aPersonWhoScreams\"ThereIsNoWIFI!\"", noInternetPerson);
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
        snakeRoom = perch;
        blobfish = new Room("at Pranav's blobfish kingdom");
        blobfishRoom = blobfish;
        den = new Room("at the gorilla's den");
        tahseen = new Room("at Tahseen's funny hippo location");
        orangutan = new Room("at the orangutan's hidden forest");
        howler = new Room("at the Howler's Howling Zone");
        rhino = new Room("at rhino's grassland");
        nope = new Room("at The Land With NO Internet Connection");
    
        
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
        ocelot.setExit("aSpecialPath", perch);

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
        tahseen.addItem(itemMap.get("aSpecialCard"));
        rhino.addItem(itemMap.get("beetles"));
        rhino.addItem(itemMap.get("reed"));
        perch.addItem(itemMap.get("unknown"));
        perch.addItem(itemMap.get("snake"));
        den.addItem(itemMap.get("mouse"));
        den.addItem(itemMap.get("salt"));
        blobfish.addItem(itemMap.get("slime"));
        blobfish.addItem(itemMap.get("sign"));
        lewin.addItem(itemMap.get("miniJohnCalipariStatue"));
        lewin.addItem(itemMap.get("vole"));
        ocelot.addItem(itemMap.get("cardinal"));
        ocelot.addItem(itemMap.get("bambooShoot"));
        orangutan.addItem(itemMap.get("insects"));
        orangutan.addItem(itemMap.get("grass"));
        howler.addItem(itemMap.get("nuts"));
        howler.addItem(itemMap.get("eggs"));
        nope.addItem(itemMap.get("watermelon"));
        nope.addItem(itemMap.get("aPersonWhoScreams\"ThereIsNoWIFI!\""));
      
        // places the required items need to enter each room
        roomMap.put(ocelot, itemMap.get("grass"));
        roomMap.put(lewin, itemMap.get("aPersonWhoScreams\"ThereIsNoWIFI!\""));
        roomMap.put(perch, itemMap.get("beetles"));
        roomMap.put(blobfish, itemMap.get("salt"));
        roomMap.put(den, itemMap.get("bambooShoot"));
        roomMap.put(orangutan, itemMap.get("watermelon"));
        roomMap.put(howler, itemMap.get("slime"));
        roomMap.put(rhino, itemMap.get("mud"));
        roomMap.put(nope, itemMap.get("snake"));
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
        System.out.println("Welcome to the \"Nature Place\"");
        System.out.println("The \"Nature Place\" is a new, incredibly fun adventure game.");
        System.out.println("Apparently, this world is a mess, and I hear that you need to get back to Kentucky. You need to collect items in various rooms to enter other rooms. The inhabitants of the other rooms need these items to preoccupy themselves while you quietly collect more items to enter more rooms. In some rooms, you need to use commands to exit the rooms themselves. You'll find that out later! Once you reach Mr. Lewin's Wildcat Kingdom, Mr. Lewin will lead you to a portal, which will get you back to Kentucky safely. (Hint: He will be glad that you snakeAttacked the cardinals and saltified the blobfish). Currently, your in my \"Funny Hippo Location,\" where we will help you get started on the treacherous journey! READ ME MR. LEWIN: TO WIN THE GAME, YOU NEED TO pick mud, go east, pick beetles, go east, pick snake, use the command snakeAttack, go north, pick watermelon, go west, pick grass, go west, drop all items except grass and snake, pick bambooShoot, go south, pick salt, go north, go west, use command saltify, go north, pick beetles, go aSpecialPath, drop salt, go north, look, pick the WIFI item (type the whole thing), go east, go south, you WON!. This is the only way to win. \n                                                                                                              - Tahseen");
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
        else if (commandWord.equals("saltify")) {
          saltifyRoom(command);
        }
        else if (commandWord.equals("snakeAttack")) {
          snakeAttackedRoom(command);
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
        System.out.println("You have the following items currently: " + player.getPlayerItems());
    }

    /** 
     * Try to in to one direction. If there is an exit 
     * and the player is allowed to enter, enter the new
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
            return;
        }
        // check conditions 
      
        if (nextRoom == winningRoom && !player.hasSaltified()) {
            System.out.println("You have to saltify the blobfish at the blobfishRoom by picking up the salt from the gorilla's den.");
         return;
        }

        // check if the player is in the blobfishRoom and check for saltification.  
        if (currentRoom == blobfishRoom && !player.hasSaltified()) {
            System.out.println("You are in the blobfishRoom. You must saltify to exit!");
            return;
        }

        // check if the player is in the snakeRoom (perch) and check for snakeAttacked.
        if (currentRoom == snakeRoom && !player.hasSnakeAttacked()) {
            System.out.println("You are at the cardinal's perch. You must snakeAttack to exit.");
            return;
        }

        // check for room precondition      
        Item requiredItem = roomMap.get(nextRoom);
        if (requiredItem != null && !player.hasItem(requiredItem)) {
           System.out.println("You must have " + requiredItem.getName() + " to enter!");
           return;
        }
      
        // since all the preconditions met, let the player go to the next room. 
        currentRoom = nextRoom;
        System.out.println(currentRoom.getLongDescription());
        System.out.println("You have the following items currently: " + player.getPlayerItems());
    }

    /** 
     * Look into the room. If there are items,
     * the items are printed out.
     * @param command The command to be processed.
     */
    private void lookInRoom(Command command) {
      String intro = "Here are the items: ";
      ArrayList<Item> theOfficialItems = currentRoom.getItems();

      for (int i = 0; i < theOfficialItems.size(); i++) {
        if (i == theOfficialItems.size() - 1) {
          intro += theOfficialItems.get(i).getName();
        }
        else {
          intro += theOfficialItems.get(i).getName() + ", ";
        }
       }
      System.out.println(intro);
      System.out.println("You have the following items currently: " + player.getPlayerItems());
    }

    /** 
     * Look into the room. If there is that item,
     * that item is picked, so that item is removed
     * from the room.
     * @param command The command to be processed.
     */
    private void pickItem(Command command) {
      if (command.hasSecondWord()) {
        // get the item name from the command
        String itemName = command.getSecondWord();
        if (itemMap.containsKey(itemName) && currentRoom.hasItem(itemMap.get(itemName))) {
          Item item = itemMap.get(itemName);
          
          // remove the item name from the currentRoom
          currentRoom.removeItem(item);

          // add the item to the player
          player.addItem(item);
          System.out.println("You picked up a/an or some " + itemName + ".");
        }
        else {
          System.out.println("Item does not exist or item is not present in this room.");
        }
        
      }
      else {
        System.out.println("What Item?");
      }
      System.out.println("You have the following items currently: " + player.getPlayerItems());
    }

    /** 
     * Look into the player. If the player has the item,
     * the item is dropped, so the item is added
     * to the room.
     * @param command The command to be processed.
     */
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
      System.out.println("You have the following items currently: " + player.getPlayerItems());
    }

    /** 
     * Look into the player and room. If the player has the salt
     * and the player is in the blobfishRoom, the player has saltified
     * the room.
     * @param command The command to be processed.
     */
    private void saltifyRoom(Command command) {
      if(player.hasItem(itemMap.get("salt"))) {
        if(currentRoom == blobfishRoom) {
          player.setSaltified(true);
          System.out.println("You attacked the blobfish!");
        }
        else {
          System.out.println("You must be in the blobfish room to saltify!");
        }
      }
      else {
        System.out.println("You don't have salt to saltify the blobfish!");
      }
      System.out.println("You have the following items currently: " + player.getPlayerItems());
    }

    /** 
     * Look into the player and room. If the player has the snake
     * and the player is in the snakeRoom (perch), the player has snakeAttacked
     * the cardinals.
     * @param command The command to be processed.
     */
    private void snakeAttackedRoom(Command command) {
      if(player.hasItem(itemMap.get("snake"))) {
        if(currentRoom == snakeRoom) {
          player.setSnakeAttacked(true);
          System.out.println("You attacked the cardinals!");
        }
        else {
          System.out.println("You must be at the cardinal's perch to snakeAttack!");
        }
      }
      else {
        System.out.println("You don't have the snake to snakeAttack the cardinals!");
      }
      System.out.println("You have the following items currently: " + player.getPlayerItems());
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
