package com.abakli.controller;

import com.abakli.service.BorrowerRecordService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/record")
public class RecordController {

    private final BorrowerRecordService service;

    public RecordController(BorrowerRecordService service) {
        this.service = service;
    }

    @GetMapping("/pending-returns")
    public String readPending(Model model) {

        model.addAttribute(service.findAllByUserContactUnreturned());

        return "record/return-history";
    }

    @GetMapping("/return-history")
    public String readReturns(Model model) {

        model.addAttribute(service.findAllByUserContactReturned());

        return "record/return-history";
    }

    @GetMapping("/pending")
    public String returnBook(Model model) {

        model.addAttribute(service.findAllUnreturned());

        return "record/return-record";
    }

    @GetMapping("/return-records")
    public String readRecords(Model model) {

        model.addAttribute(service.findAllReturned());

        return "record/return-record";
    }

    @GetMapping("/return/{id}")
    public String returnBook(@PathVariable Long id) {

        service.returnBook(id);

        return "redirect:/record/pending";
    }

    @GetMapping("/delete/{id}")
    public String deleteRecord(@PathVariable Long id) {

        service.delete(id);

        return "redirect:/record/return-records";
    }
}
