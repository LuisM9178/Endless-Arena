import java.util.Scanner;
import java.lang.Math;
import java.util.Random;

public class EndlessArena
{
  public static long cap = 10;

  public static Character player = new Character();

  public static int playerDEF;

  public static int playerMDEF;

  public static Enemy enemy = new Enemy();

  public static int enemyHP;

  public static int enemyDEF;

  public static int enemyMDEF;

  public static int actChoice;

  public static int currentHP;

  public static int heal;

  public static int healCount = 10;

  public static Random rand = new Random();

  public static int n = rand.nextInt(2);

  public static int killCount = 0;

  public static void main(String[] args)
  {
    Scanner scan = new Scanner(System.in);
    int enmUp = 0;

    classMenu();
    int menuChoice = getInt();

    enemy = createEnm();

    switch(menuChoice){
      case 1: player = classKnight();
        break;
      case 2: player = classMage();
        break;
      case 3: player = classSwordMage();
        break;
    }
    System.out.println();
    System.out.println("Okay then, onto battle!");

    while(currentHP > 0){
      System.out.println("Enemy has Appeared!");
      System.out.println();

      while(enemyHP > 0 && currentHP > 0){
        System.out.println("Enemy HP: " + enemyHP);
        System.out.println();
        System.out.println("Your turn.");

        player.setDEF(playerDEF);
        player.setMDEF(playerMDEF);

        actMenu();
        actChoice = scan.nextInt();

        switch(actChoice){
          case 1: System.out.println("You Attacked!");
            if(enemy.getDEF() > player.getSTR()){
              enemyHP = enemyHP - 0;
              System.out.println("You delt 0 points of damage!");
            } else{
              enemyHP = enemyHP - (player.getSTR() - enemy.getDEF());
              System.out.println("You delt " + (player.getSTR() - enemy.getDEF()) + " points of damage!");
            }
            System.out.println();
            break;
          case 2: System.out.println("You used a Magic Attack!");
            if(enemy.getMDEF() > player.getMP()){
              enemyHP = enemyHP - 0;
              System.out.println("You delt 0 points of damage!");
            } else{
              enemyHP = enemyHP - (player.getMP() - enemy.getMDEF());
              System.out.println("You delt " + (player.getMP() - enemy.getMDEF()) + " points of damage!");
            }
            System.out.println();
            break;
          case 3: System.out.println("You Healed!");
            currentHP = currentHP + heal;
            System.out.println("You healed " + heal + " HP!");
            while(currentHP > player.getHP()){
              currentHP--;
            }
            healCount--;
            System.out.println();
            break;
          case 4: System.out.println("You Defended!");
            System.out.println("Your Defense and Magic Defense increased until next turn!");
            player.setDEF(player.getDEF() + (player.getDEF() / 2));
            player.setMDEF(player.getMDEF() + (player.getMDEF() / 2));
            System.out.println();
            break;
        }
        System.out.println("Enemy's turn.");
        enemy.setDEF(enemyDEF);
        enemy.setMDEF(enemyMDEF);

        switch(rand.nextInt(3) + 1){
          case 1: System.out.println("The Enemy Attacked!");
            if(player.getDEF() > enemy.getSTR()){
              currentHP = currentHP - 0;
              System.out.println("The Enemy delt 0 points of damage!");
            } else{
              currentHP = currentHP - (enemy.getSTR() - player.getDEF());
              System.out.println("The Enemy delt " + (enemy.getSTR() - player.getDEF()) + " points of damage!");
            }
            System.out.println();
            break;
          case 2: System.out.println("The Enemy used a Magic Attack!");
            if(player.getMDEF() > enemy.getMP()){
              currentHP = currentHP - 0;
              System.out.println("The Enemy delt 0 points of damage!");
            } else{
              currentHP = currentHP - (enemy.getMP() - player.getMDEF());
              System.out.println("The Enemy delt " + (enemy.getMP() - player.getMDEF()) + " points of damage!");
            }
            System.out.println();
            break;
          case 3: System.out.println("The Enemy Defended!");
            System.out.println("The Enemy's Defense and Magic Defense increased until next turn!");
            enemy.setDEF(enemy.getDEF() + (enemy.getDEF() / 2));
            enemy.setMDEF(enemy.getMDEF() + (enemy.getMDEF() / 2));
            System.out.println();
            break;
        }
      }
      player.setEXP(player.getEXP() + enemy.getEXP());
      if(rand.nextInt(100) + 1 == 1){
        player.setEXP(player.getEXP() + 10);
        System.out.println("You recieved extra EXP!");
        System.out.println();
      }
      expCheck(player);
      healCount = 10;

      enmUp++;

      if(enmUp == 5){
        enemyLevelUp(enemy);
        enmUp = 0;
      }
      enemyHP = enemy.getHP();

      killCount++;
      System.out.println("Onto the next Enemy!");
    }
    System.out.println("Game Over");
    System.out.println();
    System.out.println("You defeated: " + killCount + " Enemy(s)");
  }

