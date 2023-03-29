# US 003 - As a system administrator, I want to register a new employee

## 1. Requirements Engineering


### 1.1. User Story Description


As a system administrator, I want to register a new employee.


### 1.2. Customer Specifications and Clarifications 


**From the specifications document:**

>	The company's systems administrator will be responsible for registering al employees (specifying the name, the citizen's card number, the tax number, the address, the email address, the contact telephone number and the agency to which it is assigned)

>	The company's systems administrator will be responsible for registering branches of the network (specifying the designation, location and local manager)

>	The company's systems administrator will be preparing the system in order to facilitate the insertion of advertisements and facilitate the use of the application.


**From the client clarifications:**

> **Question:** Can an Agent work in more than 1 store (Multiple stores)?
>  
> **Answer:** No.


> **Question:** Can a client also be an agent or owner ? Can the store manager , be also an angent?
>  
> **Answer:** A person can have multiple roles.


> **Question:** The administrator when registering a new employee will also have to specify the category/office that he will perfom (for example agent, store manager, store network manager)?
>
> **Answer:** The administrator has to specify the role of the employee.


> **Question:** Is the store manager also an agent?
>
> **Answer:** No.


> **Question:** The network manager is the system admin?
>
> **Answer:** No.


> **Question:** There is only one manager for each store and only ONE system administrator?
>
> **Answer:** Yes.


> **Question:** When a System Administrator (admin) makes a request to register a new employee or a new network branch (or any other alteration), does the System ask for the admin credentials (login, password)?
>
> **Answer:** The System Administrator should be logged in the application.


> **Question:** Can there be more than one admin?
>
> **Answer:** No.


> **Question:** To register an employee I need to allocate him with a branch. To register a branch I need an employee (to be local manger) but I can't create the employee because I have no branch and I can’t create the branch because I have no employee.
>
> **Answer:** When a store is created in the system, the System Administrator should not set the Store Manager.
When registering a store, the System Administrator should introduce the following information: an ID, a designation/name, a location, a phone number and an e-mail address.


> **Question:** When registering a new employee, will the administrator set the respective employee password?
>
> **Answer:** The password should have eight characters in length and should be generated automatically. The password is sent to the employee by e-mail.





 

### 1.3. Acceptance Criteria


* **AC1:** All required fields must be filled in.
* **AC2:** The employee password should have eight characters in length.


### 1.4. Found out Dependencies


* There is a dependency to US5 because the system administrator creates the agencies too.


### 1.5 Input and Output Data


**Input Data:**

* Typed data:
	* the name
	* the citizen's card number 
	* the tax number
	* the address
	* the email address
	* the contact telephone number
  
	
* Selected data:
	* the agency to which it is assigned


**Output Data:**

* (In)Success of the operation

### 1.6. System Sequence Diagram (SSD)


#### System Sequence Diagram (SSD)

![System Sequence Diagram - Alternative Two](svg/us003-system-sequence-diagram.svg)

### 1.7 Other Relevant Remarks

* .