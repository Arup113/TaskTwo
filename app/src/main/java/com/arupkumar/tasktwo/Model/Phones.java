
package com.arupkumar.tasktwo.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

public class Phones {

    @SerializedName("home")
    @Expose
    private String home;
    @SerializedName("mobile")
    @Expose
    private String mobile;

    /**
     * No args constructor for use in serialization
     * 
     */
    public Phones() {
    }

    /**
     * 
     * @param home
     * @param mobile
     */
    public Phones(String home, String mobile) {
        super();
        this.home = home;
        this.mobile = mobile;
    }

    public String getHome() {
        return home;
    }

    public void setHome(String home) {
        this.home = home;
    }

    public Phones withHome(String home) {
        this.home = home;
        return this;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public Phones withMobile(String mobile) {
        this.mobile = mobile;
        return this;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("home", home).append("mobile", mobile).toString();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(home).append(mobile).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof Phones) == false) {
            return false;
        }
        Phones rhs = ((Phones) other);
        return new EqualsBuilder().append(home, rhs.home).append(mobile, rhs.mobile).isEquals();
    }

}
