package com.chuvblocks.chuvblioteca.modelo;

import javax.persistence.*;

import org.openxava.annotations.*;

import lombok.*;

@Entity @Getter @Setter
@View(name = "Simple", members = "titulo, autor, categoria, stock")
@Tab(properties = "titulo, autor.nombre, categoria.nombre, stock")
public class Libro {

    @Id
    @Hidden
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 150)
    @Required
    private String titulo;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @ReferenceView("Simple")
    private Autor autor;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @ReferenceView("Simple")
    private Categoria categoria;

 // La logica de control de stock se implementará en el segundo avance
    @Required
    private int stock; 
}
