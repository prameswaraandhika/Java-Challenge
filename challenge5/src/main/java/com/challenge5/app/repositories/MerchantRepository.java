package com.challenge5.app.repositories;

import com.challenge5.app.model.Merchant;
import com.challenge5.app.model.dtos.MonthlyRevenueDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface MerchantRepository extends JpaRepository<Merchant, UUID> {
    List<Merchant> findAllByLocation(String location);


    @Query("SELECT NEW com.challenge5.app.model.dtos.MonthlyRevenueDTO(TO_CHAR(o.time, 'YYYY-MM') AS month, u.username AS username, SUM(od.totalPrice) AS monthlyRevenue) " +
            "FROM Order o " +
            "JOIN o.orderDetails od " +
            "JOIN o.user u " +
            "WHERE o.complete = true " +
            "AND o.id = :id " +
            "GROUP BY TO_CHAR(o.time, 'YYYY-MM'), u.username " +
            "ORDER BY TO_CHAR(o.time, 'YYYY-MM'), u.username")
    public List<MonthlyRevenueDTO> getMonthlyRevenues(UUID id);


}
