
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
        Board board = new Board(n, range);
        while (true){
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            int row = (int) (Math.random() * n);
            int col = (int) (Math.random() * n);
            Node node = board.getRandomNode();
            board.play(node.getRow(),node.getCol());
        }
    }
}
