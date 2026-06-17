interface CustomerRepository { String getCustomerName(); }
class CustomerRepositoryImpl implements CustomerRepository { public String getCustomerName() { return "John Doe"; } }

class CustomerService {
    private CustomerRepository repo;
    public CustomerService(CustomerRepository r) { this.repo = r; }
    public String fetchCustomer() { return repo.getCustomerName(); }
}
