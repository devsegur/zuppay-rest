package com.zup.rest.infrastructure.feign;

import com.zup.rest.domain.dto.PaymentDTO;
import com.zup.rest.domain.dto.TransactionDTO;
import org.springframework.cloud.client.loadbalancer.reactive.Response;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient(name = "zuppay-processor")
@RequestMapping(path = "/user")
public interface ZuppayRestFeign {

  // TODO Configure Auth
  //  @GetMapping("/current")
  //  ResponseEntity<UserDTO> current(@RequestHeader("Authorization") String authorization);

  @PostMapping("/processor/")
  Response<TransactionDTO> createTransaction(@RequestBody PaymentDTO payment);
}
