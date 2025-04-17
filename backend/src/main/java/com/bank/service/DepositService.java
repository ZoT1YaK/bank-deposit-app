package com.bank.service;

import com.bank.model.DepositRequest;

import java.util.List;

/**
 * Interface for deposit request service operations.
 */
public interface DepositService {

    List<DepositRequest> listAll();

    DepositRequest save(DepositRequest request);
}
