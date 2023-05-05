package com.driver.services;


import com.driver.EntryDto.ProductionHouseEntryDto;
import com.driver.model.ProductionHouse;
import com.driver.repository.ProductionHouseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;

@Service
public class ProductionHouseService {

    @Autowired
    ProductionHouseRepository productionHouseRepository;

    public Integer addProductionHouseToDb(ProductionHouseEntryDto productionHouseEntryDto){
        ProductionHouse existing=productionHouseRepository.findByName(productionHouseEntryDto.getName());
        int id=-1;
        if(existing==null){
            ProductionHouse productionHouse=new ProductionHouse(productionHouseEntryDto.getName());
            productionHouse.setRatings(0);
            productionHouseRepository.save(productionHouse);
            id=productionHouse.getId();
        }
        return id;
    }



}
