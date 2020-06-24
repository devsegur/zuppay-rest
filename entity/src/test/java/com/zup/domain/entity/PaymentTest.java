package com.zup.domain.entity;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.junit.platform.commons.util.ReflectionUtils;

class PaymentTest {

  @Test
  void getPaymentId() {
    assertTrue(
        ReflectionUtils.isMethodPresent(
            Payment.class, method -> method.getName().equals("getPaymentId")));
  }

  @Test
  void getCreditCard() {
    assertTrue(
        ReflectionUtils.isMethodPresent(
            Payment.class, method -> method.getName().equals("getCreditCard")));
  }

  @Test
  void getProductId() {
    assertTrue(
        ReflectionUtils.isMethodPresent(
            Payment.class, method -> method.getName().equals("getProductId")));
  }

  @Test
  void getDescription() {
    assertTrue(
        ReflectionUtils.isMethodPresent(
            Payment.class, method -> method.getName().equals("getDescription")));
  }

  @Test
  void getDueDate() {
    assertTrue(
        ReflectionUtils.isMethodPresent(
            Payment.class, method -> method.getName().equals("getDueDate")));
  }

  @Test
  void getMoney() {
    assertTrue(
        ReflectionUtils.isMethodPresent(
            Payment.class, method -> method.getName().equals("getMoney")));
  }

  @Test
  void getCurrency() {
    assertTrue(
        ReflectionUtils.isMethodPresent(
            Payment.class, method -> method.getName().equals("getCurrency")));
  }

  @Test
  void setPaymentId() {
    assertTrue(
        ReflectionUtils.isMethodPresent(
            Payment.class, method -> method.getName().equals("setPaymentId")));
  }

  @Test
  void setCreditCard() {
    assertTrue(
        ReflectionUtils.isMethodPresent(
            Payment.class, method -> method.getName().equals("setCreditCard")));
  }

  @Test
  void setProductId() {
    assertTrue(
        ReflectionUtils.isMethodPresent(
            Payment.class, method -> method.getName().equals("setProductId")));
  }

  @Test
  void setDescription() {
    assertTrue(
        ReflectionUtils.isMethodPresent(
            Payment.class, method -> method.getName().equals("setDescription")));
  }

  @Test
  void setDueDate() {
    assertTrue(
        ReflectionUtils.isMethodPresent(
            Payment.class, method -> method.getName().equals("setDueDate")));
  }

  @Test
  void setMoney() {
    assertTrue(
        ReflectionUtils.isMethodPresent(
            Payment.class, method -> method.getName().equals("setMoney")));
  }

  @Test
  void setCurrency() {
    assertTrue(
        ReflectionUtils.isMethodPresent(
            Payment.class, method -> method.getName().equals("setCurrency")));
  }
}
