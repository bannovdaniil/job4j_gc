package ru.job4j.cache;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Scanner;

public class Emulator {
    private List<String> items = List.of("Set dirs", "Put cache file", "Get cache file", "Show all", "Exit");
    private DirFileCache dirFileCache;
    private Path dirPath;

    public static void main(String[] args) {
        Emulator emulator = new Emulator();
        try (Scanner scanner = new Scanner(System.in)) {
            int command;
            do {
                emulator.showMenu();
                command = scanner.nextInt();
                if (emulator.dirFileCache == null) {
                    if (command != 1 && command != 5) {
                        command = 0;
                    }
                }
                if (command > 0 && command <= emulator.items.size()) {
                    System.out.println(emulator.items.get(command - 1));
                }
                switch (command) {
                    case (0):
                        System.out.println("Объект еще не активирован, начните с указания пути");
                        break;
                    case (1):
                        System.out.println("Enter dir path:");
                        String dir = scanner.next();
                        emulator.loadDir(dir);
                        break;
                    case (2):
                        emulator.putAllFile();
                        break;
                    case (3):
                        String file = scanner.next();
                        emulator.getCache(file);
                        break;
                    case (4):
                        emulator.dirFileCache.showMeCache();
                        break;
                    case (5):
                        System.out.println("Bye, bye...");
                        break;
                    default:
                        System.out.println("Нет такой команды.");
                }
            } while (command != 5);
        }
    }

    /**
     * получить файл из базы
     *
     * @param file - имя файла
     */
    private void getCache(String file) {
        String text = dirFileCache.get(file);
        if (text != null) {
            System.out.println(text);
        }
    }

    /**
     * заносим все файлы из директории в cache
     */
    private void putAllFile() {
        try (DirectoryStream<Path> directoryStream = Files.newDirectoryStream(dirPath)) {
            for (var file : directoryStream) {
                if (Files.isRegularFile(file)) {
                    String fileName = file.getFileName().toString();
                    dirFileCache.put(fileName, dirFileCache.load(fileName));
                }
            }
        } catch (IOException err) {
            err.printStackTrace();
        }
    }

    /**
     * создаем объект кеша.
     *
     * @param dir - папка
     */
    private void loadDir(String dir) {
        System.out.printf("You select: %s%n", dir);
        this.dirPath = Path.of(dir);
        if (!Files.exists(dirPath)) {
            System.out.println("Bad path, enter other.");
            return;
        }
        if (!Files.isDirectory(dirPath)) {
            System.out.println("It's not a directory.");
            return;
        }
        dirFileCache = new DirFileCache(dirPath.toAbsolutePath().normalize().toString());
    }

    /**
     * показать меню
     */
    public void showMenu() {
        for (int i = 0; i < items.size(); i++) {
            System.out.printf("%d. %s%n", i + 1, items.get(i));
        }
    }

}
