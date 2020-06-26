package com.zup.domain.service;

import static java.util.Optional.ofNullable;

import com.zup.domain.dto.CreditCardDTO;
import com.zup.domain.entity.CreditCard;
import com.zup.domain.mapper.CreditCardMapper;
import com.zup.infrasctructure.repository.CreditCardRepository;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
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
  public CreditCardDTO findOneById(UUID uuid) {
    return repository.findById(uuid).map(mapper::map).orElse(null);
  }

  @Override
  public CreditCardDTO save(CreditCardDTO dto) {
    var entity = mapper.map(dto);
    return mapper.map(repository.save(entity));
  }

  @Override
  public CreditCardDTO update(CreditCardDTO dto) {
    return ofNullable(dto)
        .filter(ifExistsOnDatabase())
        .map(mapper::map)
        .map(repository::save)
        .map(mapper::map)
        .orElse(null);
  }

  @Override
  public CreditCardDTO delete(CreditCardDTO dto) {
    return ofNullable(dto)
        .filter(ifExistsOnDatabase())
        .map(mapper::map)
        .map(setCardDeleted())
        .map(repository::save)
        .map(mapper::map)
        .orElse(null);
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
