package edu.psu.mobydickens.controller;

import edu.psu.mobydickens.model.Book;
import edu.psu.mobydickens.service.BookService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class IndexController {

    // For the main page
    @GetMapping("/")
    public String index(Model model) {
        return "homepage";
    }                                         // add a button with the link to the in stock page




}
