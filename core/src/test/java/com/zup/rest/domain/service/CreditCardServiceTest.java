package com.zup.rest.domain.service;

import static java.util.Optional.ofNullable;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;

import com.google.common.collect.ImmutableList;
import com.zup.rest.domain.dto.CreditCardDTO;
import com.zup.rest.domain.entity.CreditCard;
import com.zup.rest.domain.entity.Transaction;
import com.zup.rest.domain.exception.message.NotFoundedException;
import com.zup.rest.domain.mapper.CreditCardMapper;
import com.zup.rest.infrastructure.repository.CreditCardRepository;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.UUID;
import javassist.tools.web.BadHttpRequest;
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
  void mustReturnAllCreditCardsWhenListAll() {

    var creditCardUuid = UUID.fromString("4002-8922-2490-9141-2222");
    var transactionRandomUuid = UUID.randomUUID();
    var expectedResponse = buildDTO(creditCardUuid);

    when(repository.findAll()).thenReturn((buildEntity(creditCardUuid, transactionRandomUuid)));
    when(mapper.map(buildEntity(creditCardUuid, transactionRandomUuid).get(0)))
        .thenReturn(buildDTO(creditCardUuid).get(0));
    when(mapper.map(buildEntity(creditCardUuid, transactionRandomUuid).get(1)))
        .thenReturn(buildDTO(creditCardUuid).get(1));
    when(mapper.map(buildEntity(creditCardUuid, transactionRandomUuid).get(2)))
        .thenReturn(buildDTO(creditCardUuid).get(2));
    var response = service.listAll();

    assertThat(response, is(equalTo(expectedResponse)));
  }

  @Test
  void mustReturnOneWhenFindById() throws NotFoundedException {
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
  void mustPerformSaveAndReturnResponse() throws BadHttpRequest {

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
  void mustPerformUpdateAndReturnResponseWithOtherOwnerName() throws NotFoundedException {
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
  void mustPerformDeleteAndReturnResponseWithDeletedDate() throws NotFoundedException {
    var creditCardUuid = UUID.fromString("4002-8922-2490-9141-2222");
    var cardNumber = "0000 2222 1111 2222";
    var securityCode = "007";
    var ownerName = "So-and-so";
    var deletedDate = LocalDateTime.now();
    var transactionRandomUuid = UUID.randomUUID();
    var paymentRandomUuid = ImmutableList.of(UUID.randomUUID());
    var creditCard =
        buildCreditCard(creditCardUuid, transactionRandomUuid, cardNumber, ownerName, securityCode);
    var deletedCard =
        buildCreditCard(creditCardUuid, transactionRandomUuid, cardNumber, ownerName, securityCode);
    var givenArgument =
        buildCreditCardDTO(creditCardUuid, paymentRandomUuid, cardNumber, ownerName, securityCode);
    CreditCardDTO expectedResponse =
        buildCreditCardDTO(creditCardUuid, paymentRandomUuid, cardNumber, ownerName, securityCode);
    givenArgument.setDeletedDate(deletedDate);

    when(mapper.map(givenArgument)).thenReturn(creditCard);
    when(mapper.map(creditCard)).thenReturn(expectedResponse);
    doReturn(deletedCard).when(repository).save(any());
    doReturn(true).when(repository).existsById(UUID.fromString(String.valueOf(creditCardUuid)));
    var response = service.delete(givenArgument);

    assertThat(response, is(equalTo(expectedResponse)));
  }

  @Test()
  void mustRespondWithNotFoundExceptionWhenFindByIdGivenNull() {
    assertThrows(NotFoundedException.class, () -> service.findOneById(null));
  }

  @Test()
  void mustRespondWithNotFoundExceptionWhenUpdateGivenNull() {
    assertThrows(NotFoundedException.class, () -> service.update(null));
  }

  @Test()
  void mustRespondWithNotFoundExceptionWhenDeleteGivenNull() {
    assertThrows(NotFoundedException.class, () -> service.delete(null));
  }

  @Test()
  void mustRespondWithNotFoundExceptionWhenFindById() {
    var uuid = UUID.randomUUID();

    assertThrows(NotFoundedException.class, () -> service.findOneById(uuid));
  }

  @Test()
  void mustRespondWithNotFoundExceptionWhenUpdate() {
    var uuid = UUID.randomUUID();

    assertThrows(
        NotFoundedException.class,
        () -> service.update(CreditCardDTO.builder().creditCardId(uuid).build()));
  }

  @Test()
  void mustRespondWithNotFoundExceptionWhenDelete() {
    var uuid = UUID.randomUUID();

    assertThrows(
        NotFoundedException.class,
        () -> service.delete(CreditCardDTO.builder().creditCardId(uuid).build()));
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
        .transaction(Transaction.builder().transactionId(transactionRandomUuid).build())
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

  private ImmutableList<CreditCardDTO> buildDTO(UUID creditCardUuid) {
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
