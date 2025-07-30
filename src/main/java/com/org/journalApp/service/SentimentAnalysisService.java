package com.org.journalApp.service;

import org.springframework.stereotype.Service;

//we are not using this service anymore as we have made created one enum field in journal entry entity
@Service
public class SentimentAnalysisService {

    public String getSentiment(String text){

        return "";
    }
}
