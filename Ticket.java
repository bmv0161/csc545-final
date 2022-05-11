public class Ticket extends Table{
    private int id;
    private String dateOfPurchase;
    private boolean legible;
    private boolean discount;
    private double fare;

    public Ticket() {}
    public Ticket(String date, boolean dis, Passenger p) {
        this.dateOfPurchase = date;
        this.discount = dis;
        this.id = p.getId();
        vend(p);
    }
    public void vend(Passenger p) {
        legible = validateTrip(p.getTrip());
        if(legible) {
            calculateFare();
        } else {
            fare = -1;
        }
    }
    public void calculateFare() {
        if(discount) {
            fare = 4.95 * .75;
        } else {
            fare =  4.95;
        }
    }
    public boolean validateTrip(Trip trip) {
        if(Route.contains(trip.getOrigin()) && Route.contains(trip.getDestination())) {
            calculateFare();
            return true;
        }
        return false;
    }
    public String toString() {
        return String.format("Ticket %d:: legible: %s, fare: %f", id, legible, fare);
    }
}
