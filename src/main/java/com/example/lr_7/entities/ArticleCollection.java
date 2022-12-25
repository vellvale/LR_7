package com.example.lr_7.entities;

import com.example.lr_7.exception.CollectionException;
import com.example.lr_7.exception.CollectionRuntimeException;
import com.example.lr_7.interfaces.PaperCollection;

import java.io.*;
import java.util.Objects;

public class ArticleCollection implements PaperCollection {
    private String name;                            // название сборника статей
    private int annotation;                         // кол-во страниц аннотации
    private int[] numberOfPagesArticles;    // кол-во страниц каждой статьи

    public ArticleCollection() {                    // конструктор по умолчанию
        name = "Название сборника статей";
        annotation = 0;
        numberOfPagesArticles = new int[]{};
    }

    public ArticleCollection(String name, int annotation, int[] numberOfPagesArticles) {
        if (name == null || numberOfPagesArticles == null || annotation <= 0) {
            throw new CollectionRuntimeException("Неверные параметры");
        }
        this.name = name;
        this.annotation = annotation;
        this.numberOfPagesArticles = numberOfPagesArticles;
    }

    public ArticleCollection(String name, int annotation, int numOfEls) {
        this.name = name;
        this.annotation = annotation;
        this.numberOfPagesArticles = new int[numOfEls];
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
        return numberOfPagesArticles;
    }

    public void setNumberOfPagesArray(int value, int index) {
        if (isValidIndex(index))
            numberOfPagesArticles[index] = value;
    }

    private boolean isValidIndex(int index) {
        return index >= 0 && index < numberOfPagesArticles.length;
    }

    public int sumOfElements() {
        int sum = 0;
        for (Integer i : numberOfPagesArticles)
            sum += i;
        return sum;
    }

    public int getLength() {
        return numberOfPagesArticles.length;
    }

    public int getItem(int index) {
        if (!isValidIndex(index))
            throw new CollectionRuntimeException("Выход за границы массива");
        return numberOfPagesArticles[index];
    }
    // endregion


    // бизнес-метод  -  подсчет общего количества страниц без учета аннотаций статей
    public int calculateTotalNumberOfPagesWithoutAnnotation() throws CollectionException {

        int i = getLength() * annotation;       // суммарное кол-во страниц аннотаций в сборнике
        return sumOfElements() - i;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        String NL = System.getProperty("line.separator");

        result.append("ArticleCollection Объект {" + NL);
        result.append("Название сборника: " + name + NL);
        result.append("Кол-во страниц аннотации: " + annotation + NL);
        result.append("Кол-во страниц: [ ");
        for (Integer p : numberOfPagesArticles) {
            result.append('"');
            result.append(p);
            result.append("\" ");
        }
        result.append("]" + NL);
        result.append("}");

        return result.toString();
    }

    @Override
    public boolean equals(Object obj) {
        return Objects.nonNull(obj) && obj instanceof ArticleCollection && obj.hashCode() == hashCode();
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, annotation, numberOfPagesArticles);
    }


    //************ ЛР4

    //реализовать добавленные в интерфейс методы в обоих классах
    public void output(OutputStream out) throws IOException {                //запись в байтовый поток (поток вывода в файл)
        DataOutputStream dataOutputStream = new DataOutputStream(out);
        dataOutputStream.writeUTF(getClass().getName());
        dataOutputStream.writeUTF(name);
        dataOutputStream.writeInt(annotation);
        dataOutputStream.writeInt(numberOfPagesArticles.length);
        for (int i : numberOfPagesArticles) {
            dataOutputStream.writeInt(i);
        }
    }

    public void write(Writer out) {                                           //запись в символьный поток
        PrintWriter writer = new PrintWriter(out);
        writer.printf("%s ", getClass().getName());
        writer.printf("%s ", name);
        writer.printf("%d ", annotation);
        writer.printf("%d ", numberOfPagesArticles.length);
        for (int i : numberOfPagesArticles) {
            writer.printf("%d ", i);
        }
        writer.println();
    }
}
