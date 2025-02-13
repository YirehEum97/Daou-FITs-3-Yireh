package example.controller;

import example.dao.BookDAO;
import example.mybatis.MyBatisSessionFactory;
import example.vo.BookVO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import org.apache.ibatis.session.SqlSessionFactory;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class BookSearchMyBatisController implements Initializable {

    @FXML private TextField searchTextBox;
    @FXML private TextField isbnTextBox;
    @FXML private TextField titleTextBox;
    @FXML private TextField authorTextBox;
    @FXML private TextField priceTextBox;
    @FXML private TextField totalPriceTextBox;

    @FXML private Button searchBtn;
    @FXML private Button createBtn;
    @FXML private Button deleteBtn;
    @FXML private Button addBtn;

    @FXML private TableView<BookVO> tableView;
    @FXML private TableView<BookVO> cartTableView;

    @FXML private TableColumn<BookVO, String> isbnCol;
    @FXML private TableColumn<BookVO, String> titleCol;
    @FXML private TableColumn<BookVO, String> authorCol;
    @FXML private TableColumn<BookVO, Integer> priceCol;
    @FXML private TableColumn<BookVO, Integer> cartPriceCol;
    @FXML private TableColumn<BookVO, Integer> cartTitleCol;

    static ObservableList<BookVO> cartList = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        isbnCol.setCellValueFactory(new PropertyValueFactory<>("bisbn"));
        titleCol.setCellValueFactory(new PropertyValueFactory<>("btitle"));
        authorCol.setCellValueFactory(new PropertyValueFactory<>("bauthor"));
        priceCol.setCellValueFactory(new PropertyValueFactory<>("bprice"));

        cartPriceCol.setCellValueFactory(new PropertyValueFactory<>("bprice"));
        cartTitleCol.setCellValueFactory(new PropertyValueFactory<>("btitle"));

        searchBtn.setOnAction(e->{
            SqlSessionFactory factory =
                    MyBatisSessionFactory.getSqlSessionFactory();
            BookDAO dao = new BookDAO(factory);


            ObservableList<BookVO> list = dao.selectByTitleBookVO(searchTextBox.getText());
            tableView.setItems(list);
        });

        createBtn.setOnAction(e->{
            SqlSessionFactory factory =
                    MyBatisSessionFactory.getSqlSessionFactory();
            BookDAO dao = new BookDAO(factory);
            BookVO bookVO = new BookVO(isbnTextBox.getText(),
                    titleTextBox.getText(),
                    authorTextBox.getText(),
                    Integer.parseInt(priceTextBox.getText()));

            dao.insertBookVO(bookVO);
        });

        deleteBtn.setOnAction(e->{
            SqlSessionFactory factory =
                    MyBatisSessionFactory.getSqlSessionFactory();
            BookVO selectedBook = tableView.getSelectionModel().getSelectedItem();
            BookDAO dao = new BookDAO(factory);

            if (selectedBook != null) {
                dao.deleteBookVO(selectedBook);
                tableView.getItems().remove(selectedBook);

            }
        });

        addBtn.setOnAction(e->{
            SqlSessionFactory factory =
                    MyBatisSessionFactory.getSqlSessionFactory();
            BookVO selectedBook = tableView.getSelectionModel().getSelectedItem();
            cartList.add(selectedBook);

            cartTableView.setItems(cartList);
            int totalprice = 0;
            for (BookVO cartItem : cartList){
                totalprice += cartItem.getBprice();
            }
            totalPriceTextBox.setText(String.valueOf(totalprice));
        });
    }
}
