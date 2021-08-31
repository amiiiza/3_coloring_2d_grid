
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
        new Board(n, range);
    }
}
