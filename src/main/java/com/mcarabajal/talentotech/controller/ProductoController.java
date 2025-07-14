package com.mcarabajal.talentotech.controller;

import com.mcarabajal.talentotech.dto.ProductoRequest;
import com.mcarabajal.talentotech.entity.Producto;
import com.mcarabajal.talentotech.service.ProductoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/productos")
public class ProductoController {

    private ProductoService productService;

    @Autowired
    public ProductoController(ProductoService productService) {
        this.productService = productService;
    }

    @GetMapping()
    public List<Producto> getProductos(){
        return productService.getProductos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Producto> getProductoById(@PathVariable Long id) {
        Producto producto = productService.getProductoById(id);
        return producto != null ? ResponseEntity.ok(producto) : ResponseEntity.notFound().build();
    }

    @GetMapping("/search/{nombre}")
    public List<Producto> getProductoByNombre(@PathVariable String nombre){
        return productService.getProductosByNombre(nombre);
    }

    @PostMapping
    public ResponseEntity<Producto> createProducto(@Valid @RequestBody ProductoRequest request){
        Producto productoCreado = productService.createProducto(request);
        return ResponseEntity.ok(productoCreado);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Producto> updateProducto(@PathVariable Long id, @Valid @RequestBody ProductoRequest request){
        Producto actualizado = productService.updateProducto(id, request);
        return actualizado != null ? ResponseEntity.ok(actualizado) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Producto> deleteProduct (@PathVariable Long id){
        productService.deleteProducto(id);
        return ResponseEntity.noContent().build();
    }

}
