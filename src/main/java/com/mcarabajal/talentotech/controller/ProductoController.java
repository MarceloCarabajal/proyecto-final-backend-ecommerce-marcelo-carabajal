package com.mcarabajal.talentotech.controller;

import com.mcarabajal.talentotech.entity.Producto;
import com.mcarabajal.talentotech.service.ProductoService;
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

    @GetMapping("/hello")
    public String hello(){
        return "Hola, bienvenido";
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
    public Producto createProducto(@RequestBody Producto producto){
        Producto productoCreado = productService.createProducto(producto);
        return productoCreado;
    }

    @PutMapping("/{id}")
    public ResponseEntity<Producto> updateProducto(@PathVariable Long id, @RequestBody Producto productoActualizado){
        Producto productoExistente = productService.updateProducto(id, productoActualizado);
        return productoExistente != null ? ResponseEntity.ok(productoExistente) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Producto> deleteProduct (@PathVariable Long id){
        productService.deleteProducto(id);
        return ResponseEntity.noContent().build();
    }

}
