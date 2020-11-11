# Luhn-Test

Enter 16-digits code on the form and check it using Luhn algorithm.

## HTTP endpoints

- **/api/check-code** - accept POST in application/json format
  - parameters:
    - `code` - 16 digits string
  - returns:
    - `message` json formatted response
- **/**  
  static html page route

## Start server

Server listens on `http://localhost:8800/`

```bash
  clojure -A:serve
```

## Run tests

```bash
  clojure -A:test
```
