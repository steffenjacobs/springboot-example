# springboot-example
A few examples with Springboot

## 3 simple springboot applications:
- test-backend: simple application which cannot persist data at all  
- test-backend-persistent: simple application which can persist its data into a storage.txt file  
- test-backend-db simple: application which can persist its data into a postgresql server at localhost:5432  

## HTTP-GET methods to interact with the applications in the browser
Add an entry to cache with localhost:8080/createEntry  
show all entries: localhost:8080/entries  
clear entries: localhost:8080/clear  
  
persist entries to storage file / database: localhost:8080/persist  
load entries from storage file / database: localhost:8080/load  
