package ru.stqa.pft.addressbook.model;

public class ContactData {
    private String id;
    private final String contactname;
    private final String contactsurname;
    private final String phone;
    private final String email;
    private String group;



    public ContactData(String contactname, String contactsurname, String phone, String email, String group) {
        this.id = null;
        this.contactname = contactname;
        this.contactsurname = contactsurname;
        this.phone = phone;
        this.email = email;
        this.group = group;
    }

    public ContactData(String id, String contactname, String contactsurname, String phone, String email, String group) {
        this.id = id;
        this.contactname = contactname;
        this.contactsurname = contactsurname;
        this.phone = phone;
        this.email = email;
        this.group = group;
    }

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

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (contactname != null ? !contactname.equals(that.contactname) : that.contactname != null) return false;
        return contactsurname != null ? contactsurname.equals(that.contactsurname) : that.contactsurname == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (contactname != null ? contactname.hashCode() : 0);
        result = 31 * result + (contactsurname != null ? contactsurname.hashCode() : 0);
        return result;
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

    public String getId() {
        return id;
    }
}
