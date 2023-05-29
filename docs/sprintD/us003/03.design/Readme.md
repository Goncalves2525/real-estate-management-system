# US 003 - As a system administrator, I want to register a new employee

## 3. Design - User Story Realization 

### 3.1. Rationale

The following table lists the design decisions made for this person story.

| Interaction ID | Question: Which class is responsible for... | Answer               | Justification (with patterns)                                                                                 |
|:---------------|:--------------------- |:---------------------|:--------------------------------------------------------------------------------------------------------------|
| Step 1         | ... interacting with the actor?             | RegisterEmployeeUI      | Pure Fabrication: there is no reason to assign this responsibility to any existing class in the Domain Model. |
|                | ... coordinating the US?                    | RegisterEmployeeController | Controller                                                                                                    |
|                | ... instantiating a new Employee?           | EmployeeRepository      | Creator (Rule 1): in the DM EmployeeRepository has an Employee.                                               |
|                | ... knowing the person using the system?      | UserSession | IE: cf. A&A component documentation.                                                                          |
|                |                                             | EmployeeRepository      | IE: knows/has its own Employees                                                                               |
|                |                                             | Employee                | IE: knows its own data (e.g. email)                                                                           |
| Step 2         | ...saving the inputted data?               | EmployeeRepository      | IE: repository for Employee objects.                                                                          |
| Step 3         | ...knowing the available roles?            | RoleRepository          | IE: RoleRepository stores the roles.                                                                          |
| Step 4         | ... saving the selected roles?             | Employee                | IE: object created in step 1 has its own roles.                                                               |
| Step 5         | ... validating all data (local validation)?| Employee                | IE: owns its data.                                                                                            |
|                | ... validating all data (global validation)?| EmployeeRepository      | IE: knows all its employees.                                                                                  |
|                | ... saving the created employee?           | EmployeeRepository      | IE: owns all its employees.                                                                                   |
| Step 8         | ... informing operation success?           | RegisterEmployeeUI      | IE: is responsible for person interactions.                                                                     |

### Systematization ##

According to the taken rationale, the conceptual classes promoted to software classes are:

* Employee
* EmployeeRepository
* RoleRepository

Other software classes (i.e. Pure Fabrication) identified:

* RegisterEmployeeUI
* RegisterEmployeeController
* AuthenticationRepository


## 3.2. Sequence Diagram (SD)

### Full Diagram

This diagram shows the full sequence of interactions between the classes involved in the realization of this person story.

![Sequence Diagram - Full](svg/us003-sequence-diagram.svg)


## 3.3. Class Diagram (CD)

![Class Diagram](svg/us003-class-diagram.svg)