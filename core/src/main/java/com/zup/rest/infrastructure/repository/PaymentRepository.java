package com.zup.rest.infrastructure.repository;

import com.zup.rest.domain.entity.Payment;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentRepository
    extends JpaRepository<Payment, UUID>, QueryByExampleExecutor<Payment> {}
