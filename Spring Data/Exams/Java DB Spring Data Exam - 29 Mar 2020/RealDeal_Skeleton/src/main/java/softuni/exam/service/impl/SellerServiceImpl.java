package softuni.exam.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import softuni.exam.models.dtos.SellerSeedRootDto;
import softuni.exam.models.entities.Seller;
import softuni.exam.repository.SellerRepository;
import softuni.exam.service.SellerService;
import softuni.exam.util.ValidationUtil;
import softuni.exam.util.XmlParser;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Optional;

import static softuni.exam.constants.GlobalConstants.SELLERS_FILE_PATH;

@Service
public class SellerServiceImpl implements SellerService {

    private final SellerRepository sellerRepository;
    private final ValidationUtil validationUtil;
    private final ModelMapper modelMapper;
    private final XmlParser xmlParser;

    @Autowired
    public SellerServiceImpl(SellerRepository sellerRepository, ValidationUtil validationUtil, ModelMapper modelMapper, XmlParser xmlParser) {
        this.sellerRepository = sellerRepository;
        this.validationUtil = validationUtil;
        this.modelMapper = modelMapper;
        this.xmlParser = xmlParser;
    }

    @Override
    public boolean areImported() {
        return this.sellerRepository.count() > 0;
    }

    @Override
    public String readSellersFromFile() throws IOException {
        return Files.readString(Path.of(SELLERS_FILE_PATH));
    }

    @Override
    public String importSellers() throws IOException, JAXBException {
        StringBuilder sb = new StringBuilder();

        SellerSeedRootDto sellerSeedRootDto = this.xmlParser.parseXml(SellerSeedRootDto.class, SELLERS_FILE_PATH);

        sellerSeedRootDto.getSellers()
                .forEach(sellerSeedDto -> {
                    if (this.validationUtil.isValid(sellerSeedDto)) {
                       if(this.sellerRepository.findByEmail(sellerSeedDto.getEmail()) == null) {
                           Seller seller = this.modelMapper.map(sellerSeedDto, Seller.class);

                            sb.append(String.format("Successfully import seller %s - %s",
                                    sellerSeedDto.getLastName(), sellerSeedDto.getEmail()));
                           this.sellerRepository.saveAndFlush(seller);
                       } else {
                           sb.append("Invalid seller");
                       }
                    } else {
                        sb.append("Invalid seller");
                    }

                    sb.append(System.lineSeparator());
                });
        return sb.toString();
    }

    @Override
    public Optional<Seller> getById(Integer id) {
        return this.sellerRepository.findById(id);
    }
}
