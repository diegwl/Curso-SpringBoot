package br.com.cod3r.exerciciossb.controllers;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/calculadora")
public class CalculadoraController {

    @GetMapping("/somar/{n1}/{n2}")
    public int obterClientePorId1(@PathVariable int n1, @PathVariable int n2){
        return n1 + n2;
    }

    @GetMapping("/subtrair")
    public int obterClientePorId2(@RequestParam(name = "n1", defaultValue = "0") int n1, @RequestParam(name = "n2", defaultValue = "0") int n2){
        return n1 - n2;
    }
}
