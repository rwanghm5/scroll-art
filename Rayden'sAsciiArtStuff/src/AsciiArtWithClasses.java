import java.util.Random;

public class AsciiArtWithClasses {
    static final int WIDTH = getTerminalWidth() - 1;
    static final int ROCKET_WIDTH = 20;
    static final int ROCKET_HEIGHT = 10;
    static final Random rand = new Random();
    // NO OVERLAPS
    // 1. Skip every w (width) characters instead of every column to reduce
    // horizontal overlap
    // 2. How do you reduce overlap vertically? Remember previous row at the
    // specific width

    public static void main(String[] args) throws InterruptedException {
        long startTime = System.currentTimeMillis();
        int iterations = 0;
        char[][] nextRows = new char[21][WIDTH]; // store upcoming rows
        for (int i = 0; i < nextRows.length; i++) {
            nextRows[i] = emptyRow();
        }
        char[] prevRow = nextRows[0];

        while (true) {
            // At each lane now on the top row, 7% chance to add a new image
            for (int x = 0; x < WIDTH - 21; x=x+21) {
                if (isBlank(prevRow, x) && rand.nextDouble() < 0.07) {
                    loadNextRowsWithImage(nextRows, x);
                }
            }
            // Print and remove the top row
            System.out.println(new String(nextRows[0]));
            prevRow = nextRows[0]; // update prev row
            // Shift all rows up
            shiftRowsUp(nextRows);
            Thread.sleep(40); // Delay in ms
            long time = System.currentTimeMillis() - startTime;
            iterations++;

            // System.err.println("average time per frame: " + (time / iterations) + " ms");
        }
    }

    // #2: checking if row is blank
    static boolean isBlank(char[] row, int x) {
        for (int i = x; i < x + 21; i++) {
            if (row[i] != ' ') {
                return false;
            }
        }
        return true;
    }

