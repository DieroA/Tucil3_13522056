package src.structs;

public class WordIntPair {

    /* 
     * Attributes
    */ 

    private Word first;
    private int second;

    /* 
     * Methods
    */

    // Ctor
    public WordIntPair(Word first, int second) {
        this.first = first;
        this.second = second;
    }

    // Getter
    public Word getFirst() {
        return this.first;
    }

    public int getSecond() {
        return this.second;
    }

    // Setter
    public void setFirst(String val) {
        this.first = new Word(val);
    }

    public void setSecond(int val) {
        this.second = val;
    }
}