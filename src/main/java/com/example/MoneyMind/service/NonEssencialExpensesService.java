package com.example.MoneyMind.service;

import com.example.MoneyMind.repository.NonEssencialExpensesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NonEssencialExpensesService {
    @Autowired
    private NonEssencialExpensesRepository nonEssencialExpensesRepository;

}
