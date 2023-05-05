package com.driver.repository;

import com.driver.model.SubscriptionType;
import com.driver.model.WebSeries;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository

public interface WebSeriesRepository extends JpaRepository<WebSeries,Integer> {

    WebSeries findBySeriesName(String seriesName);

    @Query(value="select * from web_series w where w.subscriptionType=:subscriptionType",nativeQuery=true)
    List<WebSeries> findAllWebSeriesBySubscription(SubscriptionType subscriptionType);
}
