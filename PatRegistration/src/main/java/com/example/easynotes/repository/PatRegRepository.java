package com.example.easynotes.repository;

import com.example.easynotes.model.PatReg;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Anil on 10/08/18.
 */

@Repository
public interface PatRegRepository extends JpaRepository<PatReg, Long> {

}
