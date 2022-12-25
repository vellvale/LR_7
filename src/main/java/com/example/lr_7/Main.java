package com.example.lr_7;

import com.example.lr_7.exception.CollectionException;
import com.example.lr_7.interfaces.PaperCollection;
import com.example.lr_7.utils.common.PaperCollectionOperations;
import com.example.lr_7.utils.io.ByteOperations;
import com.example.lr_7.utils.io.Serialize;
import com.example.lr_7.utils.io.SymbolOperations;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static final String SERIALIZED_FILE_NAME = "serialized.bin";
    private static final String BYTE_FILE_NAME = "collections.bin";
    private static final String SYMBOL_FILE_NAME = "collections.txt";

    public static void menuLR4() {
        Scanner in = new Scanner(System.in);
        boolean isCont = true;
        List<PaperCollection> paperCollectionList = new ArrayList<>();
        while (isCont) {
            System.out.println("\n Меню функций ввода/вывода." +
                    "\n 1.Добавить ArticleCollection" +
                    "\n 2.Добавить StoryCollection" +
                    "\n 3.Сгенерировать paperCollectionList" +
                    "\n 4.Запись в байтовый поток " + BYTE_FILE_NAME +
                    "\n 5.Чтение из байтового потока " + BYTE_FILE_NAME +
                    "\n 6.Запись в символьный поток " + SYMBOL_FILE_NAME +
                    "\n 7.Чтение из символьного потока " + SYMBOL_FILE_NAME +
                    "\n 8.Сериализация объекта в файл " + SERIALIZED_FILE_NAME +
                    "\n 9.Десериализация объекта из файла " + SERIALIZED_FILE_NAME +
                    "\n 10.Показать paperCollectionList" +
                    "\n 0.Выход");
            int choice = in.nextInt();
            switch (choice) {
                case 1 -> {
                    paperCollectionList.add(PaperCollectionOperations.createArticle());
                    System.out.println("Добавлен сборник статей!");
                }
                case 2 -> {
                    paperCollectionList.add(PaperCollectionOperations.createStory());
                    System.out.println("Добавлен сборник рассказов!");
                }
                case 3 -> {
                    paperCollectionList.add(PaperCollectionOperations.createArticle("Россия", 3, new int[]{12, 13, 14, 15}));
                    paperCollectionList.add(PaperCollectionOperations.createStory("Хрестоматия", 4, new int[]{55, 23, 54, 10}));
                    System.out.println("Сгенерирован paperCollectionList!");
                }
                case 4 -> {
                    try {
                        ByteOperations.byteOutputPaperCollection(paperCollectionList, BYTE_FILE_NAME);
                        System.out.println("Информация успешно записана в байтовом виде в файл " + BYTE_FILE_NAME);
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                }
                case 5 -> {
                    try {
                        List<PaperCollection> byteInput = ByteOperations.
                                byteInputPaperCollection(BYTE_FILE_NAME);
                        System.out.println(byteInput);
                    } catch (IOException | CollectionException ex) {
                        System.out.println(ex.getMessage());
                    }
                }
                case 6 -> {
                    try {
                        SymbolOperations.writePaperCollection(paperCollectionList, SYMBOL_FILE_NAME);
                        System.out.println("Информация успешно записана в текстовом виде в файл " + SYMBOL_FILE_NAME);
                    } catch (IOException ex) {
                        System.out.println(ex.getMessage());
                    }
                }
                case 7 -> {
                    try {
                        List<PaperCollection> symbolRead =
                                SymbolOperations.readPaperCollection(SYMBOL_FILE_NAME);
                        System.out.println(symbolRead);
                    } catch (IOException | CollectionException ex) {
                        System.out.println(ex.getMessage());
                    }
                }
                case 8 -> {
                    try {
                        Serialize.serializePaperCollection(paperCollectionList,
                                SERIALIZED_FILE_NAME);
                        System.out.println("Информация успешно записана в сериализованном виде в файл " +
                                SERIALIZED_FILE_NAME);
                    } catch (IOException ex) {
                        System.out.println(ex.getMessage());
                    }
                }
                case 9 -> {
                    try {
                        List<PaperCollection> deserialized =
                                Serialize.deserializePaperCollection(SERIALIZED_FILE_NAME);
                        System.out.println(deserialized);
                    } catch (IOException | ClassNotFoundException ex) {
                        System.out.println(ex.getMessage());
                    }
                }
                case 10 -> {
                    System.out.println(paperCollectionList);
                }
                case 0 -> {
                    isCont = false;
                }
            }
        }
    }


    public static void main(String[] args) {
        menuLR4();
    }
}