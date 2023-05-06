# US 001 - Display Listed Properties

## 3. Design - User Story Realization 

### 3.1. Rationale


| Interaction ID                               | Question: Which class is responsible for...                  | Answer                      | Justification (with patterns)            |
|:---------------------------------------------|:-------------------------------------------------------------|:----------------------------|:-----------------------------------------|
| Step 1 : User requests to list Properties  		 | 	... requesting filters and sort criteria to person?           | ListAnnouncementsUI         | Pure Fabrication                         |
| 		                                           | 	... validating users input's?                               | ListAnnouncementsUI         | Pure Fabrication                         |
| Step 2 : System Retrieves List               | 	... coordination between users request and retrieving list? | ListAnnouncementsController | Controller                               |
|                                              | 	... obtaining the list?                                     | AnnouncementRepository      | Information Expert,<br/>Pure Fabrication |
| Step 3 : System shows list			  		            | 	... displaying the list?                                    | ListAnnouncementsUI         | Pure Fabrication                         |
|                                              | 	... creating a Property?                                    | Announcement                | Creater                                  |



### Systematization ##

According to the taken rationale, the conceptual classes promoted to software classes are: 

 * Property
 * Announcement

Other software classes (i.e. Pure Fabrication) identified: 

 * ListAnnouncementsUI  
 * ListAnnouncementsController
 * AnnouncementRepository


## 3.2. Sequence Diagram (SD)

This diagram shows the full sequence of interactions between the classes involved in the realization of this person story.

![Sequence Diagram - Full](svg/us001-sequence-diagram-full.svg)


## 3.3. Class Diagram (CD)

![Class Diagram](svg/us001-class-diagram.svg)