import java.util.Random;

public class AsciiArt {
    static final int WIDTH = getTerminalWidth() - 1;
    static final int ROCKET_WIDTH = 12;
    static final int ROCKET_HEIGHT = 16;
    static final Random rand = new Random();

    public static void main(String[] args) throws InterruptedException {
        char[][] nextRows = new char[ROCKET_HEIGHT][WIDTH]; // store upcoming rows
        for (int i = 0; i < nextRows.length; i++) {
            nextRows[i] = emptyRow();
        }

        while (true) {
            for (int x = 0; x < WIDTH - ROCKET_WIDTH; x=x+6) {
                if (rand.nextDouble() < 0.01) {
                    char[][] img;
                    if (rand.nextDouble() < 0.5)
                        img = getRocket();
                    else {
                        img = getSword();
                    }
                    for (int iy = 0; iy < ROCKET_HEIGHT; iy++) {
                        for (int ix = 0; ix < ROCKET_WIDTH; ix++) {
                            nextRows[iy][x + ix] = img[iy][ix];
                        }
                    }
                }
            }

            // Print and remove the top row
            System.out.println(new String(nextRows[0]));
            // Shift all rows up
            shiftRowsUp(nextRows);
            Thread.sleep(40); // Delay in ms
        }
    }

    static void shiftRowsUp(char[][] nextRows) {
        for (int i = 1; i < nextRows.length; i++) {
            nextRows[i - 1] = nextRows[i];
        }
        nextRows[nextRows.length - 1] = emptyRow();
    }

    static char[] emptyRow() {
        char[] row = new char[WIDTH];
        for (int i = 0; i < WIDTH; i++) {
            row[i] = ' ';
        }
        return row;
    }

    // rename your function here
    static char[][] getRocket() {
        char[][] img = new char[ROCKET_HEIGHT][ROCKET_WIDTH];
        // fill with empty space
        for (int y = 0; y < ROCKET_HEIGHT; y++) {
            for (int x = 0; x < ROCKET_WIDTH; x++) {
                img[y][x] = ' ';
            }
        }
        // then fill individual characters
        img[0][5] = '/';
        img[0][6] = '\\';
        img[1][4] = '/';
        img[1][7] = '\\';
        img[2][3] = '/';
        img[2][8] = '\\';
        img[3][2] = '/';
        img[3][9] = '\\';
        img[4][1] = '|';
        img[4][10] = '|';
        img[5][1] = '|';
        img[5][10] = '|';
        img[6][1] = '|';
        img[6][5] = 'R';
        img[6][6] = 'W';
        img[6][10] = '|';
        img[7][1] = '|';
        img[7][10] = '|';
        img[8][1] = '|';
        img[8][10] = '|';
        img[9][1] = '|';
        img[9][10] = '|';
        img[10][1] = '\\';
        img[10][10] = '/';
        img[11][2] = '\\';
        img[11][9] = '/';
        img[12][3] = '|';
        img[12][4] = '|';
        img[12][5] = '_';
        img[12][6] = '_';
        img[12][7] = '|';
        img[12][8] = '|';
        img[13][3] = '|';
        img[13][4] = '|';
        img[13][7] = '|';
        img[13][8] = '|';
        img[14][3] = '|';
        img[14][4] = '|';
        img[14][7] = '|';
        img[14][8] = '|';

        return img;
    }

    static char[][] getSword() {
        char[][] img = new char[ROCKET_HEIGHT][ROCKET_WIDTH];
        // fill with empty space
        for (int y = 0; y < ROCKET_HEIGHT; y++) {
            for (int x = 0; x < ROCKET_WIDTH; x++) {
                img[y][x] = ' ';
            }
        }
        // then fill individual characters
        img[0][2] = '/';
        img[0][3] = '\\';
        img[1][1] = '(';
        img[1][4] = ')';
        img[2][1] = '/';
        img[2][4] = '\\';

        img[3][0] = '|';
        img[3][2] = '~';
        img[3][3] = '~';
        img[3][5] = '|';
        img[4][0] = '|';
        img[4][2] = '~';
        img[4][3] = '~';
        img[4][5] = '|';
        img[5][0] = '|';
        img[5][2] = '~';
        img[5][3] = '~';
        img[5][5] = '|';
        img[6][0] = '|';
        img[6][2] = 'R';
        img[6][3] = 'W';
        img[6][5] = '|';
        img[7][0] = '|';
        img[7][2] = '~';
        img[7][3] = '~';
        img[7][5] = '|';
        img[8][0] = '|';
        img[8][2] = '~';
        img[8][3] = '~';
        img[8][5] = '|';
        img[9][0] = '(';
        img[9][2] = '~';
        img[9][3] = '~';
        img[9][5] = ')';
        img[10][0] = '\\';
        img[10][2] = '~';
        img[10][3] = '~';
        img[10][5] = '/';
        img[11][1] = '|';
        img[11][4] = '|';
        img[12][1] = '|';
        img[12][4] = '|';
        img[13][1] = '\\';
        img[13][4] = '/';
        img[14][2] = '\\';
        img[14][3] = '/';

        return img;
    }

    public static int getTerminalWidth() {
        String os = System.getProperty("os.name").toLowerCase();

        if (os.contains("nix") || os.contains("nux") || os.contains("mac")) {
            return getUnixTerminalWidth();
        } else {
            return 80; // fallback for unknown OS
        }
    }

    private static int getUnixTerminalWidth() {
        try {
            // Try to get terminal size from environment variables first
            String columns = System.getenv("COLUMNS");
            if (columns != null && !columns.isEmpty()) {
                return Integer.parseInt(columns);
            }

            // Fallback to stty command
            ProcessBuilder pb = new ProcessBuilder("/bin/sh", "-c", "stty size </dev/tty");
            pb.redirectErrorStream(true);
            Process process = pb.start();
            java.io.BufferedReader reader = new java.io.BufferedReader(
                    new java.io.InputStreamReader(process.getInputStream()));
            String output = reader.readLine();
            if (output != null && !output.isEmpty()) {
                String[] parts = output.trim().split(" ");
                return Integer.parseInt(parts[1]); // columns
            }
        } catch (Exception ignored) {
            // Silently ignore errors and fall back to default
        }
        return 80; // fallback
    }

}