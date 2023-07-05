package br.com.cod3r.exerciciossb.model.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import br.com.cod3r.exerciciossb.model.entities.Produto;
import org.springframework.data.repository.query.Param;

public interface ProdutoRepository extends CrudRepository<Produto, Integer> {
    public Iterable<Produto> findByNomeContainingIgnoreCase(String parteNome);

    @Query("SELECT p FROM Produto p Where p.nome LIK %:nome")
    public  Iterable<Produto> searchByNameLike(@Param("nome") String nome);
}
