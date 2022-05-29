package booking.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
@Entity
public class Hotel {
	@Id
	@GeneratedValue
	private long id;
	private String name, address;
	private int starLevel;
}
