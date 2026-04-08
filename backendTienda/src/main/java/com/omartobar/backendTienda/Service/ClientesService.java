package com.omartobar.backendTienda.Service;

import com.omartobar.backendTienda.Entity.Clientes;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ClientesService {

    List<Clientes> getAllClientes();
    Clientes getClienteById(Integer id);
    Clientes saveCliente(Clientes cliente) throws RuntimeException;
    Clientes updateCliente(Integer id, Clientes cliente);
    void deleteCliente(Integer id);

}