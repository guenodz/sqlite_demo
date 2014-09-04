/*
 * Copyright 2013 Mohamed Guendouz.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.guendouz.tutdb;

import com.sun.istack.internal.NotNull;

import java.io.File;
import java.io.FileNotFoundException;
import java.sql.*;
import java.util.ArrayList;

public class Connexion {
    // Defaults to the path to the sample DB
    private String DBPath = "Data/Database.db";
    private Connection connection = null;
    private Statement statement = null;

    public Connexion(@NotNull String dBPath) throws FileNotFoundException {
        // Check DB existence + read permissions
        if (new File(dBPath).canRead())
            DBPath = dBPath;
        else
            throw new FileNotFoundException("Err! DB Not found or Cannot be read.");
    }

    public void connect() throws SQLException, ClassNotFoundException {
        Class.forName("org.sqlite.JDBC");
        connection = DriverManager.getConnection("jdbc:sqlite:" + DBPath);
        statement = connection.createStatement();
        System.out.println("Successfully connected to " + DBPath);
    }

    public void close() throws SQLException {
        statement.close();
        connection.close();
    }

    public ResultSet query(String request) throws SQLException {
        return statement.executeQuery(request);
    }

    public ArrayList<Book> getAllBooks() throws SQLException {
        ArrayList<Book> result = new ArrayList<>();

        try (
                PreparedStatement preparedStatement = connection
                        .prepareStatement("SELECT * FROM Book");
                ResultSet resultSet = preparedStatement.executeQuery()
        ) {

            while (resultSet.next()) {
                Book book = new Book(resultSet.getString(1),
                        resultSet.getString(2), resultSet.getString(3),
                        resultSet.getInt(4), resultSet.getDate(5),
                        resultSet.getString(6));
                result.add(book);
            }

        }
        return result;
    }

    public void addBook(Book book) throws SQLException {

        try (PreparedStatement preparedStatement = connection
                .prepareStatement("INSERT INTO Book VALUES(?,?,?,?,?,?)")) {

            preparedStatement.setString(1, book.getBookId());
            preparedStatement.setString(2, book.getTitle());
            preparedStatement.setString(3, book.getSubTitle());
            preparedStatement.setInt(4, book.getPages());
            preparedStatement.setDate(5, book.getPublished());
            preparedStatement.setString(6, book.getDescription());

            preparedStatement.executeUpdate();
        }
        System.out.println("Successfully inserted " + book.getTitle());
    }
}