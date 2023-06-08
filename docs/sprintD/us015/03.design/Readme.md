# US015 - As an agent, I intend to list booking requests for properties managed by me

## 3. Design - User Story Realization 

### 3.1. Rationale

| Interaction ID | Question: Which class is responsible for...                          | Answer                          | Patterns              |
|:---------------|:----------------------------------------------------------------------|:----------------------------------|:---------------------|
| Step 1         | ... interacting with the actor?                                       | VisitScheduleRequestsWindow (UI)  | Pure Fabrication     |
|                | ... coordinating the US?                                              | VisitScheduleController (VCTRL)   | Controller           |
|                | ... requesting the current user's email?                              | AuthenticationController (AuthCTRL)| Information Expert   |
| Step 2         | ... providing the current user's email?                               | AuthenticationController (AuthCTRL)| Information Expert   |
|                | ... processing the request for pending scheduled visits?              | VisitScheduleController (VCTRL)   | Controller           |
| Step 3         | ... obtaining the agent's pending scheduled visits by email?          | VisitScheduleRepository (VSREPO)  | Information Expert   |
| Step 4         | ... displaying the pending scheduled visits?                          | VisitScheduleRequestsWindow (UI)  | Pure Fabrication     |
| Step 5         | ... requesting the filtering and sorting of pending scheduled visits? | VisitScheduleController (VCTRL)   | Controller           |
| Step 6         | ... filtering and sorting the pending scheduled visits?               | VisitScheduleRepository (VSREPO)  | Information Expert   |
| Step 7         | ... displaying the filtered and sorted scheduled visits?              | VisitScheduleRequestsWindow (UI)  | Pure Fabrication     |


## 3.2. Sequence Diagram (SD)

### Full Diagram

This diagram shows the full sequence of interactions between the classes involved in the realization of this user story.

![Sequence Diagram - Full](svg/us015-sequence-diagram-full.svg)



## 3.3. Class Diagram (CD)

![Class Diagram](svg/us015-class-diagram.svg)