package com.softuni.xmlcardealer.controllers;


import com.softuni.xmlcardealer.models.dtos.*;
import com.softuni.xmlcardealer.models.dtos.seeddtos.*;
import com.softuni.xmlcardealer.services.*;
import com.softuni.xmlcardealer.utils.XmlParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import javax.xml.bind.JAXBException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import static com.softuni.xmlcardealer.constants.GlobalConstants.*;


@Component
public class AppController implements CommandLineRunner {

    private final XmlParser xmlParser;
    private final SupplierService supplierService;
    private final CustomerService customerService;
    private final CarService carService;
    private final PartService partService;
    private final SaleService saleService;

    @Autowired
    public AppController(XmlParser xmlParser, SupplierService supplierService, CustomerService customerService, CarService carService, PartService partService, SaleService saleService) {
        this.xmlParser = xmlParser;
        this.supplierService = supplierService;
        this.customerService = customerService;
        this.carService = carService;
        this.partService = partService;
        this.saleService = saleService;
    }

    @Override
    public void run(String... args) throws IOException, JAXBException {
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

    private void writeAllSaleDetails() throws IOException, JAXBException {
        List<SaleDetailsViewDto> dtos = this.saleService.getSalesDetails();
        SaleDetailWrapperViewDto wrapper = new SaleDetailWrapperViewDto();
        wrapper.setSales(dtos);

        this.xmlParser.marshalToFile(QUERY_EX6_PATH, wrapper);
    }

    private void writeAllCustomersWithPurchases() throws IOException, JAXBException {
        List<CustomerPurchasesViewDto> dtos = this.customerService.getCustomersPurchases();
        CustomerPurchaseWrapperViewDto wrapper = new CustomerPurchaseWrapperViewDto();
        wrapper.setCustomers(dtos);

        this.xmlParser.marshalToFile(QUERY_EX5_PATH, wrapper);
    }

    private void writeAllCarsWithTheirParts() throws IOException, JAXBException {
        CarPartsWrapperViewDto wrapper = this.carService.getAllCarsWithTheirParts();

        this.xmlParser.marshalToFile(QUERY_EX4_PATH, wrapper);
    }

    private void writeLocalSuppliers() throws IOException, JAXBException {
        List<SupplierViewDto> dtos = this.supplierService.getLocalSuppliers();
        SupplierWrapperViewDto wrapper = new SupplierWrapperViewDto();
        wrapper.setSuppliers(dtos);

        this.xmlParser.marshalToFile(QUERY_EX3_PATH, wrapper);
    }

    private void writeCarsFromMake(String make) throws IOException, JAXBException {
        List<CarViewDto> dtos = this.carService.getCarsByMake(make);
        CarWrapperViewDto wrapper = new CarWrapperViewDto();
        wrapper.setCars(dtos);

        this.xmlParser.marshalToFile(QUERY_EX2_PATH, wrapper);
    }

    private void writeAllOrderedCustomers() throws IOException, JAXBException {
        List<CustomerByBirthdayDto> dtos = this.customerService.getAllSortedByBirthday();
        CustomerWrapperByBirthdayDto wrapper = new CustomerWrapperByBirthdayDto();
        wrapper.setCustomers(dtos);

        this.xmlParser.marshalToFile(QUERY_EX1_PATH, wrapper);
    }

    @Transactional
    public void seedDatabase() throws FileNotFoundException, JAXBException {
        SupplierSeedRootDto supplierSeedRootDto = this.xmlParser
                .unmarshalFromFile(SUPPLIERS_FILE_PATH, SupplierSeedRootDto.class);
        this.supplierService.saveAll(supplierSeedRootDto.getSuppliers());

        CustomerSeedRootDto customerSeedRootDto = this.xmlParser
                .unmarshalFromFile(CUSTOMERS_FILE_PATH, CustomerSeedRootDto.class);
        this.customerService.saveAll(customerSeedRootDto.getCustomers());

        PartSeedRootDto partSeedRootDto = this.xmlParser
                .unmarshalFromFile(PARTS_FILE_PATH, PartSeedRootDto.class);
        this.partService.saveAll(partSeedRootDto.getParts());

        CarSeedRootDto carSeedRootDto = this.xmlParser
                .unmarshalFromFile(CARS_FILE_PATH, CarSeedRootDto.class);
        this.carService.saveAll(carSeedRootDto.getCars());

        this.saleService.generateSales();
    }
}
