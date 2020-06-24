package com.zup.domain.dto;

import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@AllArgsConstructor
@NoArgsConstructor
@Data
@SuperBuilder
public class BaseDTO {
  private LocalDate createdDate;
  private LocalDate updatedDate;
  private LocalDate deletedDate;
}
