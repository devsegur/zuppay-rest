package com.zup.rest.infrasctructure.controller;

import com.zup.rest.domain.dto.CreditCardDTO;
import com.zup.rest.domain.exception.message.NotFoundedException;
import com.zup.rest.domain.service.CreditCardService;
import java.util.Collection;
import java.util.UUID;
import javassist.tools.web.BadHttpRequest;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping(value = "/credit-card", produces = MediaType.APPLICATION_JSON_VALUE)
public class CreditCardController implements CrudController<CreditCardDTO> {

  private CreditCardService service;

  @Override
  @GetMapping
  public Collection<CreditCardDTO> listAll() {
    return service.listAll();
  }

  @Override
  @GetMapping(value = "/{id}")
  public CreditCardDTO findOneById(@NonNull @PathVariable(value = "id") UUID id)
      throws NotFoundedException {
    return service.findOneById(id);
  }

  @Override
  @PostMapping
  public CreditCardDTO save(@NonNull @RequestBody CreditCardDTO dto) throws BadHttpRequest {
    return service.save(dto);
  }

  @Override
  @PutMapping
  public CreditCardDTO update(@NonNull @RequestBody CreditCardDTO dto) throws NotFoundedException {
    return service.update(dto);
  }

  @Override
  @DeleteMapping()
  public CreditCardDTO delete(@NonNull @RequestBody CreditCardDTO dto) throws NotFoundedException {
    return service.delete(dto);
  }
}
