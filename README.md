# eBay Web Test Automation Framework

A Selenium-based test automation framework for eBay, built with Java, TestNG, and Allure reporting. The framework follows the **Page Object Model (POM)** design pattern and reads all test data from external JSON files.

---

## 📁 Project Structure

```
ebay/
├── src/
│   └── test/
│       └── java/
│           └── com/
│               └── ebay/
│                   ├── pages/          # Page Object classes
│                   ├── tests/          # TestNG test classes
│                   ├── listeners/      # TestNG listeners (e.g., TestListener)
│                   └── utils/          # Utility/helper classes
├── src/
│   └── main/
│       └── resources/
│           └── testdata/               # External JSON test data files
├── testng.xml                          # TestNG suite configuration
├── pom.xml                             # Maven build & dependency config
└── README.md
```

---

## 🧰 Tech Stack

| Tool/Library     | Version   | Purpose                          |
|------------------|-----------|----------------------------------|
| Java             | 21        | Programming language             |
| Selenium         | 4.21.0    | Browser automation               |
| TestNG           | 7.10.2    | Test execution framework         |
| Allure           | 2.24.0    | Test reporting                   |
| AspectJ          | 1.9.24    | Allure aspect weaving            |
| json-simple      | 1.1.1     | Reading JSON test data files     |
| Maven            | 3.x       | Build & dependency management    |

---

## ✅ Test Scenarios

### TC01 — Home Page Validation (`TC01_HomeTest`)

| Method | Description |
|---|---|
| `validateHomePageUrl` | Navigates to eBay and asserts the current URL contains the expected home page URL (read from JSON) |
| `validateHomeBanners` | Navigates to eBay and asserts that home page banners are visible |

### TC02 — Search & Filter Results (`TC02_SearchResultsTest`)

| Method | Description |
|---|---|
| `searchResultsTest` | Searches for the product SKU (read from JSON), waits for results, and validates the result card count and the displayed count label |
| `verifySearchResultsWithManualTransmission` | Performs the same search, then applies the **Transmission → Manual** filter and validates the filtered result card count and count label |

> All test inputs (`HomePageURL`, `productSKU`) are read from the external JSON data file via `JsonReader` — nothing is hardcoded in the test classes.

---

## 🚀 How to Run

### Prerequisites

- Java 21 installed and `JAVA_HOME` configured
- Maven installed
- Microsoft Edge installed (EdgeDriver managed automatically via Selenium Manager)
- Allure CLI installed (for viewing reports)

### Run Tests

```bash
mvn clean test
```

This command:
1. Compiles the project
2. Executes the TestNG suite defined in `testng.xml`
3. Generates raw Allure results under `target/allure-results/`

### View Allure Report

```bash
allure serve target/allure-results
```

This opens an interactive Allure report in your default browser.

---

## 📊 Reporting

The framework uses **Allure TestNG** for rich, interactive HTML reports.

Reports include:
- Test pass/fail status per scenario
- Step-by-step breakdown of each test
- Failure screenshots (captured by `TestListener` on test failure)
- Test suite timeline and statistics

---

## 📂 Test Data

All test inputs are **externalized** — no hardcoded values in test classes.

Test data is stored in JSON files (e.g., `src/main/resources/testdata/searchData.json`) and parsed using `json-simple`.

Example `searchData.json`:
```json
{
  "searchKeyword": "mazda mx-5",
  "filterCategory": "Transmission",
  "filterValue": "Manual"
}
```

---

## 🏗️ Framework Design

### Page Object Model (POM)
Each page of the eBay website is represented by a dedicated class under `com.ebay.pages`. Page classes encapsulate all locators and interactions for that page, keeping test classes clean and readable.

### Listeners
`com.ebay.listeners.TestListener` implements TestNG's `ITestListener` to handle:
- Logging test start, pass, fail, and skip events
- Capturing and attaching screenshots to Allure on failure

### Utilities
`com.ebay.utils` contains shared helpers such as:
- `DriverManager` — `EdgeDriver` initialization and teardown (see `BaseTest`)
- `JsonReader` — Parsing test data from JSON files
- `ScreenshotUtil` — Capturing screenshots for Allure attachments

---

## ⚙️ Configuration Notes

> **Important:** The `argLine` in `pom.xml` points to the AspectJ weaver JAR in your local Maven repository. If you are running on a different machine, update this path:

```xml
<argLine>
  -javaagent:${user.home}/.m2/repository/org/aspectj/aspectjweaver/1.9.24/aspectjweaver-1.9.24.jar
</argLine>
```

Or replace `C:/Users/INK/` with your own username path on Windows, or the appropriate path on macOS/Linux.

---

## 📋 TestNG Suite

The suite is defined in `testng.xml` at the project root:

```xml
<suite name="EbaySuite" parallel="false">
    <listeners>
        <listener class-name="io.qameta.allure.testng.AllureTestNg"/>
        <listener class-name="com.ebay.listeners.TestListener"/>
    </listeners>
    <test name="HomeAndSearchTests">
        <classes>
            <class name="com.ebay.tests.TC01_HomeTest"/>
            <class name="com.ebay.tests.TC02_SearchResultsTest"/>
        </classes>
    </test>
</suite>
```

---

## 📌 Notes

- The framework uses **Microsoft Edge** (`EdgeDriver`) as the browser, managed automatically via Selenium Manager (bundled with Selenium 4.x) — no manual driver setup required.
- Tests run sequentially (`parallel="false"`) to avoid browser conflicts.
- Allure results accumulate in `target/allure-results`; run `mvn clean` to reset between runs.
