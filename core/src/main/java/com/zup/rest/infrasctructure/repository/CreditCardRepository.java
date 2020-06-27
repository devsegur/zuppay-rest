package com.zup.rest.infrasctructure.repository;

import com.zup.rest.domain.entity.CreditCard;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CreditCardRepository extends JpaRepository<CreditCard, UUID> {}
