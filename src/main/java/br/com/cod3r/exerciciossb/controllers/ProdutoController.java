package br.com.cod3r.exerciciossb.controllers;

import br.com.cod3r.exerciciossb.model.entities.Produto;
import br.com.cod3r.exerciciossb.model.repositories.ProdutoPageRepository;
import br.com.cod3r.exerciciossb.model.repositories.ProdutoRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import java.awt.print.Pageable;
import java.util.Optional;

@RestController
@RequestMapping("/api/produtos")
public class ProdutoController {

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private ProdutoPageRepository produtoPageRepository;

    @RequestMapping(method = {RequestMethod.POST, RequestMethod.PUT})
    public @ResponseBody Produto salvarProduto(@Valid Produto produto) {
        produtoRepository.save(produto);
        return produto;
    }

    //@PostMapping
    //public @ResponseBody Produto novoProduto(@Valid Produto produto) {
    //    produtoRepository.save(produto);
    //    return produto;
    //}

    //@PostMapping
    //public @ResponseBody Produto novoProduto(@RequestParam String nome, @RequestParam double preco, @RequestParam double desconto) {
    //    Produto produto = new Produto(nome, preco, desconto);
    //    produtoRepository.save(produto);
    //    return produto;
    //}

    @GetMapping
    public Iterable<Produto> obterProdutos (){
        return produtoRepository.findAll();
    }

    @GetMapping(path = "/{id}")
    public Optional<Produto> obterProdutoPorId(@PathVariable int id){
        return produtoRepository.findById(id);
    }

    @GetMapping(path = "/pagina/{numeroPagina}/{qtdPagina}")
    public Iterable<Produto> obterProdutoPorPagina(@PathVariable int numeroPagina, @PathVariable int qtdPagina){
        if (qtdPagina >= 5) qtdPagina = 5;
        PageRequest page = PageRequest.of(numeroPagina, 3);
        return produtoPageRepository.findAll(page);
    }

    @GetMapping(path = "/nome/{parteNome}")
    public Iterable<Produto> obterProdutosPorNome (@PathVariable String parteNome){
        return produtoRepository.findByNomeContainingIgnoreCase(parteNome);
    }

    //@PutMapping
    //public Produto alterarProduto(@Valid Produto produto){
    //    produtoRepository.save(produto);
    //    return produto;
    //}

    @DeleteMapping(path = "/{id}")
    public void excluirProduto(@PathVariable int id){
        produtoRepository.deleteById(id);
    }
}
