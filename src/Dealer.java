public class Dealer extends Hand {
    public Dealer(Card[] cards, int numCards, String name) {
        super(cards, numCards, name);
    }
    /* public void revealCards() {
        for (int i = 0; i < getNumCards(); i++) {
            getCard(i).faceUp = true;
        }
    } */
    public int getPrivateValue() {
        int value = 0;
        for (int i = 0; i < getNumCards(); i++) {
            value += getCard(i).getValue();
        }
        return value;
    }
    
}
