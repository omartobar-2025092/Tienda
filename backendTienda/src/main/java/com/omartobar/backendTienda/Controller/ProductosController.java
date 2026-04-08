package com.omartobar.backendTienda.Controller;

import com.omartobar.backendTienda.Entity.Productos;
import com.omartobar.backendTienda.Service.ProductosService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/productos")

public class ProductosController {

    private final ProductosService productosService;

    public ProductosController(ProductosService productosService) {
        this.productosService = productosService;
    }

    @GetMapping
    public List<Productos> getAllProductos() {
        return productosService.getAllProductos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getProductoById(@PathVariable Integer id) {
        try {
            Productos productoSolicitado = productosService.getProductoById(id);
            return new ResponseEntity<>(productoSolicitado, HttpStatus.ACCEPTED);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping
    public ResponseEntity<Object> createProducto(@Valid @RequestBody Productos producto) {
        try {
            Productos createdProducto = productosService.saveProducto(producto);
            return new ResponseEntity<>(createdProducto, HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateProducto(
            @PathVariable Integer id,
            @Valid @RequestBody Productos producto) {

        try {
            productosService.updateProducto(id, producto);
            return ResponseEntity.ok("Producto actualizado correctamente");
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Producto no encontrado");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteProducto(@PathVariable Integer id) {
        try {
            productosService.deleteProducto(id);
            return ResponseEntity.ok("Producto eliminado correctamente");
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Producto no encontrado");
        }
    }
}
