package Controller;

import Model.Coordinates;
import View.MyFrame;


public class MyTheard extends Thread {

    private GraphicsController controller;
    private MyFrame frame;

    public MyTheard(GraphicsController controller,MyFrame frame)
    {
        this.controller = controller;
        this.frame = frame;
    }

    public void run()
    {
        controller.deleteAll();
        double y;
        for (double i = frame.getX1(); i <= frame.getX2(); i += frame.getStep()) {
            y = (Math.exp(-frame.getA() * i + frame.getB())) / (Math.pow(i, 3) - frame.getC());

            Coordinates coordinates = new Coordinates(i, y);

            controller.addCoord(coordinates);

            frame.generationTable();

            try{
                Thread.sleep((long) 0.1);
            }catch (InterruptedException e)
            {
                e.printStackTrace();
            }
        }

    }

}
