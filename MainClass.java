import java.util.Random;
import java.util.Scanner;

public class MainClass {

    public static Scanner sc = new Scanner(System.in);
    public static Random rand = new Random();
    public static GameField gfPlayer = new GameField();
    public static TargetField tfPlayer = new TargetField();
    public static GameField gfComputer = new GameField();
    public static TargetField tfComputer = new TargetField();

    public static void main(String[] args) {


        tfComputer.printMe();
        gfPlayer.printMe();

        //Ходит игрок
        while (true) {
            Player.turn();
            if (gfComputer.isAllDead()) {
                System.out.println("Игрок победил");
                break;
            }
            // Ходит компьютер
            AI.turn();
            if (gfPlayer.isAllDead()) {
                System.out.println("Компьютер победил");
                break;
            }
        }
        System.out.println("Спасибо за игру");

    }
}

