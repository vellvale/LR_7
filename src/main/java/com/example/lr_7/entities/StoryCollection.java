package com.example.lr_7.entities;

import com.example.lr_7.exception.CollectionException;
import com.example.lr_7.exception.CollectionRuntimeException;
import com.example.lr_7.interfaces.PaperCollection;

import java.io.*;
import java.util.Objects;

public class StoryCollection implements PaperCollection {
    private String name;                            // название сборника рассказов
    private int annotation;                         // кол-во страниц вводной информации
    private int[] numberOfPagesStories;     // кол-во страниц каждого рассказа

    public StoryCollection() {                      // конструктор по умолчанию
        name = "Название сборника рассказов";
        annotation = 0;
        numberOfPagesStories = new int[]{};
    }

    public StoryCollection(String name, int annotation, int[] numberOfPagesStories) {
        if (name == null || numberOfPagesStories == null || annotation <= 0) {
            throw new CollectionRuntimeException("Неверные параметры");
        }
        this.name = name;
        this.annotation = annotation;
        this.numberOfPagesStories = numberOfPagesStories;
    }

    public StoryCollection(String name, int annotation, int numOfEls) {
        this.name = name;
        this.annotation = annotation;
        this.numberOfPagesStories = new int[numOfEls];
    }

    // region методы доступа к полям и к элементам массива
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAnnotation() {
        return annotation;
    }

    public void setAnnotation(int annotation) {
        if (annotation < 0)
            throw new CollectionRuntimeException("Количество вводных страниц не может быть отрицательным");
        else
            this.annotation = annotation;
    }

    public int[] getNumberOfPagesArray() {
        return numberOfPagesStories;
    }

    public void setNumberOfPagesArray(int value, int index) {
        if (isValidIndex(index))
            numberOfPagesStories[index] = value;
    }

    private boolean isValidIndex(int index) {
        return index >= 0 && index < numberOfPagesStories.length;
    }

    public int sumOfElements() {
        int sum = 0;
        for (Integer i : numberOfPagesStories)
            sum += i;
        return sum;
    }

    public int getLength() {
        return numberOfPagesStories.length;
    }

    public int getItem(int index) {
        if (!isValidIndex(index))
            throw new CollectionRuntimeException("Выход за границы массива");
        return numberOfPagesStories[index];
    }
    // endregion


    // бизнес-метод  -  подсчет общего количества страниц сборника без учета вводных страниц книг
    public int calculateTotalNumberOfPagesWithoutAnnotation() throws CollectionException {
        int i = getLength() * annotation;               // суммарное кол-во страниц аннотаций в сборнике
        return sumOfElements() - i;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        String NL = System.getProperty("line.separator");

        result.append("StoryCollection Объект {" + NL);
        result.append("Название сборника: " + name + NL);
        result.append("Кол-во страниц аннотации: " + annotation + NL);
        result.append("Кол-во страниц: [ ");
        for (Integer p : numberOfPagesStories) {
            result.append('"');
            result.append(p);
            result.append("\" ");
        }
        result.append("]" + NL);
        result.append("}" + NL);

        return result.toString();
    }

    @Override
    public boolean equals(Object obj) {
        return Objects.nonNull(obj) && obj instanceof StoryCollection && obj.hashCode() == hashCode();
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, annotation, numberOfPagesStories);
    }

    //************

    //реализовать добавленные в интерфейс методы в обоих классах
    public void output(OutputStream out) throws IOException {                //запись в байтовый поток (поток вывода в файл)
        DataOutputStream dataOutputStream = new DataOutputStream(out);
        dataOutputStream.writeUTF(getClass().getName());
        dataOutputStream.writeUTF(name);
        dataOutputStream.writeInt(annotation);
        dataOutputStream.writeInt(numberOfPagesStories.length);
        for (int i : numberOfPagesStories) {
            dataOutputStream.writeInt(i);
        }
    }

    public void write(Writer out) {                                          //запись в символьный поток
        PrintWriter writer = new PrintWriter(out);
        writer.printf("%s ", getClass().getName());
        writer.printf("%s ", name);
        writer.printf("%d ", annotation);
        writer.printf("%d ", numberOfPagesStories.length);
        for (int i : numberOfPagesStories) {
            writer.printf("%d ", i);
        }
        writer.println();
    }
}
