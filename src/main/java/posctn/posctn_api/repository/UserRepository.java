package posctn.posctn_api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import posctn.posctn_api.enums.RoleEnum;
import posctn.posctn_api.model.UserModel;

@Repository
public interface UserRepository extends JpaRepository<UserModel, Long> {

    /**
     * Check existing username
     *
     * @param username
     * @return
     */
    boolean existsByUsername(String username);

}
