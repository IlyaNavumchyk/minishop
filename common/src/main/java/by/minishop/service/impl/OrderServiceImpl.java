package by.minishop.service.impl;

import by.minishop.domain.Customer;
import by.minishop.domain.Order;
import by.minishop.exception.NoSuchEntityException;
import by.minishop.repository.OrderRepository;
import by.minishop.service.CustomerService;
import by.minishop.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Primary
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final CustomerService customerService;

    @Override
    public Page<Order> findAll(Pageable pageable) {

        return orderRepository.findAll(pageable);
    }

    @Override
    public Order findById(Long id) {
        return orderRepository.findById(id).orElseThrow(
                () -> new NoSuchEntityException(String.format("Order with id %d not found.", id)));
    }

    @Transactional
    @Override
    public void create(Order order, Long customerId) {

        Customer customer = customerService.findById(customerId);

        order.setCustomer(customer);
        customer.getOrders().add(order);

        orderRepository.save(order);
    }

    @Transactional
    @Override
    public void update(Order order) {

        orderRepository.save(order);
    }
}
