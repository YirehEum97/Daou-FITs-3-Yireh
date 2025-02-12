package booksearchmvc.controller;

import booksearchmvc.service.BookSearchMVCService;
import booksearchmvc.vo.BookVO;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;


import java.net.URL;
import java.util.ResourceBundle;

public class BookSearchMVCController implements Initializable {

    @FXML private TextField searchTab;
    @FXML private Button searchBtn;
    @FXML private Button deleteBtn;
    @FXML private TableView<BookVO> tbl;
    @FXML private TableColumn<BookVO, String> isbnCol;
    @FXML private TableColumn<BookVO, String> titleCol;
    @FXML private TableColumn<BookVO, String> authorCol;
    @FXML private TableColumn<BookVO, Integer> priceCol;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        isbnCol.setCellValueFactory(new PropertyValueFactory<>("bisbn"));
        titleCol.setCellValueFactory(new PropertyValueFactory<>("btitle"));
        authorCol.setCellValueFactory(new PropertyValueFactory<>("bauthor"));
        priceCol.setCellValueFactory(new PropertyValueFactory<>("bprice"));

        searchBtn.setOnAction(e->{
            BookSearchMVCService service = new BookSearchMVCService();
            ObservableList<BookVO> list = service.searchBookByKeyword(searchTab.getText());
            tbl.setItems(list);
        });

        deleteBtn.setOnAction(e->{
            BookVO selectedBook = tbl.getSelectionModel().getSelectedItem();
            if (selectedBook != null) {
                BookSearchMVCService service = new BookSearchMVCService();
                int del_row = service.removeBook(selectedBook);
                if (del_row > 0) {
                    tbl.getItems().remove(selectedBook);
                }
            }
        });

    }
}
