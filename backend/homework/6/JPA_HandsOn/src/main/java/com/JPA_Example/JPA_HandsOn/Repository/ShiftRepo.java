package com.JPA_Example.JPA_HandsOn.Repository;

import com.JPA_Example.JPA_HandsOn.Entities.Shift;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ShiftRepo extends JpaRepository<Shift,Long> {

    @Query("""
        select s
        from Shift s
        join s.shiftType st
        where s.dateStart = :startDate
          and s.dateEnd <= :endBy
        order by st.name asc
    """)
    List<Shift> findNewYearTopShifts(
            @Param("startDate") LocalDate startDate,
            @Param("endBy") LocalDate endBy,
            Pageable pageable
    );

}