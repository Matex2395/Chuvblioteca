package com.chuvblocks.chuvblioteca.modelo;

import java.math.*;
import java.time.*;
import java.util.*;

import javax.persistence.*;

import org.openxava.annotations.*;

import lombok.*;

@Entity @Getter @Setter
@View(name = "Simple", members =
"fecha, cliente;" +
"total")
@Tab(properties = "fecha, cliente.nombre, total")
public class Factura {

    @Id
    @Hidden
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Required
    private LocalDate fecha;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @ReferenceView("Simple")
    private Cliente cliente;

    @OneToMany(mappedBy = "factura", cascade = CascadeType.ALL)
    @ListProperties("libro.titulo, cantidad, precioUnitario, subtotal")
    private Collection<DetalleFactura> detallefactura;

    @Stereotype("DINERO")
    @ReadOnly
    @Depends("detallefactura")
    public BigDecimal getTotal() {
        if (detallefactura == null) return BigDecimal.ZERO;
        return detallefactura.stream()
            .map(DetalleFactura::getSubtotal)
            .filter(Objects::nonNull)
            .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}