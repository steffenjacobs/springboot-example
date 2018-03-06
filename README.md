# springboot-example
A few examples with Springboot

## 3 simple springboot applications:
- test-backend: simple application which cannot persist data at all  
- test-backend-persistent: simple application which can persist its data into a storage.txt file  
- test-backend-db simple: application which can persist its data into a postgresql server at postgre-db:5432/springbootdb with credentials postgres/root 

## HTTP-GET methods to interact with the applications in the browser
|Description|HTTP-GET|
| -- | -- |
|Add an entry to cache | localhost:8080/createEntry  |
|show all entries | localhost:8080/entries  |
|clear entries | localhost:8080/clear  |
|  | |
|persist entries to storage file / database | localhost:8080/persist  |
|load entries from storage file / database | localhost:8080/load  |

[I also dockerized this project.](https://github.com/steffenjacobs/docker-example)
