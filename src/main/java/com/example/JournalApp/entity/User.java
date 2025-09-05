package com.example.JournalApp.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Document(collection = "users")
@Data // lombox include all the getters,setters
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    private ObjectId id;

    @Indexed(unique = true)
    @NonNull // Lombok annotation
    public String userName;

    @NonNull // Lombok annotation
    public String password;

    public String email;

    public boolean sentimentAnalysis;

    @DBRef
    public List<JournalEntry> journalEntries=new ArrayList<>();

    private List<String> roles;

}
