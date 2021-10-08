DROP DATABASE IF EXISTS secondary_db;
CREATE DATABASE secondary_db;
USE secondary_db;
DROP TABLE IF EXISTS bills;
CREATE TABLE bills(
                      bill_id INTEGER PRIMARY KEY AUTO_INCREMENT,
                      entry_time DATETIME NOT NULL DEFAULT NOW(),
                      out_time DATETIME,
                      price DECIMAL(10,2)
);