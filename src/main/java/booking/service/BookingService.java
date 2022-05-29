package booking.service;

import java.util.Collection;

import booking.model.Booking;


public interface BookingService {
	public Collection<Booking> getReservationsForLoggedUser();
}
