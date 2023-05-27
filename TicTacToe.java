import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class TicTacToe 
{
    static ArrayList<Integer> player1Positions = new ArrayList<Integer>();
    static ArrayList<Integer> player2Positions = new ArrayList<Integer>();
    static ArrayList<Integer> cpuPositions = new ArrayList<Integer>();
    public static void main(String[] args) 
    {
        char [][] gameBoard = {{'1', '|', '2', '|', '3'}, 
        {'-', '+', '-', '+', '-'}, 
        {'4', '|', '5', '|', '6'}, 
        {'-', '+', '-', '+', '-'}, 
        {'7', '|', '8', '|', '9'}};

        printGameBoard(gameBoard);


        System.out.println();
        System.out.println("***************************");
        System.out.println();
        
        Scanner scan = new Scanner(System.in);
        //ask player1 which whome wants to play
        System.out.println("Player1 do you want to play with Player2 or CPU \nEnter 1 for Player2 choice \nEnter 2 for CPU choice");
        int choice = scan.nextInt(); 

        System.out.println();
        System.out.println("***************************");
        System.out.println();
        
        while(true)
        {
            
            //for player
            System.out.print(" Player1 Enter your placement (1-9): ");
            int player1Pos = scan.nextInt();

            while(player1Positions.contains(player1Pos) || player2Positions.contains(player1Pos)){
                System.out.println("position taken! Enter a correct position");
                player1Pos = scan.nextInt(); 

            }

            System.out.println(player1Pos);
            placePiece(gameBoard, player1Pos, "player1");
            printGameBoard(gameBoard);

            System.out.println();
            System.out.println("***************************");
            System.out.println();

            String result =  checkWinner();
            if(result.length()>0){
                System.out.println(result);
                break;
            }

            //
            if(choice == 1){

                System.out.print("Player2 Enter your placement (1-9): ");
                int player2Pos = scan.nextInt();
                while(player1Positions.contains(player2Pos) || player2Positions.contains(player2Pos)){
                    System.out.println("position taken! Enter a correct position");
                    player2Pos = scan.nextInt(); 

                }


                placePiece(gameBoard, player2Pos, "player2");

                printGameBoard(gameBoard);

                System.out.println();
                System.out.println("***************************");
                System.out.println();

                result =  checkWinner();
                if(result.length()>0){
                    System.out.println(result);
                    break;
                }

            }
            else if(choice == 2){

                //for cpu
                Random rand = new Random();
                int cpuPos = rand.nextInt(9) + 1;
                while(player1Positions.contains(cpuPos) || cpuPositions.contains(cpuPos)){
                    cpuPos = scan.nextInt(); 

                }

                placePiece(gameBoard, cpuPos, "cpu");

                printGameBoard(gameBoard);

                System.out.println();
                System.out.println("***************************");
                System.out.println();

                result =  checkWinner();
                if(result.length()>0){
                    System.out.println(result);
                    break;
                }

            }
            else{
                System.out.println("Enter correct choice");
                break;
            }

        }

        
    }



    //to print the board
    public static void printGameBoard(char[][] gameBoard)
    {
        for(char[]row : gameBoard){
            for(char c : row){
                System.out.print(c);
            }
            System.out.println();
        }

    }

    //to place the x
    public static void placePiece(char[][] gameBoard, int pos, String user)
    {
        char symbol = ' ';
        if(user.equals("player1")){
            symbol = 'X';
            player1Positions.add(pos);
        }
        else if(user.equals("player2")){
            symbol = 'o';
            player2Positions.add(pos);
        }
        else if(user.equals("cpu")){
            symbol = 'o';
            cpuPositions.add(pos);
        }


        switch(pos){
            case 1:
                gameBoard[0][0] = symbol;
                break;
            case 2:
                gameBoard[0][2] = symbol;
                break;
            case 3:
                gameBoard[0][4] = symbol;
                break;
            case 4:
                gameBoard[2][0] = symbol;
                break;
            case 5:
                gameBoard[2][2] = symbol;
                break;
            case 6:
                gameBoard[2][4] = symbol;
                break;
            case 7:
                gameBoard[4][0] = symbol;
                break;
            case 8:
                gameBoard[4][2] = symbol;
                break;
            case 9:
                gameBoard[4][4] = symbol;
                break;
            default:
                break;
        }

    }

    //to check winner
    public static String checkWinner()
    {
        List topRow = Arrays.asList(1, 2, 3);
        List midRow = Arrays.asList(4, 5, 6);
        List botRow = Arrays.asList(7, 8, 9);
        List leftCol = Arrays.asList(1, 4, 7);
        List midCol = Arrays.asList(2, 5, 8);
        List rightCol = Arrays.asList(3, 6, 9);
        List cross1 = Arrays.asList(1, 5, 9);
        List cross2 = Arrays.asList(7, 5, 3);

        List<List> winning = new ArrayList<List>();
        winning.add(topRow);
        winning.add(midRow);
        winning.add(botRow);
        winning.add(leftCol);
        winning.add(midCol);
        winning.add(rightCol);
        winning.add(cross1);
        winning.add(cross2);

        for(List l : winning){
            if(player1Positions.containsAll(l)){
                return "Congratulation Player1 won!";
            }
            else if(player2Positions.containsAll(l)){
                return "Congratulation Player2 won!";
            }
            else if(cpuPositions.containsAll(l)){
                return "CPU won!";
            }
            
            else if((player1Positions.size() + player2Positions.size() == 9) || (player1Positions.size() + cpuPositions.size() == 9)){
                return "TIE!....";
            }
        }

        return "";
    }
    
}
