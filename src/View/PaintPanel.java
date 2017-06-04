package View;

import Controller.GraphicsController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;

public class PaintPanel extends JComponent implements MouseWheelListener, MouseMotionListener, MouseListener {
    int lenghtY, lenghtX;
    double step = 0.1;
    int scale = 50;
    private double zoom = 1.0;
    int start = 0;
    MyFrame frame;
    int precent;

    private Dimension initialSize = new Dimension(790,800);

    private GraphicsController controller;

    public static final double SCALE_STEP = 0.1d; //шаг масштабирования

    private Point origin;
    private double previousZoom = zoom; //предыдущий зум
    AffineTransform tx = new AffineTransform();
    private double scrollX = 0d;
    private double scrollY = 0d;

    public PaintPanel(GraphicsController controller,MyFrame frame) {
        this.frame = frame;
        this.controller = controller;
        addMouseWheelListener(this);
        addMouseMotionListener(this);
        addMouseListener(this);
        setAutoscrolls(true);
        lenghtY = (int) (initialSize.getHeight()); // длина оси у
        lenghtX = (int) (initialSize.getWidth()); // длина оси х
    }

    public Dimension getInitialSize() {
        return initialSize;
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D line = (Graphics2D) g.create();

        line.transform(tx);
        line.setColor(Color.BLACK);

        int lengthArrow = 10;
        double half = 2;


        // Ось Y
        line.draw(new Line2D.Double((int) (lenghtX / half)*zoom, start, (int) (lenghtX / half)*zoom, (int) lenghtY*zoom));

        // Надпись
        line.drawString("Y", (int) ((lenghtX / half)*zoom - 20), 10);


        //Стрелки

        line.draw(new Line2D.Double((int) (lenghtX / half - lengthArrow)*zoom, lengthArrow*zoom, (lenghtX / half)*zoom, start));

        line.draw(new Line2D.Double((int) (lenghtX / half + lengthArrow)*zoom, lengthArrow*zoom, (lenghtX / half)*zoom, start));


        //Деления

        int i = (int) ((lenghtY / 2-scale)*zoom);
        int s = scale;

        while (i >= 0 && i <= ((lenghtY*zoom) / 2)) {
            line.draw(new Line2D.Double((lenghtX / half - 2)*zoom, i, (lenghtX / half + 2)*zoom, i));
            if(i !=(lenghtY*zoom)/2){
                line.drawString(String.valueOf(s), (int) ((lenghtX / half - 25)*zoom), i);
                s = s + scale;
            }
            i -= scale * zoom;
        }

        i = (int) (((lenghtY / 2) + scale)*zoom);
        s = scale;

        while (i > ((lenghtY*zoom) / 2) && i <= lenghtY*zoom) {
            line.draw(new Line2D.Double((lenghtX / half - 2)*zoom, i, (lenghtX / half + 2)*zoom, i));
            if(i != (lenghtY/2)*zoom) {
                line.drawString(String.valueOf(-s), (int) ((lenghtX / half - 30)*zoom), (i));
                s = s + scale;
            }
            i += scale * zoom;
        }

        // Ось X
        line.draw(new Line2D.Double((int) start, (int) (lenghtY / half)*zoom, lenghtX*zoom, (int) (lenghtY / half)*zoom));

        //Стрелки

        line.draw(new Line2D.Double((int) (lenghtX - lengthArrow)*zoom, (lenghtY / 2 - lengthArrow)*zoom, lenghtX*zoom, (lenghtY / 2)*zoom));

        line.draw(new Line2D.Double((int) (lenghtX - lengthArrow)*zoom, (lenghtY / 2 + lengthArrow)*zoom, lenghtX*zoom, (lenghtY / 2)*zoom));

        //Деления

        int j = (int) ((lenghtX / 2 - scale)*zoom);
        s = scale;

        while (j >= 0 && j <= (lenghtX / 2)*zoom) {
            line.draw(new Line2D.Double(j, (lenghtY / half - 2)*zoom, j, (lenghtY / half + 2)*zoom));
            if(j != lenghtX/2) {
                line.drawString(String.valueOf(-s), j, (int) ((lenghtY / half + 15)*zoom));
                s = s + scale;
            }
            j -= scale * zoom;

        }

        j = (int) (((lenghtX / 2) + scale) * zoom);
        s = scale;

        while (j > ((lenghtX*zoom) / 2) && j <= lenghtX*zoom) {
            line.draw(new Line2D.Double(j, (lenghtY / half - 2)*zoom, j, (lenghtY / half + 2)*zoom));
            if(j != lenghtX/2) {
                line.drawString(String.valueOf(s), j, (int) ((lenghtY / half + 15)*zoom));
                s = s + scale;
            }
            j += scale * zoom;
        }

        line.drawString("0", (int) (((lenghtX / half) - 15)*zoom), (int) (((lenghtY / half) + 15)*zoom));
        line.drawString("Х", (int)((lenghtX - 10)*zoom), (int) (((lenghtY / half) + 25)*zoom));

        funcPar(g);

        line.dispose();
    }

