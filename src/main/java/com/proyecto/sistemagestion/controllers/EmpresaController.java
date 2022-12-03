package com.proyecto.sistemagestion.controllers;

import com.proyecto.sistemagestion.entities.Empresa;
import com.proyecto.sistemagestion.repositories.EmpresaRepository;
import com.proyecto.sistemagestion.services.EmpresaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class EmpresaController {

    @Autowired
    EmpresaService empresaService;

    @Autowired
    EmpresaRepository empresaRepository;

    @GetMapping("/empresas")
    public String ViewEmpresasPage(@RequestParam(value = "page", required = false, defaultValue = "0") Integer pagenum,
                                   @RequestParam(value = "length", required = false, defaultValue = "4") Integer lenum,
                                   Model model, @ModelAttribute("msg") String msg){
        Page<Empresa> empresasPage= empresaRepository.findAll(PageRequest.of(pagenum, lenum));
        model.addAttribute("empresaslist",empresasPage.getContent());
        model.addAttribute("pages", new Integer[empresasPage.getTotalPages()]);
        model.addAttribute("pagenum", pagenum);
        model.addAttribute("msg",msg);
        return "viewEmpresasPage"; //Llamamos al HTML
    }

    @GetMapping("/nuevaempresa")
    public String NewEmpresaPage(Model model, @ModelAttribute("msg") String msg){
        Empresa emp= new Empresa();
        model.addAttribute("emp",emp);
        model.addAttribute("msg",msg);
        return "newEmpresaPage";
    }

    @PostMapping("/guardarempresa")
    public String SaveEmpresa(Empresa emp, RedirectAttributes redirectAttributes){
        try{
            if(empresaService.saveOrUpdateEmpresa(emp)==true){
                redirectAttributes.addFlashAttribute("msg", "saveOK");
                return "redirect:/empresas";
            } else{
                redirectAttributes.addFlashAttribute("msg", "saveERROR");
                return "redirect:/nuevaempresa";
            }
        } catch (Exception e){
            redirectAttributes.addFlashAttribute("msg", "saveREPEAT");
            return "redirect:/nuevaempresa";
        }
    }

    @GetMapping("/editarempresa/{id}")
    public String EditEmpresaPage(Model model, @PathVariable Integer id, @ModelAttribute("msg") String msg){
        Empresa emp=empresaService.getEmpresaById(id);
        model.addAttribute("emp",emp);
        model.addAttribute("msg", msg);
        return "editEmpresaPage";
    }

    @PostMapping("/actualizarempresa")
    public String UpdateEmpresa(@ModelAttribute("emp") Empresa emp, RedirectAttributes redirectAttributes){
        try{
            if(empresaService.saveOrUpdateEmpresa(emp)==true){
                redirectAttributes.addFlashAttribute("msg", "updtOK");
                return "redirect:/empresas";
            } else{
                redirectAttributes.addFlashAttribute("msg", "updtERROR");
                return "redirect:/editarempresa/"+emp.getId();
            }

        } catch (Exception e){
            redirectAttributes.addFlashAttribute("msg", "updtREPEAT");
            return "redirect:/editarempresa/"+emp.getId();
        }
    }

    @GetMapping("/eliminarempresa/{id}")
    public String DeleteEmpresa(@PathVariable Integer id, RedirectAttributes redirectAttributes){
        if (empresaService.deleteEmpresa(id)==true){
            redirectAttributes.addFlashAttribute("msg", "dltOK");
            return "redirect:/empresas";
        }
        redirectAttributes.addFlashAttribute("msg", "dltEROR");
        return "redirect:/empresas";
    }

}
