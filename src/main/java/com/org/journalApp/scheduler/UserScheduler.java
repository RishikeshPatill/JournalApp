package com.org.journalApp.scheduler;

import com.org.journalApp.cache.AppCache;
import com.org.journalApp.entity.JournalEntry;
import com.org.journalApp.entity.User;
import com.org.journalApp.repository.UserRepositoryImpl;
import com.org.journalApp.service.EmailService;
import com.org.journalApp.service.SentimentAnalysisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserScheduler {

    @Autowired
    private EmailService emailService;

    @Autowired
    private UserRepositoryImpl userRepository;

    @Autowired
    private SentimentAnalysisService sentimentAnalysisService;

    @Autowired
    private AppCache appCache;

    @Scheduled(cron = "0 0 9 * * SUN")
    public void fetchUserAndSendSAMail(){
        List<User> users = userRepository.getUserForSentimentAnalysis();
        for(User user: users){
            List<JournalEntry> journalEntries = user.getJournalEntries();
            List<String> filteredList = journalEntries.stream().filter(x -> x.getDate().isAfter(LocalDateTime.now().minus(7, ChronoUnit.DAYS))).map(x->x.getContent()).collect(Collectors.toList());
            String entry = String.join(" ", filteredList);
            String sentiment = sentimentAnalysisService.getSentiment(entry);
            emailService.sendMail(user.getEmail(), "Sentiment for last 7 days",sentiment);
        }
    }
    //we have scheduled a cron job to auto refresh the app cache fpr every 10 minutes so that if something changed then
    // in 10 minutes it reflects the new changes, so that we do not need to restart the application in order to reload the cache
    @Scheduled(cron = "0 0/10 * ? * *")
    public void clearAppCache(){
        appCache.init();
    }
}
