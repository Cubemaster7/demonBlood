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
  private boolean haventBeen2 = true;
  private String dialogue1;
  private String dialogue2;
  private String dialogue3;
  private boolean cutsceneComplete = false;
  public Location(int id){   
    this.id = id;
    switch (id) {
      case 0:
        name = "Whiteridge";
        description = "The starting town";
        manymies = 0;
        firstTimeCutscene = 1;
        endCutscene = 2;
        boss = 1;
        ableToGo=false;
        break;
      case 1:
        name = "Refugee Camp";
        description = "An area full of people made homeless by the demon's wrath";
        area = "Central Office";
        firstTimeCutscene = 3;
        endCutscene = 4;
        manymies = 0;
        dialogue1 = "Brenda: That dastardly demon ruined all of our lives! It goes village to village, murdering the innocent!";
        dialogue2 = "Louzyr: You look new. You should head over to the main tent.";
        dialogue3 = "Reese: They may provide us with food, but in the end, we're all gunna starve. The fields burned!";
        ableToGo=true;
        break;
      case 2:
        name = "Everton Forest";
        description = "A large copse of tall trees. The expansive branch systems cast dappled shadows on the paths below.";
        recommendedLevel = 5;
        firstTimeCutscene = 5;
        endCutscene = 6;
        manymies = 3;
        enemyTypes = new int[]{2,3,4}; //References enemy id's in enemy.java.
        break;
      case 3:
        name = "Warrington";
        description = "A walled town with a cozy feel";
        area = "Tavern";
        firstTimeCutscene = 7;
        endCutscene = 8;
        manymies = 0;
        dialogue1 = "Joel: Don't you love it when the sun sets over the treeline?";
        dialogue2 = "Biggus: Some people here believe in really wacky stuff. I hear they praise demons. Especially those who frequent the taverns.";
        dialogue3 = "Peter: I'm always being sent to the store to buy potatoes.";
        weaponTypes = new int[]{1,2,7,8};
        break;
      case 4:
        name = "Field of Stones";
        description = "A flat expanse of stones left there by the glaciers that shaped the land long ago";
        firstTimeCutscene = 9;
        endCutscene = 10;
        manymies = 3;
        enemyTypes = new int[]{2,3,4};
        break;
      case 5:
        name = "Around Town";
        description = "Circular town surrounding a small forest";
        area = "Library";
        firstTimeCutscene = 11;
        endCutscene = 12;
        manymies = 0;
        dialogue1 = "Ellie: Visiting the forest is my favorite part about living here.";
        dialogue2 = "Gil: I've always wanted to visit the restricted section of the library. Too bad I'm dirt poor. Those records must be mystical.";
        dialogue3 = "Dand: Hey buddy, wanna travel Around Town with me?";
        weaponTypes = new int[]{1,2,7,8,3,4,9,10};
        break;
      case 6:
        name = "Deepland Swamp";
        description = "An area of several acres of swampland. Rumored to hide mysteries.";
        firstTimeCutscene = 13;
        endCutscene = 14;
        manymies = 3;
        enemyTypes = new int[]{2,3,4};
        break;
      case 7:
        name = "Easthaven City";
        description = "A very mountainous city bustling with people";
        area = "Demonic Temple";
        firstTimeCutscene = 15;
        endCutscene = 16;
        manymies = 0;
        dialogue1 = "Salva: Have you seen my family?";
        dialogue2 = "Pat: Easthaven City is a paradise of smart folks. The only remnant of darker times is the old demonic temple downtown. It's worth checking out, in my opinion.";
        dialogue3 = "Chad: I don't believe that anyone could be happier than people who live here.";
        weaponTypes = new int[]{1,2,3,4,5,7,8,9,10,11};
        break;
      case 8:
        name = "Daemonium Cliffs";
        description = "A dreary mountain range which is the source of many demonic legends";
        firstTimeCutscene = 17;
        endCutscene = 0;
        manymies = 3;
        enemyTypes = new int[]{2,3,4};
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
  public int getDialogue(int person) {
    switch (person) {
      case 1:
      System.out.println(dialogue1);
      break;
      case 2:
      System.out.println(dialogue2);
      break;
      case 3:
      System.out.println(dialogue3);
      break;
    }
    return person;
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
  public boolean haventBeen2() {
    if (haventBeen2) {
      haventBeen2 = false;
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
  public void setRevealed(boolean tempRevealed){
    areaRevealed = tempRevealed;
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
  public void setComplete(){
    cutsceneComplete = true;
  }
  public boolean getComplete(){
    return cutsceneComplete;
  }
}