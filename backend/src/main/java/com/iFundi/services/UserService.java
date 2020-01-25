package com.iFundi.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iFundi.models.User;
import com.iFundi.models.extras.UsersToVerify;
import com.iFundi.repositories.UserRepository;
import com.iFundi.security.AES;

@Service
public class UserService {
	@Autowired
	private UserRepository userRepository;

	public List<User> getUsers() {
		return userRepository.findAll();
	}

	public int activateAccount(Long userId) {
		// TODO Auto-generated method stub
		return userRepository.activateAccount(userId);
	}

	public User authUser(User user) throws Exception {
		// System.out.println(user.getPassword());
		System.out.println("User Password####" + user.getPassword());
		System.out.println("User Username####" + user.getUsername());
		return userRepository.findByUsernameAndPassword(user.getUsername(), AES.encrypt(user.getPassword()));
	}

	public User addUser(User user) {
		// TODO Auto-generated method stub
		return userRepository.save(user);
	}

	public User findByUsername(String username) {
		// TODO Auto-generated method stub
		return userRepository.findByUsername(username);
	}

	public User findByEmail(String email) {
		// TODO Auto-generated method stub
		return userRepository.findByEmail(email);
	}

	public List<UsersToVerify> getUsersToVerify() {
		// TODO Auto-generated method stub
		return userRepository.getUsersToVerify();
	}

	public int verifyUsers(Long userId) {
		// TODO Auto-generated method stub
		return userRepository.approveUsers(userId);
	}

	public User findById(String id) {
		// TODO Auto-generated method stub
		return userRepository.findById(id);
	}

	public int SetUserLoggedin(Long userId) {
		// TODO Auto-generated method stub
		return userRepository.SetUserLoggedin(userId);
	}

	public int SetUserLoggedOut(String username) {
		// TODO Auto-generated method stub
		return userRepository.SetUserLoggedOut(username);
	}

	public int updateUsers(String fullname, String email, String phone, int group_id, String branch, Long userId) {
		// TODO Auto-generated method stub
		return userRepository.updateUsers(fullname, email, phone, group_id, branch, userId);
	}

	public int updatePassword(String username, String password) {
		return userRepository.updatePassword(username, password);
	}

	public int deactivateUser(User user) {
		User u = userRepository.findByUserId(user.getId());
		if (u != null) {
			u.setStatus(false);
			User u1 = userRepository.save(u);
			if (u1 != null) {
				return 1;
			}

		}
		return 0;
	}

	public int activateUser(User user) {
		User u = userRepository.findByUserId(user.getId());
		if (u != null) {
			u.setStatus(true);
			User u1 = userRepository.save(u);
			if (u1 != null) {
				return 0;
			}

		}
		return 0;
	}

	// fetch enrolled users for report
	public List<User> getEnrolledUsers(String fromDate, String toDate) {

		return userRepository.getAll(fromDate, toDate);
	}

	// password reset
	public int resetPassword(String username, String password) {
		return userRepository.resetPassword(username, password);
	}
}
