package Controller;

import Model.Coordinates;
import View.MyFrame;

import java.awt.geom.Point2D;


public class MyTheard implements Runnable {

    private GraphicsController controller;
    private MyFrame frame;
    private Thread thread;

    public MyTheard(GraphicsController controller,MyFrame frame)
    {
        this.controller = controller;
        this.frame = frame;
        thread = new Thread(this);
        thread.start();
    }

    public void run()
    {
        controller.deleteAll();
        double y;
        for (double i = frame.getX1(); i <= frame.getX2(); i += frame.getStep()) {

            y = (Math.exp(-frame.getA() * i + frame.getB())) / (Math.pow(i, 3) - frame.getC());

            Coordinates coordinates = new Coordinates(new Point2D.Double(i, y) {
            });

            controller.addCoord(coordinates);

            frame.generationTable();

            try{
                Thread.sleep((long) 0.0001);
            }catch (InterruptedException e)
            {
                e.printStackTrace();
            }
        }

    }

}
