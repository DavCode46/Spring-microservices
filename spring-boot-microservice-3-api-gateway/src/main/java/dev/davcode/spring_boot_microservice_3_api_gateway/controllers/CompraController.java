package dev.davcode.spring_boot_microservice_3_api_gateway.controllers;

import dev.davcode.spring_boot_microservice_3_api_gateway.request.ComprasServiceRequest;
import dev.davcode.spring_boot_microservice_3_api_gateway.request.InmuebleServiceRequest;
import dev.davcode.spring_boot_microservice_3_api_gateway.security.UserPrincipal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/gateway/compra")
public class CompraController {

    @Autowired
    private ComprasServiceRequest comprasServiceRequest;

    @PostMapping
    public ResponseEntity<?> saveCompra(@RequestBody Object compra) {
        return new ResponseEntity<>(comprasServiceRequest.saveCompra(compra), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<?> getAllComprasOfUser(@AuthenticationPrincipal UserPrincipal userPrincipal) {
        return ResponseEntity.ok(comprasServiceRequest.getAllComprasOfUser(userPrincipal.getId()));
    }

}
