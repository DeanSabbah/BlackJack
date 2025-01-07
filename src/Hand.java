public abstract class Hand {
    Card[] cards = new Card[11];
    private int numCards = 0;
    protected String name;
	private boolean isBusted = false;

    public void addCard(Card card) {
        cards[numCards] = card;
        numCards++;
    }
    public Card[] getCards() {
        return cards;
    }
	public Card getCards(int i) {
		return cards[i];
	}
    public int getNumCards() {
        return numCards;
    }
    public void resetNumCards(){
        numCards = 0;
    }
    public void revealCards() {
        for (int i = 0; i < numCards; i++) {
            cards[i].flip(true);
        }
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
    public int getPrivateValue() {
        int value = 0;
        for (int i = 0; i < numCards; i++) {
            value += cards[i].getValue();
        }
        return value;
    }
    public int getSuitValue() {
        int value = 0;
        for(int i = 0; i < numCards; i++){
            if(cards[i].isFaceUp()){
                value += cards[i].getSuitValue();
            }
        }
        return value;
    }
    public int getPrivateSuitValue() {
        int value = 0;
        for(int i = 0; i < numCards; i++){
            value += cards[i].getSuitValue();
        }
        return value;
    }
	public void setBusted(boolean bust){
		isBusted = bust;
	}
	public boolean getBusted(){
		return isBusted;
	}
    public String getName() {
        return name;
    }
    public String toString() {
        String s = "";
        for (int i = 0; i < numCards; i++) {
            s += cards[i] + "\n";
        }
        return s;
    }
	public String cardsToString(){
		String s = "";
		for (int i = 0; i < numCards; i++) {
			s += cards[i] + ", ";
		}
		return s;
	}
}