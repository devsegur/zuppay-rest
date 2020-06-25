package com.zup.infrasctructure.repository;

import com.zup.domain.entity.BaseEntity;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CreditCardRepository extends JpaRepository<BaseEntity, UUID> {}
