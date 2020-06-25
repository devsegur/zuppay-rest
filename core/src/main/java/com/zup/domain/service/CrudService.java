package com.zup.domain.service;

import com.zup.domain.dto.BaseDTO;
import java.util.Collection;
import java.util.UUID;

public interface CrudService<T extends BaseDTO> {

  Collection<T> listAll();

  T findOneById(UUID id);

  T save(T dto);

  T update(T dto);

  T delete(T dto);
}
