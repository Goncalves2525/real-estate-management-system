# US015 - As an agent, I intend to list all booking requests for properties managed by me

## 1. Requirements Engineering


### 1.1. User Story Description


As an agent, I intend to list all booking requests for properties managed by me


### 1.2. Customer Specifications and Clarifications 


**From the specifications document:**

* The users should use a graphical user interface to access the features introduced in Sprint D.

* The application should use object serialization to ensure persistence of the data between two runs of the application. Serialization must be applied to all classes developed in all sprints.


**From the client clarifications:**

> **Question:** 
>  
> **Answer:** 


> **Question:** 
>  
> **Answer:** 

### 1.3. Acceptance Criteria

* **AC1:** The list of requests must be shown for a specific period (begin date, end date).
* **AC2:** The list of requests must be sorted by date in ascending order. The sorting algorithm to be used by the application must be defined through a configuration file. At least two sorting algorithms should be available.


### 1.4. Found out Dependencies

* US009 - As a client, I want to leave a message to the agent to schedule a visit to a property of my interest. - **Must be implemented before this user story.**
* US016 - As an agent, when viewing a booking request,I want to respond to the user that scheduled the visit. - **Must be implemented after this user story.**

### 1.5 Input and Output Data


**Input Data:**

* Typed data:
  * Date interval (begin date, end date)

**Output Data:**

* (In)Success of the operation

### 1.6. System Sequence Diagram (SSD)

![System Sequence Diagram](svg/us015-system-sequence-diagram.svg)

### 1.7 Other Relevant Remarks

* 