import java.util.ArrayList;

public class Player {

  private static final double MAX_WEIGHT = 1000;
  private static final int MAX_ITEMS = 5;
  private double currentWeight;
  private int currentNumOfItems;

  public Player() {
    currentWeight = 0;
    currentNumOfItems = 0;
  }

  public double getWeight() {  
    return currentWeight;
  }

  public int getItems() {
    return currentNumOfItems;
  }

  public void setWeight(double weight) {
    currentWeight = weight;
  }

  public void setItems(int items) {
    currentNumOfItems = items;
  } 

  public double incrementWeight(Item item) {
    currentWeight += item.getWeight();
    if (currentWeight > MAX_WEIGHT) {
        System.out.println("You can't carry this!");
        currentWeight -= item.getWeight();
    }
    return currentWeight;
  }

  public double decrementWeight(Item item) {
    if (currentWeight <= MAX_WEIGHT) {
      currentWeight -= item.getWeight();
    }
    return currentWeight;
  }

  public void incrementItems() {
    currentNumOfItems += 1;
    if (currentNumOfItems < MAX_ITEMS) {
      System.out.println("You can't carry anymore items!");
    }
  }
  
  public void decrementItems() {
    currentNumOfItems -= 1;
    if (currentNumOfItems < 0) {
      currentNumOfItems = 0;
      System.out.println("You have no more items left!");
    }
  }
}
