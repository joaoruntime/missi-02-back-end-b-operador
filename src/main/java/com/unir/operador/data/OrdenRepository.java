package com.unir.operador.data;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.unir.operador.model.Entities.OrdenEntity;

public interface OrdenRepository extends JpaRepository<OrdenEntity, Long> {

	// List<MercanciaEntity> findByName(String name);

	// @Query(value = "SELECT * FROM your_table_name WHERE condition", nativeQuery = true)
    // List<MercanciaEntity> executeCustomQuery();
}
