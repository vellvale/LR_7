package com.example.lr_7.utils.common;

import com.example.lr_7.entities.ArticleCollection;
import com.example.lr_7.entities.StoryCollection;
import com.example.lr_7.exception.CollectionRuntimeException;

import java.util.Scanner;

public class PaperCollectionOperations {

    //добавить сборник статей
    public static ArticleCollection createArticle() {
        String name;
        int annotation;
        int[] numberOfPagesArticles;
        Scanner sc = new Scanner(System.in);
        System.out.println("Введите название сборника статей: ");
        name = sc.nextLine();
        System.out.println("Введите количество страниц аннотации: ");
        annotation = fillIntValue(sc);
        System.out.println("Через пробел введите значения количества страниц сборника статей: ");
        numberOfPagesArticles = fillNumberOfPagesValue(sc);
        return new ArticleCollection(name, annotation, numberOfPagesArticles);
    }

    public static ArticleCollection createArticle(String name, int annotation, int[] numberOfPages) {
        return new ArticleCollection(name, annotation, numberOfPages);
    }

    public static StoryCollection createStory() {
        String name;
        int annotation;
        int[] numberOfPagesStories;
        Scanner sc = new Scanner(System.in);
        System.out.println("Введите название сборника рассказов: ");
        name = sc.nextLine();
        System.out.println("Введите количество страниц аннотации: ");
        annotation = fillIntValue(sc);
        System.out.println("Через пробел введите значения количества страниц сборника рассказов: ");
        numberOfPagesStories = fillNumberOfPagesValue(sc);
        return new StoryCollection(name, annotation, numberOfPagesStories);
    }

    public static StoryCollection createStory(String name, int annotation, int[] numberOfPages) {
        return new StoryCollection(name, annotation, numberOfPages);
    }

    private static int fillIntValue(Scanner s) {
        int value = 0;
        String str = s.nextLine();
        try {
            value = Integer.parseInt(str);
        } catch (NumberFormatException e) {
            throw new CollectionRuntimeException("Ошибка в заполнении значения страниц аннотации");
        }
        return value;
    }

    private static int[] fillNumberOfPagesValue(Scanner s) {
        String str = s.nextLine();
        String[] numberOfPages = str.split(" ");
        int[] parsedNumberOfPages = new int[numberOfPages.length];
        for (int i = 0; i < numberOfPages.length; i++) {
            try {
                parsedNumberOfPages[i] = Integer.parseInt(numberOfPages[i]);
            } catch (NumberFormatException e) {
                throw new CollectionRuntimeException("Ошибка в заполнении продолжительностей просмотров");
            }
        }
        return parsedNumberOfPages;
    }
}
