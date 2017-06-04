package View;

import Model.Coordinates;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;


public class ModelTable extends AbstractTableModel {

    private static final int columnCount = 2;
    private ArrayList<Coordinates> list;

    public  ModelTable()
    {
        list = new ArrayList<>();
    }

    public String getColumnName(int columnIndex)
    {
        switch (columnIndex) {
            case 0: return "x";
            case 1: return "F(x)";
        }
        return  "";
    }


    @Override
    public int getRowCount() {
        return list.size();
    }

    @Override
    public int getColumnCount() {
        return columnCount;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Coordinates rows = list.get(rowIndex);
        return rows.getValue(columnIndex);
    }

    public void add(Coordinates coord)
    {
        list.add(coord);
        fireTableDataChanged();
    }

    public void deleteAll()
    {
        list.clear();
        fireTableDataChanged();
    }

}
