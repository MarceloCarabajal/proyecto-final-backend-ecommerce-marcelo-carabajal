package com.mcarabajal.talentotech.service;

import com.mcarabajal.talentotech.entity.Categoria;
import com.mcarabajal.talentotech.entity.Producto;
import com.mcarabajal.talentotech.repository.CategoriaRepository;
import com.mcarabajal.talentotech.repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductoService {

    @Autowired
    private CategoriaRepository categoriaRepository;

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
        if (producto.getCategoria() != null && producto.getCategoria().getId() != null) {
            Categoria categoria = categoriaRepository.findById(producto.getCategoria().getId()).orElse(null);
            producto.setCategoria(categoria);
        }
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
            if (datosActualizados.getCategoria() != null && datosActualizados.getCategoria().getId() != null) {
                Categoria categoria = categoriaRepository.findById(datosActualizados.getCategoria().getId()).orElse(null);
                productoExistente.setCategoria(categoria);
            }
            return repo.save(productoExistente);
        }

        return  null;
    }

    public void deleteProducto(Long id) {
        repo.deleteById(id);
    }

}
