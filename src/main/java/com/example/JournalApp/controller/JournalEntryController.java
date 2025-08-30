//package com.example.JournalApp.controller;
//
//import com.example.JournalApp.entity.JournalEntry;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//@RestController
//@RequestMapping("/_journal")
//public class JournalEntryController {
//    // endpoints declared as methods
//
//    // As we don't integrate the DB yet so using Map as table to store Journal Entries
//    public Map<String,JournalEntry> journalEntries = new HashMap<>();
//
//    @GetMapping
//    public List<JournalEntry> getAll(){  //localhost:8080/journal
//       return new ArrayList<>(journalEntries.values());
//    }
//
//    @PostMapping
//    public boolean createEntry(@RequestBody JournalEntry myEntry){  //localhost:8080/journal
//        journalEntries.put(myEntry.getId(),myEntry);
//        return true;
//    }
//
//    @GetMapping("id/{myId}")
//    public JournalEntry getJournalEntryById(@PathVariable String myId){
//            return journalEntries.get(myId);
//    }
//
//    @DeleteMapping("id/{myId}")
//    public String deleteJournalEntry(@PathVariable String myId){
//        journalEntries.remove(myId);
//        return "Journal Entry removed with ID :" + myId;
////        return true;
//    }
//
//    @PutMapping("id/{myId}")
//    public JournalEntry updateJournalEntryById(@PathVariable String myId,@RequestBody JournalEntry myEntry){
//      return journalEntries.put(myId,myEntry);
//    }
//}
