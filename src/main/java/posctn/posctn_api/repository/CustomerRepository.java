package posctn.posctn_api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import posctn.posctn_api.model.CustomerModel;

import java.util.Optional;

@Service
public interface CustomerRepository extends JpaRepository<CustomerModel, Long> {

    /**
     *
     * @param name
     * @return
     */
    Optional<CustomerModel> findByName(String name);
}
