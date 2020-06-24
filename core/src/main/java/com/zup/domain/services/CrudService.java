package com.zup.domain.services;

import com.zup.domain.dto.BaseDTO;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface CrudService {

  Flux<BaseDTO> listAll();

  Mono<BaseDTO> findOneById(Long id);

  Mono<BaseDTO> save(BaseDTO dto);

  Mono<BaseDTO> update(BaseDTO dto);

  Mono<BaseDTO> delete(BaseDTO dto);
}
