package dev.davcode.spring_boot_microservice_2.compra.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@Entity
@Table(name = "compra")
public class Compra {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NonNull
    @Column(name = "user_id", nullable = false)
    private Long userId;

    @NonNull
    @Column(name = "inmueble_id", nullable = false)
    private Long inmuebleId;

    @NonNull
    @Column(name = "titulo", nullable = false)
    private String titulo;

    @NonNull
    @Column(name = "precio", nullable = false)
    private Double precio;

    @Column(name = "fecha_compra", nullable = false)
    private LocalDateTime fechaCompra;

}
