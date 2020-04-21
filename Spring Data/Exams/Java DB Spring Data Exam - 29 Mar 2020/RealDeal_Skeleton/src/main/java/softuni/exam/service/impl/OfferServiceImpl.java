package softuni.exam.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import softuni.exam.models.dtos.CarDto;
import softuni.exam.models.dtos.OfferSeedRootDto;
import softuni.exam.models.dtos.SellerDto;
import softuni.exam.models.entities.Car;
import softuni.exam.models.entities.Offer;
import softuni.exam.models.entities.Picture;
import softuni.exam.models.entities.Seller;
import softuni.exam.repository.OfferRepository;
import softuni.exam.service.CarService;
import softuni.exam.service.OfferService;
import softuni.exam.service.PictureService;
import softuni.exam.service.SellerService;
import softuni.exam.util.ValidationUtil;
import softuni.exam.util.XmlParser;

import javax.transaction.Transactional;
import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Set;

import static softuni.exam.constants.GlobalConstants.OFFERS_FILE_PATH;

@Service
@Transactional
public class OfferServiceImpl implements OfferService {

    private final OfferRepository offerRepository;
    private final ValidationUtil validationUtil;
    private final ModelMapper modelMapper;
    private final XmlParser xmlParser;
    private final CarService carService;
    private final SellerService sellerService;
    private final PictureService pictureService;

    @Autowired
    public OfferServiceImpl(OfferRepository offerRepository, ValidationUtil validationUtil, ModelMapper modelMapper, XmlParser xmlParser, CarService carService, SellerService sellerService, PictureService pictureService) {
        this.offerRepository = offerRepository;
        this.validationUtil = validationUtil;
        this.modelMapper = modelMapper;
        this.xmlParser = xmlParser;
        this.carService = carService;
        this.sellerService = sellerService;
        this.pictureService = pictureService;
    }

    @Override
    public boolean areImported() {
        return this.offerRepository.count() > 0;
    }

    @Override
    public String readOffersFileContent() throws IOException {
        return Files.readString(Path.of(OFFERS_FILE_PATH));
    }

    @Override
    public String importOffers() throws IOException, JAXBException {
        StringBuilder sb = new StringBuilder();

        OfferSeedRootDto offerSeedRootDtos = this.xmlParser.parseXml(OfferSeedRootDto.class, OFFERS_FILE_PATH);

        offerSeedRootDtos.getOffers()
                .forEach(offerSeedDto -> {
                    if (this.validationUtil.isValid(offerSeedDto)) {
                        if (this.offerRepository.findByDescriptionAndAddedOn(offerSeedDto.getDescription(),
                                offerSeedDto.getAddedOn()) == null) {

                            Offer offer = this.modelMapper.map(offerSeedDto, Offer.class);

                            CarDto carDto = offerSeedDto.getCar();
                            SellerDto sellerDto = offerSeedDto.getSeller();

                            Car car1 = this.modelMapper.map(carDto, Car.class);
                            Seller seller1 = this.modelMapper.map(sellerDto, Seller.class);

                            Car car = this.carService.getById(car1.getId()).orElse(null);
                            Seller seller = this.sellerService.getById(seller1.getId()).orElse(null);
                            Set<Picture> pictures = this.pictureService.getAllByCar(car);
                            if (car != null && seller != null) {
                                offer.setCar(car);
                                offer.setSeller(seller);
                                offer.setPictures(pictures);

                                sb.append(String.format("Successfully import offer %s - %s",
                                        offerSeedDto.getAddedOn().toString(),
                                        offerSeedDto.isHasGoldStatus()));

                                this.offerRepository.saveAndFlush(offer);
                            } else {
                                sb.append("Invalid offer");
                            }
                        } else {
                            sb.append("Invalid offer");
                        }
                    } else {
                        sb.append("Invalid offer");
                    }
                    sb.append(System.lineSeparator());
                });


        return sb.toString();
    }
}
