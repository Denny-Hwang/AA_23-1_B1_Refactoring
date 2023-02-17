import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Customer {
	private String name;

	private List<Rental> rentals = new ArrayList<Rental>();

	public Customer(String name) {
		this.setName(name);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Rental> getRentals() {
		return rentals;
	}

	public void setRentals(List<Rental> rentals) {
		this.rentals = rentals;
	}

	public void addRental(Rental rental) {
		rentals.add(rental);

	}

	private int calcDaysRented(Rental each) {
		if (each.getStatus() == 1) { // returned Video
			long diff = each.getReturnDate().getTime() - each.getRentDate().getTime();
			return (int) (diff / (1000 * 60 * 60 * 24)) + 1;
		} else { // not yet returned
			long diff = new Date().getTime() - each.getRentDate().getTime();
			return (int) (diff / (1000 * 60 * 60 * 24)) + 1;
		}
	}

	private double calcCharge(Rental each, int daysRented) {
		double eachCharge = 0;
		switch (getEachVideo(each).getPriceCode()) {
			case Video.REGULAR:
				eachCharge += 2;
				if (daysRented > 2)
					eachCharge += (daysRented - 2) * 1.5;
				break;
			case Video.NEW_RELEASE:
				eachCharge = daysRented * 3;
				break;
		}
		return eachCharge;
	}

	private int calcPoint(Rental each, int daysRented) {
		int eachPoint = 0;
		eachPoint++;

		if ((getEachVideo(each).getPriceCode() == Video.NEW_RELEASE) )
			eachPoint++;

		if ( daysRented > each.getDaysRentedLimit() )
			eachPoint -= Math.min(eachPoint, getEachVideo(each).getLateReturnPointPenalty()) ;

		return eachPoint;
	}

	private String printEachResult(Rental each, int daysRented, double eachCharge, int eachPoint) {
		return "\t" + each.getVideo().getTitle() + "\tDays rented: " + daysRented + "\tCharge: " + eachCharge
				+ "\tPoint: " + eachPoint + "\n";
	}

	private String printTotalResult (double totalCharge, int totalPoint) {
		return "Total charge: " + totalCharge + "\tTotal Point:" + totalPoint + "\n";
	}

	public String getReport() {
		String result = "Customer Report for " + getName() + "\n";

		List<Rental> rentals = getRentals();

		double totalCharge = 0;
		int totalPoint = 0;

		for (Rental each : rentals) {

			int daysRented = calcDaysRented(each);
			double eachCharge = calcCharge(each, daysRented);
			int eachPoint = calcPoint(each, daysRented);
			
			result += printEachResult(each, daysRented, eachCharge, eachPoint);

			totalCharge += eachCharge;

			totalPoint += eachPoint ;
		}

		result += printTotalResult (totalCharge, totalPoint);

		if ( totalPoint >= 10 ) {
			System.out.println("Congrat! You earned one free coupon");
		}
		
		return result ;
	}
	
	private static Video getEachVideo(Rental each) {
		return each.getVideo();
	}
}
