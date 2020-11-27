/**
 * Copyright (c) 2018-2020 ixiancheng.com All Rights Reserved.
 */
package com.algorithm.sha.awt;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.math.BigDecimal;

/**
 * calc
 *
 * @author shazhihong
 * @date 2020/11/27
 */
public class Calc {

    public static void main(String[] args) {
        new MyFrame("计算器");
    }

}

class MyFrame extends Frame {
    private static final long serialVersionUID = 1L;
    private TextArea ta, tb;

    public MyFrame(String title) {
        super(title);
        SetTextAreas();
        SetButtonArea();
        SetMainFrame();
    }

    private void SetButtonArea() {

        addButton("7", 33, 28, 20, 178);
        addButton("8", 33, 28, 58, 178);
        addButton("9", 33, 28, 96, 178);
        addButton("/", 33, 28, 134, 178);
        addButton("%", 33, 28, 172, 178);

        addButton("4", 33, 28, 20, 210);
        addButton("5", 33, 28, 58, 210);
        addButton("6", 33, 28, 96, 210);
        addButton("*", 33, 28, 134, 210);
        addButton("1/x", 33, 28, 172, 210);

        addButton("1", 33, 28, 20, 242);
        addButton("2", 33, 28, 58, 242);
        addButton("3", 33, 28, 96, 242);
        addButton("-", 33, 28, 134, 242);

        addButton("0", 71, 28, 20, 274);
        addButton(".", 33, 28, 96, 274);
        addButton("+", 33, 28, 134, 274);

        addButton("=", 33, 60, 172, 242);
    }

    BigDecimal m, n;
    String k;
    boolean flag = true;
    boolean flag2 = false;

    private void addButton(String string, int i, int j, int x, int y) {
        final Button b = new Button(string);
        b.setLocation(x, y);
        b.setSize(i, j);
        b.setFont(new Font("标楷体", Font.BOLD, 15));
        b.setBackground(Color.pink);
        b.setForeground(Color.darkGray);
        b.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                counts();
            }

            private void counts() {
                tb.append(!"".equals(b.getActionCommand()) ? b.getActionCommand() : ta.getText());

                if (b.getActionCommand().equals("+") ||
                        b.getActionCommand().equals("-") ||
                        b.getActionCommand().equals("*") ||
                        b.getActionCommand().equals("%") ||
                        b.getActionCommand().equals("1/x") ||
                        b.getActionCommand().equals("/")) {
                    flag = flag && false;
                    k = b.getActionCommand();
                    ta.setText("");
                } else if (b.getActionCommand().equals("=")) {
                    String tt = tb.getText();
                    tb.setText("");
                    ta.setText("");
                    ta.append(tt);
                    ta.append(System.getProperty("line.separator"));
                    n = NifToSufAndCalc.count(tt);
                    ta.append("=" + n);
                    k = "=";
                    flag2 = true;
                } else {
                    if (flag2) {
                        flag = true;
                        flag2 = false;
                        ta.setText("");
                        m = n = BigDecimal.ZERO;
                    }
                    ta.append(b.getActionCommand());
                }
            }
        });
        this.add(b);
    }

    private void SetTextAreas() {
        ta = new TextArea("", 8, 52, 3);
        ta.setBackground(Color.lightGray);
        ta.setSize(190, 50);
        ta.setFont(new Font("标楷体", Font.BOLD, 15));
        ta.setLocation(20, 60);
        this.add(ta);
        tb = new TextArea("", 8, 52, 3);
        tb.setBackground(Color.pink);
        tb.setSize(190, 25);
        tb.setFont(new Font("标楷体", Font.BOLD, 16));
        tb.setForeground(Color.blue);
        tb.setLocation(20, 130);
        this.add(ta);
        this.add(tb);
    }

    @SuppressWarnings("deprecation")
    private void SetMainFrame() {
        this.setLayout(null);
        this.setSize(220, 310);
        this.setVisible(true);
        this.setLocation(310, 340);
        this.setResizable(false);
        ta.setEditable(false);
        tb.setEditable(false);
        this.setCursor(Cursor.HAND_CURSOR);
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });

    }
}