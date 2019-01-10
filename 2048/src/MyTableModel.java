import javax.swing.table.AbstractTableModel;

public class MyTableModel extends AbstractTableModel {

    Integer[][] data;

    public MyTableModel(Integer[][] data){
        this.data = data;
    }

    public Integer[][] getData() {
        return data;
    }

    @Override
    public int getRowCount() {
        return data.length;
    }

    @Override
    public int getColumnCount() {
        return data.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        return data[rowIndex][columnIndex];
    }
}
