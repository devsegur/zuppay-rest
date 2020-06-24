package com.zup.domain.entity;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.junit.platform.commons.util.ReflectionUtils;

class CreditCardTest {

  @Test
  void getCreditCardId() {
    assertTrue(
        ReflectionUtils.isMethodPresent(
            CreditCard.class, method -> method.getName().equals("getCreditCardId")));
  }

  @Test
  void getOwnerName() {
    assertTrue(
        ReflectionUtils.isMethodPresent(
            CreditCard.class, method -> method.getName().equals("getOwnerName")));
  }

  @Test
  void getCardNumber() {
    assertTrue(
        ReflectionUtils.isMethodPresent(
            CreditCard.class, method -> method.getName().equals("getCardNumber")));
  }

  @Test
  void getExpirationDate() {
    assertTrue(
        ReflectionUtils.isMethodPresent(
            CreditCard.class, method -> method.getName().equals("getExpirationDate")));
  }

  @Test
  void getSecurityCode() {
    assertTrue(
        ReflectionUtils.isMethodPresent(
            CreditCard.class, method -> method.getName().equals("getSecurityCode")));
  }

  @Test
  void setCreditCardId() {
    assertTrue(
        ReflectionUtils.isMethodPresent(
            CreditCard.class, method -> method.getName().equals("setCreditCardId")));
  }

  @Test
  void setOwnerName() {
    assertTrue(
        ReflectionUtils.isMethodPresent(
            CreditCard.class, method -> method.getName().equals("setOwnerName")));
  }

  @Test
  void setCardNumber() {
    assertTrue(
        ReflectionUtils.isMethodPresent(
            CreditCard.class, method -> method.getName().equals("setCardNumber")));
  }

  @Test
  void setExpirationDate() {
    assertTrue(
        ReflectionUtils.isMethodPresent(
            CreditCard.class, method -> method.getName().equals("setExpirationDate")));
  }

  @Test
  void setSecurityCode() {
    assertTrue(
        ReflectionUtils.isMethodPresent(
            CreditCard.class, method -> method.getName().equals("setSecurityCode")));
  }
}
