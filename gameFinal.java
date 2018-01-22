import java.util.*;
public class gameFinal {
  public static void main(String[] args) {
    GameTitleCard gmt = new GameTitleCard("DEMONBLOOD.png", 614, 460); //This is the title card that displays at the beginning of the game.
    gmt.isVisible(true); //It shows up and then the player can close out of it or keep it or whatever they want with it.
    GameTitleCard map = new GameTitleCard("map1.png", 714, 560); //This is the map that displays at the beginning of the game.
    map.isVisible(true);
    printBetter("Welcome to DemonBlood, a text based adventure role playing game.");
    System.out.println("Who would you like to play as?");
    System.out.println("1. Australius Gage: Electricity Powers");
    System.out.println("2. Claudius Steed: Bone Powers");
    System.out.println("3. Rosalina Berry: Water Powers");
    int userin = getInput(3);
    Character player = new Character(userin, 1);  //Initializes the player character as the player's choice
    Character npc1;
    Character npc2;
    //Initializes the 2 teammates as the other 2 characters
    if (userin==1) {
      npc1 = new Character(2, 2);
      npc2 = new Character(3, 3);
    }
    else if (userin==2) {
      npc1 = new Character(1, 2);
      npc2 = new Character(3, 3);
    }
    else {
      npc1 = new Character(1, 2);
      npc2 = new Character(2, 3);
    }
    boolean playing = true;  //Goes until they are done with the game
    int location = 0;  //Location variable
    boolean notArrived = true;
    while (playing) {
      GameMusicPlayer music;
      userin = 0;
      if (player.getLocationList()[location].haventBeen()) {  //Checks haventBeen() of the current location to see if it should run the first time cutscene for that area
        
        cutscene(player.getLocationList()[location].getFirstTimeCutscene(), player, npc1, npc2);
      }
      if(!player.getLocationList()[location].getName().equals("Whiteridge") && notArrived){ //if you arent at whiteridge
        printBetter("The team arrived at "+player.getLocationList()[location].getName()+"!");
        notArrived = false; //you have arrived
      }
      if (player.getLocationList()[location].getMany() == 0 && player.getLocationList()[location].getAreaRevealed() && !player.getLocationList()[location].getName().equals("Refugee Camp")) {//Runs if town area and the extra area has been revealed and if it isnt refugee camp
        printStats(player); //prints levels, exp, and coins
        System.out.println("1=Talk to people 2=Customize 3=Shop 4=Leave 5=Go to "+player.getLocationList()[location].getArea());
        music = new GameMusicPlayer("hometown.mp3"); //Happy music for town area
        music.play();
        userin = getInput(5);
      }
      else if 
        (player.getLocationList()[location].getMany() == 0 && !player.getLocationList()[location].getAreaRevealed() && !player.getLocationList()[location].getName().equals("Whiteridge") && !player.getLocationList()[location].getName().equals("Refugee Camp")) {  //Runs if town area and the extra area has not been revealed and if it isnt whiteridge or refugee camp
        printStats(player);
        System.out.println("1=Talk to people 2=Customize 3=Shop 4=Leave");
        music = new GameMusicPlayer("hometown.mp3");
        music.play(); //Happy music
        userin = getInput(4);
      }
      else if(player.getLocationList()[location].getName().equals("Whiteridge")){ //runs if town is whiteridge, as it shouldnt have a menu
        userin = 4;
      }
      else if(player.getLocationList()[location].getName().equals("Refugee Camp") && !player.getLocationList()[location].getAreaRevealed()){ //runs if it is the refugee camp and you havent discovered the special area. It has a seperate one because it doesnt have a shop
        printStats(player);
        System.out.println("1=Talk to people 2=Customize 3=Leave");
        music = new GameMusicPlayer("hometown.mp3");
        music.play(); //Happy music
        userin = getInput(3);
      }
      else if(player.getLocationList()[location].getName().equals("Refugee Camp") && player.getLocationList()[location].getAreaRevealed()){ //runs if it is the refugee camp and you have discovered the special area. It has a seperate one because it doesnt have a shop
        printStats(player);
        System.out.println("1=Talk to people 2=Customize 3=Leave 4=Go to "+player.getLocationList()[location].getArea());
        music = new GameMusicPlayer("hometown.mp3");
        music.play(); //Happy music
        userin = getInput(4);
      }
      else { //Runs if in a combat/enemy area
        printStats(player);
        System.out.println("1=Fight enemies 2=Customize 3=Fight "+new Enemy(player.getLocationList()[location].getBoss()).getName()+" 4=Leave");
        music = new GameMusicPlayer("dragonRoostIsland.mp3"); //Adventerous music
        music.play();
        userin = getInput(4);
      }
      if (player.getLocationList()[location].getMany() == 0 && userin==1) { //talking to npcs in town
        music.stop();
        if(player.getLocationList()[location].getDialogue(getRandom(1,3)) == 2){ //if the dialogue is the special one, it unlocks the secret area
          player.getLocationList()[location].setRevealed(true); //reveal the special area
        }
      }
      else if (player.getLocationList()[location].getMany() != 0 && userin==1) { //if it is a enemy area and you choose to fight enemies
        music.stop();
        music = new GameMusicPlayer("caught.mp3");
        music.play();
        battle(player, npc1, npc2, new Enemy(player.getLocationList()[location].getEnemyTypes()[getRandom(0,player.getLocationList()[location].getEnemyTypes().length-1)])); //initiate a battle
        music.stop();
      }
      else if (userin == 2) { //customizing inventory
        music.stop();
        customize(player, npc1, npc2); //calls customization method 
      }
      else if (player.getLocationList()[location].getMany() == 0 && userin == 3 && !player.getLocationList()[location].getName().equals("Refugee Camp")) { //if choice is to shop (refugee camp does not have a shop
        music.stop();
        shop(player, location);
      }
      else if (player.getLocationList()[location].getMany() != 0 && userin == 3) { //if choice is to fight boss in combat area
        if(player.getLocationList()[location].getName().equals("Field of Stones") || player.getLocationList()[location].getName().equals("Deepland Swamp") || player.getLocationList()[location].getName().equals("Demon Realm")){ //if the location is one of the listed then that means its battle is in its cutscene
          cutscene(player.getLocationList()[location].getLastTimeCutscene(), player, npc1, npc2); //calls last area cutscene
        }
        else{ // if the battle is not in the cutscene
          battle(player, npc1, npc2, new Enemy(player.getLocationList()[location].getBoss()));
        }
        player.getLocationList()[location].setComplete(); //you completed the boss so you can travel to the next area
      }
      else if (userin == 4 && !player.getLocationList()[location].getName().equals("Refugee Camp") || userin == 3 && player.getLocationList()[location].getName().equals("Refugee Camp")) { //leaving the area
        if(player.getLocationList()[location].getMany() != 0 && player.getLocationList()[location].haventBeen2() && player.getLocationList()[location].getName().equals("Everton Forest") && player.getLocationList()[location].getComplete()){  //runs final cutscene for towns and the forest if you havent been through the areas already
          cutscene(player.getLocationList()[location].getLastTimeCutscene(), player, npc1, npc2);
        }
        int availablePlaces = 0;  //how many places you can travel
        if(player.getLocationList()[location].getComplete()){ //if you completed the boss or cutscene then it allows the player to move on to the next location
          player.getLocationList()[location+1].setAbleToGo();// you are able to go to the area
        }
        System.out.println("Where do you want to go?");
        for (int i = 1; i<player.getLocationList().length; i++ ) { //For as many places as are in the location list
          if (player.getLocationList()[i].getAbleToGo()) { //only shows location if you can go there
            if(i == 8){
              System.out.println((i)+": Daemonium Cliffs");
            }
            else{
              System.out.println((i)+": "+player.getLocationList()[i].getName());
            }
            availablePlaces++;  //Adds to the availablePlaces variable so that you can go there
          }
        }
        location = getInput(availablePlaces); //You go to the place that you choose
        music.stop();
        notArrived = true; //you have not arrived at the next area
      }
      else if(userin == 5 || userin == 4 && player.getLocationList()[location].getName().equals("Refugee Camp")){ //if you decide to go to the special areas
        music.stop();
        cutscene(player.getLocationList()[location].getLastTimeCutscene(), player, npc1, npc2);  //runs cutscenes
        player.getLocationList()[location].setComplete();  //now you can go to the next area
      }
      if(location == 8 && player.getLocationList()[location].getComplete()){  //if you are at the final location, run the last 3 cutscenes
        music.stop();
        cutscene(19, player, npc1, npc2);
        cutscene(20, player, npc1, npc2);
        cutscene(21, player, npc1, npc2);
        playing = false; //ends the game
      }
      
    }
    randomPhrase();  //At the end of the game it displays a random phrase so that the ending is different every time
    printBetter("Thanks for playing!");
    printBetter("Created by Luke \"Cubemaster\" Hamel and Will \"Paperpal\" Newman.");
  }
  public static int getIntFromString(int max){  //input a string and return an int (for error handling)
    Scanner input = new Scanner(System.in);
    System.out.println("Enter a number from 1 to "+max+".");
    String userstring;
    int userin = 0;
    boolean isString = true;
    while(isString){
      try{  //if the string is an integer
        userstring = input.next();
        userin = Integer.parseInt(userstring); //set it to an integer
        isString = false;
      }
      catch(NumberFormatException e){ //if error
        System.out.println("Invalid input!"); //scream jk it catches the error and goes back through the loop
      }
    }
    return userin;
  }
  public static int getInput(int max) { //method that handles all error handling
    Scanner input = new Scanner(System.in);
    int userin = getIntFromString(max); //passes into previous method
    while (userin <= 0 || userin > max) {   //if it is not within parameters
      System.out.println("Invalid input!");
      userin = getIntFromString(max); //do it again
    }
    input.close();
    return userin;
  }
  public static void addWeapon(Character player, int id) {  //adds a weapon to the player inventory
    player.getInventory().add(new Weapon(id));//Adds the weapon of that id to the inventory
  }
  public static void removeWeapon(Character player, int id) { //removes a weapon from the player inventory
    for (int i = (player.getInventory().size())-1; i >= 0; i--) {//This for loop goes through the inventory looking for the first weapon with that id
      if (player.getInventory().get(i).getId() == id) {
        player.getInventory().remove(i); //Removes that weapon
        break;//Breaks so that it only removes 1 weapon. If multiple items have that ID it does NOT delete every one.
      }
    }
  }
  public static void printInventory(Character player) {  //prints the players inventory
    for (Weapon weapon : player.getInventory()) { //better for loop
      System.out.println(weapon.getName());
    }
  }
  public static void battle(Character player, Character npc1, Character npc2, Enemy enemy) { //combat method
    player.startBattle(); //Initializes every main character for a battle
    npc1.startBattle();
    npc2.startBattle();
    int aggro = 1;  //Enemy targets the player by default
    boolean ranAway = false;
    printBetter(enemy.getName()+" begins a battle!");
    while ((player.getHp()!=0 || npc1.getHp()!=0 || npc2.getHp()!=0) && enemy.getHp() != 0) { //while every health is not 0
      System.out.println(player.getName()+": "+player.getHp()+"HP | Special 1: "+boolToReady(player.getSpecial(1).isReady())+" | Special 2: "+boolToReady(player.getSpecial(2).isReady())+" | Special 3: "+boolToReady(player.getSpecial(3).isReady()));//Shows the player's HP and if their specials are ready
      System.out.println(npc1.getName()+": "+npc1.getHp()+"HP"); //npc1 health
      System.out.println(npc2.getName()+": "+npc2.getHp()+"HP"); //npc2 health
      System.out.println(enemy.getName()+": "+enemy.getHp()+"HP"); //enemy health
      if(player.isAlive()) {
        System.out.println("1=Standard Attack 2=Special Attack 3=Flee");
        int userin = getInput(3);
        switch (userin) {
          case 1: //standard attack
            characterAttack(player, enemy, 1);
            break;
          case 2: //special attack
            System.out.println("1. "+player.getSpecial(1).getName()+" is "+boolToReady(player.getSpecial(1).isReady())); //shows whether or not the specials are ready
            System.out.println("2. "+player.getSpecial(2).getName()+" is "+boolToReady(player.getSpecial(2).isReady()));
            System.out.println("3. "+player.getSpecial(3).getName()+" is "+boolToReady(player.getSpecial(3).isReady()));
            int special = getInput(3);
            if (player.getSpecial(special).isReady()) {
              specialAttack(player, enemy, player.getSpecial(special), npc1, npc2); //If its ready and the player tries to use it, it works
            }
            else {
              printBetter(player.getSpecial(special).getName()+" is not ready yet!");
            }
            break;
          case 3:
            if (enemy.getAbleToFlee()) {
            if (getRandom(1,100) > 70) { //successful flee
              printBetter("The team ran away!");
              enemy.modHp(-1000, 1);
            }
            else { //failed flee
              printBetter("The team tried to flee but failed!");
            }
          }
            else {
              printBetter("The team tried to flee but was unable to!");
            }
          default:
            break;
        }
      }
      if (enemy.getHp()!=0) {
        if (npc1.isAlive()) {
          npcAttack(npc1, enemy, player, npc2); //NPC1 attacks if they are alive
        }
        if (npc2.isAlive()) {
          npcAttack(npc2, enemy, player, npc1);//NPC2 attacks if they are alive
        }
        int aggroCheck = enemy.aggroCheck();
        if (aggroCheck == 1) {  //Enemy starts to attack the player
          aggro = 1;
          printBetter(enemy.getName()+" turns their attention towards "+player.getName()+"!");
        }
        else if (aggroCheck == 2) { //Enemy starts to attack NPC1
          aggro = 2;
          printBetter(enemy.getName()+" turns their attention towards "+npc1.getName()+"!");
        }
        else if (aggroCheck == 3) { //Enemy starts to attack NPC2
          aggro = 3;
          printBetter(enemy.getName()+" turns their attention towards "+npc2.getName()+"!");
        }
        if (aggro==1) {
          enemyAttack(enemy, player);
        }
        else if (aggro==2) {
          enemyAttack(enemy, npc1);
        }
        else {
          enemyAttack(enemy, npc2);
        } 
        
        player.incrementSpecials();
        npc1.incrementSpecials();
        npc2.incrementSpecials();
      }
    }
    if (player.getHp() == 0 && npc1.getHp() == 0 && npc2.getHp() == 0) { //if all players die
      System.out.print("The team has fallen...");
    }
    else if(!ranAway){
      printBetter(enemy.getName()+" falls!");
      player.addExp(enemy.getExp());  //gives players exp
      npc1.addExp(enemy.getExp());
      npc2.addExp(enemy.getExp());
      player.modCoins(enemy.getCoins()); //gives players gold
      npc1.modCoins(enemy.getCoins());
      npc2.modCoins(enemy.getCoins());
      printBetter("The team gets "+enemy.getExp()+" cups of demon blood and " + enemy.getCoins() + " coins!");
    }
  }
  public static boolean tutorial(Character player, Enemy enemy) { //tutorial for first enemy
    player.startBattle();   //enters player into a battle
    printBetter(enemy.getName()+" begins a battle!");
    printBetter("(When prompted, enter '1' to perform a normal attack. Your stats will determine how often you hit the enemy and how much damage you will take.)");
    printBetter("(When a special attack is ready, enter '2' and then '1','2', or '3' to perform the designated special attack.)");
    printBetter("(Each special attack has different effects, so pay close attention to which one you use!)");
    while (player.getHp()!=0 && enemy.getHp() != 0) {
      System.out.println(player.getName()+": "+player.getHp()+"HP | Special 1: "+boolToReady(player.getSpecial(1).isReady())+" | Special 2: "+boolToReady(player.getSpecial(2).isReady())+" | Special 3: "+boolToReady(player.getSpecial(3).isReady()));
      System.out.println(enemy.getName()+": "+enemy.getHp()+"HP");
      if(player.isAlive()) {
        System.out.println("1=Standard Attack 2=Special Attack");
        int userin = getInput(2);
        switch (userin) {
          case 1:
            characterAttack(player, enemy, 1); //calls method for a character attacking
            break;
          case 2:
            System.out.println("1. "+player.getSpecial(1).getName()+" is "+boolToReady(player.getSpecial(1).isReady()));
            System.out.println("2. "+player.getSpecial(2).getName()+" is "+boolToReady(player.getSpecial(2).isReady()));
            System.out.println("3. "+player.getSpecial(3).getName()+" is "+boolToReady(player.getSpecial(3).isReady()));
            int special = getInput(3);
            if (player.getSpecial(special).isReady()) {
              specialAttack(player, enemy, player.getSpecial(special), new Character(2, 2), new Character(3, 3));  //calls method for a special attack
            }
            else {
              printBetter(player.getSpecial(special).getName()+" is not ready yet!");
            }
            break;
          default:
            break;
        }
      }
      if (enemy.getHp()!=0) { //if the enemy is not dead
        enemyAttack(enemy, player); //the enemy attacks
        player.incrementSpecials();
      }
    }
    if (player.getHp() == 0) { //if the player is dead
      return false;
    }
    else {
      return true;
    }
  }
  public static void characterAttack(Character character, Enemy enemy, int attackerId) { //when the character attacks
    int chance = getChance(character.getDex(), enemy.getAgi()); //combat variables
    int hit = getRandom(1,100);  
    if (hit >= (100-chance)) {
      int damage = getDamage(character.getStr(), enemy.getCon())/1;
      printBetter(character.getName()+" hits "+enemy.getName()+" for "+damage+" damage!");
      enemy.modHp(0-damage, attackerId); //updates the health of the enemy
    }
    else {
      printBetter(character.getName()+" attacks "+enemy.getName()+" and misses!");
    }
  }
  public static void npcAttack(Character npc, Enemy enemy, Character player, Character otherNpc) { //controls the npc attacks
    if (npc.getSpecial(3).isReady()) {
      specialAttack(npc, enemy, npc.getSpecial(3), player, otherNpc);
    }
    else if (npc.getSpecial(2).isReady()) {
      specialAttack(npc, enemy, npc.getSpecial(2), player, otherNpc); 
    }
    else if (npc.getSpecial(1).isReady()) {
      specialAttack(npc, enemy, npc.getSpecial(1), player, otherNpc);
    }
    else {
      characterAttack(npc, enemy, npc.getGameId());
    }
  }
  public static void enemyAttack(Enemy enemy, Character character) { //controls the enemy attacks
    int chance = getChance(enemy.getDex(), character.getAgi());
    int hit = getRandom(1,100);
    if (hit >= (100-chance)) {
      int damage = getDamage(enemy.getStr(), character.getCon())/1;
      printBetter(enemy.getName()+" hits "+character.getName()+" for "+damage+" damage!");
      character.modHp(0-damage);
    }
    else {
      printBetter(enemy.getName()+" attacks "+character.getName()+" and misses!");
    }
  }
  public static void printStats(Character character){ //prints level, exp, and coins
    System.out.println("Level: " + character.getLvl() + "       Cups Of Blood: " + character.getExp() + "        Coins: " + character.getCoins());
  }
  public static void specialAttack(Character character, Enemy enemy, SpecialAttack special, Character mate1, Character mate2) { //controls special attacks
    printBetter(character.getName()+": \""+special.getName()+"!\"");
    if (special.getIsBuff()) {
      if (special.getBuffStat().equals("str")) {
        character.setStr((int) (character.getStr()+special.getBuff())); //buffs strength
        printBetter(character.getName()+"'s strength stat is now "+character.getStr()+"!");
      }
      else if (special.getBuffStat().equals("con")) { //buffs constitution
        character.setCon((int) (character.getCon()+special.getBuff()));
        printBetter(character.getName()+"'s constitution stat is now "+character.getCon()+"!");
      }
      else if (special.getBuffStat().equals("agi")) { //buffs agility
        character.setAgi((int) (character.getAgi()+special.getBuff()));
        printBetter(character.getName()+"'s agility stat is now "+character.getAgi()+"!");
      }
      else if (special.getBuffStat().equals("dex")) { //buffs dexterity
        character.setDex((int) (character.getDex()+special.getBuff()));
        printBetter(character.getName()+"'s dexterity stat is now "+character.getDex()+"!");
      }
    }
    if (special.getIsDamage()) {    //enemy takes damage
      int damage = getDamage(character.getStr(), enemy.getCon())*special.getDamage();
      printBetter(enemy.getName()+" takes "+damage+" damage!");
      enemy.modHp(0-damage, character.getGameId());
    }
    if (special.getIsHeal()) {  //heals character
      printBetter("The team heals by "+special.getHeal()+"!");
      if (character.getHp() != 0) {
        character.modHp(special.getHeal());
      }
      if (mate1.getHp() != 0) {
        mate1.modHp(special.getHeal());
      }
      if (mate2.getHp() != 0) {
        mate2.modHp(special.getHeal());
      }
    }
    if (special.getIsAggro()) {
      enemy.changeAggro(character.getGameId(), special.getAggro()); 
    }
    special.resetCooldown(); //makes you wait to use next special
  }
  
