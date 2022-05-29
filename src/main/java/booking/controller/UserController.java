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
import booking.service.BookingService;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/")
@Slf4j
public class UserController {
	@Autowired
	private HotelRepository hotelRepo;
	
	@Autowired
	private RoomRepository roomRepo;
	
	@Autowired
	private BookingRepository bookingRepo;
	
	@Autowired
	private UserRepository userRepo;
	
	//private BookingService bookingService;
	
	@GetMapping("/user")
	public String getHomeUser(Model model) {
		return "user/home_user";
	}

	
	@GetMapping("/user/booking/form")
	public String getBookingForm(Booking booking, Model model) {
		model.addAttribute("listHotel", hotelRepo.findAll());
		model.addAttribute("listRoom", roomRepo.findAll());
		return "user/booking-user-add";
	}
	
	@GetMapping("/user/booking/update")
	public String getUpdateForm(@RequestParam("id")long id , Model model) {
		model.addAttribute("booking", bookingRepo.findById(id));
		return "booking/booking-update";
	}
	
	@GetMapping("/user/booking/delete")
	public String getDeleteForm(@RequestParam("id")long id , Model model) {
		model.addAttribute("booking", bookingRepo.findById(id).get());
		return "booking/booking-delete";
	}
	
	@GetMapping("/user/room/all")
	public String getAllRoom(Model model) {
		model.addAttribute("list", roomRepo.findAll());
		return "user/room-list";
	}
	
	@GetMapping("/user/hotel/all")
	public String getAllHotel(Model model) {
		model.addAttribute("list", hotelRepo.findAll());
		return "user/hotel-list";
	}
	
	
	 @GetMapping("/user/booking/all") public String getAllBooking(Model model) {
	 model.addAttribute("list", bookingRepo.findAll()); return
	 "user/booking-list"; }
	 
	
//	@GetMapping("/user/booking/all")
//	public String getAllBooking(Model model) {
//		model.addAttribute("list", bookingService.getReservationsForLoggedUser());
//		return "user/booking-list";
//	}
	
	
	@PostMapping("/user/booking/add")
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

        // Định nghĩa 2 mốc thời gian ban đầu
//        Date date1 = Date.valueOf(checkin);
//        Date date2 = Date.valueOf(checkout);
        Date date1 = dateFormat.parse(checkin);
        Date date2 = dateFormat.parse(checkout);

        c1.setTime(date1);
        c2.setTime(date2);

        // Công thức tính số ngày giữa 2 mốc thời gian:
        long noDay = (c2.getTime().getTime() - c1.getTime().getTime()) / (24 * 3600 * 1000 * 366);
		booking.setTotal(noDay*room.getPrice()/20);
		
		bookingRepo.save(booking);
		return "redirect:/user/booking/all";
	}
	
	@PostMapping("/user/booking/update")
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

        // Định nghĩa 2 mốc thời gian ban đầu
//        Date date1 = Date.valueOf(checkin);
//        Date date2 = Date.valueOf(checkout);
        Date date1 = dateFormat.parse(checkin);
        Date date2 = dateFormat.parse(checkout);

        c1.setTime(date1);
        c2.setTime(date2);

        // Công thức tính số ngày giữa 2 mốc thời gian:
        long noDay = (c2.getTime().getTime() - c1.getTime().getTime()) / (24 * 3600 * 1000 * 366);
		booking.setTotal(noDay*room.getPrice()/20);
		
		bookingRepo.save(booking);
		return "redirect:/user/booking/all";
	}
	
	@PostMapping("/user/booking/delete")
	public String deleteBooking(@RequestParam("id")long id) {
		bookingRepo.deleteById(id);
		return "redirect:/user/booking/all";
	}
	
	@GetMapping("/user/form")
	public String getUserForm(User user) {
		return "user/user-add";
	}
	
	@GetMapping("/user/update")
	public String getUpdateFormUser(@RequestParam("id")long id , Model model) {
		model.addAttribute("user", userRepo.findById(id));
		return "user/user-update";
	}
	
	@GetMapping("/user/delete")
	public String getDeleteFormUser(@RequestParam("id")long id , Model model) {
		model.addAttribute("user", userRepo.findById(id).get());
		return "user/user-delete";
	}
	
	@GetMapping("/user/all")
	public String getAlluser(Model model) {
		model.addAttribute("list", userRepo.findAll());
		return "user/user-list";
	}
	
	
	@PostMapping("/user/add")
	public String addUser(User user, Model model) {

		userRepo.save(user);
		return "redirect:/user/all";
	}
	
	@PostMapping("/user/update")
	public String updateUser(User user) {
		userRepo.save(user);
		return "redirect:/user/all";
	}
	
	@PostMapping("/user/delete")
	public String deleteUser(@RequestParam(required=false,name="id")long id) {
		userRepo.deleteById(id);
		return "redirect:/user/all";
	}
}
