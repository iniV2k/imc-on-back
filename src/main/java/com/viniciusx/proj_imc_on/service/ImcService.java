package com.viniciusx.proj_imc_on.service;

import com.viniciusx.proj_imc_on.model.Imc;
import com.viniciusx.proj_imc_on.repository.ImcRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

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

    @Transactional
    public Imc saveImc(Imc imc) throws IOException {
        imc.setValorImc(retornarValorImc(imc));
        repository.save(imc);
        return imc;
    }

    private double retornarValorImc(Imc imc) {
        return Math.round(imc.getPeso() / Math.pow((imc.getAltura() / 100.0), 2) * 100.0) / 100.0;
    }

    public void deleteById(int id) {
        repository.deleteById(id);
    }

    public boolean deleteAllImcs(String senha) {
        if (senha.equals("A1b2c3@")) {
            repository.deleteAll();
            return true;
        } else {
            return false;
        }
    }

    public Imc updateImc(Imc imc, int id) {

        Optional<Imc> imcEncontrado = repository.findById(id);

        if (imcEncontrado.isPresent()) {
            Imc novosDados = imcEncontrado.get();
            novosDados.setId(imc.getId());
            novosDados.setNome(imc.getNome());
            novosDados.setAltura(imc.getAltura());
            novosDados.setPeso(imc.getPeso());
            novosDados.setSexo(imc.getSexo());
            novosDados.setValorImc(imc.getValorImc());
            novosDados.setDataHoraRegistro(imc.getDataHoraRegistro());

            return repository.save(novosDados);
        }

        throw new RuntimeException("IMC não encontrado com o ID: " + id);
    }
}
