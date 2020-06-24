package com.zup.domain.entity;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.junit.platform.commons.util.ReflectionUtils;

class TransactionTest {

  @Test
  void getTransactionId() {
    assertTrue(
        ReflectionUtils.isMethodPresent(
            Transaction.class, method -> method.getName().equals("getTransactionId")));
  }

  @Test
  void getChargeDate() {
    assertTrue(
        ReflectionUtils.isMethodPresent(
            Transaction.class, method -> method.getName().equals("getChargeDate")));
  }

  @Test
  void getPaymentStatus() {
    assertTrue(
        ReflectionUtils.isMethodPresent(
            Transaction.class, method -> method.getName().equals("getPaymentStatus")));
  }

  @Test
  void getPayment() {
    assertTrue(
        ReflectionUtils.isMethodPresent(
            Transaction.class, method -> method.getName().equals("getPayment")));
  }

  @Test
  void getCreditCard() {
    assertTrue(
        ReflectionUtils.isMethodPresent(
            Transaction.class, method -> method.getName().equals("getCreditCard")));
  }

  @Test
  void setTransactionId() {
    assertTrue(
        ReflectionUtils.isMethodPresent(
            Transaction.class, method -> method.getName().equals("setTransactionId")));
  }

  @Test
  void setChargeDate() {
    assertTrue(
        ReflectionUtils.isMethodPresent(
            Transaction.class, method -> method.getName().equals("setChargeDate")));
  }

  @Test
  void setPaymentStatus() {
    assertTrue(
        ReflectionUtils.isMethodPresent(
            Transaction.class, method -> method.getName().equals("setPaymentStatus")));
  }

  @Test
  void setPayment() {
    assertTrue(
        ReflectionUtils.isMethodPresent(
            Transaction.class, method -> method.getName().equals("setPayment")));
  }

  @Test
  void setCreditCard() {
    assertTrue(
        ReflectionUtils.isMethodPresent(
            Transaction.class, method -> method.getName().equals("setCreditCard")));
  }
}
