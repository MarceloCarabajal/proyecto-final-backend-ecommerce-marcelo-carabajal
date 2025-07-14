package com.mcarabajal.talentotech.service;

import com.mcarabajal.talentotech.dto.ProductoRequest;
import com.mcarabajal.talentotech.entity.Categoria;
import com.mcarabajal.talentotech.entity.Producto;
import com.mcarabajal.talentotech.exception.ProductoInvalidoException;
import com.mcarabajal.talentotech.repository.CategoriaRepository;
import com.mcarabajal.talentotech.repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductoService {

    @Autowired
    private CategoriaRepository categoriaRepository;

    private ProductoRepository productoRepository;

    @Autowired
    public ProductoService(ProductoRepository repo) {
        this.productoRepository = repo;
    }

    public List<Producto> getProductos(){
        return productoRepository.findAll();
    }

    public Producto getProductoById(Long id) {
        return productoRepository.findById(id).orElse(null);
    }

    public List<Producto> getProductosByNombre(String nombre) {
        return productoRepository.findByNombre(nombre);
    }

    public Producto createProducto(ProductoRequest request) {
        Categoria categoria = categoriaRepository.findById(request.getCategoriaId())
                .orElseThrow(() -> new ProductoInvalidoException("Categoría no encontrada: " + request.getCategoriaId()));

        Producto producto = new Producto();
        producto.setNombre(request.getNombre());
        producto.setPrecio(request.getPrecio());
        producto.setCantidadEnStock(request.getCantidadEnStock());
        producto.setDescripcion(request.getDescripcion());
        producto.setCategoria(categoria);

        return productoRepository.save(producto);
    }

    public Producto updateProducto(Long id, ProductoRequest request) {
        Producto productoExistente = productoRepository.findById(id)
                .orElse(null);

        if (productoExistente != null) {
            productoExistente.setNombre(request.getNombre());
            productoExistente.setPrecio(request.getPrecio());
            productoExistente.setCantidadEnStock(request.getCantidadEnStock());
            if (request.getCategoriaId() != null) {
                Categoria categoria = categoriaRepository.findById(request.getCategoriaId()).orElse(null);
                productoExistente.setCategoria(categoria);
            }
            productoExistente.setDescripcion(request.getDescripcion());

            return productoRepository.save(productoExistente);
        }

        return  null;
    }

    public void deleteProducto(Long id) {
        productoRepository.deleteById(id);
    }

}
