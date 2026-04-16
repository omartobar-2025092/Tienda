package com.omartobar.backendTienda.Controller;

import com.omartobar.backendTienda.Entity.Clientes;
import com.omartobar.backendTienda.Service.ClientesService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clientes")

public class ClientesController {

    private final ClientesService clientesService;

    public ClientesController(ClientesService clientesService) {
        this.clientesService = clientesService;
    }

    @GetMapping
    public List<Clientes> getAllClientes() {
        return clientesService.getAllClientes();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getClienteById(@PathVariable Integer id) {
        try {
            Clientes clienteSolicitado = clientesService.getClienteById(id);
            return new ResponseEntity<>(clienteSolicitado, HttpStatus.ACCEPTED);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping
    public ResponseEntity<Object> createCliente(@Valid @RequestBody Clientes cliente) {
        try {
            Clientes createdCliente = clientesService.saveCliente(cliente);
            return new ResponseEntity<>(createdCliente, HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateCliente(
            @PathVariable Integer id,
            @Valid @RequestBody Clientes cliente) {

        try {
            clientesService.updateCliente(id, cliente);
            return ResponseEntity.ok("Cliente actualizado correctamente");
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cliente no encontrado");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteCliente(@PathVariable Integer id) {
        try {
            clientesService.deleteCliente(id);
            return ResponseEntity.ok("Cliente eliminado correctamente");
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cliente no encontrado");
        }
    }
}