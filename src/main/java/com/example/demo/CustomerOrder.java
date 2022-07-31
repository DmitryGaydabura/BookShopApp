package com.example.demo;

public class CustomerOrder {

    private int id;
    private String name;
    private String book;
    private String date;
    private String address;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBook() {
        return book;
    }

    public void setBook(String book) {
        this.book = book;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "CustomerOrder{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", book='" + book + '\'' +
                ", date='" + date + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}
