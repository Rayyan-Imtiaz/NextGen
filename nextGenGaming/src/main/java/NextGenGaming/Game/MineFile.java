package NextGenGaming.Game;

import java.io.*;

public class MineFile {

    public static void store(int seconds) {
        try (OutputStream os = new FileOutputStream("MineFile", true)) {
            os.write(seconds);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void displayHigh() {
        try (InputStream is = new FileInputStream("MineFile")) {
            int data;
            int max = Integer.MIN_VALUE;

            while ((data = is.read()) != -1) {
                if (max < data) {
                    max = data;
                }
            }

            System.out.println("High Score: " + max + "seconds");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
