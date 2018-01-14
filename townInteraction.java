import java.util.*;
public class townInteraction{
  public static void main(String[] args){
    Scanner input = new Scanner(System.in);
    System.out.println();
    int locationLength = 9;
    Location[] locationList = new Location[locationLength];
    locationList = setLocations(locationList);
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
    inventory = pickUpWeapon(weaponsList, inventory, 3);;
    System.out.println("Which town do you want to go to?");
    int choice1 = valueCheckerInt(1,9);
    if(choice1 == 1){
      inventory = townInteraction(person, locationList[3], weaponsList, inventory);
    }
    System.out.println(person.getCoins());
  }
  public static Weapon[] townInteraction(Character person, Location locationList, Weapon[] weaponsList, Weapon[] inventory){
    Scanner input = new Scanner(System.in);
    System.out.println("Welcome to " + locationList.getName() + "!");
    input.nextLine();
    boolean revealed = false;
    int choice;
    while(true){
      System.out.println("1. Talk to the people");
      System.out.println("2. Go to the local shopkeep");
      if(revealed){
        System.out.println("3. Go to the " + locationList.getArea());
        System.out.println("4. Leave town");
        choice = valueCheckerInt(1,4);
      }
      else if(revealed == false && locationList.getName().equals("Refugee Camp")){
        choice = valueCheckerInt(1,2);
      }
      else {
        System.out.println("3. Leave town");
        choice = valueCheckerInt(1,3);
      }
      if(choice == 1){
        revealed = peopleInteractions(locationList.getName());
      }
      /*else if(choice == 2){
        while(true){
          int result = getRandom(1,3);
          if(result == 1){
            System.out.println("Richard");
            System.out.println("I heard ye old rumors of cool people in the library");
            input.nextLine();
          }
          else if(result == 2){
            System.out.println("Peasant");
            System.out.println("It's great here, this is the only shop in the kingdom that sells frostBlades");
            input.nextLine();
          }
          else{
            System.out.println("Thicke");
            System.out.println("Hey there stranger, wanna buy some deathSticks?");
            input.nextLine();
          }
          System.out.println("Would you like to talk to another person?");
          System.out.println("1. Yes");
          System.out.println("2. No");
          int choice2 = valueCheckerInt(1,2);
          if(choice2 == 2){
            break;
          }
        }
      }*/
      else if(choice == 2){
        inventory = shopping(weaponsList, inventory, person);
      }
      /*else if(choice == 4){
       System.out.println("Welcome to Big Dick's halfway inn");
       input.nextLine();
       while(true){
       System.out.println("Would you like to rent a room for the night?");
       System.out.println("1. Yes");
       System.out.println("2. No");
       int choice2 = valueCheckerInt(1,2);
       if(choice2 == 1){
       while(true){
       System.out.println("We have two rooms:");
       System.out.println("1. Basic Room  (10 coins)");
       System.out.println("2. More comfortable room   (25 coins)");
       System.out.println("3. Exit");
       System.out.println("What would you like to do?     (You have " + person.getCoins() + " coins)");
       int choice3 = valueCheckerInt(1,3);
       if(choice3 == 1){
       System.out.println("Okay, enjoy your stay");
       System.out.println(" - 10 coins");
       input.nextLine();
       person.setCoins(person.getCoins() - 10);
       }
       else if(choice3 == 2){
       System.out.println("Okay, enjoy your stay");
       System.out.println(" - 25 coins");
       input.nextLine();
       person.setCoins(person.getCoins() - 25);
       }
       else{
       break;
       }
       }
       }
       else{
       break;
       }
       }
       }*/
      else if(choice == 3 && revealed == false){
        break;
      }
      else if(choice == 3 && revealed){
        
      }
      else{
        break;
      }
      
      
      
      
    }
    return inventory;
    
    
  }
  public static Location[] setLocations(Location[] locationList){
    locationList[0] = new Location();
    locationList[0].setName("Everton Forest");
    locationList[0].setDesc("A large copse of tall trees. The expansive branch systems cast dappled shadows on the paths below.");
    locationList[0].setDiff(0);
    locationList[0].setRec(1);
    locationList[0].setMany(3);
    String[] enemyTypesForest = new String[locationList[0].getMany()];
    enemyTypesForest[0] = "Skeleton";
    enemyTypesForest[1] = "Guard";
    enemyTypesForest[2] = "Generic enemy 3";
    locationList[0].setTypes(enemyTypesForest);
    
    
    locationList[1] = new Location();  //refugee camp
    locationList[1].setName("Refugee Camp");
    locationList[1].setDesc("An area full of people made homeless by the demon's wrath");
    locationList[1].setArea("Central Office");
    
    locationList[2] = new Location();  //start
    locationList[2].setName("Whiteridge");
    locationList[2].setDesc("Player starter town");
    locationList[2].setArea("None");
    
    locationList[3] = new Location();  //second town
    locationList[3].setName("Warrington");
    locationList[3].setDesc("A walled town with a cozy feel");
    locationList[3].setArea("Tavern");
    
    locationList[4] = new Location();
    locationList[4].setName("Field of Stones");
    locationList[4].setDesc("A flat expanse of stones left there by the glaciers that shaped the land long ago");
    locationList[4].setMany(3);
    String[] enemyTypesField = new String[locationList[4].getMany()];
    enemyTypesField[0] = "Skeleton";
    enemyTypesField[1] = "Guard";
    enemyTypesField[2] = "Generic enemy 3";
    locationList[4].setTypes(enemyTypesField);
    
    locationList[5] = new Location();
    locationList[5].setName("Deepland Swamp");
    locationList[5].setDesc("An area of several acres of swampland. Rumored to hide mysteries...");
    locationList[5].setMany(3);
    String[] enemyTypesSwamp = new String[locationList[5].getMany()];
    enemyTypesSwamp[0] = "Skeleton";
    enemyTypesSwamp[1] = "Guard";
    enemyTypesSwamp[2] = "Generic enemy 3";
    locationList[5].setTypes(enemyTypesSwamp);
    
    locationList[6] = new Location();
    locationList[6].setName("Daemonium Cliffs");
    locationList[6].setDesc("A dreary mountain range which is the source of many demonic legends");
    locationList[6].setMany(3);
    String[] enemyTypesCliffs = new String[locationList[6].getMany()];
    enemyTypesCliffs[0] = "Skeleton";
    enemyTypesCliffs[1] = "Guard";
    enemyTypesCliffs[2] = "Generic enemy 3";
    locationList[6].setTypes(enemyTypesCliffs);
    
    locationList[7] = new Location();  
    locationList[7].setName("Around Town");
    locationList[7].setDesc("Circular town surrounding a small forest");
    locationList[7].setArea("Library");
    
    locationList[8] = new Location();  
    locationList[8].setName("Easthaven City");
    locationList[8].setDesc("A very mountanous city bustling full of people.");
    locationList[8].setArea("Old Demonic Temple");
    
    
    
    /*String[] enemyTypesTown1 = new String[locationList[1].getMany()];
     enemyTypesTown1[0] = "Skeleton";
     enemyTypesTown1[1] = "Guard";
     enemyTypesTown1[2] = "Generic enemy 3";
     locationList[0].setTypes(enemyTypesTown1);*/
    //System.out.println("Location: " + locationList[0].getName());
    //System.out.println("Description: " + locationList[0].getDesc());
    //System.out.println("The difficulty is " + locationList[0].getDiff() + " which means you should be at least level " + locationList[0].getRec() + " to enter.");
    //System.out.println("There are " + locationList[0].getMany() + " different enemy types in the " + locationList[0].getName() + ":");
    return locationList;
  }
  public static int valueCheckerInt(int lowBound, int highBound){  //method for error handling for numbers that have to be > 0
    Scanner input = new Scanner(System.in);
    int answer = input.nextInt();
    while(answer < lowBound || answer > highBound){
      System.out.println("Invalid input. Please try again");
      answer = input.nextInt();
    }
    input.close();
    return answer;
  }
  
