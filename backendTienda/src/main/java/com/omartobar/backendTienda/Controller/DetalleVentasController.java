package com.omartobar.backendTienda.Controller;

import com.omartobar.backendTienda.Entity.DetalleVentas;
import com.omartobar.backendTienda.Service.DetalleVentasService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/detalleventas")

public class DetalleVentasController {

    private final DetalleVentasService detalleVentasService;

    public DetalleVentasController(DetalleVentasService detalleVentasService) {
        this.detalleVentasService = detalleVentasService;
    }

    @GetMapping
    public List<DetalleVentas> getAllDetalleVentas() {
        return detalleVentasService.getAllDetalleVentas();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getDetalleVentaById(@PathVariable Integer id) {
        try {
            DetalleVentas detalleSolicitado = detalleVentasService.getDetalleVentaById(id);
            return new ResponseEntity<>(detalleSolicitado, HttpStatus.ACCEPTED);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping
    public ResponseEntity<Object> createDetalleVenta(@Valid @RequestBody DetalleVentas detalleVenta) {
        try {
            DetalleVentas createdDetalle = detalleVentasService.saveDetalleVenta(detalleVenta);
            return new ResponseEntity<>(createdDetalle, HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateDetalleVenta(
            @PathVariable Integer id,
            @Valid @RequestBody DetalleVentas detalleVenta) {

        try {
            detalleVentasService.updateDetalleVenta(id, detalleVenta);
            return ResponseEntity.ok("Detalle de venta actualizado correctamente");
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Detalle de venta no encontrado");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteDetalleVenta(@PathVariable Integer id) {
        try {
            detalleVentasService.deleteDetalleVenta(id);
            return ResponseEntity.ok("Detalle de venta eliminado correctamente");
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Detalle de venta no encontrado");
        }
    }
}
