package com.sda;

import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Created by RENT on 2017-08-24.
 */
public class PersonFileManager {

    private static final String SEPARATOR = ",";
    private Reader source;

    public PersonFileManager(String path) throws FileNotFoundException {


            source = new FileReader(path);

    }
    public List<Person> readPersonList(){

        BufferedReader br;

            if(source != null){
            BufferedReader reader = new BufferedReader(source) ;

                return reader.lines()

                        .map(mapToPerson)
                        .collect(Collectors.toList());

            }
            return null;
        }


    public double getFemaleCount(List<Person> list) {

        return list.stream().map(person -> person.getGender())
                .filter(gender -> gender.contains("Female"))
                .count();

    }

    public long getDifferenceInDays(Person p1, Person p2){


        return getDifferenceInDays(p1.getDate(),p2.getDate());
    }
    public Optional<Person> getOldestPersonLambda(List<Person> list){

        return list.stream().sorted(Comparator.comparing(Person::getDate)).findFirst();
    }

    Comparator<Person> byAge =
            (Person o1, Person o2)-> (int)getDifferenceInDays(o1,o2);

    public Person getOldestPerson(List<Person> list){
        Person oldest = list.get(0);
        for (int i = 1; i < list.size(); i++) {
            if(getDifferenceInDays(oldest.getDate(),list.get(i).getDate())<0){
                oldest = list.get(i);
            }
        }
        //int result = list.stream().map(person -> person.getDate()).

        return oldest;
    }
    private long getDifferenceInDays(String date1, String date2){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate firstDate = LocalDate.parse(date1, formatter   );
        LocalDate secondDate = LocalDate.parse(date2, formatter);
        long between = ChronoUnit.DAYS.between(firstDate,secondDate);
        return between;
    }

    private Function<String, Person> mapToPerson = (line) -> {
        String[] p = line.split(SEPARATOR);// a CSV has comma separated lines
        Person person = new Person();
        person.setName(p[0].trim());//<-- this is the first column in the csv file
        person.setGender(p[1].trim());
        person.setDate(p[2].trim());
        //more initialization goes here
        return person;
    };

}