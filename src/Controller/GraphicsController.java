package Controller;

import Model.Base;
import Model.Coordinates;

/**
 * Created by Asus on 23.05.2017.
 */
public class GraphicsController {

    private Base base;

    public  GraphicsController(Base base)
    {
        this.base = base;
    }

    public void addCoord(Coordinates coordinates) {
        base.add(coordinates);
    }

    public int sizeArray() {
        return base.size();
    }

    public Coordinates get(int i) {
        return base.get(i);
    }

    public Base getBase() {
        return base;
    }

    public void deleteAll()
    {
        base.deleteAll();
    }

    public double getYmax()
    {
        double y;
        double yMax = (double) base.get(0).getValue(1);

        for(int i = 0; i < base.size(); i++)
        {
            y = (double) base.get(i).getValue(1);

            if(Math.abs(y) > Math.abs(yMax)) yMax = y;
        }

        return Math.abs(yMax);
    }


}