  public static Enemy createEnm()
  {
    Enemy tmpEnm = new Enemy();
    tmpEnm.setEXP(2);
    tmpEnm.setHP(rand.nextInt(25));
    enemyHP = tmpEnm.getHP();
    tmpEnm.setMP(rand.nextInt(25));
    tmpEnm.setSTR(rand.nextInt(25));
    tmpEnm.setDEF(rand.nextInt(25));
    enemyDEF = tmpEnm.getDEF();
    tmpEnm.setMDEF(rand.nextInt(25));
    enemyMDEF = tmpEnm.getMDEF();
    return tmpEnm;
  }
  public static Enemy enemyLevelUp(Enemy tmpEnm)
  {
    tmpEnm.setEXP(tmpEnm.getEXP() + rand.nextInt(5));
    tmpEnm.setHP(tmpEnm.getHP() + rand.nextInt(5 * (player.getLevel())));
    enemyHP = tmpEnm.getHP();
    tmpEnm.setMP(tmpEnm.getMP() + rand.nextInt(5 * (player.getLevel())));
    tmpEnm.setSTR(tmpEnm.getSTR() + rand.nextInt(5 * (player.getLevel())));
    tmpEnm.setDEF(tmpEnm.getDEF() + rand.nextInt(5 * (player.getLevel())));
    enemyDEF = tmpEnm.getDEF();
    tmpEnm.setMDEF(tmpEnm.getMDEF() + rand.nextInt(5 * (player.getLevel())));
    enemyMDEF = tmpEnm.getMDEF();
    return tmpEnm;
  }

  public static void classMenu()
  {
    System.out.println("1) Knight");
    System.out.println("2) Mage");
    System.out.println("3) Swordmage");
    System.out.println();
    System.out.println("What class will the character be?");
  }

  public static int getInt()
	{
		Scanner scan = new Scanner(System.in);
		return scan.nextInt();
	}

  public static String getString()
	//getString() opening curly bracket
	{
		//Create Scanner object called scan
		Scanner scan = new Scanner(System.in);
		//return nextLine()
		return scan.nextLine();
	//getString() closing curly bracket
	}

  public static Character classMage()
  {
    Character tmpChar = new Character();
    System.out.println("Enter your character's name:");
    tmpChar.setName(getString());
    tmpChar.setLevel(1);
    tmpChar.setEXP(0);
    tmpChar.setHP(30);
    heal = tmpChar.getHP() / 3;
    currentHP = tmpChar.getHP();
    //Note: MP stands for "Magic Power" not "Magic Points"
    tmpChar.setMP(35);
    tmpChar.setSTR(15);
    tmpChar.setDEF(20);
    playerDEF = tmpChar.getDEF();
    tmpChar.setMDEF(30);
    playerMDEF = tmpChar.getMDEF();
    return tmpChar;
  }
  public static Character classKnight()
  {
    Character tmpChar = new Character();
    System.out.println("Enter your character's name:");
    tmpChar.setName(getString());
    tmpChar.setLevel(1);
    tmpChar.setEXP(0);
    tmpChar.setHP(50);
    heal = tmpChar.getHP() / 5;
    currentHP = tmpChar.getHP();
    //Note: MP stands for "Magic Power" not "Magic Points"
    tmpChar.setMP(10);
    tmpChar.setSTR(35);
    tmpChar.setDEF(30);
    playerDEF = tmpChar.getDEF();
    tmpChar.setMDEF(20);
    playerMDEF = tmpChar.getMDEF();
    return tmpChar;
  }
  public static Character classSwordMage()
  {
    Character tmpChar = new Character();
    System.out.println("Enter your character's name:");
    tmpChar.setName(getString());
    tmpChar.setLevel(1);
    tmpChar.setEXP(0);
    tmpChar.setHP(45);
    heal = tmpChar.getHP() / 3;
    currentHP = tmpChar.getHP();
    //Note: MP stands for "Magic Power" not "Magic Points"
    tmpChar.setMP(35);
    tmpChar.setSTR(35);
    tmpChar.setDEF(30);
    playerDEF = tmpChar.getDEF();
    tmpChar.setMDEF(30);
    playerMDEF = tmpChar.getMDEF();
    return tmpChar;
  }

  public static void actMenu()
  {
    System.out.print("[1) Attack] ");
    System.out.print(" [2) Magic] ");
    System.out.print(" [3) Healing Surge] ");
    System.out.print(" [4) Defend]");
    System.out.println();
    System.out.println(player.getName() + " | LVL: " + player.getLevel() + " | EXP: " + player.getEXP() + " | HP: " + currentHP + "/" + player.getHP() + " | Healing Surges Left: " + healCount);
    System.out.println("What will " + player.getName() + " do?");
  }

  public static void expCheck(Character tmpChar)
  {
    if(tmpChar.getEXP() >= cap && tmpChar.getLevel() < 100){
      levelUp(tmpChar);
      cap = cap + (10 * tmpChar.getLevel());
    }
  }

  public static Character levelUp(Character tmpChar)
  {
    System.out.println("You Leved Up!");
    tmpChar.setLevel(tmpChar.getLevel() + 1);
    System.out.println("LVL = " + tmpChar.getLevel());
    tmpChar.setHP(tmpChar.getHP() + rand.nextInt(10) + 1);
    heal = tmpChar.getHP() / 3;
    System.out.println("HP = " + tmpChar.getHP());
    tmpChar.setMP(tmpChar.getMP() + rand.nextInt(10) + 3);
    System.out.println("MP (Magic Power) = " + tmpChar.getMP());
    tmpChar.setSTR(tmpChar.getSTR() + rand.nextInt(10) + 3);
    System.out.println("STR = " + tmpChar.getSTR());
    tmpChar.setDEF(tmpChar.getDEF() + rand.nextInt(10) + 1);
    playerDEF = tmpChar.getDEF();
    System.out.println("DEF = " + tmpChar.getDEF());
    tmpChar.setMDEF(tmpChar.getMDEF() + rand.nextInt(10) + 1);
    System.out.println("MDEF = " + tmpChar.getMDEF());
    playerMDEF = tmpChar.getMDEF();
    return tmpChar;
  }
}
