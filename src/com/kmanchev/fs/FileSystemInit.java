package com.kmanchev.fs;

import java.util.Scanner;

public class FileSystemInit {

    public static void main(String args) {

        FileSystemService fsService = new FileSystemService("root");
        Scanner scanner = new Scanner(System.in);

        while (true) {
            String command = scanner.nextLine();
            if (command.equals("end")) {
                break;
            }

            String result = fsService.digest(command);
            System.out.println(result);
            System.out.println();
        }
    }
}
