package com.example.lr_7.interfaces;

import com.example.lr_7.exception.CollectionException;

import java.io.IOException;
import java.io.OutputStream;
import java.io.Serializable;
import java.io.Writer;

public interface PaperCollection extends Serializable {
    String getName();

    void setName(String name);

    int getAnnotation();

    void setAnnotation(int annotation) throws CollectionException;

    int[] getNumberOfPagesArray();

    void setNumberOfPagesArray(int value, int index);

    int sumOfElements();

    int getLength();

    int getItem(int index);

    int calculateTotalNumberOfPagesWithoutAnnotation() throws CollectionException;

    //***********
    //реализовать добавленные в интерфейс методы в обоих классах
    void output(OutputStream out) throws IOException;                 //запись в байтовый поток

    void write(Writer out) throws IOException;                        //запись в символьный поток


}
