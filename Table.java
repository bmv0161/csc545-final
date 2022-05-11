import java.lang.reflect.Field;
import java.lang.IllegalAccessException;

public class Table {
    public String getName() {
        return this.getClass().getSimpleName();
    }
    public String[] getColumns() {
        Field[] fields1 = getFields();
        int len = fields1.length;
        if(getName().equals("Passenger")) { len -= 2; }
        String[] columns = new String[len * 2];
        try {
            for(int i = 0, j = 0; i < fields1.length; i++, j += 2) {
                Field x = fields1[i];
                x.setAccessible(true);
                Class t = x.getType();
                Object o = x.get(this);
                if(!t.getSimpleName().equals("Ticket") && !t.getSimpleName().equals("Trip")) {
                    columns[j] = x.getName();
                    columns[j +1] = t.getSimpleName();
                } else {
                    j -= 2;
                }
            }
        } catch (Exception e){
            System.err.println("GetColumns: " + e);
        }

        return columns;
    }
    public Field[] getFields() {
        return this.getClass().getDeclaredFields();
    }
    public String[] getValues() {
        Field[] fields = getFields();
        int len = fields.length;
        if(getName().equals("Passenger")) {
            len -= 2;
        }
        String[] values = new String[len];
        try {
            for (int i = 0; i < values.length; i++) {
                Field x = fields[i];
                x.setAccessible(true);
                Class t = x.getType();
                Object o = x.get(this);
                if(!t.getSimpleName().equals("Ticket") && !t.getSimpleName().equals("Trip")) {
                    values[i] = o.toString();
                }
            }
        } catch (Exception e) {
            System.err.println("getValues interrupted: " + e);
        }
        return values;
    }
}
