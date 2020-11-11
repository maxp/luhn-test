# Luhn-Test

Enter 16-digits code on the form and check it using Luhn algorithm.

## HTTP endpoints

- **/api/check-code**
  - parameters:
    - `code` - 16 digits string
  - returns:
    - `message` text in response body
- **/**  
  static html page route

## Start server

Server listens on port `8800`

```bash
  clojure -A:serve
```

## Run tests

```bash
  clojure -A:test
```
