package com.bank.service;

import com.bank.model.DepositRequest;
import com.bank.repository.DepositRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class DepositServiceImplTest {

    @InjectMocks
    DepositServiceImpl depositService;

    @Mock
    DepositRepository depositRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldReturnAllDeposits() {
        DepositRequest request = new DepositRequest();
        request.setCustomerId("C1");
        request.setCustomerName("Alice");
        request.setDepositAmount(2000);
        request.setCurrency("EUR");
        request.setTerm("1 year");

        when(depositRepository.listAll()).thenReturn(List.of(request));

        List<DepositRequest> results = depositService.listAll();

        assertEquals(1, results.size());
        assertEquals("Alice", results.get(0).getCustomerName());
        verify(depositRepository, times(1)).listAll();
    }

    @Test
    void shouldSaveValidDeposit() {
        DepositRequest request = new DepositRequest();
        request.setCustomerId("C2");
        request.setCustomerName("Bob");
        request.setDepositAmount(3000);
        request.setCurrency("EUR");
        request.setTerm("6 months");

        DepositRequest saved = depositService.save(request);

        verify(depositRepository).persist(request);
        assertEquals("Bob", saved.getCustomerName());
    }

    @Test
    void shouldThrowExceptionForInvalidUsdTerm() {
        DepositRequest request = new DepositRequest();
        request.setCustomerId("C3");
        request.setCustomerName("Tom");
        request.setDepositAmount(2000);
        request.setCurrency("USD");
        request.setTerm("1 month");

        assertThrows(IllegalArgumentException.class, () -> depositService.save(request));
        verify(depositRepository, never()).persist((DepositRequest) any());
    }
}
