import java.util.*;
public class Character {
 private String name = new String();
 private String fullName = new String();
 private int maxHp = 1000;
 private int hp = 1000;
 private int str = 10;
 private int con = 10;
 private int dex = 10;
 private int agi = 10;
 private int baseStr = 10;
 private int baseCon = 10;
 private int baseDex = 10;
 private int baseAgi = 10;
 private int exp = 0;
 private int lvl = 0;
 private int id;
 private int coins = 50;
 private String pronoun;
 private String posPronoun;
 private boolean alive = true;
 private Weapon weapon = new Weapon(1);
 private ArrayList<Weapon> inventory=new ArrayList<Weapon>();  
 private Location[] locationList = new Location[9];

 private SpecialAttack special1;
 private SpecialAttack special2;
 private SpecialAttack special3;
 private int gameId;
 
 
 public Character(int id, int gameId) {
   if (id == 1) {
     name = "Australius";
     fullName = "Australius Gage";
     this.id = 1;
     special1 = new SpecialAttack(1);
     special2 = new SpecialAttack(2);
     special3 = new SpecialAttack(3);
     pronoun = "he";
     posPronoun = "his";
    
   }
   else if (id == 2) {
     name = "Claudius";
     fullName = "Claudius Steed";
     this.id = 2;
     special1 = new SpecialAttack(4);
     special2 = new SpecialAttack(5);
     special3 = new SpecialAttack(6);
     pronoun = "he";
     posPronoun = "his";
   
   }
   else if (id == 3) {
    name = "Rosalina"; 
    fullName = "Rosalina Berry";
    this.id = 3;
    special1 = new SpecialAttack(7);
    special2 = new SpecialAttack(8);
    special3 = new SpecialAttack(9);
    pronoun = "she";
    posPronoun = "her";
  
   }
   this.gameId = gameId;
   if (gameId==1) {
    locationList[0] = new Location(0); //whiteridge
    locationList[1] = new Location(1);  //refugee camp
    locationList[2] = new Location(2);  //forest
    locationList[3] = new Location(3);  //warrington
    locationList[4] = new Location(4); //Field of Stones
    locationList[5] = new Location(5); //around town
    locationList[6] = new Location(6); //Deepland swamp
    locationList[7] = new Location(7);  //easthaven city
    locationList[8] = new Location(8);  //daemonium cliffs
   }
 }
   
   public String getName() {
     return name;
   }
   public String getFullName() {
     return fullName;
   }
   public int getMaxHp() {
     return maxHp;
   }
   public void setMaxHp(int newMaxHp) {
     maxHp = newMaxHp;
   }
   public int getHp() {
     return hp;
   }
    public void setStr(int newStr) {
     str = newStr;
   }
   public int getStr() {
     return str;
   }
    public void setCon(int newCon) {
     con = newCon;
   }
   public int getCon() {
     return con;
   }
    public void setDex(int newDex) {
     dex = newDex;
   }
   public int getDex() {
     return dex;
   }
    public void setAgi(int newAgi) {
     agi = newAgi;
   }
   public int getAgi() {
     return agi;
   }
   public int getExp() {
     return exp;
   }
   public void modExp(int expMod) {
     exp += expMod;
   }
   public int getLvl() {
     return lvl;
   }
   public int getId() {
     return id;
   }
   public void modHp(int hpMod) {
     hp += hpMod;
     if (hp <= 0) {
       hp = 0;
       alive = false;
     }
     if (hp >= maxHp) {
       hp = maxHp;
     }
   }
   public void startBattle() {
     special1.resetCooldown();
     special2.resetCooldown();
     special3.resetCooldown();
     str = baseStr;
     agi = baseAgi;
     con = baseCon;
     dex = baseDex;
     alive = true;
   }
   public SpecialAttack getSpecial(int id) {
     switch (id) {
       case 1: 
         return special1;
       case 2:
         return special2;
       case 3:
         return special3;
       default:
         return special1;
     }
   }
   public void incrementSpecials() {
    special1.increment();
    special2.increment();
    special3.increment();
   }
   public int getGameId() {
    return gameId; 
   }
   public boolean isAlive() {
     return alive;
   }
   public String pro() {
     return pronoun;
   }
   public String pos() {
     return posPronoun;
   }
   public void modCoins(int coinMod) {
     coins += coinMod;
   }
   public int getCoins() {
     return coins;
   }
   public Location[] getLocationList() {
     return locationList;
   }
   public ArrayList<Weapon> getInventory() {
    return inventory; 
   }
   public String getWeaponName() {
     switch (id) {
       case 1:
         return (weapon.getAdjective()+" Sword");
       case 2:
         return (weapon.getAdjective()+" Club");
       case 3:
         return (weapon.getAdjective()+" Spear");
     }
     return " ";
   }
   public Weapon getWeapon() {
     return weapon;
   }
   public void setWeapon(int id) {
     weapon = new Weapon(id);
   }
}