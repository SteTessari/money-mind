package com.example.MoneyMind.controllers;

import com.example.MoneyMind.dtos.GastoEssencialDTO;
import com.example.MoneyMind.service.GastoEssencialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/gastos-essenciais")
public class GastosEssenciaisController {

    @Autowired
    private GastoEssencialService gastoEssencialService;

    @PostMapping
    public ResponseEntity<String> inserir(@RequestBody GastoEssencialDTO gastoEssencialDTO) {
        gastoEssencialService.inserir(gastoEssencialDTO);
        return ResponseEntity.ok("Inserido com sucesso");
    }

    @PutMapping("/{idGastoEssencial}")
    public ResponseEntity<String> atualizar(@PathVariable Long idGastoEssencial,
                                            @RequestBody GastoEssencialDTO gastoEssencialDTO) {
        gastoEssencialService.atualizar(idGastoEssencial, gastoEssencialDTO);
        return ResponseEntity.ok("Atualizado com sucesso");
    }

    @GetMapping
    public ResponseEntity<List<GastoEssencialDTO>> filtrar(@RequestBody String descricao) {
        return ResponseEntity.ok(gastoEssencialService.filtrar(descricao));
    }

    @GetMapping
    public ResponseEntity<Page<GastoEssencialDTO>> buscarTodos(@PageableDefault Pageable pageable) {
        return ResponseEntity.ok(gastoEssencialService.buscarTodos(pageable));
    }


}
