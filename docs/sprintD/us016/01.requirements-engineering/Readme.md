# US016 - As an agent, when viewing a booking request, I want to respond to the user that scheduled the visit.

## 1. Requirements Engineering

### 1.1. User Story Description


As an agent, when viewing a booking request, I want to respond to the user that scheduled the visit.


### 1.2. Customer Specifications and Clarifications 


**From the specifications document:**

* The users should use a graphical user interface to access the features introduced in Sprint D.

* The application should use object serialization to ensure persistence of the data between two runs of the application. Serialization must be applied to all classes developed in all sprints.


**From the client clarifications:**

> **Question:** In AC2, what is DEI's email service? Are you referring to Outlook?
>  
> **Answer:** Different email services can send the message. These services must be configured using a configuration file to enable using different platforms (e.g.: gmail, DEI's email service, etc.). DEI email service is an email service like gmail or Outllook. These are only examples and you should prepare your system to support any email service.


> **Question:** When the agent is responding to the user that created the request, what should the answer be? Because accepting or declining the request is already done in US011.
>  
> **Answer:** In US11 the agent wants to accept or decline a purchase order for a property. In US16 the agent wants to answer visit requests.


> **Question:** When the agent requests the booking requests list to contact the client, that list should ONLY contain the requests related to that agent?
>
> **Answer:** Yes. Listing is a feature described in US15. Important: In US15 the Agent gets a list of booking requests (made to him). Then, the agent, may want to respond to the user (as defined in US16). US15 and US16 are executed sequentially. Even so, the agent should be able to see a list of all booking requests made to him (US15) without answer any booking request.


> **Question:** Our team is having trouble understanding US016's AC2. Until now, the email has been sent in the form of a text file, however, with this AC, a configuration file that allows the use of different platforms has been introduced. How should the sending of emails be carried out then?
>
> **Answer:** The configuration file defines the email service to be used. The URI of the email service should be defined in the configuration file. The URI can be the path of a file. Please discuss this question with your ESOFT teatchers.


> **Question:** The US15 does the listing and in US16 we are already responsing to one booking request. That said, were is the selection part being done?
>
> **Answer:** In US15 the Agent gets a list of booking requests (made to him). Then, the agent, may want to respond to the user (as defined in US16). US15 and US16 are executed sequentially. Even so, the agent should be able to see a list of all booking requests made to him (US15) without answer any booking request. In US16 the agent selects the booking request.


> **Question:** Should the email with the reply be sent as a file, for example txt, or should it be sent as an email?
>
> **Answer:** You should sent/write the e-mail message to a file named email.txt. All e-mails to be send should be written to this file. We will not use real world e-mail services and this file is used to replace the real world e-mail services.


> **Question:** What should be the difference between Gmail and Dei email service?
>
> **Answer:** These services must be configured using a configuration file to enable using different platforms.


> **Question:** To send an email, we must have a configuration file with the data: host, port, user, password, in which, the host will have a URL or a path to the text file, or should we have only the host field?
>
> **Answer:** In the configuration file you should only define the email service name (example: gmail) and the host address. The host address is a URI. As students did not take a course where they learn how to setup and use and email server, the URI to use is the path to the local email.txt file. All email messages must be written to this file.


> **Question:** Regarding the AC4, it is stated "The response should include the property identification and location.". Is the property identification the same as the location? If not, how should we handle it in the legacy file since there isn't a specific column for each?
>
> **Answer:** The response should include only the property location. US16 is not related with the legacy file!


### 1.3. Acceptance Criteria

* **AC1:** The response is sent by email.
* **AC2:** Different email services can send the message. These services must be configured using a configuration file to enable using different platforms (e.g.: gmail, DEI's email service, etc.)
* **AC3:** The response should include the name and phone number of the responsible Agent.
* **AC4:** The response should include the property identification and location.
* **AC5:** When an Agent responds to a booking request the list of booking requests should be updated to not show this request.

### 1.4. Found out Dependencies

* US009 - As a client, I want to leave a message to the agent to schedule a visit to a property of my interest. - **Must be implemented before this user story.**
* US015 - As an agent, I intend to list all booking requests for properties managed by me - **Must be implemented before this user story.**

### 1.5 Input and Output Data

**Input Data:**

* Typed data:
  * Reason for rejecting the booking request
* Selected data:
  * Selection of the booking request to be responded to


**Output Data:**

* (In)Success of the operation

### 1.6. System Sequence Diagram (SSD)

![System Sequence Diagram](svg/us016-system-sequence-diagram.svg)

### 1.7 Other Relevant Remarks

* 