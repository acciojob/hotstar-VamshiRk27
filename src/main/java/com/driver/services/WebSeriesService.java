package com.driver.services;

import com.driver.EntryDto.WebSeriesEntryDto;
import com.driver.model.ProductionHouse;
import com.driver.model.WebSeries;
import com.driver.repository.ProductionHouseRepository;
import com.driver.repository.WebSeriesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WebSeriesService {

    @Autowired
    WebSeriesRepository webSeriesRepository;

    @Autowired
    ProductionHouseRepository productionHouseRepository;

    public Integer addWebSeries(WebSeriesEntryDto webSeriesEntryDto)throws  Exception{

        //Add a webSeries to the database and update the ratings of the productionHouse
        //Incase the seriesName is already present in the Db throw Exception("Series is already present")
        //use function written in Repository Layer for the same
        //Dont forget to save the production and webseries Repo
        ProductionHouse productionHouse=productionHouseRepository.findById(webSeriesEntryDto.getProductionHouseId()).get();
        List<WebSeries> webSeriesList=productionHouse.getWebSeriesList();

        WebSeries webSeries=webSeriesRepository.findBySeriesName(webSeriesEntryDto.getSeriesName());
        if(webSeries!=null){
            throw new Exception("Series is already present");
        }
        WebSeries series=new WebSeries();
        series.setSeriesName(webSeriesEntryDto.getSeriesName());
        series.setAgeLimit(webSeriesEntryDto.getAgeLimit());
        series.setRating(webSeriesEntryDto.getRating());
        series.setSubscriptionType(webSeriesEntryDto.getSubscriptionType());
        series.setProductionHouse(productionHouse);

        productionHouse.setRatings((productionHouse.getRatings()+webSeriesEntryDto.getRating())/2);

        webSeriesList.add(series);
        productionHouseRepository.save(productionHouse);
        int id=series.getId();
        return id;
    }

}
