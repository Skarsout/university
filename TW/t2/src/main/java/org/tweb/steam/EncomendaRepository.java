package org.tweb.steam;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface EncomendaRepository extends CrudRepository<Encomenda, Long> {

    Encomenda findById(long id);
}
