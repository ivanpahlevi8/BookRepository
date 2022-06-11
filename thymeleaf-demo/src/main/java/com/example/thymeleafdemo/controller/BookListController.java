package com.example.thymeleafdemo.controller;

import com.example.thymeleafdemo.domain.BookList;
import com.example.thymeleafdemo.repository.CommonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/book")
public class BookListController {

    CommonRepository<BookList> repository;

    @Autowired
    public BookListController(CommonRepository<BookList> repo)
    {
        this.repository = repo;
    }

    @GetMapping("/home")
    public String getBook(Model model)
    {
        //model.addAttribute("something", "came from book list controller");
        return "home";
    }

    @GetMapping("/form")
    public String showForm(Model model){
        BookList bookAdd = new BookList();
        model.addAttribute("book", bookAdd);
        return "form";
    }

    @RequestMapping(value = "/addbook", method = {RequestMethod.GET, RequestMethod.POST})
    public String addBook(@ModelAttribute BookList book, Model mode){
        BookList bookAdded = repository.save(book);
        mode.addAttribute("book", bookAdded);
        return "upload";
    }

    @GetMapping("/showbook")
    public String showAllBook(Model model){
        List<BookList> allBook = (List<BookList>) repository.findAll();
        model.addAttribute("books", allBook);
        return "show";
    }

    @RequestMapping(value = "/showbookid", method = {RequestMethod.POST, RequestMethod.GET})
    public String showBokkById(@RequestParam(value = "id", required = false) String id, Model model){
        BookList bookGet;
        bookGet = repository.findById(id);
        model.addAttribute("book", bookGet);
        return "upload";
    }

    @RequestMapping(value = "/showbookisbn", method = {RequestMethod.POST, RequestMethod.GET})
    public String showBookByIsbn(@RequestParam(value = "isbn", required = false) String isbn, Model model){
        BookList bookGet;
        bookGet = repository.findByIsbn(isbn);
        model.addAttribute("book", bookGet);
        return "upload";
    }

    @RequestMapping(value = "/showbooktitle", method = {RequestMethod.POST, RequestMethod.GET})
    public String showBookByTitle(@RequestParam(value = "title", required = false) String title, Model model){
        BookList bookGet;
        bookGet = repository.findByTitle(title);
        model.addAttribute("book", bookGet);
        return "upload";
    }

    @RequestMapping(value = "/deletebook", method = {RequestMethod.DELETE, RequestMethod.GET})
    public String deleteBook(@RequestParam(value = "isbn", required = false) String isbn, Model model){
        BookList getBook;
        getBook = repository.findByIsbn(isbn);
        System.out.println("Title : " + getBook.getTitle());
        getBook = repository.delter(getBook);
        model.addAttribute("book", getBook);
        return "upload";
    }

    @GetMapping("/getid")
    public String getId(Model mode){
        return "idoform";
    }

    @GetMapping("/getisbn")
    public String getIsbn(Model model){
        return "isbnform";
    }

    @GetMapping("/gettitle")
    public String getTitle(Model model){
        return "titleform";
    }

    @GetMapping("/getdelete")
    public String getDelete(Model model){
        return "deleteform";
    }
}
