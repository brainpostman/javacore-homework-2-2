package com.company;

import java.util.*;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) {
        List<String> names = Arrays.asList("Jack", "Connor", "Harry", "George", "Samuel", "John");
        List<String> families = Arrays.asList("Evans", "Young", "Harris", "Wilson", "Davies", "Adamson", "Brown");
        Collection<Person> persons = new ArrayList<>();
        for (int i = 0; i < 10_000_000; i++) {
            persons.add(new Person(
                    names.get(new Random().nextInt(names.size())),
                    families.get(new Random().nextInt(families.size())),
                    new Random().nextInt(100),
                    Sex.values()[new Random().nextInt(Sex.values().length)],
                    Education.values()[new Random().nextInt(Education.values().length)])
            );
        }
        int minors = (int)persons.stream()
                .filter(p -> p.getAge() < 18)
                .count();
        List<String> conscripts = persons.stream()
                .filter(p -> p.getSex() == Sex.MAN )
                .filter(p -> p.getAge() > 17)
                .filter(p -> p.getAge() < 28)
                .map(Person::getFamily)
                .collect(Collectors.toList());
        List<Person> workAble = persons.stream()
                .filter(p -> p.getEducation() == Education.HIGHER)
                .filter(p -> p.getAge() > 17)
                .filter(p -> p.getAge() < 66)
                .filter(p -> (p.getSex() == Sex.WOMAN && p.getAge() < 61) || p.getSex() == Sex.MAN)
                .sorted(Comparator.comparing(Person::getFamily))
                .collect(Collectors.toList());
    }
}
