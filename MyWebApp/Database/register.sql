CREATE DATABASE travelDB;

USE travelDB;

CREATE TABLE registrations (
  id INT AUTO_INCREMENT PRIMARY KEY,
  name VARCHAR(100),
  email VARCHAR(100),
  phone VARCHAR(15),
  age VARCHAR(10),
  gender VARCHAR(10),
  destination VARCHAR(100),      
  departure DATETIME,
  ret DATETIME,
  package VARCHAR(20)
);
