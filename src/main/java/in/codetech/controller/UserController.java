package in.codetech.controller;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import in.codetech.model.Role;
import in.codetech.model.User;
import in.codetech.model.UserRole;
import in.codetech.repository.UserRepository;
import in.codetech.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userService;

//	@PostMapping
//	public ResponseEntity<User> createUser(@RequestBody User user) {
//		User savedUser = userService.saveUser(user);
//		return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
//	}
	
	@PostMapping("/")
	public User createUser(@RequestBody User user) throws Exception {
		
		user.setProfile("default.png");
		Set<UserRole> roles = new HashSet();
		
		Role role = new Role();
		role.setRoleId(45L);
		role.setRoleName("NORMAL");
		
		UserRole userRole = new UserRole();
		userRole.setUser(user);
		userRole.setRole(role);
		
		roles.add(userRole);
		
		return this.userService.createUser(user, roles);
	}


	@GetMapping("/{username}")
	public User getUser(@PathVariable("username") String username) {
		return this.userService.getUser(username);

	}


	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteUser(@PathVariable Long id) {
		userService.deleteUserById(id);
		return new ResponseEntity<>("Deleted Sucessfully", HttpStatus.OK);
	}

}
