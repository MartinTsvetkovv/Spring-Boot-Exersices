package com.example.restestatesoftuni.services;

import com.example.restestatesoftuni.domain.entities.Offer;
import com.example.restestatesoftuni.domain.entities.models.binding.service.OfferFindServiceModel;
import com.example.restestatesoftuni.domain.entities.models.binding.service.OfferServiceModel;
import com.example.restestatesoftuni.domain.entities.models.binding.view.OfferViewModel;
import com.example.restestatesoftuni.repository.OfferRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Validator;
import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class OfferServiceImpl implements OfferService {
    private final OfferRepository offerRepository;
    private final Validator validator;
    private final ModelMapper modelMapper;

    @Autowired
    public OfferServiceImpl(OfferRepository offerRepository, Validator validator, ModelMapper modelMapper) {
        this.offerRepository = offerRepository;
        this.validator = validator;
        this.modelMapper = modelMapper;
    }


    @Override
    public void registerOffer(OfferServiceModel offerServiceModel) {
        if (this.validator.validate(offerServiceModel).size() != 0){
            throw new IllegalArgumentException("Something went wrong");
        }
        this.offerRepository.saveAndFlush(this.modelMapper.map(offerServiceModel, Offer.class));
    }

    @Override
    public List<OfferServiceModel> getOffers() {
        return this.offerRepository.findAll()
                .stream()
                .map(o -> this.modelMapper.map(o, OfferServiceModel.class))
                .collect(Collectors.toList());
    }

    @Override
    public void findOffer(OfferFindServiceModel offerFindServiceModel) {
        if(this.validator.validate(offerFindServiceModel).size() != 0){
            throw new IllegalArgumentException("Something went wrong");
        }

        //List<OfferViewModel> findAllOffers = this.offerRepository.findAll().stream().map(o -> this.modelMapper.map(o, OfferViewModel.class)).collect(Collectors.toList());

        Offer offer = this.getOffers().stream()
                .filter(o -> o.getApartmentType().toLowerCase().equals(offerFindServiceModel.getFamilyApartmentType().toLowerCase())
                && offerFindServiceModel.getFamilyBudget()
                        .compareTo(o.getApartmentRent()
                                .add(o.getAgencyCommission()
                                        .divide(new BigDecimal("100"))
                                        .multiply(o.getApartmentRent()))) >= 0)
                .map(o -> this.modelMapper.map(o, Offer.class))
                .findFirst().orElse(null);

        if (offer == null){
            throw new IllegalArgumentException("Something went wrong");
        }

        this.offerRepository.delete(offer);

    }
}
