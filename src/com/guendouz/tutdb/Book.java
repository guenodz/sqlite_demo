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

public class Book {
    private String BookId;
    private String Title;
    private String SubTitle;
    private int Pages;
    private Date Published;
    private String Description;

    public Book(String bookId,
                String title,
                String subTitle,
                int pages,
                Date published,
                String description) {

        BookId = bookId;
        Title = title;
        SubTitle = subTitle;
        Pages = pages;
        Published = published;
        Description = description;
    }

    public String getBookId() {
        return BookId;
    }

    public void setBookId(String bookId) {
        BookId = bookId;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getSubTitle() {
        return SubTitle;
    }

    public void setSubTitle(String subTitle) {
        SubTitle = subTitle;
    }

    public int getPages() {
        return Pages;
    }

    public void setPages(int pages) {
        Pages = pages;
    }

    public Date getPublished() {
        return Published;
    }

    public void setPublished(Date published) {
        Published = published;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    @Override
    public String toString() {
        return String.format("%s [%s]\n%s",
                Title,
                Published,
                Description);
    }
}