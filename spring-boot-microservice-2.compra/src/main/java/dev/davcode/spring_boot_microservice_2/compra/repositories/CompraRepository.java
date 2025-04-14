package dev.davcode.spring_boot_microservice_2.compra.repositories;

import dev.davcode.spring_boot_microservice_2.compra.entities.Compra;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CompraRepository extends JpaRepository<Compra, Long> {

    List<Compra> findAllByUserId(Long userId);
}
