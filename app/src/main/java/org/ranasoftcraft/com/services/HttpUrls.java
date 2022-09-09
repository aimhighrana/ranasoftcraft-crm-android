package org.ranasoftcraft.com.services;

/**
 * @author sandeep.rana
 */
public class HttpUrls {

    static String prefix = "https://ranasoftcraft-crm-services.herokuapp.com";

    public String login() {
        return prefix + "/signin";
    }

    public String createUpdateEmployee(){
        return prefix + "/admin/user/create";
    }

    public  String getEmployeeList() {
        return prefix + "/admin/employee/_all";
    }
}
