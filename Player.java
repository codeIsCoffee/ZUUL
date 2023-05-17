import java.util.ArrayList;

public class Player {

  private static final double MAX_WEIGHT = 1000;
  private static final int MAX_ITEMS = 5;
  private ArrayList<Item> playerItems; 
  private double currentWeight;

  public Player() {
    currentWeight = 0;
    playerItems = new ArrayList<Item>();
  }

  public void addItem(Item item) {
    double weight = getTotalWeight() + item.getWeight();
    if (playerItems.size() < MAX_ITEMS && weight < MAX_WEIGHT) {
      playerItems.add(item);
    }
    else {
     System.out.print("There are too many items or too much weight!") 
    }
  }

  public int getNumItems() {
    return playerItems.size();
  }

  public void removeItem(Item item) {
    if (playerItems.contains(item)) {
      playerItems.remove(item);
    }
  }

  public double getTotalWeight() {
   double weight = 0.0;
   for (int i = 0; i < playerItems.size(); i++) {
      Item item = playerItems.get(i);
      weight += item.getWeight();
    }
    return weight;
  }
}
  