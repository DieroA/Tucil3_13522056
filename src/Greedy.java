package src;

import java.util.ArrayList;

import src.structs.WordIntPair;
import src.structs.Word;

public class Greedy extends Graf {

    /* 
     * Methods
    */

    // Ctor
    public Greedy(String startWord, String endWord) {
        super(startWord, endWord);
        
        pq.add(new WordIntPair(this.currentWord, calculateH(this.currentWord)));
    }

    
    public void next() throws NullPointerException {
        // Cari kata berikutnya yang memiliki h(n) terendah
        // tambahkan ke tail priority queue.
        
        if (pq.size() == 0) {
            throw (new NullPointerException());
        }
        
        WordIntPair head = getHead();
        currentWord = head.getFirst();
        
        // Kembali kalau sudah pernah di-ekspan
        if (visited.contains(currentWord.getStr())) 
           return;
        visited.add(currentWord.getStr());
        
        ArrayList<Word> nextWords = head.getFirst().getNextWords();
        WordIntPair min = null;
        for (Word el : nextWords) {
            // Tambahkan kata-kata yang memiliki nilai heuristik terendah
            if (visited.contains(el.getStr())) 
                continue;
            
            WordIntPair currentTuple = new WordIntPair(el, calculateG(head) + calculateH(el));
            if (min == null)
                min = currentTuple;
            else
                if (min.getSecond() > currentTuple.getSecond()) 
                    min = currentTuple;
        }

        if (min != null)
            add(min);
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

    public int calculateG(WordIntPair prev) {
        // Mengembalikan nilai g(n), karena dalam
        // greedy best first search f(n) = h(n),
        // g(n) = 0
        return 0;
    }

    // Output
    public int calcF(int g, String str) {
        // Hitung "f(n)" untuk print
        return (calculateH(new Word(str)));
    }

    public String getName() {
        // Mengembalikan nama algoritma untuk print
        return "Greedy Best First Search";
    }
}
