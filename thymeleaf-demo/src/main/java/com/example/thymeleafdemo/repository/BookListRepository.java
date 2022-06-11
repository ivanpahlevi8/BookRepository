package com.example.thymeleafdemo.repository;

import com.example.thymeleafdemo.domain.BookList;
import org.springframework.stereotype.Repository;

import java.awt.print.Book;
import java.util.*;
import java.util.stream.Collectors;

@Repository
public class BookListRepository implements CommonRepository<BookList> {

    Map<String, BookList> books = new HashMap<>();

    @Override
    public Iterable<BookList> findAll() {
        Iterable<BookList> res = books.entrySet().stream().map(Map.Entry::getValue).collect(Collectors.toList());
        return res;
    }

    @Override
    public BookList findById(String id) {
        BookList bookFound = books.get(id);
        return bookFound;
    }

    @Override
    public BookList save(BookList domain) {
        String bookId = domain.getId();
        BookList bookAdded = books.get(bookId);
        if (bookAdded != null)
        {
            bookAdded.setAuthor(domain.getAuthor());
            bookAdded.setDescription(domain.getDescription());
            bookAdded.setId(domain.getId());
            bookAdded.setIsbn(domain.getIsbn());
            bookAdded.setReader(domain.getReader());
            bookAdded.setTitle(domain.getTitle());
            domain = bookAdded;
        }
        books.put(bookId, domain);
        return books.get(bookId);
    }

    @Override
    public BookList findByIsbn(String isbn) {
        List<BookList> bookLis = new ArrayList<>(books.values());
        BookList bookGet = null;
        int numData = bookLis.size();
        for(int i = 0; i < numData; i++){
            if(bookLis.get(i).getIsbn().equals(isbn)){
                bookGet = bookLis.get(i);

                break;
            }
        }
        return bookGet;
    }

    @Override
    public BookList findByTitle(String title) {
        List<BookList> bookLis = new ArrayList<>(books.values());
        BookList bookGet = null;
        int numData = bookLis.size();
        for (int i = 0; i < numData; i++){
            if(bookLis.get(i).getTitle().equals(title)){
                bookGet = bookLis.get(i);
                System.out.println("Inside findByTitle : " + bookGet.getTitle());
                break;
            }
        }
        return bookGet;
    }

    @Override
    public BookList findByReader(String reader) {
        List<BookList> book = new ArrayList<>(books.values());
        BookList booGet = null;
        int numData = book.size();
        for(int i = 0; i < numData; i++){
            if(book.get(i).getReader().equals(reader)){
                /*
                    Untuk membandingkan dua string harus menggunakan method equals karena keetika menggunakan ==
                    compiler akan membandingkan dua memori dari string bukan value dari string
                 */
                booGet = book.get(i);
                break;
            }
        }
        return booGet;
    }

    @Override
    public BookList delter(BookList domain) {
        BookList getBook = null;
        String id = domain.getId();
        getBook = books.get(id);
        books.remove(id);
        return getBook;
    }

    @Override
    public Iterable<BookList> save(Collection<BookList> domain) {
        domain.forEach(this::save);
        return findAll();
    }
}
