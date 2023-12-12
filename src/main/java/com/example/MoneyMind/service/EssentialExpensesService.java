package com.example.MoneyMind.service;

import com.example.MoneyMind.dtos.EssentialExpensesDTO;
import com.example.MoneyMind.entidades.EssentialExpenses;
import com.example.MoneyMind.entidades.Limit;
import com.example.MoneyMind.enums.AccountStatus;
import com.example.MoneyMind.exception.MoneyMindException;
import com.example.MoneyMind.mapper.EssencialExpensesMapper;
import com.example.MoneyMind.repository.EssencialExpensesRepository;
import com.example.MoneyMind.repository.LimitsRepository;
import com.example.MoneyMind.util.validations.ValidateEssencialExpenses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.YearMonth;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class EssentialExpensesService extends ValidateEssencialExpenses {

    @Autowired
    private LimitsRepository limitsRepository;
    @Autowired
    private EssencialExpensesRepository essencialExpensesRepository;

    private final EssencialExpensesMapper essencialExpensesMapper = EssencialExpensesMapper.INSTANCE;

    public void create(EssentialExpensesDTO essentialExpensesDTO) {
        if (essentialExpensesDTO.getInvoicePaymentDate() != null) {
            essentialExpensesDTO.setAccountStatus(AccountStatus.PAGA);
        }

        if (essentialExpensesDTO.getInvoicePaymentDate() == null &&
                essentialExpensesDTO.getInvoiceDueDate().isBefore(LocalDate.now())) {
            essentialExpensesDTO.setAccountStatus(AccountStatus.ATRASADA);
        }

        EssentialExpenses essentialExpenses = essencialExpensesMapper.toObject(essentialExpensesDTO);
        treatmentsBeforeInserting(essentialExpenses);
    }

    public void update(Long idGastoEssencial, EssentialExpensesDTO essentialExpensesDTO) {
        EssentialExpenses essentialExpenses = findById(idGastoEssencial);

        EssentialExpenses essentialExpensesUpdated = essencialExpensesMapper.updateFromDTO(
                essentialExpensesDTO, essentialExpenses);

        create(essentialExpensesUpdated);
    }

    public EssentialExpensesDTO findDTOById(Long idEssentialExpenses) {
        EssentialExpenses essentialExpenses = essencialExpensesRepository.findById(idEssentialExpenses)
                .orElseThrow(() -> new MoneyMindException(
                        HttpStatus.NO_CONTENT,
                        "No result found."));

        return essencialExpensesMapper.toDTO(essentialExpenses);
    }

    public Page<EssentialExpensesDTO> findAll(Pageable pageable) {

        Page<EssentialExpenses> expenses = essencialExpensesRepository.findAll(pageable);
        return expenses.map(essencialExpensesMapper::toDTO);
    }

    private void create(EssentialExpenses essentialExpenses) {
        essencialExpensesRepository.save(essentialExpenses);
    }

    private void treatmentsBeforeInserting(EssentialExpenses essentialExpenses) {
        BigDecimal value = essentialExpenses.getValue();

        List<EssentialExpenses> expenses = essencialExpensesRepository
                .findByMonth(essentialExpenses.getMonth().toString());

        Limit limit = limitsRepository.findByMonth(YearMonth.now().toString())
                .orElse(null);

        validarLimite(expenses, limit, value);
        create(essentialExpenses);
    }

    private EssentialExpenses findById(Long idGastoEssencial) {
        return essencialExpensesRepository.findById(idGastoEssencial)
                .orElseThrow(() -> new MoneyMindException(
                        HttpStatus.NO_CONTENT,
                        "No result found."));
    }

    public List<EssentialExpensesDTO> filter(String description) {
        return essencialExpensesRepository.findByDescription(description)
                .stream()
                .map(essencialExpensesMapper::toDTO)
                .collect(Collectors.toList());
    }
}
