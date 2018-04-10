
package com.arupkumar.tasktwo.Model;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

public class User {

    @SerializedName("id")
    @Expose
    private long id;
    @SerializedName("firstName")
    @Expose
    private String firstName;
    @SerializedName("lastName")
    @Expose
    private String lastName;
    @SerializedName("phones")
    @Expose
    private Phones phones;
    @SerializedName("email")
    @Expose
    private List<String> email = null;
    @SerializedName("dateOfBirth")
    @Expose
    private String dateOfBirth;
    @SerializedName("registered")
    @Expose
    private boolean registered;
    @SerializedName("gender")
    @Expose
    private String gender;
    @SerializedName("photo")
    @Expose
    private long photo;

    /**
     * No args constructor for use in serialization
     * 
     */
    public User() {
    }

    /**
     * 
     * @param dateOfBirth
     * @param id
     * @param lastName
     * @param email
     * @param registered
     * @param gender
     * @param firstName
     * @param photo
     * @param phones
     */
    public User(long id, String firstName, String lastName, Phones phones, List<String> email, String dateOfBirth, boolean registered, String gender, long photo) {
        super();
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phones = phones;
        this.email = email;
        this.dateOfBirth = dateOfBirth;
        this.registered = registered;
        this.gender = gender;
        this.photo = photo;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public User withId(long id) {
        this.id = id;
        return this;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public User withFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public User withLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public Phones getPhones() {
        return phones;
    }

    public void setPhones(Phones phones) {
        this.phones = phones;
    }

    public User withPhones(Phones phones) {
        this.phones = phones;
        return this;
    }

    public List<String> getEmail() {
        return email;
    }

    public void setEmail(List<String> email) {
        this.email = email;
    }

    public User withEmail(List<String> email) {
        this.email = email;
        return this;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public User withDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
        return this;
    }

    public boolean isRegistered() {
        return registered;
    }

    public void setRegistered(boolean registered) {
        this.registered = registered;
    }

    public User withRegistered(boolean registered) {
        this.registered = registered;
        return this;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public User withGender(String gender) {
        this.gender = gender;
        return this;
    }

    public long getPhoto() {
        return photo;
    }

    public void setPhoto(long photo) {
        this.photo = photo;
    }

    public User withPhoto(long photo) {
        this.photo = photo;
        return this;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("id", id).append("firstName", firstName).append("lastName", lastName).append("phones", phones).append("email", email).append("dateOfBirth", dateOfBirth).append("registered", registered).append("gender", gender).append("photo", photo).toString();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(dateOfBirth).append(id).append(lastName).append(email).append(registered).append(gender).append(firstName).append(photo).append(phones).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof User) == false) {
            return false;
        }
        User rhs = ((User) other);
        return new EqualsBuilder().append(dateOfBirth, rhs.dateOfBirth).append(id, rhs.id).append(lastName, rhs.lastName).append(email, rhs.email).append(registered, rhs.registered).append(gender, rhs.gender).append(firstName, rhs.firstName).append(photo, rhs.photo).append(phones, rhs.phones).isEquals();
    }

}
