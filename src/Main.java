import Controller.GraphicsController;
import Model.Base;
import View.MyFrame;

import java.awt.*;

/**
 * Created by Asus on 23.05.2017.
 */
public class Main {

    public static void main(String[] args){

        Base base = new Base();
        GraphicsController controller = new GraphicsController(base);

        MyFrame m = new MyFrame("Построение графика",new Dimension(1100,782),controller);
        m.init();

    }


}
