package com.unir.operador.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import com.unir.operador.data.OrdenRepository;
import com.unir.operador.facade.ProductsFacade;
import com.unir.operador.model.Entities.MercanciaEntity;
import com.unir.operador.model.Entities.OrdenEntity;
import com.unir.operador.model.request.OrdenRequest;
import org.springframework.util.StringUtils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class OrdersServiceImpl implements OrdersService {

  @Autowired
  private ProductsFacade productsFacade;

  @Autowired
  private OrdenRepository ordenRepository;

  @Override
  public List<MercanciaEntity> disponibilidadMercancia(OrdenRequest request) {
    List<MercanciaEntity> mercancias = request.getMercancias().stream().map(productsFacade::getProduct)
        .filter(Objects::nonNull).collect(Collectors.toList());
    return mercancias;
  }

  @Override
  public OrdenEntity crearOrden(OrdenRequest request) {
    List<MercanciaEntity> disponibilidad = this.disponibilidadMercancia(request);

    if (disponibilidad.size() != request.getMercancias().size())
      return null;

    for (int i = 0; i < disponibilidad.size(); i++) {
      MercanciaEntity mercanciaToUpdate = disponibilidad.get(i);
      MercanciaEntity mercanciaOrden = request.getMercancias().stream()
          .filter(value -> value.getId() == mercanciaToUpdate.getId()).findFirst().get();
      mercanciaToUpdate.setCantidad(mercanciaToUpdate.getCantidad() - mercanciaOrden.getCantidad());
      productsFacade.updateProduct(mercanciaToUpdate);

    }


    if (request != null && StringUtils.hasLength(request.getIdentificacion().trim())
        && !request.getMercancias().isEmpty()) {

      List<MercanciaEntity> mercancias = request.getMercancias();

      ObjectMapper mapper = new ObjectMapper();

      List<String> json = mercancias.stream().map(value -> {
        try {
          return mapper.writeValueAsString(value);
        } catch (JsonProcessingException e) {
          // TODO Auto-generated catch block
          e.printStackTrace();
        }
        return null;
      }).collect(Collectors.toList());

      OrdenEntity orden = OrdenEntity.builder().identificacion(request.getIdentificacion()).mercancias(json)
          .nombre(request.getNombre()).direccion(request.getDireccion())
          .telefono(request.getTelefono()).email(request.getEmail()).build();

      return ordenRepository.save(orden);
    } else {
      return null;
    }
  }

  @Override
  public List<OrdenEntity> getOrdenes() {
    List<OrdenEntity> ordenes = ordenRepository.findAll();
    return ordenes.isEmpty() ? null : ordenes;
  }

}
