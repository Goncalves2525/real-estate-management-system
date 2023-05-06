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

> **QUESTION:** Can a single employee have more than one role? This is, when a system administrator is registering an employee, can he/she select more than one role for that employee or is it limited to one role per employee?
>
> **ANSWER:** An employee can have more than one role.

> **QUESTION:** When registering a new employee, should their email be their personal email or the company email? If it is the company email, what should the email sufix be? For an example, the email address person@gmail.com has the sufix "gmail.com". Should we register an employee as person@company-name.com?
>
> **ANSWER:** The employee's email account is any email account provided by the employee.

> **QUESTION:** When a person starts a registration, does the application need to validate if the data is valid (for example: blank text box, email without @, etc)?
>
> **ANSWER:** Data validation is always a good practice.

> **QUESTION:** Each branch has an ID, this ID is only composed of numbers or letters, or both?
>
> **ANSWER:** I already answered this question: "The ID is an integer number".

> **QUESTION:** When the System Administrator registers a new Employee, he should receive in his e-mail, the login ID and password. I wanted to know if we are supposed to actually send the credentials to the email, or if we have to approach this rhetorically and create for example a txt file with the information.
>
> **ANSWER:** The credentials should be written to a local file named email.txt.

> **QUESTION:** According to the the statement, the administrator has to clarify the citizen's card number, but what card is it? In the US, there is no identification card, so is it referring to the passport?
>
> **ANSWER:** You are correct. The citizen card number should be replaced by the passport card number.

> **QUESTION:** If so, should it be possible to change each value/detail on its own, or would the only options be to cancel or submit the request?
>
> **ANSWER:** If there are any errors, the application should allow the owner to correct the errors.

> **QUESTION:** When the system administrator is registering a new employee are we free to display what we feel is important or should a specific message be shown? I was thinking of displaying whether the operation was successful or not, is that fine or should something else be displayed as well?
>
> **ANSWER:** A good pratice is to show the information and ask for confirmation.

> **QUESTION:** You have stated before that name, cc number, tax number, email address, phone number and the assigned agency of the employee are the mandatory requirements to register a new one, leaving out the employee's adress and role. This confused me, because it wasn't clear whether leaving out those two characteristics from the answer was intentional or not. Futhermore, the role of the employee seems like too much of an important piece of information to be left out. My request is, then, for you to state whether or not that was a conscious decision in your answer.
>
> **ANSWER:** The role is required.

> **QUESTION:** When registering employees does the administrator selects a role for them?
>
> **ANSWER:** Yes.

> **ANSWER:** This message is to clarify the format of the address "71 ST. NICHOLAS DRIVE, NORTH POLE, FAIRBANKS NORTH STAR, AK, 99705". In this address we have the:
Street: 71 ST. NICHOLAS DRIVE;
City: NORTH POLE;
District: Fairbanks North Star (this is opcional);
State: AK;
Zipcode: 99705.
In the USA, the addresses will not include municipalities or parishes.

> **QUESTION:** Are there only 2 types of commission or can the Administrator define more types of commission?
>
> **ANSWER:** For now we only have two types of commissions

> **QUESTION:** Do employees use a company issued e-mail or do they have to use their personal e-mail to log-in? Can an employee use his work account to sell and/or buy property? And in case he has a company e-mail can he use it for those purposes or does it have to be his personal e-mail?
>
> **ANSWER:** A e-mail account is required to register an employee. This e-mail account will be used as login and will also be used as the employee business e-mail account.

