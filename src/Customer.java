import java.util.LinkedList;
import java.util.List;

class Customer {
    private String name;
    private List<Rental> rentals;

    public Customer(String name) {
        this.name = name;
        this.rentals = new LinkedList<Rental>();
    }

    public void addRental(Rental rental) {
        rentals.add(rental);
    }

    public String getName() {
        return name;
    }

    public String statement() {
        double totalAmount = 0;
        int totalPoints = 0;
        String result = "Rental Record for " + getName() + "\n";
        for (Rental rental : rentals) {
            result += "\t" + rental.getMovie().getTitle() + "\t"
                    + String.valueOf(rental.getPrice()) + "\n";
            totalAmount += rental.getPrice();
            totalPoints += getPointsFor(rental);
        }
        // add footer lines
        result += "Amount owed is " + String.valueOf(totalAmount) + "\n";
        result += "You earned " + String.valueOf(totalPoints)
                + " frequent renter points";
        return result;

    }

    private int getPointsFor(Rental rental) {
        int points = 1;
        // add bonus for a two day new release rental
        if ((rental.getMovie().getPriceCode() == Movie.NEW_RELEASE)
                && rental.getDaysRented() > 1)
            points++;
        return points;
    }
}
