package by.minishop.repository;

import by.minishop.domain.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
    Optional<Customer> findByLogin(String customerLogin);

    Optional<Customer> findByEmail(String customerEmail);
}