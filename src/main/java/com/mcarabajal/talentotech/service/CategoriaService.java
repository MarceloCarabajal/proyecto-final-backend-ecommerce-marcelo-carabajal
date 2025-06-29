package com.mcarabajal.talentotech.service;

import com.mcarabajal.talentotech.entity.Categoria;
import com.mcarabajal.talentotech.repository.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoriaService {

    private CategoriaRepository repo;

    @Autowired
    public CategoriaService(CategoriaRepository repo) {
        this.repo = repo;
    }

    public List<Categoria> getCategorias(){
        return repo.findAll();
    }

    public Categoria getCategoriaById(Long id) {
        return repo.findById(id).orElse(null);
    }

    public Categoria createCategoria(Categoria categoria) {
        repo.save(categoria);
        return categoria;
    }

    public Categoria updateCategoria(Long id, Categoria datosActualizados) {
        Categoria categoriaExistente = repo.findById(id)
                .orElse(null);

        if (categoriaExistente != null) {
            categoriaExistente.setNombre(datosActualizados.getNombre());
            return repo.save(categoriaExistente);
        }

        return  null;
    }

    public void deleteCategoria(Long id) {
        repo.deleteById(id);
    }
}
