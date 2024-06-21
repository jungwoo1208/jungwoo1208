package Locker;

import java.io.Serializable;

class Account implements Serializable {
    String id;
    String pw;
    String name;
    String phone;
    int cash;
    int locker_number;
    String purpose;

    public Account(String id, String pw, String name, String phone, int cash, int locker_number) {
        this.id = id;
        this.pw = pw;
        this.name = name;
        this.phone = phone;
        this.cash = cash;
        this.locker_number = locker_number;
        this.purpose = "";
    }
    public Account(String id, String pw, String name, String phone, int cash, int locker_number,String purpose) {
        this.id = id;
        this.pw = pw;
        this.name = name;
        this.phone = phone;
        this.cash = cash;
        this.locker_number = locker_number;
        this.purpose = purpose;
    }
    void set_cash(int cash) {
        this.cash = cash;
    }
    void set_locker_number(int locker_number) {
        this.locker_number = locker_number;
    }

    String get_id() {
        return this.id;
    }
    String get_pw() {
        return this.pw;
    }
    String get_name() {
        return this.name;
    }
    String get_purpose() {
        return this.purpose;
    }
    void set_purpose(String purpose) {
        this.purpose = purpose;
    }
    String get_phone() {
        return this.phone;
    }
    int get_cash() {
        return this.cash;
    }
    int get_locker_number() {
        return this.locker_number;
    }
}
