package com.org.journalApp.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@NoArgsConstructor
@Document(collection = "journal_app_configurations")
public class JournalAppConfigurationEntity {

    private String key;
    private String value;

}
