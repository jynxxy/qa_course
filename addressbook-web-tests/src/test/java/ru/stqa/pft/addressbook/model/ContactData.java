package ru.stqa.pft.addressbook.model;

import com.google.gson.annotations.Expose;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamOmitField;
import org.checkerframework.checker.units.qual.C;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.io.File;

@Entity
@Table (name = "addressbook")
@XStreamAlias("contact")

public class ContactData {

    @XStreamOmitField
    @Id
    @Column (name = "id")
    private int id = Integer.MAX_VALUE;

    @Expose
    @Column (name = "firstname")
    private String firstname;

    @Expose
    @Column (name = "lastname")
    private String lastname;

    @Type(type = "text")
    private String address;

    @Type(type = "text")
    private String address2;

    @Expose
    @Column (name =  "home")
    @Type(type = "text")
    private String home;

    @Column (name = "mobile")
    @Type(type = "text")
    private String mobile;

    @Column (name = "work")
    @Type(type = "text")
    private String work;

    @Expose
    @Type(type = "text")
    private String email;

    @Type(type = "text")
    private String email2;

    @Type(type = "text")
    private String email3;

    @Expose
    @Transient
    private String group;

    @Transient
    private String allPhones;

    @Column (name = "photo")
    @Type(type = "text")
    private String photo;

    @Override
    public String toString() {
        return "ContactData{" +
                "id=" + id +
                ", firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ContactData that = (ContactData) o;

        if (id != that.id) return false;
        if (firstname != null ? !firstname.equals(that.firstname) : that.firstname != null) return false;
        return lastname != null ? lastname.equals(that.lastname) : that.lastname == null;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (firstname != null ? firstname.hashCode() : 0);
        result = 31 * result + (lastname != null ? lastname.hashCode() : 0);
        return result;
    }

    public int getId() {
        return id;
    }

    public String getFirstName() {
        return firstname;
    }

    public String getLastName() {
        return lastname;
    }

    public String getAddress() {
        return address;
    }

    public String getAddress2() {
        return address2;
    }

    public String getHomePhone() {
        return home;
    }

    public String getMobilePhone() {
        return mobile;
    }

    public String getWorkPhone() {
        return work;
    }

    public String getEmail() {
        return email;
    }

    public String getEmail2() {
        return email2;
    }

    public String getEmail3() {
        return email3;
    }

    public String getGroup() {
        return group;
    }

    public String getAllPhones() {
        return allPhones;
    }

    public File getPhoto() {
        return new File(photo);
    }

    public ContactData withAllPhones(String allPhones) {
        this.allPhones = allPhones;
        return this;
    }

    public ContactData withId(int id) {
        this.id = id;
        return this;
    }

    public ContactData withFirstName(String contactname) {
        this.firstname = contactname;
        return this;
    }

    public ContactData withLastName(String contactsurname) {
        this.lastname = contactsurname;
        return this;
    }

    public ContactData withAddress(String address) {
        this.address = address;
        return this;
    }

    public ContactData withAddress2(String address2) {
        this.address2 = address2;
        return this;
    }

    public ContactData withHomePhone(String phone) {
        this.home = phone;
        return this;
    }

    public ContactData withMobilePhone(String mobile) {
        this.mobile = mobile;
        return this;
    }

    public ContactData withWorkPhone(String work) {
        this.work = work;
        return this;
    }

    public ContactData withEmail(String email) {
        this.email = email;
        return this;
    }

    public ContactData withEmail2(String email2) {
        this.email2 = email2;
        return this;
    }

    public ContactData withEmail3(String email3) {
        this.email3 = email3;
        return this;
    }

    public ContactData withGroup(String group) {
        this.group = group;
        return this;
    }

    public ContactData withPhoto(File photo) {
        this.photo = photo.getPath();
        return this;
    }
}
