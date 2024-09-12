import java.util.Scanner;

public class Game {
    private Hand[] players;
    private Deck deck;
    private int numPlayers;
	private int turn = 1;
    public Game(Hand[] players, Deck deck, int numPlayers){
        this.players = players;
        this.deck = deck;
        this.numPlayers = numPlayers;
        deck.shuffle();
        this.players[0] = new Dealer(new Card[11], 0, "Dealer");
        Scanner scanner = new Scanner(System.in);
        try {
            for (int i = 1; i < numPlayers; i++) {
                System.out.println("Enter player " + i + "'s name: ");
                String name = scanner.nextLine();
                addPlayer(this, name, i);
            }
        } catch (Exception e) {
            System.out.println(e.toString());
        }
        this.players[0].addCard(deck.deal(false));
        for (int i = 1; i < numPlayers; i++) {
            players[i].addCard(deck.deal());
        }
        for (int i = 0; i < numPlayers; i++) {
            players[i].addCard(deck.deal());
			if(players[i].getValue() == 21){
				System.out.println(players[i].getName() + " has blackjack!");
				endGame();
				return;
			}
			else if(players[i].getValue() > 21){
				players[i].cards[1].setValue(i);
			}
        }

		// game starts
		turn();
		Hand winner = this.players[0];
		for(int i = 1; i < this.numPlayers; i++){
			if(this.players[i].getValue() > winner.getValue() && !this.players[i].getBusted()){
				winner = this.players[i];
			}
		}
		System.out.println(winner.getName() + " has won the game with a value of " + winner.getValue());
		return;
    }
    
    public void hit(int currPlayer){
		this.players[currPlayer].addCard(deck.deal());
		System.out.println(this.players[currPlayer].getName() + "'s cards:" + this.players[currPlayer].cardsToString() + "value: " + this.players[currPlayer].getValue());

		if(this.players[currPlayer].getValue() > 21){
			for(Card card : this.players[currPlayer].getCards()){
				if(card == null){
					break;
				}
				if(card.isAce() && card.getValue() == 11){
					card.setValue(1);
					break;
				}
			}
			if(this.players[currPlayer].getValue() > 21){
				this.players[currPlayer].setBusted(true);
				System.out.println(this.players[currPlayer].getName() + " has busted.");
				return;
			}
		}
		else if(this.players[currPlayer].getValue() == 21){
			return;
		}
		else{
			turn();
		}
		return;
	}

	public void turn(){
		int curPlayer = this.turn%this.numPlayers;
		System.out.println("-------------------------------------------------------");
		if(curPlayer == 0){
			this.players[0].revealCards();
			System.out.println("Dealers's cards: " + this.players[0].cardsToString() + "value: " + this.players[0].getValue() + "\n");
			while(this.players[0].getValue() < 17){
				hit(0);
			}
			return;
		}
		else{
			System.out.println("Dealers's cards: " + this.players[0].cardsToString() + "value: " + this.players[0].getValue() + "\n");
			System.out.println(this.players[curPlayer].getName() + "'s cards: " + this.players[curPlayer].cardsToString() + "value: " + this.players[curPlayer].getValue());

			System.out.println("Would you like to hit or pass?");
			Scanner scanner = new Scanner(System.in);
			String input = "";
			try {
				input = scanner.nextLine();
			} 
			catch (Exception e) {
				e.printStackTrace();
			}
			
			if (input.toLowerCase().equals("hit")) {
				hit(curPlayer);
			}
			else if(!input.toLowerCase().equals("pass")) {
				System.out.println("Invalid input. Please enter 'hit' or 'pass'.");
				turn();
				return;
			}
			changeTurn();
		}
		return;
	}

    public void changeTurn(){
		this.turn++;
		turn();
	}

    public void endGame(){
		System.out.println("Game is done.");
		return;
	}

    public void addPlayer(Game game, String name, int index){
        while(name.equals("Dealer")){
            System.out.println("Invalid name. Please enter a different name.");
            Scanner scanner = new Scanner(System.in);
            try {
                name = scanner.nextLine();
            } catch (Exception e) {
                System.out.println("Invalid input.");
            }
        }
        game.players[index] = new Hand(new Card[11], 0, name);
    }

    public void printPlayers(){
        for (int i = 0; i < this.numPlayers; i++) {
            System.out.println(this.players[i].getName());
        }
    }        
}
