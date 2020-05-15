package com.softuni.xmlcardealer.services;


import com.softuni.xmlcardealer.models.dtos.CustomerByBirthdayDto;
import com.softuni.xmlcardealer.models.dtos.CustomerPurchasesViewDto;
import com.softuni.xmlcardealer.models.dtos.seeddtos.CustomerSeedDto;
import com.softuni.xmlcardealer.models.entities.Customer;

import java.util.List;

public interface CustomerService {

    void saveAll(List<CustomerSeedDto> customerSeedDtos);

    List<Customer> getAllCustomers();

    List<CustomerByBirthdayDto> getAllSortedByBirthday();

    List<CustomerPurchasesViewDto> getCustomersPurchases();
}
