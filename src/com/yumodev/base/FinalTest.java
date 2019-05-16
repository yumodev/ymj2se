package com.yumodev.base;

import com.yumodev.entry.Book;
import junit.framework.TestCase;

public class FinalTest extends TestCase {



    public void test(){
        int a = 10;
        paramTest(a);
        System.out.println(a);

        Book book = new Book("book1", 1.0f);

        System.out.println(book.toString());
        paramFinalClass(book);
        System.out.println(book.toString());
    }

    private void paramTest(int i){
        i = 20;
    }

    private void paramClass(Book book){
        book.name = "book2";
        book = new Book("book3", 1.0f);
        System.out.println(book.toString());
    }

    private void paramFinalClass(final Book book){
        book.name = "book4";
        System.out.println(book.toString());
        paramClass(book);
        //book = new Book("book3", 1.0f);
    }
}
