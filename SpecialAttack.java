public class SpecialAttack {
  private boolean isDamage;
  private boolean isBuff;
  private boolean isHeal;
  private boolean isAggro;
  private int damage; //Modifier to multiply normal damage by
  private double buff; //Add this to stat
  private String buffStat = new String(); //Stat to add to
  private int heal; //Add this much to health
  private int cooldown;
  private double aggro; //Multiply aggro by this
  private int currentCooldown; 
  private String name = new String();
  private String description = new String();
  //1=Buff+Damage 2=Heal+Damage 3=Buff+LessAggro 4=Damage+MoreAggro 5=Buff+Damage 6=That's a lot of damage 7=Heal+Buff 8=Heal+Damage 9=Heal  
  public SpecialAttack(int id) {
    switch (id) {
      case 1:
        name = "Shocking Swing";
        description = "Uses the power of electricity to boost agility, and hits the enemy as well.";
        isDamage = true;
        isBuff = true;
        damage = 2;
        buffStat = "agi";
        buff = 1;
        cooldown = 4;                                                                                                                                  
        break;
      case 2:
        name = "Healing Hit";
        description = "Drains energy from the target in order to heal the team.";
        isDamage = true;
        isHeal = true;
        damage = 3;
        heal = 100;
        cooldown = 5;                                                                                                                                  
        break;
      case 3:
        name = "Electrical Charge";
        description = "Charges up Australius to boost his strength and reduces aggro";
        isBuff = true;
        isAggro = true;
        buffStat = "str";
        buff = 2;
        cooldown = 7;
        aggro = 0.5;
        break;
      case 4:
        name = "Skull Taunt";
        description = "Taunts the enemy and whacks them with a mighty swing";
        isDamage = true;
        isAggro = true;
        damage = 2;
        cooldown = 3;
        aggro = 2;
        break;
      case 5:
        name = "Steady Sternum";
        description = "Builds up defense before unleashing an attack";
        isDamage = true;
        isBuff = true;
        damage = 2;
        buffStat = "con";
        buff = 1;
        cooldown = 5;
        break;
      case 6:
        name = "Bone Saw";
        description = "I've got you in here for 3 minutes!";
        isDamage = true;
        damage = 3;
        cooldown = 8;
        break;
      case 7:
        name = "Turning Tide";
        description = "Boosts accuracy and heals the team";
        isHeal = true;
        isBuff = true;
        buffStat = "dex";
        buff = 1;
        heal = 50;
        cooldown = 3;
        break;
      case 8:
        name = "Evaporation Evisceration";
        description = "Violently disembowels the enemy, and also gives the team a nice heal";
        isHeal = true;
        isDamage = true;
        damage = 2;
        heal = 75;
        cooldown = 6;
        break;
      case 9:
        name = "Full Flood";
        description = "Majorly heals the team";
        isHeal = true;
        heal = 150;
        cooldown = 9;
        break;

    }
  }
  public String getName() {
   return name; 
  }
  public String getDescription() {
   return description; 
  }
  public boolean getIsDamage() {
    return isDamage;
  }
  public boolean getIsBuff() {
    return isBuff;
  }
  public boolean getIsHeal() {
    return isHeal;
  }
  public boolean getIsAggro() {
   return isAggro; 
  }
  public int getDamage() {
    return damage;
  }
  public double getBuff() {
    return buff;
  }
  public String getBuffStat() {
    return buffStat;
  }
  public int getHeal() {
    return heal;
  }
  public double getAggro() {
    return aggro;
  }
  public void resetCooldown() {
    currentCooldown = 0;
  }
  public void increment() {
    if (currentCooldown < cooldown) {
    currentCooldown ++;
    }
  }
  public boolean isReady() {
    if (currentCooldown >= cooldown) {
      return true;
    }
    else {
      return false;
    }
  }
}