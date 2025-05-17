package dao;

import entity.Book;

import java.util.List;

public interface BookDaoImpl {
    public List<Book> listRatedBooks(String author, int rating);
}
