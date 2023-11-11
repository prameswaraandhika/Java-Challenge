package com.challenge5.app.repositories;

import com.challenge5.app.model.Merchant;
import com.challenge5.app.model.dtos.MerchantMonthlyRevenueDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Repository
public interface MerchantRepository extends JpaRepository<Merchant, UUID> {
    List<Merchant> findAllByLocation(String location);



    @Query("SELECT NEW com.challenge5.app.model.dtos.MerchantMonthlyRevenueDto" +
            "(o.time, m.name, p.name as product, od.quantity, od.totalPrice) " +
            "FROM OrderDetail od JOIN od.order o JOIN od.product p " +
            "JOIN p.merchant m WHERE m.id = :merchantId AND" +
            " o.time BETWEEN :startDate AND :endDate")
    List<MerchantMonthlyRevenueDto> getMonthlyRevenues(@Param("merchantId") UUID id,
                                                       @Param("startDate") LocalDate start,
                                                       @Param("endDate") LocalDate end
                                                       );


}
