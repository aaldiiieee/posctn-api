package posctn.posctn_api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import posctn.posctn_api.model.TransactionModel;

import java.util.List;
import java.util.Optional;

@Repository
public interface TransactionRepository extends JpaRepository<TransactionModel, Long> {

    @Query("""
        SELECT DISTINCT t FROM TransactionModel t
        LEFT JOIN FETCH t.customer
        LEFT JOIN FETCH t.items i
        LEFT JOIN FETCH i.menuItem
        WHERE t.id = :id
    """)
    Optional<TransactionModel> findDetailById(Long id);

    @Query("""
        SELECT t FROM TransactionModel t
        LEFT JOIN FETCH t.customer
    """)
    List<TransactionModel> findAllWithCustomer();
}