    private static void loadNextRowsWithImage(char[][] nextRows, int x) {
        // triple array version to better manage all images, it's getting a little bit out of hand!! and it is not like they are all the same size anyway.
        char[][][] images = {buildLlama(), getIceCream(), GEM()};
        char[][] img = images[rand.nextInt(images.length)];
        for (int iy = 0; iy < img.length; iy++) { // height
            for (int ix = 0; ix < img[iy].length; ix++) { // width
                nextRows[iy][x + ix] = img[iy][ix];
            }
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

    // Rohan's GEM
     static char[][] GEM () {
        char[][] img = new char[16][16];
        for (int y = 0; y < 16; y = y + 1) {
            for (int x = 0; x < 16; x = x + 1) {
                img[y][x] = ' ';
            }
        }
        
    img[0][5]  = '_';
    img[0][6]  = '_';
    img[0][7]  = '_';
    img[0][8]  = '_';
    img[0][9]  = '_';
    img[0][10] = '_';

    img[1][2]  = '_';
    img[1][3]  = '_';
    img[1][4]  = '/';
    img[1][5]  = '[';
    img[1][6]  = ']';
    img[1][8]  = '[';
    img[1][9]  = ']';
    img[1][11] = '\\';
    img[1][12] = '_';
    img[1][13] = '_';
    img[1][14] = '_';

    img[2][1]  = '|';
    img[2][4]  = 'R';
    img[2][5]  = 'O';
    img[2][6]  = 'H';
    img[2][7]  = 'A';
    img[2][8]  = 'N';
    img[2][14] = '|';

    img[3][1]  = '|';
    img[3][2]  = '_';
    img[3][3]  = '_';
    img[3][4]  = '_';
    img[3][5]  = '_';
    img[3][6]  = '_';
    img[3][7]  = '_';
    img[3][8]  = '_';
    img[3][9]  = '_';
    img[3][10] = '_';
    img[3][11] = '_';
    img[3][12] = '_';
    img[3][13] = '_';
    img[3][14] = '|';

    img[4][1]  = '(';
    img[4][2]  = ')';
    img[4][12]  = '(';
    img[4][13] = ')';

    img[5][0]  = '=';
    img[5][1]  = '=';
    img[5][2]  = '=';
    img[5][3]  = '=';
    img[5][4]  = '=';
    img[5][5]  = '=';
    img[5][6]  = '=';
    img[5][7]  = '=';
    img[5][8]  = '=';
    img[5][9]  = '=';
    img[5][10] = '=';
    img[5][11] = '=';
    img[5][12] = '=';
    img[5][13] = '=';
    img[5][14] = '=';
    img[5][15] = '=';

    img[6][2]  = '-';
    img[6][3]  = '-';
    img[6][6]  = '-';
    img[6][7]  = '-';
    img[6][10] = '-';
    img[6][11] = '-';
    img[6][14] = '-';
    img[6][15] = '-';

    img[7][0]  = '=';
    img[7][1]  = '=';
    img[7][2]  = '=';
    img[7][3]  = '=';
    img[7][4]  = '=';
    img[7][5]  = '=';
    img[7][6]  = '=';
    img[7][7]  = '=';
    img[7][8]  = '=';
    img[7][9]  = '=';
    img[7][10] = '=';
    img[7][11] = '=';
    img[7][12] = '=';
    img[7][13] = '=';
    img[7][14] = '=';
    img[7][15] = '=';

        return img;
    }

    // Ishaan's Ice Cream
    static char[][] getIceCream() {
        char[][] img = new char[8][8];

        // Meant to Fill  exmpty spaces
        for (int y = 0; y < 7; y++) {
            for (int x = 0; x < 7; x++) {
             img[y][x] = ' ';
            }
        }
        //Actual Ice Cream Art
        img[0][3] = '0';
        img[1][1] = '(';
        img[1][6] = ')';
        img[2][0] = '(';
        img[2][2] = '_';
        img[2][3] = '_';
        img[2][4] = '_';
        img[2][5] = '_';
        img[2][7] = ')';
        img[3][1] = '\\';
        img[3][6] = '/';
        img[4][2] = '\\';
        img[4][5] = '/';
        img[5][3] = '\\';
        img[5][4] = '/';
        img[5][5] = 'I';
        img[5][6] = 'M';

        return img;
    }
    //Maria's llama
    static char[][] buildLlama() {
        char[][] llama = new char[20][20];
        for (int i = 0; i < 20; i++) {
            for (int j = 0; j < 20; j++) {
                llama[i][j] = ' ';
            }
        }

        llama[0][1] = '/';
        llama[0][2] = '^';
        llama[0][3] = '-';
        llama[0][4] = '-';
        llama[0][5] = '-';
        llama[0][6] = '^';
        llama[0][7] = '\\';
        llama[1][1] = '|';
        llama[1][3] = '.';
        llama[1][5] = '.';
        llama[1][7] = '|';
        llama[2][1] = '\\';
        llama[2][4] = '`';
        llama[2][7] = '/';
        llama[2][12] = 'M';
        llama[2][13] = 'S';
        llama[3][1] = '/';
        llama[3][2] = '=';
        llama[3][3] = '=';
        llama[3][4] = '=';
        llama[3][5] = '=';
        llama[3][6] = '=';
        llama[3][7] = '\\';
        llama[3][8] = '_';
        llama[3][9] = '_';
        llama[3][10] = '_';
        llama[3][11] = '_';
        llama[3][12] = '_';
        llama[3][13] = '_';
        llama[3][14] = '_';
        llama[3][15] = '_';
        llama[3][16] = '_';
        llama[4][0] = '/';
        llama[4][17] = '\\';
        llama[4][18] = '[';
        llama[4][19] = ']';
        llama[5][0] = '\\';
        llama[5][17] = '/';
        llama[6][1] = '-';
        llama[6][2] = '-';
        llama[6][3] = '-';
        llama[6][4] = '-';
        llama[6][5] = '-';
        llama[6][6] = '-';
        llama[6][7] = '-';
        llama[6][8] = '-';
        llama[6][9] = '-';
        llama[6][10] = '-';
        llama[6][11] = '-';
        llama[6][12] = '-';
        llama[6][13] = '-';
        llama[6][14] = '-';
        llama[6][15] = '-';
        llama[6][16] = '-';
        llama[7][1] = '|';
        llama[7][3] = '|';
        llama[7][5] = '|';
        llama[7][7] = '|';
        llama[7][10] = '|';
        llama[7][12] = '|';
        llama[7][14] = '|';
        llama[7][16] = '|';
        llama[8][1] = '[';
        llama[8][2] = '|';
        llama[8][3] = ']';
        llama[8][5] = '[';
        llama[8][6] = '|';
        llama[8][7] = ']';
        llama[8][10] = '[';
        llama[8][11] = '|';
        llama[8][12] = ']';
        llama[8][14] = '[';
        llama[8][15] = '|';
        llama[8][16] = ']';
        return llama;
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
