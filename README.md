# SPA Reservation System

## Overview

The SPA Reservation System is a console-based application written in Java that allows users to book and manage spa treatments and accommodations. This project is designed to handle client registrations, treatment reservations, and accommodation bookings, ensuring a smooth and organized process for both clients and administrators.

## Features

- **Client Management:**
  - Add and manage client information.
  - View client details and reservations.
  
- **Treatment Management:**
  - Add, modify, and delete spa treatments.
  - View available treatments.
  
- **Accommodation Management:**
  - Add, modify, and delete accommodation options.
  - View available accommodations.
  
- **Reservation Management:**
  - Book treatments and accommodations.
  - Confirm and cancel reservations.
  - View and manage client reservations.

## Technologies Used

- **Java**: The core language used for developing the application.
- **Maven**: For project management and dependency management.
- **JUnit**: For unit testing the application.

## Getting Started

### Prerequisites

- Java Development Kit (JDK) 19.0.2
- Apache Maven

### Installation

1. **Clone the repository:**
    ```sh
    git clone https://github.com/djakiela/Projekt-zespolowy.git
    ```
2. **Navigate to the project directory:**
    ```sh
    cd Projekt-zespolowy/SPA
    ```
3. **Build the project using Maven:**
    ```sh
    mvn clean install
    ```

### Running the Application

1. **Run the main class:**
    ```sh
    mvn exec:java -Dexec.mainClass="pl.wsb.spa.Main"
    ```

## Testing

Unit tests are written using JUnit. To run the tests, use the following command:
```sh
mvn test
