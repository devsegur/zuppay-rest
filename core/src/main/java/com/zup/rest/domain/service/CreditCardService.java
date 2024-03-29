package com.zup.rest.domain.service;

import static java.util.Optional.ofNullable;

import com.zup.rest.domain.dto.CreditCardDTO;
import com.zup.rest.domain.entity.CreditCard;
import com.zup.rest.domain.exception.message.NotFoundedException;
import com.zup.rest.domain.mapper.CreditCardMapper;
import com.zup.rest.infrastructure.repository.CreditCardRepository;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import javassist.tools.web.BadHttpRequest;
import lombok.AllArgsConstructor;
import org.apache.commons.lang.SerializationUtils;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CreditCardService implements CrudService<CreditCardDTO> {

  private final CreditCardRepository repository;
  private final CreditCardMapper mapper;

  @Override
  public List<CreditCardDTO> listAll() {
    return repository.findAll().parallelStream().map(mapper::map).collect(Collectors.toList());
  }

  @Override
  public CreditCardDTO findOneById(UUID uuid) throws NotFoundedException {
    return repository.findById(uuid).map(mapper::map).orElseThrow(NotFoundedException::new);
  }

  @Override
  public CreditCardDTO save(CreditCardDTO dto) throws BadHttpRequest {
    return ofNullable(dto)
        .map(mapper::map)
        .map(repository::save)
        .map(mapper::map)
        .orElseThrow(BadHttpRequest::new);
  }

  @Override
  public CreditCardDTO update(CreditCardDTO dto) throws NotFoundedException {
    return ofNullable(dto)
        .filter(ifExistsOnDatabase())
        .map(mapper::map)
        .map(repository::save)
        .map(mapper::map)
        .orElseThrow(NotFoundedException::new);
  }

  @Override
  public CreditCardDTO delete(CreditCardDTO dto) throws NotFoundedException {
    return ofNullable(dto)
        .filter(ifExistsOnDatabase())
        .map(mapper::map)
        .map(setCardDeleted())
        .map(repository::save)
        .map(mapper::map)
        .orElseThrow(NotFoundedException::new);
  }

  private Predicate<CreditCardDTO> ifExistsOnDatabase() {
    return optDTO -> repository.existsById(optDTO.getCreditCardId());
  }

  private Function<CreditCard, CreditCard> setCardDeleted() {
    return card -> {
      CreditCard clonedCard = ((CreditCard) SerializationUtils.clone(card));
      clonedCard.setDeletedDate(LocalDateTime.now());
      return clonedCard;
    };
  }
}
