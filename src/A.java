package src;

import src.structs.WordIntPair;
import src.structs.Word;

public class A extends Graf {

    /* 
     * Methods
    */

    // Ctor
    public A(String startWord, String endWord) {
        super(startWord, endWord);

        pq.add(new WordIntPair(this.currentWord, calculateH(currentWord)));
    }
    
    public int calculateG(WordIntPair prev) {
        // Mengembalikan nilai g(n), yaitu
        // jumlah langkah yang sudah diambil
        return (prev.getSecond() + 1);
    }

    public int calculateH(Word word) {
        // Mengembalikan nilai h(n), yaitu
        // jumlah huruf berbeda antara word dengan endWord
        char[] charsWord = word.getStr().toCharArray();
        char[] charsEndWord = endWord.toCharArray();

        int cnt = 0;
        for (int i = 0; i < charsWord.length; i++) {
            if (charsWord[i] != charsEndWord[i])
                cnt++;
        }

        return cnt;
    }

    // Output
    public int calcF(int g, String str) {
        // Hitung "f(n)" untuk print
        return (calculateH(new Word(str)) + g);
    }

    public String getName() {
        // Mengembalikan nama algoritma untuk print
        return "A*";
    }
}
