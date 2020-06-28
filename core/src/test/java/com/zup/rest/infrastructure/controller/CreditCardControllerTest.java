package com.zup.rest.infrastructure.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.google.gson.Gson;
import com.zup.rest.domain.dto.CreditCardDTO;
import java.util.UUID;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@WebMvcTest(value = CreditCardController.class)
class CreditCardControllerTest {

  @Autowired private MockMvc mockMvc;
  @MockBean private CreditCardController controller;
  private final String path = "/credit-card/";

  @Test
  void mustReturnStatusOkWhenPerformGetMapping() throws Exception {
    mockMvc.perform(get(path)).andExpect(status().isOk()).andReturn();
  }

  @Test
  void mustReturnStatusOkWhenPerformGetMappingWithId() throws Exception {
    var uuid = UUID.randomUUID();
    var finalPath = String.format("%s%s", path, uuid);

    mockMvc
        .perform(get(finalPath).contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andReturn();
  }

  @Test
  void mustReturnStatusOkWhenPerformPostMappingWithContentBody() throws Exception {
    var content = new Gson().toJson(CreditCardDTO.builder().build());

    mockMvc
        .perform(post(path).contentType(MediaType.APPLICATION_JSON).content(content))
        .andExpect(status().isOk())
        .andReturn();
  }

  @Test
  void mustReturnStatusOkWhenPerformPutMappingWithContentBody() throws Exception {
    var content = new Gson().toJson(CreditCardDTO.builder().build());

    mockMvc
        .perform(put(path).contentType(MediaType.APPLICATION_JSON).content(content))
        .andExpect(status().isOk())
        .andReturn();
  }

  @Test
  void mustReturnStatusOkWhenPerformDeleteMappingWithContentBody() throws Exception {
    var content = new Gson().toJson(CreditCardDTO.builder().build());

    mockMvc
        .perform(
            MockMvcRequestBuilders.delete(path)
                .contentType(MediaType.APPLICATION_JSON)
                .content(content))
        .andExpect(status().isOk())
        .andReturn();
  }

  @Test
  void mustReturnStatusBadRequestWhenPerformPostMappingWithoutContent() throws Exception {
    mockMvc
        .perform(post(path).contentType(MediaType.APPLICATION_JSON).content(""))
        .andExpect(status().is(400))
        .andReturn();
  }

  @Test
  void mustReturnStatusBadRequestWhenPerformPutMappingWithoutContent() throws Exception {
    mockMvc
        .perform(put(path).contentType(MediaType.APPLICATION_JSON).content(""))
        .andExpect(status().is(400))
        .andReturn();
  }

  @Test
  void mustReturnStatusBadRequestWhenPerformDeleteMappingWithoutContent() throws Exception {
    mockMvc
        .perform(
            MockMvcRequestBuilders.delete(path).contentType(MediaType.APPLICATION_JSON).content(""))
        .andExpect(status().is(400))
        .andReturn();
  }
}
