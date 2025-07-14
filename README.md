#  Course Search Application – Spring Boot + Elasticsearch

A Spring Boot application that indexes educational courses into Elasticsearch and provides a powerful search API with filters, sorting, and pagination.

---

## ⚙️ Application Setup Guide

## 🚀 Getting Started

1. Clone the repository:
   
```bash
   git clone https://github.com/LogicNinjaX/elasticsearch-assignment.git
```

2. Edit configuration file `application.yml`.

```json
spring:
    application:
          name: elasticsearch-assignment

    data:
      elasticsearch:
         cluster-node: ${cluster-node} # your cluster url
```

3. Start Elasticsearch (via Docker)

```bash
docker-compose up -d
```

4. Build and run the project:
```bash
mvn spring-boot:run
```
5. Access API at `http://localhost:8080/api/search`

---

## API Example – Search Courses

✅ Endpoint
```
http://localhost:8080/api/search
```

---

### 🔧 Query Parameters

| Parameter    | Type       | Required | Description                                      |
|--------------|------------|----------|--------------------------------------------------|
| `q`          | String     | ✅       | Full-text search keyword                         |
| `minAge`     | Integer    | ❌       | Minimum age of target audience                   |
| `maxAge`     | Integer    | ❌       | Maximum age of target audience                   |
| `category`   | String     | ❌       | Course category (e.g., Math, Science, Music, Technology)            |
| `type`       | String     | ❌       | Course type (`ONE_TIME`, `COURSE`, `CLUB`)       |
| `minPrice`   | Float      | ❌       | Minimum course price                             |
| `maxPrice`   | Float      | ❌       | Maximum course price                             |
| `startDate`  | ISO 8601   | ❌       | Only include courses starting on or after date   |
| `sort`       | String     | ❌       | `priceAsc`, `priceDesc`, or default (by date)    |
| `page`       | Integer    | ❌       | Page number (default: 0)                         |
| `size`       | Integer    | ❌       | Number of results per page (default: 10)         |

---

### 📥 Sample Requests

```bash
GET /api/search?q=students&page=1&size=5
```
Response
```json
{
    "total": 4,
    "courses": [
        {
            "id": 2,
            "title": "Mechanical Engineering",
            "category": "Music",
            "price": 75.05,
            "nextSessionDate": "2025-07-14T13:32:02.851Z"
        },
        {
            "id": 5,
            "title": "Statistics with Fun",
            "category": "Technology",
            "price": 120.31,
            "nextSessionDate": "2025-07-15T13:32:02.852Z"
        },
        {
            "id": 10,
            "title": "Space Exploration",
            "category": "Art",
            "price": 134.38,
            "nextSessionDate": "2025-07-15T13:32:02.854Z"
        },
        {
            "id": 13,
            "title": "Music Theory",
            "category": "Science",
            "price": 74.3,
            "nextSessionDate": "2025-07-15T13:32:02.854Z"
        }
    ]
}
```
---
```bash
GET /api/search?q=students&category=Technology&sort=priceAsc&size=2
```
Response
```json
{
    "total": 2,
    "courses": [
        {
            "id": 25,
            "title": "Intro to Chemistry",
            "category": "Technology",
            "price": 14.69,
            "nextSessionDate": "2025-08-17T13:32:02.854Z"
        },
        {
            "id": 33,
            "title": "Biology in Nature",
            "category": "Technology",
            "price": 56.53,
            "nextSessionDate": "2025-09-03T13:32:02.855Z"
        }
    ]
}
```
---
```bash
GET http://localhost:8080/api/search?q=students&category=Technology&startDate=2025-08-17T13:32:02.854Z&sort=priceAsc&size=2
```
Response
```json
{
    "total": 2,
    "courses": [
        {
            "id": 25,
            "title": "Intro to Chemistry",
            "category": "Technology",
            "price": 14.69,
            "nextSessionDate": "2025-08-17T13:32:02.854Z"
        },
        {
            "id": 33,
            "title": "Biology in Nature",
            "category": "Technology",
            "price": 56.53,
            "nextSessionDate": "2025-09-03T13:32:02.855Z"
        }
    ]
}
```
