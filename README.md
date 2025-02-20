# Road_Monitoring_System
## Summary

The road condition monitoring software platform lets citizens identify and address road infrastructure issues. Through a user-friendly interface, citizens can report road conditions by uploading photos and descriptions of problem areas. These reports are then routed to relevant authorities for rapid resolution, facilitating quicker maintenance and improvement of road conditions. Involving citizens in the monitoring process, aims to increase community engagement and participation, contributing to improved road safety and road conditions.

## Functionality

### Functional requirements

**RC01: User registration**

Users will be able to register and create an account using their email address, username, and secure password. The registration process will include email verification to ensure the user's authenticity. The user interface for registration will be feasible and user-friendly.

* Priority: High
* Actors: Citizen/Users

**RC02: Submission of reports**

Registered users will have the ability to submit detailed reports on road conditions, which includes uploading photos and adding descriptions of the issues encountered. The platform will allow the user to specify the exact location either by using automatic GPS data from their device or through manual entry. Each submission will be timestamped and logged into the system database for tracking and resolution.

* Priority: High
* Actors: Citizen/Users

**RC03: Location Data**

The system will accurately capture and record the geographical coordinates of the location where the road issue is reported. This feature will enable the authorities to locate the problem easily without requiring additional information from the reporter/user. Location accuracy will enhance the efficiency of the resolution process by directing resources precisely where they are needed.

* Priority: High
* Actors: Citizen/Users

**RC04: Categorization of Reports**

Users will be able to classify their reports into different predefined categories, such as potholes, traffic signal malfunctions, or signage issues. This categorization helps in sorting and prioritizing issues based on their type and expected impact on road safety and traffic flow. It also facilitates quicker response times by directing reports to the appropriate departments or authorities.

* Priority: Medium
* Actors: Citizen/Users

**RC05: Notification to Authorities**

The system would be able to automatically send a notification to the relevant authorities after the report submission to send the necessary details of the issue like the nature and the location of the issue. These notifications can be sent via email or directly from the platform's administrative interface. Timely notification ensures that no report is overlooked and is addressed as soon as possible.

* Priority: High
* Actors: System/Authority

**RC06: Report Processing**

Authorities require a functional interface to review incoming reports, categorize them correctly, and prioritize them based on urgency and impact. The interface should allow the authorities to update the status of each issue as it moves through different stages of resolution. Efficient processing capabilities will ensure that resources are allocated appropriately to manage road safety effectively.

* Priority: High
* Actors: Authority

**RC09: Analysis function**

The platform should enable statistical analysis of reported problems and their resolution in order to identify trends and bottlenecks and develop more effective strategies to improve road conditions.

* Priority: Low
* Actors: system

**RC10: Security and privacy**

The platform must ensure that users' data and the reports they submit are secure and protected and comply with applicable data protection regulations.

* Priority: High
* Actors: System

## Description of the domain concepts (domain model)

### Class diagram

This is the class diagram -
![class_diagram](uploads/10b6e2efe92750bcc0a7c2955a0688cd/class_diagram.png)

**User to Report:** One-to-Many
Each user can submit multiple reports. This relationship allows the system to track which user submitted each report and users can view their report status. 

**Authority to Report:** One-to-Many
Each authority can manage multiple reports, including updating their status and category as prioritized. 

**Feedback to Report:** One-to-One
Each feedback is linked to a single report. This ensures that feedback is specific to a particular report, allowing for detailed reviews and improvements based on user experiences.

**Notification to Report/User:** Many-to-One (Implicit)
Notification will be sent to the authority as soon as a report is submitted. 

**Report to Category:** Many-to-One
Each report is assigned a category, helping to organize and prioritize reports based on their nature.

**Report to Location:** Many-to-One
Reports include location information, which is crucial for resolving location-specific issues.

**Report to ReportStatus:** Many-to-One
Each report has a status indicating its current state in the resolution process.

**Report to Feedback:** One-to-One
Each report can have associated feedback, providing user insights and satisfaction ratings.

**Analysis:**
This class is used for generating various analyses and reports based on the data, providing insights and statistical summaries to aid decision-making.


### Strategic design
![BoundedContext_Pentaminds](uploads/0785af0e4eeb1f0cc57d1245db99e7a7/BoundedContext_Pentaminds.jpg)

### Context mapping:

**User -> Report:**

