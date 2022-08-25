Restaurants Microservice
===
This microservice will contain the required endpoints and database connections to create restaurants, add food items to those restaurants, view the details of each restaurant, and search for specific types of food and restaurants.
<br>
JSON formats at bottom of page.

## `/restaurants`
### POST:
- Creates a new restaurant using a JSON object
- 201 Created: JSON object representing the restaurant on creation success
- 400 Bad Request: on creation failure
- 422 Unprocessable Entity: if JSON object is malformed or has invalid field values

### GET:
- 200 OK:  JSON list of restaurants
- 204 No Content: if no restaurants was created

## `/restaurants/{id}`
### GET:
- 200 OK: JSON object of restaurant with id
- 400 Bad Request: on passing an invalid id
- 404 Not Found: on failure to find restaurant

### PUT:
- Updates a restaurant using a JSON object
- 200 OK: JSON object if restaurant updated successfully
- 400 Bad Request: on passing invalid id
- 422 Unprocessable Entity: if JSON object is malformed or has invalid field values

### DELETE:
- 200 OK:  successful deletion of restaurant
- 400 Bad Request: on passing invalid id
- 404 Not Found: on failure to find the restaurant

## `/restaurants/{id}/address`
### PUT:
- 200 OK:  JSON object of updated address associated with restaurant
- 400 Bad Request: on passing invalid id
- 404 Not Found: on failure to find the address

## `/food`
### POST:
- Create new food item for restaurant id using JSON object
- 201 Created: JSON object of restaurant on creation success
- 400 Bad Request: on creation failure
- 422 Unprocessable Entity: if JSON object is malformed or has invalid field values

### GET:
- 200 OK:  JSON list of all food
- 204 No Content: if no food has been added to table

## `/food/{id}`
### GET:
- 200 OK:  JSON object of food requested
- 400 Bad Request: on passing invalid id
- 404 Not Found: on failure to find food

### PUT:
- Updates a food using a JSON object
- 200 OK: JSON object if food updated successfully
- 400 Bad Request: on passing invalid id
- 422 Unprocessable Entity: if JSON object is malformed or has invalid field values

### DELETE:
- 200 OK:  successful deletion of food item
- 400 Bad Request: on passing invalid id
- 404 Not Found: on failure to find the food item

## `/search`
### POST:
- Searches restaurants and food for matching parameters passed with JSON object
- 200 OK: JSON object if results are found
- 404 Not Found: if no results are found
- 422 Unprocessable Entity: if JSON object is malformed or has invalid field values

<br>

## Restaurant JSON DTOs
### Full Restaurant DTO:
```json
{
  "id": "int",
  "name": "string",
  "phoneNo": "string",
  "website": "string",
  "openTime": "time",
  "closeTime": "time",
  "rating": "decimal",
  "ratingCount": "int",
  "address": {
    "id": "int",
    "latitude": "real",
    "longitude": "real",
    "zipcode": "string",
    "city": "string",
    "state": "string",
    "country": "string",
    "streetAddress": "string",
    "unitNumber": "string? (Nullable)"
  },
  "food": [
    {
      "id": "int",
      "name": "string",
      "price": "decimal"
    }
  ]
}
```

### Short Restaurant DTO:
```json
{
  "id": "int",
  "name": "string",
  "openTime": "string",
  "closeTime": "string",
  "rating": "decimal"
}
```

### Create Restaurant DTO:
```json
{
  "name": "string", 
  "zipcode": "string",
  "city": "string",
  "state": "string",
  "country": "string? (Nullable)",
  "streetAddress": "string",
  "unitNumber": "string? (Nullable)", 
  "phoneNo": "string",
  "website": "string? (Nullable)",
  "openTime": "time",
  "closeTime": "time"
}
```

### Restaurant Update DTO:
```json
{
  "name": "string? (Nullable)",
  "phone_no": "string? (Nullable)",
  "website": "string? (Nullable)", 
  "openTime": "time? (Nullable)",  
  "closeTime": "time? (Nullable)"  
}
```

## Food JSON DTOs
### Full Food DTO
```json
{
  "id": "int",
  "name": "string",
  "description": "string",
  "price": "decimal",
  "restaurantId": "int"
}
```

### Short Food DTO
```json
{
  "id": "int",
  "name": "string",
  "price": "decimal"
}
```

### Food Creation DTO
```json
{
  "name": "string",
  "description": "string? (Nullable)",
  "price": "decimal",
  "restaurantId": "int"
}
```

### Food Update DTO
```json
{
  "name": "string? (Nullable)",       
  "description": "string? (Nullable)",
  "price": "decimal? (Nullable)"
}
```

## Address JSON DTOs
### Address DTO
```json
{
    "id": "int",
    "latitude": "double",
    "longitude": "double",
    "zipcode": "string",
    "city": "string",
    "state": "string",
    "country": "string",
    "streetAddress": "string",
    "unitNumber": "string"
}
```

### Address Update DTO
```json
{
  "id": "int",
  "zipcode": "string? (Nullable)",
  "city": "string? (Nullable)",
  "state": "string? (Nullable)",
  "country": "string? (Nullable)",
  "streetAddress": "string? (Nullable)",
  "unitNumber": "string? (Nullable)",
  "latitude": "double? (Nullable)",
  "longitude": "double? (Nullable)"
}
```