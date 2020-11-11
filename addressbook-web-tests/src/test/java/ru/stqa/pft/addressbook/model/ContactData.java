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

    private String middlename;
    @Expose
    @Column (name = "lastname")
    private String lastname;
    private String nick;
    private String title;
    private String company;
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
    private String fax;
    @Expose
    @Type(type = "text")
    private String email;

    @Type(type = "text")
    private String email2;

    @Type(type = "text")
    private String email3;
    private String homepage;
    private String birthday_day;
    private String birthday_month;
    private String birthday_year;
    private String anniversary_day;
    private String anniversary_month;
    private String anniversary_year;
    private String address2;
    private String phone2;
    private String notes;
    private String allPhones;
    private String group;
    private File photo;

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

    public String getMiddlename() {
        return middlename;
    }

    public String getNick() {
        return nick;
    }

    public String getTitle() {
        return title;
    }

    public String getCompany() {
        return company;
    }

    public String getFax() {
        return fax;
    }

    public String getNotes() {
        return notes;
    }

    public String getPhone2() {
        return phone2;
    }

    public String getHomepage() {
        return homepage;
    }

    public String getBirthday_day() {
        return birthday_day;
    }

    public String getBirthday_month() {
        return birthday_month;
    }

    public String getBirthday_year() {
        return birthday_year;
    }

    public String getAnniversary_day() {
        return anniversary_day;
    }

    public String getAnniversary_month() {
        return anniversary_month;
    }

    public String getAnniversary_year() {
        return anniversary_year;
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

    public ContactData withMiddlename(String middlename) {
        this.middlename = middlename;
        return this;
    }

    public ContactData withNick(String nick) {
        this.nick = nick;
        return this;
    }

    public ContactData withTitle(String title) {
        this.title = title;
        return this;
    }

    public ContactData withCompany(String company) {
        this.company = company;
        return this;
    }

    public ContactData withFax(String fax) {
        this.fax = fax;
        return this;
    }

    public ContactData withNotes(String notes) {
        this.notes = notes;
        return this;
    }

    public ContactData withPhone2(String phone2) {
        this.phone2 = phone2;
        return this;
    }

    public ContactData withHomepage(String homepage) {
        this.homepage = homepage;
        return this;
    }

    public ContactData withBirthday_day(String birthday_day) {
        this.birthday_day = birthday_day;
        return this;
    }

    public ContactData withBirthday_month(String birthday_month) {
        this.birthday_month = birthday_month;
        return this;
    }

    public ContactData withBirthday_year(String birthday_year) {
        this.birthday_year = birthday_year;
        return this;
    }

    public ContactData withAnniversary_day(String anniversary_day) {
        this.anniversary_day = anniversary_day;
        return this;
    }

    public ContactData withAnniversary_month(String anniversary_month) {
        this.anniversary_month = anniversary_month;
        return this;
    }

    public ContactData withAnniversary_year(String anniversary_year) {
        this.anniversary_year = anniversary_year;
        return this;
    }
}
