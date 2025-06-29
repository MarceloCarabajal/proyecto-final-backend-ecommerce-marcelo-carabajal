package com.mcarabajal.talentotech.service;

import com.mcarabajal.talentotech.entity.Producto;
import com.mcarabajal.talentotech.repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductoService {

    private ProductoRepository repo;

    @Autowired
    public ProductoService(ProductoRepository repo) {
        this.repo = repo;
    }

    public List<Producto> getProductos(){
        return repo.findAll();
    }

    public Producto getProductoById(Long id) {
        return repo.findById(id).orElse(null);
    }

    public List<Producto> getProductosByNombre(String nombre) {
        return repo.findByNombre(nombre);
    }

    public Producto createProducto(Producto producto) {
        repo.save(producto);
        return producto;
    }

    public Producto updateProducto(Long id, Producto datosActualizados) {
        Producto productoExistente = repo.findById(id)
                .orElse(null);

        if (productoExistente != null) {
            productoExistente.setNombre(datosActualizados.getNombre());
            productoExistente.setPrecio(datosActualizados.getPrecio());
            productoExistente.setCantidadEnStock(datosActualizados.getCantidadEnStock());
            return repo.save(productoExistente);
        }

        return  null;
    }

    public void deleteProducto(Long id) {
        repo.deleteById(id);
    }

}
