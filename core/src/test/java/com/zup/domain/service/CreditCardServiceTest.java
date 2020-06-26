package com.zup.domain.service;

import static java.util.Optional.ofNullable;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;

import com.google.common.collect.ImmutableList;
import com.zup.domain.dto.CreditCardDTO;
import com.zup.domain.entity.CreditCard;
import com.zup.domain.entity.Transaction;
import com.zup.domain.mapper.CreditCardMapper;
import com.zup.infrasctructure.repository.CreditCardRepository;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Collections;
import java.util.UUID;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class CreditCardServiceTest {

  @InjectMocks CreditCardService service;
  @Mock CreditCardRepository repository;
  @Mock CreditCardMapper mapper;

  @Test
  void listAll() {

    var creditCardUuid = UUID.fromString("4002-8922-2490-9141-2222");
    var transactionRandomUuid = UUID.randomUUID();
    var paymentRandomUuid = UUID.randomUUID();
    var expectedResponse = buildDTO(creditCardUuid, Collections.singleton(paymentRandomUuid));

    when(repository.findAll()).thenReturn((buildEntity(creditCardUuid, transactionRandomUuid)));
    when(mapper.map(buildEntity(creditCardUuid, transactionRandomUuid).get(0)))
        .thenReturn(buildDTO(creditCardUuid, Collections.singleton(paymentRandomUuid)).get(0));
    when(mapper.map(buildEntity(creditCardUuid, transactionRandomUuid).get(1)))
        .thenReturn(buildDTO(creditCardUuid, Collections.singleton(paymentRandomUuid)).get(1));
    when(mapper.map(buildEntity(creditCardUuid, transactionRandomUuid).get(2)))
        .thenReturn(buildDTO(creditCardUuid, Collections.singleton(paymentRandomUuid)).get(2));
    var response = service.listAll();

    assertThat(response, is(equalTo(expectedResponse)));
  }

  @Test
  void findOneById() {
    var creditCardUuid = UUID.fromString("4002-8922-2490-9141-2222");
    var transactionRandomUuid = UUID.randomUUID();
    var cardNumber = "0000 2222 1111 2222";
    var securityCode = "007";
    var ownerName = "So-and-so";
    var paymentRandomUuid = ImmutableList.of(UUID.randomUUID());
    var creditCard =
        buildCreditCard(creditCardUuid, transactionRandomUuid, cardNumber, ownerName, securityCode);
    var expectedResponse =
        buildCreditCardDTO(creditCardUuid, paymentRandomUuid, cardNumber, ownerName, securityCode);

    when(repository.findById(creditCardUuid)).thenReturn(ofNullable(creditCard));
    when(mapper.map(creditCard)).thenReturn(expectedResponse);
    var response = service.findOneById(creditCardUuid);

    assertThat(response, is(equalTo(expectedResponse)));
  }

  @Test
  void save() {

    var creditCardUuid = UUID.fromString("4002-8922-2490-9141-2222");
    var cardNumber = "0000 2222 1111 2222";
    var securityCode = "007";
    var ownerName = "So-and-so";
    var transactionRandomUuid = UUID.randomUUID();
    var paymentRandomUuid = ImmutableList.of(UUID.randomUUID());
    var creditCard =
        buildCreditCard(creditCardUuid, transactionRandomUuid, cardNumber, ownerName, securityCode);
    var expectedResponse =
        buildCreditCardDTO(creditCardUuid, paymentRandomUuid, cardNumber, ownerName, securityCode);

    when(mapper.map(expectedResponse)).thenReturn(creditCard);
    when(mapper.map(creditCard)).thenReturn(expectedResponse);
    when(repository.save(creditCard)).thenReturn(creditCard);
    var response = service.save(expectedResponse);

    assertThat(response, is(equalTo(expectedResponse)));
  }

  @Test
  void update() {
    var creditCardUuid = UUID.fromString("4002-8922-2490-9141-2222");
    var cardNumber = "0000 2222 1111 2222";
    var securityCode = "007";
    var pastOwnerName = "So-and-so";
    var thenOwnerName = "So-and-so-more";
    var transactionRandomUuid = UUID.randomUUID();
    var paymentRandomUuid = ImmutableList.of(UUID.randomUUID());
    var creditCard =
        buildCreditCard(
            creditCardUuid, transactionRandomUuid, cardNumber, pastOwnerName, securityCode);
    var expectedResponse =
        buildCreditCardDTO(
            creditCardUuid, paymentRandomUuid, cardNumber, thenOwnerName, securityCode);

    when(mapper.map(expectedResponse)).thenReturn(creditCard);
    when(mapper.map(creditCard)).thenReturn(expectedResponse);
    when(repository.save(creditCard)).thenReturn(creditCard);
    when(repository.existsById(expectedResponse.getCreditCardId())).thenReturn(true);
    var response = service.update(expectedResponse);

    assertThat(response, is(equalTo(expectedResponse)));
  }

  @Test
  void delete() {
    var creditCardUuid = UUID.fromString("4002-8922-2490-9141-2222");
    var cardNumber = "0000 2222 1111 2222";
    var securityCode = "007";
    var pastOwnerName = "So-and-so";
    var thenOwnerName = "So-and-so-more";
    var deletedDate = LocalDateTime.now();
    var transactionRandomUuid = UUID.randomUUID();
    var paymentRandomUuid = ImmutableList.of(UUID.randomUUID());
    var creditCard =
        buildCreditCard(
            creditCardUuid, transactionRandomUuid, cardNumber, pastOwnerName, securityCode);
    var deletedCard =
        buildCreditCard(
            creditCardUuid, transactionRandomUuid, cardNumber, pastOwnerName, securityCode);
    var givenArgument =
        buildCreditCardDTO(
            creditCardUuid, paymentRandomUuid, cardNumber, thenOwnerName, securityCode);
    CreditCardDTO expectedResponse =
        buildCreditCardDTO(
            creditCardUuid, paymentRandomUuid, cardNumber, thenOwnerName, securityCode);
    givenArgument.setDeletedDate(deletedDate);

    when(mapper.map(givenArgument)).thenReturn(creditCard);
    when(mapper.map(creditCard)).thenReturn(expectedResponse);
    doReturn(deletedCard).when(repository).save(any());
    doReturn(true).when(repository).existsById(UUID.fromString(String.valueOf(creditCardUuid)));
    var response = service.delete(givenArgument);

    assertThat(response, is(equalTo(expectedResponse)));
  }

  private CreditCard buildCreditCard(
      UUID creditCardUuid,
      UUID transactionRandomUuid,
      String cardNumber,
      String ownerName,
      String securityCode) {
    return CreditCard.builder()
        .creditCardId(creditCardUuid)
        .cardNumber(cardNumber)
        .ownerName(ownerName)
        .transaction(
            ImmutableList.of(Transaction.builder().transactionId(transactionRandomUuid).build()))
        .securityCode(securityCode)
        .build();
  }

  private ImmutableList<CreditCard> buildEntity(UUID creditCardUuid, UUID transactionRandomUuid) {
    return ImmutableList.of(
        buildCreditCard(
            creditCardUuid, transactionRandomUuid, "0000 2222 1111 2222", "So-and-so", "007"),
        buildCreditCard(
            creditCardUuid, transactionRandomUuid, "0001 2222 1111 2222", "So-and-so-Jr", "008"),
        buildCreditCard(
            creditCardUuid, transactionRandomUuid, "0002 2222 1111 2222", "So-and-so-Jr", "009"));
  }

  private ImmutableList<CreditCardDTO> buildDTO(
      UUID creditCardUuid, Collection<UUID> paymentRandomUuid) {
    return ImmutableList.of(
        buildCreditCardDTO(creditCardUuid, null, "0000 2222 1111 2222", "So-and-so", "007"),
        buildCreditCardDTO(creditCardUuid, null, "0001 2222 1111 2222", "So-and-so-Jr", "008"),
        buildCreditCardDTO(creditCardUuid, null, "0000 2222 1111 2222", "So-and-so", "009"));
  }

  private CreditCardDTO buildCreditCardDTO(
      UUID creditCardUuid,
      Collection<UUID> payments,
      String cardNumber,
      String ownerName,
      String securityCode) {
    return CreditCardDTO.builder()
        .creditCardId(creditCardUuid)
        .payment(payments)
        .cardNumber(cardNumber)
        .ownerName(ownerName)
        .securityCode(securityCode)
        .build();
  }
}
