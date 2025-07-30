package com.org.journalApp.cache;

import com.org.journalApp.entity.JournalAppConfigurationEntity;
import com.org.journalApp.repository.JournalAppConfigurationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class AppCache {

//    public enum keys{
//        WEATHER_API;
//    }

    public Map<String, String>appCache;

    @Autowired
    private JournalAppConfigurationRepository journalAppConfigurationRepository;
    @PostConstruct
    public void init(){
        appCache= new HashMap<>();
        List<JournalAppConfigurationEntity> all = journalAppConfigurationRepository.findAll();
        for(JournalAppConfigurationEntity journalAppConfigurationEntity : all){
            appCache.put(journalAppConfigurationEntity.getKey(),journalAppConfigurationEntity.getValue());
        }
    }

}
