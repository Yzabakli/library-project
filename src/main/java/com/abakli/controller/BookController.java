package com.abakli.controller;

import com.abakli.client.BookClient;
import com.abakli.dto.BookDTO;
import com.abakli.dto.SearchDTO;
import com.abakli.service.BookService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequestMapping("/book")
public class BookController {

    private final BookService bookService;
    private final BookClient bookClient;

    public BookController(BookService bookService, BookClient bookClient) {
        this.bookService = bookService;
        this.bookClient = bookClient;
    }

    @GetMapping
    public String readAllBooks(Model model) {

//        model.addAttribute("books", bookService.readAll());
        model.addAttribute("books", bookService.readAllFromRest());
        return "book/browse";
    }

    @GetMapping("/search")
    public String search(Model model) {

        model.addAttribute(new SearchDTO());
//        model.addAttribute("books", bookService.readAll());
        model.addAttribute("books", bookService.readAllFromRest());

        return "book/search";
    }

    @PostMapping("/search")
    public String search(SearchDTO searchDTO, Model model) {

//        model.addAttribute("books", bookService.readAll());
        model.addAttribute("books", bookService.readAllFromRest());

        return "redirect:/book/search/" + searchDTO.getKeyword();
    }

    @GetMapping("/search/{keyword}")
    public String searchBySubject(@PathVariable String keyword, Model model) {

        model.addAttribute(new SearchDTO());
//        model.addAttribute("books", bookService.getByTitleContains(keyword));
        model.addAttribute("books", bookService.getBySubjectFromRest(keyword));

        return "book/search";
    }

    @GetMapping("/borrow/{id}")
    public String borrow(@PathVariable Long id) {

        bookService.borrow(id);

        return "redirect:/book";
    }

    @GetMapping("/borrow/api/{key}")
    public String borrowFromAPI(@PathVariable String key) {

        bookService.borrow(bookService.saveFromAPI(key).getId());

        return "redirect:/book";
    }

    @GetMapping("/create")
    public String createBook(Model model) {

        model.addAttribute(new BookDTO());
        model.addAttribute(bookService.readAll());

        return "book/create";
    }

    @PostMapping("/create")
    public String createBook(@Valid BookDTO bookDTO, BindingResult bindingResult, Model model) {

        if (bindingResult.hasErrors()) {

            model.addAttribute(bookService.readAll());

            return "book/create";

        }
        bookService.save(bookDTO);

        return "redirect:/book/create";
    }

    @GetMapping("/update/{bookId}")
    public String updateBook(@PathVariable Long bookId, Model model) {

        model.addAttribute(bookService.findById(bookId));
        model.addAttribute(bookService.readAll());

        return "book/update";
    }

    @PostMapping("/update/{id}")
    public String updateBook(BookDTO bookDTO) {

        bookService.update(bookDTO);

        return "redirect:/book/create";
    }

    @GetMapping("/delete/{id}")
    public String deleteBook(@PathVariable Long id) {

        bookService.delete(id);

        return "redirect:/book/create";
    }
}
