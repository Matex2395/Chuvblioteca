package com.chuvblocks.chuvblioteca.services;

import java.util.*;
import java.util.stream.*;

import javax.inject.*;

import org.openxava.actions.*;
import org.openxava.jpa.*;

import com.chuvblocks.chuvblioteca.modelo.*;

public class AccionAutoresMasLibros extends ViewBaseAction {

    @Inject
    private LibroService libroService;

    @Override
    public void execute() throws Exception {
        List<Libro> libros = XPersistence.getManager()
            .createQuery("FROM Libro", Libro.class)
            .getResultList();

        List<Autor> autores = libroService.obtenerAutoresConMasLibros(libros);

        if (autores.isEmpty()) {
            addInfo("No se encontraron autores con libros.");
            return;
        }

        String nombres = autores.stream()
            .map(Autor::getNombre)
            .collect(Collectors.joining(", "));

        addInfo("Autor(es) con más libros: " + nombres);
    }
}
