package com.sda;

import java.io.FileNotFoundException;
import java.util.List;
import java.util.Optional;

public class Main {

    public static void main(String[] args) {

        PersonFileManager personFileManager = null;
        try {
            personFileManager = new PersonFileManager("src\\main\\resources\\address-book.csv");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        List<Person> list = personFileManager.readPersonList();
        Person oldest = personFileManager.getOldestPerson(list);
        System.out.println(oldest.getName());
        Optional<Person> oldestLambda = personFileManager.getOldestPersonLambda(list);
        System.out.println("lambda");
        if(oldestLambda.isPresent()) System.out.println(oldestLambda);

    }
}
