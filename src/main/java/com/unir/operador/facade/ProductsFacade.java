package com.unir.operador.facade;

import com.unir.operador.model.Entities.MercanciaEntity;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;

@Component
@RequiredArgsConstructor
@Slf4j
public class ProductsFacade {

  @Value("${getProduct.url}")
  private String getProductUrl;

  @Value("${updateMercancia.url}")
  private String updateMercanciaUrl;

  private final RestTemplate restTemplate;

  public MercanciaEntity getProduct(MercanciaEntity mercanciaOut) {
    try {
      return restTemplate.getForObject(String.format(getProductUrl, mercanciaOut.getId()), MercanciaEntity.class);
    } catch (HttpClientErrorException e) {
      log.error("Client Error: {}, Product with ID {}", e.getStatusCode(), mercanciaOut.getId());
      return null;
    }
  }

  public void updateProduct(MercanciaEntity product) {

    try {
      restTemplate.put(String.format(updateMercanciaUrl, product.getId()), product);
    } catch (HttpClientErrorException e) {
      log.error("Client Error: {}", e.getStatusCode());

    }
  }

}
