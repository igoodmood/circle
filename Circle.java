package com.company;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.*;

public class Circle extends JFrame {

    private boolean sorter = false;
    private Squares squares;
    private JButton sort;
    static Integer[] array = new Integer[15];
    public Circle() {
        super("Squaretester");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        squares = new Squares();
        this.setLayout(new BorderLayout());
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
        buttonPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
        JButton generate = new JButton("Сгенерировать");
        ActionListener actionListener = new TestActionListener();
        generate.addActionListener(actionListener);
        sort = new JButton("Сортировать");
        ActionListener actionListener2 = new TestActionListener2();
        sort.addActionListener(actionListener2);
        buttonPanel.add(generate);
        buttonPanel.add(sort);
        this.add(buttonPanel, BorderLayout.SOUTH);
        getContentPane().add(squares);
        generator();
        }


    public static void main(String[] args) {
        new Circle();
    }

    public class TestActionListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            try {
                squares.clearLastShape();
                generator();
                repaint();
                sort.setEnabled(true);
            }catch (Exception ex)
            {
                System.out.println(ex);
            }
        }
    }

    public class TestActionListener2 implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            try {
                squares.clearLastShape();
                sorter = true;
                sorterator();
                repaint();
                sorter = false;
                sort.setEnabled(false);
            }catch (Exception ex)
            {
                System.out.println(ex);
            }
        }
    }

    public void sorterator()
    {
        try {
            boolean changes;
            do {
                changes = false;
                for (int i = 0; i < array.length - 1; i++) {
                    if (array[i] > array[i + 1]) {
                        int _current = array[i];
                        array[i] = array[i + 1];
                        array[i + 1] = _current;
                        changes = true;
                    }
                }
            }
            while (changes);

            for (int i = 0; i < 15; i++) {
                squares.addSquare(i * 64, 10, 50, array[i]);
            }
        }catch (Exception ex)
        {
            System.out.println(ex);
        }
    }

    public void generator()
    {
        try {
            for (int i = 0; i < 15; i++) {
                int height = (int) ((int) 10 + (Math.random() * (100 - 10)));
                array[i] = height;
            }

            for (int i = 0; i < 15; i++) {
                squares.addSquare(i * 64, 10, 50, array[i]);
            }

            pack();
            setLocationRelativeTo(null);
            setVisible(true);
        } catch (Exception ex)
        {
            System.out.println(ex);
        }
    }
}

class Squares extends JPanel {
    Graphics2D g2;
    private static final int PREF_W = 950;
    private static final int PREF_H = 110;
    private List<Rectangle> squares = new ArrayList<Rectangle>();
    public void addSquare(int x, int y, int width, int height) {
        Rectangle rect = new Rectangle(x, y, width, height);
        squares.add(rect);
    }

    public void clearLastShape()    //pop
    {
        try {
            int size = squares.size();
            while (size > 0) {
                squares.remove(size - 1);
                size--;
            }
            repaint();
        } catch (Exception ex)
        {
            System.out.println(ex);
        }
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(PREF_W, PREF_H);
    }

    @Override
    public void paintComponent(Graphics g) {
        try {
            super.paintComponent(g);
            g2 = (Graphics2D) g;
            for (Rectangle rect : squares) {
                int r, gi, b;
                r = (int) ((int) 0 + (Math.random() * (255 - 0)));
                gi = (int) ((int) 0 + (Math.random() * (255 - 0)));
                b = (int) ((int) 0 + (Math.random() * (255 - 0)));
                g2.setColor(new Color(r, gi, b));
                g2.fill(rect);
                g2.draw(rect);
            }
        } catch (Exception ex)
        {
            System.out.println(ex);
        }
    }
}
