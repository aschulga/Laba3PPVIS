package View;

import Controller.GraphicsController;
import Controller.MyTheard;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Asus on 23.05.2017.
 */
public class MyFrame {

    private String title;
    private Dimension d;
    private JFrame frame = new JFrame();
    private double step = 0.1;
    private PaintPanel seeGraph;
    private JScrollPane jsp;

    private GraphicsController controller;

    JButton buildButton = new JButton("Построить график");

    JLabel createA = new JLabel("Введите a: ");
    JTextField textFieldA = new JTextField(5);
    JLabel createB = new JLabel("Введите b: ");
    JTextField textFieldB = new JTextField(5);
    JLabel createC = new JLabel("Введите c: ");
    JTextField textFieldC = new JTextField(5);
    JLabel createX = new JLabel("Введите диапазон переменной x: ");
    JTextField textFieldOneValue = new JTextField(5);
    JTextField textFieldTwoValue = new JTextField(5);

    JLabel labelPercent = new JLabel("100"+" %");


    Table table;


    public MyFrame(String title, Dimension d, GraphicsController controller) {
        this.title = title;
        this.d = d;
        this.controller = controller;
        table  = new Table(controller);

        seeGraph = new PaintPanel(controller,MyFrame.this);
        jsp = new JScrollPane(seeGraph);
        seeGraph.setPreferredSize(seeGraph.getInitialSize());
    }

    public void init() {
        frame.setTitle(title);
        frame.setSize(d);
        frame.setLayout(new BorderLayout());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.add(buildButton, BorderLayout.SOUTH);

        table.getJsp().setPreferredSize(new Dimension(500,200));
        frame.add(table.getJsp(),BorderLayout.WEST);

        jsp.setPreferredSize(new Dimension(700,700));
        frame.add(jsp, BorderLayout.EAST);

        buildButton.addActionListener(new buildButtonActionListener());

        JPanel operationsPanel = new JPanel();

        operationsPanel.setLayout(new GridBagLayout());

        operationsPanel.add(createA, new GridBagConstraints(0, 0, 1, 1, 1, 1,
                GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL, new Insets(2, 2, 2, 2), 0, 0));

        operationsPanel.add(textFieldA, new GridBagConstraints(1, 0, 1, 1, 1, 1,
                GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL, new Insets(2, 2, 2, 2), 0, 0));

        operationsPanel.add(createB, new GridBagConstraints(0, 1, 1, 1, 1, 1,
                GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL, new Insets(2, 2, 2, 2), 0, 0));

        operationsPanel.add(textFieldB, new GridBagConstraints(1, 1, 1, 1, 1, 1,
                GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL, new Insets(2, 2, 2, 2), 0, 0));

        operationsPanel.add(createC, new GridBagConstraints(2, 0, 1, 1, 1, 1,
                GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL, new Insets(2, 2, 2, 2), 0, 0));

        operationsPanel.add(textFieldC, new GridBagConstraints(3, 0, 1, 1, 1, 1,
                GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL, new Insets(2, 2, 2, 2), 0, 0));

        operationsPanel.add(createX, new GridBagConstraints(4, 0, 1, 1, 1, 1,
                GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL, new Insets(2, 2, 2, 2), 0, 0));

        operationsPanel.add(textFieldOneValue, new GridBagConstraints(5, 0, 1, 1, 1, 1,
                GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL, new Insets(2, 2, 2, 2), 0, 0));

        operationsPanel.add(textFieldTwoValue, new GridBagConstraints(6, 0, 1, 1, 1, 1,
                GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL, new Insets(2, 2, 2, 2), 0, 0));

        operationsPanel.add(labelPercent, new GridBagConstraints(6, 1, 1, 1, 1, 1,
                GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL, new Insets(2, 2, 2, 2), 0, 0));

        frame.add(operationsPanel, BorderLayout.BEFORE_FIRST_LINE);

        frame.setVisible(true);
        frame.pack();
    }

    public double getA() {
        return Double.parseDouble(textFieldA.getText());
    }

    public double getB() {
        return Double.parseDouble(textFieldB.getText());
    }

    public double getC() {
        return Double.parseDouble(textFieldC.getText());
    }

    public double getX1() {
        return Double.parseDouble(textFieldOneValue.getText());
    }

    public double getX2() {
        return Double.parseDouble(textFieldTwoValue.getText());
    }

    public double getStep() {
        return step;
    }

    public class buildButtonActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {

            if(textFieldA.getText().equals("")||textFieldB.getText().equals("")||textFieldC.getText().equals("")||
                    textFieldOneValue.getText().equals("")||textFieldTwoValue.getText().equals(""))

                JOptionPane.showMessageDialog(new Frame(), "Заполните все поля таблицы. Для продолжения работы нажмите \"ОК\"");

            else if(Double.parseDouble(textFieldOneValue.getText()) > Double.parseDouble(textFieldTwoValue.getText()))

                JOptionPane.showMessageDialog(new Frame(), "Введены некорректные данные. Для продолжения работы нажмите \"ОК\"");

            else {
                Thread t1 = new MyTheard(controller, MyFrame.this);
                t1.start();
            }

        }
    }

    public void generationTable()
    {
        table.addCoord(controller);
        frame.repaint();
    }
}
