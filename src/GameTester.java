import java.util.Scanner;

public class GameTester {
    public static void addPlayer(Hand[] players, int index){
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("Enter player " + index + "'s name: ");
                String name = scanner.nextLine();
                while(name.toLowerCase() == "dealer"){
                    System.out.println("Invalid name. Please enter a different name.");
                    try {
                        name = scanner.nextLine();
                    } catch (Exception e) {
                        System.out.println(e.toString());
                    }
                }
        
        players[index] = new Player(name);
    }
    
    public static void main(String[] args) {
        int numPlayers = 0;
        int numDecks = 0;
        Scanner scanner = new Scanner(System.in);
        try{    
            System.out.println("Enter the number of players: ");
            numPlayers = scanner.nextInt() + 1;
            System.out.println("Enter the number of decks: ");
            numDecks = scanner.nextInt();
            scanner.nextLine();
        } catch (Exception e) {
        System.out.println(e.toString());
        }

        Hand players[] = new Hand[numPlayers];
        
        players[0] = new Dealer();
        
        for (int i = 1; i < numPlayers; i++) {
            addPlayer(players, i);
        }
        
        Game game = new Game(players, new Deck(numDecks), numPlayers);
        
        String again = "yes";
        while (again.charAt(0) == 'y') {
            if(!game.getEnd()){
                System.out.println("Play again? (Yes or No)");
                again = scanner.nextLine().toLowerCase();
                while(!(again.charAt(0) == 'y') && !(again.charAt(0) == 'n')){
                    System.out.println("Please enter yes or no.");
                    again = scanner.nextLine().toLowerCase();
                }
            }
            if(again.charAt(0) == 'y'){
                for(int i = 0; i < numPlayers; i++){
                    players[i].cards = new Card[11];
                    players[i].resetNumCards();
                    players[i].setBusted(false);
                }
            }
            else{
                break;
            }
            game = new Game(players, new Deck(numDecks), (numPlayers));
        }
        scanner.close();
    }
}
