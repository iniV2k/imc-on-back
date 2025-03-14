package com.viniciusx.proj_imc_on.controller;

import com.viniciusx.proj_imc_on.model.Imc;
import com.viniciusx.proj_imc_on.service.ImcService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "https://vini-imc-on.netlify.app/")
public class ImcController {

    private final ImcService service;

    public ImcController(ImcService service) {
        this.service = service;
    }

    //
    @GetMapping("/imc")
    public ResponseEntity<List<Imc>> findAll() {
        return new ResponseEntity<>(service.findAll(), HttpStatus.OK);
    }

    @GetMapping("/imc/{imcId}")
    public ResponseEntity<?> findById(@PathVariable("imcId") int id) {
        Imc imc = service.findById(id);

        if (imc.getId() > 0) {
            return new ResponseEntity<>(imc, HttpStatus.OK);
        } else {
            String erro = String.format("Não foi possível encontrar o registro com o id[%d] informado", id);
            return new ResponseEntity<>(erro, HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/imc")
    public ResponseEntity<?> saveImc(@RequestBody Imc imc) {
        Imc imcSalvo = null;

        try {
            imcSalvo = service.saveImc(imc);
            return new ResponseEntity<>(imcSalvo, HttpStatus.CREATED);
        } catch (IOException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/imc/{id}")
    public ResponseEntity<?> updateImc(@RequestBody Imc imc, @PathVariable("id") int id) {
        Imc imcAtualizado = null;

        try {
            imcAtualizado = service.updateImc(imc, id);
            return new ResponseEntity<>(imcAtualizado, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/imc/{imcId}")
    public ResponseEntity<String> deleteById(@PathVariable("imcId") int id) {
        Imc imc = service.findById(id);

        if (imc.getId() > 0) {
            service.deleteById(id);
            String sucesso = String.format("Registro de imc #{%d} deletado com sucesso!", id);
            return new ResponseEntity<>(sucesso, HttpStatus.ACCEPTED);
        } else {
            String erro = String.format("Ocorreu um erro ao tentar deletar o registro de imc #{%d}", id);
            return new ResponseEntity<>(erro, HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/imc/all/{senha}")
    public ResponseEntity<String> deleteAll(@PathVariable("senha") String senha) {
        if (service.deleteAllImcs(senha)) {
            return new ResponseEntity<>("Todos os registros foram excluídos!", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Senha incorreta!", HttpStatus.UNAUTHORIZED);
        }
    }

}
