import java.util.ArrayList;

public class Battleship extends ConsoleProgram
{
    public void run()
    {
        // Start here! This class should interact with the user
        // to play the game of Battleship
        
        // You only need to allow for the user to set up each player's
        // ships and for each player to make a guess on the other player's grid
        // Don't worry about finishing the whole game yet.
        
        // You will probably need to make additions to the Player class to
        // allow for this setting up and guessing
        Player a = new Player();
        Player b = new Player();
        int opponentHits = 0;
        int yourHits = 0;
        
        System.out.println("Welcome to Battleship. First you need to choose the location of your ships.");
        System.out.println("Your current grid of ships.");
        placeShips(a); 
        String continueTurn2 = readLine("Press enter to continue.");
        placeRandomShips(b);
        
        while(a.checkWin() == false && b.checkWin() == false) {
            askForGuess(b);
            String continueTurn = readLine("Press enter for the computer turn.");
            computerMove(a);
            String continueTurn1 = readLine("Press enter for your turn.");
        }
        if(a.checkWin()) {
            System.out.println("You lost!");
            System.out.println("Thanks for playing.");
        }
        if(b.checkWin()) {
            System.out.println("You won!");
            System.out.println("Thanks for playing.");
        }
    }
    //Method to find errors
    public static boolean invalidLocation(int row, int col, int direction, Player p, int boatLength) {
        if(row+boatLength > 10 && direction==1) {
            return true;
        }
        if(col+boatLength>10 && direction==0) {
            return true;
        }
        if(direction==0) {
            for(int i = col; i<col+boatLength; i++) {
                if(p.checkShip(i, row)) {
                    return true;
                }
            }
        }
        if(direction==1) {
            for(int i = row; i<row+boatLength; i++) {
                if(p.checkShip(col, i)) {
                    return true;
                }
            }
        }
        return false;
    }
    //Check how many boats you have sunk. If all the values of one ship are 
    //guessed, it is sunk. 
    public int checkSink(Player p) {
        ArrayList<Integer> rows = new ArrayList<Integer>();
        ArrayList<Integer> cols = new ArrayList<Integer>();
        int[] counts = {2, 3, 3, 4, 5};
        int shipsSunk = 0;
        
        for(int i = 0; i<5; i++) {
            int r = p.myShips.get(i).getRow(); 
            rows.add(r);
            int c = p.myShips.get(i).getCol();
            cols.add(c);
        }
        for(int i = 0; i<5; i++) {
            if(p.myShips.get(i).getDirection()==1) {
                for(int j = rows.get(i); j<rows.get(i) + p.myShips.get(i).getLength(); j++) {
                    if (p.checkGuess(cols.get(i), j)) {
                        counts[i] -=1 ;
                    }
                }
            }
            if(p.myShips.get(i).getDirection()==0) {
                for(int k = cols.get(i); k<cols.get(i) + p.myShips.get(i).getLength(); k++) {
                    if (p.checkGuess(k, rows.get(i))) {
                        counts[i] -= 1;
                    }
                }
            }
        }
        for(int i = 0; i<5; i++) {
            if(counts[i]==0) {
                shipsSunk += 1;
            }
        }
        return shipsSunk;
    }  
    //Method for player choosing ships to place
    public void placeShips(Player a) {
        int[] slengths = {2, 3, 3, 4, 5};
        for(int k = 0; k < slengths.length; k++) {
            a.printMyShips();
            System.out.println("Now you need to place a ship of length " + slengths[k]); 
            Ship ship1 = new Ship(slengths[k]);
            int introw1 = 10;
            int col1 = 10;
            int intdir1 = 1;
            boolean invalidFirst = false;
            while(invalidLocation(introw1, col1-1, intdir1, a, slengths[k])) {
                if(invalidFirst) {
                    System.out.println("Invalid location. Choose another location.");
                }
                String row1 = "";
                while((row1.equalsIgnoreCase("A") || row1.equalsIgnoreCase("b") || row1.equalsIgnoreCase("c") || row1.equalsIgnoreCase("d") || row1.equalsIgnoreCase("e") || row1.equalsIgnoreCase("f") || row1.equalsIgnoreCase("g") || row1.equalsIgnoreCase("h") || row1.equalsIgnoreCase("i") || row1.equalsIgnoreCase("j")) == false) {
                    row1 = readLine("Which row? (A-J)"); 
                }
                introw1 = 0;
                String[] rowconvert = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J"};
                for(int i = 0; i<10; i++) {
                    if(row1.equalsIgnoreCase(rowconvert[i])) {
                        introw1 = i;
                    }
                }
                col1=0;
                while((col1 == 1 || col1 == 2 || col1 == 3 || col1 == 4 || col1 == 5 || col1 == 6 || col1 == 7 || col1 == 8 || col1 == 9 || col1 == 10) == false) {
                    col1 = readInt("Which column? (1-10)");
                }
                String dir1 = "";
                while((dir1.equalsIgnoreCase("horizontal") || dir1.equalsIgnoreCase("vertical")) == false) {
                    dir1 = readLine("Horizontal or vertical?");
                }
                intdir1 = -1;
                if(dir1.equalsIgnoreCase("horizontal")) {
                    intdir1 = 0;
                }
                if(dir1.equalsIgnoreCase("vertical")) {
                    intdir1 = 1;
                }
                invalidFirst = true;
            }
            a.chooseShipLocation(ship1, introw1, col1-1, intdir1);
        }
        System.out.println("Location of my ships:");
        a.printMyShips();
    }
            

