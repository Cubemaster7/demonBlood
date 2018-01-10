//ideas: inventory cap, durability
import java.util.*;
public class willgametest{
  public static void main(String[] args){
    Scanner input = new Scanner(System.in);
    Character person = new Character(1, 2);
    person.setCoins(100);
    int length = 4;
    Weapon[] weaponsList = new Weapon[length];
    weaponsList = weaponSetter(weaponsList);
    int inventorySize = 0;
    Weapon[] inventory = new Weapon[inventorySize];
    inventory = pickUpWeapon(weaponsList, inventory, 0);
    inventory = pickUpWeapon(weaponsList, inventory, 1);
    inventory = pickUpWeapon(weaponsList, inventory, 2);
    inventory = pickUpWeapon(weaponsList, inventory, 3);
    shopping(weaponsList, inventory, person);
    input.close();
  }
  public static int valueCheckerInt(int answer, int lowBound, int highBound){  //method for error handling for numbers that have to be > 0
    Scanner input = new Scanner(System.in);
    answer = input.nextInt();
    while(answer < lowBound || answer > highBound){
      System.out.println("Invalid input. Please try again");
      answer = input.nextInt();
    }
    input.close();
    return answer;
  }
  public static void itemPrinter(Weapon[] weaponsList, int userChoice, boolean selling, Character person){
    System.out.println(weaponsList[userChoice].getName());
    System.out.println(weaponsList[userChoice].getDesc());
    System.out.println("Modifier: " + weaponsList[userChoice].getMod());
    System.out.println("In order to use it, you must be level " + weaponsList[userChoice].getReq());
    if(selling){
      System.out.print("This weapon will sell for " + weaponsList[userChoice].getSell() + " coins.");
    }
    else{
      System.out.print("This weapon will cost " + weaponsList[userChoice].getCost() + " coins to purchase.");
    }
    System.out.println(" You have " + person.getCoins() + " coins.");
  }
  public static Weapon[] weaponSetter(Weapon[] weaponsList){
    weaponsList[0] = new Weapon();
    weaponsList[0].setName("Steel Sword");
    weaponsList[0].setDesc("The most basic a sword can be");
    weaponsList[0].setLocation("Town1");
    weaponsList[0].setMod(1.5);
    weaponsList[0].setLevel(0);
    weaponsList[0].setReq(0);
    weaponsList[0].setUpgrades("none");
    weaponsList[0].setCost(10);
    weaponsList[0].setSell(5);
    weaponsList[0].setHave(true);
    weaponsList[0].setTestShop(true);
    weaponsList[0].setId(0);
    
    weaponsList[1] = new Weapon();
    weaponsList[1].setName("FlameSword");
    weaponsList[1].setDesc("A sword with a firey twist");
    weaponsList[1].setLocation("EnemyDrop");
    weaponsList[1].setMod(1.3);
    weaponsList[1].setLevel(0);
    weaponsList[1].setReq(1);
    weaponsList[1].setUpgrades("none");
    weaponsList[1].setCost(20);
    weaponsList[1].setSell(10);
    weaponsList[1].setHave(true);
    weaponsList[1].setTestShop(true);
    weaponsList[1].setId(1);
    
    weaponsList[2] = new Weapon();
    weaponsList[2].setName("Dagger");
    weaponsList[2].setDesc("A short blade");
    weaponsList[2].setLocation("Starter Village");
    weaponsList[2].setMod(1.0);
    weaponsList[2].setLevel(0);
    weaponsList[2].setReq(0);
    weaponsList[2].setUpgrades("none");
    weaponsList[2].setCost(5);
    weaponsList[2].setSell(2);
    weaponsList[2].setHave(true);
    weaponsList[2].setTestShop(true);
    weaponsList[2].setId(2);
    
    weaponsList[3] = new Weapon();
    weaponsList[3].setName("FrostBlade");
    weaponsList[3].setDesc("Chills to the bone");
    weaponsList[3].setLocation("EnemyDrop");
    weaponsList[3].setMod(2.0);
    weaponsList[3].setLevel(0);
    weaponsList[3].setReq(1);
    weaponsList[3].setUpgrades("none");
    weaponsList[3].setCost(20);
    weaponsList[3].setSell(10);
    weaponsList[3].setHave(true);
    weaponsList[3].setTestShop(true);
    weaponsList[3].setId(3);
    return weaponsList;
  }
  public static void shopping(Weapon[] weaponsList, Weapon[] inventory, Character person){
    Scanner input = new Scanner(System.in);
    Weapon[] shopList = new Weapon[0];
    shopList = setShopItems(weaponsList, shopList);
    System.out.println("Welcome to my store.");
    input.nextLine();
    while(true){
      System.out.println("1. Buy");
      System.out.println("2. Sell");
      System.out.println("3. Exit               (You have " + person.getCoins() + " coins)");
      int choice = valueCheckerInt(0, 1, 3);
      if(choice == 1){
        while(true){
          System.out.println("Here are the weapons I have in stock:");
          int counter = 0;
          for(int i = 0; i < shopList.length; i ++){
            System.out.println((i+1) + ". " + shopList[i].getName());
            counter = i;
          }
          System.out.println((counter + 2) + ". Exit");
          System.out.println("Are you interested in any?    (You have " + person.getCoins() + " coins)");
          int choice2 = valueCheckerInt(0, 1, shopList.length + 1);
          if(choice2 <= shopList.length){ //if the choice is one of the weapons
            itemPrinter(shopList, choice2 - 1, false, person);
            System.out.println("Would you like to purchase the " + shopList[choice2 - 1].getName() + "?");
            System.out.println("1. Yes");
            System.out.println("2. No");
            int choice3 = valueCheckerInt(0, 1, 2);
            if(choice3 == 1){
              System.out.println("-" + shopList[choice2 - 1].getCost() + " coins");
              person.setCoins(person.getCoins() - shopList[choice2 - 1].getCost());
              System.out.println("Here you go! Enjoy");
              inventory = pickUpWeapon(weaponsList, inventory, getWeaponId(weaponsList, shopList, (choice2 - 1)));
              shopList = decreaseSize(shopList, (choice2 - 1));
            }
          }
          else{ // if the user chooses to exit
            break;
          }
        }
      }
      if(choice == 2){
        while(true){
          if(inventory.length > 0){
            printInventory(inventory);
            System.out.println((inventory.length + 1) + ". Exit");
            System.out.println("Which would you like to sell?     (You have " + person.getCoins() + " coins)");
            int choice2 = valueCheckerInt(0, 1, inventory.length + 1);
            if(choice2 <= inventory.length){ //if the choice is one of the weapons
              itemPrinter(inventory, choice2 - 1, true, person);
              System.out.println("Are you sure you want to sell the " + inventory[choice2 - 1].getName() + "?");
              System.out.println("1. Yes");
              System.out.println("2. No");
              int choice3 = valueCheckerInt(0, 1, 2);
              if(choice3 == 1){
                System.out.println("+" + inventory[choice2 - 1].getSell() + " coins");
                person.setCoins(person.getCoins() + shopList[choice2 - 1].getSell());
                //System.out.println("Thank you");
                //System.out.println(inventory[choice2 - 1].getName() + " " + getWeaponId(weaponsList, inventory, (choice2 - 1)));
                shopList = pickUpWeapon(weaponsList, shopList, getWeaponId(weaponsList, inventory, (choice2 - 1)));
                inventory = decreaseSize(inventory, (choice2 - 1));
              }
            }
            else{
              break;
            }
          }
          else{
            System.out.println("You have no more weapons to sell.");
            input.nextLine();
            break;
          }
        }
      }
      if(choice == 3){  //error specifically goes to case three for some reason
        break;
      }
    }
    input.close();
    System.out.println("Bye");
  }
  public static Weapon[] increaseSize(Weapon[] inventory) { //method which increases the size of the pizza object array by 1
    Weapon[] temp = new Weapon[inventory.length + 1]; //creates temporary array
    for (int i = 0; i < inventory.length; i++){
      temp[i] = inventory[i];   //copies original array into temporary array
    }
    inventory = temp; //sets original to the temporary
    return inventory;
  }
  
  
  
