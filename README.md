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

## `/restaurants/{id}/food`
### POST:
- Create new food item for restaurant id using JSON object
- 201 Created: JSON object of restaurant on creation success
- 400 Bad Request: on creation failure
- 422 Unprocessable Entity: if JSON object is malformed or has invalid field values

### GET:
- 200 OK:  JSON list of food associated with restaurant
- 204 No Content: if no food has been added to restaurant

## `/restaurants/{rid}/food/{fid}`
### GET:
- 200 OK:  JSON object of food requested
- 400 Bad Request: on passing invalid rid or fid
- 404 Not Found: on failure to find restaurant or food

### PUT:
- Updates a food using a JSON object
- 200 OK: JSON object if food updated successfully
- 400 Bad Request: on passing invalid rid or fid
- 422 Unprocessable Entity: if JSON object is malformed or has invalid field values

### DELETE:
- 200 OK:  successful deletion of food item
- 400 Bad Request: on passing invalid rid or fid
- 404 Not Found: on failure to find the food item

## `/food`
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
### Restaurant JSON:
```json
{
  "id": "int",
  "name": "string",
  "address": {
    "id": "int",
    "latitude": "real",
    "longitude": "real",
    "zipCode": "string",
    "state": "string",
    "country": "string",
    "streetAddress": "string",
    "houseNumber": "string? (Nullable)"
  },
  "phoneNo": "string",
  "website": "string",
  "openTime": "time",
  "closeTime": "time",
  "rating": "decimal",
  "ratingCount": "int",
  "menuItems": [
    {
      "id": "int",
      "name": "string",
      "price": "decimal",
      "description": "string"
    }
  ]
}
```

### Restaurant Creation JSON:
```json
{
  "name": "string", 
  "zipCode": "string",
  "state": "string",
  "country": "string",
  "streetAddress": "string",
  "houseNumber": "string? (Nullable)",
  "unitNumber": "string? (Nullable)", 
  "phoneNo": "string",
  "website": "string",
  "openTime": "time",
  "closeTime": "time"
}
```

### Restaurant Update JSON:
```json
{
  "name": "string (Nullable)",
  "zipCode": "string (Nullable)", 
  "state": "string (Nullable)",   
  "country": "string (Nullable)", 
  "streetAddress": "string (Nullable)",
  "houseNumber": "string (Nullable)",  
  "unitNumber": "string (Nullable)",   
  "phone_no": "string (Nullable)",
  "website": "string (Nullable)", 
  "openTime": "time (Nullable)",  
  "closeTime": "time (Nullable)"  
}
```

## Food JSON DTOs
### Food JSON
```json
{
  "id": "int",
  "name": "string",
  "description": "string",
  "price": "decimal",
  "restaurantId": "int"
}
```

### Food Creation JSON
```json
{
  "name": "string",
  "description": "string",
  "price": "decimal"
}
```

### Food Update JSON
```json
{
  "name": "string (Nullable)",       
  "description": "string (Nullable)",
  "price": "decimal (Nullable)"      
}
```

## Search JSON DTOs
### Search JSON
```json
{
  "term": "string"
}
```