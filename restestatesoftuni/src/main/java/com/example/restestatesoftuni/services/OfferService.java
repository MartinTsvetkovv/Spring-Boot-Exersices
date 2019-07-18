package com.example.restestatesoftuni.services;

import com.example.restestatesoftuni.domain.entities.models.binding.service.OfferFindServiceModel;
import com.example.restestatesoftuni.domain.entities.models.binding.service.OfferServiceModel;

import java.util.List;

public interface OfferService {

    void registerOffer(OfferServiceModel offerServiceModel);

    List<OfferServiceModel> getOffers();

    void findOffer(OfferFindServiceModel offerFindServiceModel);
}
