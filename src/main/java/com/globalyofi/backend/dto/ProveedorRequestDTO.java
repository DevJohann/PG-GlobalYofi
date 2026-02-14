package com.globalyofi.backend.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class ProveedorRequestDTO {

    @NotBlank(message = "El nombre del proveedor es obligatorio")
    private String nombre;

    private String contactoPrincipal;

    @NotBlank(message = "El teléfono del proveedor es obligatorio")
    private String telefono;

    @Email(message = "El correo electrónico no tiene un formato válido")
    private String email;

    private String direccion;
    private String ciudad;

    @NotBlank(message = "El NIT es obligatorio")
    private String nit;

    private String estado;
}
