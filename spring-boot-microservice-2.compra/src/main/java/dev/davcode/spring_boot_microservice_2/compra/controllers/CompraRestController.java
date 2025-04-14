package dev.davcode.spring_boot_microservice_2.compra.controllers;

import dev.davcode.spring_boot_microservice_2.compra.entities.Compra;
import dev.davcode.spring_boot_microservice_2.compra.services.CompraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/compra")
public class CompraRestController {

    @Autowired
    private CompraService compraService;

    @PostMapping
    public ResponseEntity<?> saveCompra(@RequestBody Compra compra) {
        return new ResponseEntity<Compra>(compraService.saveCompra(compra), HttpStatus.CREATED);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<?> findAllComprasOfUser(@PathVariable Long userId) {
        return ResponseEntity.ok(compraService.findAllComprasOfUser(userId));
    }
}