  public static Weapon[] decreaseSize(Weapon[] inventory, int choice) { //
    Weapon[] temp = new Weapon[inventory.length - 1]; //creates temporary array
    for (int i = 0; i < inventory.length; i++){
      if(i < choice){
        temp[i] = inventory[i];   //copies original array into temporary array
      }
      else if(i > choice){
        temp[i - 1] = inventory[i];
      }
    }
    inventory = temp; //sets original to the temporary
    return inventory;
  }
  
  
  
  
  public static Weapon[] pickUpWeapon(Weapon[] weaponsList, Weapon[] inventory, int id){//, boolean isPerson){
    //if(isPerson){
     // weaponsList[id].setHave(true);
    //}
    inventory = increaseSize(inventory);
    //System.out.println(inventory.length + "   " + id);
    inventory[inventory.length - 1] = weaponsList[id];
    //System.out.println("hey");
    //System.out.println("You picked up the " + inventory[id].getName() + ".");
    return inventory;
  }
  public static void printInventory(Weapon[] inventory){
    System.out.println("Weapons:");
    for(int i = 0; i < inventory.length; i ++){
      System.out.println((i+1) + ". " + inventory[i].getName());
    }
  }
  public static Weapon[] setShopItems(Weapon[] weaponsList, Weapon[] shopList){
    for(int i = 0; i < weaponsList.length; i ++){
      if(weaponsList[i].getTestShop()){
        shopList = increaseSize(shopList);
        shopList[shopList.length-1] = weaponsList[i];
      }
    }
    return shopList;
  }
  public static int getWeaponId(Weapon[] weaponsList, Weapon[] inventory, int inventoryPos){
    for(int i = 0; i < weaponsList.length; i ++){
      if(weaponsList[i].getName().equals(inventory[inventoryPos].getName())){
        return weaponsList[i].getId();
      }
    }
    return -1;
  }
  
}