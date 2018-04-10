
package com.arupkumar.tasktwo.Model;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

public class UserDetails {

    @SerializedName("product")
    @Expose
    private String product;
    @SerializedName("version")
    @Expose
    private double version;
    @SerializedName("releaseDate")
    @Expose
    private String releaseDate;
    @SerializedName("demo")
    @Expose
    private boolean demo;
    @SerializedName("users")
    @Expose
    private List<User> users = null;

    /**
     * No args constructor for use in serialization
     * 
     */
    public UserDetails() {
    }

    /**
     * 
     * @param demo
     * @param product
     * @param users
     * @param releaseDate
     * @param version
     */
    public UserDetails(String product, double version, String releaseDate, boolean demo, List<User> users) {
        super();
        this.product = product;
        this.version = version;
        this.releaseDate = releaseDate;
        this.demo = demo;
        this.users = users;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public UserDetails withProduct(String product) {
        this.product = product;
        return this;
    }

    public double getVersion() {
        return version;
    }

    public void setVersion(double version) {
        this.version = version;
    }

    public UserDetails withVersion(double version) {
        this.version = version;
        return this;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public UserDetails withReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
        return this;
    }

    public boolean isDemo() {
        return demo;
    }

    public void setDemo(boolean demo) {
        this.demo = demo;
    }

    public UserDetails withDemo(boolean demo) {
        this.demo = demo;
        return this;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public UserDetails withUsers(List<User> users) {
        this.users = users;
        return this;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("product", product).append("version", version).append("releaseDate", releaseDate).append("demo", demo).append("users", users).toString();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(demo).append(product).append(users).append(releaseDate).append(version).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof UserDetails) == false) {
            return false;
        }
        UserDetails rhs = ((UserDetails) other);
        return new EqualsBuilder().append(demo, rhs.demo).append(product, rhs.product).append(users, rhs.users).append(releaseDate, rhs.releaseDate).append(version, rhs.version).isEquals();
    }

}
