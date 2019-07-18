package com.example.restestatesoftuni.web.Controller;

import com.example.restestatesoftuni.domain.entities.models.binding.OfferFindBindingModel;
import com.example.restestatesoftuni.domain.entities.models.binding.service.OfferFindServiceModel;
import com.example.restestatesoftuni.services.OfferService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class FindController {
    private final OfferService offerService;
    private final ModelMapper modelMapper;

    @Autowired
    public FindController(OfferService offerService, ModelMapper modelMapper) {
        this.offerService = offerService;
        this.modelMapper = modelMapper;
    }


    @GetMapping("/find")
    public String find(){
        return "find.html";
    }

    @PostMapping("/find")
    public String findOffer(@ModelAttribute(name = "model") OfferFindBindingModel model){
        try{
            this.offerService.findOffer(this.modelMapper.map(model, OfferFindServiceModel.class));
        }catch (IllegalArgumentException e){
            e.printStackTrace();
            return "redirect:/find";
        }

        return "redirect:/";
    }
}
