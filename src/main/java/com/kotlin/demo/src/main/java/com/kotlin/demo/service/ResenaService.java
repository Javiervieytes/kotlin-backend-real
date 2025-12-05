package com.kotlin.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kotlin.demo.model.Resena;
import com.kotlin.demo.repository.ResenaRepository;
import com.kotlin.demo.repository.UsuarioRepository;

@Service
public class ResenaService {

    @Autowired
    private ResenaRepository resenaRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    public List<Resena> getAllResenas() {
        return resenaRepository.findAll();
    }

    public Resena getResenaById(Long id) {
        return resenaRepository.findById(id).orElse(null);
    }

    public Resena saveResena(Resena resena) {
        if (resena.getUsuario() == null || resena.getUsuario().getId() == null) {
            throw new RuntimeException("La reseña debe tener un usuario con id");
        }

        usuarioRepository.findById(resena.getUsuario().getId())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        return resenaRepository.save(resena);
    }

    public void deleteResena(Long id) {
        resenaRepository.deleteById(id);
    }
}
