public class Chip {
    private int count = 0;
    private final int value;

    // Constructors
    Chip(int value){
        this.value = value;
    }

    Chip(int count, int value){
        this.count = count;
        this.value = value;
    }
    
    // Functions
    public void increaseCount(){
        count++;
    }

    public void increaseCount(int amount){
        count += amount;
    }
    // Getters
    public int getValue() {
        return value;
    }

    public int getCount() {
        return count;
    }

    public int getTotalValue(){
        return count * value;
    }
}