package com.jcah.clienteapp.repository;

import com.jcah.clienteapp.entity.Ciudad;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CiudadRepository extends CrudRepository<Ciudad,Long> {
}
