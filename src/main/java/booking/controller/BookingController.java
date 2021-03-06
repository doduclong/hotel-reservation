package booking.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import booking.model.Booking;
import booking.model.Hotel;
import booking.model.Room;
import booking.model.User;
import booking.repository.BookingRepository;
import booking.repository.HotelRepository;
import booking.repository.RoomRepository;
import booking.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/")
@Slf4j
public class BookingController {
	@Autowired
	private HotelRepository hotelRepo;
	
	@Autowired
	private RoomRepository roomRepo;
	
	@Autowired
	private BookingRepository bookingRepo;
	
	@Autowired
	private UserRepository userRepo;
	
	
//	@GetMapping
//	public String getMainPage() {
//		return "room/room-list";
//	}
	
	@GetMapping("/booking/form")
	public String getBookingForm(Booking booking, Model model) {
		model.addAttribute("listHotel", hotelRepo.findAll());
		model.addAttribute("listRoom", roomRepo.findAll());
		return "booking/booking-add";
	}
	
	@GetMapping("/booking/update")
	public String getUpdateForm(@RequestParam("id")long id , Model model) {
		model.addAttribute("booking", bookingRepo.findById(id));
		return "booking/booking-update";
	}
	
	@GetMapping("/booking/delete")
	public String getDeleteForm(@RequestParam("id")long id , Model model) {
		model.addAttribute("booking", bookingRepo.findById(id).get());
		return "booking/booking-delete";
	}
	
	@GetMapping("/booking/all")
	public String getAllBooking(Model model) {
		model.addAttribute("list", bookingRepo.findAll());
		return "booking/booking-list";
	}
	
	
	@PostMapping("/booking/add")
	public String addBooking(@RequestParam("hotel_id")long hotel_id, @RequestParam("room_id")long room_id,@RequestParam("user_id")long user_id,@RequestParam("checkin")String checkin,@RequestParam("checkout")String checkout,Room room, Hotel hotel,User user, Booking booking) throws ParseException {
		hotel = hotelRepo.findById(hotel_id).get();
		booking.setHotel_name(hotel.getName());
		
		room = roomRepo.findById(room_id).get();
		booking.setRoom_name(room.getName());
		
		user = userRepo.findById(user_id).get();
		booking.setUsername(user.getUsername());
		
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");

        Calendar c1 = Calendar.getInstance();
        Calendar c2 = Calendar.getInstance();

        // ?????nh ngh??a 2 m???c th???i gian ban ?????u
//        Date date1 = Date.valueOf(checkin);
//        Date date2 = Date.valueOf(checkout);
        Date date1 = dateFormat.parse(checkin);
        Date date2 = dateFormat.parse(checkout);

        c1.setTime(date1);
        c2.setTime(date2);

        // C??ng th???c t??nh s??? ng??y gi???a 2 m???c th???i gian:
        long noDay = (c2.getTime().getTime() - c1.getTime().getTime()) / (24 * 3600 * 1000 * 366);
		booking.setTotal(noDay*room.getPrice()/20);
		bookingRepo.save(booking);
		return "redirect:/booking/all";
	}
	
	@PostMapping("/booking/update")
	public String updateBooking(@RequestParam("hotel_id")long hotel_id, @RequestParam("room_id")long room_id,@RequestParam("user_id")long user_id,@RequestParam("checkin")String checkin,@RequestParam("checkout")String checkout,Room room, Hotel hotel,User user,Booking booking) throws ParseException {
		
		hotel = hotelRepo.findById(hotel_id).get();
		booking.setHotel_name(hotel.getName());
		
		room = roomRepo.findById(room_id).get();
		booking.setRoom_name(room.getName());
		
		user = userRepo.findById(user_id).get();
		booking.setUsername(user.getUsername());
		
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");

        Calendar c1 = Calendar.getInstance();
        Calendar c2 = Calendar.getInstance();

        // ?????nh ngh??a 2 m???c th???i gian ban ?????u
//        Date date1 = Date.valueOf(checkin);
//        Date date2 = Date.valueOf(checkout);
        Date date1 = dateFormat.parse(checkin);
        Date date2 = dateFormat.parse(checkout);

        c1.setTime(date1);
        c2.setTime(date2);

        // C??ng th???c t??nh s??? ng??y gi???a 2 m???c th???i gian:
        long noDay = (c2.getTime().getTime() - c1.getTime().getTime()) / (24 * 3600 * 1000 * 366);
		booking.setTotal(noDay*room.getPrice()/20);
		bookingRepo.save(booking);
		return "redirect:/booking/all";
	}
	
	@PostMapping("/booking/delete")
	public String deleteBooking(@RequestParam("id")long id) {
		bookingRepo.deleteById(id);
		return "redirect:/booking/all";
	}
}
