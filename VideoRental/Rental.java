import java.util.Date;

public class Rental {
	private Video video ;
	private int status ; // 0 for Rented, 1 for Returned
	public static final int STATUS_RENTED = 0;
	public static final int STATUS_RETURNED = 1;
	private Date rentDate ;
	private Date returnDate ;

	public Rental(Video video) {
		this.video = video ;
		status = STATUS_RENTED ;
		rentDate = new Date() ;
	}

	public Video getVideo() {
		return video;
	}

	public void setVideo(Video video) {
		this.video = video;
	}

	public int getStatus() {
		return status;
	}

	public void returnVideo() {
		if ( status == STATUS_RETURNED ) {
			this.status = STATUS_RETURNED;
			returnDate = new Date() ;
		}
	}
	public Date getRentDate() {
		return rentDate;
	}

	public void setRentDate(Date rentDate) {
		this.rentDate = rentDate;
	}

	public Date getReturnDate() {
		return returnDate;
	}

	public int getDaysRentedLimit() {
		int limit = 0 ;
		int daysRented ;
		long diff;

		if (getStatus() == STATUS_RETURNED) {
			diff = returnDate.getTime() - rentDate.getTime();
// 			daysRented = (int) (diff / (1000 * 60 * 60 * 24)) + 1;
		} else { // not yet returned
			diff = new Date().getTime() - rentDate.getTime();
// 			daysRented = (int) (diff / (1000 * 60 * 60 * 24)) + 1;
		}
		daysRented = (int) (diff / (1000 * 60 * 60 * 24)) + 1;
		
		if ( daysRented <= 2) return limit ;

		switch ( video.getVideoType() ) {
			case Video.VHS: limit = 5 ; break ;
			case Video.CD: limit = 3 ; break ;
			case Video.DVD: limit = 2 ; break ;
		}
		return limit ;
	}
}
