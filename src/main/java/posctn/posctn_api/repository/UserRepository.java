package posctn.posctn_api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import posctn.posctn_api.model.UserModel;

@Repository
public interface UserRepository extends JpaRepository<UserModel, Long> {

    /**
     * Check existing username
     *
     * @param username
     * @return true if the username already exists, false otherwise
     */
    boolean existsByUsername(String username);

    /**
     * Check existing id
     *
     * @param id
     * @return true if the id already exists, false otherwise
     */
    boolean existsById(Long id);
}
