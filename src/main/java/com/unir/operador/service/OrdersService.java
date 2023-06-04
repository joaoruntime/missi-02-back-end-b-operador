package com.unir.operador.service;

import com.unir.operador.model.request.OrdenRequest;

import java.util.List;

import com.unir.operador.model.Entities.MercanciaEntity;
import com.unir.operador.model.Entities.OrdenEntity;

public interface OrdersService {

	List<MercanciaEntity> disponibilidadMercancia(OrdenRequest request);

	OrdenEntity crearOrden(OrdenRequest request);

	List<OrdenEntity> getOrdenes();
}
