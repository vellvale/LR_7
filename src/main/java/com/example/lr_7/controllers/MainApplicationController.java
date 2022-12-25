package com.example.lr_7.controllers;

import com.example.lr_7.exception.CollectionException;
import com.example.lr_7.interfaces.PaperCollection;
import com.example.lr_7.utils.common.PaperCollectionOperations;
import com.example.lr_7.utils.io.ByteOperations;
import com.example.lr_7.utils.io.Serialize;
import com.example.lr_7.utils.io.SymbolOperations;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;


public class MainApplicationController implements Initializable {
    List<PaperCollection> paperCollectionList = new ArrayList<>();
    private static final String SERIALIZED_FILE_NAME = "serialized.bin";
    private static final String BYTE_FILE_NAME = "collections.bin";
    private static final String SYMBOL_FILE_NAME = "collections.txt";
    @FXML
    private RadioButton load1;
    @FXML
    private RadioButton load2;
    @FXML
    private RadioButton load3;
    @FXML
    private RadioButton load4;
    @FXML
    private ListView listView;
    @FXML
    public RadioButton theme1;
    @FXML
    public RadioButton theme2;
    @FXML
    private AnchorPane anchorPane;


    public MainApplicationController() {
    }

    @FXML
    protected void onStartButtonClick() {
        if (load1.isSelected()) {
            try {
                List<PaperCollection> byteInput = ByteOperations.byteInputPaperCollection(BYTE_FILE_NAME);
                ObservableList<PaperCollection> items = FXCollections.observableArrayList(byteInput);
                listView.setItems(items);
            } catch (IOException | CollectionException ex) {
                System.out.println(ex.getMessage());
            }
        }
        if (load2.isSelected()) {
            try {
                List<PaperCollection> symbolRead =
                        SymbolOperations.readPaperCollection(SYMBOL_FILE_NAME);
                ObservableList<PaperCollection> items = FXCollections.observableArrayList(symbolRead);
                listView.setItems(items);
            } catch (IOException | CollectionException ex) {
                System.out.println(ex.getMessage());
            }
        }
        if (load3.isSelected()) {
            try {
                List<PaperCollection> deserialized =
                        Serialize.deserializePaperCollection(SERIALIZED_FILE_NAME);
                ObservableList<PaperCollection> items = FXCollections.observableArrayList(deserialized);
                listView.setItems(items);
            } catch (IOException | ClassNotFoundException ex) {
                System.out.println(ex.getMessage());
            }
        }
        if (load4.isSelected()) {
            paperCollectionList.add(PaperCollectionOperations.createArticle("Академический журнал", 3, new int[]{9, 16, 24, 32}));
            paperCollectionList.add(PaperCollectionOperations.createStory("Список лучших рассказов 1913 года", 2, new int[]{32, 65, 34, 12, 57, 66}));
            paperCollectionList.add(PaperCollectionOperations.createArticle("Книга больших новостных фотографий", 2, new int[]{3, 6, 8, 4}));
            paperCollectionList.add(PaperCollectionOperations.createStory("Сказки для малышей", 2, new int[]{21, 16, 6, 9}));
            ObservableList<PaperCollection> items = FXCollections.observableArrayList(paperCollectionList);
            listView.setItems(items);
        }
    }

    @FXML
    protected void onClearButtonClick() {
        listView.getItems().clear();
    }

    @FXML
    protected void handle() {
        System.out.println("clicked on " + listView.getSelectionModel().getSelectedItem());
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Описание объекта массива");
        alert.setHeaderText(null);
        alert.setContentText(listView.getSelectionModel().getSelectedItem().toString() + "\nНомер объекта в базе: " + listView.getSelectionModel().getSelectedIndex());
        alert.showAndWait();
    }

    @FXML
    protected void onClickTheme1() {
        System.out.println("Css1 selected");
        anchorPane.getStylesheets().clear();
        anchorPane.getStylesheets().add(getClass().getResource("/css/theme1.css").toExternalForm());

    }

    @FXML
    protected void onClickTheme2() {
        System.out.println("Css2 selected");
        anchorPane.getStylesheets().clear();
        anchorPane.getStylesheets().add(getClass().getResource("/css/theme2.css").toExternalForm());

    }

    // Метод initialize() контроллера вызывается во время вызова FXMLLoader.load(...)
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}