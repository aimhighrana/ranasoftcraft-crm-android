package org.ranasoftcraft.com.services;

/**
 * @author sandeep.rana
 */
public class HttpUrls {

    static String prefix = "https://ranasoftcraft-crm-services.herokuapp.com";

    public String login() {
        return prefix + "/signin";
    }
}
