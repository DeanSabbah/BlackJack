import defs.Defs;

public class Player extends Hand {
    private Chip[] chips;
    public Player(String name) {
        this.name = name;
        this.chips = new Chip[Defs.values.length];

        
        for (int i = 0; i < chips.length; i++) {
            chips[i] = new Chip(Defs.defaults[i], Defs.values[i]); // Assuming Chip has a constructor that takes a value
        }
    }

    public int getChipsValue(){
        int sum = 0;
        for(Chip chip : chips){
            sum += chip.getTotalValue();
        }
        return sum;
    }

    public int getChipAmount(int index){
        return chips[index].getCount();
    }

    public void addChip(int value){
        chips[value].increaseCount();;
    }

    public void addChips(int value, int amount){
        chips[value].increaseCount(amount);
    }
}
