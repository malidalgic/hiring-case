# Compensation Data API

This project provides a REST API for querying compensation data. The API supports listing compensation data with filtering and sorting capabilities, fetching a single record, and returning a sparse fieldset.

## Technologies Used

- Java
- Spring Boot
- JSONPath

## Setup

### Prerequisites

- Java 17
- Maven

The application will start on `http://localhost:8080`.

## API Endpoints

### List Compensation Data

- **URL:** `/compensation_data`
- **Method:** `GET`
- **Query Parameters:**
  - `sort`: (Optional) Sort by a specific field (e.g., `Timestamp`).
  - Filtering parameters (e.g., `Employer=Walmart`, `Location=Bentonville, AR`).

#### Example Request

    http
    GET /compensation_data?sort=Timestamp&Employer=Walmart
    

#### Example Response

    json
    [
        {
            "Timestamp": "3/21/2016 12:58:58",
            "Employer": "Walmart",
            "Location": "Bentonville, AR",
            "Job Title": "Senior Developer",
            "Years at Employer": "8",
            "Years of Experience": "15",
            "Annual Base Pay": "65,000",
            "Signing Bonus": "",
            "Annual Bonus": "5,000",
            "Annual Stock Value/Bonus": "3,000",
            "Gender": "Male",
            "Additional Comments": ""
        }
    ]
    

### Get Single Compensation Record

- **URL:** `/compensation_data/single`
- **Method:** `GET`
- **Query Parameters:**
  - `Timestamp`: The timestamp of the record to fetch.
  - `fields`: (Optional) Comma-separated list of fields to include in the response.

#### Example Request

    http
    GET /compensation_data/single?Timestamp=3/21/2016%2012:58:58
    

#### Example Response

    json
    {
        "Timestamp": "3/21/2016 12:58:58",
        "Employer": "Walmart",
        "Location": "Bentonville, AR",
        "Job Title": "Senior Developer",
        "Years at Employer": "8",
        "Years of Experience": "15",
        "Annual Base Pay": "65,000",
        "Signing Bonus": "",
        "Annual Bonus": "5,000",
        "Annual Stock Value/Bonus": "3,000",
        "Gender": "Male",
        "Additional Comments": ""
    }
