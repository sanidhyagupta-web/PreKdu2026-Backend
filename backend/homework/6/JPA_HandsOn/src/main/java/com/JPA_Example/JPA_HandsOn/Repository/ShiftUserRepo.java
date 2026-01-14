package com.JPA_Example.JPA_HandsOn.Repository;

import com.JPA_Example.JPA_HandsOn.Entities.ShiftUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShiftUserRepo extends JpaRepository<ShiftUser,Long> {

}
