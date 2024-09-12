import java.util.Scanner;

public class GameTester {
    public static void main(String[] args) {
        int numPlayers = 0;
        Scanner scanner = new Scanner(System.in);
        try{    
            System.out.println("Enter the number of players: ");
            numPlayers = scanner.nextInt() + 1;
        } catch (Exception e) {
        System.out.println(e.toString());
        }
		
    	new Game(new Hand[numPlayers], new Deck(1), (numPlayers));

        scanner.close();
    }
    
}
