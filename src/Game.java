import java.util.NoSuchElementException;
import java.util.Scanner;

import defs.Defs;

public class Game {
    private Hand[] players;
    private Deck deck;
    private int numPlayers;
	private int turn = 1;
	private int numBusted = 0;
	private Scanner scanner;
	private boolean end = false;
	private Chip[] pot;
    public Game(Hand[] players, Deck deck, int numPlayers, Scanner scanner){
        this.players = players;
        this.deck = deck;
        this.numPlayers = numPlayers;
		this.scanner = scanner;
        deck.shuffle();
		pot = new Chip[Defs.values.length];

		for(int i = 0; i < pot.length; i++){
			pot[i] = new Chip(Defs.values[i]);
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
				players[i].cards[1].setValue(1);
			}
        }

		// game starts
		bettingTurn();

		end = endGame();
		
		return; 
    }
    
	/**
	 * Hit function for the player,
	 * Adds a card to the player's hand,
	 * Checks if the player has bust.
	 * If the player has bust, the player is marked as bust.
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
				System.out.println(this.players[currPlayer].getName() + " has bust.");
				return;
			}
		}
		else if(this.players[currPlayer].getValue() == 21){
			return;
		}
		return;
	}

	public void bettingTurn(){
		// Dealer checks will be done before this method is called
		Player curPlayer = (Player) players[turn%numPlayers];

		System.out.println("---------------------------------------------------------------------------------");

		System.out.println("Dealers's cards: " + this.players[0].cardsToString() + "value: " + this.players[0].getValue() + "\n");
		System.out.println(curPlayer.getName() + "'s cards: " + curPlayer.cardsToString() + "value: " + curPlayer.getValue() + "\n");

		System.out.println(curPlayer.getName() + " has $" + curPlayer.getChipsValue());
		System.out.println("\nThese are your available chips:");
		
		String s = "foobar";
		while(true){
			for (int i = 0; i < Defs.values.length; i++) {
				System.out.print((i + 1) + ". ");
				System.out.printf("%-5d", Defs.values[i]);
			}
			System.out.println();
			for (int i = 0; i < Defs.values.length; i++) {
				System.out.print((i + 1) + ". ");
				System.out.printf("%-5d", curPlayer.getChipAmount(i));
			}
			System.out.println();

			int val;
			System.out.println("What chip value would you like to bet? (1 - " + Defs.values.length + ")");
			System.out.println("Press enter to stop betting.");
			s = scanner.nextLine();
			if(s == ""){
				break;
			}
			try{
				val = Integer.parseInt(s) - 1;
				if(val < 0 || val >= pot.length)
					throw new IllegalArgumentException("Please enter an integer that is between 1 and " + pot.length + "."); 
				else if(curPlayer.getChipAmount(val) == 0)
					throw new IllegalArgumentException("No chips of that type.");
			}
			catch (NumberFormatException e) {
				System.out.println("Enter a chip value or press enter to finish betting.");
				continue;
			}
			catch (IllegalArgumentException e){
				System.out.println(e.getMessage());
				continue;
			}
			int amount;
			while(true){
				System.out.println("How many $" + Defs.values[val] + " chips would you like to bet?");
				try{
					amount = scanner.nextInt();
					scanner.nextLine();
				}
				catch(NoSuchElementException e){
					System.out.println("Please enter an integer.");
					continue;
				}
				if(amount > curPlayer.getChipAmount(val)){
					System.out.println("Not enough chips, please choose a smaller number");
					continue;
				}
				else{
					break;
				}
			}
			curPlayer.addChips(val, -amount);
			pot[val].increaseCount(amount);
		}
		changeTurn();
	}

	public void turn(){
		// Burn a card at the beginning of each turn
		this.deck.deal();
		int curPlayer = this.turn%this.numPlayers;
		System.out.println("---------------------------------------------------------------------------------");
		if(this.numBusted == this.numPlayers - 1){
			System.out.println("All players have bust. Dealer wins.");
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
		if(Math.floor((turn*1.0)/numPlayers) >= 1){
			if(turn - numPlayers == 0)
				turn++;
			turn();
		}
		else{
			bettingTurn();
		}
	}

    public boolean endGame(){
		Hand winner = this.players[0];
		for(int i = 1; i < this.numPlayers; i++){
			if(this.players[i].getBusted()){
				continue;
			}
			if(this.players[i].getValue() > winner.getValue() || winner.getBusted()){
				winner = this.players[i];
			}
			else if(this.players[i].getValue() == winner.getValue() && !winner.equals(players[0])){
				System.out.println("The game is pushed.");
				return false;
			}
		}
		System.out.println(winner.getName() + " has won the game.");
		return true;
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
