package ru.pwflamy;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class AuctionApplication {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.scan("ru.pwflamy");
        context.refresh();
    }
}