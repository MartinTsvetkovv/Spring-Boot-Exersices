package com.example.restestatesoftuni.repository;

import com.example.restestatesoftuni.domain.entities.Offer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OfferRepository extends JpaRepository<Offer, String> {


}
