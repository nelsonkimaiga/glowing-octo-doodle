package com.iFundi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.iFundi.models.Config;

@Repository
public interface ConfigRepository extends JpaRepository <Config,Long> {
	
	@Query("SELECT u FROM Config u WHERE u.defaultConf=true")
	Config getDefaultsConfigs();
}