> **QUESTION:** When registering a new employee, all the required data (name, citizen's card number, etc...) have to be filled or exists not mandatory data?
>
> **ANSWER:** Required/Mandatory data that should be filled when registering an employee: name, the citizen's card number, the tax number, the email address, the contact telephone number and the agency to which it is assigned

> **QUESTION:** However, it was replied to a question when a new Employee is created in the system, that a 8 digit Password should be automatically generated. How many digits should we go forward for password length validation in your software? And please confirm required special characters, etc.
>
> **ANSWER:** Sorry, I completely forgot that all our authentication systems require passwords with seven alphanumeric characters in length, including three capital letters and two digits. The password should be generated automatically. The password is sent to the employee by e-mail.

> **QUESTION:** Does the system administrator select the agency to which the employee is assigned and his role from a list? Or does he just type that data?
>
> **ANSWER:** The System Administrator should select.

> **QUESTION:** The system administrator cannot add an agent that already exists, the agent has two unique numbers that identify him (Tax number and Citizen's card number) which one should be used to identify the agent?
>
> **ANSWER:** The tax number.

> **QUESTION:** To register an employee I need to allocate him with a branch. To register a branch I need an employee (to be local manger) but I can't create the employee because I have no branch and I can’t create the branch because I have no employee.
>
> **ANSWER:** Thank you for identifying this issue. We already updated the project description. When a store is created in the system, the System Administrator should not set the Store Manager.
When registering a store, the System Administrator should introduce the following information: an ID, a designation/name, a location, a phone number and an e-mail address.

> **QUESTION:** When registering a new employee, will the administrator set the respective employee password?
>
> **ANSWER:** The password should have eight characters in length and should be generated automatically. The password is sent to the employee by e-mail.

> **QUESTION:** As System Administrator, who wants to specify districts, municipalities and parishes, what specifically he wants to do? Create new locations? Control existent locations?
>
> **ANSWER:** The System Administrator wants to specify information in the system that can be used/selected to introduce the location. Remember that this is an extra US

> **QUESTION:** Can an employee be registered to more than one agency?
>
> **ANSWER:** No

> **QUESTION:** Could you please share how will the designation of new stores be made, is there a pattern perhaps?
>
> **ANSWER:** There is no pattern. The System Administrator can introduce any designation/name. The designation/name should have at most forty characters

> **QUESTION:** Will the System Administrator be able to choose a location from a list of available locations (defined elsewhere in the application) or will he be able to submit any location he wants?
>
> **ANSWER:** The System Administrator can submit any location.

> **QUESTION:** What would be the attributes of the System Administrator, Agency and the Roles?
>
> **ANSWER:** The System Administrator is an employee. You can get the roles from the project description. Please check the project description.
Moreover, I just answered a question to clarify what are the attributes of an agency/store.

> **QUESTION:** Does a store designation have to be detailed? If so, will the system administrator have to register the name, email, phone number or anything else? Does the location of a store have to be detailed as well? If yes, will the system administrator have to record the address, postcode or something else? To register the store manager, does the system administrator only register the manager's name? If not, will the system administrator have to register the manager as if he were an employee (except the agent he is assigned to)?
>
> **ANSWER:** When registering a store, the System Administrator should introduce the following information: an ID, a designation/name, a location, a local manager, a phone number and an e-mail address. The ID is an integer number. An example of the store location is: 71 ST. NICHOLAS DRIVE, NORTH POLE, FAIRBANKS NORTH STAR, AK, 99705. An example phone number is (907) 488-6419.

> **QUESTION:** can there be more than one admin?
>
> **ANSWER:** No.

> **QUESTION:** The administrator when registering a new employee will also have to specify the category/office that he will perfom (for example agent, store manager, store network manager)?
>
> **ANSWER:** The administrator has to specify the role of the employee.





### 1.3. Acceptance Criteria


* **AC1:** All required fields must be filled in.
* **AC2:** The employee password must have seven alphanumeric characters in length, including three capital letters and two digits. The password should be generated automatically. The password is sent to the employee by e-mail


### 1.4. Found out Dependencies


* There is a dependency to US5 and US7 because the system administrator creates the agencies too.


### 1.5 Input and Output Data


**Input Data:**

* Typed data:
	* name
	* email address
    * passport card number 
	* the tax number
    * the contact telephone number
    * the address

	
* Selected data:
	* the roles that the employee will perform
    * the agency to which it is assigned


**Output Data:**

* (In)Success of the operation

### 1.6. System Sequence Diagram (SSD)


#### System Sequence Diagram (SSD)

![System Sequence Diagram - Alternative Two](svg/us003-system-sequence-diagram.svg)

### 1.7 Other Relevant Remarks

* .