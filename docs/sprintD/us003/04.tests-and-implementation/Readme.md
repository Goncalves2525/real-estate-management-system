# US 003 - As a system administrator, I want to register a new employee

# 4. Tests 

## 4.1 EmployeeTest
**Test 1:** Check that it is not possible to add a role that already exists. 

	@Test
    void addRole() {
        employee.addRole(Role.STORE_NETWORK_MANAGER);

        assertTrue(employee.hasRole(Role.STORE_NETWORK_MANAGER));
    }

## 4.2 EmployeeRepositoryTest
**Test 2:** Check if it is possible to get an employee by his email.

	@Test
    //need to have an employee in the repository
    void getEmployeeByEmail() {
        employeeRepository.addEmployee(employee1);
        employeeRepository.addEmployee(employee2);
        assertEquals(employee1, employeeRepository.getEmployeeByEmail("john.doe@example.com"));
        assertNull(employeeRepository.getEmployeeByEmail("nonexistent@example.com"));
    }

**Test 3:** Check if it is possible to get an employee by his id.
    
    @Test
    //need to have an employee in the repository
    void getAgentsByAgencyId() {
        employeeRepository.addEmployee(employee1);
        employeeRepository.addEmployee(employee2);
        ArrayList<Employee> agents = employeeRepository.getAgentsByAgencyId(1);
        assertEquals(2, agents.size());
        assertTrue(agents.contains(employee1));
        assertTrue(agents.contains(employee2));
    }

**Test 4:** Check if the employee exists in the repository.

    @Test
    //need to have an employee in the repository
    void employeeExists() {
        assertFalse(employeeRepository.employeeExists("john.doe@example.com"));
        employeeRepository.addEmployee(employee1);
        assertTrue(employeeRepository.employeeExists("john.doe@example.com"));
    }

**Test 5:** Check if it is possible to get the employee list.

    @Test
    void getEmployeeList() {
        employeeRepository.addEmployee(employee1);
        employeeRepository.addEmployee(employee2);
        ArrayList<Employee> employees = employeeRepository.getEmployeeList();
        assertEquals(2, employees.size());
        assertTrue(employees.contains(employee1));
        assertTrue(employees.contains(employee2));
    }

**Test 6:** Check if it is possible to add an employee to the repository.

    @Test
    void add() {
    employeeRepository.add(employee1);
    assertTrue(employeeRepository.getEmployeeList().contains(employee1));
    }

## 4.3 RegisterEmployeeControllerTest

**Test 7:** Check if it is possible to get the role by his value.

    @Test
    void getRoleByVale() {
    Role role = Role.ADMIN;
    Role result = controller.getRoleByVale(role.getValue());
    assertEquals(role, result);
   

**Test 8:** Check if it is possible to register an employee.

    @Test
    void registerEmployee() {
        String name = "John Doe";
        String email = "john.doe@example.com";
        int passportCardNumber = 123456;
        int taxNumber = 654321;
        int telephoneNumber = 987654;
        Address address = new Address("happy","new","dist","big",4525);
        ArrayList<Employee> agenteTest = new ArrayList<>();
        Agency agency = new Agency(3, "agency1", "email@mail.com", 1234567890,address, agenteTest);
        List<Role> roles = Arrays.asList(Role.ADMIN, Role.AGENT);

        Employee employee = new Employee(name, email, passportCardNumber, taxNumber, telephoneNumber, address, agency, roles);

        boolean result = controller.registerEmployee(name, email, passportCardNumber, taxNumber, telephoneNumber, address, agency, roles);

        assertTrue(result);
    }


**Test 9:** Check if it is possible to generate a password.

    @Test
    void generatePassword() {
        String generatedPassword = controller.generatePassword();
        assertNotNull(generatedPassword);
        assertTrue(generatedPassword.length() == 7);

    }



# 5. Construction (Implementation)


## Class RegisterEmployeeController 

```java
public boolean registerEmployee(String name, String email, int passportCardNumber, int taxNumber, int telephoneNumber, Address address, Agency agency, List<Role> roles) {
        Employee employee = new Employee(name, email, passportCardNumber, taxNumber, telephoneNumber, address, agency, roles);
        return employeeRepository.addEmployee(employee);
        }

```


# 6. Integration and Demo 

* Some agencies are bootstrapped while system starts.


# 7. Observations






