package Model;

/**
 * Created by Asus on 23.05.2017.
 */
public class Coordinates {

    public static final int CoordX = 0;
    public static final int CoordY = 1;
    private  double x;
    private  double y;

    public Coordinates(double x,double y)
    {
        this.x = x;
        this.y = y;
    }

    public Object getValue(int index)
    {
        switch (index)
        {
            case CoordX: return x;
            case CoordY: return y;

        }
        return null;
    }
}
