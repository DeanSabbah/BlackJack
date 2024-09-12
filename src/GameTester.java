import java.util.Scanner;

public class GameTester {
    public static void main(String[] args) {
        int numPlayers = 0;
        int numDecks = 0;
        Scanner scanner = new Scanner(System.in);
        try{    
            System.out.println("Enter the number of players: ");
            numPlayers = scanner.nextInt() + 1;
            System.out.println("Enter the number of decks: ");
            numDecks = scanner.nextInt();
        } catch (Exception e) {
        System.out.println(e.toString());
        }
		
    	new Game(new Hand[numPlayers], new Deck(numDecks), (numPlayers));

        scanner.close();
    }
    
}
