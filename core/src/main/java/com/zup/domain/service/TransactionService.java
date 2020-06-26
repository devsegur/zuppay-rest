package com.zup.domain.service;

import static java.util.Optional.ofNullable;

import com.zup.domain.dto.TransactionDTO;
import com.zup.domain.entity.Transaction;
import com.zup.domain.mapper.TransactionMapper;
import com.zup.infrasctructure.repository.TransactionRepository;
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
public class TransactionService implements CrudService<TransactionDTO> {

  private final TransactionRepository repository;
  private final TransactionMapper mapper;

  @Override
  public List<TransactionDTO> listAll() {
    return repository.findAll().parallelStream().map(mapper::map).collect(Collectors.toList());
  }

  @Override
  public TransactionDTO findOneById(UUID uuid) {
    return repository.findById(uuid).map(mapper::map).orElse(null);
  }

  @Override
  public TransactionDTO save(TransactionDTO dto) {
    var entity = mapper.map(dto);
    return mapper.map(repository.save(entity));
  }

  @Override
  public TransactionDTO update(TransactionDTO dto) {
    return ofNullable(dto)
        .filter(ifExistsOnDatabase())
        .map(mapper::map)
        .map(repository::save)
        .map(mapper::map)
        .orElse(null);
  }

  @Override
  public TransactionDTO delete(TransactionDTO dto) {
    return ofNullable(dto)
        .filter(ifExistsOnDatabase())
        .map(mapper::map)
        .map(setTransactionDeleted())
        .map(repository::save)
        .map(mapper::map)
        .orElse(null);
  }

  private Predicate<TransactionDTO> ifExistsOnDatabase() {
    return optDTO -> repository.existsById(optDTO.getTransactionId());
  }

  private Function<Transaction, Transaction> setTransactionDeleted() {
    return transaction -> {
      Transaction clonedTransaction = ((Transaction) SerializationUtils.clone(transaction));
      clonedTransaction.setDeletedDate(LocalDateTime.now());
      return clonedTransaction;
    };
  }
}
