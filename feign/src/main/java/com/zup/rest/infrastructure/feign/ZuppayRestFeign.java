package com.zup.rest.infrastructure.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@FeignClient(name = "zuppay-processor")
@RestController
public interface ZuppayRestFeign {

  // TODO Configure Auth
  //  @GetMapping("/current")
  //  ResponseEntity<UserDTO> current(@RequestHeader("Authorization") String authorization);

  @RequestMapping("/due-payment/")
  ResponseEntity<HttpStatus> createTransaction();
}
