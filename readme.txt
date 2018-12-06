to run db in docker execute in cmd: 
docker run --name=test_app_mysql --restart always -e MYSQL_DATABASE=testapp -e MYSQL_USER=root -e MYSQL_PASSWORD=root -e MYSQL_ROOT_PASSWORD=root -p 3303:3306 -d mysql:5.7.15 --character-set-server=utf8 --collation-server=utf8_unicode_ci
db clean data on start and init 
to build app as docker container run maven goal