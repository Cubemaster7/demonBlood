public class Enemy {
  private int id; //enemy id
  private int hp; //health
  private int exp; //experience it gives when killed
  private int maxHp; //maximum health
  private int str; //attack stat
  private int con;//defense stat
  private int dex;//chance to hit stat
  private int agi; //chance to dodge stat
  private boolean ableToFlee; //if you can flee from the enemy
  private String name = new String();
  private int aggro=1; //The ID of the character who the enemy attacks
  private int aggro1=0; 
  private int aggro2=0; 
  private int aggro3=0; 
  private int coins; //coins it drops
  
  
  public Enemy(int newId) { //id calls the appropriate case
    id = newId;
    switch (id) {
      case 1:
        name = "Demon Weakling"; //name
        maxHp = 500; //max health
        str = 5; //attack stat
        con = 5; //defense stat
        dex = 3; //chance to hit stat
        agi = 3; //chance to dodge stat
        ableToFlee = true; //you can flee
        exp = 25; //drops 25 cups of blood
        coins = 5; //drops 5 coins
        break;
      case 2:
        name = "Demon"; //basic demon
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
        name = "Constricted Demon"; //lower tier demon
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
        name = "Taint Demon"; //mini-boss
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
        name = "Boulder Demon";//mini-boss
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
        name = "Wither Demon";//mini-boss
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
        name = "Rage Demon";  //one that attacked village, kinda final boss
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
        name = "Eldrich Abomination";   //final boss
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
        name = "Demon Soldier"; //top tier demon
        str=12;
        con=12;
        dex=6;
        agi=6;
        exp=30;
        coins = 20;
        break;
      case 10:
        name="Demon Specialist";//top tier demon
        str=18;
        con=18;
        dex=10;
        agi=10;
        exp=30;
        coins = 20;
        break;
      case 11:
        name="Demon Brute";//top tier demon
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
  //getters and setters
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
  public void modHp(int hpMod, int attackerId) { //controls health during battle
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