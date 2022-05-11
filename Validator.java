import java.io.File;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.Scanner;
import java.util.ArrayList;

public class Validator {
    ArrayList<Passenger> records;
    Route route;
    public Validator() {
        init();

        readRoute();
        readPassengers();
        Mapper.insertMany(route);

        for(Passenger x: records) {
            Mapper.insert(x);
            Mapper.insert(x.getTrip());
            Mapper.insert(x.getTicket());
        }
    }
    public void init() {
        Mapper.create(new Passenger());
        Mapper.create(new Route());
        Mapper.create(new Ticket());
        Mapper.create(new Trip());
    }
    public String printRecords() {
        String str = "";
        for(Passenger x: records) {
            str = str.concat(x.toString());
        }
        return str;
    }
    public void readRoute() {
        route = new Route();
        try {
            File f = new File("./route");
            Scanner read = new Scanner(f);
            while (read.hasNextLine()) {
                String data = read.nextLine();
                route.add(data);
            }
            read.close();
        } catch(FileNotFoundException e) {
            e.printStackTrace();
        }
    }
    public void readPassengers() {
        records = new ArrayList<>();
        try {
            File f = new File("./passengers");
            Scanner read = new Scanner(f);
            int i = 0;
            Passenger p = new Passenger();
            Trip t = new Trip();
            while (read.hasNextLine()) {
                String data = read.nextLine();
                switch(i % 6){
                    case 0:
                        p = new Passenger();
                        p.setId(Integer.parseInt(data));
                        break;
                    case 1:
                        p.setAge(Integer.parseInt(data));
                        break;
                    case 2:
                        p.setDisability(Boolean.parseBoolean(data));
                        break;
                    case 3:
                        t = new Trip();
                        t.setOrigin(data);
                        break;
                    case 4:
                        t.setDestination(data);
                        break;
                    case 5:
                        t.setDate(data);
                        p.setTrip(t);
                        records.add(p);
                        break;
                    default:
                        break;
                }
                i++;
            }

            read.close();
        } catch(FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
