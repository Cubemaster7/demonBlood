public class Enemy {
 private int id;
 private int hp;
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
 
 
 public Enemy(int newId) {
   id = newId;
   switch (id) {
     case 0:
     name = "Demon Weakling";
     maxHp = 500;
     str = 5;
     con = 5;
     dex = 3;
     agi = 3;
     ableToFlee = true;
     break;
     
     case 1:
     name = "Demon";
     maxHp = 1000;
     str = 20;
     con = 20;
     dex = 10;
     agi = 10;
     ableToFlee = true;
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
}