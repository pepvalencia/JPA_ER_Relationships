create database db_naturally; -- Creates the new database
create user 'springuser'@'%' identified by 'ThePassword'; -- Creates the user if not exists
grant all on db_naturally.* to 'springuser'@'%'; -- Gives all privileges to the new user on the newly created database