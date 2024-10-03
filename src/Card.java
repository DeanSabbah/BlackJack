public class Card {
    private int suit;
    private String rank;
    private int value;
    private boolean faceUp = true;
    boolean ace = false;

    public Card(int suit, String rank, int value) {
        this.suit = suit;
        this.rank = rank;
        this.value = value;
    }
    public String getSuit() {
        switch (suit) {
            case 0:
                return "Clubs";
            case 1:
                return "Diamonds";
            case 2:
                return "Hearts";
            case 3:
                return "Spades";
            default:
                return "Invalid Suit";
        }
    }
    public int getSuitValue() {
        return suit;
    }
    public String getRank() {
        return rank;
    }
    public int getValue() {
        return value;
    }
	public void setValue(int value){
		this.value = value;
	}
	public boolean isAce(){
		return ace;
	}
	public void flip(){
		faceUp = !faceUp;
	}
	public void flip(boolean faceUp){
		this.faceUp = faceUp;
	}
	public boolean isFaceUp(){
		return faceUp;
	}
    public String toString() {
        if (faceUp) {
            switch (suit) {
                case 0:
                    return rank + " of Clubs";
                case 1:
                    return rank + " of Diamonds";
                case 2:
                    return rank + " of Hearts";
                case 3:
                    return rank + " of Spades";
                default:
                    return "Invalid Suit";
            }
        }
        else {
            return "Face Down";
        }
    }
}
