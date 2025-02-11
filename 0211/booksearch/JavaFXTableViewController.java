package booksearch;

import java.net.URL;
import java.util.ResourceBundle;

import booksearch.query.Query;
import booksearch.vo.Book;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;

public class JavaFXTableViewController implements Initializable {

    @FXML private TableView<Book> tbl;
    @FXML private Button btn1;
    @FXML private TextField searchTab;
    @FXML private TableColumn<Book, String> isbnCol;
    @FXML private TableColumn<Book, String> titleCol;
    @FXML private TableColumn<Book, String> authorCol;
    @FXML private TableColumn<Book, Integer> priceCol;

    private ObservableList<Book> books;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        isbnCol.setCellValueFactory(new PropertyValueFactory<>("bisbn"));
        titleCol.setCellValueFactory(new PropertyValueFactory<>("btitle"));
        authorCol.setCellValueFactory(new PropertyValueFactory<>("bauthor"));
        priceCol.setCellValueFactory(new PropertyValueFactory<>("bprice"));


        btn1.setOnAction(e-> {
            books = FXCollections.observableArrayList();
            String keyword = searchTab.getText();
            try {
                books.addAll(Query.query(keyword));
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }

            tbl.setItems(books);
        });

//        addContext
    }

    private void addContextMenuToTable(){
        tbl.setRowFactory(tv->{
            TableRow<Book> row = new TableRow<>();
            ContextMenu contextMenu = new ContextMenu();
            MenuItem deleteItem = new MenuItem("삭제");

            deleteItem.setOnAction(e->{
                Book selectedBook = row.getItem();
                if (selectedBook != null){
                    books.remove(selectedBook);
                }
            });

            contextMenu.getItems().add(deleteItem);

            row.setOnMouseClicked(e->{
                if (e.getButton() == MouseButton.SECONDARY && !row.isEmpty()){
                    contextMenu.show(row, e.getSceneX(), e.getSceneY());
                }
            });

            return row;
        });
    }
}
