package src;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.PriorityQueue;

import src.structs.WordIntPair;
import src.structs.Word;

public abstract class Graf {

    /* 
     * Attributes
    */

    protected PriorityQueue<WordIntPair> pq;
    protected HashSet<String> visited;
    protected String startWord;
    protected String endWord;
    protected Word currentWord;

    /* 
     * Methods
    */

    // Ctor
    public Graf(String startWord, String endWord) {
        visited = new HashSet<>();
        pq = new PriorityQueue<>(Comparator.comparing(WordIntPair::getSecond));
        
        this.startWord = startWord.toLowerCase();
        this.endWord = endWord.toLowerCase();
        this.currentWord = new Word(this.startWord);
    }
    
    // Getter
    public WordIntPair getHead() {
        // Mengembalikan elemen pertama dalam priority queue
        // dan menghapus elemen tsb dari priority queue.
        return pq.poll();
    }

    // Lainnya
    public void add(WordIntPair val) {
        // Menambahkan tuple (first, second) ke tail priority queue
        pq.offer(val);
    }

    public void next() throws NullPointerException {
        // Cari semua kata berikutnya yang valid kemudian
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
        for (Word el : nextWords) {
            // Tambahkan kata-kata yang belum pernah di-ekspan ke dalam queue
            if (visited.contains(el.getStr())) 
                continue;

            WordIntPair currentTuple = new WordIntPair(el, calculateG(head) + calculateH(el));
            add(currentTuple);
        }
    }

    public void start() {
        // Mulai pencarian

        while (true) {
            // Berhenti ketika sudah sampai endWord
            if (currentWord.getStr().equals(endWord)) {
                // Print rute
                System.out.println();
                int cnt = 0;
                for (String el: currentWord.getPrev()) {
                    System.out.print(el + " (" + calcF(cnt, el) + ")" + " -> ");
                    cnt++;
                }
                System.out.println(currentWord.getStr() + " (" + calcF(cnt, currentWord.getStr()) + ")");
                // Print jumlah node yang dikunjungi
                System.out.println("Jumlah node yang dikunjungi: " + currentWord.getPrev().size());
                // Print panjang queue untuk laporan
                // System.out.println("Panjang queue: " + pq.size());

                break;
            }
            
            try {
                next();
            } catch (NullPointerException e) {
                System.out.println("\nAlgoritma " + getName() + " tidak menemukan jalan dari " + startWord + " ke " + endWord + ".");
                break;
            }
        }
    }

    public abstract int calculateG(WordIntPair prev);

    public abstract int calculateH(Word word);

    // Output
    public abstract int calcF(int g, String str);

    public abstract String getName();
}
