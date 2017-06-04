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

/*
    public JPanel createTable()
    {
        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        panel.add(jsp,new GridBagConstraints(0, 0, 1, 1, 1, 1,
                GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL, new Insets(2, 2, 2, 2), 0, 0));

        return panel;
    }*/

    public void addCoord(GraphicsController controller)
    {
        this.controller = controller;

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


