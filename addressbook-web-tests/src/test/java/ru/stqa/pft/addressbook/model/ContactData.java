package ru.stqa.pft.addressbook.model;

public class ContactData {
    private int id;
    private final String contactname;
    private final String contactsurname;
    private final String phone;
    private final String email;
    private String group;

    public ContactData(String contactname, String contactsurname, String phone, String email, String group) {
        this.contactname = contactname;
        this.contactsurname = contactsurname;
        this.phone = phone;
        this.email = email;
        this.group = group;
    }

//    public ContactData(int id, String contactname, String contactsurname, String phone, String email, String group) {
//        this.id = id;
//        this.contactname = contactname;
//        this.contactsurname. contactsurname;
//        this.phone = phone;
//        this.email = email;
//        this.group = group;
//    }

    public String getContactName() {
        return contactname;
    }

    public String getContactSurname() {
        return contactsurname;
    }

    public String getPhone() {
        return phone;
    }

    public String getEmail() {
        return email;
    }

    public String getGroup() {
        return group;
    }
}
