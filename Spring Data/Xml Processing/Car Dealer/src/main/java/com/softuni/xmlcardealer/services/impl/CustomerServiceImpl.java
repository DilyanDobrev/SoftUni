package com.softuni.xmlcardealer.services.impl;

import com.softuni.xmlcardealer.models.dtos.seeddtos.CustomerSeedDto;
import com.softuni.xmlcardealer.models.dtos.CarViewDto;
import com.softuni.xmlcardealer.models.dtos.CustomerByBirthdayDto;
import com.softuni.xmlcardealer.models.dtos.CustomerPurchasesViewDto;
import com.softuni.xmlcardealer.models.dtos.SaleViewDto;
import com.softuni.xmlcardealer.models.entities.Customer;
import com.softuni.xmlcardealer.models.entities.Part;
import com.softuni.xmlcardealer.repositories.CustomerRepository;
import com.softuni.xmlcardealer.services.CustomerService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public CustomerServiceImpl(CustomerRepository customerRepository,
                               ModelMapper modelMapper) {
        this.customerRepository = customerRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public void saveAll(List<CustomerSeedDto> customerSeedDtos) {
        if (this.customerRepository.count() != 0) {
            return;
        }

        Customer[] customers = this.modelMapper.map(customerSeedDtos, Customer[].class);
        this.customerRepository.saveAll(Arrays.asList(customers));
    }

    @Override
    public List<Customer> getAllCustomers() {
        return this.customerRepository.findAll();
    }

    @Override
    public List<CustomerByBirthdayDto> getAllSortedByBirthday() {
        return this.customerRepository
                .getAllByBirthdate()
                .stream()
                .map(customer -> {
                    CustomerByBirthdayDto customerDto = this.modelMapper.map(customer, CustomerByBirthdayDto.class);
                    customerDto.setPurchases(customer
                            .getSales()
                            .stream()
                            .map(sale -> {
                                SaleViewDto saleViewDto = this.modelMapper.map(sale, SaleViewDto.class);
                                saleViewDto.setCar(this.modelMapper.map(sale.getCar(), CarViewDto.class));
                                return saleViewDto;
                            })
                            .collect(Collectors.toSet()));
                    return customerDto;
                })
                .collect(Collectors.toList());
    }

    @Override
    public List<CustomerPurchasesViewDto> getCustomersPurchases() {
        return this.customerRepository
                .findAll()
                .stream()
                .map(customer -> {
                    CustomerPurchasesViewDto customerDto = new CustomerPurchasesViewDto();
                    customerDto.setName(customer.getName());
                    customerDto.setBoughtCars(customer.getSales().size());

                    BigDecimal cost = customer.getSales()
                            .stream()
                            .map(sale -> sale.getCar()
                                    .getParts()
                                    .stream()
                                    .map(Part::getPrice)
                                    .reduce(BigDecimal.ZERO, BigDecimal::add)
                            )
                            .reduce(BigDecimal.ZERO, BigDecimal::add);

                    customerDto.setSpentMoney(cost);
                    return customerDto;
                })
                .sorted((c1, c2) -> {
                    int result = c2.getSpentMoney().compareTo(c1.getSpentMoney());
                    if (result == 0) {
                        result = c2.getBoughtCars().compareTo(c1.getBoughtCars());
                    }
                    return result;
                })
                .collect(Collectors.toList());
    }
}
