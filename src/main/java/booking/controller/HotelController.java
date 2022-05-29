package booking.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import booking.model.Hotel;
import booking.repository.HotelRepository;

@Controller
@RequestMapping("/")
public class HotelController {
	@Autowired
	private HotelRepository hotelRepo;
	
//	@GetMapping
//	public String getMainPage() {
//		return "room/room-list";
//	}
	
	@GetMapping("/hotel/form")
	public String getHotelForm(Hotel hotel) {
		return "hotel/hotel-add";
	}
	
	@GetMapping("/hotel/update")
	public String getUpdateForm(@RequestParam("id")long id , Model model) {
		model.addAttribute("hotel", hotelRepo.findById(id));
		return "hotel/hotel-update";
	}
	
	@GetMapping("/hotel/delete")
	public String getDeleteForm(@RequestParam("id")long id , Model model) {
		model.addAttribute("hotel", hotelRepo.findById(id).get());
		return "hotel/hotel-delete";
	}
	
	@GetMapping("/hotel/all")
	public String getAllHotel(Model model) {
		model.addAttribute("list", hotelRepo.findAll());
		return "hotel/hotel-list";
	}
	
	
	@PostMapping("/hotel/add")
	public String addHotel(Hotel hotel, Model model) {

		hotelRepo.save(hotel);
		return "redirect:/hotel/all";
	}
	
	@PostMapping("/hotel/update")
	public String updateHotel(Hotel hotel) {
		hotelRepo.save(hotel);
		return "redirect:/hotel/all";
	}
	
	@PostMapping("/hotel/delete")
	public String deleteHotel(@RequestParam(required=false,name="id")long id) {
		hotelRepo.deleteById(id);
		return "redirect:/hotel/all";
	}
}
