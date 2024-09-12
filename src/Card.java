public class Card {
    private String suit;
    private String rank;
    private int value;
    private boolean faceUp = true;
    boolean ace = false;

    public Card(String suit, String rank, int value) {
        this.suit = suit;
        this.rank = rank;
        this.value = value;
    }
    public String getSuit() {
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
            return rank + " of " + suit;
        }
        else {
            return "Face Down";
        }
    }
}
