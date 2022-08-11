package com.slayerd.Client.impl;

import com.slayerd.Client.BookClient;
import com.slayerd.entity.Book;
import org.springframework.stereotype.Component;

@Component
public class BookFallBackClient implements BookClient {
    @Override
    public Book findBookById(int bid) {
        Book book = new Book();
        book.setDesc("补救措施");
        return book;
    }
}
