package com.globalyofi.backend.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "categoria")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Categoria {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idCategoria;

    @Column(nullable = false)
    private String nombre;

    private String descripcion;
    private Boolean activa;

    @Column(name = "fecha_creacion")
    private LocalDateTime fechaCreacion;
}
