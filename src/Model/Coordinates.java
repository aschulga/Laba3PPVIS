package Model;

import java.awt.geom.Point2D;

import static Controller.ClassOfConst.valueX;
import static Controller.ClassOfConst.valueY;

public class Coordinates {

    private Point2D point;

    public Coordinates(Point2D point)
    {
        this.point = point;
    }

    public Object getValue(int index)
    {
        switch (index)
        {
            case valueX: return point.getX();
            case valueY: return point.getY();

        }
        return null;
    }
}
