package Model;

import java.util.ArrayList;

/**
 * Created by Asus on 23.05.2017.
 */
public class Base {

    public ArrayList<Coordinates> coordinatesArrayList = new ArrayList<Coordinates>();

    public void add(Coordinates coordinates)
    {
        coordinatesArrayList.add(coordinates);
    }

    public int size()
    {
        return coordinatesArrayList.size();
    }

    public Coordinates get(int i)
    {
        return coordinatesArrayList.get(i);
    }

    public void deleteAll()
    {
        coordinatesArrayList.clear();
    }

}
