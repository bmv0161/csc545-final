import java.sql.*;
import java.lang.Class;
import java.util.HashMap;
import java.lang.reflect.Field;
import java.util.Arrays;

public class Mapper {
    static String url = "jdbc:mysql://localhost/csc545";
    static String driver = "com.mysql.cj.jdbc.Driver";
    static String user = "root";
    static String password = "9>/]y4nNZg95?{s8";

    static HashMap<String, String> typesMap;
    static {
        typesMap = new HashMap<>();
        typesMap.put("String", "VARCHAR(255)");
        typesMap.put("boolean", "VARCHAR(255)");
        typesMap.put("int", "INTEGER");
        typesMap.put("double", "DECIMAL(10,2)");
        typesMap.put("String[]", "VARCHAR(255)");
        typesMap.put("ArrayList", "VARCHAR(255)");
        typesMap.put("Route", "VARCHAR(255)");
        typesMap.put("Ticket", "VARCHAR(255)");
        typesMap.put("Trip", "VARCHAR(255)");
    }

    public static void create(Table object) {
        try {
            Class.forName(driver);
            Connection cn = DriverManager.getConnection(url, user, password);
            Statement stm = cn.createStatement();
            String tableName = object.getName();
            String[] columns = object.getColumns();
            String qry = "DROP TABLE IF EXISTS " + tableName;
            stm.executeUpdate(qry);

            qry = "CREATE TABLE %s (" +
                    "%s";
            String colf = "%s %s, ";
            String cols = "";
            for(int i = 0; i < columns.length; i += 2) {
                cols = cols.concat(String.format(colf, columns[i], typesMap.get(columns[i+1])));
            }
            qry = String.format(qry, tableName, cols);

            if(columns[1].toUpperCase().contains("ID")) {
                qry = qry.concat(String.format("PRIMARY KEY(%s))", columns[1]));
            } else {
                qry = qry.substring(0, qry.length()-2) + ")";
            }
            stm.executeUpdate(qry);
        } catch (Exception x) {
            //System.err.println(object.getName());
            System.err.println("Table creation interrupted: " + x);
        }
    }

    public static void insert(Table record) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection cn = DriverManager.getConnection(url, user, password);
            Statement stm = cn.createStatement();
            String qry = "INSERT INTO %s VALUES (%s)";
            String tableName = record.getName();
            String[] values = record.getValues();

            String valf = "\'%s\', ";
            String vals = "";
            for(String x: values) {
                vals = vals.concat(String.format(valf, x));
            }

            vals = vals.substring(0, vals.length()-2);
            qry = String.format(qry, tableName, vals);
            //System.out.println(qry);
            stm.executeUpdate(qry);
        } catch(Exception x) {
            System.err.println("Insertion interrupted by: " + x);
        }
    }
    public static void insertMany(Table record) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection cn = DriverManager.getConnection(url, user, password);
            Statement stm = cn.createStatement();

            String tableName = record.getName();
            String valuestr = record.getValues()[0];
            valuestr = valuestr.substring(1, valuestr.length() -1);
            String[] values = valuestr.split(", ");
            String frmt = "INSERT INTO %s VALUES (%s)";
            String valf = "\'%s\'";

            for(String v: values) {
                String val = String.format(valf, v);
                String qry = String.format(frmt, tableName, val);
                //System.out.println(qry);
                stm.executeUpdate(qry);
            }
        } catch(Exception x) {
            System.err.println("Insertion interrupted by: " + x);
        }
    }
    public static void update(String table, String column, String condition) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection cn = DriverManager.getConnection(url, user, password);
            Statement stm = cn.createStatement();
            String qry = "UPDATE %s SET %s WHERE %s";

            qry = String.format(qry, table, column, condition);
            //System.out.println(qry);
            stm.executeUpdate(qry);
        } catch(Exception e) {
            System.out.println("update interrupted by: " + e);
        }
    }
    public static String[][] select(String table, String column, String condition) {
        String[][] results = new String[0][0];
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection cn = DriverManager.getConnection(url, user, password);
            Statement stm = cn.createStatement();
            String qry = String.format("SELECT %s FROM %s %s", column, table, condition);

            ResultSet rs = stm.executeQuery("SELECT count(*) from " + table.split(", ")[0]);
            rs.next();
            int numberOfRows = rs.getInt("count(*)");
            //System.out.println(numberOfRows);
            //System.out.println(qry);
            rs = stm.executeQuery(qry);
            ResultSetMetaData md = rs.getMetaData();
            int numberOfColumns = md.getColumnCount();

            results = new String[numberOfRows][numberOfColumns];
            int j = 0;
            while (rs.next()){
                for(int i = 1; i <= numberOfColumns; i++) {
                    results[j][i-1] = rs.getString(i);
                }
                j++;
            }
        } catch (Exception e) {
            System.out.println("select interrupted: " + e);
        }
        return results;
    }
    public static String[] select(String table, String column) {
        String[] results = {""};
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection cn = DriverManager.getConnection(url, user, password);
            Statement stm = cn.createStatement();
            String qry = String.format("SELECT %s FROM %s", column, table);

            ResultSet rs = stm.executeQuery(qry);
            ResultSetMetaData md = rs.getMetaData();
            int numberOfColumns = md.getColumnCount();

            results = new String [numberOfColumns];
            for(int i = 0; i<numberOfColumns; i++){
                results[i]="";
            }

            while (rs.next()){
                for(int i =1; i<=numberOfColumns; i++)
                    results[i-1]+=rs.getObject(i)+"\n";
            }
        } catch (Exception e) {
            System.out.println("select interrupted: " + e);
        }
        return results;
    }
}
