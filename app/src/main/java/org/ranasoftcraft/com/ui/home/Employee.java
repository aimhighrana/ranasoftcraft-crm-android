package org.ranasoftcraft.com.ui.home;

import android.arch.lifecycle.ViewModel;
import android.support.v4.app.ListFragment;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

/**
 * @author sandeep.rana
 */
public class Employee implements Serializable   {

    private String username;

    private long phone;

    private String email;

    private String address;

    private Set<String> roles;

    public String getUsername() {
        return username;
    }

    public Employee setUsername(String username) {
        this.username = username;
        return this;
    }

    public long getPhone() {
        return phone;
    }

    public Employee setPhone(long phone) {
        this.phone = phone;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public Employee setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getAddress() {
        return address;
    }

    public Employee setAddress(String address) {
        this.address = address;
        return this;
    }

    public Set<String> getRoles() {
        return roles;
    }

    public void setRoles(Set<String> roles) {
        this.roles = roles;
    }

    public List<Employee> _defaultEmployee() {
        return Arrays.asList(
                new Employee().setAddress("Sec 1")
                        .setEmail("test@gmail.com")
                        .setPhone(762876877871l)
                        .setUsername("srana"),
                new Employee().setAddress("Sec 2")
                        .setEmail("test2@gmail.com")
                        .setPhone(762879877871l)
                        .setUsername("admin")
        );
    }
}
