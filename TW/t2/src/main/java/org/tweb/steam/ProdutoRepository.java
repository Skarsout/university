package org.tweb.steam;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface ProdutoRepository extends CrudRepository<Produto, String> {

	Produto findByNome(String nome);

	List<Produto> findByCategoria(String categoria);

	void deleteByNome(String nome);
}
