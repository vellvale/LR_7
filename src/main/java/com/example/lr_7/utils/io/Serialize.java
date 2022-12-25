package com.example.lr_7.utils.io;

import com.example.lr_7.interfaces.PaperCollection;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Serialize {
    public static void serializePaperCollection(List<PaperCollection> paperCollectionList, String fileName)
            throws IOException {
        try (FileOutputStream fos = new FileOutputStream(fileName)) {
            for (PaperCollection collection : paperCollectionList) {
                PaperStatic.serialize(collection, fos);
            }
        }
    }

    public static List<PaperCollection> deserializePaperCollection(String fileName)
            throws IOException, ClassNotFoundException {
        List<PaperCollection> paperCollectionList = new ArrayList<>();
        try (FileInputStream fis = new FileInputStream(fileName)) {
            while (fis.available() != 0) {
                PaperCollection collection = PaperStatic.deserialize(fis);
                paperCollectionList.add(collection);
            }
        } catch (FileNotFoundException ex) {
            throw new IOException(ex.getMessage());
        }
        return paperCollectionList;
    }
}
