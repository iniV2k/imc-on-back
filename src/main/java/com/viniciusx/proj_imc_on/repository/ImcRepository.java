package com.viniciusx.proj_imc_on.repository;

import com.viniciusx.proj_imc_on.model.Imc;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ImcRepository extends JpaRepository<Imc, Integer> {

    @Modifying
    @Query(value = "ALTER SEQUENCE imc_id_seq RESTART WITH 1", nativeQuery = true)
    void resetarSequencia();

}
