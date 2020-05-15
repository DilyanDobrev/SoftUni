package com.softuni.xmlcardealer.services.impl;

import com.softuni.xmlcardealer.models.dtos.CarViewShortDto;
import com.softuni.xmlcardealer.models.dtos.SaleDetailsViewDto;
import com.softuni.xmlcardealer.models.entities.Car;
import com.softuni.xmlcardealer.models.entities.Customer;
import com.softuni.xmlcardealer.models.entities.Part;
import com.softuni.xmlcardealer.models.entities.Sale;
import com.softuni.xmlcardealer.repositories.SaleRepository;
import com.softuni.xmlcardealer.services.CarService;
import com.softuni.xmlcardealer.services.CustomerService;
import com.softuni.xmlcardealer.services.SaleService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Service
@Transactional
public class SaleServiceImpl implements SaleService {

    private final SaleRepository saleRepository;
    private final ModelMapper modelMapper;
    private final CarService carService;
    private final CustomerService customerService;

    @Autowired
    public SaleServiceImpl(SaleRepository saleRepository,
                           ModelMapper modelMapper,
                           CarService carService,
                           CustomerService customerService) {
        this.saleRepository = saleRepository;
        this.modelMapper = modelMapper;
        this.carService = carService;
        this.customerService = customerService;
    }

    @Override
    public void generateSales() {
        if (this.saleRepository.count() != 0) {
            return;
        }

        List<Car> cars = this.carService.getAllCars();
        List<Customer> customers = this.customerService.getAllCustomers();
        Random random = new Random();
        Double[] discounts = {0d, 0.05d, 0.1d, 0.15d, 0.2d, 0.3d, 0.4d, 0.5d};

        List<Sale> sales = new LinkedList<>();

        for (Car car : cars) {
            Sale sale = new Sale();
            sale.setCar(car);
            sale.setCustomer(customers.get(random.nextInt(customers.size())));
            sale.setDiscount(discounts[random.nextInt(discounts.length)]);
            this.saleRepository.saveAndFlush(sale);
            sales.add(sale);
        }

        this.saleRepository.saveAll(sales);
    }

    @Override
    public List<SaleDetailsViewDto> getSalesDetails() {
        return this.saleRepository
                .findAll()
                .stream()
                .map(sale -> {
                    SaleDetailsViewDto saleDto = new SaleDetailsViewDto();
                    saleDto.setCar(this.modelMapper.map(sale.getCar(), CarViewShortDto.class));
                    saleDto.setCustomerName(sale.getCustomer().getName());
                    saleDto.setDiscount(sale.getDiscount());

                    saleDto.setPrice(sale
                            .getCar()
                            .getParts()
                            .stream()
                            .map(Part::getPrice)
                            .reduce(BigDecimal.ZERO, BigDecimal::add));

                    saleDto.setPriceWithDiscount(saleDto
                            .getPrice()
                            .multiply(BigDecimal.valueOf(1.0d - saleDto.getDiscount())));
                    return saleDto;
                })
                .collect(Collectors.toList());
    }
}
