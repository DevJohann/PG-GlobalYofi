package com.globalyofi.backend.repository;

import com.globalyofi.backend.entity.Proveedor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProveedorRepository extends JpaRepository<Proveedor, Integer> {
    boolean existsByNit(String nit);
}
