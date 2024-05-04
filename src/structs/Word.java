package src.structs;

import java.util.Set;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;

public class Word {

    /* 
     * Attributes
    */ 

    public static Set<String> validWords = new HashSet<>();
    private String str;
    private ArrayList<String> prev;

    /* 
     * Methods
    */

    // Ctor
    public Word(String str) {
        this.str = str;
        this.prev = new ArrayList<>();
    }

    // Getter
    public String getStr() {
        return this.str;
    }

    public ArrayList<String> getPrev() {
        return this.prev;
    }

    // Setter
    public void setStr(String val) {
        this.str = val;
    }

    public void setPrev(Word prevWord) {
        this.prev.addAll(prevWord.getPrev());
        this.prev.add(prevWord.getStr());
    }

    // Lain-lain
    public static void readWordFile() throws IOException {
        // Baca words.txt dan simpan dalam validWords

        BufferedReader reader = new BufferedReader(new FileReader("src/words.txt"));

        String s;
        while ((s = reader.readLine()) != null)
            validWords.add(s.toLowerCase());

        reader.close();
    }

    public static boolean isValidWord(String str) {
        // Mengembalikan true jika str merupakan kata 
        // yang valid dalam bahasa inggris
        
        return validWords.contains(str);
    }

    public ArrayList<Word> getNextWords() {
        // Mengembalikan array yang berisi semua kata-kata dalam
        // bahasa inggris valid dan tidak ada dalam prev yang didapatkan 
        // dari mengganti satu huruf dalam str menjadi huruf lain.

        ArrayList<Word> ret = new ArrayList<>();
        char[] chars = str.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            for (char c = 'a'; c != '{'; c++) {
                if (c == chars[i])
                    continue;
                
                char[] tempChars = Arrays.copyOf(chars, chars.length);
                tempChars[i] = c;

                // Masukkan dalam ret jika tempWord tidak ada dalam prev & merupakan kata valid
                Word tempWord = new Word(new String(tempChars));
                if (!getPrev().contains(tempWord.getStr()) && isValidWord(tempWord.getStr())) {
                    tempWord.setPrev(this);
                    ret.add(tempWord);
                }
            }
        }
        return ret;
    }
}