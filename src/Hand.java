public class Hand {
    Card[] cards = new Card[11];
    private int numCards = 0;
    private String name;
	private boolean isBusted = false;
    public Hand(Card[] cards, int numCards, String name){
        this.cards = cards;
        this.numCards = numCards;
        this.name = name;
    }
    public void addCard(Card card) {
        cards[numCards] = card;
        numCards++;
    }
    public int getNumCards() {
        return numCards;
    }
    public Card[] getCards() {
        return cards;
    }
	public String cardsToString(){
		String s = "";
		for (int i = 0; i < numCards; i++) {
			s += cards[i] + ", ";
		}
		return s;
	}
	public Card getCard(int i) {
		return cards[i];
	}
    public int getValue() {
        int value = 0;
        for (int i = 0; i < numCards; i++) {
            if (cards[i].isFaceUp()){
                value += cards[i].getValue();
            }
        }
        return value;
    }
    public String toString() {
        String s = "";
        for (int i = 0; i < numCards; i++) {
            s += cards[i] + "\n";
        }
        return s;
    }
    public void revealCards() {
        for (int i = 0; i < numCards; i++) {
            cards[i].flip(true);
        }
    }
    public int getPrivateValue() {
        int value = 0;
        for (int i = 0; i < numCards; i++) {
            value += cards[i].getValue();
        }
        return value;
    }
    public String getName() {
        return name;
    }
	public void setBusted(boolean isBusted){
		this.isBusted = isBusted;
	}
	public boolean getBusted(){
		return isBusted;
	}
    public void resetNumCards(){
        numCards = 0;
    }
}