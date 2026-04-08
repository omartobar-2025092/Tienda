package com.omartobar.backendTienda.Service;

import com.omartobar.backendTienda.Entity.Clientes;
import com.omartobar.backendTienda.Repository.ClientesRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClientesServiceImplements implements ClientesService {

    private final ClientesRepository clientesRepository;

    public ClientesServiceImplements(ClientesRepository clientesRepository) {
        this.clientesRepository = clientesRepository;
    }

    @Override
    public List<Clientes> getAllClientes() {
        return clientesRepository.findAll();
    }

    @Override
    public Clientes getClienteById(Integer id) {
        return clientesRepository.findById(id).orElse(null);
    }

    @Override
    public Clientes saveCliente(Clientes cliente) throws RuntimeException {
        return clientesRepository.save(cliente);
    }

    @Override
    public Clientes updateCliente(Integer id, Clientes cliente) {
        Optional<Clientes> clienteExistente = clientesRepository.findById(id);

        if (clienteExistente.isPresent()) {
            Clientes newCliente = clienteExistente.get();
            newCliente.setNombreCliente(cliente.getNombreCliente());
            newCliente.setApellidoCliente(cliente.getApellidoCliente());
            newCliente.setDireccion(cliente.getDireccion());
            newCliente.setEstado(cliente.getEstado());

            return clientesRepository.save(newCliente);
        } else {
            return null;
        }
    }

    @Override
    public void deleteCliente(Integer id) {
        clientesRepository.deleteById(id);
    }
}