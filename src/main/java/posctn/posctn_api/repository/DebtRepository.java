package posctn.posctn_api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import posctn.posctn_api.model.DebtModel;

public interface DebtRepository extends JpaRepository<DebtModel, Long> {
}
