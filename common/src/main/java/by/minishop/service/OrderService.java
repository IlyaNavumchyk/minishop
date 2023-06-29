package by.minishop.service;

import by.minishop.domain.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface OrderService {

    Page<Order> findAll(Pageable pageable);

    Order findById(Long id);

    void create(Order order, Long customerId);

    void
    update(Order order);
}
