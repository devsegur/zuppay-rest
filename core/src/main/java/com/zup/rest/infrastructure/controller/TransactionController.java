package com.zup.rest.infrastructure.controller;

import com.zup.rest.domain.dto.TransactionDTO;
import com.zup.rest.domain.exception.message.NotFoundedException;
import com.zup.rest.domain.service.TransactionService;
import java.util.Collection;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping(value = "/transaction", produces = MediaType.APPLICATION_JSON_VALUE)
public class TransactionController implements CrudController<TransactionDTO> {

  private TransactionService service;

  @Override
  @GetMapping
  public Collection<TransactionDTO> listAll() {
    return service.listAll();
  }

  @Override
  @GetMapping(value = "/{id}")
  public TransactionDTO findOneById(@NonNull @PathVariable(value = "id") UUID id)
      throws NotFoundedException {
    return service.findOneById(id);
  }

  @Override
  public TransactionDTO save(@NonNull @RequestBody TransactionDTO dto) {
    return null;
  }

  @Override
  public TransactionDTO update(@NonNull @RequestBody TransactionDTO dto)
      throws NotFoundedException {
    return null;
  }

  @Override
  @DeleteMapping()
  public TransactionDTO delete(@NonNull @RequestBody TransactionDTO dto)
      throws NotFoundedException {
    return service.delete(dto);
  }
}
