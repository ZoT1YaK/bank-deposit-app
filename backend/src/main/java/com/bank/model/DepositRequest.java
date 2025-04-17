package com.bank.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

/**
 * Represents a bank deposit request.
 */
@Entity
@Table(name = "deposit_requests")
public class DepositRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String customerId;

    @NotBlank
    private String customerName;

    @Min(1000)
    private double depositAmount;

    @Pattern(regexp = "EUR|USD")
    private String currency;


    @Pattern(
            regexp = "1 month|3 months|6 months|1 year|2 years|3 years",
            message = "Invalid term"
    )
    private String term;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public double getDepositAmount() {
        return depositAmount;
    }

    public void setDepositAmount(double depositAmount) {
        this.depositAmount = depositAmount;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getTerm() {
        return term;
    }

    public void setTerm(String term) {
        this.term = term;
    }
}
