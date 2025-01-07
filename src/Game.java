import java.util.Scanner;

public class Game {
    private Hand[] players;
    private Deck deck;
    private int numPlayers;
	private int turn = 1;
	private int numBusted = 0;
	private boolean end = false;
    public Game(Hand[] players, Deck deck, int numPlayers){
        this.players = players;
        this.deck = deck;
        this.numPlayers = numPlayers;
        deck.shuffle();

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
				players[i].cards[1].setValue(1);
			}
        }

		// game starts
		turn();

		end = endGame();
		
		return; 
    }
    
	/**
	 * Hit function for the player,
	 * Adds a card to the player's hand,
	 * Checks if the player has busted.
	 * If the player has busted, the player is marked as busted.
	 * If the player has an ace and their hand's value is greater than 21, the value of the ace is changed to 1.
	 * @param currPlayer: the current player's index
	 * @return void
	 */
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
					System.out.println(this.players[currPlayer].getName() + "'s cards:" + this.players[currPlayer].cardsToString() + "value: " + this.players[currPlayer].getValue());
					if(this.players[currPlayer].getValue() <= 21){
						break;
					}
				}
			}
			if(this.players[currPlayer].getValue() > 21){
				this.players[currPlayer].setBusted(true);
				this.numBusted++;
				System.out.println(this.players[currPlayer].getName() + " has busted.");
				return;
			}
		}
		else if(this.players[currPlayer].getValue() == 21){
			return;
		}
		return;
	}

	public void turn(){
		// Burn a card at the beginning of each turn
		this.deck.deal();
		int curPlayer = this.turn%this.numPlayers;
		System.out.println("---------------------------------------------------------------------------------");
		if(this.numBusted == this.numPlayers - 1){
			System.out.println("All players have busted. Dealer wins.");
			this.players[0].revealCards();
			System.out.println("Dealers's cards: " + this.players[0].cardsToString() + "value: " + this.players[0].getValue() + "\n");
			return;
		}
		else if(curPlayer == 0){
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
			while(!input.toLowerCase().equals("hit") && !input.toLowerCase().equals("pass")){
				System.out.println("Invalid input. Please enter 'hit' or 'pass'.");
				try {
					input = scanner.nextLine();
				} 
				catch (Exception e) {
					e.printStackTrace();
				}
			}
			while (input.toLowerCase().equals("hit")){
				hit(curPlayer);
				if(this.players[curPlayer].getBusted()){
					break;
				}
				else if(this.players[curPlayer].getValue() == 21){
					System.out.println("Max value reached.");
					break;
				}
				System.out.println("Would you like to hit or pass?");
				try {
					input = scanner.nextLine().toLowerCase();
				} 
				catch (Exception e) {
					e.printStackTrace();
				}
				while(!input.equals("hit") && !input.equals("pass")){
					System.out.println("Invalid input. Please enter 'hit' or 'pass'.");
					try {
						input = scanner.nextLine().toLowerCase();
					} 
					catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
			changeTurn();
		}
		return;
	}

    public void changeTurn(){
		this.turn++;
		turn();
	}

    public boolean endGame(){
		Hand winner = this.players[0];
		for(int i = 1; i < this.numPlayers; i++){
			if(this.players[i].getBusted()){
				if(i < this.numPlayers - 1){
					winner = this.players[i + 1];
				}
				else if(i == 0){
					winner = this.players[1];
				}
				continue;
			}
			if(this.players[i].getValue() > winner.getValue()){
				winner = this.players[i];
			}
			else if(this.players[i].getValue() == winner.getValue()){
				System.out.println("The game is pushed.");
				return true;
			}
		}
		System.out.println(winner.getName() + " has won the game.");
		return false;
	}

    public void printPlayers(){
        for (int i = 0; i < this.numPlayers; i++) {
            System.out.println(this.players[i].getName());
        }
    }
	
	public boolean getEnd(){
		return end;
	}
}
