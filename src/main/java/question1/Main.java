package question1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static java.util.stream.Collectors.groupingBy;

public class Main {

    public static void main(String[] args) {
        List<String> list = new ArrayList<>(Arrays.asList("Amir", "Hatef", "Mehran", "Mojtaba", "Mohammad"
                , "Ali", "Davood", "Reza", "Mohsen"));

        System.out.println("A:\n");
        System.out.println(list.stream().collect(groupingBy(String::length)));
    }
}
