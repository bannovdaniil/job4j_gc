package ru.job4j.gc;

import java.util.Random;

public class Crematorium {
    private static final Runtime ENVIRONMENT = Runtime.getRuntime();

    public static void info(String message) {
        System.out.println(message);
        final long freeMemory = ENVIRONMENT.freeMemory();
        final long totalMemory = ENVIRONMENT.totalMemory();
        final long maxMemory = ENVIRONMENT.maxMemory();
        System.out.println("=== Environment state ===");
        System.out.printf("Free: %d%n", freeMemory / 1024 / 1024);
        System.out.printf("Total: %d%n", totalMemory / 1024 / 1024);
        System.out.printf("Max: %d%n", maxMemory / 1024 / 1024);
    }

    public static void main(String[] args) throws InterruptedException {
        String[] names = new String[]{"Lupen", "Rakita", "Mitya", "Elf",
                "Rembo", "Commando", "Terminator", "Combo"};
        Random random = new Random();
        int count = 0;
        
        if (1 == 1) {
            User[] users = new User[15000];
            for (int i = 0; i < users.length; i++) {
                users[i] = new User(
                        random.nextInt(20) + 16,
                        names[count++ & 0x07],
                        random.nextBoolean());
            }
            for (int i = 0; i < users.length; i++) {
                users[i] = null;
            }
        }
        User[] users2 = new User[20000];
        for (int i = 0; i < users2.length; i++) {
            users2[i] = new User(
                    random.nextInt(20) + 16,
                    names[count++ & 0x07],
                    random.nextBoolean());
        }
    }
}
