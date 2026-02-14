package com.globalyofi.backend.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class CategoriaResponseDTO {
    private Integer id;
    private String nombre;
    private String descripcion;
    private Boolean activa;
    private LocalDateTime fechaCreacion;
}
