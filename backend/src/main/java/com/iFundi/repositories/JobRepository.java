package com.iFundi.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.iFundi.models.Job;

/**
 * Created by CLLSDJACKT013 on 2/6/2019.
 */
@Repository
public interface JobRepository extends JpaRepository<Job, Long> {
	@Override
	@Query(nativeQuery = true, value = "select * from Job")
	List<Job> findAll();

	Job findById(@Param("id") Integer id);

	@Query("delete from Job job where job.id =:id")
	void deleteJobById(@Param("id") int Id);
	// @Query("update Account account set account =:AccountNo where account.account
	// = AccountNo.")
	// void updateAccount(@Param("Account")Account AccountNo);
}
