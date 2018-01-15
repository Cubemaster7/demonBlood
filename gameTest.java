import java.util.*;
public class gameTest {
  public static void main(String[] args) {
    GameTitleCard gmt = new GameTitleCard("DEMONBLOOD.png", 614, 460);
    gmt.isVisible(true);
    System.out.println("Who would you like to play as?");
    System.out.println("1. Australius Gage");
    System.out.println("2. Claudius Steed");
    System.out.println("3. Rosalina Berry");
    int userin = getInput(3);
    Character player = new Character(userin, 1);
    Character npc1;
    Character npc2;
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
    boolean playing = true;
    int location = 0;
    
    while (playing) {
     if (player.getLocationList()[location].haventBeen()) {
       cutscene(player.getLocationList()[location].getFirstTimeCutscene(), player, npc1, npc2);
     }
      if (player.getLocationList()[location].getMany() == 0 && player.getLocationList()[location].getAreaRevealed()) {
      System.out.println("1=Talk to people 2=Customize 3=Shop 4=Leave 5=Go to "+player.getLocationList()[location].getArea());
      userin = getInput(5);
     }
     else if 
       (player.getLocationList()[location].getMany() == 0 && !player.getLocationList()[location].getAreaRevealed()) {
      System.out.println("1=Talk to people 2=Customize 3=Shop 4=Leave");
      userin = getInput(4);
     }
     else {
       System.out.println("1=Fight enemies 2=Customize 3=Fight "+new Enemy(player.getLocationList()[location].getBoss()).getName()+"4=Leave");
       userin = getInput(4);
     }
     if (player.getLocationList()[location].getMany() == 0 && userin==1) {
       printBetter(player.getLocationList()[location].getDialogue(getRandom(1,3)));
     }
     else if (player.getLocationList()[location].getMany() != 0 && userin==1) {
      battle(player, npc1, npc2, new Enemy(player.getLocationList()[location].getEnemyTypes()[getRandom(0,player.getLocationList()[location].getEnemyTypes().length-1)]));
     }
     else if (userin == 2) {
       customize(player, npc1, npc2);
     }
     else if (player.getLocationList()[location].getMany() == 0 && userin == 3) {
       shop(player, location);
     }
     else if (player.getLocationList()[location].getMany() != 0 && userin == 3) {
       battle(player, npc1, npc2, new Enemy(player.getLocationList()[location].getBoss()));
     }
     else if (userin == 4) {
       int availablePlaces = 0;
       for (int i = 0; i<player.getLocationList().length; i++ ) {
         if (player.getLocationList()[i].getAbleToGo()) {
         System.out.println((i+1)+": "+player.getLocationList()[i].getName());
         availablePlaces++;
         }
       }
       location = getInput(availablePlaces)-1;
       printBetter("The team arrived at "+player.getLocationList()[location].getName()+"!");
     }
    }
    randomPhrase();
  
  }
  public static int getInput(int max) {
   System.out.println("Enter a number from 1 to "+max+".");
   Scanner input = new Scanner(System.in);
   int userin = 0;
   while (userin <= 0 || userin > max) {
    userin = input.nextInt();
    if (userin <= 0 || userin > max) {
     System.out.println("Invalid input!");
    }
   }
   return userin;
  }
  public static void addWeapon(Character player, int id) {
   player.getInventory().add(new Weapon(id));
  }
  public static void removeWeapon(Character player, int id) {
    for (int i = (player.getInventory().size())-1; i >= 0; i--) {
      if (player.getInventory().get(i).getId() == id) {
       player.getInventory().remove(i); 
       break;
      }
    }
  }
  public static void printInventory(Character player) {
    for (Weapon weapon : player.getInventory()) {
      System.out.println(weapon.getName());
    }
  }
  public static void battle(Character player, Character npc1, Character npc2, Enemy enemy) {
    player.startBattle();
    npc1.startBattle();
    npc2.startBattle();
    int aggro = 1;
   
    printBetter(enemy.getName()+" begins a battle!");
    //printBetter(getQuote(1,1));
    while ((player.getHp()!=0 || npc1.getHp()!=0 || npc2.getHp()!=0) && enemy.getHp() != 0) {
      System.out.println(player.getName()+": "+player.getHp()+"HP | Special 1: "+boolToReady(player.getSpecial(1).isReady())+" | Special 2: "+boolToReady(player.getSpecial(2).isReady())+" | Special 3: "+boolToReady(player.getSpecial(3).isReady()));
      System.out.println(npc1.getName()+": "+npc1.getHp()+"HP");
      System.out.println(npc2.getName()+": "+npc2.getHp()+"HP");
      System.out.println(enemy.getName()+": "+enemy.getHp()+"HP");
      if(player.isAlive()) {
      System.out.println("1=Standard Attack 2=Special Attack 3=Flee");
      int userin = getInput(3);
      switch (userin) {
        case 1:
          characterAttack(player, enemy, 1);
          break;
        case 2:
          System.out.println("1. "+player.getSpecial(1).getName()+" is "+boolToReady(player.getSpecial(1).isReady()));
          System.out.println("2. "+player.getSpecial(2).getName()+" is "+boolToReady(player.getSpecial(2).isReady()));
          System.out.println("3. "+player.getSpecial(3).getName()+" is "+boolToReady(player.getSpecial(3).isReady()));
          int special = getInput(3);
          if (player.getSpecial(special).isReady()) {
           specialAttack(player, enemy, player.getSpecial(special), npc1, npc2); 
          }
          else {
            printBetter(player.getSpecial(special).getName()+" is not ready yet!");
          }
          break;
        case 3:
          if (enemy.getAbleToFlee()) {
          if (getRandom(1,100) > 70) {
            printBetter("The team ran away!");
            enemy.modHp(-1000, 1);
          }
          else {
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
      npcAttack(npc1, enemy, player, npc2);
        }
        if (npc2.isAlive()) {
      npcAttack(npc2, enemy, player, npc1);
        }
      int aggroCheck = enemy.aggroCheck();
      if (aggroCheck == 1) {
        aggro = 1;
        printBetter(enemy.getName()+" turns their attention towards "+player.getName()+"!");
      }
       else if (aggroCheck == 2) {
        aggro = 2;
        printBetter(enemy.getName()+" turns their attention towards "+npc1.getName()+"!");
      }
       else if (aggroCheck == 3) {
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
    if (player.getHp() == 0 && npc1.getHp() == 0 && npc2.getHp() == 0) {
      System.out.print("Ah jeez you are dead");
    }
    else {
      System.out.println("Woohoo you totally made it my buddy");
    }
  }
   public static boolean tutorial(Character player, Enemy enemy) {
    player.startBattle();   
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
          characterAttack(player, enemy, 1);
          break;
        case 2:
          System.out.println("1. "+player.getSpecial(1).getName()+" is "+boolToReady(player.getSpecial(1).isReady()));
          System.out.println("2. "+player.getSpecial(2).getName()+" is "+boolToReady(player.getSpecial(2).isReady()));
          System.out.println("3. "+player.getSpecial(3).getName()+" is "+boolToReady(player.getSpecial(3).isReady()));
          int special = getInput(3);
          if (player.getSpecial(special).isReady()) {
           specialAttack(player, enemy, player.getSpecial(special), new Character(2, 2), new Character(3, 3)); 
          }
          else {
            printBetter(player.getSpecial(special).getName()+" is not ready yet!");
          }
          break;
        default:
          break;
      }
      }
      if (enemy.getHp()!=0) {
      enemyAttack(enemy, player);
      player.incrementSpecials();
    }
    }
    if (player.getHp() == 0) {
      return false;
    }
    else {
      return true;
    }
  }
  public static void characterAttack(Character character, Enemy enemy, int attackerId) {
   
  int chance = getChance(character.getDex(), enemy.getAgi());
  //printBetter("There is a "+chance+"% chance of a hit");
  int hit = getRandom(1,100);
  //printBetter("Rolled a "+hit+", needed a "+(100-chance));
  if (hit >= (100-chance)) {
    
    int damage = getDamage(character.getStr(), enemy.getCon())/1;
    printBetter(character.getName()+" hits "+enemy.getName()+" for "+damage+" damage!");
  enemy.modHp(0-damage, attackerId);
  
  }
  else {
    printBetter(character.getName()+" attacks "+enemy.getName()+" and misses!");
  }
  }
  public static void npcAttack(Character npc, Enemy enemy, Character player, Character otherNpc) {
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
  public static void enemyAttack(Enemy enemy, Character character) {
   
  int chance = getChance(enemy.getDex(), character.getAgi());
  //printBetter("There is a "+chance+"% chance of a hit");
  int hit = getRandom(1,100);
  //printBetter("Rolled a "+hit+", needed a "+(100-chance));
  if (hit >= (100-chance)) {
    
    int damage = getDamage(enemy.getStr(), character.getCon())/1;
    printBetter(enemy.getName()+" hits "+character.getName()+" for "+damage+" damage!");
  character.modHp(0-damage);
  
  }
  else {
    printBetter(enemy.getName()+" attacks "+character.getName()+" and misses!");
  }
  }
    public static void specialAttack(Character character, Enemy enemy, SpecialAttack special, Character mate1, Character mate2) {
      printBetter(character.getName()+": \""+special.getName()+"!\"");
      if (special.getIsBuff()) {
        if (special.getBuffStat().equals("str")) {
         character.setStr((int) (character.getStr()+special.getBuff()));
         printBetter(character.getName()+"'s strength stat is now "+character.getStr()+"!");
        }
        else if (special.getBuffStat().equals("con")) {
         character.setCon((int) (character.getCon()+special.getBuff()));
         printBetter(character.getName()+"'s constitution stat is now "+character.getCon()+"!");
        }
        else if (special.getBuffStat().equals("agi")) {
          character.setAgi((int) (character.getAgi()+special.getBuff()));
          printBetter(character.getName()+"'s agility stat is now "+character.getAgi()+"!");
        }
        else if (special.getBuffStat().equals("dex")) {
          character.setDex((int) (character.getDex()+special.getBuff()));
          printBetter(character.getName()+"'s dexterity stat is now "+character.getDex()+"!");
        }
      }
      if (special.getIsDamage()) {
        int damage = getDamage(character.getStr(), enemy.getCon())*special.getDamage();
        printBetter(enemy.getName()+" takes "+damage+" damage!");
        enemy.modHp(0-damage, character.getGameId());
      }
      if (special.getIsHeal()) {
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
      special.resetCooldown();
    }
  
  public static int getRandom(int min, int max) {
    Random rand = new Random();
   return rand.nextInt(max - min + 1) + min;
  }
  
  
  public static void printBetter(String message) {
   Scanner input = new Scanner(System.in);
   System.out.println(message);
   input.nextLine();
   input.close();
  }
  public static void randomPhrase() {
   System.out.print("The 3 heroes set off to ");
   String[] verbs = new String[]{"kill", "sniff", "jounce", "lick", "like", "wreck", "joust", "befriend", "beat","eat","caress"};
   String[] nouns = new String[]{"limb", "friend", "pope", "broadsword", "lady", "King", "world", "meat","AP Computer Science Class", "bible"};
   System.out.print(verbs[getRandom(0,(verbs.length-1))]);
   System.out.print(" the ");
   System.out.print(nouns[getRandom(0,(nouns.length-1))]);
   System.out.println("!");
  }
  
  public static int getChance(int attackerStat, int defenderStat) {
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
  
  public static int getDamage(int attackerStat, int defenderStat) {
   int returner;
   int statDif = attackerStat-defenderStat;
   returner = (int) (4*statDif)+50
     
     ;
   return returner;
  }
  public static void customize(Character player, Character npc1, Character npc2) {
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
        customizeSubMenu(player, player);
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
  public static void customizeSubMenu(Character character, Character player) {
    boolean changing = true;
    while (changing) {
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
    else {
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
  public static void shop(Character player, int location) {
    printBetter("Shopkeeper: \"Buy somethin' will ya!\"");
    
    boolean shopping = true;
    while (shopping) {
    printBetter(player.getName()+" has "+player.getCoins()+" coins.");
    System.out.println("1=Buy 2=Sell 3=Exit");
    int userin = getInput(3);
    switch (userin) {
      case 1:
        for (int i = 0; i<player.getLocationList()[location].getWeaponTypes().length; i++) {
         System.out.println((i+1)+"="+new Weapon(player.getLocationList()[location].getWeaponTypes()[i]).getName()+", "+new Weapon(player.getLocationList()[location].getWeaponTypes()[i]).getPrice()+" coins");
      }
        System.out.println((player.getLocationList()[location].getWeaponTypes().length+1)+"=Exit");
        userin = getInput(player.getLocationList()[location].getWeaponTypes().length+1);
        if (userin != player.getLocationList()[location].getWeaponTypes().length+1) {
          if (player.getCoins() >= new Weapon(player.getLocationList()[location].getWeaponTypes()[userin-1]).getPrice()) {
            addWeapon(player, player.getLocationList()[location].getWeaponTypes()[userin-1]);
            printBetter("Bought "+new Weapon(player.getLocationList()[location].getWeaponTypes()[userin-1]).getName()+"!");
            player.modCoins(-(new Weapon(player.getLocationList()[location].getWeaponTypes()[userin-1]).getPrice()));
          }
          else {
            printBetter("Cannot afford "+new Weapon(player.getLocationList()[location].getWeaponTypes()[userin-1]).getName()+"!");
          }
        }
        break;
      case 2:
       for (int i = 0; i<player.getInventory().size(); i++) {
       System.out.println((i+1)+"="+player.getInventory().get(i).getName()+", "+player.getInventory().get(i).getPrice()+" coins"); 
      }
       System.out.println((player.getInventory().size()+1)+"=Exit");
       userin = getInput(player.getInventory().size()+1);
       if (userin != player.getInventory().size()+1) {
         player.modCoins(player.getInventory().get(userin-1).getPrice());
         printBetter("Sold "+player.getInventory().get(userin-1).getName()+"!");
         removeWeapon(player, player.getInventory().get(userin-1).getId());
       }
       break;
      case 3:
        printBetter("Shopkeeper: \"Come again!\"");
        shopping = false;
    
    }
    }
  }
    public static void cutscene(int cutscene, Character player, Character npc1, Character npc2) {
    switch (cutscene) {
      case 1: //First time cutscene for village. Village burns down, before fighting demons for tutorial
        printBetter("The heavy wooden door of the tavern thuds closed behind "+player.getFullName()+" as "+player.pro()+" heads out into the brisk night. (Press Enter to continue reading text)");
        printBetter(player.getName()+" is disappointed to have to leave "+player.pos()+" friends so early, but "+player.pro()+" has a lot of work to do in the morning.");
        printBetter(player.getName()+" stares back at the wooden walls of the tavern for a few seconds, taking in the dulled noises of clinking dishes and human commotion.");
        printBetter("Taking a deep sigh, "+player.pro()+" starts on "+player.pos()+" way down the beaten main road and side streets to home, a cozy shack in the southwestern quadrant of the village.");
        GameMusicPlayer music = new GameMusicPlayer("countdown.mp3");
        music.play();
        printBetter("Suddenly, the world lights up and noise erupts all around "+player.getName()+"!");
        printBetter(player.getName()+" turns around, startled, to see the tavern in flames.");
        printBetter("Screams echo from inside as the bodies burn in the hungry flames. Soon, houses and buildings all throughout the village undergo the same gruesome fate.");
        printBetter("AND THEN HERE IS THE PART WHERE THEY NEED TO DO THE DEMON WEAKLING FIGHT TUTORIAL");
        tutorial(player, new Enemy(0));
        printBetter("AND THEN THERE'S THE PART WHERE THE PLAYER CHARACTER MEETS UP WITH THEIR 2 FRIENDS AND THEN START FIGHTING A DEMON");
        battle(player, npc1, npc2, new Enemy(1));
        break;
        
    }

  }
}