  public static int getRandom(int min, int max) { //calls a random number
    Random rand = new Random();
    return rand.nextInt(max - min + 1) + min;
  }
  
  
  public static void printBetter(String message) { //method that takes the string you enter and prints it with a line you hit enter in
    Scanner input = new Scanner(System.in);
    System.out.println(message);
    input.nextLine();
    input.close();
  }
  public static void randomPhrase() { //fun method that makes every ending different
    System.out.print("The 3 heroes set off to ");
    String[] verbs = new String[]{"kill", "sniff", "jounce", "lick", "like", "wreck", "joust", "befriend", "beat","eat","caress", "intimidate", "embrace"}; //arrays of stored words
    String[] nouns = new String[]{"limb", "friend", "pope", "broadsword", "lady", "King", "world", "meat","AP Computer Science Class", "bible", "alien", "bread"};
    System.out.print(verbs[getRandom(0,(verbs.length-1))]);
    System.out.print(" the ");
    System.out.print(nouns[getRandom(0,(nouns.length-1))]);
    System.out.println("!");
  }
  
  public static int getChance(int attackerStat, int defenderStat) {  //gets the chances of success for combat
    int returner;
    int statDif = attackerStat-defenderStat;
    if (statDif > 10) {
      returner = 95;
    }
    else if (statDif < -10) {
      returner = 15;
    }
    else if (statDif > 0) {
      returner = 75+(statDif*2);
    }
    else {
      returner = 75+(statDif*6);
    }
    return returner;
  }
  
