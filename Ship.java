public class Ship
{
    private int row;
    private int col;
    private int length;
    private int direction;
    
    public static final int UNSET = -1;
    public static final int HORIZONTAL = 0;
    public static final int VERTICAL = 1;
    
    public Ship(int length)
    {
        this.length = length;
        this.row = -1;
        this.col = -1;
        this.direction = UNSET;
    }
    public boolean isLocationSet()
    {
        if (row == -1 || col == -1) {
            return false;
        }
        else {
            return true;
        }
    }
    
    public boolean isDirectionSet()
    {
        if (direction == UNSET) {
            return false;
        }
        else {
            return true;
        }
    }
    
    public void setLocation(int row, int col)
    {
        this.row = row;
        this.col = col;
    }
    
    public void setDirection(int direction)
    {
        this.direction = direction;
    }
    
    public int getRow()
    {
        return row;
    }
    
    public int getCol()
    {
        return col;
    }
    
    public int getLength()
    {
        return length;
    }
    
    public int getDirection()
    {
        return direction;
    }
    
    private String directionToString()
    {
        if (direction == UNSET)
        {
            return "unset direction";
        }
            
        else if (direction == HORIZONTAL)
        {
            return "horizontal";
        }
        else
        {
            return "vertical";
        }
    }
    private String locationToString() {
        if(getRow() == UNSET) {
            return "(unset location)";
        }
        return "(" + getRow() + ", " + getCol() + ")";
    }
    
    public String toString()
    {
        return directionToString() + " ship of length " + getLength() + " at " + locationToString();  
    }
}
