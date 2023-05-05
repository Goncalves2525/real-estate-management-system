# US 001 - Display Listed Properties

## 1. Requirements Engineering


### 1.1. User Story Description


As an unregistered endUser, I want to display listed properties.



### 1.2. Customer Specifications and Clarifications 


**From the specifications document:**

>	Unregistered users are able to consult the properties by type, number of rooms, and sort by criteria such as price or the parish where the property is located.




**From the client clarifications:**

> Non-authenticated users can only list properties.

> The client should be able to select the type of business (renting or buying), the type of property and the number of rooms. Then, the client should be able to sort properties by price, city and state where the property is located.
 
> If the client does not select the type of business, the type of property and the number of rooms, the application should allow the client to sort all properties that are on sale or on renting.

> If the system does not contain any properties, the system should show an empty list of properties.

> **Question:**  When an unregistered endUser wants to list properties, the list given by the program is sorted by default with which criteria? For example the list is shown with the properties sorted by most recently added?
>  
> **Answer:** Thank you for your suggestion. By default, the list should be shown with the properties sorted by most recently added.

> **Question:** Can the properties be in sale and lease at the same time?
>  
> **Answer:** No.

> **Question:** Can a endUser filter the properties list for example by a type but choosing multiple value?
>
> **Answer:** The endUser should only select one value for each feature of the property.


### 1.3. Acceptance Criteria


* **AC1:** The properties can be consulted by type of business, type of property and number of rooms.
* **AC2:** The properties can be sorted by price and parish.
* **AC3:** It is possible to only filter or only sort the properties.
* **AC4:** It is possible to filter and sort properties simultaneously.



### 1.4. Found out Dependencies


* There is a dependency to "US002 To publish a Sale" since listing the properties depends on what the agents published.


### 1.5 Input and Output Data


**Input Data:**
	
	
* Selected data:
	* Property type (filter)
    * Business type (filter)
    * Number of rooms (filter)
    * Price (sort)
    * Parish (sort)


**Output Data:**

* Filtered list of properties
* Ordered list of properties
* Filtered and ordered list of properties

### 1.6. System Sequence Diagram (SSD)

**Other alternatives might exist.**

#### Alternative One

![System Sequence Diagram - Alternative One](svg/us001-system-sequence-diagram-alternative-one.svg)

#### Alternative Two

![System Sequence Diagram - Alternative Two](svg/us001-system-sequence-diagram-alternative-two.svg)

#### Alternative Three

![System Sequence Diagram - Alternative Three](svg/us001-system-sequence-diagram-alternative-three.svg)

### 1.7 Other Relevant Remarks

*  By default, if there are no properties to be displayed, en empty list is shown.