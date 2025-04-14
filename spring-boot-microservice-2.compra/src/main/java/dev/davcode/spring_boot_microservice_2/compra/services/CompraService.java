package dev.davcode.spring_boot_microservice_2.compra.services;

import dev.davcode.spring_boot_microservice_2.compra.entities.Compra;

import java.util.List;

public interface CompraService {
    Compra saveCompra(Compra compra);

    List<Compra> findAllComprasOfUser(Long userId);
}
