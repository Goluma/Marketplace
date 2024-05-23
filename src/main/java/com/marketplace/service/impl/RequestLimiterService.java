package com.marketplace.service.impl;

import com.marketplace.domain.UserDetailsImpl;
import com.marketplace.domain.dto.UserDto;
import com.marketplace.service.UserService;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

@Service
@Log
public class RequestLimiterService {

    @Autowired
    private UserService userService;

    private ConcurrentHashMap<String, List<LocalDateTime>> mapOfUserRequests = new ConcurrentHashMap<>();

    private ConcurrentHashMap<String, AtomicInteger> counterMap = new ConcurrentHashMap<>();

    public boolean addNewRequest(UserDetailsImpl userDetails){
        String userEmail = userDetails.getUsername();
        if (mapOfUserRequests.get(userEmail).size() > 9){
            if (checking(userEmail)){
                mapOfUserRequests.get(userEmail).add(LocalDateTime.now());
                counterMap.get(userEmail).incrementAndGet();
                log.info("Accepted");
                return true;
            } else {
                log.info("Refused");
                return false;
            }
        } else {
            mapOfUserRequests.get(userEmail).add(LocalDateTime.now());
            counterMap.get(userEmail).incrementAndGet();
            log.info("Accepted");
            return true;
        }
    }

    private boolean checking(String userEmail){
        LocalDateTime time1 = mapOfUserRequests.get(userEmail).get(counterMap.get(userEmail).get() - 10);
        if (ChronoUnit.SECONDS.between(time1, LocalDateTime.now()) < 60){
            return false;
        }
        return true;
    }

    public Long secondsToWait(String userEmail){
        LocalDateTime time1 = mapOfUserRequests.get(userEmail).get(counterMap.get(userEmail).get() - 10);
        return 60 - ChronoUnit.SECONDS.between(time1, LocalDateTime.now());
    }

    public void addUsers(){
        List<UserDto> userEntityList = userService.getAllUsers();
        for (UserDto email : userEntityList){
            mapOfUserRequests.put(email.getEmail(), new ArrayList<>());
            counterMap.put(email.getEmail(), new AtomicInteger(0));
        }
    }

}
