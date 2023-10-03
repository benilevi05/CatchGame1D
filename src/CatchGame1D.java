import java.util.Random;
import java.util.Scanner;
import java.lang.Math;

/**
 * A simple game in java which uses an x value to control the position of objects and logs it into an array.
 * The player and enemy are both classes which extend Movables class. Once they collide, the game ends.
 * Currently, it has a mathematical limit of 7-8 moves before being forced a defeat.
 * Will work on random power ups to keep the player alive for more than this amount.
 * Will add more enemies and a better logic system to complement that.
 * This is my first simple project in java.
 *
 * @author benilevi
 */
public class CatchGame1D {
    Player player = new Player();
    Enemy enemy = new Enemy();
    Scanner sc = new Scanner(System.in);
    Random random = new Random();
    final static int TILE_AMOUNT = 7; //has to be odd
    final static int ADDITION_AMOUNT = TILE_AMOUNT / 2;
    int[] gameTile = new int[TILE_AMOUNT]; //0 = empty 1 = player 2 = enemy
    int[][] records = new int[100][TILE_AMOUNT];
    int recordsIndex = 0;

    void initialize(){
        int r1 = random.nextInt(1, 3);
        player.setEnergy(4);
        enemy.setEnergy(7);
        player.setTileAmount(TILE_AMOUNT);
        enemy.setTileAmount(TILE_AMOUNT);
        enemy.setX(r1);
        gameTile[ADDITION_AMOUNT] = 1;
        gameTile[ADDITION_AMOUNT + r1] = 2;
    }

    boolean playerTurn(){
        boolean turnComplete = false;
        gameTile[player.x + ADDITION_AMOUNT] = 0;
        System.out.println("Press m to move or press j to jump.");
        String input1 = sc.next();
        System.out.println("Press a to go left or d to go right.");
        String input2 = sc.next();
        if (input1.equals("m")){
            if(input2.equals("a")){
               if(player.moveLeft()){
                   turnComplete = true;
               }
            } else {
                if(player.moveRight()){
                    turnComplete = true;
                }
            }
        } else if (input1.equals("j")){
            if(input2.equals("a")){
                if(player.jumpLeft()){
                    turnComplete = true;
                }
            } else {
                if(player.jumpRight()){
                    turnComplete = true;
                }
            }
        } else {
            System.out.println("Please press a valid value.");
        }
        if (turnComplete){
            gameTile[player.x + ADDITION_AMOUNT] = 1;
            System.out.println("You moved to " + player.x);
            player.energy += 1;
            System.out.println("You have " + player.energy + " energy remaining.");
        }
        return turnComplete;
    }
    void enemyTurn() {
        gameTile[enemy.x + ADDITION_AMOUNT] = 0;
        int r = random.nextInt(0, enemy.energy); //More the energy, more the jump chance
        if (enemy.x < player.x) { //Change player.x if AI too good.
            if (r > 2) {
                enemy.jumpRight();
                System.out.println("The enemy jumps!");
            } else {
                enemy.moveRight();
            }
        } else {
            if (r > 2) {
                enemy.jumpLeft();
                System.out.println("The enemy jumps!");
            } else {
                enemy.moveLeft();
            }
        }
        gameTile[enemy.x + ADDITION_AMOUNT] = 2;
        enemy.energy += 1;
        int distance = Math.abs((player.x - enemy.x));
        System.out.println("The enemy is " + distance + " away from you.");
    }
    void setRecords(){
        int tileIndex = 0;
        for(int j: gameTile){
            records[recordsIndex][tileIndex] = j;
            tileIndex++;
        }
        recordsIndex++;
    }
    void printRecords(){
        int loopedAmount = 0;
            for(int[] i: records){
                if(loopedAmount < recordsIndex){
                    for(int j: i){
                        if(j == 0){
                            System.out.print("-");
                        } else if(j == 1){
                            System.out.print("*");
                        } else if(j == 2) {
                            System.out.print("!");
                        }
                    }
                    System.out.println();
                }
                loopedAmount++;
            }
    }

    void run(){
         initialize();
        setRecords();
         while (true){
             if(playerTurn()){
                 if(player.x == enemy.x){
                     break;
                 }
                 enemyTurn();
                 if(player.x == enemy.x){
                     break;
                 }
                 setRecords();
             }
         }
        setRecords();
         printRecords();
        System.out.println("You were able to move " + player.moved + " amount of time.");
    }
    public static void main(String[] args) {
        new CatchGame1D().run();
    }
}