- The user is requesting all reports from the report service to show on his account. So user service requires data therefore user acts as a customer and the report acts as a supplier. The context mapping is the customer-supplier relation. Here user is the downstream and the report is the upstream. This is synchronous communication because one service is requesting and another one is responding.

**Report -> Analysis:**

- Analysis is getting data whatever the report service is providing. So here report service is playing as upstream and the supplier and analysis service plays as customer and downstream. This is also synchronous communication because one service is requesting and another one is responding.

**Report -> Authority:**

- Authority requires data from the report service. So here authority acts as a customer and downstream and report acts as a supplier and upstream. The context mapping is the customer-supplier relationship. This is synchronous communication because one service is requesting and another one is responding.

**Report -> Notification:**

- The Report service sends the mail notification and connects to the notification service.  This is asynchronous communication because one service is sending data.

**Notification -> Authority:**

- Notification service requires an email address from the authority service so the notification service is playing as the customer and downstream and the authority service is playing as supplier and upstream. This is synchronous communication because one service is requesting and another one is responding.


### Tactical Design
The picture below represents the Tactical Design of the Bounded Contexts.
![TacticalDesign](uploads/da59b8ecf9e00ccd46d4dcb25771a409/TacticalDesign.jpg)

### Event Storming

This is our Domain Storytelling notation :

![Domain_Storytelling_Notation](uploads/93ea516b6c8ea6b13990c44a43547c44/Domain_Storytelling_Notation.png)

This event storming picture depicts a flowchart illustrating the process of reporting road conditions through a digital platform. Here is the step-by-step story described by the diagram:

**Registration:** A citizen asks for registration on the platform.

**Login:** The citizen asks for login credentials. The platform authenticates the citizen's credentials and generates access.

**Report Creation:**

**Taking Photo:**
-  The logged-in user takes a photo of the road condition.
**Describing Condition:**
-  The logged-in user describes the road condition.
**Submitting Report:**
-  The logged-in user uploads the photo and submits the road condition report to the platform.

**Report Generation:** The platform generates the report.

**Authority Interaction:**

**Register and log in:**
-  The Authority can register and log in.
**Searching:**
-  The authority searches for the report.
**Editing Status:**
-  The authority can edit the status of the report.
**Notifying authority:**
-  As soon as a report is submitted, the authority will be notified.
**Checking Status:**
-  The logged-in user checks the status of their report.

## Architecture design

This is our System architecture:
![Architectural_Design](uploads/8e38e5a29026f69ad784570e5b5c2de6/Architectural_Design.jpg)

**Client:**
   - A user will interact with the system through their web browser. This interaction sends an HTTP request to the server.

**Controller:**
   - The HTTP request first arrives at the Controller. Controller as the entry point that will handle incoming requests. It will determine what the user wants and forwards the request to the appropriate Service for processing.

**Service:**
   - The Service component then will receive the request from the Controller. This is where the main business logic resides. The Service will process the request, performing necessary operations such as calculations, validations, or preparing data.

**Repository:**
   - If the Service needs to access or update data, it interacts with the Repository. The Repository will be responsible for communicating with the Database (DB). It will perform tasks according to the request like fetching records, updating entries, or storing new data.

**Database (DB):**
   - The Database is the actual storage where data is kept. It holds all the information that the system needs to function, such as user details, application data, etc. The Repository will then retrieve or modify data in the Database as instructed by the Service.

**Message Queue:**
   - Sometimes, the Service has tasks that don't need to be handled immediately, such as sending notifications or logging information. These tasks are placed in the Message Queue. The Message Queue will handle these tasks in the background, ensuring that they don't delay the main operations of the system.

**Response to User:**
   - After processing the request and possibly interacting with the Repository and Message Queue, the Service sends the results back to the Controller. The Controller then will format this information into an HTTP response and send it back to the user's browser.


## Implementation

We have used the following technologies for our project:

- Visual studio code
- Java
- Springboot
- React
- MongoDB
- Discovery server
- Kafka
- Resilience4j

**Visual studio code:**

- It is a great support for debugging, syntax highlighting, intelligent code completion, snippets, code refactoring, and embedded version control with Git. 
- Chosen for its customizable environment, and strong support for other web technologies.

**Java:**
- Java has a vast and mature ecosystem with a large community, extensive libraries, frameworks (like Spring Boot), and tools that can be used to build robust applications. Java has many features such as dynamic coding, multiple security features, platform-independent characteristics, network-centric designing, etc. 
- Our team already has prior experience and expertise in Java, so it would make sense to leverage that knowledge over Kotlin.

