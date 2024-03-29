package com.jcah.clienteapp.controller;

import com.jcah.clienteapp.entity.Ciudad;
import com.jcah.clienteapp.entity.Cliente;
import com.jcah.clienteapp.service.ICiudadService;
import com.jcah.clienteapp.service.IClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/views/clientes")
public class ClienteController {

    @Autowired
    private IClienteService clienteService;
    @Autowired
    private ICiudadService ciudadService;

    @GetMapping("/")
    public String listarClientes(Model model){
        List<Cliente> listadoClientes = clienteService.listarTodos();

        model.addAttribute("titulo","Listado de Clientes");
        model.addAttribute("clientes",listadoClientes);


        return "/views/clientes/listar";
    }


    @GetMapping("/create")
    public String crear(Model model){

        Cliente cliente = new Cliente();
        List<Ciudad> listCiudades = ciudadService.listarCiudades();

        model.addAttribute("titulo","Formulario: Nuevo Cliente");
        model.addAttribute("cliente",cliente);
        model.addAttribute("ciudades",listCiudades);

        return "/views/clientes/frmCrear";
    }

    @PostMapping("/save")
    public String guardar(@Valid @ModelAttribute  Cliente cliente, BindingResult result, Model model, RedirectAttributes flash){
        List<Ciudad> listCiudades = ciudadService.listarCiudades();

        if(result.hasErrors()){
            model.addAttribute("titulo","Formulario: Nuevo Cliente");
            model.addAttribute("cliente",cliente);
            model.addAttribute("ciudades",listCiudades);

            return "/views/clientes/frmCrear";
        }

        clienteService.guardar(cliente);
        System.out.println("Cliente  Guardado con exito");
        flash.addFlashAttribute("success","Cliente guardado con exito");
        return "redirect:/views/clientes/";
    }


    @GetMapping("/edit/{id}")
    public String editar(@PathVariable("id") Long idCliente ,  Model model,RedirectAttributes flash){
        Cliente cliente = null;
        if(idCliente > 0){
             cliente = clienteService.buscarPorId(idCliente);
             if(cliente == null){
                 System.out.println("Error: el cliente no existe ");
                 flash.addFlashAttribute("error","Error: el cliente no existe ");
                 return  "redirect:/views/clientes/";
             }

        }else{
            System.out.println("Error: con el id del cliente");
            flash.addFlashAttribute("error","Error: con el id del cliente");
            return  "redirect:/views/clientes/";

        }


        List<Ciudad> listCiudades = ciudadService.listarCiudades();

        model.addAttribute("titulo","Formulario: Nuevo Cliente");
        model.addAttribute("cliente",cliente);
        model.addAttribute("ciudades",listCiudades);

        return "/views/clientes/frmCrear";
    }

    @GetMapping("/delete/{id}")
    public String eliminar(@PathVariable("id") Long idCliente ,RedirectAttributes flash){
        Cliente cliente = null;
        if(idCliente > 0){
            cliente = clienteService.buscarPorId(idCliente);
            if(cliente == null){
                System.out.println("Error: el cliente no existe ");
                flash.addFlashAttribute("error","Error: el cliente no existe");
                return  "redirect:/views/clientes/";
            }

        }else{
            System.out.println("Error: con el id del cliente");
            flash.addFlashAttribute("error","Error: con el id del cliente");
            return  "redirect:/views/clientes/";

        }

        clienteService.eliminar(idCliente);
        System.out.println("cliente eliminado con exito");
        flash.addFlashAttribute("warning","Registro eliminado con exito");
        return "redirect:/views/clientes/";
    }
}
