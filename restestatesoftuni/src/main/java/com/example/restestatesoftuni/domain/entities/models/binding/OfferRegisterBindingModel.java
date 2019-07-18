package com.example.restestatesoftuni.domain.entities.models.binding;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

//тук взимаме инфото от view-то (хтмл-а)
public class OfferRegisterBindingModel {
    private BigDecimal apartmentRent;
    private String apartmentType;
    private BigDecimal agencyCommission;


    public OfferRegisterBindingModel() {
    }

    public BigDecimal getApartmentRent() {
        return apartmentRent;
    }

    public void setApartmentRent(BigDecimal apartmentRent) {
        this.apartmentRent = apartmentRent;
    }


    public String getApartmentType() {
        return apartmentType;
    }

    public void setApartmentType(String apartmentType) {
        this.apartmentType = apartmentType;
    }


    public BigDecimal getAgencyCommission() {
        return agencyCommission;
    }

    public void setAgencyCommission(BigDecimal agencyCommission) {
        this.agencyCommission = agencyCommission;
    }
}
