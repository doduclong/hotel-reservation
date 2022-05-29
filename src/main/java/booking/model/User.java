package booking.model;

import javax.persistence.*;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@Entity
@Table(name="user")
public class User {
	@Id
	@GeneratedValue
	private Long id;
	@Column(nullable = false, unique = true)
	private String username;
	private String password;
	private String role;

	
}
