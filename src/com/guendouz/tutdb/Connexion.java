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

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Connexion {
	private String DBPath = "data\\Database.db";
	private Connection connection = null;
	private Statement statement = null;

	public Connexion(String dBPath) {
		DBPath = dBPath;
	}

	public void connect() {
		try {
			Class.forName("org.sqlite.JDBC");
			connection = DriverManager.getConnection("jdbc:sqlite:" + DBPath);
			statement = connection.createStatement();
			System.out.println("Connexion a " + DBPath + " avec succès");
		} catch (ClassNotFoundException notFoundException) {
			notFoundException.printStackTrace();
			System.out.println("Erreur de connecxion");
		} catch (SQLException sqlException) {
			sqlException.printStackTrace();
			System.out.println("Erreur de connecxion");
		}
	}

	public void close() {
		try {
			connection.close();
			statement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public ResultSet query(String requet) {
		ResultSet resultat = null;
		try {
			resultat = statement.executeQuery(requet);
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Erreur dans la requet : " + requet);
		}
		return resultat;

	}

	public ArrayList<Book> getAllBooks() {
		ArrayList<Book> result = new ArrayList<>();
		try {
			PreparedStatement preparedStatement = connection
					.prepareStatement("SELECT * FROM Book");
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				Book book = new Book(resultSet.getString(1),
						resultSet.getString(2), resultSet.getString(3),
						resultSet.getInt(4), resultSet.getDate(5),
						resultSet.getString(6));
				result.add(book);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return result;
	}

	public void addBook(Book book) {
		try {
			PreparedStatement preparedStatement = connection
					.prepareStatement("INSERT INTO Book VALUES(?,?,?,?,?,?)");
			preparedStatement.setString(1, book.getBookId());
			preparedStatement.setString(2, book.getTitle());
			preparedStatement.setString(3, book.getSubTitle());
			preparedStatement.setInt(4, book.getPages());
			preparedStatement.setDate(5, book.getPublished());
			preparedStatement.setString(6, book.getDescription());
			preparedStatement.executeUpdate();
			System.out.println("Insertion Avec Succées");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
