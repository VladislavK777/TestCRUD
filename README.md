# TestCRUD
### Get Token
POST /get-token
<br>Body username and pass:
<br/>{
	"username": "admin", 
	"password": "123" 
}

### Base API for user and admin role:
GET /api/v1/all
<br/>return json

GET /api/v1/search/name?name=<name>
<br/>return json

GET /api/v1/search/brand?brand=<brand>
<br/>return json

GET /api/v1/leftovers
<br/>return json

### API for only admin role:
POST /api/v1/admin/insert
<br/>Body for example: 
<br/>{
	"name": "T-shirt",
	"brand": "US Polo",
	"price": 4000.56,
	"quantity": 4
}

PUT /api/v1/admin/update/1
<br/>Body for example: 
<br/>{
	"name": "T-shirt",
	"brand": "US Polo",
	"price": 4000.56,
	"quantity": 4
}

DELETE /api/v1/admin/delete/1

# Example call
### Call get token
curl -i -H "Content-Type:application/json" -d "{ \"username\":\"admin\", \"password\":\"123\" }" http://localhost:8080/get-token
<br/>Return:
<br/>{"authToken":"\<TOKEN\>"}

### Call API
curl -i -H "Authorization: Bearer \<TOKEN\>" -X GET http://localhost:8080/api/v1/all
