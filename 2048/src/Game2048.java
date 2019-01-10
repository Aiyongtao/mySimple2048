import javax.swing.*;
import javax.swing.event.TableModelListener;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableModel;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.Timer;
import java.util.TimerTask;

public class Game2048 {

    static boolean change = true;
    static int count = 0;
    static int startUnChange = 0;

    public static void main(String[] args) throws IOException {
        long l = 9007199254740992l;
        int i = 0;
        while (l != 0) {
            System.out.println("i : " + i + " , value = " + l);
            l = l >> 1;
            i++;
        }
    }

    JFrame mainFrame;
    Integer[][] data;
    JTable table;

    public Game2048(){
        mainFrame = new JFrame("2048");
        data = new Integer[4][4];
        for (int i = 0; i < data.length; i++)
            for (int j = 0; j < data.length; j++)
                data[i][j] = 0;

        int x = (int)(Math.random() * 100) % data.length;
        int y = (int)(Math.random() * 100) % data.length;

        data[x][y] = 2;

        int width = new ImageIcon("./img/2.png").getIconWidth();
        int height = new ImageIcon("./img/2.png").getIconHeight();

        table = new JTable();
        table.setModel(new MyTableModel(data));
        ColumnEdit column1 = new ColumnEdit(table, 1);
        ColumnEdit column2 = new ColumnEdit(table, 2);
        ColumnEdit column3 = new ColumnEdit(table, 3);
        ColumnEdit column4 = new ColumnEdit(table, 0);

        table.setRowHeight(height);
        table.setVisible(true);
        table.setEnabled(false);
        mainFrame.add(table);
        mainFrame.pack();
        mainFrame.setVisible(true);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                super.keyPressed(e);
                //getKeyCode 37,38,39,40 左上右下
                process(e.getKeyCode());
            }
        });


    }

    public void process(int code){
        switch (code){
            case 37:{
                pressLeft();
                break;
            }
            case 38:{
                pressUp();
                break;
            }
            case 39:{
                pressRight();
                break;
            }
            case 40:{
                pressDown();
                break;
            }
            default:
                break;
        }
        table.repaint();

        for (int i = 0; i < data.length; i++)
            for (int j = 0; j < data.length; j++) {
                if (data[i][j] == 2048)
                {
                    System.out.println("success");
                    System.exit(0);
                }

            }

        boolean fail = true;
        for (int i = 0; i < data.length; i++) {
            for (int j = 0; j < data.length; j++) {
                int val = data[i][j];
                if (val == 0) fail = false;
                if (i-1 >= 0){
                    fail = (fail && (val != data[i-1][j]));
                }
                if (i+1 <= data.length -1) {
                    fail = (fail && (val != data[i+1][j]));
                }
                if (j-1 >= 0){
                    fail = (fail && (val != data[i][j-1]));
                }
                if (j+1 <= data.length -1) {
                    fail = (fail && (val != data[i][j+1]));
                }
            }
            if (!fail) break;
        }


        if (fail) {
            System.out.println("fail");
            System.exit(0);
        }
    }

    public void pressDown(){
        boolean hasChanged = false;
        for (int j = 0; j < data.length; j++) {
            int pos = data.length - 1;
            int recent = -1;
            for (int i = data.length - 1; i >= 0; i--) {
                if (data[i][j] != 0) {
                    if (recent != data[i][j]) {
                        data[pos--][j] = data[i][j];
                        if (pos+1 != i) {
                            hasChanged = true;
                            data[i][j] = 0;
                        }
                        recent = data[pos+1][j];
                    }
                    else {
                        hasChanged = true;
                        data[pos+1][j] *= 2;
                        data[i][j] = 0;
                        recent = -1;
                    }
                }
            }
        }
        if (!hasChanged) {
            change = false;
            return ;
        }

        int random = (int)(Math.random() * 100) % data.length;
        int value = Math.random() > 0.8 ? 4 : 2;
        for (int i = 0; i < data.length; i++) {
            if (data[0][(i+random) % data.length] == 0) {
                data[0][(i+random) % data.length] = value;
                break;
            }
        }
        change = true;
    }

    public void pressUp(){
        boolean hasChanged = false;
        for (int j = 0; j < data.length; j++) {
            int pos = 0;
            int recent = -1;
            for (int i = 0; i <= data.length - 1; i++) {
                if (data[i][j] != 0) {
                    if (recent != data[i][j]) {
                        data[pos++][j] = data[i][j];
                        if (pos-1 != i) {
                            hasChanged = true;
                            data[i][j] = 0;
                        }
                        recent = data[pos-1][j];
                    }
                    else {
                        hasChanged = true;
                        data[pos-1][j] *= 2;
                        data[i][j] = 0;
                        recent = -1;
                    }
                }
            }
        }
        if (!hasChanged){
            change = false;
            return;
        }
        int random = (int)(Math.random() * 100) % data.length;
        int value = Math.random() > 0.8 ? 4 : 2;
        for (int i = 0; i < data.length; i++) {
            if (data[data.length - 1][(i+random) % data.length] == 0) {
                data[data.length - 1][(i+random) % data.length] = value;
                break;
            }
        }
        change = true;
    }

    public void pressLeft(){
        boolean hasChanged = false;
        for (int i = 0; i < data.length; i++) {
            int pos = 0;
            int recent = -1;
            for (int j = 0; j <= data.length - 1; j++) {
                if (data[i][j] != 0) {
                    if (recent != data[i][j]) {
                        data[i][pos++] = data[i][j];
                        if (pos-1 != j) {
                            data[i][j] = 0;
                            hasChanged = true;
                        }

                        recent = data[i][pos-1];
                    }
                    else {
                        hasChanged = true;
                        data[i][pos-1] *= 2;
                        data[i][j] = 0;
                        recent = -1;
                    }
                }
            }
        }
        if (!hasChanged){
            change = false;
            return;
        }
        int random = (int)(Math.random() * 100) % data.length;
        int value = Math.random() > 0.8 ? 4 : 2;
        for (int i = 0; i < data.length; i++) {
            if (data[(i+random) % data.length][data.length - 1] == 0) {
                data[(i+random) % data.length][data.length - 1] = value;
                break;
            }
        }
        change = true;
    }

    public void pressRight(){
        boolean hasChanged = false;
        for (int i = 0; i < data.length; i++) {
            int pos = data.length - 1;
            int recent = -1;
            for (int j = data.length - 1; j >= 0; j--) {
                if (data[i][j] != 0) {
                    if (recent != data[i][j]) {
                        data[i][pos--] = data[i][j];
                        if (pos+1 != j) {
                            data[i][j] = 0;
                            hasChanged = true;
                        }
                        recent = data[i][pos+1];
                    }
                    else {
                        hasChanged = true;
                        data[i][pos+1] *= 2;
                        data[i][j] = 0;
                        recent = -1;
                    }
                }
            }
        }
        if (!hasChanged){
            change = false;
            return;
        }
        int random = (int)(Math.random() * 100) % data.length;
        int value = Math.random() > 0.8 ? 4 : 2;
        for (int i = 0; i < data.length; i++) {
            if (data[(i+random) % data.length][0] == 0) {
                data[(i+random) % data.length][0] = value;
                break;
            }
        }
        change = true;
    }

}

