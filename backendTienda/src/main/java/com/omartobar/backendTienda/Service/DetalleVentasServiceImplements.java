package com.omartobar.backendTienda.Service;

import com.omartobar.backendTienda.Entity.DetalleVentas;
import com.omartobar.backendTienda.Repository.DetalleVentasRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DetalleVentasServiceImplements implements DetalleVentasService {

    private final DetalleVentasRepository detalleVentasRepository;

    public DetalleVentasServiceImplements(DetalleVentasRepository detalleVentasRepository) {
        this.detalleVentasRepository = detalleVentasRepository;
    }

    @Override
    public List<DetalleVentas> getAllDetalleVentas() {
        return detalleVentasRepository.findAll();
    }

    @Override
    public DetalleVentas getDetalleVentaById(Integer id) {
        return detalleVentasRepository.findById(id).orElse(null);
    }

    @Override
    public DetalleVentas saveDetalleVenta(DetalleVentas detalleVenta) throws RuntimeException {
        return detalleVentasRepository.save(detalleVenta);
    }

    @Override
    public DetalleVentas updateDetalleVenta(Integer id, DetalleVentas detalleVenta) {
        Optional<DetalleVentas> detalleExistente = detalleVentasRepository.findById(id);

        if (detalleExistente.isPresent()) {
            DetalleVentas newDetalle = detalleExistente.get();
            newDetalle.setCantidad(detalleVenta.getCantidad());
            newDetalle.setPrecioUnitario(detalleVenta.getPrecioUnitario());
            newDetalle.setSubtotal(detalleVenta.getSubtotal());
            newDetalle.setCodigoProducto(detalleVenta.getCodigoProducto());
            newDetalle.setCodigoVenta(detalleVenta.getCodigoVenta());

            return detalleVentasRepository.save(newDetalle);
        } else {
            return null;
        }
    }

    @Override
    public void deleteDetalleVenta(Integer id) {
        detalleVentasRepository.deleteById(id);
    }
}
