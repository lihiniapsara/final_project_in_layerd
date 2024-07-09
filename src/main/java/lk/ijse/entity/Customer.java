/*
package lk.ijse.entity;

import com.jfoenix.controls.JFXComboBox;

public class Customer {
    private String customerId ;
    private String name ;
    private String address; ;
    private String contact ;

    public Customer(JFXComboBox<String> cmbCustomerId) {
    }

    public Customer(String customerId, String name, String address,String contact) {
        this.customerId = customerId;
        this.name = name;
        this.address = address;
        this.contact = contact;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "customerId='" + customerId + '\'' +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", contact='" + contact + '\'' +
                '}';
    }
}
*/


package lk.ijse.entity;

public class Customer {
    private String customerId;
    private String name;
    private String address;
    private String contact;

    public Customer() {
    }

    public Customer(String customerId, String name, String address, String contact) {
        this.customerId = customerId;
        this.name = name;
        this.address = address;
        this.contact = contact;
    }

    public Customer(String name, String address, String contact) {

    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }
}
