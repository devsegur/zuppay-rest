package com.zup.domain.service;

import static java.util.Optional.ofNullable;

import com.zup.domain.dto.PaymentDTO;
import com.zup.domain.entity.Payment;
import com.zup.domain.exception.message.NotFoundedException;
import com.zup.domain.mapper.PaymentMapper;
import com.zup.infrasctructure.repository.PaymentRepository;
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
public class PaymentService implements CrudService<PaymentDTO> {

  private final PaymentRepository repository;
  private final PaymentMapper mapper;

  @Override
  public List<PaymentDTO> listAll() {
    return repository.findAll().parallelStream().map(mapper::map).collect(Collectors.toList());
  }

  @Override
  public PaymentDTO findOneById(UUID uuid) throws NotFoundedException {
    return repository.findById(uuid).map(mapper::map).orElseThrow(NotFoundedException::new);
  }

  @Override
  public PaymentDTO save(PaymentDTO dto) {
    var entity = mapper.map(dto);
    return mapper.map(repository.save(entity));
  }

  @Override
  public PaymentDTO update(PaymentDTO dto) throws NotFoundedException {
    return ofNullable(dto)
        .filter(ifExistsOnDatabase())
        .map(mapper::map)
        .map(repository::save)
        .map(mapper::map)
        .orElseThrow(NotFoundedException::new);
  }

  @Override
  public PaymentDTO delete(PaymentDTO dto) throws NotFoundedException {
    return ofNullable(dto)
        .filter(ifExistsOnDatabase())
        .map(mapper::map)
        .map(setPaymentDeleted())
        .map(repository::save)
        .map(mapper::map)
        .orElseThrow(NotFoundedException::new);
  }

  private Predicate<PaymentDTO> ifExistsOnDatabase() {
    return optDTO -> repository.existsById(optDTO.getPaymentId());
  }

  private Function<Payment, Payment> setPaymentDeleted() {
    return payment -> {
      Payment clonedPayment = ((Payment) SerializationUtils.clone(payment));
      clonedPayment.setDeletedDate(LocalDateTime.now());
      return clonedPayment;
    };
  }
}
