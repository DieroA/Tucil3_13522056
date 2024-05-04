import java.io.IOException;
import java.util.Scanner;

import src.*;
import src.structs.Word;

/* 
 * CARA RUN:
 * - javac -d bin *.java
 * - java -cp bin Main
 */

public class Main {
    public static void main(String[] args) {
        System.out.println("~=---------------------------=~");
        System.out.println(
                        "  _______         _ _   ____  \r\n" + //
                        " |__   __|       (_) | |___ \\ \r\n" + //
                        "    | |_   _  ___ _| |   __) |\r\n" + //
                        "    | | | | |/ __| | |  |__ < \r\n" + //
                        "    | | |_| | (__| | |  ___) |\r\n" + //
                        "    |_|\\__,_|\\___|_|_| |____/ ");
        System.out.println("\n Diero Arga Purnama (13522056)");
        System.out.println("~=---------------------------=~\n");

        Scanner input = new Scanner(System.in);
        while (true) {
            // Baca words.txt
            if (Word.validWords.size() == 0) {
                try {
                    Word.readWordFile();
                } catch (IOException e) {
                    System.out.println("Gagal buka words.txt");
                    break;
                }          
            } else {
                System.out.print("Ketik [Y] untuk keluar dari program: ");
                String exit = input.nextLine();
                if (exit.equals("Y")) {
                    System.out.println("Berhasil keluar dari program!");       
                    break;
                }
            }

            // Input
            System.out.print("Kata awal: ");
            String startWord = input.nextLine();
            System.out.print("Kata tujuan: ");
            String endWord = input.nextLine();

            if (startWord.length() != endWord.length()) {
                System.out.println("Kata awal & kata tujuan harus memiliki panjang yang sama!");
                break;
            } else if (!Word.isValidWord(startWord.toLowerCase()) || !Word.isValidWord(endWord.toLowerCase())) {
                System.out.println("Kata awal atau kata tujuan tidak valid.");
                break;
            } else if (startWord.equals(endWord)) {
                System.out.println("Kata awal & kata tujuan harus berbeda.");
                break;
            }

            System.out.println("Algoritma yang ingin digunakan: ");
            System.out.println("[1]. UCS");
            System.out.println("[2]. Greedy Best First Search");
            System.out.println("[3]. A*");
            System.out.print(">> ");

            int algoritma;
            try {
                algoritma = Integer.parseInt(input.nextLine());
                if (algoritma > 3 || algoritma < 1) {
                    System.out.println("Input harus merupakan 1, 2, atau 3.");
                    break;  
                }
            } catch (NumberFormatException e) {
                System.out.println("Input harus merupakan 1, 2, atau 3.");
                break;
            }

            // Algoritma
            long startTime = System.currentTimeMillis();
            
            Graf t;
            switch (algoritma) {
                case 1:
                    t = new UCS(startWord, endWord);
                    break;
                case 2:
                    t = new Greedy(startWord, endWord);
                    break;
                default:
                    t = new A(startWord, endWord);
            }
            t.start();
            
            long endTime = System.currentTimeMillis();
            long runtime = endTime - startTime;
            
            // Output
            System.out.println("Runtime: " + runtime + " ms\n");
        }
        input.close();
    }
}