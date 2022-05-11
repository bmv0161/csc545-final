import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Arrays;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        Validator v = new Validator();
        System.out.println(v.printRecords());
        Mapper.update("ticket, trip", "fare = fare + 1", "ticket.id = trip.id and Trip.Origin = 'Temple University'");
        String[] str1 = Mapper.select("route", "route");
        System.out.println(Arrays.toString(str1));

        String[][] str = Mapper.select("ticket, trip", "ticket.id, fare, trip.origin, trip.destination", "where trip.id = ticket.id");
        for(int i = 0; i < str.length; i++) { System.out.println(Arrays.toString(str[i])); }
    }
}