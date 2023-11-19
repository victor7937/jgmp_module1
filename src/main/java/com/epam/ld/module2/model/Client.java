package com.epam.ld.module2.model;

import java.util.List;
import java.util.Map;

/**
 * The type Client.
 */
public class Client {
    private String addresses;

    private Map<String, String> tags;

    public Client() {}

    public Client(String addresses, Map<String, String> tags) {
        this.addresses = addresses;
        this.tags = tags;
    }

    /**
     * Gets addresses.
     *
     * @return the addresses
     */
    public String getAddresses() {
        return addresses;
    }

    /**
     * Sets addresses.
     *
     * @param addresses the addresses
     */
    public void setAddresses(String addresses) {
        this.addresses = addresses;
    }

    public Map<String, String> getTags() {
        return tags;
    }

    public void setTags(Map<String, String> tags) {
        this.tags = tags;
    }
}
