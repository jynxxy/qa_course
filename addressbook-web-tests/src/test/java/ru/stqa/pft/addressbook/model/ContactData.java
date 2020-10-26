package ru.stqa.pft.addressbook.model;

public class ContactData {
    private int id = Integer.MAX_VALUE;
    private String contactname;
    private String contactsurname;
    private String phone;
    private String email;
    private String group;

    @Override
    public String toString() {
        return "ContactData{" +
                "id='" + id + '\'' +
                ", contactname='" + contactname + '\'' +
                ", contactsurname='" + contactsurname + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ContactData that = (ContactData) o;

        if (contactname != null ? !contactname.equals(that.contactname) : that.contactname != null) return false;
        return contactsurname != null ? contactsurname.equals(that.contactsurname) : that.contactsurname == null;
    }

    @Override
    public int hashCode() {
        int result = contactname != null ? contactname.hashCode() : 0;
        result = 31 * result + (contactsurname != null ? contactsurname.hashCode() : 0);
        return result;
    }

    public int getId() {
        return id;
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

    public String getGroup() {
        return group;
    }

    public ContactData withId(int id) {
        this.id = id;
        return this;
    }

    public ContactData withContactname(String contactname) {
        this.contactname = contactname;
        return this;
    }

    public ContactData withContactsurname(String contactsurname) {
        this.contactsurname = contactsurname;
        return this;
    }

    public ContactData withPhone(String phone) {
        this.phone = phone;
        return this;
    }

    public ContactData withEmail(String email) {
        this.email = email;
        return this;
    }

    public ContactData withGroup(String group) {
        this.group = group;
        return this;
    }

}
