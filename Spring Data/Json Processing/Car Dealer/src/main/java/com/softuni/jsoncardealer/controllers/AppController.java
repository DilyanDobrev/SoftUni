package com.softuni.jsoncardealer.controllers;


import com.google.gson.Gson;
import com.softuni.jsoncardealer.models.dtos.*;
import com.softuni.jsoncardealer.models.dtos.seedDtos.CarSeedDto;
import com.softuni.jsoncardealer.models.dtos.seedDtos.CustomerSeedDto;
import com.softuni.jsoncardealer.models.dtos.seedDtos.PartSeedDto;
import com.softuni.jsoncardealer.models.dtos.seedDtos.SupplierSeedDto;
import com.softuni.jsoncardealer.services.*;
import com.softuni.jsoncardealer.utils.FileIOUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

import static com.softuni.jsoncardealer.constants.GlobalConstants.*;


@Component
public class AppController implements CommandLineRunner {

    private final Gson gson;
    private final CarService carService;
    private final CustomerService customerService;
    private final PartService partService;
    private final SaleService saleService;
    private final SupplierService supplierService;
    private final FileIOUtil fileIOUtil;

    @Autowired
    public AppController(Gson gson, CarService carService, CustomerService customerService, PartService partService, SaleService saleService, SupplierService supplierService, FileIOUtil fileIOUtil) {
        this.gson = gson;

        this.carService = carService;
        this.customerService = customerService;
        this.partService = partService;
        this.saleService = saleService;
        this.supplierService = supplierService;
        this.fileIOUtil = fileIOUtil;
    }

    @Override
    public void run(String... args) throws IOException {
        this.seedDatabase();

//        Ex 1
//        this.writeAllOrderedCustomers();
//        Ex 2
//        this.writeCarsFromMake("Toyota");
//        Ex 3
//        this.writeLocalSuppliers();
//        Ex 4
//        this.writeAllCarsWithTheirParts();
//        Ex 5
//        this.writeAllCustomersWithPurchases();
//        Ex 6
//        this.writeAllSaleDetails();
    }

    private void writeAllSaleDetails() throws IOException {
        List<SaleDetailsViewDto> dtos = this.saleService.getSalesDetails();

        String json = this.gson.toJson(dtos);

        this.fileIOUtil.write(json, QUERY_EX6_PATH);
    }

    private void writeAllCustomersWithPurchases() throws IOException {
        List<CustomerPurchasesViewDto> dtos = this.customerService.getCustomersPurchases();

        String json = this.gson.toJson(dtos);

        this.fileIOUtil.write(json, QUERY_EX5_PATH);
    }

    private void writeAllCarsWithTheirParts() throws IOException {
        List<CarPartsViewDto> dtos = this.carService.getAllCarsWithTheirParts();

        String json = this.gson.toJson(dtos);

        this.fileIOUtil.write(json, QUERY_EX4_PATH);
    }

    private void writeLocalSuppliers() throws IOException {
        List<SupplierViewDto> dtos = this.supplierService.getLocalSuppliers();

        String json = this.gson.toJson(dtos);

        this.fileIOUtil.write(json, QUERY_EX3_PATH);
    }

    private void writeCarsFromMake(String make) throws IOException {
        List<CarViewDto> dtos = this.carService.getCarsByMake(make);

        String json = this.gson.toJson(dtos);

        this.fileIOUtil.write(json, QUERY_EX2_PATH);
    }

    private void writeAllOrderedCustomers() throws IOException {
        List<CustomerByBirthdayDto> dtos = this.customerService.getAllSortedByBirthday();

        String json = this.gson.toJson(dtos);

        this.fileIOUtil.write(json, QUERY_EX1_PATH);

    }

    @Transactional
    public void seedDatabase() throws FileNotFoundException {
        SupplierSeedDto[] supplierSeedDtos = this.gson.
                fromJson(new FileReader(SUPPLIERS_FILE_PATH),
                        SupplierSeedDto[].class);
        this.supplierService.saveAll(supplierSeedDtos);

        CustomerSeedDto[] customerSeedDtos = this.gson.
                fromJson(new FileReader(CUSTOMERS_FILE_PATH),
                        CustomerSeedDto[].class);
        this.customerService.saveAll(customerSeedDtos);

        PartSeedDto[] partSeedDtos = this.gson.
                fromJson(new FileReader(PARTS_FILE_PATH),
                        PartSeedDto[].class);
        this.partService.saveAll(partSeedDtos);

        CarSeedDto[] carSeedDtos = this.gson.
                fromJson(new FileReader(CARS_FILE_PATH),
                        CarSeedDto[].class);
        this.carService.saveAll(carSeedDtos);

        this.saleService.generateSales();
    }
}
