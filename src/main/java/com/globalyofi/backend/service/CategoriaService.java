package com.globalyofi.backend.service;

import com.globalyofi.backend.dto.CategoriaRequestDTO;
import com.globalyofi.backend.dto.CategoriaResponseDTO;
import com.globalyofi.backend.entity.Categoria;
import com.globalyofi.backend.repository.CategoriaRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoriaService {

    @Autowired
    private CategoriaRepository categoriaRepository;

    public List<CategoriaResponseDTO> obtenerTodas() {
        return categoriaRepository.findAll()
                .stream()
                .map(this::convertirAResponse)
                .collect(Collectors.toList());
    }

    public CategoriaResponseDTO crear(CategoriaRequestDTO dto) {
        if (categoriaRepository.existsByNombre(dto.getNombre())) {
            throw new IllegalArgumentException("Ya existe una categoría con ese nombre");
        }

        Categoria categoria = Categoria.builder()
                .nombre(dto.getNombre())
                .descripcion(dto.getDescripcion())
                .activa(dto.getActiva())
                .build();

        categoriaRepository.save(categoria);
        return convertirAResponse(categoria);
    }

    public CategoriaResponseDTO actualizar(Integer id, CategoriaRequestDTO dto) {
        Categoria categoria = categoriaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Categoría no encontrada"));

        categoria.setNombre(dto.getNombre());
        categoria.setDescripcion(dto.getDescripcion());
        categoria.setActiva(dto.getActiva());

        categoriaRepository.save(categoria);
        return convertirAResponse(categoria);
    }

    public void eliminar(Integer id) {
        Categoria categoria = categoriaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Categoría no encontrada"));
        categoriaRepository.delete(categoria);
    }

    private CategoriaResponseDTO convertirAResponse(Categoria categoria) {
        return CategoriaResponseDTO.builder()
                .id(categoria.getIdCategoria())
                .nombre(categoria.getNombre())
                .descripcion(categoria.getDescripcion())
                .activa(categoria.getActiva())
                .fechaCreacion(categoria.getFechaCreacion())
                .build();
    }
}