  public static int getDamage(int attackerStat, int defenderStat) { //gets the damage for combat
    int returner;
    int statDif = attackerStat-defenderStat;
    returner = (int) (4*statDif)+50;
    if(returner <= 0){
      returner = 1;
    }
    return returner;
  }
  public static void customize(Character player, Character npc1, Character npc2) { //customizing the inventory
    boolean customizing = true;
    while (customizing) {
      System.out.println(player.getName()+": "+player.getWeaponName());
      System.out.println(npc1.getName()+": "+npc1.getWeaponName());
      System.out.println(npc2.getName()+": "+npc2.getWeaponName());
      System.out.println("Customize whom?");
      System.out.println("1="+player.getName()+" 2="+npc1.getName()+" 3="+npc2.getName()+" 4=Exit");
      int userin = getInput(4);
      switch (userin) {
        case 1:
          customizeSubMenu(player, player); //calls a lower tier customization menu
          break;
        case 2:
          customizeSubMenu(npc1, player);
          break;
        case 3:
          customizeSubMenu(npc2, player);
          break;
        case 4:
          customizing = false;
      }
    }
  }
  public static void customizeSubMenu(Character character, Character player) { //lower tier of customization
    boolean changing = true;
    while (changing) {
      System.out.println(character.getName()+", level "+character.getLvl()); //prints various stats
      System.out.println(character.getExp()+" cups of Demon Blood");
      System.out.println(character.getWeaponName()+" Bonuses:");
      System.out.println("Str: "+character.getWeapon().getStr());
      System.out.println("Con: "+character.getWeapon().getCon());
      System.out.println("Dex: "+character.getWeapon().getDex());
      printBetter("Agi: "+character.getWeapon().getAgi());
      if (player.getInventory().size()!=0) {
        System.out.println("Replace "+character.getWeaponName()+" with what?");
        for (int i = 0; i<player.getInventory().size(); i++) {
          System.out.println((i+1)+": "+player.getInventory().get(i).getName());
        }
        System.out.println((player.getInventory().size()+1)+": Exit");
        int userin = getInput(player.getInventory().size()+1);
        if (userin == player.getInventory().size()+1) {
          changing = false;
        }
        else { //assigns weapons to players
          addWeapon(player, character.getWeapon().getId());
          character.setWeapon(player.getInventory().get(userin-1).getId()); 
          removeWeapon(player, player.getInventory().get(userin-1).getId());
        }
      }
      else {
        changing = false;
      }
    }
  }
  public static String boolToReady(boolean tester) {
    if (tester) {
      return "Ready";
    }
    else {
      return "Not Ready";
    }
  }
  public static void shop(Character player, int location) { //shop interactions
    printBetter("Shopkeeper: \"Buy somethin' will ya!\"");
    
    boolean shopping = true;
    while (shopping) {
      printBetter(player.getName()+" has "+player.getCoins()+" coins.");
      System.out.println("1=Buy 2=Sell 3=Exit");
      int userin = getInput(3);
      switch (userin) {
        case 1: //buying
          for (int i = 0; i<player.getLocationList()[location].getWeaponTypes().length; i++) {   //while i is less than the weapon types at the store
          System.out.println((i+1)+"="+new Weapon(player.getLocationList()[location].getWeaponTypes()[i]).getName()+", "+new Weapon(player.getLocationList()[location].getWeaponTypes()[i]).getPrice()+" coins");
        }
          System.out.println((player.getLocationList()[location].getWeaponTypes().length+1)+"=Exit");  //prints the menu options
          userin = getInput(player.getLocationList()[location].getWeaponTypes().length+1);
          if (userin != player.getLocationList()[location].getWeaponTypes().length+1) {
            if (player.getCoins() >= new Weapon(player.getLocationList()[location].getWeaponTypes()[userin-1]).getPrice()) { //successfully purchasing a weapon
              addWeapon(player, player.getLocationList()[location].getWeaponTypes()[userin-1]);
              printBetter("Bought "+new Weapon(player.getLocationList()[location].getWeaponTypes()[userin-1]).getName()+"!");
              player.modCoins(-(new Weapon(player.getLocationList()[location].getWeaponTypes()[userin-1]).getPrice()));
            }
            else { //unsuccessfully purchasing a weapon
              printBetter("Cannot afford "+new Weapon(player.getLocationList()[location].getWeaponTypes()[userin-1]).getName()+"!");
            }
          }
          break;
        case 2://selling
          for (int i = 0; i<player.getInventory().size(); i++) {
          System.out.println((i+1)+"="+player.getInventory().get(i).getName()+", "+player.getInventory().get(i).getPrice()+" coins"); 
        }
          System.out.println((player.getInventory().size()+1)+"=Exit");
          userin = getInput(player.getInventory().size()+1);
          if (userin != player.getInventory().size()+1) {  //removes chosen weapon
            player.modCoins(player.getInventory().get(userin-1).getPrice());
            printBetter("Sold "+player.getInventory().get(userin-1).getName()+"!");
            removeWeapon(player, player.getInventory().get(userin-1).getId());
          }
          break;
        case 3: //exit
          printBetter("Shopkeeper: \"Come again!\"");
          shopping = false;
      }
    }
  }
  public static void cutscene(int cutscene, Character player, Character npc1, Character npc2) { //contains all cutscenes for the game
    switch (cutscene) {
      case 1: //whiteridge beginning. Village burns down, before fighting demons for tutorial
        printBetter("The heavy wooden door of the tavern thuds closed behind "+player.getFullName()+" as "+player.pro()+" heads out into the brisk night. (Press Enter to continue reading text)");
        printBetter(player.getName()+" is disappointed to have to leave "+player.pos()+" friends so early, but "+player.pro()+" has a lot of work to do in the morning.");
        printBetter(player.getName()+" stares back at the wooden walls of the tavern for a few seconds, taking in the dulled noises of clinking dishes and human commotion.");
        printBetter("Taking a deep sigh, "+player.pro()+" starts on "+player.pos()+" way down the beaten main road and side streets to home, a cozy shack in the southwestern quadrant of the village.");
        GameMusicPlayer music = new GameMusicPlayer("countdown.mp3");
        music.play();
        printBetter("Suddenly, the world lights up and noise erupts all around "+player.getName()+"!");
        printBetter(player.getName()+" turns around, startled, to see the tavern in flames.");
        printBetter("Screams echo from inside as the bodies burn in the hungry flames. Soon, houses and buildings all throughout the village undergo the same gruesome fate.");
        printBetter(player.getName() + " turns around and finds that " + player.pro() + " is face to face with a demon!");
        tutorial(player,new Enemy(1));
        printBetter("Amazed that " + player.pro() + " fought such a creature, " + player.getName() + " starts heading towards the edge of the village.");
        printBetter("Then, " + player.pro() + " spots two other people walking nearby.");
        printBetter("They introduce themselves as " + npc1.getFullName() + " and " + npc2.getFullName() + ".");
        printBetter("Suddenly, a demon appears!");
        battle(player,npc1,npc2,new Enemy(2));
        break;
      case 2: //end whiteridge scene
        printBetter(player.getName() + ": Woohoo we did it!");
        printBetter("Although they celebrate over their minor victory, all around them the village burns.");
        printBetter("Saddended, they think what they should do next.");
        printBetter(player.getName() + ": Well, we should probably get help, maybe find the nearest village.");
        printBetter("And they head off into the dark land beyond their destroyed homes.");
        break;
      case 3: //refugee camp beginning
        printBetter("As " + player.getName() + " crosses over the last hill, the group sees the source of the lights.");
        printBetter("A large expanse of canvas tents stretches in front of the group.");
        printBetter(player.getName() + " heads down, in search of some answers.");
        break;
      case 4: //refugee camp end
        String manName1="Richard";
        printBetter(player.getName() +  " enters a spacious, dimly lit canvas tent.");
        System.out.println(manName1 + ": New faces. What brings you here?");
        System.out.println("1. Our village was destroyed, and we wandered here.");
        System.out.println("2. Just passing through.");
        int choice1=getInput(2);
        if(choice1==1){
          printBetter(manName1 + ": More victims, huh?");
        }
        printBetter(manName1 + ": Well I am Richard, leader of this makeshift settlement. Everyone staying here is a victim of the demon attacks.");
        printBetter(manName1 + ": It's devastating seeing all these lives destroyed.");
        printBetter(manName1 + ": No one's quite sure why, but a firey demon from the demon realm started attacking and destroying villages.");
        printBetter(manName1 + ": So much destruction...  And no one seems to be able to do anything about it.");
        printBetter(npc1.getName() + ": We have to do something! We can't just let this happen.");
        printBetter(npc2.getName() + ": Yeah, too many people are dying. It should be us to save the kingdom.");
        System.out.println("So whatdya say? You wanna join us?");
        System.out.println("1. Heck yeah!");
        System.out.println("2. No way.");
        int choice2 = getInput(2);
        if(choice2==1){
          printBetter("Woohoo!");
        }
        else{
          printBetter("I'm disappointed in you, " + player.getName() + ". But I guess it is your decision to make. Bye.");
          System.exit(0);
        }
        printBetter("Inspired, the team exits the tent.");
        break;
      case 5: //beginning forest cutscene
        printBetter("Tall leafy trees tower over the group.");
        printBetter("It is dark under the canopy besides the dappling of sunshine on the forest floor.");
        printBetter("Shrieks of beasts echo from deep within the forest.");
        printBetter("Warily, the group enters.");
        break;
      case 6: //end forest cutscene
        printBetter("At long last the mighty crew emerges from the dense undergrowth.");
        printBetter("Exhausted, they continue along the final stretch to the town of Warrington.");
        break;
      case 7: //warrington beginning cutscene
        printBetter("The team finally crosses into the town of Warrington.");
        printBetter("In front of them is a relatively small town with houses mainly constructed of wood and stone.");
        printBetter("It gives off a cozy feel.");
        break;
      case 8: //warrington end cutscene
        String manName2="Esmund";
        printBetter(player.getName() + " enters the musty tavern. The only person there is a man sitting in the corner at a table.");
        printBetter(player.getName() + " decide to approach the man, given the rumors you heard from around town.");
        printBetter(manName2+": Hello there.");
        printBetter(player.getName()+": Can you tell us anything about the demon attacks on the villages?");
        printBetter(manName2+": Getting right to the point, I see. Well you are correct, it is a demon.");
        printBetter(manName2+": It resides in the demon realm, but recently for some unknown reason, it has been emerging from its world to come to ours for the sole purpose of killing people");
        printBetter(manName2+": The demon itself is very powerful, one with excellent control of fire.");
        printBetter(player.getName()+": What can we do to stop it?");
        printBetter(manName2+": Stop it? Well you would have to fight it in the demon realm.");
        printBetter(manName2+": No one knows for sure how, but there is a legend that a team of heroes long ago managed to get there.");
        printBetter(manName2+": If there are any records at all, they would be at the library at the next town. Around Town, I believe.");
        printBetter(manName2+": It's the largest library around.");
        printBetter(manName2+": And one final tip: the more enemies you defeat, the more demon blood you get. Demon blood makes you stronger.");
        printBetter(manName2+": Best of luck on your journey.");
        break;
      case 9: //field of stones beginning
        printBetter("Fully stocked up from their time at Warrington, the group heads towards a wide open expanse of sky.");
        printBetter("In front of them is a vast stretch of bare rock, with no cover between their current position and the end of the field.");
        printBetter("With no better option, they head onwards.");
        break;
      case 10: //field of stones end
        printBetter("On the horizon, the team sees man-made structures that appear to be the roofs of houses.");
        printBetter(player.getName() + ": There! In the distance!");
        printBetter("Now motivated, the team picks up the pace.");
        printBetter("Suddenly, the ground starts rumbling all around.");
        printBetter("Out of the stones, a being of rock rises in front of them.");
        printBetter(player.getName() + ": It's a boulder demon!");
        //battle(player, npc1, npc2, new Enemy(player.getLocationList()[4].getBoss()));
        printBetter("The team continues onwards towards the village ahead.");
        break;
      case 11: //around town beginning
        printBetter("Finally, the team arrives at Around Town.");
        printBetter("It is a circular town that surrounds a small forest, that the residents have deemed their park.");
        break;
      case 12: //around town end cut scene
        printBetter("The library is a large hall with a high cieling and two floors, surrounded on all sides by bookshelves. There are desks in the middle.");
        printBetter("At the end of the hall is the librarian's desk. "+player.getName()+" approaches it.");
        printBetter(player.getName()+": Excuse me, but we are looking for a specific book. It's story about people entering the demon realm.");
        printBetter("Librarian: Sure, it'll be on the first floor, left side, shelf 4.");
        printBetter(player.getName()+": Thanks.");
        printBetter(player.getName()+" approaches the shelf the librarian mentions and starts to scan through it.");
        printBetter("Eventually, "+player.getName()+" finds a book titled, \"Forbidden Adventures\".");
        printBetter("A quick scan reveals to "+player.getName()+" that they found the correct book.");
        printBetter("The story reads:");
        printBetter("The three heroes, after finally locating the fabled \"God Weapon\", traveled to the Daemonium Cliffs.");
        printBetter("That is the location of a link between the demon and mortal realms.");
        printBetter("When they arrive, they search the mountains for days.");
        printBetter("After a week, they locate the portal.");
        printBetter("It is a pattern of rocks with a slot in the middle.");
        printBetter("They experimented with ways of opening it, until they realized that the slot is for the God Weapon.");
        printBetter("They put it in, and the portal glows purple, and then opens.");
        printBetter("The three adventurers step in, ready to fight the horrid demons.");
        printBetter("Unfortunately, only 1 emerged.");
        printBetter("In an effort to prevent any daring youth to enter the portal, he hid the God Weapon deep in a swamp.");
        printBetter("After that, neither the last adventurer nor the weapon were ever seen again.");
        printBetter(player.getName()+": Well that was short.");
        printBetter(player.getName()+": Although the story didn't provide a lot of details, we did find out that the key to the demon realm is hidden in the swamp.");
        printBetter(player.getName()+": And the only swamp we know of is only a two day's walk from here.");
        printBetter(player.getName()+": Let's go!");
      case 13: //swamp beginning cutscene
        printBetter(player.getName() + " Well, this is the swamp.");
        printBetter("Short scraggy trees stick out of the saturated soil. Puddles make up half the ground.");
        printBetter("The sound of bugs is all around, and from the team's position, it doesn't look like the wetlands end.");
        printBetter("Reluctantly, the team enters.");
        break;
      case 14: //swamp end cutscene
        printBetter("As you near closer to the center of the swamp, you encounter a small stone structure.");
        printBetter("After closely inspecting it, you clearly see that there is a door on the front.");
        printBetter(player.getName()+": All ready to enter? Let's do this.");
        printBetter("The door scrapes open. A small set of stairs leads down to a surprisingly large room.");
        printBetter("The walls, floor, and ceiling are stone.");
        printBetter("At the end of the room, however, is a human skeleton chained to the wall by its wrists.");
        printBetter(player.getName()+" slowly approaches the skeleton to inspect it, but suddenly flames shoot out, forming a small demon.");
        battle(player, npc1, npc2, new Enemy(player.getLocationList()[6].getBoss()));
        printBetter("Finally, the demon is defeated. As " + player.getName()+" approaches the skeleton again, " + player.pro()+" realizes that it is clutching a weapon.");
        printBetter("It is dirty and damaged, but it is a weapon.");
        printBetter("Dirty Weapon acquired");
        addWeapon(player,13);
        printBetter("Satisfied with their experiences, the group heads back up to the surface to leave the swamp.");
        break;
      case 15: //easthaven beginning cutscene
        printBetter("With the artifact in hand, the team approaches the stone walls of Easthaven City.");
        printBetter("It is the largest settlement in all the lands, and densely populated.");
        printBetter("There are countless buildings, none made of wood, representing the wealth.");
        printBetter("The city itself is in a very mountainous area, so the buildings are staggered in levels.");
        printBetter("It is surely a sight to see.");
        printBetter("The team enters.");
        break;
      case 16: //easthaven city end cutscene
        String manName3 = "Carlton";
        printBetter("The steeple of the demonic temple looms over the group.");
        printBetter("The old door creaks open to reveal a red lit room full of candles.");
        printBetter(manName3 + ": ahh, guests.");
        printBetter(player.getName()+"looks up to see a man walking down a set of spiral stairs towards them.");
        printBetter(player.getName()+": We heard you are educated in the demon culture.");
        printBetter(manName3 + ": That is certainly correct. What do you need to know?");
        printBetter(player.getName()+": We need to know the location of the demon realm breach in the Daemonic Cliffs.");
        printBetter(manName3 + ": Why? To fight?");
        printBetter(player.getName()+": Yes, we are going to defeat the demon that has been terrorizing our villages.");
        printBetter(manName3 + ": Very well. The north side of the mountains. Under a large overhang.");
        printBetter(manName3 + ": But I'll warn you. Often the demons aren't unintelligent creatures who kill for no reason.");
        printBetter(manName3 + ": It may have a purpose for taking all those lives.");
        printBetter(manName3 + ": But I won't stop you from going. That is a decision you must make on your own.");
        printBetter(player.getName()+": Thank you, we'll be on our way now.");
        break;
      case 17: //daemonium cliffs beginning 
        printBetter("The mountains loomed in front of them. After walking along the north face they had located the overhang.");
        printBetter("Not to long after, they also found the portal.");
        printBetter("It was just as described; a circle of rocks that formed a pattern. And right in the center, a hole.");
        printBetter("Taking a guess, "+player.getName()+" plunges the dirty weapon into the hole.");
        printBetter("Instantly, to their surprise, it lit up purple, just like in the book.");
        printBetter(player.getName()+" pulls the weapon from the hole, but... it's no longer dirty.");
        printBetter("It's shiny and dazzling.");
        removeWeapon(player,13);
        addWeapon(player,6);
        printBetter("God Weapon acquired");
        printBetter("Meanwhile the portal opens up.");
        printBetter("The three look at each other and hop in.");
        printBetter("The team emerges from the other side.");
        printBetter("They look around. Their environment is almost like a forest, but very red, and very hot. There is no sky, only stone.");
        printBetter(player.getName()+": Looks like we're going to have to walk until we find the dastardly demon.");
        break;
      case 18: //demon realm end
        printBetter(player.getName()+" pushes through the last bit of hellish undergrowth to emerge into a clearing. There is a light coming from the ceiling.");
        printBetter(player.getName()+": What's this?");
        printBetter("Suddenly, a shadow falls over the clearing.");
        printBetter("A loud thud echoes through the cavern as a huge monstrous demon lands in the center.");
        printBetter("Demon: WHO DARES TO ENTER MY LAIR?");
        printBetter(player.getName()+": We want to challenge you, in revenge for burning down our village!");
        printBetter("Demon: Very well. But you will lose.");
        battle(player, npc1, npc2, new Enemy(player.getLocationList()[7].getBoss()));
        printBetter("The demon, suddenly feeble, falls to the ground in a heap.");
        printBetter("Then, in a raspy voice with lots of pauses, he says:");
        printBetter("You fool. You come here to defeat me but I am not your true threat. I had to kill your kind.");
        printBetter("I had to painfully end their lives. It was the only way.. The only way to stop it. The abomination. ");
        printBetter("An evil incomprehensible even for us demons. The more humans there are, the more he is summoned.");
        printBetter("I realized this all too late. Now his power is at its peak.");
        printBetter("Suddenly, a huge roar rocks the cave");
        printBetter("Demon: Quick bring me to my throne. Help me heal, and I can teleport you to the abomination’s location.");
        printBetter("You’ll have to trust me if you have any hope of defeating it.");
        printBetter("Demon: Here, a portal. Hop through and you will arrive near the abonimation. Be careful.");
        printBetter("The team exchange glances, and one by one, enter the portal.");
        break;
      case 19: // beginning eldrich fight scene.
        printBetter("The team arrives in a desolate area.");
        printBetter("There appears to be ruins all around them.");
        printBetter("Slowly the group realized that this used to be their home village.");
        printBetter("However now it is complety foriegn to " + player.getName() + ".");
        printBetter("There are pools of lava all around, and strange pillars have risen from the ground.");
        printBetter("The group looks up. A beast unlike any creature on the planet is hovering over this strange landscape.");
        printBetter("It catches your eyes and it paralyzes the team in fear. There are hardly words to describe the creature.");
        printBetter("But this is it. It is time to take back the land from the evil beings.");
        printBetter("It is time to fight.");
        battle(player, npc1, npc2, new Enemy(8));
        break;
      case 20: //end eldrich fight scene
        printBetter("The abomination freezes. It appears to be affected by a strong wind.");
        printBetter("It is torn apart. Bits of its flesh fly off its body and desintegrate until it is no longer.");
        printBetter("Finally it is over.");
        printBetter("The land has been freed from the evil.");
        printBetter(player.getName() + " feels a wonderful sense of completion.");
        printBetter("Their grand adventure ended in complete success.");
        break;
      case 21: //parade
        printBetter("A few days have passed.");
        printBetter("Applause and cheers echo from all around.");
        printBetter("Ceremonial music plays as your team is carried through Easthaven City.");
        printBetter("A parade has been thrown for the team's success in defeating the eternal evil that watched over the land.");
        printBetter("Thanks to you, the kingdom has been saved.");
        break;
    }
  }
}
