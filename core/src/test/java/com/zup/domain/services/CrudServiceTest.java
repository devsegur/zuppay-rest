package com.zup.domain.services;

import static org.junit.jupiter.api.Assertions.assertTrue;

import com.zup.domain.dto.BaseDTO;
import org.junit.jupiter.api.Test;
import org.junit.platform.commons.util.ReflectionUtils;

class CrudServiceTest {

  @Test
  void MustBeDeclaredMethodListAll() {
    assertTrue(
        ReflectionUtils.isMethodPresent(
            CrudService.class, method -> method.getName().equals("listAll")));
  }

  @Test
  void MustBeDeclaredMethodFindOneById() {
    assertTrue(
        ReflectionUtils.findMethod(CrudService.class, "findOneById", Long.class).isPresent());
  }

  @Test
  void MustBeDeclaredMethodSave() {
    assertTrue(ReflectionUtils.findMethod(CrudService.class, "save", BaseDTO.class).isPresent());
  }

  @Test
  void MustBeDeclaredMethodUpdate() {
    assertTrue(ReflectionUtils.findMethod(CrudService.class, "update", BaseDTO.class).isPresent());
  }

  @Test
  void MustBeDeclaredMethodDelete() {
    assertTrue(ReflectionUtils.findMethod(CrudService.class, "delete", BaseDTO.class).isPresent());
  }
}
