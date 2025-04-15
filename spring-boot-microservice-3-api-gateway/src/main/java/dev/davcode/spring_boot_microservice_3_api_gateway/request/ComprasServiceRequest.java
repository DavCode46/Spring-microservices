package dev.davcode.spring_boot_microservice_3_api_gateway.request;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(
        value="compra-service",
        path = "/api/compra",
        url = "${compras.service.url}",
        configuration = FeignConfiguration.class
)
public interface ComprasServiceRequest {

    @PostMapping
    Object saveCompra(@RequestBody Object requestBody);


    @GetMapping("/{id}")
    List<Object> getAllComprasOfUser(@PathVariable Long id);

}
