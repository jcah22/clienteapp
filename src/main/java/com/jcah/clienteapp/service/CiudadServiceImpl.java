package com.jcah.clienteapp.service;

import com.jcah.clienteapp.entity.Ciudad;
import com.jcah.clienteapp.repository.CiudadRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CiudadServiceImpl implements ICiudadService{

    @Autowired
    private CiudadRepository ciudadRepository;

    @Override
    public List<Ciudad> listarCiudades() {
        return (List<Ciudad>) ciudadRepository.findAll();
    }
}
