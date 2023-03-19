# US 001 - Display Listed Properties

## 1. Requirements Engineering


### 1.1. User Story Description


As an unregistered user, I want to display listed properties.



### 1.2. Customer Specifications and Clarifications 


**From the specifications document:**

>	Unregistered users are able to consult the properties by type, number of rooms, and sort by criteria such as price or the parish where the property is located.


>	//As long as it is not published, access to the task is exclusive to the employees of the respective organization.// 



**From the client clarifications:**

> **Question:** When an unregistered user opens the application, are there already properties being listed?
>  
> **Answer:** 


> **Question:** //Monetary data is expressed in any particular currency?
>  
> **Answer:** //Monetary data (e.g. estimated cost of a task) is indicated in POTs (virtual currency internal to the platform).


### 1.3. Acceptance Criteria


* **AC1:** The properties can be consulted by type and number of rooms
* **AC2:** The properties can be sorted by price and parish.
* **AC3:** 


### 1.4. Found out Dependencies


* There is a dependency to "US002 To publish a Sale" since listing the properties depends on what the agents published.


### 1.5 Input and Output Data


**Input Data:**

* Typed data:
	* ???
	
	
* Selected data:
	* Property category


**Output Data:**

* List of properties
* 

### 1.6. System Sequence Diagram (SSD)

**Other alternatives might exist.**

#### Alternative One

![System Sequence Diagram - Alternative One](svg/us001-system-sequence-diagram-alternative-one.svg)

#### Alternative Two

![System Sequence Diagram - Alternative Two](svg/us001-system-sequence-diagram-alternative-two.svg)

### 1.7 Other Relevant Remarks

*  ???