package com.chuvblocks.chuvblioteca.services;

import java.util.*;

import javax.ejb.*;

import com.chuvblocks.chuvblioteca.modelo.*;

@Stateless
public class LibroService {
	
	public LibroService() { 
    }
	
    public List<Autor> obtenerAutoresConMasLibros(List<Libro> libros) {
        Map<Autor, Integer> contador = new HashMap<>();
        if (libros == null) return Collections.emptyList();

        for (Libro libro : libros) {
            if (libro == null || libro.getAutor() == null) continue;
            contador.put(libro.getAutor(), contador.getOrDefault(libro.getAutor(), 0) + 1);
        }

        int max = 0;
        for (Integer count : contador.values()) {
            if (count > max) max = count;
        }

        List<Autor> resultado = new ArrayList<>();
        for (Map.Entry<Autor, Integer> entry : contador.entrySet()) {
            if (entry.getValue() == max) {
                resultado.add(entry.getKey());
            }
        }

        return resultado;
    }
}