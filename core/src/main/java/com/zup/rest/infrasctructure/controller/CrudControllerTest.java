package com.zup.rest.infrasctructure.controller;

import static org.junit.jupiter.api.Assertions.assertTrue;

import com.zup.rest.domain.dto.BaseDTO;
import java.util.UUID;
import org.junit.jupiter.api.Test;
import org.junit.platform.commons.util.ReflectionUtils;

class CrudControllerTest {

  @Test
  void mustBeDeclaredMethodListAll() {
    assertTrue(
        ReflectionUtils.isMethodPresent(
            CrudController.class, method -> method.getName().equals("listAll")));
  }

  @Test
  void mustBeDeclaredMethodFindOneById() {
    assertTrue(
        ReflectionUtils.findMethod(CrudController.class, "findOneById", UUID.class).isPresent());
  }

  @Test
  void mustBeDeclaredMethodSave() {
    assertTrue(ReflectionUtils.findMethod(CrudController.class, "save", BaseDTO.class).isPresent());
  }

  @Test
  void mustBeDeclaredMethodUpdate() {
    assertTrue(
        ReflectionUtils.findMethod(CrudController.class, "update", BaseDTO.class).isPresent());
  }

  @Test
  void mustBeDeclaredMethodDelete() {
    assertTrue(
        ReflectionUtils.findMethod(CrudController.class, "delete", BaseDTO.class).isPresent());
  }
}
