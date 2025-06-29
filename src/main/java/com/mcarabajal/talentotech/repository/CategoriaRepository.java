package com.mcarabajal.talentotech.repository;

import com.mcarabajal.talentotech.entity.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {
}
