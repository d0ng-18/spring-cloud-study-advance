package com.slayerd.Client;

import com.slayerd.Client.impl.BookFallBackClient;
import com.slayerd.entity.Book;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient(value = "bookservice",fallback = BookFallBackClient.class)
public interface BookClient {
    @RequestMapping("/book/{bid}")
    Book findBookById(@PathVariable("bid") int bid);
}
