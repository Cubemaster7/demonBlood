public class Weapon {
  private String name;
  private String adjective;
  private int str;
  private int con;
  private int agi;
  private int dex;
  private int price;
  private int id;
  public Weapon(int id){
   this.id = id;
   switch (id) { 
     case 1:
       name = "Wooden Weapon";
       adjective = "Wooden";
       str = 0;
       con = 0;
       agi = 0;
       dex = 0;
       price = 10;
       break;
     case 2:
       name = "Steel Weapon";
       adjective = "Steel";
       str = 1;
       con = 1;
       agi = 1;
       dex = 1;
       price = 30;
       break;
     case 3:
       name = "Magic Weapon";
       adjective = "Magic";
       str = 2;
       con = 2;
       agi = 2;
       dex = 2;
       price = 50;
       break;
     case 4:
       name = "Crystal Weapon";
       adjective = "Crystal";
       str = 3;
       con = 3;
       agi = 3;
       dex = 3;
       price = 100;
       break;
     case 5:
       name = "Holy Weapon";
       adjective = "Holy";
       str = 4;
       con = 4;
       agi = 4;
       dex = 4;
       price = 300;
       break;
     case 6:
       name = "Godly Weapon";
       adjective = "Godly";
       str = 5;
       con = 5;
       agi = 5;
       dex = 5;
       price = 1000;
       break;
   }
  }
 

  public String getName(){
    return name;
  }
  public String getDesc(){
    return description;
  }
 
  public int getLevel(){
    return level;
  }
  public int getStr() {
    return str;
  }
  public int getCon() {
    return con;
  }
  public int getAgi() {
    return agi;
  }
  public int getDex() {
    return dex;
  }
  public int getPrice(){
    return price;
  }
  public int getId(){
    return id;
  }
 
  public String getAdjective() {
    return adjective;
  }
}
