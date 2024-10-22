# Supplementary Specification (FURPS+)

## Functionality

_Specifies functionalities that:_

- _are common across several US/UC;_
- _are not related to US/UC, namely: Audit, Reporting and Security._

### Authentication         
Authentication performed using a password holding seven alphanumeric characters, including three capital letters and two digits.


### Licensing              
Licensing the application for use by other companies and/or organizations and/or healthcare systems besides DGS.                 

### Localizability         
Support, at least, the Portuguese and the English languages.                                                                    

### Mail                   
Send notifications via e-mail.                                                                                                   

### Persistence            
It should use object serialization to ensure data persistence between two runs of the application.                               

### Scheduling             
Schedule notifications to be sent when a certain period of time has passed.

### Security                
Authentication performed using a password holding seven alphanumeric characters, including three capital letters and two digits.

### SMS                    
Send notifications via SMS.                                                                                                      

                                                            



## Usability

_Evaluates the user interface. It has several subcategories,
among them: error prevention; interface aesthetics and design; help and
documentation; consistency and standards._


### Accessibility

User manual documentation

## Reliability
_Refers to the integrity, compliance and interoperability of the software. The requirements to be considered are: frequency and severity of failure, possibility of recovery, possibility of prediction, accuracy, average time between failures._


No information.

## Performance
_Evaluates the performance requirements of the software, namely: response time, start-up time, recovery time, memory consumption, CPU usage, load capacity and application availability._

### Response Time  
Calculation of the least effective period of the day in a vaccination center in less than the time it takes to run the specified benchmark algorithm 


## Supportability
_The supportability requirements gathers several characteristics, such as:
testability, adaptability, maintainability, compatibility,
configurability, installability, scalability and more._



### Adaptability    
It should work with any type of vaccines to an ongoing disease outbreak                                                                                                         

### Localizability 
It should support, at least, the Portuguese and the English languages                                                                                                           

### Scalability    
It should support any number of users and vaccination centers regitered                                                                                                         

### Testability    
It should have unit tests for every method that the program has, except for methods that implement Input/Output operations <br/> Generate coverage report


## +

### Design Constraints

_Specifies or constraints the system design process. Examples may include: programming languages, software process, mandatory standards/patterns, use of development tools, class library, etc._




#### Localizability        
Support, at least, the Portuguese and the English languages                                        

#### Persistence           
Utilization of object serialization to ensure data persistence between two runs of the application 
 
#### Scheduling            
Multiple users can register event occurrences and scheduling events                                
 
#### Standards-compliance  
Utilization of the Object-oriented Programming paradigm                                            


### Implementation Constraints

_Specifies or constraints the code or construction of a system such as: mandatory standards/patterns, implementation languages,
database integrity, resource limits, operating system._



#### Implementation Language  
- Developed in Java language <br/>- Graphical interface developed in JavaFX 11 <br/>- The unit tests should be implemented using the JUnit 5 framework 


### Interface Constraints
_Specifies or constraints the features inherent to the interaction of the
system being developed with other external systems._


#### External Systems   
Issuance of EU covid digital vaccination certificate.

### Physical Constraints

_Specifies a limitation or physical requirement regarding the hardware used to house the system, as for example: material, shape, size or weight._

No information.