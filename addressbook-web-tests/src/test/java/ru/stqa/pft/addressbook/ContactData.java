package ru.stqa.pft.addressbook;

public class ContactData {
    private final String contactname;
    private final String contactsurname;
    private final String phone;
    private final String email;

    public ContactData(String contactname, String contactsurname, String phone, String email) {
        this.contactname = contactname;
        this.contactsurname = contactsurname;
        this.phone = phone;
        this.email = email;
    }

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
}
