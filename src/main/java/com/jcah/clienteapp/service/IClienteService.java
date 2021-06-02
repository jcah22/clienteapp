package com.jcah.clienteapp.service;

import com.jcah.clienteapp.entity.Cliente;

import java.util.List;
import java.util.Optional;

public interface IClienteService {

    public List<Cliente> listarTodos();

    public void guardar(Cliente cliente);

   public Cliente buscarPorId(Long id);

    public void eliminar(Long id);
}
