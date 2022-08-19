package com.slayerd.service.impl;

import com.slayerd.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.slayerd.entity.Book;
import com.slayerd.mapper.BookMapper;


@Service
public class BookServiceImpl implements BookService {

    @Autowired
    private BookMapper bookMapper;

    @Override
    public Book getBookById(int bid) {
        return bookMapper.getBookById(bid);
    }

    @Override
    public boolean setRemain(int bid, int count) {
        return bookMapper.setRemain(bid, count) > 0;
    }

    @Override
    public int getRemain(int bid) {
        return bookMapper.getRemain(bid);
    }
}
