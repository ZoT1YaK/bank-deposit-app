package com.bank.service;

import com.bank.model.DepositRequest;
import com.bank.repository.DepositRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

import java.util.List;

/**
 * Implementation of deposit request service.
 */
@ApplicationScoped
public class DepositServiceImpl implements DepositService{

    @Inject
    private DepositRepository depositRepository;

    /**
     * Retrieves all deposit requests.
     * @return list of all deposit requests
     */
    @Override
    public List<DepositRequest> listAll() {
        return depositRepository.listAll();
    }

    /**
     * Saves a new deposit request to the database.
     * Applies business rules: USD deposits must be at least 3 months.
     * 
     * @param request the deposit request
     * @return saved deposit request
     * @throws IllegalArgumentException if USD deposit is under 3 months
     */
    @Override
    @Transactional
    public DepositRequest save(DepositRequest request) {
        if ("USD".equals(request.getCurrency()) && "1 month".equals(request.getTerm())) {
            throw new IllegalArgumentException("USD deposits must be at least 3 months");
        }

        depositRepository.persist(request);
        return request;
    }
}
