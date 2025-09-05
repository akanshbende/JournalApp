package com.example.JournalApp.Scheduler;

import com.example.JournalApp.cache.SimpleAppCache;
import com.example.JournalApp.entity.JournalEntry;
import com.example.JournalApp.entity.User;
import com.example.JournalApp.enums.Sentiment;
import com.example.JournalApp.repository.UserRepositoryImpl;
import com.example.JournalApp.service.EmailService;
//import com.example.JournalApp.service.SentimentAnalysisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class UserScheduler {

    @Autowired
    private UserRepositoryImpl userRepository;

    @Autowired
    private EmailService emailService;

//    @Autowired
//    private SentimentAnalysisService sentimentAnalysisService;

    @Autowired
    private SimpleAppCache appCache;

//    @Scheduled(cron = "0 0 9 ? * SUN")
    public void fetchSaUsersAndSendMail(){
        List<User> usersSA = userRepository.getUsersSA();
        for (User user: usersSA){
            List<JournalEntry> journalEntries = user.getJournalEntries();
            List<Sentiment> sentiments= journalEntries.stream().filter(x -> x.getDate().isAfter(LocalDateTime.now().minus(7, ChronoUnit.DAYS))).map(x-> x.getSentiment()).collect(Collectors.toList());
//            String joinedEntry =String.join(" ",filteredEntires);
//            String sentiment=sentimentAnalysisService.getSentiments(joinedEntry);

            Map<Sentiment,Integer> sentimentCounts=new HashMap<>();

            for (Sentiment sentiment:sentiments){
                if(sentiment!=null){
                    sentimentCounts.put(sentiment,sentimentCounts.getOrDefault(sentiment,0)+1);
                }
            }

            Sentiment mostFrequentSentiment = null;
            int maxCount=0;
            for(Map.Entry<Sentiment,Integer> entry : sentimentCounts.entrySet()){
                if(entry.getValue()> maxCount){
                    maxCount=entry.getValue();
                    mostFrequentSentiment=entry.getKey();
                }
            }
            if(mostFrequentSentiment!=null){
                emailService.sendEmail(user.getEmail(),"Sentiment for last 7 Days",mostFrequentSentiment.toString());
            }

//            emailService.sendEmail(
//                    user.getEmail(),
//                    "Sentiment for last 7 Days",
//                    sentiment
//                    );
        }
    }

    @Scheduled(cron = "0 0/10 * 1/1 * ?")
    public void clearAppCache(){
        appCache.init();
    }

}
