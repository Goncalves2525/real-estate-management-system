# US 011 - As an agent, I want to list real estate purchase orders to accept or decline 

## 1. Requirements Engineering


### 1.1. User Story Description


As an agent, I want to list real estate purchase orders to accept or decline a purchase order for a property. After accepting or declining, an email notification should be sent to the customer.

### 1.2. Customer Specifications and Clarifications 


**From the specifications document:**

> As an agent, I want to list real estate purchase orders to accept or decline a purchase order for a property. After accepting or declining, an email notification should be sent to the customer.


**From the client clarifications:**

> **Question:** We are having a little issue defining what the sun exposure might be. We are not sure what it will be as a value. If it's a number that defines how much exposure the house has or if it is just a text saying if it has or hasn't sun exposure.
>  
> **Answer:** Sun exposure will take the following values: North, South, East, or West.


> **Question:** When the agent declines an order, she has to be removed from the list and system?
>  
> **Answer:** The order should be removed from the list but not from the system.

> **Question:** 
>
> **Answer:** 

> **Question:**
>
> **Answer:**

> **Question:**
>
> **Answer:**

> **Question:**
>
> **Answer:**

> **Question:**
>
> **Answer:**

> **Question:**
>
> **Answer:**

> **Question:**
>
> **Answer:**
### 1.3. Acceptance Criteria


* **AC1:** All required fiels must be filled in.
* **AC2:** The list of purchase orders should be grouped by property. The properties should be sorted from the oldest to the most recent one. For each property, the list of purchase orders should be sorted by the amount offered, the highest offer must appear first.
* **AC3:** For each offer, the agent must be able to accept or decline it. The action of accepting or declining an offer should trigger an email notification to the client. 
* **AC4:** When a purchase order is accepted, all the other orders should be declined, and a message sent to the client.
* **AC5:** If a property does not contain any offers, the system should show an empty list of offers.
* **AC6:**

### 1.4. Found out Dependencies


* There is a dependency to "US 003 - As a system administrator, I want to register a new employee" since the choice of agent depends on its creation.


### 1.5 Input and Output Data


**Input Data:**

* Typed data:
	* Owner:
		* a name,
		* a citizen's card number,
		* a tax number,
		* an address,
		* an email address,
		* a contact telephone number.
      
* Selected data:
	* Property:
		* an area (in m2),
		* a location,
		* a distance from the city centre,
		* a requested price (USD),
		* a single or multiple photographs.
			* Apartment or House:
				* a number of bedrooms,
				* a number of bathrooms,
				* a number of parking spaces,
				* any available equipment (such as central heating and/or air conditioning).
			* House:
				* existence of a basement,
				* existence of an inhabitable loft,
				* a sun exposure value.


**Output Data:**

* (In)Success of the operation
* 

### 1.6. System Sequence Diagram (SSD)

**Other alternatives might exist.**

#### Alternative One

![System Sequence Diagram - Alternative One](svg/us004-system-sequence-diagram-alternative-one.svg)

#### Alternative Two

![System Sequence Diagram - Alternative Two](svg/us004-system-sequence-diagram-alternative-two.svg)

### 1.7 Other Relevant Remarks

* The created task stays in a "not published" state in order to distinguish from "published" tasks.