  public static Weapon[] shopping(Weapon[] weaponsList, Weapon[] inventory, Character person){
    Scanner input = new Scanner(System.in);
    Weapon[] shopList = new Weapon[0];
    shopList = setShopItems(weaponsList, shopList);
    System.out.println("Welcome to my store.");
    input.nextLine();
    while(true){
      System.out.println("1. Buy");
      System.out.println("2. Sell");
      System.out.println("3. Exit               (You have " + person.getCoins() + " coins)");
      int choice = valueCheckerInt(1, 3);
      if(choice == 1){
        while(shopList.length > 0){
          System.out.println("Here are the weapons I have in stock:");
          int counter = 0;
          for(int i = 0; i < shopList.length; i ++){
            System.out.println((i+1) + ". " + shopList[i].getName());
            counter = i;
          }
          System.out.println((counter + 2) + ". Exit");
          System.out.println("Are you interested in any?    (You have " + person.getCoins() + " coins)");
          int choice2 = valueCheckerInt(1, shopList.length + 1);
          if(choice2 <= shopList.length && person.getCoins() >= shopList[choice2 - 1].getCost()){ //if the choice is one of the weapons
            itemPrinter(shopList, choice2 - 1, false, person);
            System.out.println("Would you like to purchase the " + shopList[choice2 - 1].getName() + "?");
            System.out.println("1. Yes");
            System.out.println("2. No");
            int choice3 = valueCheckerInt(1, 2);
            if(choice3 == 1){
              System.out.println("-" + shopList[choice2 - 1].getCost() + " coins");
              person.setCoins(person.getCoins() - shopList[choice2 - 1].getCost());
              System.out.println("Here you go! Enjoy");
              inventory = pickUpWeapon(weaponsList, inventory, getWeaponId(weaponsList, shopList, (choice2 - 1)));
              shopList = decreaseSize(shopList, (choice2 - 1));
            }
          }
          else if(choice2 <= shopList.length && person.getCoins() < shopList[choice2 - 1].getCost()){
            System.out.println("You do not have enough to purchase this.");
            break;
          }
          else{ // if the user chooses to exit
            break;
          }
        }
        if(shopList.length == 0){
          System.out.println("We are all out of stock");
        }
      }
      if(choice == 2){
        while(true){
          if(inventory.length > 0){
            printInventory(inventory);
            System.out.println((inventory.length + 1) + ". Exit");
            System.out.println("Which would you like to sell?     (You have " + person.getCoins() + " coins)");
            int choice2 = valueCheckerInt(1, inventory.length + 1);
            if(choice2 <= inventory.length){ //if the choice is one of the weapons
              itemPrinter(inventory, choice2 - 1, true, person);
              System.out.println("Are you sure you want to sell the " + inventory[choice2 - 1].getName() + "?");
              System.out.println("1. Yes");
              System.out.println("2. No");
              int choice3 = valueCheckerInt(1, 2);
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
    System.out.println("Bye");
    input.nextLine();
    input.close();
    return inventory;
  }
  public static Weapon[] increaseSize(Weapon[] inventory) { //method which increases the size of the pizza object array by 1
    Weapon[] temp = new Weapon[inventory.length + 1]; //creates temporary array
    for (int i = 0; i < inventory.length; i++){
      temp[i] = inventory[i];   //copies original array into temporary array
    }
    inventory = temp; //sets original to the temporary
    return inventory;
  }
  public static int getRandom(int min, int max) {
    Random rand = new Random();
    return rand.nextInt(max - min + 1) + min;
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
  public static int getWeaponId(Weapon[] weaponsList, Weapon[] inventory, int inventoryPos){
    for(int i = 0; i < weaponsList.length; i ++){
      if(weaponsList[i].getName().equals(inventory[inventoryPos].getName())){
        return weaponsList[i].getId();
      }
    }
    return -1;
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
  public static void printInventory(Weapon[] inventory){
    System.out.println("Weapons:");
    for(int i = 0; i < inventory.length; i ++){
      System.out.println((i+1) + ". " + inventory[i].getName());
    }
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
    weaponsList[2].setCost(100);
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
  public static boolean peopleInteractions(String townName){
    boolean revealed = false;
    Scanner input = new Scanner(System.in);
    while(true){
      int result = getRandom(1,3);
      if(townName.equals("Refugee Camp")){
        if(result == 1){
          System.out.println("Brenda");
          System.out.println("That dastardly demon ruined all of our lives! It goes village to village, murdering the innocent!");
          input.nextLine();
          revealed = true;
        }
        else if(result == 2){
          System.out.println("Louzyr");
          System.out.println("It killed all of them. My family and my friends.");
          input.nextLine();
        }
        else{
          System.out.println("Reese");
          System.out.println("They may provide us with food, but in the end, we're all gunna starve. The fields burned!");
          input.nextLine();
        }
      }
      else if(townName.equals("Warrington")){
         if(result == 1){
          System.out.println("Biggus");
          System.out.println("Some people here believe in really wacky stuff. I hear they praise demons. Especially those who frequent the taverns");
          input.nextLine();
          revealed = true;
        }
        else if(result == 2){
          System.out.println("Joel");
          System.out.println("Don't you love it when the sun sets over the treeline?");
          input.nextLine();
        }
        else{
          System.out.println("Peter");
          System.out.println("I'm always being sent to the store to buy potatoes.");
          input.nextLine();
        }
      }
      else if(townName.equals("Around Town")){
         if(result == 1){
          System.out.println("Gil");
          System.out.println("I've always wanted to visit the restricted section of the library. Too bad I'm dirt poor. Those records must be mystical.");
          input.nextLine();
          revealed = true;
        }
        else if(result == 2){
          System.out.println("Ellie");
          System.out.println("Visiting the forest is my favorite part about living here.");
          input.nextLine();
        }
        else{
          System.out.println("Dand");
          System.out.println("Hey buddy, wanna travel Around Town with me?");
          input.nextLine();
        }
      }
      else if(townName.equals("Easthaven City")){
         if(result == 1){
          System.out.println("Pat");
          System.out.println("Easthaven City is a paradise of smart folks. The only remnant of darker times is the old demonic temple downtown. It's worth checking out, in my opinion.");
          input.nextLine();
          revealed = true;
        }
        else if(result == 2){
          System.out.println("Salva");
          System.out.println("Have you seen my family?");
          input.nextLine();
        }
        else{
          System.out.println("Chad");
          System.out.println("I don't believe that anyone could be happier than people who live here.");
          input.nextLine();
        }
      }
      System.out.println("Would you like to talk to another person?");
      System.out.println("1. Yes");
      System.out.println("2. No");
      int choice2 = valueCheckerInt(1,2);
      if(choice2 == 2){
        break;
      }
    }
    return revealed;
  }
  public static void specialInteraction(Weapon[] inventory, Weapon[] weaponsList, String townName){
    Scanner input = new Scanner(System.in);
    if(townName.equals("Refugee Camp")){
      System.out.println("You enter a spacious, dimly lit canvas tent.");
      input.nextLine();
      System.out.println("Billiard Saxton: New faces. What brings you here?");
      System.out.println("1. Our village was destroyed, and we wandered here.");
      System.out.println("2. Just passing through.");
      int choice1 = valueCheckerInt(1,2);
      if(choice1 == 1){
        System.out.println("Billiard Saxton: More victims, huh?");
      }
      else{
        System.out.println("Billiard Saxton: Everyone staying here is a victim of the demon attacks.");
      }
      System.out.println("Billiard Saxton: It's devastating seeing all these lives destroyed.");
      System.out.println("Billiard Saxton: No one's quite sure why, but a firey demon from the demon realm started attacking and destroying villages.");
      System.out.println("Billiard Saxton: So much destruction...  And no one seems to be able to do anything about it.");
      System.out.println("INSERT INSPIRATIONAL SPEECH HERE");
      System.out.println("So whatdya say? You wanna join us?");
      System.out.println("1. Heck yeah!");
      System.out.println("2. Nah, I'm lame");
      int choice2 = valueCheckerInt(1,2);
      if(choice2 == 1){
        System.out.println("Yay");
      }
      else{
        System.out.println("Bye bye loser");
      }
        
    }
  }
}