    void funcPar(Graphics g) {

        Graphics2D graph = (Graphics2D) g.create();

        graph.transform(tx);
        graph.setColor(Color.red);

        double y1, y2;
        double x1,x2;

        for(int i = 1; i < controller.getBase().size(); i++)
        {
            x1 = (double) controller.get(i-1).getValue(0);
            x2 = (double) controller.get(i).getValue(0);

            y1 = (double) controller.get(i-1).getValue(1);
            y2 = (double) controller.get(i).getValue(1);

            graph.draw(new Line2D.Double(lenghtX / 2*zoom + x1 * zoom, lenghtY / 2*zoom - y1 * zoom, lenghtX / 2*zoom + x2 * zoom, lenghtY / 2 *zoom- y2 * zoom));
        }
        graph.dispose();
    }



    @Override
    public void setSize(Dimension size) {
        super.setSize(size);
        if (initialSize == null) {
            this.initialSize = size;
        }
    }

    @Override
    public void setPreferredSize(Dimension preferredSize) {
        super.setPreferredSize(preferredSize);
        if (initialSize == null) {
            this.initialSize = preferredSize;
        }
    }

    public void mouseWheelMoved(MouseWheelEvent e) {

        precent = (int) ((initialSize.width * zoom) / (initialSize.width) * 100);

            if ((initialSize.width) * zoom >= lenghtX && (initialSize.height) * zoom >= lenghtY) {

                if (precent <= 1000) {
                    double zoomFactor = -SCALE_STEP * e.getPreciseWheelRotation() * zoom;
                    zoom = Math.abs(zoom + zoomFactor);
                    Dimension d = new Dimension(
                            (int) (initialSize.width * zoom),
                            (int) (initialSize.height * zoom));
                    setPreferredSize(d);
                    setSize(d);
                    validate();
                    followMouseOrCenter(e);
                    previousZoom = zoom;

                    frame.labelPercent.setText(String.valueOf(precent) + " %");
                } else {
                    zoom = 10;
                }
            } else {
                followMouseOrCenter(e);
                zoom = 1.0;
            }
        }

    public void followMouseOrCenter(MouseWheelEvent e) {
        Point2D point = e.getPoint(); // получаю координаты мыши
        Rectangle visibleRect = getVisibleRect(); // получаю координаты прямоугольника(видимой области)

        scrollX = point.getX() / previousZoom * zoom - (point.getX() - visibleRect.getX());

        scrollY = point.getY() / previousZoom * zoom - (point.getY() - visibleRect.getY());

        visibleRect.setRect(scrollX, scrollY, visibleRect.getWidth(), visibleRect.getHeight());

        scrollRectToVisible(visibleRect);
    }

    public void mouseDragged(MouseEvent e) {

        Rectangle visibleRect = getVisibleRect(); // получаю координаты прямоугольника(видимой области)

        if(((int)(initialSize.width*zoom-visibleRect.getWidth()) == (int)visibleRect.getX()) && (frame.getX2() >= lenghtX/2))
        {
            initialSize.width = initialSize.width+100;
            lenghtX = initialSize.width;
        }


        if(((int)visibleRect.getX()==start) && (Math.abs(frame.getX1()) >= lenghtX/2))
        {
            initialSize.width = initialSize.width+100;
            lenghtX = initialSize.width;
        }

        if(((int)(initialSize.height*zoom-visibleRect.height) == (int)visibleRect.getY())&& (controller.getYmax() >= lenghtY/2))
        {
            initialSize.height = initialSize.height+100;
            lenghtY = initialSize.height;
        }

        if(((int)visibleRect.getY()==start) && (controller.getYmax() >= lenghtY/2))
        {
            initialSize.height = initialSize.height+100;
            lenghtY = initialSize.height;
        }

        Dimension d = new Dimension(
                (int) (initialSize.width * zoom),
                (int) (initialSize.height * zoom));
        setPreferredSize(d);
        setSize(d);

        if (origin != null) {
            int deltaX = origin.x - e.getX();
            int deltaY = origin.y - e.getY();
            Rectangle view = getVisibleRect();
            Dimension size = getSize();
            view.x += deltaX;
            view.y += deltaY;
            scrollRectToVisible(view);
        }
    }

    public void mouseMoved(MouseEvent e) {
    }

    public void mouseClicked(MouseEvent e) {
    }

    public void mousePressed(MouseEvent e) {
        origin = new Point(e.getPoint());
    }

    public void mouseReleased(MouseEvent e) {

    }

    public void mouseEntered(MouseEvent e) {

    }

    public void mouseExited(MouseEvent e) {

    }

}