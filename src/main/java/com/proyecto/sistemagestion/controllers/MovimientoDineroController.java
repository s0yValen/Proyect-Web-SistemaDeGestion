package com.proyecto.sistemagestion.controllers;

import com.proyecto.sistemagestion.entities.Empleado;
import com.proyecto.sistemagestion.entities.MovimientoDinero;
import com.proyecto.sistemagestion.repositories.MovimientoDineroRepository;
import com.proyecto.sistemagestion.services.EmpleadoService;
import com.proyecto.sistemagestion.services.MovimientoDineroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class MovimientoDineroController {

    @Autowired
    MovimientoDineroService movimientoDineroService;

    @Autowired
    EmpleadoService empleadoService;

    @Autowired
    MovimientoDineroRepository movimientoDineroRepository;

    @GetMapping("/movimientos")
    public String ViewMovimientosPage(@RequestParam(value = "page", required = false, defaultValue = "0") Integer pagenum,
                                      @RequestParam(value = "length", required = false, defaultValue = "4") Integer lenum,
                                      Model model, @ModelAttribute("msg") String msg){
        Page<MovimientoDinero> movementsPage = movimientoDineroRepository.findAll(PageRequest.of(pagenum, lenum));
        model.addAttribute("movementslist",movementsPage.getContent());
        model.addAttribute("pages", new Integer[movementsPage.getTotalPages()]);
        model.addAttribute("pagenum", pagenum);
        model.addAttribute("msg",msg);
        Long sumaTotal = movimientoDineroService.obtenerSumaMonto();
        model.addAttribute("sumaMontos",sumaTotal);
        return "viewMovimientosPage"; //Llamamos al HTML
    }

    @GetMapping("/nuevomovimiento")
    public String NewMovimientoPage(Model model, @ModelAttribute("msg") String msg){
        MovimientoDinero movimiento= new MovimientoDinero();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String correo = auth.getName();
        Integer id = movimientoDineroService.idByCorreo(correo);
        model.addAttribute("movimiento",movimiento);
        model.addAttribute("id",id);
        model.addAttribute("msg",msg);
        return "newMovimientoPage";
    }

    @PostMapping("/guardarmovimiento")
    public String SaveMovimiento(MovimientoDinero movement, RedirectAttributes redirectAttributes){
        try{
            if(movimientoDineroService.saveUpdateMovimiento(movement)==true){
                redirectAttributes.addFlashAttribute("msg", "saveOK");
                return "redirect:/movimientos";
            } else{
                redirectAttributes.addFlashAttribute("msg", "saveERROR");
                return "redirect:/nuevomovimiento";
            }
        } catch (Exception e){
            redirectAttributes.addFlashAttribute("msg", "saveREPEAT");
            return "redirect:/nuevomovimiento";
        }
    }

    @GetMapping("/editarmovimiento/{id}")
    public String EditMovimientoPage(Model model, @PathVariable Integer id, @ModelAttribute("msg") String msg){
        MovimientoDinero movement=movimientoDineroService.getMovimientoById(id);
        model.addAttribute("movement",movement);
        model.addAttribute("msg", msg);
        return "editMovimientoPage";
    }

    @PostMapping("/actualizarmovimiento")
    public String UpdateMovimiento(@ModelAttribute("emp") MovimientoDinero movement, RedirectAttributes redirectAttributes){
        try{
            if(movimientoDineroService.saveUpdateMovimiento(movement)==true){
                redirectAttributes.addFlashAttribute("msg", "updtOK");
                return "redirect:/movimientos";
            } else{
                redirectAttributes.addFlashAttribute("msg", "updtERROR");
                return "redirect:/editarmovimiento/"+movement.getId();
            }

        } catch (Exception e){
            redirectAttributes.addFlashAttribute("msg", "updtREPEAT");
            return "redirect:/editarmovimiento/"+movement.getId();
        }

    }

    @GetMapping("/eliminarmovimiento/{id}")
    public String DeleteMovimiento(@PathVariable Integer id, RedirectAttributes redirectAttributes){
        if (movimientoDineroService.deleteMovimientoById(id)==true){
            redirectAttributes.addFlashAttribute("msg", "dltOK");
            return "redirect:/movimientos";
        }
        redirectAttributes.addFlashAttribute("msg", "dltEROR");
        return "redirect:/movimientos";
    }

}
