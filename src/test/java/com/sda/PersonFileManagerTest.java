package com.sda;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

/**
 * Created by RENT on 2017-08-24.
 */

public class PersonFileManagerTest {
    List<Person> list;
    PersonFileManager personFileManager;
    @Before
    public void setUp(){
        try {
            personFileManager = new PersonFileManager("src\\main\\resources\\address-book.csv");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        list = personFileManager.readPersonList();
    }

    @Test
    public void shouldReturnListWithCorrectSize() throws IOException {

        Assert.assertEquals(5, list.size());
    }

    @Test(expected = FileNotFoundException.class)
    public void shouldThrowExceptionWhenFileNotFound() throws FileNotFoundException {
        personFileManager = new PersonFileManager("src\\main\\esources\\address-book.csv");
    }

    @Test
    public void shouldReturn2ForFemaleCount(){

        Assert.assertEquals(2.0, personFileManager.getFemaleCount(list),0.1);
    }

    @Test
    public void shouldRetrunSarahAsTheOldest(){
        Person expected = new Person("Sarah", "Female", "1980-08-17");

        Person result = personFileManager.getOldestPerson(list);
        Assert.assertEquals(expected, result);
    }

    @Test
    public void shouldReturnDaysDifference(){
        Long result = personFileManager.getDifferenceInDays(list.get(1),list.get(2));
        Long expected = 1934L;
        Assert.assertEquals(expected, result);

    }


}