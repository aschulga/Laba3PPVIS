package View;

import Controller.GraphicsController;

import javax.swing.*;

/**
 * Created by Asus on 20.05.2017.
 */
public class Table {

    GraphicsController controller;

    ModelTable model = new ModelTable();
    JTable table = new JTable(model);
    JScrollPane jsp = new JScrollPane(table);

    public  Table (GraphicsController controller)
    {
        this.controller = controller;
    }

    public void addCoord()
    {

        model.deleteAll();

        for(int i = 0; i < controller.sizeArray(); i++)
        {
            model.add(controller.get(i));
        }

    }

    public JScrollPane getJsp()
    {
        return jsp;
    }

}


