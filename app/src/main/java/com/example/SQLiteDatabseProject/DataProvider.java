package com.example.SQLiteDatabseProject;

class DataProvider {

    private String name;
    private String email;
    private String number;

    DataProvider(String name, String email, String number) {
        setName(name);
        setEmail(email);
        setNumber(number);
    }

    String getName() {
        return name;
    }

    private void setName(String name) {
        this.name = name;
    }

    String getEmail() {
        return email;
    }

    private void setEmail(String email) {
        this.email = email;
    }

    String getNumber() {
        return number;
    }

    private void setNumber(String number) {
        this.number = number;
    }
}
