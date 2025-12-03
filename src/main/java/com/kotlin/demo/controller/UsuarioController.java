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

import com.kotlin.demo.model.Usuario;
import com.kotlin.demo.service.UsuarioService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/users")
@Tag(name = "Usuario Controller", description = "Operaciones CRUD para Usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping
    @Operation(summary = "Ver a todos los Usuarios")
    public List<Usuario> getAllUsers() {
        return usuarioService.getAllUsers();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Ver un Usuario por ID")
    public Usuario getUserById(@PathVariable Long id) {
        return usuarioService.getUserById(id);
    }

    @PostMapping
    @Operation(summary = "Agregar un nuevo Usuario")
    public Usuario createUser(@RequestBody Usuario usuario) {
        return usuarioService.saveUser(usuario);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar un Usuario existente")
    public Usuario updateUser(@PathVariable Long id, @RequestBody Usuario usuario) {
        Usuario existingUser = usuarioService.getUserById(id);
        if (existingUser != null) {
            existingUser.setName(usuario.getName());
            existingUser.setCorreo(usuario.getCorreo());
            existingUser.setContrasena(usuario.getContrasena());
            return usuarioService.saveUser(existingUser);
        }
        return null;
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar un Usuario por ID")
    public void deleteUser(@PathVariable Long id) {
        usuarioService.deleteUser(id);
    }
}
