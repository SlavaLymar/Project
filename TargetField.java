
public class TargetField extends MainClass{

    public static final int SIZE = 10;

    private char[][] field1;

    public TargetField() {
        field1 = new char[SIZE][SIZE];
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                field1[i][j] = '.';
            }
        }
    }

    public void setChar(int x, int y, char _inp) {
        field1[y][x] = _inp;
    }

    public void printMe() {
        for (int i = 0; i < SIZE + 1; i++) {
            System.out.printf("%3d", i);
        }
        System.out.print('\n');
        for (int i = 0; i < SIZE; i++) {
            System.out.printf("%3d", i + 1);
            for (int j = 0; j < SIZE; j++) {
                System.out.printf("%3c", field1[i][j]);
            }
            System.out.print('\n');
        }
    }

    public void checkWhenKilltfP(int _k, int _l) {

                field1[_k][_l] = 'o';
    }
}
