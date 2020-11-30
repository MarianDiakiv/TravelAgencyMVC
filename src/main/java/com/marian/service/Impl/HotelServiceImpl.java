package com.marian.service.Impl;

import com.marian.dao.HotelDao;
import com.marian.entity.Country;
import com.marian.entity.Hotel;
import com.marian.service.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HotelServiceImpl implements HotelService {


    private HotelDao hotelDao;

    @Autowired
    public HotelServiceImpl(HotelDao hotelDao) {
        this.hotelDao = hotelDao;
    }

    @Override
    public void save(Hotel hotel) {
        hotelDao.save(hotel);
    }

    @Override
    public List<Hotel> getByCountry(Country country) {
        return hotelDao.getbuCountry(country);
    }

    @Override
    public List<Hotel> getAll() {
        return hotelDao.getAll();
    }

    @Override
    public Hotel getById(int id) {
        return hotelDao.getById(id);
    }

    @Override
    public void update(Hotel hotel, int id) {
        Hotel temp = hotelDao.getById(id);
        hotel.setId(id);
        hotel.setCountry(temp.getCountry());
        hotelDao.update(hotel);
    }
}
