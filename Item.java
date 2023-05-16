public class Item {

  private String itemName;
  private double weight;
  private boolean picked;
  
  public Item(String itemName, double weight, boolean picked) {
    this.itemName = itemName;
    this.weight = weight;
    this.picked = picked;
  }

  public void display() {
    if (picked == true) {
      System.out.println("You picked up a " + item.getName());
    }
  }

  public String getName() {
    return itemName;
  }

  public double getWeight() {
    return weight;
  }

  public boolean getHold() {
    return picked;
  }
}


