package com.unir.operador.controller;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.unir.operador.model.Entities.MercanciaEntity;
import com.unir.operador.model.Entities.OrdenEntity;
import com.unir.operador.model.request.OrdenRequest;
import com.unir.operador.service.OrdersService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequiredArgsConstructor
@Slf4j
public class OrdersController {

	private final OrdersService service;

	@PostMapping("/disponibilidad")
	public ResponseEntity<List<MercanciaEntity> > createOrder(@RequestBody OrdenRequest request) {

		List<MercanciaEntity> result = service.disponibilidadMercancia(request);

		if (request != null) {
			return ResponseEntity.ok(result);
		} else {
			return ResponseEntity.badRequest().build();
		}

	}

	@GetMapping("/ordenes")
	public ResponseEntity<List<OrdenEntity>> getProducts(@RequestHeader Map<String, String> headers) {

		log.info("*********************************************headers: {}", headers);
		List<OrdenEntity> ordenes = service.getOrdenes();

		if (ordenes != null) {
			return ResponseEntity.ok(ordenes);
		} else {
			return ResponseEntity.ok(Collections.emptyList());
		}
	}

	@PostMapping("/orden")
	public ResponseEntity<OrdenEntity> crearOrden(@RequestBody OrdenRequest request) {

		OrdenEntity result = service.crearOrden(request);

		if (result != null) {
			return ResponseEntity.status(HttpStatus.CREATED).body(result);
		} else {
			return ResponseEntity.badRequest().build();
		}

	}

}
