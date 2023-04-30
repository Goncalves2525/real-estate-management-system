# US 004 - Owner submit sale or rent 

## 1. Requirements Engineering


### 1.1. User Story Description


As an owner, I intend to submit a request for listing a property sale or rent,
choosing the responsible agent.

### 1.2. Customer Specifications and Clarifications 


**From the specifications document:**

> The owner provides property characteristics and the requested prices, and sends the request to an agent.

> In the case of a request for the sale of a property, the owner must provide information on: the type of property (apartment, house or land), the area in m2, the location, the distance from the city centre, the requested price and one or more photographs.

> If the property is an apartment or a house, the owner also provides: the number of bedrooms, the number of bathrooms, the number of parking spaces and the available equipment, such as central heating and/or air conditioning.

> In case the property is a house, the existence of a basement, an inhabitable loft, and sun exposure must be registered as well.


**From the client clarifications:**

> **Question:** We are having a little issue defining what the sun exposure might be. We are not sure what it will be as a value. If it's a number that defines how much exposure the house has or if it is just a text saying if it has or hasn't sun exposure.
>  
> **Answer:** Sun exposure will take the following values: North, South, East, or West.


> **Question:** Are there any restrictions on the choice of an Agent?
>  
> **Answer:** No.

> **Question:** In case the submission of the listing is online may the owner choose any agent?
>
> **Answer:** Yes.


> **Question:**  In case it is on an agency, must the agent be assigned automatically by the system?
>
> **Answer:** The agent that registers the information in the system can choose to assign any agent.

> **Question:** Is it possible to submit multiple listing for the same property and type of listing?
>
> **Answer:** No.

> **Question:**  Does that imply that a seller can choose the agency/branch/store independently of the location of the property?
>
> **Answer:** Yes.

> **Question:** when publishing a property, if the owner leaves the listing unfinished, can it be saved or stay as as a sketch to be finished later ?
>
> **Answer:** No.

> **Question:** Is there a designated currency for this business, or should we use USD?
>
> **Answer:** Please use USD.

> **Question:** Does an owner need to be registered in the system to submit a request for a property listing?
>
> **Answer:** No. When making the request to list a property, the owner should introduce his own data. The Owner attributes are: the name, the citizen's card number, the tax number, the address, the email address and the telephone number.

> **Question:** In the Project description, there are only specifications for a Sale. What are the required characteristics for a rental?
>
> **Answer:** The caracteristics for a rental are the same as the ones for the sale of a property. The rent value is per month. Additionally, we have to define the contract duration.

> **Question:** In the case of listing a land property, shall the owner refer if there is a building permit already approved?
>
> **Answer:** No.

> **Question:** The client should be able to select (from a list) the type of business, the type of property, and the number of rooms. Does this apply too to the process of an owner submitting a request?
>
> **Answer:** Yes.

> **Question:** When assigning an agent to a property listing, are the available agents shown by the system for the owner to pick? Or does the owner need to provide the agent's information (name, agency,etc)?
>
> **Answer:** The owner should select one agent from a list of agents that work in the selected agency. The owner should select the agency before selecting the agent.

> **Question:** The actor "Buyer\ Client\ Customer", are the names synonyms . Yes or No?
>
> **Answer:** Yes.

> **Question:** In the project description it is mentioned that in the case of a request for the sale of a property, the owner must provide "one or more photographs". Taking that into account, is there a maximum number of photos that can be submitted when publishing an announcement? If so, how many?
>
> **Answer:** The maximum number of photos is 30.

> **Question:**  It was previously stated that an unregistered user could do a property listing request. However, with the introduction of US007, I want to clarify and make sure that now a user needs to be registered in order to buy, sell or rent properties, or if they can still do it unregistered.
>
> **Answer:**  In Sprint B we introduce US7 and now, in US4, the owner needs to be registered in the system to submit a request for listing. You should update all artifacts to include this change.

> **Question:**
>
> **Answer:**


### 1.3. Acceptance Criteria


* **AC1:** All required fiels must be filled in.
* **AC2:** 
* **AC3:** 


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