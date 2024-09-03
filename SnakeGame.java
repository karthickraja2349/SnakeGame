
import java.util.Scanner;
import java.util.Queue;
import java.util.LinkedList;
import java.util.Random;

public class SnakeGame {
    private char[][] snakeBoard;
    private Queue<Node> queue = new LinkedList<>();
    private Random random = new Random();
    private int count;

    public SnakeGame(int row, int column) {
        this.snakeBoard = new char[row][column];
        // Initialize  board 
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < column; j++) {
                snakeBoard[i][j] = ' ';
            }
        }
        queue.add(new Node(0, 0));
        addRandomFood();
        snakeMove(0, 0);
    }

    public SnakeGame() {
        this(6, 6);
    }

    private void addRandomFood() {
        int row, column;
        do {
            row = random.nextInt(snakeBoard.length);
            column = random.nextInt(snakeBoard[0].length);
        } while (snakeBoard[row][column] != ' '); //   food  placed on  empty cell

        snakeBoard[row][column] = 'X';
    }

    private void snakeMove(int row, int column) {
        if (row >= 0 && column >= 0 && row < snakeBoard.length && column < snakeBoard[0].length) {
            if (snakeBoard[row][column] == '.') {
                System.out.println("Game Over!!!");
                System.out.println("Your score is:"+count);
                System.exit(0);
            }
            queue.add(new Node(row, column));

            if (snakeBoard[row][column] != 'X'){
                Node node = queue.poll();
                int previousRow = node.getRow();
                int previousColumn = node.getColumn();
                snakeBoard[previousRow][previousColumn] = ' ';
            }

            if (snakeBoard[row][column] == 'X') {
                count++;
                addRandomFood();
            }
            snakeBoard[row][column] = '.';
            printSnake();

            Scanner input = new Scanner(System.in);
            System.out.println("Enter the position to move R/r-> right, L/l->left, U/u->up, D/d->down");
            char direction = input.next().charAt(0);
            switch (direction) {
                case 'U':
                case 'u':
                    snakeMove(row - 1, column);
                    break;
                case 'D':
                case 'd':
                    snakeMove(row + 1, column);
                    break;
                case 'R':
                case 'r':
                    snakeMove(row, column + 1);
                    break;
                case 'L':
                case 'l':
                    snakeMove(row, column - 1);
                    break;
                default:
                    System.out.println("Please select R/L/U/D");
                    break;
            }
        } else {
            System.out.println("Invalid Move");
            System.out.println("Game Over!!!");
            System.out.println("Your score is:"+count);
            System.exit(0);
        }
    }

    private void printSnake() {
          int numRows = snakeBoard.length;
          int numColumns = snakeBoard[0].length;
          PrintBorder();
          for (int i = 0; i < numRows; i++) {
              System.out.print("|");
              for (int j = 0; j < numColumns; j++) {
                 System.out.print(snakeBoard[i][j] + " ");
              }
              System.out.print("|");
              System.out.println();
          }
         PrintBorder();
     }

     private void PrintBorder(){
          System.out.print("*");
          for (int i = 0; i < snakeBoard[0].length; i++) {
             System.out.print("--");
          }
          System.out.print("*");
          System.out.println();
     }
}




