
public class AI extends MainClass {

    public static int z;
    public static int u;

    public static void turn() {
        while (true) {
            System.out.println("Компьютер ходит");
            z = rand.nextInt(10);
            u = rand.nextInt(10);

            if (gfPlayer.checkAgainTurn(z, u)) {
                continue;
            }

            char ctemp1 = gfPlayer.strikeThisC(z, u);
            tfPlayer.setChar(z, u, ctemp1);
            tfComputer.printMe();
            gfPlayer.printMe();
            if (gfPlayer.checkAgainTurnX(z, u)){
                continue;
        }
        break;
        }
    }
}
