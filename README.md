# Integration Test Feign POC

This project demonstrates a simple test automation setup using:

- **Feign** as an API client to test downstream system data through an API
- **Google BigQuery client** to set source data
- **TestNG** as the test execution framework

## Getting Started

### Prerequisites

Before you begin, ensure you have the following:

- **IDE**: IntelliJ IDEA or any Java IDE of your choice
- **Java 21** installed and configured (`java -version` should show 21)
- **Gradle Wrapper** available (the `gradlew` script is included in this project)

### Initial Setup

1. Clone this repository:

   ```bash
   git clone <your-repo-url>
   cd <project-directory>
   ```

2. Build the project:

   ```bash
   ./gradlew build
   ```

## How to run

Run tests:

   ```bash
   ./gradlew clean test
   ```

## Allure Report

This project uses **Allure** for advanced test reporting.

### How to generate the Allure report

After running your tests, generate the Allure report with:

```bash
./gradlew allureReport
```

To view the report in your browser, run:

```bash
./gradlew allureServe
```

This will start a local server and open the Allure report automatically.

## Example

- See `ApiIntegrationTest.java` for a sample test using Feign and BigQuery.
