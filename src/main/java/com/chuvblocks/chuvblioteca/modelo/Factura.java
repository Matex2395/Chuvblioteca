package com.chuvblocks.chuvblioteca.modelo;

import java.math.*;
import java.time.*;
import java.util.*;

import javax.persistence.*;

import org.openxava.annotations.*;

import lombok.*;

@Entity @Getter @Setter
public class Factura {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Required
    private LocalDate fecha;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private Cliente cliente;

    @ElementCollection
    @ListProperties("libro.titulo, libro.autor.nombre, libro.categoria.nombre, cantidad, precioUnitario, subtotal")
    private Collection<DetalleFactura> detalles;

    @Stereotype("DINERO")
    @ReadOnly
    @Calculation("sum(detalles.subtotal)")
    private BigDecimal total;
}