package com.example.JournalApp.service;

import com.example.JournalApp.entity.JournalEntry;
import com.example.JournalApp.entity.User;
import com.example.JournalApp.repository.JournalEntryRepository;
import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class JournalEntryService {

        @Autowired
        private JournalEntryRepository journalEntryRepository;

        @Autowired
        private UserService userService;



        @Transactional
        public void saveEntry(JournalEntry journalEntry, String userName){
           try {
               User user=userService.findByUserName(userName);
               journalEntry.setDate(LocalDateTime.now());
               JournalEntry saved = journalEntryRepository.save(journalEntry);

               user.getJournalEntries().add(saved);// saving the JE in user JE' array
               userService.saveEntry(user);
           } catch (RuntimeException e) {
               log.error("Error",e);
               throw new RuntimeException("An error Occurred while saving the entry.",e);
           }
        }

        public void saveEntry(JournalEntry journalEntry){
            journalEntryRepository.save(journalEntry);
        }

        public List<JournalEntry> getAll(){
           return journalEntryRepository.findAll();
        }

        public Optional<JournalEntry> findById(ObjectId id){
            return journalEntryRepository.findById(id);
        }

        @Transactional
        public boolean deleteById(ObjectId id,String userName){
            boolean removed=false;
            try {
                User user=userService.findByUserName(userName);
                removed=user.getJournalEntries().removeIf(x -> x.getId().equals(id));// iterating through journal entries
                if(removed){
                    userService.saveEntry(user);
                    journalEntryRepository.deleteById(id);
                }

            } catch (RuntimeException e) {

                throw new RuntimeException("An error Occurred while deleting the entry.",e);
            }
            return removed;

        }

        public void deleteAllJEs(){
            journalEntryRepository.deleteAll();
        }
}

// controller ---> service ---> repository