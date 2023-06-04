package com.unir.operador.model.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

import com.unir.operador.model.Entities.MercanciaEntity;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrdenRequest {

    private String identificacion;
    private List<MercanciaEntity> mercancias;
    private String nombre;
    private String direccion;
    private String telefono;
    private String email;
}
