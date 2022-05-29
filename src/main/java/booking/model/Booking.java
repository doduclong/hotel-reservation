package booking.model;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
@Entity
public class Booking {
	@Id
	@GeneratedValue
	private long id;
	//private String bookingID;
	//@DateTimeFormat(pattern = "yyyy-MM-dd")
	//private LocalDate checkin;
	
	//@DateTimeFormat(pattern = "yyyy-MM-dd")
	//private LocalDate checkout;
	//private String checkin;
	//private String checkout;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date checkin, checkout;
	
	
	private long hotel_id;
	private long user_id;
	private long room_id;
	private String hotel_name;
	private String room_name;
	private String username;
	
	//private User user;
	//private Hotel hotel;
	//private boolean state;
	//private Set<Room> rooms = new HashSet<Room>();
	//private String userID, clientID;
	private long total;
	
}
