package org.tweb.steam;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {

    User findOneByUsername(String username);

    User findById(long id);
}
