package com.example.lr_7.utils.io;

import com.example.lr_7.interfaces.PaperCollection;
import com.example.lr_7.exception.CollectionException;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ByteOperations {

    public static void byteOutputPaperCollection(List<PaperCollection> paperCollectionList, String fileName)
            throws IOException {
        try (FileOutputStream fos = new FileOutputStream(fileName)) {
            for (PaperCollection motionPicture : paperCollectionList) {
                PaperStatic.output(motionPicture, fos);
            }
        }
    }

    public static List<PaperCollection> byteInputPaperCollection(String fileName)
            throws IOException, CollectionException {
        List<PaperCollection> paperCollectionList = new ArrayList<>();
        try (FileInputStream fis = new FileInputStream(fileName)) {
            while (fis.available() != 0) {
                PaperCollection collection = PaperStatic.input(fis);
                paperCollectionList.add(collection);
            }
        } catch (FileNotFoundException | ClassNotFoundException ex) {
            throw new IOException(ex.getMessage());
        }
        return paperCollectionList;
    }
}
