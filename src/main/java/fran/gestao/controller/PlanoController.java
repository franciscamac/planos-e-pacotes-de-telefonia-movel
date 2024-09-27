package fran.gestao.controller;

import fran.gestao.model.Plano;
import fran.gestao.service.PlanoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("api/planos")
public class PlanoController {
    @Autowired
    private PlanoService planoService;

    @GetMapping
    public String listarPlanos(Model model) {
        List<Plano> planos = planoService.listarPlanos();
        model.addAttribute("planos", planos);
        return "listar_planos"; // Nome do template Thymeleaf
    }

    @GetMapping("/novo")
    public String novoPlano(Model model) {
        model.addAttribute("plano", new Plano());
        return "novo_plano"; // Nome do template Thymeleaf
    }

    @PostMapping
    public String salvarPlano(@ModelAttribute Plano plano) {
        planoService.salvarPlano(plano);
        return "redirect:/planos";
    }

    @GetMapping("/{id}")
    public String editarPlano(@PathVariable Long id, Model model) {
        Plano plano = planoService.buscarPlano(id);
        model.addAttribute("plano", plano);
        return "editar_plano"; // Nome do template Thymeleaf
    }

    @PostMapping("/{id}")
    public String atualizarPlano(@PathVariable Long id, @ModelAttribute Plano plano) {
        planoService.atualizarPlano(id, plano);
        return "redirect:/planos";
    }

    @GetMapping("/deletar/{id}")
    public String deletarPlano(@PathVariable Long id) {
        planoService.deletarPlano(id);
        return "redirect:/planos";
    }

}
