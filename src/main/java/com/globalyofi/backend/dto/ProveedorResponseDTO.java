package com.globalyofi.backend.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class ProveedorResponseDTO {

    private Integer id;
    private String nombre;
    private String contactoPrincipal;
    private String telefono;
    private String email;
    private String direccion;
    private String ciudad;
    private String nit;
    private String estado;
    private LocalDateTime fechaRegistro;
}
