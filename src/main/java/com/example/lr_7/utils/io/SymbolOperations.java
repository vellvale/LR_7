package com.example.lr_7.utils.io;

import com.example.lr_7.interfaces.PaperCollection;
import com.example.lr_7.exception.CollectionException;
import com.example.lr_7.exception.EmptyException;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class SymbolOperations {
    public static void writePaperCollection(List<PaperCollection> paperCollectionList, String fileName)
            throws IOException {
        try (FileWriter fw = new FileWriter(fileName)) {
            for (PaperCollection collection : paperCollectionList) {
                PaperStatic.write(collection, fw);
            }
        }
    }

    public static List<PaperCollection> readPaperCollection(String fileName)
            throws IOException, CollectionException {
        List<PaperCollection> paperCollectionList = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            boolean isEndOfFile = false;
            while (!isEndOfFile) {
                try {
                    PaperCollection collection = PaperStatic.read(br);
                    paperCollectionList.add(collection);
                } catch (EmptyException ex) {
                    isEndOfFile = true;
                }
            }
        } catch (FileNotFoundException ex) {
            throw new IOException(ex.getMessage());
        }
        return paperCollectionList;
    }
}
