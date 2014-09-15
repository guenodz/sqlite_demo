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

import java.io.FileNotFoundException;
import java.sql.Date;
import java.sql.SQLException;

public class Main {

    public static void main(String[] args) {
        try {

            // Try to connect to DB
            Connexion connexion = new Connexion("Data/Database.db");

            // What if the path was wrong or I can't read on the DB?
//            Connexion connexion = new Connexion("Data/Database.d");


            connexion.connect();

            Book book = new Book("9aa883e6-dfc0-4149-902a-4dbdfa22a408",
                    "Système D'Information", "", 450, Date.valueOf("2012-11-01"),
                    "");

            System.out.println(book.getPublished());

            connexion.addBook(book);

            connexion.getAllBooks()
//                    .forEach(b -> System.out.println(b.getTitle()));
                    .forEach(System.out::println);

            connexion.close();


        } catch (SQLException | ClassNotFoundException | FileNotFoundException e) {
            e.printStackTrace();
        }

        System.out.println("\nExecution DONE!");
    }

}
