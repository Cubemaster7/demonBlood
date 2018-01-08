import java.util.*;
public class Location{
  private static String name;
  private static String description;
  private static int difficulty;
  private static int recommendedLevel;
  private static int manymies;
  private static String[] enemyTypes;
  public Location(String name, String description, int difficulty, int recommendedLevel, int manymies, String[] enemyTypes){
    this.name = name;
    this.description = description;
    this.difficulty = difficulty;
    this.recommendedLevel = recommendedLevel;
    this.manymies = manymies;
    this.enemyTypes = new String[manymies];
  }
  public Location(){
    this.name = "";
    this.description = "";
    this.difficulty = 1;
    this.recommendedLevel = 1;
    this.manymies = 0;
    this.enemyTypes = new String[this.manymies];
  }
  public static void setName(String tempName){
    name = tempName;
  }
  public static String getName(){
    return name;
  }
  public static void setDesc(String tempDesc){
    description = tempDesc;
  }
  public static String getDesc(){
    return description;
  }
  public static void setDiff(int tempDiff){
    difficulty = tempDiff;
  }
  public static int getDiff(){
    return difficulty;
  }
  public static void setRec(int tempRec){
    recommendedLevel = tempRec;
  }
  public static int getRec(){
    return recommendedLevel;
  }
  public static void setMany(int tempMany){
    manymies = tempMany;
  }
  public static int getMany(){
    return manymies;
  }
  public static void setTypes(String[] tempTypes){
    enemyTypes = tempTypes;
  }
  public static String getTypes(int number){
    return enemyTypes[number];
  }
    
}