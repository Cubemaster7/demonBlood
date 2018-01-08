public class Weapon {
  private String name;
  private String location;
  private String description;
  private double damageMod;
  private int level;
  private int requirements;
  private String upgrades;
  private int cost;
  private int sell;
  private boolean have;
  private int id;
  private boolean testShopHas;
  public Weapon(String name, String location, String description, double damageMod, int level, int requirements, String upgrades, int cost, int sell, boolean have, int id, boolean testShopHas){
    this.name = name;
    this.location = location;
    this.description = description;
    this.damageMod = damageMod;
    this.level = level;
    this.requirements = requirements;
    this.upgrades = upgrades;
    this.cost = cost;
    this.sell = sell;
    this.have = have;
    this.id = id;
    this.testShopHas = testShopHas;
  }
  public Weapon(){
    this.name = "";
    this.location = "Unknown";
    this.description = "";
    this.damageMod = 1;
    this.level = 0;
    this.requirements = 0;
    this.upgrades = "0";
    this.cost = 0;
    this.sell = 0;
    this.have = false;
    this.id = -1;
    this.testShopHas = false;
  }
  public void setName(String tempName){
    name = tempName;
  }
  public String getName(){
    return name;
  }
  public void setLocation(String tempLocation){
    location = tempLocation;
  }
  public String getLocation(){
    return location;
  }
  public void setDesc(String tempDesc){
    description = tempDesc;
  }
  public String getDesc(){
    return description;
  }
  public void setMod(double tempMod){
    damageMod = tempMod;
  }
  public double getMod(){
    return damageMod;
  }
  public void setLevel(int tempLevel){
    level = tempLevel;
  }
  public int getLevel(){
    return level;
  }
  public void setReq(int tempReq){
    requirements = tempReq;
  }
  public int getReq(){
    return requirements;
  }
  public void setUpgrades(String tempUpgrades){
    upgrades = tempUpgrades;
  }
  public String getUpgrades(){
    return upgrades;
  }
  public void setCost(int tempCost){
    cost = tempCost;
  }
  public int getCost(){
    return cost;
  }
  public void setSell(int tempSell){
    sell = tempSell;
  }
  public int getSell(){
    return sell;
  }
  public void setHave(boolean tempHave){
    have = tempHave;
  }
  public boolean getHave(){
    return have;
  }
  public void setId(int tempId){
    id = tempId;
  }
  public int getId(){
    return id;
  }
  public void setTestShop(boolean tempTestShop){
    testShopHas = tempTestShop;
  }
  public boolean getTestShop(){
    return testShopHas;
  }
}
