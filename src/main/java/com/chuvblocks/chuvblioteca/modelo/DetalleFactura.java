package com.chuvblocks.chuvblioteca.modelo;

import java.math.*;

import javax.persistence.*;

import org.openxava.annotations.*;

import lombok.*;

@Entity @Getter @Setter
@Tab(properties = "libro.titulo, cantidad, precioUnitario, subtotal")
public class DetalleFactura {

    @Id
    @Hidden
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @ReferenceView("Simple")
    private Libro libro;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @ReferenceView("Simple")
    private Factura factura;

    @Required
    private int cantidad;

    @Stereotype("DINERO")
    @Required
    private BigDecimal precioUnitario;

    @Stereotype("DINERO")
    @ReadOnly
    @Calculation("cantidad * precioUnitario")
    private BigDecimal subtotal;
}