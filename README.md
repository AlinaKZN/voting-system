Voting system for deciding where to have lunch.
=====================

It represents an application service with REST API using Hibernate/SpringMVC.
Other technologies used: hsqlbd, maven, git

API Documentation:
-----------------

###Admin:
#### get all users
`curl -s http://localhost:8050/rest/admin/users --user admin@gmail.com:admin`

#### get user with id=100001
`curl -s http://localhost:8050/rest/admin/users/100001 --user admin@gmail.com:admin`



### User
#### get all restaurants
`curl -s http://localhost:8050/rest/profile/restaurants --user user@yandex.ru:password`

#### get profile
`curl -s http://localhost:8050/rest/profile --user user@yandex.ru:password`

###Not auth user - not working
#### register new user
`curl -s -i -X POST -d '{"name":"New User","email":"test@mail.ru","password":"test-password"}' -H 'Content-Type:application/json;charset=UTF-8' http://localhost:8050/rest/profile/register`



