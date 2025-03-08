package com.viniciusx.proj_imc_on.repository;

import com.viniciusx.proj_imc_on.model.Imc;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

@Repository
public interface ImcRepository extends JpaRepository<Imc, Integer> {

}
