package com.kotlin.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kotlin.demo.model.Resena;
import com.kotlin.demo.service.ResenaService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/v1/resenas")
@Tag(name = "Resena Controller", description = "Operaciones CRUD para Resenas")
public class ResenaController {

    @Autowired
    private ResenaService resenaService;

    @GetMapping
    @Operation(summary = "Ver todas las Resenas")
    public List<Resena> getAllResenas() {
        return resenaService.getAllResenas();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Ver una Resena por ID")
    public Resena getResenaById(@PathVariable Long id) {
        return resenaService.getResenaById(id);
    }

    @PostMapping
    @Operation(summary = "Agregar una nueva Resena")
    public Resena createResena(@RequestBody Resena resena) {
        return resenaService.saveResena(resena);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar una Resena existente")
    public Resena updateResena(@PathVariable Long id, @RequestBody Resena resena) {
        Resena existingResena = resenaService.getResenaById(id);
        if (existingResena != null) {
            existingResena.setTitle(resena.getTitle());
            existingResena.setDescription(resena.getDescription());
            existingResena.setCategoria(resena.getCategoria());
            return resenaService.saveResena(existingResena);
        }
        return null; 
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar una Resena por ID")
    public void deleteResena(@PathVariable Long id) {
        resenaService.deleteResena(id);
    }
}
