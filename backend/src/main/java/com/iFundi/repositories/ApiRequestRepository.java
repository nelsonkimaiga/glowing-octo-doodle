package com.iFundi.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.iFundi.models.ApiRequest;

/**
 * Created by CLLSDJACKT013 on 3/4/2019.
 */
@Repository
public interface  ApiRequestRepository extends CrudRepository<ApiRequest,Long> {

}
