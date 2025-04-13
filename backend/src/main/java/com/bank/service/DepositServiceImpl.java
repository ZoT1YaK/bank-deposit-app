package com.bank.service;

import com.bank.model.DepositRequest;
import com.bank.repository.DepositRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

import java.util.List;

@ApplicationScoped
public class DepositServiceImpl implements DepositService{

    @Inject
    public DepositRepository depositRepository;

    @Override
    public List<DepositRequest> listAll() {
        return depositRepository.listAll();
    }

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
