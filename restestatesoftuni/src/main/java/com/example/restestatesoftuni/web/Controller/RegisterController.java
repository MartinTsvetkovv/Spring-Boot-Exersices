package com.example.restestatesoftuni.web.Controller;

import com.example.restestatesoftuni.domain.entities.models.binding.OfferRegisterBindingModel;
import com.example.restestatesoftuni.domain.entities.models.binding.service.OfferServiceModel;
import com.example.restestatesoftuni.services.OfferService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class RegisterController {

    private final OfferService offerService;
    private final ModelMapper modelMapper;

    public RegisterController(OfferService offerService, ModelMapper modelMapper) {
        this.offerService = offerService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/reg")
    public String register() {
        return "register.html";
    }

    @PostMapping("/reg")
    public String registerConfirm(@ModelAttribute(name = "model") OfferRegisterBindingModel model) {
        try {
            this.offerService.registerOffer(this.modelMapper.map(model, OfferServiceModel.class));
        } catch (IllegalArgumentException e) {
            e.printStackTrace();

            return "redirect:/reg";
        }
        return "redirect:/";
    }
}
