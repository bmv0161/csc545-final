public class Trip extends Table{
    private int id;
    private String origin;
    private String destination;
    private String date;

    public Trip() {}
    public Trip(String o, String dest, String date) {
        this.origin = o;
        this.destination = dest;
        this.date = date;
    }
    public String getDate() {
        return date;
    }
    public String getDestination() {
        return destination;
    }
    public String getOrigin() {
        return origin;
    }
    public void setOrigin(String o) {
        origin = o;
    }
    public void setDestination(String d) {
        destination = d;
    }
    public void setDate(String d) {
        date = d;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String toString() {
        return String.format("Trip %d:: origin: %s, dest: %s, date: %s", id, origin, destination, date);
    }
}
