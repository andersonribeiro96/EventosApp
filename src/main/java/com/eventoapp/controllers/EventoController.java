package com.eventoapp.controllers;

import com.eventoapp.models.Convidado;
import com.eventoapp.models.Evento;
import com.eventoapp.repository.ConvidadoRepository;
import com.eventoapp.repository.EventosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;


@Controller
public class EventoController {

    @Autowired
    EventosRepository eventosRepository;

    @Autowired
    ConvidadoRepository convidadoRepository;

    @GetMapping("/cadastrarEvento")
    public String form() {
        return "evento/formEvento";
    }

    @PostMapping(value = "/cadastrarEvento")
    public String form(@Valid Evento evento, BindingResult result, RedirectAttributes attributes) {
        if (result.hasErrors()) {
            attributes.addFlashAttribute("mensagem", "Verique o formulario");
        } else {
            eventosRepository.save(evento);
            attributes.addFlashAttribute("mensagem", "Evento salvo com sucesso!");
        }

        return "redirect:/cadastrarEvento";
    }

    @GetMapping("/{codigo}")
    public ModelAndView detalhesEvento(@PathVariable("codigo") long codigo) {
        Evento evento = eventosRepository.findByCodigo(codigo);
        ModelAndView modelAndView = new ModelAndView("detalhesEvento");
        modelAndView.addObject("evento", evento);
        Iterable<Convidado> convidados = convidadoRepository.findByEvento(evento);
        modelAndView.addObject("convidados", convidados);
        return modelAndView;
    }

    @PostMapping("/{codigo}")
    public String detalhesEvento(@PathVariable("codigo") long codigo, @Valid Convidado convidado,
                                 BindingResult result, RedirectAttributes attributes) {
        if (result.hasErrors()) {
            attributes.addFlashAttribute("mensagem", "Verique o formulario!");
        } else {
            Evento evento = eventosRepository.findByCodigo(codigo);
            convidado.setEvento(evento);
            convidadoRepository.save(convidado);
            attributes.addFlashAttribute("mensagem", "Convidado inserido com sucesso!");
        }
        return "redirect:/{codigo}";
    }

    @RequestMapping("/deletarEvento/{codigo}")
    public String deletarEvento(@PathVariable("codigo") long codigo){
        Evento evento = eventosRepository.findByCodigo(codigo);
        eventosRepository.delete(evento);
        return "redirect:/";
    }

    @RequestMapping("/deletarConvidado/{codigo}")
    public String deletarConvidado(@PathVariable("codigo") String codigo){
        Convidado convidado = convidadoRepository.findByRg(codigo);
        convidadoRepository.delete(convidado);
        Evento evento = convidado.getEvento();
        long codigoEvento = evento.getCodigo();
        String codigoString = "" + codigoEvento;
        return "redirect:/" + codigoString;
    }


}