**Springboot:**
- Spring Boot simplifies building, testing, deploying, and monitoring microservices. This makes the development process faster and more efficient.
- In the microservice architecture, there may be hundreds of microservice instances deployed at a given time.
- Supports Load Balancer.
- Auto Configuration.
- Minimal Code using Annotations.
- Dependency Management.

**React:**
- React is much more flexible and can be used for easily scaling up.
- It is a compelling library capable of producing the UIs of full-blown platforms and large applications.
- React is a component-based approach and virtual DOM, React is much faster than other frameworks.
- React is chosen for creating a responsive and dynamic front end.

**MongoDB:**

- It can be seen in most cases that most of the data is non-structured by the users which cannot be saved in a traditional SQL database. SQL is then just taking extra memory.
- MongoDB is an excellent choice for rapid iterative development.
It is easier to use.


**Eureka:**

- Eureka is a service discovery server used in microservice architectures to register and locate services.
- Eureka is selected to manage service registration and discovery, allowing microservices to dynamically find and communicate with each other without hard-coded endpoints.
- Dynamic IP address and port discovery.

**Kafka:**
- Kafka is a distributed streaming platform used for building real-time data pipelines and streaming applications.
- Kafka is chosen to handle real-time data streams, making it ideal for implementing notification systems and asynchronous communication between microservices.

**Resilence4j:**

- Resilience4j is a lightweight fault tolerance library designed for functional programming, providing mechanisms such as circuit breakers, retries, and rate limiters.
- Resilience4j is chosen for its efficiency and simplicity in implementing resilience patterns, helping to build robust microservices that can handle failures gracefully. Also, we didn't have any experience working with Resilence4j so we wanted to get an idea.



## Solution Architecture

This is our Solution Architecture -

![SolutionArchitecture](uploads/178681bc8b8aaea3ae6a409b2e319ea6/SolutionArchitecture.jpg)

**Client:**
   - The Client is the end-user interacting with the system through a web or mobile application. The Client sends requests to the system and receives responses.

**API Gateway:**
   - The API Gateway acts as a single entry point for all client requests. It routes these requests to the appropriate microservices. 
   - When the Client sends a request, it first hits the API Gateway. The API Gateway then forwards the request to the appropriate service based on the request type.

**Discovery Server (Eureka):**
   - Eureka is a service discovery server that helps in locating services. Each microservice registers itself with Eureka and periodically sends the confirmation to indicate it is still running.
   - The API Gateway and other services query Eureka to discover the endpoints of other services they need to communicate with. This enables dynamic scaling and resilience as services can come and go, and the system can adapt without hardcoded endpoints.

**Microservices:**
Each service interacts with its own MongoDB instance to store and retrieve data as needed.


**User -> Report:**
   The user requests that all reports from the report service be shown on his account. So user service requires data therefore the user acts as a customer and the report acts as a supplier. The context mapping is the customer-supplier relation. Here user is the downstream and the report is the upstream. This is synchronous communication because one service is requesting and another is responding.

**Report -> Analysis:**
   - Analysis is getting data whatever the report service is providing. So here report service is playing as upstream and the supplier and analysis service plays as customer and downstream. This is also synchronous communication because one service is requesting and another is responding.

**Report -> Authority:**
   - Authority requires data from the report service. So here authority acts as a customer and downstream and report acts as a supplier and upstream. The context mapping is the customer-supplier relationship. This is synchronous communication because one service is requesting and another is responding.

**Report -> Notification:**
   - Report service sends the mail notification and connects to the notification service.  This is asynchronous communication because one service is sending data. The report service publishes a message to Kafka topics to trigger a notification, which the Notification Service consumes and processes.

**Notification -> Authority:**
   - Notification service requires an email address from the authority service so the notification service is playing as the customer and downstream and the authority service is playing as supplier and upstream. This is synchronous communication because one service is requesting and another is responding.

**Ressilence4j:**
Resilence4j saves the system from failure. When a service call is wrapped with a Circuit Breaker, Resilience4j will monitor the calls. If a certain number of calls fail, the circuit opens and subsequent calls fail immediately, preventing further stress on the failing service.
After a set period, the Circuit Breaker moves to a half-open state to test if the service has recovered. If the test call succeeds, the circuit closes; if it fails, the circuit opens again.

