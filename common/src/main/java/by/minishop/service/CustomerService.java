package by.minishop.service;

import by.minishop.domain.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CustomerService {

    Page<Customer> findAll(Pageable pageable);

    Customer findById(Long id);

    void create(Customer customer);

    void
    update(Customer customer);

    void delete(Long id);
}
