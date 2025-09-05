package com.example.JournalApp.entity;


import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("config_journal_app")
@Data
public class ConfigJournalAppEntity {

    private String key;
    private String value;
}
