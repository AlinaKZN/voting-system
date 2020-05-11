Voting system for deciding where to have lunch.
=====================

It represents an application service with REST API using Hibernate/SpringMVC.
Other technologies used: hsqlbd, maven, git

API Documentation:
-----------------

###Admin:
#### input new restaurant
`curl -s -X POST -H "Content-Type:application/json;charset=UTF-8" -d {\"name\":\"created\"} http://localhost:8050/rest/admin/restaurants --user admin@gmail.com:admin`

#### get all users
`curl -s http://localhost:8050/rest/admin/users --user admin@gmail.com:admin`

#### get user with id=100001
`curl -s http://localhost:8050/rest/admin/users/100001 --user admin@gmail.com:admin`

### User
**Main functions:**
#### get all restaurants with today menu
`curl -s http://localhost:8050/rest/profile/restaurants/menus --user user@yandex.ru:password`

#### get profile
`curl -s http://localhost:8050/rest/profile --user user@yandex.ru:password`

**Additional functions:**
#### get all restaurants
`curl -s http://localhost:8050/rest/profile/restaurants --user user@yandex.ru:password`

#### get restaurant with id=100004
`curl -s http://localhost:8050/rest/profile/restaurants/100004 --user user@yandex.ru:password`

#### get restaurant with id=100004 with history of votes
`curl -s http://localhost:8050/rest/profile/restaurants/100004/votes --user user@yandex.ru:password`

#### get restaurant with id=100004 with history of menu
`curl -s http://localhost:8050/rest/profile/restaurants/100004/menu --user user@yandex.ru:password`

###Not auth user - not working
#### register new user
`curl -s -i -X POST -d '{"name":"New User","email":"test@mail.ru","password":"test-password"}' -H 'Content-Type:application/json;charset=UTF-8' http://localhost:8050/rest/profile/register`



