
public class Player extends MainClass{

    public static void turn(){
        while (true) {
            System.out.println("Введите координаты X и Y");
            int x = sc.nextInt() - 1;
            int y = sc.nextInt() - 1;
            if (x < 0 || x > 11 || y < 0 || y > 11) {
                System.out.println("Координаты должны быть целыми числами от 1 до 10");
                continue;
            }
            if(gfComputer.checkAgainTurn(x,y)){
                System.out.println("Это место уже было подвержено обстрелу");
                continue;
            }
            char ctemp = gfComputer.strikeThisP(x, y);
            tfComputer.setChar(x, y, ctemp);
            tfComputer.printMe();
            gfPlayer.printMe();
            if (gfComputer.checkAgainTurnX(x,y)) {
             continue;
            }
            break;
        }
    }
}
