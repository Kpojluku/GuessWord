import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Game {
    private ArrayList<String> words;
    private String word;
    private StringBuilder sb;
    private String letter;
    private int trial;
    private boolean isWin;
    private PlayersLog player;


    public void Go() {
        player = new PlayersLog();
        word = words.get((int) (Math.random() * (words.size() - 1)));
        sb = new StringBuilder();
        isWin = false;
        trial = 1;
        for (int i = 0; i < word.length(); i++) {
            sb.append("_");
        }
        try (
                BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))
        ) {
            System.out.println("Enter your name: ");
            player.setPlayerName(reader.readLine());
            while (!isWin) {
                System.out.print("Key in one character or your guess word:");
                letter = reader.readLine();
                if (letter.length() == 1) {
                    checkSymbol();
                } else if (letter.length() > 1) {
                    checkWord();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private ArrayList<Integer> indexChar(String str, char ch) {
        ArrayList<Integer> index = new ArrayList<>();
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == ch) index.add(i);
        }
        return index;
    }


    private void checkSymbol() {
        for (Integer index : indexChar(word, letter.charAt(0))) {
            sb.replace(index, index + 1, letter);
        }
        System.out.println("Trial " + trial + ": " + sb);
        System.out.println();
        trial++;
        if (sb.indexOf("_") == -1) {
            trial--;
            win();
        }

    }

    private void checkWord() {
        if (letter.equals(word)) {
            win();
        } else {
            System.out.println("The word is wrong");
            System.out.println("Don't be discouraged, play again.");
            isWin = true;
        }
    }

    public void setWordsFromFile() {
        try (
                FileInputStream fileInputStream = new FileInputStream("src\\resources\\words.txt");
                BufferedReader reader = new BufferedReader(new InputStreamReader(fileInputStream))
        ) {
            words = new ArrayList<>();
            while (reader.ready()) {
                words.add(reader.readLine());
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void win() {
        System.out.println("Congratulation!");
        System.out.println("You got in " + trial + " trials!");
        player.Log(word, trial);
        isWin = true;
    }

    public void setWords(ArrayList<String> words) {
        this.words = words;
    }
}
