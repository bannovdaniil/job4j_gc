package ru.job4j.gc.leak;

import ru.job4j.gc.leak.model.Post;

import java.util.Random;
import java.util.Scanner;

/**
 * Нужно поправить код, устранив факторы, которые могут привести к утечке памяти, а также оптимизировать код в местах,
 * где создаются лишние объекты в хипе.
 */
public class Menu {
    private static final int ADD_POST = 1;
    private static final int ADD_MANY_POST = 2;
    private static final int SHOW_ALL_POSTS = 3;
    private static final int DELETE_POST = 4;
    private static final String SELECT = "Выберите меню";
    private static final String COUNT = "Выберите количество создаваемых постов";
    private static final String TEXT_OF_POST = "Введите текст";
    private static final String EXIT = "Конец работы";
    private static final String ID_FOR_DELETE = "Все посты удалены.";
    private static final String MENU = """
                Введите 1 для создание поста.
                Введите 2, чтобы создать определенное количество постов.
                Введите 3, чтобы показать все посты.
                Введите 4, чтобы удалить все посты.
                Введите любое другое число для выхода.
            """;

    private static CommentGenerator commentGenerator;
    private static PostStore postStore;
    private static Scanner scanner;

    public static void main(String[] args) {
        Random random = new Random();
        commentGenerator = new CommentGenerator(random, new UserGenerator(random));
        scanner = new Scanner(System.in);
        postStore = new PostStore();

        start();
    }

    private static void start() {
        String postText;
        boolean run = true;
        while (run) {
            System.out.println(MENU);
            System.out.println(SELECT);
            int userChoice = Integer.parseInt(scanner.nextLine());
            System.out.println(userChoice);

            switch (userChoice) {
                case ADD_POST:
                    postText = getPostText(scanner);
                    createPost(postText);
                    break;
                case ADD_MANY_POST:
                    postText = getPostText(scanner);
                    System.out.println(COUNT);
                    int count = Integer.parseInt(scanner.nextLine());
                    for (int i = 0; i < count; i++) {
                        createPost(postText);
                    }
                    break;
                case SHOW_ALL_POSTS:
                    postStore.getPosts().forEach(System.out::println);
                    break;
                case DELETE_POST:
                    System.out.println(ID_FOR_DELETE);
                    postStore.removeAll();
                    break;
                default:
                    run = false;
                    System.out.println(EXIT);
            }
        }
    }

    private static String getPostText(Scanner scanner) {
        System.out.println(TEXT_OF_POST);
        return scanner.nextLine();
    }

    private static void createPost(String text) {
        commentGenerator.generate();
        postStore.add(new Post(text, commentGenerator.getComments()));
    }
}