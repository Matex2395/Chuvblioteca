package com.chuvblocks.chuvblioteca.modelo;

import java.math.*;

import javax.persistence.*;

import org.openxava.annotations.*;

import lombok.*;

@Entity @Getter @Setter
public class DetalleFactura {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private Libro libro;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
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