package src;

import src.structs.*;

public class UCS extends Graf {

    /* 
     * Methods
    */

    // Ctor
    public UCS(String startWord, String endWord) {
        super(startWord, endWord);

        pq.add(new WordIntPair(this.currentWord, 0));
    }
    
    public int calculateG(WordIntPair prev) {
        // Mengembalikan nilai g(n), yaitu
        // jumlah langkah yang sudah diambil
        return (prev.getSecond() + 1);
    }

    public int calculateH(Word word) {
        // Mengembalikan nilai h(n)
        // Karena dalam UCS f(n) = g(n), h(n) = 0
        return 0;
    }

    // Output
    public int calcF(int g, String str) {
        // Hitung "f(n)" untuk print
        return (g);
    }

    public String getName() {
        // Mengembalikan nama algoritma untuk print
        return "Uniform Cost Search (UCS)";
    }
}
