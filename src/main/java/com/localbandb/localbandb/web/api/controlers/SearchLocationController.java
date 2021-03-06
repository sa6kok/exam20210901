package com.localbandb.localbandb.web.api.controlers;

import com.localbandb.localbandb.services.services.CountryService;
import com.localbandb.localbandb.services.services.PropertyService;
import com.localbandb.localbandb.web.api.models.CityViewModel;
import com.localbandb.localbandb.web.api.models.CountryViewModel;
import com.localbandb.localbandb.web.api.models.PropertyViewModel;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import static com.localbandb.localbandb.web.api.constants.Constants.MY_URL;

import java.util.List;

@CrossOrigin(origins = MY_URL, maxAge = 3600)
@RestController
@RequestMapping("/search")
public class SearchLocationController extends BaseController {
    private final CountryService countryService;
    private final PropertyService propertyService;

    @Autowired
    public SearchLocationController(CountryService countryService, PropertyService propertyService) {
        this.countryService = countryService;
        this.propertyService = propertyService;
    }

    @GetMapping("/country")
    public List<CountryViewModel> searchCountry() {
        return countryService.getAllCountryNames();
    }

    @GetMapping("/cities/{country}")
    public List<CityViewModel> searchCountry(@PathVariable String country) throws NotFoundException {
        return countryService.getOrderedCitiesForCountry(country);
    }

    @GetMapping("/show/{country}/{city}")
    public List<PropertyViewModel> searchCity(@PathVariable String country, @PathVariable String city) {
        return propertyService.getAllByCity(city);
    }

    @GetMapping("/check/{city}")
    public int checkIfProperties(@PathVariable String city) {
       /* List<PropertyViewModel> allByCity = propertyService.getAllByCity(city);
        return allByCity.size() == 0;*/

        int count = 0;
        for (int i = 0;  i < city.length(); i++) {
            if (Character.isDigit(city.charAt(i))) {
                count++;
            }
        }
       return count;

    }

    @GetMapping("/show/{country}")
    public List<PropertyViewModel> searchAllPropertiesByCountry(@PathVariable String country) {
        if (country.equals("all")) {
            return propertyService.getAllOrderedByAverageReviews();
        }
        return propertyService.getAllByCountry(country);
    }

}
