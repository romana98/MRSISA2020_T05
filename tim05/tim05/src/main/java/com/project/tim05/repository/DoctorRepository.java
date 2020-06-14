package com.project.tim05.repository;

import javax.persistence.LockModeType;
import javax.persistence.QueryHint;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.QueryHints;

import com.project.tim05.model.Doctor;

public interface DoctorRepository extends JpaRepository<Doctor, Integer>{
	Doctor findByEmail(String email);
	
	//Zakljucavamo product koji se vraca za citanje i pisanje
	@Lock(LockModeType.PESSIMISTIC_WRITE)
	//Postgres po defaultu poziva for update bez no wait, tako da treba dodati vrednost 0 za timeout
	//kako bismo dobili PessimisticLockingFailureException ako pri pozivu ove metode torka nije dostupna
	@QueryHints({@QueryHint(name = "javax.persistence.lock.timeout", value ="0")})
	Doctor findById(int id);
	
	}
