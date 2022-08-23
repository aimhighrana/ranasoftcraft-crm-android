package org.ranasoftcraft.com.services;

/**
 * @author sandeep.rana
 */
public class HttpUrls {

    static String prefix = "http://localhost:1200";

    public String login() {
        return prefix + "/signin";
    }
}
