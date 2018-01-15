public class Location{
  private String name;
  private String description;
  private int recommendedLevel;
  private int manymies;
  private int[] enemyTypes;
  private int[] weaponTypes;
  private String area;
  private boolean areaRevealed = false;
  private int id;
  private int firstTimeCutscene;
  private int endCutscene;
  private int boss;
  private boolean ableToGo = false;
  private boolean haventBeen = true;
  private String dialogue1;
  private String dialogue2;
  private String dialogue3;
  public Location(int id){   
    this.id = id;
    switch (id) {
      case 0:
        name = "Everton Forest";
        description = "A large copse of tall trees. The expansive branch systems cast dappled shadows on the paths below.";
        recommendedLevel = 5;
        manymies = 3;
        enemyTypes = new int[]{2,3,4}; //References enemy id's in enemy.java.
        break;
      case 1:
        name = "Refugee Camp";
        description = "An area full of people made homeless by the demon's wrath";
        area = "Central Office";
        manymies = 0;
        dialogue1 = "1";
        dialogue2 = "2";
        dialogue3 = "3";
        break;
      case 2:
        name = "Whiteridge";
        description = "The starting town";
        manymies = 0;
        firstTimeCutscene = 1;
        endCutscene = 2;
        weaponTypes = new int[]{1,2};
        boss = 1;
        ableToGo=true;
        break;
      case 3:
        name = "Warrington";
        description = "A walled town with a cozy feel";
        area = "Tavern";
        manymies = 0;
        dialogue1 = "1";
        dialogue2 = "2";
        dialogue3 = "3";
        break;
      case 4:
        name = "Field of Stones";
        description = "A flat expanse of stones left there by the glaciers that shaped the land long ago";
        manymies = 3;
        enemyTypes = new int[]{2,3,4};
        break;
      case 5:
        name = "Deepland Swamp";
        description = "An area of several acres of swampland. Rumored to hide mysteries";
        manymies = 3;
        enemyTypes = new int[]{2,3,4};
        break;
      case 6:
        name = "Daemonium Cliffs";
        description = "A dreary mountain range which is the source of many demonic legneds";
        manymies = 3;
        enemyTypes = new int[]{2,3,4};
        break;
      case 7:
        name = "Around Town";
        description = "Circular town surrounding a small forest";
        area = "Library";
        manymies = 0;
          dialogue1 = "1";
        dialogue2 = "2";
        dialogue3 = "3";
        break;
      case 8:
        name = "Easthaven City";
        description = "A very mountainous city bustling with people";
        area = "Demonic Temple";
        manymies = 0;
          dialogue1 = "1";
        dialogue2 = "2";
        dialogue3 = "3";
        break;
        
    }
  }
  public String getName(){
    return name;
  }
  public String getDesc(){
    return description;
  }
  public int getRec(){
    return recommendedLevel;
  }
  public int getMany(){
    return manymies;
  }
  public int getTypes(int number){
    return enemyTypes[number];
  }
  public String getDialogue(int person) {
    switch (person) {
      case 1:
      return dialogue1;
      case 2:
      return dialogue2;
      case 3:
      return dialogue3;
      default:
      return "...";
    }
  }
  public boolean haventBeen() {
    if (haventBeen) {
      haventBeen = false;
      return true;
    }
    else {
      return false;
    }
  }
  public int getFirstTimeCutscene() {
    return firstTimeCutscene;
  }
  public int getLastTimeCutscene() {
    return endCutscene;
  }
  public boolean getAreaRevealed() {
   return areaRevealed; 
  }
  public String getArea() {
    return area;
  }
  public int getBoss() {
   return boss; 
  }
  public int[] getWeaponTypes() {
    return weaponTypes;
  }
  public int[] getEnemyTypes() {
    return enemyTypes;
  }
  public boolean getAbleToGo() {
    return ableToGo;
  }
  public void setAbleToGo() {
   ableToGo = true; 
  }
}