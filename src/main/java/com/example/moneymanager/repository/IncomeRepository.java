package com.example.moneymanager.repository;

import com.example.moneymanager.entity.IncomeEntity;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public interface IncomeRepository extends JpaRepository<IncomeEntity, Long> {
    //SELECT * FROM tbl_incomes WHERE profile_id = ? ORDER BY date DESC
    List<IncomeEntity> findByProfileIdOrderByDateDesc(Long profileId);

    //SELECT * FROM tbl_incomes WHERE profile_id = ? ORDER BY data DESC LIMIT 5
    List<IncomeEntity> findTop5ByProfileIdOrderByDateDesc(Long profileId);

    @Query(
            "SELECT SUM(e.amount) FROM IncomeEntity AS e WHERE i.profile.id = :profileId"
    )
    BigDecimal findTotalExpenseByProfileId(@Param("profileId") Long profileId);

    // SELECT * FROM tbl_incomes WHERE profile_id = ? AND data BETWEEN ? AND ? AND name LIKE %?%
    List<IncomeEntity> findByProfileIdAndDateBetweenAndNameContainingIgnoreCase(
            Long profileId,
            LocalDate startDate,
            LocalDate endDate,
            String keyword,
            Sort sort
    );

    //SELECT * FROM tbl_incomes WHERE profile_id = ? AND date BETWEEN ? AND ?
    List<IncomeEntity> findByProfileIdAndDateBetween(Long profileId, LocalDate startDate, LocalDate endDate);
}

