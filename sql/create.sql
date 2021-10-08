#CREATE main_db;
DROP DATABASE IF EXISTS main_db;
CREATE DATABASE main_db;
USE main_db;
DROP TABLE IF EXISTS tablez;
CREATE TABLE tbs(
                    tb_id INTEGER PRIMARY KEY AUTO_INCREMENT,
                    tb_name VARCHAR(50) NOT NULL,
                    number_of_seats INTEGER NOT NULL DEFAULT 4
);
ALTER TABLE tbs AUTO_INCREMENT = 100;

INSERT INTO tbs(tb_name, number_of_seats) VALUES("Table-01", 6);


#CREATE secondary_db; 
DROP DATABASE IF EXISTS secondary_db;
CREATE DATABASE secondary_db;
USE secondary_db;
DROP TABLE IF EXISTS bills;
CREATE TABLE bills(
                      bill_id INTEGER PRIMARY KEY AUTO_INCREMENT,
                      table_id INTEGER NOT NULL,
                      entry_time DATETIME NOT NULL DEFAULT NOW(),
                      out_time DATETIME,
                      price DECIMAL(10,2)
);
ALTER TABLE bills AUTO_INCREMENT = 100;
INSERT INTO bills(table_id, entry_time,out_time,price) VALUES(100,NOW(), NULL, 1200);



