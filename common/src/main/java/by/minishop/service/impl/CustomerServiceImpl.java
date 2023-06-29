package by.minishop.service.impl;

import by.minishop.domain.Customer;
import by.minishop.exception.EntityAlreadyExsistsException;
import by.minishop.exception.NoSuchEntityException;
import by.minishop.repository.CustomerRepository;
import by.minishop.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Primary
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;

    @Override
    public Page<Customer> findAll(Pageable pageable) {
        return customerRepository.findAll(pageable);
    }

    @Override
    public Customer findById(Long id) {
        return customerRepository.findById(id).orElseThrow(
                () -> new NoSuchEntityException(String.format("Customer with id %d not found.", id)));
    }

    @Transactional
    @Override
    public void create(Customer customer) {

        checkCustomerLoginForNotExistInDB(customer.getCustomerLogin());
        checkCustomerEmailForNotExistInDB(customer.getEmail());

        customerRepository.save(customer);
    }

    @Transactional
    @Override
    public void update(Customer customer) {

        Customer byId = findById(customer.getId());

        if (!(byId.getCustomerLogin().equals(customer.getCustomerLogin()))) {
            checkCustomerLoginForNotExistInDB(customer.getCustomerLogin());
        }

        if (!(byId.getEmail().equals(customer.getEmail()))) {
            checkCustomerEmailForNotExistInDB(customer.getEmail());
        }

        customerRepository.save(customer);
    }

    @Transactional
    @Override
    public void delete(Long id) {

        customerRepository.deleteById(id);
    }

    private void checkCustomerLoginForNotExistInDB(String customerLogin) {

        if (customerRepository.findByLogin(customerLogin).isPresent()) {
            throw new EntityAlreadyExsistsException(
                    String.format("Entity with login %s already exists", customerLogin));
        }
    }

    private void checkCustomerEmailForNotExistInDB(String customerEmail) {

        if (customerRepository.findByEmail(customerEmail).isPresent()) {
            throw new EntityAlreadyExsistsException(
                    String.format("Entity with email %s already exists", customerEmail));
        }
    }
}
