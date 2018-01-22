public class Enemy {
  private int id;
  private int hp;
  private int exp;
  private int maxHp;
  private int str;
  private int con;
  private int dex;
  private int agi;
  private boolean ableToFlee;
  private String name = new String();
  private int aggro=1; //The ID of the character who the enemy attacks
  private int aggro1=0; 
  private int aggro2=0; 
  private int aggro3=0; 
  private int coins;
  
  
  public Enemy(int newId) {
    id = newId;
    switch (id) {
      case 1:
        name = "Demon Weakling";
        maxHp = 500;
        str = 5;
        con = 5;
        dex = 3;
        agi = 3;
        ableToFlee = true;
        exp = 25;
        coins = 5;
        break;
      case 2:
        name = "Demon";
        maxHp = 1000;
        str = 20;  //damage
        con = 20;  //defense
        dex = 10;   //chance to hit
        agi = 10;   //chance to dodge
        ableToFlee = true;
        exp = 30;
        coins = 10;
        break;
      case 3:
        name = "Constricted Demon";
        maxHp = 750;
        str = 10;
        con = 10;
        dex = 6;
        agi = 6;
        ableToFlee = true;
        exp = 27;
        coins = 20;
        break;
      case 4:
        name = "Taint Demon";
        maxHp = 1500;
        str = 25;
        con = 20;
        dex = 15;
        agi = 10;
        exp = 40;
        coins = 50;
        ableToFlee = false;
        break;
      case 5:
        name = "Boulder Demon";
        maxHp = 1500;
        str = 25;
        con = 25;
        dex = 10;
        agi = 5;
        exp = 40;
        coins = 50;
        ableToFlee = false;
        break;
      case 6:
        name = "Wither Demon";
        maxHp = 1500;
        str = 20;
        con = 25;
        dex = 15;
        agi = 10;
        exp = 40;
        coins = 50;
        ableToFlee = false;
        break;
      case 7:
        name = "Rage Demon";  //one that attacked village
        maxHp = 2000;
        str = 25;
        con = 25;
        dex = 25;
        agi = 25;
        exp = 50;
        coins = 100;
        ableToFlee = false;
        break;
      case 8:
        name = "Eldrich Abomination";  
        maxHp = 3000;
        str = 30;
        con = 30;
        dex = 10;
        agi = 10;
        exp = 100;
        coins = 250;
        ableToFlee = false;
        break;
      case 9:
        name = "Demon Soldier";
        str=12;
        con=12;
        dex=6;
        agi=6;
        exp=30;
        coins = 20;
        break;
      case 10:
        name="Demon Specialist";
        str=18;
        con=18;
        dex=10;
        agi=10;
        exp=30;
        coins = 20;
        break;
      case 11:
        name="Demon Brute";
        str=20;
        con=10;
        dex=20;
        agi=10;
        exp=30;
        coins = 20;
        break;
    }
    hp = maxHp;
  }
  public String getName() {
    return name;
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
  public void modHp(int hpMod, int attackerId) {
    hp += hpMod;
    if (hp <= 0) {
      hp = 0;
    }
    if (attackerId==1) {
      aggro1 -= hpMod;
    }
    else if (attackerId==2) {
      aggro2 -= hpMod;
    }
    else {
      aggro3 -= hpMod;
    }
  }
  public int aggroCheck() { //Returns 0 if no change and returns 1-3 for aggro change
    if (aggro1 >= aggro2 && aggro1 >= aggro3) {
      if (aggro != 1) {
        aggro = 1;
        return 1;
      }
      else {
        return 0;
      }
    }
    else if (aggro2 >= aggro1 && aggro2 >= aggro3) {
      if (aggro != 2) {
        aggro = 2;
        return 2;
      }
      else {
        return 0;
      }
    }
    else {
      if (aggro != 3) {
        aggro = 3;
        return 3;
      }
      else {
        return 0;
      }
    }
  }
  public void changeAggro(int gameId, double aggroMod) {
    if (gameId==1) {
      aggro1 = (int) (aggro1 * aggroMod);
    }
    else if (gameId==2) {
      aggro2 = (int) (aggro2 * aggroMod);
    }
    else {
      aggro3 = (int) (aggro3 * aggroMod);
    }
  }
  public int getStr() {
    return str;
  }
  public int getCon() {
    return con;
  }
  public int getDex() {
    return dex;
  }
  public int getAgi() {
    return agi;
  }
  public boolean getAbleToFlee() {
    return ableToFlee; 
  }
  public int getExp(){
    return exp;
  }
  public int getCoins(){
    return coins;
  }
}