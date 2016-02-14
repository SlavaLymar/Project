import java.util.Random;

public class GameField extends MainClass{

    public static final int SIZE = 10;
    public static String result;

    private char[][] field2;
    private int[][] shipNum;
    private Random rand = new Random();


    public GameField() {
        field2 = new char[SIZE][SIZE];
        shipNum = new int[SIZE][SIZE];
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                field2[i][j] = '~';
            }
        }
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                shipNum[i][j] = '*';
            }
        }
        initShips();
    }

    public char strikeThisP(int x, int y) {
        if(field2[y][x] == '~') {
            System.out.println("Промах");
            field2[y][x] = 'o';
            return 'o';
        }

        result = "";
        if(field2[y][x] == '#') {
            field2[y][x] = 'X';
            int w = shipNum[y][x];
            shipNum[y][x] = 0;
            for (int i = 0; i < SIZE; i++) {
                for (int j = 0; j < SIZE; j++) {
                    if(shipNum[i][j] == w) {
                        result = "ранен";
                        }
                }
            }
            if(result == "") {
                result = "убит";

                checkWhenKillgfP();
            }

            System.out.println("Корабль " + result);
            }
        return 'X';
    }

    public char strikeThisC(int x, int y) {
        if(field2[y][x] == '~') {
            System.out.println("Промах");
            field2[y][x] = 'o';
            return 'o';
        }

        result = "";
        if(field2[y][x] == '#') {
            field2[y][x] = 'X';
            int w = shipNum[y][x];
            shipNum[y][x] = 0;
            for (int i = 0; i < SIZE; i++) {
                for (int j = 0; j < SIZE; j++) {
                    if(shipNum[i][j] == w) {
                        result = "ранен";
                    }
                }
            }
            if(result == "") {
                result = "убит";

                checkWhenKillgfC();
            }

            System.out.println("Корабль " + result);
        }
        return 'X';
    }

    public boolean isAllDead() {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if(field2[i][j] == '#') return false;
            }
        }
        return true;
    }

    public void printMe() {
        for (int i = 0; i < SIZE + 1; i++) {
            System.out.printf("%3d", i);
        }
        System.out.print('\n');
        for (int i = 0; i < SIZE; i++) {
            System.out.printf("%3d", i + 1);
            for (int j = 0; j < SIZE; j++) {
                System.out.printf("%3c", field2[i][j]);
            }
            System.out.print('\n');
        }
    }

    public void initShips() {
        int[] shipLength = { 4, 3, 3, 2, 2, 2, 1, 1, 1, 1 };
        for (int i = 0; i < shipLength.length; i++) {
            int ax = 0, ay = 0;
            do {
                ax = rand.nextInt(2);
                ay = rand.nextInt(2);
            } while(Math.abs(ax) + Math.abs(ay) != 1);
            while(!setOneShip(rand.nextInt(SIZE), rand.nextInt(SIZE), ax, ay, shipLength[i], i + 1));
        }
    }

    public boolean setOneShip(int x, int y, int vx, int vy, int l, int _shipNum) {
        int x1 = x;
        int y1 = y;
        int x2 = x + vx * (l - 1);
        int y2 = y + vy * (l - 1);

        if (x < 0 || y < 0 || x2 > SIZE - 1 || y2 > SIZE - 1) return false;
        for (int i = y1 - 1; i <= y2 + 1; i++) {
            for (int j = x1 - 1; j <= x2 + 1; j++) {
                if(i>-1 && j > -1 && i < SIZE && j < SIZE && field2[i][j] != '~') return false;
            }
        }

        for (int i = 0; i < l; i++) {
            field2[y + vy * i][x + vx * i] = '#';
            shipNum[y + vy * i][x + vx * i] = _shipNum;
        }
        return true;
    }

    public boolean checkAgainTurnX(int _x, int _y){
        if (field2[_y][_x] == 'X'){
            return true;
        }
        return false;
        }

    public boolean checkAgainTurn(int _x, int _y){
        if (field2[_y][_x] == 'o' || field2[_y][_x] == 'X'){
            return true;
        }
        return false;
    }

    public void checkWhenKillgfP(){
        for (int i = 1; i < SIZE - 1; i++) {
            for (int j = 1; j < SIZE - 1; j++) {
                if (shipNum[i][j] == 0) {
                    for (int k = i - 1; k <= i + 1; k++) {
                        for (int l = j - 1; l <= j + 1; l++) {
                            if (field2[k][l] == 'o' || field2[k][l] == '~') {
                                field2[k][l] = 'o';
                                tfComputer.checkWhenKilltfP(k,l);
                            }
                        }
                    }
                }
            }
        }
        for (int i = 1; i < SIZE - 1; i++) {
            if (shipNum[0][i] == 0) {
                for (int k = 0; k <= 1; k++) {
                    for (int l = i - 1; l <= i + 1; l++) {
                        if (field2[k][l] == 'o' || field2[k][l] == '~') {
                            field2[k][l] = 'o';
                            tfComputer.checkWhenKilltfP(k,l);
                        }
                    }
                }
            }
        }
        for (int i = 1; i < SIZE - 1; i++) {
            if (shipNum[9][i] == 0) {
                for (int k = 9; k >= 8; k--) {
                    for (int l = i - 1; l <= i + 1; l++) {
                        if (field2[k][l] == 'o' || field2[k][l] == '~') {
                            field2[k][l] = 'o';
                            tfComputer.checkWhenKilltfP(k,l);
                        }
                    }
                }
            }
        }
        for (int i = 1; i < SIZE - 1; i++) {
            if (shipNum[i][0] == 0) {
                for (int k = i - 1; k <= i + 1; k++) {
                    for (int l = 0; l <= 1; l++) {
                        if (field2[k][l] == 'o' || field2[k][l] == '~') {
                            field2[k][l] = 'o';
                            tfComputer.checkWhenKilltfP(k,l);
                        }
                    }
                }
            }
        }
        for (int i = 1; i < SIZE - 1; i++) {
            if (shipNum[i][9] == 0) {
                for (int k = i - 1; k <= i + 1; k++) {
                    for (int l = 8; l <= 9; l++) {
                        if (field2[k][l] == 'o' || field2[k][l] == '~') {
                            field2[k][l] = 'o';
                            tfComputer.checkWhenKilltfP(k,l);
                        }
                    }
                }
            }
        }
        if (shipNum[0][0] == 0) {
            for (int i = 0; i <= 1; i++) {
                for (int j = 0; j <= 1; j++) {
                    if (field2[i][j] == 'o' || field2[i][j] == '~') {
                        field2[i][j] = 'o';
                        tfComputer.checkWhenKilltfP(i,j);
                    }
                }
            }
        }
        if (shipNum[0][9] == 0) {
            for (int i = 0; i <= 1; i++) {
                for (int j = 8; j <= 9; j++) {
                    if (field2[i][j] == 'o' || field2[i][j] == '~') {
                        field2[i][j] = 'o';
                        tfComputer.checkWhenKilltfP(i,j);
                    }
                }
            }
        }
        if (shipNum[9][0] == 0) {
            for (int i = 8; i <= 9; i++) {
                for (int j = 0; j <= 1; j++) {
                    if (field2[i][j] == 'o' || field2[i][j] == '~') {
                        field2[i][j] = 'o';
                        tfComputer.checkWhenKilltfP(i,j);
                    }
                }
            }
        }
        if (shipNum[9][9] == 0) {
            for (int i = 8; i <= 9; i++) {
                for (int j = 8; j <= 9; j++) {
                    if (field2[i][j] == 'o' || field2[i][j] == '~') {
                        field2[i][j] = 'o';
                        tfComputer.checkWhenKilltfP(i,j);
                    }
                }
            }
        }
    }

    public void checkWhenKillgfC(){
        for (int i = 1; i < SIZE - 1; i++) {
            for (int j = 1; j < SIZE - 1; j++) {
                if (shipNum[i][j] == 0) {
                    for (int k = i - 1; k <= i + 1; k++) {
                        for (int l = j - 1; l <= j + 1; l++) {
                            if (field2[k][l] == 'o' || field2[k][l] == '~') {
                                field2[k][l] = 'o';
                                tfPlayer.checkWhenKilltfP(k,l);
                            }
                        }
                    }
                }
            }
        }
        for (int i = 1; i < SIZE - 1; i++) {
            if (shipNum[0][i] == 0) {
                for (int k = 0; k <= 1; k++) {
                    for (int l = i - 1; l <= i + 1; l++) {
                        if (field2[k][l] == 'o' || field2[k][l] == '~') {
                            field2[k][l] = 'o';
                            tfPlayer.checkWhenKilltfP(k,l);
                        }
                    }
                }
            }
        }
        for (int i = 1; i < SIZE - 1; i++) {
            if (shipNum[9][i] == 0) {
                for (int k = 9; k >= 8; k--) {
                    for (int l = i - 1; l <= i + 1; l++) {
                        if (field2[k][l] == 'o' || field2[k][l] == '~') {
                            field2[k][l] = 'o';
                            tfPlayer.checkWhenKilltfP(k,l);
                        }
                    }
                }
            }
        }
        for (int i = 1; i < SIZE - 1; i++) {
            if (shipNum[i][0] == 0) {
                for (int k = i - 1; k <= i + 1; k++) {
                    for (int l = 0; l <= 1; l++) {
                        if (field2[k][l] == 'o' || field2[k][l] == '~') {
                            field2[k][l] = 'o';
                            tfPlayer.checkWhenKilltfP(k,l);
                        }
                    }
                }
            }
        }
        for (int i = 1; i < SIZE - 1; i++) {
            if (shipNum[i][9] == 0) {
                for (int k = i - 1; k <= i + 1; k++) {
                    for (int l = 8; l <= 9; l++) {
                        if (field2[k][l] == 'o' || field2[k][l] == '~') {
                            field2[k][l] = 'o';
                            tfPlayer.checkWhenKilltfP(k,l);
                        }
                    }
                }
            }
        }
        if (shipNum[0][0] == 0) {
            for (int i = 0; i <= 1; i++) {
                for (int j = 0; j <= 1; j++) {
                    if (field2[i][j] == 'o' || field2[i][j] == '~') {
                        field2[i][j] = 'o';
                        tfPlayer.checkWhenKilltfP(i,j);
                    }
                }
            }
        }
        if (shipNum[0][9] == 0) {
            for (int i = 0; i <= 1; i++) {
                for (int j = 8; j <= 9; j++) {
                    if (field2[i][j] == 'o' || field2[i][j] == '~') {
                        field2[i][j] = 'o';
                        tfPlayer.checkWhenKilltfP(i,j);
                    }
                }
            }
        }
        if (shipNum[9][0] == 0) {
            for (int i = 8; i <= 9; i++) {
                for (int j = 0; j <= 1; j++) {
                    if (field2[i][j] == 'o' || field2[i][j] == '~') {
                        field2[i][j] = 'o';
                        tfPlayer.checkWhenKilltfP(i,j);
                    }
                }
            }
        }
        if (shipNum[9][9] == 0) {
            for (int i = 8; i <= 9; i++) {
                for (int j = 8; j <= 9; j++) {
                    if (field2[i][j] == 'o' || field2[i][j] == '~') {
                        field2[i][j] = 'o';
                        tfPlayer.checkWhenKilltfP(i,j);
                    }
                }
            }
        }
    }
}

