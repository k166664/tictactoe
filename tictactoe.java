import java.util.Scanner;

public class tictactoe {
    /*
    * Check if someone wins:
    * Very brain dead way.
    * left-right, up-down, diagnols
    */
    static int CheckBoard(int[] Board) {
        // left right
        for (int i = 0; i < 9; i+=3) {
            int a = Board[i]; // store to compare
            // probably should use an iterator, but assuming 3x3 board
            if ((a != 0) && (a == Board[i + 1]) && (a == Board[i + 2])) {
                return a;
            }
        }
        // up down
        for (int i = 0; i < 3; i+=1) {
            int a = Board[i]; // store to compare
            // probably should use an iterator, but assuming 3x3 board
            if ((a != 0) && (a == Board[i + 3]) && (a == Board[i + 6])) {
                return a;
            }
        }
        // diagnol
        int a = Board[4];
        if ((a != 0) && (a == Board[0]) && (a == Board[8])) {
            return a;
        }
        // diagnol 2
        if ((a != 0) && (a == Board[2]) && (a == Board[6])) {
            return a;
        }
        return 0;
    }
    /*
     * Board will be formatted
     * 0 1 2
     * 3 4 5
     * 6 7 8
     */
    public static void main(String[] args) {
        // board
        int[] Board = {
            0, 0, 0,
            0, 0, 0,
            0, 0, 0,
        };
        // user input
        Scanner scanner = new Scanner(System.in);
        // print instructions
        System.out.println("Tic tac toe:");
        // main loop
        int won = 0;
        int player = 1;
        while (won == 0) {
            // repeat til valid char
            boolean valid = false;
            int pos = 0;
            do {
                // print board
                String out = "";
                System.out.println("Board:");
                for (int i = 0; i < 9; i+=1) {
                    out = out + Integer.toString(Board[i]) + " ";
                    if ((i % 3) == 2) { // trial and error
                        out = out + "\n";
                    }
                }
                System.out.println(out);
                System.out.println("Player " + Integer.toString(player) + "'s turn");
                System.out.println("The board is in the format of\n1 2 3\n4 5 6\n7 8 9.");
                // get input
                String input = scanner.nextLine();
                // is it in the format of "n"?
                if (input.length() == 1) {
                    try {
                        pos = Integer.parseInt(input) - 1;
                        // now check if we can place there
                        if (Board[pos] == 0) {
                            Board[pos] = player;
                            valid = true;
                        } else {
                            // not valid
                            System.out.println("That spot is already filled.");
                        }
                    } catch (NumberFormatException e) {
                        // not valid
                        System.out.println("Enter a number between 1-9 that corresponds to a spot.");
                    }
                } else {
                    // not valid
                    System.out.println("Input too long / short. Input should be 1 number");
                }
            } while (valid == false);
            // System.out.println(pos);
            won = CheckBoard(Board);
            // Check if board is full
            boolean full = true;
            for (int i = 0; i < 9; i+=1) {
                if ((Board[i]) != 0) {
                    full = false;
                }
            }
            if (full) {
                won = 0;
                break;
            }
            // Switch player (1 -> 2, 2 -> 1)
            player = player * -1 + 3;
        }
        // print results
        // https://stackoverflow.com/questions/5071040/java-convert-integer-to-string
        if (won == 0) {
            System.out.println("The game was a tie.");
        } else {
            System.out.println("Player " + Integer.toString(won) + " won!");
        }
        String out = "";
        for (int i = 0; i < 9; i+=1) {
            if (Board[i] == won) {
                // out = out + Integer.toString(Board[i]) + " ";
                out = out + "X ";
            } else {
                out = out + "  ";
            }
            if ((i % 3) == 2) { // trial and error
                out = out + "\n";
            }
        }
        System.out.println(out);
        // close scanner
        scanner.close();
    }
}