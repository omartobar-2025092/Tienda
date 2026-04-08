package com.omartobar.backendTienda.Service;

import com.omartobar.backendTienda.Entity.Productos;
import com.omartobar.backendTienda.Repository.ProductosRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductosServiceImplements implements ProductosService {

    private final ProductosRepository productosRepository;

    public ProductosServiceImplements(ProductosRepository productosRepository) {
        this.productosRepository = productosRepository;
    }

    @Override
    public List<Productos> getAllProductos() {
        return productosRepository.findAll();
    }

    @Override
    public Productos getProductoById(Integer id) {
        return productosRepository.findById(id).orElse(null);
    }

    @Override
    public Productos saveProducto(Productos producto) throws RuntimeException {
        return productosRepository.save(producto);
    }

    @Override
    public Productos updateProducto(Integer id, Productos producto) {
        Optional<Productos> productoExistente = productosRepository.findById(id);

        if (productoExistente.isPresent()) {
            Productos newProducto = productoExistente.get();
            newProducto.setNombreProducto(producto.getNombreProducto());
            newProducto.setPrecio(producto.getPrecio());
            newProducto.setStock(producto.getStock());
            newProducto.setEstado(producto.getEstado());

            return productosRepository.save(newProducto);
        } else {
            return null;
        }
    }

    @Override
    public void deleteProducto(Integer id) {
        productosRepository.deleteById(id);
    }
}