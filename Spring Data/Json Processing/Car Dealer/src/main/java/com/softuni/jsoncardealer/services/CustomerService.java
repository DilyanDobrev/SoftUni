package com.softuni.jsoncardealer.services;

import com.softuni.jsoncardealer.models.dtos.CustomerByBirthdayDto;
import com.softuni.jsoncardealer.models.dtos.CustomerPurchasesViewDto;
import com.softuni.jsoncardealer.models.dtos.seedDtos.CustomerSeedDto;
import com.softuni.jsoncardealer.models.entities.Customer;

import java.util.List;

public interface CustomerService {

    void saveAll(CustomerSeedDto[] customerSeedDtos);

    List<Customer> getAllCustomers();

    List<CustomerByBirthdayDto> getAllSortedByBirthday();

    List<CustomerPurchasesViewDto> getCustomersPurchases();
}
