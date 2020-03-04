package com.iFundi.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestMapping;

import com.iFundi.models.ApiSecurity;

/**
 * Created by CLLSDJACKT013 on 1/29/2019.
 */
@Repository
public interface ApiKeyRepository extends CrudRepository<ApiSecurity,Long> {
    @Query("select apiSecurity from ApiSecurity apiSecurity  where apiSecurity.action =:action")
    ApiSecurity findAPiKeyByAction(@Param("action")String action);

    @Query("update ApiSecurity apiSecurity set apiSecurity =:apiKey where apiSecurity.action=:action")
    void updateAPiKeyByAction(@Param("apiKey")ApiSecurity apiSecurity,@Param("action")String action);
}
