package com.iFundi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.iFundi.models.Customerr;

import java.util.List;

/**
 * Created by CLLSDJACKT013 on 2/14/2019.
 */
@Repository
public interface CustomerrRepository extends JpaRepository<Customerr,Long>{
    @Query("select '*' from Customerr")
    List<Customerr> fetchAll();

    @Query("select customerr from Customerr customerr where customerr.active =true")
    List<Customerr>fetchAllActiveCustomers();

    @Query("select customer from Customerr customer where customer.active =false")
    List<Customerr>fetchAllInactiveCustomers();

    @Query("select   customerr from Customerr customerr where customerr.afisid =:afisid")
    Customerr fetchCustomerByAfisid(@Param("afisid") String afisid);

    @Query("delete from Customerr customerr where customerr.afisid =:afisid")
    void deleteCustomerrByAfisid(@Param("afisid")String afisid);
}
