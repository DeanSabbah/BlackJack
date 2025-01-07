public class Deck {
    private Card[] cards;
    private Card topCard;
	private int numCards;
    private int numDecks;
    public Deck(int numDecks) {
        this.numDecks = numDecks;
		this.cards = new Card[52 * numDecks];
        //String[] suits = {"Clubs", "Diamonds", "Hearts", "Spades"};
        //int[] suits = {0, 1, 2, 3};
        Suit suits[] = {Suit.CLUBS, Suit.DIAMONDS, Suit.HEARTS, Suit.SPADES};
        String[] ranks = {"Ace", "2", "3", "4", "5", "6", "7", "8", "9",
                          "10", "Jack", "Queen", "King"};
        int[] values = {11,2,3,4,5,6,7,8,9,10,10,10,10};
        int i = 0;
        for(int k = 0; k < numDecks; k++){
			for (int l = 0; l < 4; l++) {
				for (int j = 0; j < ranks.length; j++) {
					cards[i] = new Card(suits[l], ranks[j], values[j]);
					if (cards[i].getRank().equals("Ace")){
						cards[i].ace = true;
					}
					i++;
				}
			}
		}

		this.numCards = i;
		
        topCard = cards[0];
    }
	public Card deal() {
        topCard = cards[0];
        for (int i = 0; i < cards.length - 1; i++) {
            cards[i] = cards[i+1];
        }
        return topCard;
    }
    public Card deal(boolean faceUp) {
        topCard = cards[0];
        topCard.flip(faceUp);
        for (int i = 0; i < cards.length - 1; i++) {
            cards[i] = cards[i+1];
        }
        return topCard;
    }
    public void shuffle() {
        for (int i = 0; i < cards.length; i++) {
            int j = (int)(Math.random() * cards.length);
            Card temp = cards[i];
            cards[i] = cards[j];
            cards[j] = temp;
        }
    }
    public Card[] getCards() {
        return cards;
    }
	public int getNumCards(){
		return numCards;
	}
    public int getNumDecks(){
        return numDecks;
    }
}
