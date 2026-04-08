package com.omartobar.backendTienda.Service;

import com.omartobar.backendTienda.Entity.Productos;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ProductosService {

    List<Productos> getAllProductos();
    Productos getProductoById(Integer id);
    Productos saveProducto(Productos producto) throws RuntimeException;
    Productos updateProducto(Integer id, Productos producto);
    void deleteProducto(Integer id);

}
