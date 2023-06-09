package com.driver.services;


import com.driver.model.Subscription;
import com.driver.model.SubscriptionType;
import com.driver.model.User;
import com.driver.model.WebSeries;
import com.driver.repository.UserRepository;
import com.driver.repository.WebSeriesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    WebSeriesRepository webSeriesRepository;


    public Integer addUser(User user){
        //Jut simply add the user to the Db and return the userId returned by the repository
        User savedUser=userRepository.save(user);
        int id= savedUser.getId();
        return id;
    }

    public Integer getAvailableCountOfWebSeriesViewable(Integer userId){

        //Return the count of all webSeries that a user can watch based on his ageLimit and subscriptionType
        //Hint: Take out all the Webseries from the WebRepository
        User user=userRepository.findById(userId).get();
        Subscription subscription=user.getSubscription();
        SubscriptionType subscriptionType=subscription.getSubscriptionType();
        List<WebSeries> webSeriesList=null;
        if(subscriptionType==SubscriptionType.BASIC){
            webSeriesList=webSeriesRepository.findAllWebSeriesBySubscription(subscriptionType);
        }
        else if(subscriptionType==SubscriptionType.PRO || subscriptionType==SubscriptionType.ELITE){
            webSeriesList=webSeriesRepository.findAll();
        }
        int count=0;
        for(WebSeries series:webSeriesList){
            if(user.getAge()>=series.getAgeLimit()){
                count++;
            }
        }
        return count;
    }


}
