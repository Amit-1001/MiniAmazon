
USE amazon;
CREATE TABLE userInfo (
	id INT(10) NOT NULL AUTO_INCREMENT,
	firstName VARCHAR(20),
    lastName VARCHAR(20),
    email VARCHAR(50),
    passwd VARCHAR(20),
    fullAddress VARCHAR(255),
    userType VARCHAR(10),
    PRIMARY KEY(id)
);
DROP TABLE userDATA;
SELECT * FROM userInfo;
SELECT * FROM userData WHERE email='sauravp@gmail.com' and passwd='Saurav@123';


CREATE TABLE productDetails(
	productId INT(10) NOT NULL AUTO_INCREMENT,
	productId VARCHAR(20) NOT NULL UNIQUE,
    productName VARCHAR(100),
    manufacturerName VARCHAR(255),
    productPrice INT(10),
    productDiscount INT(10),
    productStock INT(10),
    createdBy VARCHAR(50),
    PRIMARY KEY(pId)
);
INSERT INTO productDetails VALUES(3, "G402", "Logitech Wired Gaming Mouse", "Logitech", 2095, 28, 11, "SauravPadghan");
DROP TABLE productDetails;
SELECT * FROM productDetails;