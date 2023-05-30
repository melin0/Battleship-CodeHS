public class Location
{
    public static final int UNGUESSED = 0;
    public static final int HIT = 1;
    public static final int MISSED = 2;
    private boolean hasShip;
    private int status;
   
    public Location()
    {
        status = UNGUESSED;
        hasShip = false;
    }

    public boolean checkHit()
    {
        if (status == HIT) 
            return true;
        else
            return false;
    }

    public boolean checkMiss()
    {
        if (status == MISSED)
            return true;
        else
            return false;
    }

    public boolean isUnguessed()
    {
        if (status == UNGUESSED)
            return true;
        else
            return false;
    }

    public void markHit()
    {
        setStatus(HIT);
    }

    public void markMiss()
    {
        setStatus(MISSED);
    }

    public boolean hasShip()
    {
        return hasShip;
    }

    public void setShip(boolean val)
    {
        this.hasShip = val;
    }

    public void setStatus(int status)
    {
        this.status = status;
    }

    public int getStatus()
    {
        return status;
    }
}
