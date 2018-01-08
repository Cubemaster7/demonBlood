import java.util.*;
public class basicLocation {
  public static void main(String[] args){
    Scanner input = new Scanner(System.in);
    Location forest = new Location();
    forest.setName("Forest");
    forest.setDesc("A large copse of tall trees. The expansive branch systems cast dappled shadows on the paths below.");
    forest.setDiff(0);
    forest.setRec(1);
    forest.setMany(3);
    String[] enemyTypes = new String[forest.getMany()];
    enemyTypes[0] = "Skeleton";
    enemyTypes[1] = "Guard";
    enemyTypes[2] = "Generic enemy 3";
    forest.setTypes(enemyTypes);
    System.out.println("Location: " + forest.getName());
    System.out.println("Description: " + forest.getDesc());
    System.out.println("The difficulty is " + forest.getDiff() + " which means you should be at least level " + forest.getRec() + " to enter.");
    System.out.println("There are " + forest.getMany() + " different enemy types in the " + forest.getName() + ":");
    for(int i = 0; i < enemyTypes.length; i ++){
      System.out.println(forest.getTypes(i));
    }
    Weapon flameSword = new Weapon();
    flameSword.setName("FlameSword");
    flameSword.setDesc("A sword with a firey twist");
    flameSword.setLocation(forest.getName());
    flameSword.setMod(1.3);
    flameSword.setLevel(0);
    flameSword.setReq(1);
    flameSword.setUpgrades("no");
    System.out.println("You found the " + flameSword.getName() + " in the " + flameSword.getLocation() + ".");
    System.out.println(flameSword.getDesc());
    System.out.println("It will modify your attack stat by " + flameSword.getMod() + ", with " + flameSword.getUpgrades() + " upgrades.");
    System.out.println("In order to use it, you must be level " + flameSword.getReq() + " as the sword is level " + flameSword.getLevel() + ".");
    System.out.println("You did " + (10*flameSword.getMod()) + " damage!");
    
  }
}