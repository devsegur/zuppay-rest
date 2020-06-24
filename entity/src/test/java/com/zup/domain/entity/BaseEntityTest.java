package com.zup.domain.entity;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.junit.platform.commons.util.ReflectionUtils;

class BaseEntityTest {

  @Test
  void getCreatedDate() {
    assertTrue(
        ReflectionUtils.isMethodPresent(
            BaseEntity.class, method -> method.getName().equals("getCreatedDate")));
  }

  @Test
  void getUpdatedDate() {
    assertTrue(
        ReflectionUtils.isMethodPresent(
            BaseEntity.class, method -> method.getName().equals("getUpdatedDate")));
  }

  @Test
  void getDeletedDate() {
    assertTrue(
        ReflectionUtils.isMethodPresent(
            BaseEntity.class, method -> method.getName().equals("getDeletedDate")));
  }

  @Test
  void setCreatedDate() {
    assertTrue(
        ReflectionUtils.isMethodPresent(
            BaseEntity.class, method -> method.getName().equals("setCreatedDate")));
  }

  @Test
  void setUpdatedDate() {
    assertTrue(
        ReflectionUtils.isMethodPresent(
            BaseEntity.class, method -> method.getName().equals("setUpdatedDate")));
  }

  @Test
  void setDeletedDate() {
    assertTrue(
        ReflectionUtils.isMethodPresent(
            BaseEntity.class, method -> method.getName().equals("setDeletedDate")));
  }
}
