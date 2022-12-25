package com.example.lr_7.utils.io;

import com.example.lr_7.interfaces.PaperCollection;
import com.example.lr_7.exception.EmptyException;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class Formatter {
    public static void writeFormatterPaperCollection(List<PaperCollection> paperCollectionList, String fileName)
            throws IOException {
        try (BufferedWriter br = new BufferedWriter(new FileWriter(fileName))) {
            for (PaperCollection collection : paperCollectionList) {
                PaperStatic.writeFormat(collection, br);
            }
        }
    }

    public static List<PaperCollection> readFormatterPaperCollection(String fileName) {
        List<PaperCollection> paperCollectionList = new ArrayList<>();
        try (Scanner sc = new Scanner(fileName)) {
            boolean isEndOfFile = false;
            while (!isEndOfFile) {
                PaperCollection collection;
                try {
                    collection = PaperStatic.readFormat(sc);
                    paperCollectionList.add(collection);
                } catch (EmptyException ex) {
                    isEndOfFile = true;
                }
            }
        }
        return paperCollectionList;
    }
}
