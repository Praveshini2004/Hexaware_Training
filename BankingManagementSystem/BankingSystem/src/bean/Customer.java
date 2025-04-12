package bean;

public class Customer {
    private int customerId;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private String address;

    public Customer() {}

    public Customer(int customerId, String firstName, String lastName, String email, String phoneNumber, String address) {
        this.customerId = customerId;
        this.firstName = firstName;
        this.lastName = lastName;
        setEmail(email); // validate
        setPhoneNumber(phoneNumber); // validate
        this.address = address;
    }

    
    public int getCustomerId() {
        return customerId;
    }
    public String getFirstName() {
        return firstName;
    }
    public String getLastName() {
        return lastName;
    }
    public String getEmail() {
        return email;
    }
    public String getPhoneNumber() {
        return phoneNumber;
    }
    public String getAddress() {
        return address;
    }

    
    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public void setEmail(String email) {
        if (email != null && email.matches("^[\\w.-]+@[\\w.-]+\\.\\w{2,}$")) {
            this.email = email;
        } else {
            throw new IllegalArgumentException("Invalid email format.");
        }
    }
    public void setPhoneNumber(String phoneNumber) {
        if (phoneNumber != null && phoneNumber.matches("^\\d{10}$")) {
            this.phoneNumber = phoneNumber;
        } else {
            throw new IllegalArgumentException("Invalid phone number. Must be 10 digits.");
        }
    }
    public void setAddress(String address) {
        this.address = address;
    }

 
    public void printCustomerInfo() {
        System.out.println("Customer ID   : " + customerId);
        System.out.println("Name          : " + firstName + " " + lastName);
        System.out.println("Email         : " + email);
        System.out.println("Phone Number  : " + phoneNumber);
        System.out.println("Address       : " + address);
    }

 
    @Override
    public String toString() {
        return "Customer [ID=" + customerId + ", Name=" + firstName + " " + lastName +
               ", Email=" + email + ", Phone=" + phoneNumber + ", Address=" + address + "]";
    }
}
