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

import java.sql.Date;

public class Main {

	public static void main(String[] args) {
		Connexion connexion = new Connexion("data\\Database.db");
		connexion.connect();

		Book book = new Book("9aa883e6-dfc0-4149-902a-4dbdfa22a408",
				"Système D'Information", "", 450, Date.valueOf("2012-11-01"),
				"");
		System.out.println(book.getPublished());
		connexion.addBook(book);
		for (Book b : connexion.getAllBooks())
			System.out.println(b.getTitle());

		connexion.close();
	}

}
