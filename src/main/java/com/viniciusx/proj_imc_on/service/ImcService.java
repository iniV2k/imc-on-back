package com.viniciusx.proj_imc_on.service;

import com.viniciusx.proj_imc_on.model.Imc;
import com.viniciusx.proj_imc_on.repository.ImcRepository;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class ImcService {

    private final ImcRepository repository;

    public ImcService(ImcRepository repository) {
        this.repository = repository;
    }

    //
    public List<Imc> findAll() {
        return repository.findAll();
    }

    public Imc findById(int id) {
        return repository.findById(id).orElse(new Imc((long) -1));
    }

    public Imc saveOrUpdateImc(Imc imc) throws IOException {
        imc.setValorImc(retornarValorImc(imc));
        repository.save(imc);
        return imc;
    }

    public double retornarValorImc(Imc imc) {
        return Math.round(imc.getPeso() / Math.pow(imc.getAltura(), 2) * 100.0) / 100.0;
    }

    public void deleteById(int id) {
        repository.deleteById(id);
    }
}
