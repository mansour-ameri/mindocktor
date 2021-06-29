# mindocktor assignment 

### run project
after pull project enter project folder and in linux or mac just simply run this command
**./run.sh**


### Containers
two container should be up and running ("if not just run **docker-compos up -d** manually again ")
  * *mysql-dev*
  * *app-server*

*app-server* will run on port 8080


### Using app credentials
There is three credential for using this app
* officer
* user
* admin

all of these users come with there own role and permissions
to use backend api client enable basic authentication and also use the provided username (password is the same as username for each one)

*providing ID inside the POST and PUT body for all endpoint not needed.*

### API documents
Check all endpoint API within **{IP-address}:8080/swagger-ui-custom.html**

both **officer** and **admin** user can access swagger endpoints
