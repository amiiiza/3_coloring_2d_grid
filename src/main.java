
import java.util.Scanner;
public class main {
    public static int log2(int N) {
        return (int) (Math.log(N) / Math.log(2));
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the value n:");
        int n = Integer.parseInt(sc.nextLine());
        int range = log2(n);
        Board board = new Board(n , range);
        int row, col;
        while(true){
            System.out.println("Enter number of row:");
            row = Integer.parseInt(sc.nextLine());
            System.out.println("Enter number of column:");
            col = Integer.parseInt(sc.nextLine());
            board.play(row, col);
            board.printBoard();
        }
    }
}
