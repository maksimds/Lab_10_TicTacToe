import java.util.Scanner;

public class Main {
    private static final int ROW = 3;
    private static final int COL = 3;
    private static String board[][] = new String[COL][ROW];

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        boolean gameOver = false;
        clearBoard();
        do {

            do
            {
                gameOver = false;
                turn(in, "X");
                if (isWin("X")) {
                    System.out.println("X Wins!");
                    gameOver = true;
                }
                if (isTie())
                {
                    System.out.println("It's a Tie!");
                    gameOver = true;
                }
                if (!gameOver)
                {
                    turn(in, "O");
                    if (isWin("O")) {
                        System.out.println("O Wins!");
                        gameOver = true;
                    }
                }

            }while(!gameOver);
            clearBoard();
        } while (SafeInput.getYNConfirm(in, "Do you want to continue playing?"));
    }

    private static void turn(Scanner in, String player)
    {
        int tempRow;
        int tempCol;
        boolean validMove;
        do
        {
            validMove = false;

            System.out.print(player + "'s turn");
            tempCol = SafeInput.getRangedInt(in, "Enter your colum: ", 1, 3) - 1;
            tempRow = SafeInput.getRangedInt(in, "Enter your row: ", 1, 3) - 1;
            if (isValidMove(tempCol, tempRow))
            {
                board[tempRow][tempCol] = player;
                display();
                System.out.println();

            }
            else
            {
                validMove = true;
                System.out.println("Invalid move, you must select an unused tile.");
                System.out.println();
            }
        }    while (validMove);
    }

    private static void clearBoard() {
        for (int i = 0; i < ROW; i++) {
            for (int x = 0; x < COL; x++) {
                board[i][x] = " ";
            }
        }
    }

    private static void display() {
        for (int i = 0; i < ROW; i++) {
            for (int x = 0; x < COL; x++) {
                System.out.print("[" + board[i][x] + "]");
            }
            System.out.println();
        }
    }

    private static boolean isValidMove(int row, int col) {
        if (board[col][row].equals(" ")) {
            return true;
        } else {
            return false;
        }
    }

    private static boolean isWin(String player)
    {
        if (isColWin(player) || isRowWin(player) || isDiagnalWin(player))
        {
            return true;
        }
        return false;


    }

    private static boolean isColWin(String player) {

        for (int j = 0; j < board[0].length; j++)
        {
            boolean completeCol = true;

            if (!(board[0][j].equals(player)))
            {
                completeCol = false;
            } else {
                for (int i = 1; i < board.length; i++)
                {
                    if (!(board[i][j].equals(player)))
                    {
                        completeCol = false;
                        break;
                    }
                }
            }
            if (completeCol)
            {
                return true;
            }
        }
        return false;
    }

    private static boolean isRowWin(String player)
    {
        for (int i = 0; i < board.length; i++) {
            boolean completeRow = true;

            if (!(board[i][0].equals(player))) {
                completeRow = false;
            } else {
                for (int j = 1; j < board[i].length; j++) {
                    if (!(board[i][j].equals(player))) {
                        completeRow = false;
                        break;
                    }
                }
            }
            if (completeRow)
            {
                return true;
            }
        }
        return false;
    }

    private static boolean isDiagnalWin(String player)
    {
        boolean completeDiagonal = true;

        if(!(board[0][0].equals(player)))
        {
            completeDiagonal = false;
        }
        else
        {
            for(int i = 1; i < board.length; i++)
            {
                if (!(board[i][i].equals(player)))
                {
                    completeDiagonal = false;
                    break;
                }
            }
            if (completeDiagonal)
            {
                return true;
            }
        }
        completeDiagonal = true;
        if(!(board[0][board.length -1].equals(player)))
        {
            completeDiagonal = false;
        }
        else
        {
            for(int i = 1; i < board.length; i++)
            {
                if (!(board[i][board.length-1-i].equals(player)))
                {
                    completeDiagonal = false;
                    break;
                }
            }
            if (completeDiagonal)
            {
                return true;
            }
        }

        return false;
    }

    private static boolean isTie ()
    {
        boolean fullBoard = true;
        for (int i = 0; i < ROW; i++)
        {
            for (int x = 0; x < COL; x++) {
                if (board[i][x].equals(" "))
                {
                    fullBoard = false;
                }
            }
        }
        if (fullBoard)
        {
            return true;
        }
        return false;
    }


}