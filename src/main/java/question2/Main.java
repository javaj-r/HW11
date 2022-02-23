package question2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;

public class Main {

    public static void main(String[] args) {
        List<String> list = new ArrayList<>(Arrays.asList("Amir", "Hatef", "Mehran", "Mojtaba", "Mohammad"
                , "Ali", "Davood", "Reza", "Mohsen"));

        System.out.println("A:");
        System.out.println(list.stream().collect(groupingBy(String::length)));

        System.out.println("\nB:");

        System.out.println(list.stream().collect(groupingBy(String::length, counting())));

        System.out.println();

        System.out.println(list.stream().collect(groupingBy(String::length))
                .values().stream()
                .collect(groupingBy(List::size)));

        System.out.println();

        System.out.println(list.stream().collect(groupingBy(String::length))
                .entrySet().stream()
                .collect(groupingBy(e -> e.getValue().size())));

    }
}
