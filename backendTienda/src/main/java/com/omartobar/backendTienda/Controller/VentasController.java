package com.omartobar.backendTienda.Controller;

import com.omartobar.backendTienda.Entity.Ventas;
import com.omartobar.backendTienda.Service.VentasService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/ventas")

public class VentasController {

    private final VentasService ventasService;

    public VentasController(VentasService ventasService) {
        this.ventasService = ventasService;
    }

    @GetMapping
    public List<Ventas> getAllVentas() {
        return ventasService.getAllVentas();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getVentaById(@PathVariable Integer id) {
        try {
            Ventas ventaSolicitada = ventasService.getVentaById(id);
            return new ResponseEntity<>(ventaSolicitada, HttpStatus.ACCEPTED);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping
    public ResponseEntity<Object> createVenta(@Valid @RequestBody Ventas venta) {
        try {
            Ventas createdVenta = ventasService.saveVenta(venta);
            return new ResponseEntity<>(createdVenta, HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateVenta(
            @PathVariable Integer id,
            @Valid @RequestBody Ventas venta) {

        try {
            ventasService.updateVenta(id, venta);
            return ResponseEntity.ok("Venta actualizada correctamente");
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Venta no encontrada");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteVenta(@PathVariable Integer id) {
        try {
            ventasService.deleteVenta(id);
            return ResponseEntity.ok("Venta eliminada correctamente");
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Venta no encontrada");
        }
    }
}
