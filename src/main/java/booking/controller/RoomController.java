package booking.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import booking.model.Hotel;
import booking.model.Room;
import booking.repository.HotelRepository;
import booking.repository.RoomRepository;

@Controller
@RequestMapping("/")
public class RoomController {
	@Autowired
	private RoomRepository roomRepo;
	
	@Autowired
	private HotelRepository hotelRepo;
	
//	@GetMapping
//	public String getMainPage() {
//		return "room/room-list";
//	}
	
	@GetMapping("/room/form")
	public String getRoomForm(Room room, Model model) {
		model.addAttribute("listHotel", hotelRepo.findAll());
		return "room/room-add";
	}
	
//	@GetMapping("/room/form")
//	public void getAllHotel(Model model) {
//		model.addAttribute("listHotel", hotelRepo.findAll());
//	}
	
	@GetMapping("/room/update")
	public String getUpdateForm(@RequestParam("id")long id , Model model) {
		model.addAttribute("room", roomRepo.findById(id));
		return "room/room-update";
	}
	
	@GetMapping("/room/delete")
	public String getDeleteForm(@RequestParam("id")long id , Model model) {
		model.addAttribute("room", roomRepo.findById(id).get());
		return "room/room-delete";
	}
	
	@GetMapping("/room/all")
	public String getAllRoom(Model model) {
		model.addAttribute("list", roomRepo.findAll());
		return "room/room-list";
	}
	
//	public String populateList(Model model) {
//	    List<Long> options = new ArrayList<Long>();
//	    options.add("option 1");
//	    options.add("option 2");
//	    options.add("option 3");
//	    model.addAttribute("options", options);
//	    return "dropDownList/dropDownList.html";
//	}
	
	@PostMapping("/room/add")
	public String addRoom(@RequestParam("hotel_id")long hotel_id,Hotel hotel,Room room, Model model) {
//		boolean idExist = roomRepo.checkIdExist(room.getId());
//		//model.addAttribute("listIdHotel", hotelRepo.findAll());
//		if(idExist) {
//			model.addAttribute("error", "Room id is Exist");
//			model.addAttribute("room", room);
//			return "room/room-add";
//		}else 
		hotel = hotelRepo.findById(hotel_id).get();
		room.setHotel_name(hotel.getName());
			roomRepo.save(room);
		return "redirect:/room/all";
	}
	
	@PostMapping("/room/update")
	public String updateRoom(@RequestParam("hotel_id")long hotel_id,Hotel hotel,Room room) {
		
		hotel = hotelRepo.findById(hotel_id).get();
		room.setHotel_name(hotel.getName());
		roomRepo.save(room);
		return "redirect:/room/all";
	}
	
	@PostMapping("/room/delete")
	public String deleteRoom(@RequestParam("id")long id) {
		roomRepo.deleteById(id);
		return "redirect:/room/all";
	}
	
	
}
