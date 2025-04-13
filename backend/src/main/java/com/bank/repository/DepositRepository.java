package com.bank.repository;

import com.bank.model.DepositRequest;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class DepositRepository implements PanacheRepository<DepositRequest> {
}
