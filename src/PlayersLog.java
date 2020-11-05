import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class PlayersLog {
    private String playerName;
    private Map<String, String> playerInfo;


    public void Log(String word, int trial) {
        playerInfo = new HashMap<>();
        read();
        checkName(word, trial);
        write();
    }


    private void read() {
        try (
                FileInputStream fileInputStream = new FileInputStream("src\\resources\\player.txt");
                BufferedReader reader = new BufferedReader(new InputStreamReader(fileInputStream))
        ) {
            while (reader.ready()) {
                playerInfo.put(reader.readLine(), reader.readLine());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void checkName(String word, int trial) {
        if (playerInfo.containsKey(playerName)) {
            playerInfo.replace(playerName, playerInfo.get(playerName) + "word=" + word + " trial=" + trial + ";");
        } else {
            playerInfo.put(playerName, "word=" + word + " trial=" + trial + ";");
        }
    }

    private void write() {
        try (
                FileOutputStream fileOutputStream = new FileOutputStream("src\\resources\\player.txt");
                BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(fileOutputStream))
        ) {
            for (Map.Entry<String, String> pair : playerInfo.entrySet()) {
                writer.write(pair.getKey() + "\r\n");
                writer.write(pair.getValue() + "\r\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }
}