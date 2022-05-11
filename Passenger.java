public class Passenger extends Table{
    private int id;
    private int age;
    private boolean disability;
    private Trip trip;
    private Ticket ticket;

    public Passenger() {}
    public Passenger(int age, boolean dis, Trip trip) {
        this.age = age;
        this.disability = dis;
        this.trip = trip;
        purchaseTicket();
    }
    public void setId(int id) {
        this.id = id;
    }
    public void setAge(int age) {
        this.age = age;
    }
    public void setDisability(boolean dis) {
        this.disability = dis;
    }
    public void setTrip(Trip trip) {
        trip.setId(id);
        this.trip = trip;
        purchaseTicket();
    }
    public int getId() {
        return id;
    }
    public Trip getTrip() {
        return trip;
    }
    public Ticket getTicket() {
        return ticket;
    }
    public void purchaseTicket() {
        ticket = new Ticket(trip.getDate(), age <= 12 || disability, this);
    }
    public String toString() {
        return String.format("Passenger %d:: age: %d, dis: %b\n\t%s\n\t%s\n",
                id, age, disability,trip, ticket);
    }
}
