package com.iFundi.repositories;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.iFundi.models.User;
import com.iFundi.models.extras.UsernamesExists;
import com.iFundi.models.extras.UsersToVerify;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

	// @Query("select u from User u where u.username=?1 and u.password=?2 and
	// u.status=1 and approved='V' ")
	User findByUsernameAndPassword(String username, String password);

	User findByUsername(String username);

	@Query("select count(u) from User u")
	int getUserCount();

	@Query(nativeQuery = true, value = "SELECT ROW_NUMBER() OVER(ORDER BY u.id) as counter,  "
			+ " u.id,(select fullname from users a  where u.created_by=a.id) as createdBy, u.fullname ,um.GroupName,convert(date,u.created_at) as createdAt "
			+ " FROM users u " + " inner join usergroupsmaster um on um.id=u.group_id "
			+ " where u.verified<>'V' and u.status=1")
	List<UsersToVerify> getUsersToVerify();

	@Modifying
	@Transactional
	@Query(nativeQuery = true, value = "UPDATE users set verified='V', verified_on=getDate() WHERE id=?1")
	int approveUsers(Long userId);

	@Query(nativeQuery = true, value = "SELECT u.username FROM users u")
	List<UsernamesExists> getExistingUsernames();

	@Query(nativeQuery = true, value = "SELECT *FROM users WHERE id=?1")
	User findById(String id);

	@Modifying
	@Transactional
	@Query(nativeQuery = true, value = "UPDATE users set logged_in=1 WHERE id=?1")
	int SetUserLoggedin(Long userId);

	@Modifying
	@Transactional
	@Query(nativeQuery = true, value = "UPDATE users set logged_in = 0 WHERE username=?1")
	int SetUserLoggedOut(String username);

	@Modifying
	@Transactional
	@Query(nativeQuery = true, value = "UPDATE users set verified='N',fullname=?1,email=?2,phone=?3,group_id=?4,branchId=?5 WHERE id=?6")
	int updateUsers(String fullname, String email, String phone, int group_id, String branch, Long userId);

	@Modifying
	@Transactional
	@Query(value = "UPDATE users set password = :password WHERE username = :username", nativeQuery = true)
	int updatePassword(@Param("username") String username, @Param("password") String password);

	// get users to deactivate
//	@Query(nativeQuery = true, value = "SELECT ROW_NUMBER() OVER(ORDER BY u.id) as counter,  "
//			+ " u.id,(select fullname from users a  where u.created_by=a.id) as createdBy, u.fullname, um.GroupName,convert(date,u.created_at) as createdAt "
//			+ " FROM users u " + " inner join usergroupsmaster um on um.id=u.group_id " + " where u.status=1")
//	List<UsersToDeactivate> getUsersToDeactivate();

	@Modifying
	@Transactional
	@Query(value = "UPDATE users set status= 0  WHERE username = :userId", nativeQuery = true)
	int deactivateUser(@Param("userId") Long userId);
//
//	@Modifying
//	@Transactional
//	@Query(value = "UPDATE users set status = 1 WHERE username =?1", nativeQuery = true)
//	int activateUser(String username);

	@Query(nativeQuery = true, value = "SELECT *FROM users WHERE id=?1")
	User findByUserId(Long userId);

//	@Query(nativeQuery = true, value = "SELECT ROW_NUMBER() OVER(ORDER BY u.id) as counter,  "
//			+ " u.id,(select fullname from users a  where u.created_by=a.id) as createdBy, u.fullname ,um.GroupName,convert(date,u.created_at) as createdAt "
//			+ " FROM users u " + " inner join usergroupsmaster um on um.id=u.group_id " + " where u.status=0")
//	List<UsersToActivate> getUsersToActivate();

	@Modifying
	@Transactional
	@Query(value = "UPDATE users set status= 1  WHERE username = :userId", nativeQuery = true)
	int activateUser(@Param("userId") Long userId);

	// @Query(nativeQuery = true, value = "SELECT (select fullname from users a
	// where u.created_by=a.id) as createdBy, u.fullname ,um.GroupName FROM users u
	// inner join usergroupsmaster um on um.id=u.group_id where u.created_at BETWEEN
	// ?1 AND ?2")
	@Query(nativeQuery = true, value = "SELECT * FROM users u WHERE u.created_at BETWEEN ?1 AND ?2")
	List<User> getAll(String fromDate, String toDate);

	// admin user password reset
	@Modifying
	@Transactional
	@Query(value = "UPDATE users set password = :password WHERE username = :username", nativeQuery = true)
	int resetPassword(@Param("username") String username, @Param("password") String password);

	@Modifying
	@Transactional
	@Query(nativeQuery = true, value = "UPDATE users set status=1 WHERE id=?1")
	int activateAccount(@Param("id") Long userId);

	@Query(nativeQuery = true, value = "SELECT * FROM users WHERE email=?1 AND password=?1")
	User findByEmail(String email);
}
