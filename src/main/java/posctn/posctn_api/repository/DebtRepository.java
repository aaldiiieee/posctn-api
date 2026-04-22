package posctn.posctn_api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import posctn.posctn_api.model.DebtModel;

import java.util.List;

@Repository
public interface DebtRepository extends JpaRepository<DebtModel, Long> {

    @Query("""
        SELECT DISTINCT d FROM DebtModel d
        LEFT JOIN FETCH d.customer
        LEFT JOIN FETCH d.transaction
        LEFT JOIN FETCH d.payments
    """)
    List<DebtModel> findAllWithDetails();
}
