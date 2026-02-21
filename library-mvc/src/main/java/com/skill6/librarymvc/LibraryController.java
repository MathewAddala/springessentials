package com.skill6.librarymvc;

import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestController
public class LibraryController {

    private List<String> bookTitles = new ArrayList<>(
            Arrays.asList("Spring Boot", "Java Basics", "Microservices"));

    private List<Book> books = new ArrayList<>();

    @GetMapping("/welcome")
    public String welcome() {
        return "Welcome to the Online Library!";
    }

    @GetMapping("/count")
    public int count() {
        return bookTitles.size();
    }

    @GetMapping("/price")
    public double price() {
        return 499.99;
    }

    @GetMapping("/books")
    public List<String> getBooks() {
        return bookTitles;
    }

    @GetMapping("/books/{id}")
    public String getBookById(@PathVariable int id) {
        if (id >= 0 && id < bookTitles.size()) {
            return "Book ID: " + id + ", Title: " + bookTitles.get(id);
        }
        return "Book not found";
    }

    @GetMapping("/search")
    public String search(@RequestParam String title) {
        return "Searching for book titled: " + title;
    }

    @GetMapping("/author/{name}")
    public String author(@PathVariable String name) {
        return "Books written by: " + name;
    }

    @PostMapping("/addbook")
    public String addBook(@RequestBody Book book) {
        books.add(book);
        return "Book added successfully!";
    }

    @GetMapping("/viewbooks")
    public List<Book> viewBooks() {
        return books;
    }
}