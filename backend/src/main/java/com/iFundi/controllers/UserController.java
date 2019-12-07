package com.iFundi.controllers;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.iFundi.config.ResourceConfig;
import com.iFundi.handlers.CustomResponse;
import com.iFundi.handlers.UserResponse;
import com.iFundi.models.User;
import com.iFundi.models.extras.UsersToVerify;
import com.iFundi.security.AES;
import com.iFundi.services.SendMail;
import com.iFundi.services.UserService;

@RestController
@RequestMapping(value = ResourceConfig.iFundi_API_v1)
public class UserController {
	@Autowired
	private UserService userService;
	@Autowired
	private SendMail sendMail;

	public UserController(SendMail sendMail) {
		// TODO Auto-generated constructor stub
		this.sendMail = sendMail;
	}

	@GetMapping(value = "/test")
	public ResponseEntity test() {
		return ResponseEntity.status(201).body("ok..");
	}

	@GetMapping(value = "/sysusers")
	public ResponseEntity<?> getSysUsers() throws Exception {

		System.out.println("System users ####");

		List<User> users = userService.getUsers();
		for (User usr : users) {
			usr.setPassword(AES.decrypt(usr.getPassword()));
		}

		if (users.isEmpty()) {
			return new ResponseEntity<>(new CustomResponse(CustomResponse.APIV, 404, false, "no users found"),
					HttpStatus.OK);
		}

		return new ResponseEntity<>(
				new CustomResponse(CustomResponse.APIV, 200, true, "found users", new HashSet<>(users)), HttpStatus.OK);
	}

	@PostMapping(value = "/sysusers/auth")
	public ResponseEntity<?> authUser(@RequestBody User user) throws Exception {
		User userpro = userService.authUser(user);
		System.out.println(userpro);
		if (userpro == null) {
			return new ResponseEntity<>(new UserResponse("invalid user credentials", 409, false, UserResponse.APIV),
					HttpStatus.OK);
		} else if (!userpro.getApproved().equalsIgnoreCase("V") || userpro.isStatus() == false) {
			return new ResponseEntity<>(new UserResponse(
					"user specified is neither verified or active, kindly ensure " + " you verified and actived ", 201,
					false, UserResponse.APIV), HttpStatus.OK);
		} else if (userpro != null && userpro.isStatus() != false) {
			System.out.println("email" + user.getEmail());
			return new ResponseEntity<>(
					new UserResponse("successfully authenticated!", 200, true, UserResponse.APIV, userpro),
					HttpStatus.OK);
		} else {
			return new ResponseEntity<>(new UserResponse("invalid user credentials", 409, false, UserResponse.APIV),
					HttpStatus.OK);
		}
	}

	@PostMapping(value = "/sysusers/create")
	public ResponseEntity<?> addUser(@RequestBody User user) throws Exception {
		User username = userService.findByUsername(user.getUsername());
		if (username != null && user.getId() == 0) {
			return new ResponseEntity<>(new UserResponse(UserResponse.APIV, 203, false, "username already exists!"),
					HttpStatus.OK);
		}
		user.setPassword(AES.encrypt(user.getPassword()));
		User usr = userService.addUser(user);

		if (usr == null) {
			return new ResponseEntity<>(new CustomResponse(UserResponse.APIV, 409, false, "failed to add user"),
					HttpStatus.OK);
		}

		return new ResponseEntity<>(new CustomResponse(CustomResponse.APIV, 201, true, "User records updated"),
				HttpStatus.OK);
	}

	@SuppressWarnings("unused")
	@PostMapping(value = "/sysusers/print/auth")
	public ResponseEntity<?> printAuthUser(@RequestBody User user) throws Exception {

		User userpro = userService.findByUsername(user.getUsername());
		System.out.println(userpro);
		if (userpro == null) {
			return new ResponseEntity<>(new UserResponse("invalid user credentials", 409, false, UserResponse.APIV),
					HttpStatus.OK);
		} else if (!userpro.getApproved().equalsIgnoreCase("V") || userpro.isStatus() == false) {
			return new ResponseEntity<>(new UserResponse(
					"user specified is neither verified or active, kindly ensure " + " you verified and active ", 201,
					false, UserResponse.APIV), HttpStatus.OK);
		} else if (userpro != null) {
			System.out.println("email" + user.getEmail());
			return new ResponseEntity<>(
					new UserResponse("successfully authenticated!", 200, true, UserResponse.APIV, userpro),
					HttpStatus.OK);
		} else {
			return new ResponseEntity<>(new UserResponse("invalid user credentials", 409, false, UserResponse.APIV),
					HttpStatus.OK);

		}
	}

	@GetMapping(value = "/users/toverify")
	public ResponseEntity<?> getUsersToVerify(
//    		@RequestParam("fromDt")
//    		@DateTimeFormat(pattern="yyyy-MM-dd")
//    		Date fromDt,
//    		@RequestParam(value="toDt")
//    		@DateTimeFormat(pattern="yyyy-MM-dd")
//    		Date toDt
	) {
		System.out.println("works...");
		List<UsersToVerify> users = userService.getUsersToVerify();
		if (users.isEmpty()) {
			return new ResponseEntity<>(new CustomResponse(CustomResponse.APIV, 201, false, "no users found to approve",
					new HashSet<>(users)), HttpStatus.OK);
		}
		return new ResponseEntity<>(
				new CustomResponse(CustomResponse.APIV, 200, true, "found users to approve", new HashSet<>(users)),
				HttpStatus.OK);
	}

	@PostMapping(value = "/users/verifyusers")
	public ResponseEntity<?> verifyUsers(@RequestBody List<User> users) {

		if (users.size() > 0) {
			for (User user : users) {
				System.out.println("user id ##" + user.getId());
				int upuser = userService.verifyUsers(user.getId());

				if (upuser < 0) {
					return new ResponseEntity<>(new CustomResponse(CustomResponse.APIV, 201, false,
							"there was problem approving users, kindly retry "), HttpStatus.OK);
				}
				try {
					// send email with password to user:
					User usr = userService.findById(String.valueOf(user.getId()));
					String message = "Dear " + usr.getFullName()
							+ ",\nYour Login credentials for BOTC are: \nUsername: " + usr.getUsername()
							+ "\nPassword: " + AES.decrypt(usr.getPassword());

					SendMail send = new SendMail();
					System.out.println("OTC API Key " + System.getProperty("otcapiKey"));
					send.InitiateMail("Login Credentials", usr.getEmail(), message, "", "email server here",
							"port nummber", "email username", "email address comes here", "email password here",
							"false");

				} catch (Exception ex) {
					return new ResponseEntity<>(
							new CustomResponse(CustomResponse.APIV, 201, false, "There was an error sending email "),
							HttpStatus.OK);
				}
			}

			return new ResponseEntity<>(
					new CustomResponse(CustomResponse.APIV, 200, true, "user approved successfully"), HttpStatus.OK);
		}
		return new ResponseEntity<>(
				new CustomResponse(CustomResponse.APIV, 201, false, "There was problem updating users, kindly retry "),
				HttpStatus.OK);
	}

	@GetMapping(value = "/fetchall")
	public ResponseEntity getAllUsers() {
		List<User> users = new ArrayList<>();
		userService.getUsers().forEach(user -> {
			try {
				users.add(new User(user.getEmail(), user.getFirstName(), user.getFullName(),
						AES.decrypt(user.getPassword()), "", "", "", "", 0, 0, false, "", 0, new Date()));
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
		return ResponseEntity.status(201).body(users);
	}

}
