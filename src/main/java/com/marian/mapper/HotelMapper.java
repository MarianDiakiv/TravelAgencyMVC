package com.marian.mapper;

import com.marian.domain.HotelRequest;
import com.marian.entity.Country;
import com.marian.entity.Hotel;
import com.marian.service.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class HotelMapper {
    private CountryService countryService;

    @Autowired
    public HotelMapper(CountryService countryService) {
        this.countryService = countryService;
    }

    public Hotel requestToHotel(HotelRequest request){
    //System.out.println("COUNTRY" + request.getCountry());
    Hotel hotel = new Hotel();
    hotel.setName(request.getName());
    hotel.setCity(request.getCity());
    hotel.setStreet(request.getStreet());
    Country country = countryService.getByName(request.getCountry());
    hotel.setCountry(country);
    return hotel;
}
}
