import javax.swing.*;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;
import java.awt.*;

public class ColumnEdit extends AbstractCellEditor implements TableCellEditor, TableCellRenderer {

    public ColumnEdit(JTable table, int column)
    {
        super();
        //设置该单元格渲染和编辑样式
        TableColumnModel columnModel = table.getColumnModel();
        columnModel.getColumn(column).setCellRenderer( this );
        columnModel.getColumn(column).setCellEditor( this );
    }

    private JButton getButton(JTable table, Object value, boolean isSelected, int row, int column) {
        if (value instanceof Integer){
            int i = (Integer)value;
            int height = new ImageIcon("./img/2.png").getIconHeight();

            if (i == 2){
                JButton button = new JButton(new ImageIcon("./img/2.png"));
                button.setPreferredSize(new Dimension(height, height));
                return button;
            }
            else if (i == 4) {
                JButton button = new JButton(new ImageIcon("./img/4.png"));
                button.setPreferredSize(new Dimension(height, height));
                return button;
            }
            else if (i == 8){
                JButton button = new JButton(new ImageIcon("./img/8.png"));
                button.setPreferredSize(new Dimension(height, height));
                return button;
            }
            else if (i == 16) {
                JButton button = new JButton(new ImageIcon("./img/16.png"));
                button.setPreferredSize(new Dimension(height, height));
                return button;
            }
            else if (i == 32){
                JButton button = new JButton(new ImageIcon("./img/32.png"));
                button.setPreferredSize(new Dimension(height, height));
                return button;
            }
            else if (i == 64) {
                JButton button = new JButton(new ImageIcon("./img/64.png"));
                button.setPreferredSize(new Dimension(height, height));
                return button;
            }
            else if (i == 128){
                JButton button = new JButton(new ImageIcon("./img/128.png"));
                button.setPreferredSize(new Dimension(height, height));
                return button;
            }
            else if (i == 256) {
                JButton button = new JButton(new ImageIcon("./img/256.png"));
                button.setPreferredSize(new Dimension(height, height));
                return button;
            }
            else if (i == 512){
                JButton button = new JButton(new ImageIcon("./img/512.png"));
                button.setPreferredSize(new Dimension(height, height));
                return button;
            }
            else if (i == 1024) {
                JButton button = new JButton(new ImageIcon("./img/1024.png"));
                button.setPreferredSize(new Dimension(height, height));
                return button;
            }
            else if (i == 2048){
                JButton button = new JButton(new ImageIcon("./img/2048.png"));
                button.setPreferredSize(new Dimension(height, height));
                return button;
            }
            else {
                JButton button = new JButton();
                button.setPreferredSize(new Dimension(height, height));
                return button;
            }
        }
        return null;
    }

    @Override
    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
       return getButton(table, value, isSelected, row, column);
    }

    @Override
    public Object getCellEditorValue() {
        return null;
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        return getButton(table, value, isSelected, row, column);
    }
}
