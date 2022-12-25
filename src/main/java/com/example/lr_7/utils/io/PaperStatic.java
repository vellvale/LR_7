package com.example.lr_7.utils.io;

import com.example.lr_7.entities.ArticleCollection;
import com.example.lr_7.entities.StoryCollection;
import com.example.lr_7.exception.CollectionRuntimeException;
import com.example.lr_7.exception.EmptyException;
import com.example.lr_7.interfaces.PaperCollection;

import java.io.*;
import java.util.Scanner;
import java.util.StringTokenizer;

public class PaperStatic {
    //запись в байтовый поток
    public static void output(PaperCollection o, OutputStream out) throws IOException {
        o.output(out);
    }

    //чтение из байтового потока
    public static PaperCollection input(InputStream in) throws IOException, CollectionRuntimeException, ClassNotFoundException {
        PaperCollection p;

        DataInputStream dataInputStream;
        try {
            dataInputStream = new DataInputStream(in);
            String className = dataInputStream.readUTF();
            String name = dataInputStream.readUTF();
            int annotation = dataInputStream.readInt();
            int numOfEls = dataInputStream.readInt();

            p = getNewPaperByClassName(className, name, annotation, numOfEls);

            final int length = numOfEls;
            int numberOfPages;
            for (int i = 0; i < length; i++) {
                numberOfPages = dataInputStream.readInt();
                p.setNumberOfPagesArray(numberOfPages, i);
            }

        }
        catch(CollectionRuntimeException exc){
            System.out.println(exc.getMessage());
            p = null;
        }
        if (p == null) {
            throw new CollectionRuntimeException("Не удалось считать PaperCollection");
        }
        return p;
    }


    //запись в символьный поток
    public static void write(PaperCollection o, Writer out) throws IOException {
        o.write(out);
    }

    //чтение из символьного потока
    public static PaperCollection read(Reader in) throws IOException, CollectionRuntimeException, EmptyException {
        PaperCollection p;

        BufferedReader br = (BufferedReader) in;
        String line = br.readLine();

        if (line == null)
            throw new EmptyException();

        StringTokenizer t = new StringTokenizer(line, " ");
        String className = t.nextToken();
        String name = t.nextToken();
        int annotation = Integer.parseInt(t.nextToken());
        int numOfEls = Integer.parseInt(t.nextToken());

        try {
            p = getNewPaperByClassName(className, name, annotation, numOfEls);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        final int length = numOfEls;
        int numberOfPages;
        for (int i = 0; i < length; i++) {
            numberOfPages = Integer.parseInt(t.nextToken());
            p.setNumberOfPagesArray(numberOfPages, i);
        }
        if (p == null) {
            throw new CollectionRuntimeException("Не удалось считать PaperCollection");
        }
        return p;
    }

    //перевод структуры данных в битовую последовательность
    public static void serialize(PaperCollection o, OutputStream out) throws IOException {
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(out);
        objectOutputStream.writeObject(o);
    }

    public static PaperCollection deserialize(InputStream in) throws IOException, ClassNotFoundException {
        ObjectInputStream objectInputStream = new ObjectInputStream(in);
        return (PaperCollection) objectInputStream.readObject();
    }


    // Добавить в класс со статическими методами методы для форматного текстового ввода и вывода
    public static void writeFormat(PaperCollection o, Writer out) {
        try (PrintWriter printWriter = new PrintWriter(out)) {
            printWriter.printf("Название:" + o.getName());
            printWriter.printf("\nКоличество страниц аннотации:" + o.getAnnotation());
            printWriter.println(o.getNumberOfPagesArray().length); //размер списка
            printWriter.println("\nКоличество страниц:");
            for (int viewingTime : o.getNumberOfPagesArray()) {
                printWriter.println(viewingTime);
            }
        }
    }

    public static PaperCollection readFormat(Scanner in) throws EmptyException {
        String nameLine = in.nextLine();
        if (nameLine == null) {
            throw new EmptyException("Файл пустой");
        } else {
            String name = in.nextLine();
            in.nextLine();
            int annotation = Integer.parseInt(in.nextLine());
            int length = Integer.parseInt(in.nextLine());
            in.nextLine();
            int[] numberOfPages = new int[length];
            for (int i = 0; i < length; i++) {
                numberOfPages[i] = Integer.parseInt(in.nextLine());
            }
            return new ArticleCollection(name, annotation, numberOfPages);
        }
    }

    private static PaperCollection getNewPaperByClassName(String className, String name, int annotation, int numOfEls) throws ClassNotFoundException {
        if (className.equals(ArticleCollection.class.getName())) {
            return new ArticleCollection(name, annotation, numOfEls);
        } else if (className.equals(StoryCollection.class.getName())) {
            return new StoryCollection(name, annotation, numOfEls);
        } else {
            throw new ClassNotFoundException("Ошибка: такого класса не существует");
        }
    }
}
