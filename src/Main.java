import java.util.Scanner;

public class Main {
    static private int numPlayers;
    static private int numDecks;
    public static void addPlayer(Hand[] players, int index, Scanner scanner){
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
    
    public static void getNumPlayers(Scanner scanner){
        try{
            System.out.println("Enter the number of players: ");
            numPlayers = scanner.nextInt() + 1;
        }
        catch (Exception e) {
            System.out.println(e.toString());
        }
    }

    public static void getNumDecks(Scanner scanner){
        try{
            System.out.println("Enter the number of decks: ");
            numDecks = scanner.nextInt();
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        getNumPlayers(scanner);
        getNumDecks(scanner);
        scanner.nextLine();

        Hand players[] = new Hand[numPlayers];
        
        players[0] = new Dealer();
        
        for (int i = 1; i < numPlayers; i++) {
            addPlayer(players, i, scanner);
        }
        
        Game game = new Game(players, new Deck(numDecks), numPlayers, scanner);
        
        char again = 'y';
        while (again == 'y') {
            if(game.getEnd()){
                System.out.println("Play again? (Yes or No)");
                again = scanner.nextLine().toLowerCase().charAt(0);
                while(!(again == 'y') && !(again == 'n')){
                    System.out.println("Please enter yes or no.");
                    again = scanner.nextLine().toLowerCase().charAt(0);
                }
            }
            if(again == 'y'){
                for(int i = 0; i < numPlayers; i++){
                    players[i].cards = new Card[11];
                    players[i].resetNumCards();
                    players[i].setBusted(false);
                }
            }
            else{
                break;
            }
            game = new Game(players, new Deck(numDecks), (numPlayers), scanner);
        }
        scanner.close();
    }
}
