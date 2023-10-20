package com.example.MoneyMind.service;

import com.example.MoneyMind.repository.GastoNaoEssencialRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GastoNaoEssencialService{
    @Autowired
    private GastoNaoEssencialRepository gastoNaoEssencialRepository;

//    public List<GastoNaoEssencialDTO> buscarGastosNaoEssenciaisPorMes(int mes){
//        return gastoNaoEssencialRepository.findByMes(mes).stream()
//                .map(gastoNaoEssencialMapper::toDTO).collect(Collectors.toList());
//    }
//    public List<GastoEssencialDTO> buscarGastosEssenciaisPorMes(int mes){
//        return gastoEssencialRepository.findByMes(mes).stream()
//                .map(gastoEssencialMapper::toDTO).collect(Collectors.toList());
//    }

}
