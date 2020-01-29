package tools.mahmoudmabrok.tawasol.utils;

import java.util.Scanner;

public class Parser {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        String line ;
        String[] parts;
        String engPart ;
        String araPart ;
        while (sc.hasNext()){
            line = sc.nextLine();
            parts = line.split(" ");
            engPart = parts[0];
            araPart = parts[1];

            System.out.println("araPart = " + araPart);
            System.out.println("endPart = " + engPart);
        }
    }
}
