# Real Estate Management System

A comprehensive Java application for real estate agencies to manage properties, clients, announcements, and business operations.

## Project Overview

This real estate management system provides a complete solution for real estate agencies to handle their day-to-day operations. The system supports multiple user roles including administrators, employees (agents), clients, store managers, and network managers, each with specific access permissions and functionalities.

### Key Features

- **Property Management**: Add, edit, and browse various property types (houses, apartments, land)
- **Announcement System**: Create, publish, and manage property listings
- **User Authentication**: Role-based access control with secure login
- **Client Management**: Track client information and interactions
- **Visit Scheduling**: Book and manage property viewings
- **Order Processing**: Handle purchase and rental orders
- **Agency Management**: Organize agencies, stores, and employees
- **Notifications**: Email and SMS notifications for important events

## Architecture

The application follows a layered architecture pattern with:

- **Domain Layer**: Core business entities and logic
- **Repository Layer**: Data persistence and retrieval
- **Application Layer**: Controllers that coordinate operations
- **UI Layer**: User interface components

The system uses a repository pattern for data access, making it easy to switch between different storage mechanisms.

## Getting Started

### Prerequisites

- Java JDK 11 or higher
- Maven 3.6 or higher

### Installation

1. Clone the repository:
   ```bash
   git clone https://github.com/yourusername/real-estate-management.git
   cd real-estate-management
   ```

2. Build the project with Maven:
   ```bash
   mvn clean install
   ```

3. Run the application:
   ```bash
   java -jar target/real-estate-app.jar
   ```

### Default Users

The system comes pre-configured with the following users:

| Username | Email | Password | Role |
|----------|-------|----------|------|
| MainAdministrator | admin@this.app | admin | Administrator |
| Employee | employee@this.app | pwd | Employee |
| Client | client@this.app | pwd | Client |
| Store Manager | storemanager@this.app | pwd | Store Manager |
| Network Manager | networkmanager@this.app | pwd | Network Manager |

## Development

### Project Structure

- `src/main/java/pt/ipp/isep/dei/esoft/project/`
  - `domain/` - Business entities and value objects
  - `repository/` - Data access objects
  - `application/controller/` - Application logic
  - `ui/` - User interface components
  - `ui/console/` - Console UI implementation
  - `ui/gui/` - GUI implementation (if applicable)

### Running Tests

```bash
# Run all tests
mvn clean test

# Generate test coverage report
mvn test jacoco:report

# Check if coverage thresholds are achieved
mvn test jacoco:check

# Generate mutation testing report
mvn org.pitest:pitest-maven:mutationCoverage
```

### Generating Documentation

```bash
# Generate source code documentation
mvn javadoc:javadoc

# Generate test code documentation
mvn javadoc:test-javadoc
```

## Data Persistence

The application uses Java serialization for data persistence. Data is saved to a file named `RealEstateUSA.ser`. On startup, the application checks if this file exists:

- If it exists, it deserializes the data and only runs the user bootstrap
- If it doesn't exist, it runs the full bootstrap and creates a new serialized file

## UML Diagrams

UML diagrams can be generated using PlantUML. Run the following script from the project root:

```bash
bin/generate-plantuml-diagrams.sh
```

## Acknowledgments

* Instituto Superior de Engenharia do Porto (ISEP)
* Degree in Informatics Engineering (LEI)
* Ricardo Gonçalves