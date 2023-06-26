package ahiru.model;

import java.util.Optional;

import org.springframework.security.crypto.password.PasswordEncoder;

import ahiru.service.GroupService;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Data
@Slf4j
public class RegistrationForm {
	private String username;
	private String password;
	private Integer studentGroup;

	public User toUser(PasswordEncoder passwordEncoder,
			GroupService groupService) {
		User user = new User();
		user.setUsername(username);
		user.setPassword(passwordEncoder.encode(password));

		Optional<StudentGroup> stOptional = groupService
				.findById(studentGroup);
		log.info("Otional student group = {}", stOptional);
		user.setStudentGroup(stOptional.get());
		return user;
	}
}