    //Method for computer randomly placing ships
    public void placeRandomShips(Player b) {
        int[] slengths = {2, 3, 3, 4, 5};
        for(int k = 0; k < slengths.length; k++) {
            Ship ship1 = new Ship(slengths[k]);
            int introw1 = 10;
            int col1 = 10;
            int intdir1 = 1;
            boolean invalidFirst = false; 
            while(invalidLocation(introw1, col1, intdir1, b, slengths[k])) {
                introw1 = Randomizer.nextInt(0, 9); 
                col1 = Randomizer.nextInt(0, 9); 
                intdir1 = Randomizer.nextInt(0, 1); 
            }
            b.chooseShipLocation(ship1, introw1, col1, intdir1);
        }
        System.out.println("The enemy has placed their ships.");
    }
    
    //Player's turn to guess
    public void askForGuess(Player b) {
        
        int col1=9; 
        int introw1=9;
        
        System.out.println("Enemy grid:");
        b.printMyGuesses();
        System.out.println("");
        System.out.println("It's your turn to guess.");
        boolean guessed = false;
        while(b.checkGuess(col1-1, introw1)==true || guessed==false ) {
            if(guessed==true) {
                System.out.println("You already guessed that. Guess again."); 
            }
            String row1 = "";
            while((row1.equalsIgnoreCase("A") || row1.equalsIgnoreCase("b") || row1.equalsIgnoreCase("c") || row1.equalsIgnoreCase("d") || row1.equalsIgnoreCase("e") || row1.equalsIgnoreCase("f") || row1.equalsIgnoreCase("g") || row1.equalsIgnoreCase("h") || row1.equalsIgnoreCase("i") || row1.equalsIgnoreCase("j")) == false) {
                row1 = readLine("Which row? (A-J)"); 
            }
            introw1 = 0;
            String[] rowconvert = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J"};
            for(int i = 0; i<10; i++) {
                if(row1.equalsIgnoreCase(rowconvert[i])) {
                    introw1 = i;
                }
            }
            col1=0;
            while((col1 == 1 || col1 == 2 || col1 == 3 || col1 == 4 || col1 == 5 || col1 == 6 || col1 == 7 || col1 == 8 || col1 == 9 || col1 == 10) == false) {
                col1 = readInt("Which column? (1-10)");
            }
            guessed = true;
        }
        if(b.checkShip(col1-1, introw1)) {
            b.setHit(introw1, col1-1);
            System.out.println("That was a hit!");
        }
        else {
            b.setMiss(introw1, col1-1);
            System.out.println("That was a miss.");
        }
        System.out.println("Ships sunk: " +checkSink(b));
        System.out.println("Total hits = " + b.getHits() + " out of 17.");
    }
    //Computer's turn to guess
    public void computerMove(Player a) {
        
        int col1=9; 
        int introw1=9;
        boolean guessed = false;
        while(a.checkGuess(col1, introw1)==true || guessed==false ) {
            introw1 = Randomizer.nextInt(0, 9); 
            col1 = Randomizer.nextInt(0,9); 
            guessed = true;
        }
        int stringCol = col1+1;
        String row1 = "";
        String[] rowconvert = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J"};
        for(int i = 0; i<10; i++) {
            if(introw1 == i) {
                row1 = rowconvert[i]; 
            }
        }
        System.out.println("Computer player guesses row " + row1 + " and column " + stringCol);
        if(a.checkShip(col1, introw1)) {
            a.setHit(introw1, col1);
            System.out.println("Computer hit.");
        }
        else {
            a.setMiss(introw1, col1);
            System.out.println("Computer missed.");
        }
        System.out.println("Your grid:");
        a.printMyGuesses();
        System.out.println("");
        System.out.println("Ships sunk: " + checkSink(a));
        System.out.println("Total hits = " + a.getHits() + " out of 17.");
    }
}
