# TestCRUD
### Base API for user and admin role:
<br/>GET /api/v1/all
<br/>return json

GET /api/v1/search/name?name=<name>
<br/>return json

GET /api/v1/search/brand?brand=<brand>
<br/>return json

GET /api/v1/leftovers
<br/>return json

### API for only admin role:
<br/>POST /api/v1/admin/insert
<br/>Body for example: 
<br/>{
	"name": "T-shit",
	"brand": "US Polo",
	"price": 4000.56,
	"quantity": 4
}

PUT /api/v1/admin/update/1
<br/>Body for example: 
<br/>{
	"name": "T-shit",
	"brand": "US Polo",
	"price": 4000.56,
	"quantity": 4
}

DELETE /api/v1/admin/delete/1

# Example call
### Call login
curl -i -X POST -d username=admin -d password=123 -c /Users/vladislavklockov/Desktop/testcrud/cookies.txt
<br/>http://localhost:8080/login

### Call API
<br/>curl -i --header "Accept:application/json" -X GET -b /Users/vladislavklockov/Desktop/testcrud/cookies.txt
<br/>http://localhost:8080/api/v1/all
<br/>http://localhost:8080/api/v1/admin/delete/1 - user get 403


