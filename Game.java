import java.util.ArrayList;

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
        
    /**
     * Create the game and initialise its internal map.
     */
    public Game() 
    {
        createRooms();
        parser = new Parser();
    }

    /**
     * Create all the rooms and link their exits together.
     */
    private void createRooms()
    {
        Room ocelot, lewin, perch, blobfish, den, tahseen, orangutan, howler, rhino, nope;

        // creating the items
        Item tahseenMud = new Item("mud", 8.33, false);
        Item tahseenARandomCard = new Item("a random card", 0.06, false);
        Item rhinoReed = new Item("reed", 0.22, false);
        Item rhinoBeetle = new Item("beetles", 0.22, false);
        Item denMouse = new Item("mouse", 0.05, false);
        Item denSalt = new Item("bucket of salt", 100, false);
        Item blobfishPer = new Item("A perpendicular sign that is upside down and upside down again (right side up) with Pranav’s face attached to it", 337, false);
        Item blobfishRadio = new Item("radioactive slime", 25, false);
        Item perchWater = new Item("watermelon", 10, false);
        Item perchSnake = new Item("snake", 60, false);
        Item howlerNuts = new Item("barrel of nuts", 10, false);
        Item howlerEggs = new Item("eggs", 3, false);
        Item wildcatVole = new Item("vole", 0.06, false);
        Item wildcatStatue = new Item("Mini John Calipari Statue", 100, false);
        Item ocelotCard = new Item("cardinal", 0.1, false);
        Item ocelotShoot = new Item("Bamboo shoot", 2.2, false);
        Item orangutanInsects = new Item("insects", 0.001, false);
        Item orangutanFruits = new Item("fruits", 1.5, false);
        Item noInternetGrass = new Item("grass", 30, false);
        Item noInternetPerson = new Item("A person who screams \"There is no WIFI!\"", 8.33, false);
        
        // create the rooms
        ocelot = new Room("at the ocelot's hideout");
        lewin = new Room("at Mr. Lewin's Wildcat Kingdom");
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

        tahseen.setItem(tahseenMud);
        tahseen.setItem(tahseenARandomCard);
        rhino.setItem(rhinoBeetle);
        rhino.setItem(rhinoReed);
        perch.setItem(perchSnake);
        perch.setItem(perchWater);
        den.setItem(denMouse);
        den.setItem(denSalt);
        blobfish.setItem(blobfishPer);
        blobfish.setItem(blobfishRadio);
        lewin.setItem(wildcatStatue);
        lewin.setItem(wildcatVole);
        ocelot.setItem(ocelotCard);
        ocelot.setItem(ocelotShoot);
        orangutan.setItem(orangutanFruits);
        orangutan.setItem(orangutanInsects);
        howler.setItem(howlerEggs);
        howler.setItem(howlerNuts);
        nope.setItem(noInternetPerson);
        nope.setItem(noInternetGrass);
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
