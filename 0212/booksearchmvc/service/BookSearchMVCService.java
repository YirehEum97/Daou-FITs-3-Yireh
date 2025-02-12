package booksearchmvc.service;

import booksearchmvc.dao.BookDAO;
import booksearchmvc.dao.ConnectionMaker;
import booksearchmvc.dao.KConnectionMaker;
import booksearchmvc.vo.BookVO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class BookSearchMVCService {

    public static ConnectionMaker connectionMaker = null;

    public BookSearchMVCService(){}

//    public BookSearchMVCService(ConnectionMaker connectionMaker){
//        this.connectionMaker = connectionMaker;
//    }

    public ObservableList<BookVO> searchBookByKeyword(String text){
        if (connectionMaker == null) {
            connectionMaker = new KConnectionMaker();
        }
        BookDAO bookDAO = new BookDAO(connectionMaker);
        return bookDAO.select(text);
    }

    public int removeBook(BookVO bookVO){
        if (connectionMaker == null) {
            connectionMaker = new KConnectionMaker();
        }
        BookDAO bookDAO = new BookDAO(connectionMaker);
        int del_rows = bookDAO.delete(bookVO);

        return del_rows;
    }
}
