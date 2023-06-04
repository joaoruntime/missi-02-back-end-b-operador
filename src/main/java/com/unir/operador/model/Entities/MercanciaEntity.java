package com.unir.operador.model.Entities;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "mercancias")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class MercanciaEntity {
    @Id
	@GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "pk")
	private Long id;
	
	@Column(name = "cantidad")
	private Integer cantidad;
	

}
