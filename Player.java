import java.util.ArrayList;

public class Player
{
    public static final int[] SHIP_LENGTHS = {2, 3, 3, 4, 5};
    
    private Grid player = new Grid();
    private Grid opponent = new Grid();
    
    private Ship[] ships = new Ship[5];
    private int shipCount = 0;
    ArrayList<Ship> myShips = new ArrayList<Ship>();
    
    int hits = 0; 
    
    public Player() 
    {
        for (int i = 0; i < SHIP_LENGTHS.length; i++)
        {
            ships[i] = new Ship(SHIP_LENGTHS[i]);
        }
    }
    
    
    public void chooseShipLocation(Ship s, int row, int col, int direction)
    {
        if(shipCount >= 5)
        {
            return;
        }
        
        shipCount++;
        s.setLocation(row, col);
        s.setDirection(direction);
        player.addShip(s);
        myShips.add(s);
    }
    
    public void printMyShips() 
    {
        player.printShips();
    }
    public boolean checkShip(int row, int col) {
        return player.hasShip(col, row);
    }
    
    public void setHit(int col, int row) {
        player.markHit(col, row); 
        hits += 1;
    }
    public void setMiss(int col, int row) {
        player.markMiss(col, row); 
    }
    
    public boolean checkWin() {
        if(hits>=17) {
            return true;
        }
        return false;
    }
    public int getHits() {
        return hits;
    }

    public boolean checkGuess(int col, int row) {
        return player.alreadyGuessed(row, col); 
    }
    
    public void printOpponentGuesses() 
    {
        opponent.printStatus();
    }
    
    public void printMyGuesses() 
    {
        player.printStatus();
    }
    
    
    public void recordOpponentGuess(int row, int col)
    {
        if (player.hasShip(row, col))
        {
            opponent.markHit(row, col);
        }
        else opponent.markMiss(row, col);
    }
}
