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

    @OneToMany(mappedBy = "factura", cascade = CascadeType.ALL)
    @ListProperties("libro.titulo, cantidad, precioUnitario, subtotal")
    private Collection<DetalleFactura> detallefactura;

    @Stereotype("DINERO")
    @ReadOnly
    @Calculation("sum(detallefactura.subtotal)")
    private BigDecimal total;
}