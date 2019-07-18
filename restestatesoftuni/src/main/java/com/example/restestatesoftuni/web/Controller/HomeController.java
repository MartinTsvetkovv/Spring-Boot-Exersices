package com.example.restestatesoftuni.web.Controller;

import com.example.restestatesoftuni.domain.entities.models.binding.view.OfferViewModel;
import com.example.restestatesoftuni.repository.OfferRepository;
import com.example.restestatesoftuni.services.OfferService;
import com.example.restestatesoftuni.utils.HtmlFileReader;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class HomeController {

    private final OfferService offerService;
    private final ModelMapper modelMapper;
    private final HtmlFileReader htmlFileReader;


    @Autowired
    public HomeController(OfferService offerService, ModelMapper modelMapper, HtmlFileReader htmlFileReader) {
        this.offerService = offerService;
        this.modelMapper = modelMapper;
        this.htmlFileReader = htmlFileReader;
    }

    //@RequestMapping(value = "/", method = RequestMethod.GET)
    @GetMapping("/")
    @ResponseBody
    public String index() throws IOException {
        return this.showHtml();
    }

    public String showHtml() throws IOException {
        StringBuilder htmlOffers = new StringBuilder();
        List<OfferViewModel> offers = this.offerService.getOffers()
                .stream()
                .map(o -> this.modelMapper.map(o, OfferViewModel.class))
                .collect(Collectors.toList());

        if (offers.isEmpty()){
            htmlOffers.append("<div class=\"apartment\" style=\" border: red solid 1px \"> ")
            .append("There aren't any offers")
            .append("</div>");

            return this.htmlFileReader.readHtmlFile("C:\\Users\\marti\\IdeaProjects\\restestatesoftuni\\src\\main\\resources\\static\\index.html")
                    .replace("{{offers}}", htmlOffers.toString().trim());

        }

        for (OfferViewModel offer : offers) {
            htmlOffers.append("<div class=\"apartment\">")
            .append("<p>Rent: "+ offer.getApartmentRent()+ "</p>")
            .append("<p>Type: " + offer.getApartmentType() +"</p>")
            .append("<p>Commission: "+offer.getAgencyCommission()+"</p>").append("</div>").append(System.lineSeparator());

        }

        return this.htmlFileReader.readHtmlFile("C:\\Users\\marti\\IdeaProjects\\restestatesoftuni\\src\\main\\resources\\static\\index.html")
                .replace("{{offers}}", htmlOffers.toString().trim());

    }

}
