package com.org.journalApp.entity;

import lombok.*;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

//@Getter
//@Setter
//@NoArgsConstructor
//@AllArgsConstructor
//@Builder
//@EqualsAndHashCode
//@ToString
//@Data- this annotation alone add all the above things.

//pojo class- plain old java object
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(collection="users")
public class User {
    @Id
    private ObjectId id;

    @NonNull
    @Indexed(unique = true)
    private String username;

    private String email;

    private boolean sentimentAnalysis;

    @NonNull
    private String password;

    @DBRef
    private List<JournalEntry> journalEntries = new ArrayList<>();

    private List<String> roles;

}
