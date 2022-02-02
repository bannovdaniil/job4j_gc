package ru.job4j.gc;

import java.util.Random;

public class CommandArgs {
    public static void main(String[] args) {
        System.out.println("Start...");
        Random random = new Random();
        int length = 100;
        String[] data = new String[1_000_000];
        for (int i = 0; ; i = (i + 1) % data.length) {
            data[i] = String.valueOf(
                    (char) random.nextInt(255)
            ).repeat(length);
        }
    }
}
