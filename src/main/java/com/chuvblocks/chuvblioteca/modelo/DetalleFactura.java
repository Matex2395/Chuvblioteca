package com.chuvblocks.chuvblioteca.modelo;

import java.math.*;

import javax.persistence.*;

import org.openxava.annotations.*;

import lombok.*;

@Embeddable @Getter @Setter
public class DetalleFactura {

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private Libro libro;

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