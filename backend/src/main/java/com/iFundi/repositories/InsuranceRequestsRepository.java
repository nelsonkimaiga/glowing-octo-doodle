package com.iFundi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.iFundi.models.InsuranceRequests;

@Repository
public interface InsuranceRequestsRepository extends JpaRepository<InsuranceRequests, Long> {

}
