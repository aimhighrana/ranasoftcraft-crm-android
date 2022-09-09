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

    private Set<Roles> roles;

    private String password;

    private String name;

    public String getName() {
        return name;
    }

    public Employee setName(String name) {
        this.name = name;
        return this;
    }

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

    public String getPassword() {
        return password;
    }

    public Employee setPassword(String password) {
        this.password = password;
        return this;
    }

    public Set<Roles> getRoles() {
        return roles;
    }

    public void setRoles(Set<Roles> roles) {
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
