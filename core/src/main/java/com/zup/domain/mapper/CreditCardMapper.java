package com.zup.domain.mapper;

import com.zup.domain.dto.CreditCardDTO;
import com.zup.domain.entity.CreditCard;
import com.zup.domain.entity.Payment;
import java.util.Collection;
import java.util.UUID;
import java.util.stream.Collectors;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class CreditCardMapper {

  @Mappings({
    @Mapping(source = "creditCardId", target = "creditCardId"),
    @Mapping(source = "ownerName", target = "ownerName"),
    @Mapping(source = "cardNumber", target = "cardNumber"),
    @Mapping(source = "expirationDate", target = "expirationDate"),
    @Mapping(source = "securityCode", target = "securityCode"),
    @Mapping(expression = "java(getPaymentUuid(card.getPayment()))", target = "payment")
  })
  public abstract CreditCardDTO map(CreditCard card);

  @Mappings({
    @Mapping(source = "creditCardId", target = "creditCardId"),
    @Mapping(source = "ownerName", target = "ownerName"),
    @Mapping(source = "cardNumber", target = "cardNumber"),
    @Mapping(source = "expirationDate", target = "expirationDate"),
    @Mapping(source = "securityCode", target = "securityCode"),
    @Mapping(expression = "java(getPayments(dto.getPayment()))", target = "payment")
  })
  public abstract CreditCard map(CreditCardDTO dto);

  protected Collection<Payment> getPayments(Collection<UUID> uuidCollection) {
    return uuidCollection.stream()
        .map(id -> Payment.builder().paymentId(id).build())
        .collect(Collectors.toList());
  }

  protected Collection<UUID> getPaymentUuid(Collection<Payment> payments) {
    return payments.parallelStream().map(Payment::getPaymentId).collect(Collectors.toList());
  }
}
