public class Dealer extends Hand {
    public Dealer() {
        this.name = "Dealer";
    }
    public void revealCards() {
        for (int i = 0; i < getNumCards(); i++) {
            getCards(i).setFaceUp(true);
        }
    }
    public int getPrivateValue() {
        int value = 0;
        for (int i = 0; i < getNumCards(); i++) {
            value += getCards(i).getValue();
        }
        return value;
    }
    
}
