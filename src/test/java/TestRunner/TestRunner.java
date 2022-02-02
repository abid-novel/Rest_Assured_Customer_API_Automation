package TestRunner;

import Pages.Customer;
import org.apache.commons.configuration.ConfigurationException;
import org.testng.annotations.Test;

import java.io.IOException;

public class TestRunner {
    Customer customer;

    @org.testng.annotations.Test(priority = 0)
    public void doLogin() throws ConfigurationException, IOException {
        customer = new Customer();
        customer.callingLoginApi();
    }

    @org.testng.annotations.Test(priority = 1)
    public void getCustomerList() throws IOException {
        customer = new Customer();
        customer.callingCustomerListApi();
    }

    @org.testng.annotations.Test(priority = 2)
    public void searchCustomer() throws IOException {
        customer = new Customer();
        customer.callingSearchCustomer();
    }

    @org.testng.annotations.Test(priority = 3)
    public void generateRandomCustomer() throws IOException, ConfigurationException {
        customer = new Customer();
        customer.generateCustomer();
    }

    @org.testng.annotations.Test(priority = 4)
    public void createCustomer() throws IOException {
        customer = new Customer();
        customer.callingCustomerCreateAPi();
    }

    @org.testng.annotations.Test(priority = 5)
    public void updateCustomer() throws IOException, ConfigurationException {
        customer = new Customer();
        customer.callingUpdateCustomerApi();
    }

    @Test(priority = 6)
    public void deleteCustomer() throws IOException {
        customer = new Customer();
        customer.callingDeleteCustomerApi();
    }


}
