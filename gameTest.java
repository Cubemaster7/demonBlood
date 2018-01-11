import java.util.*;

public class gameTest {
  public static void main(String[] args) {
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
    
    randomPhrase();
    if (battle(player, npc1, npc2, new Enemy(1))) {
      System.out.println("The team won!");
    }
    else {
      System.out.println("The team died");
    }
    
    
  
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
  public static boolean battle(Character player, Character npc1, Character npc2, Enemy enemy) {
    player.startBattle();
    npc1.startBattle();
    npc2.startBattle();
    int aggro = 1;
    GameMusicPlayer music = new GameMusicPlayer("kittyMarchingBand.mp3"
                                               );
    music.play();
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
   String[] verbs = new String[]{"kill", "sniff", "jounce", "lick", "like", "rek", "joust", "befriend", "beat","eat","caress"};
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
  public static String getQuote(int character, int scenario) {
   //1=Australius 4=Random
   //1=Engaging enemy 2=Attack hit 3=Attack missed 4=Special Attack 5=Buff 6=Heal 7=Ended attack
    int choice = getRandom(1,3);
    switch (character) {
      case 1:
        switch (scenario) {
        case 1:
          switch (choice) {
          case 1:
            return "Australius: \"A new enemy approaches!\"";
         
          case 2:
            return "Australius: \"Oh no, more enemies!\"";
            
          case 3:
            return "Australius: \"I really hate these guys...\"";
            
        }
      
        default:
          return "Australius: \"...\"";
      }
      default:
        return "...";
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
}
