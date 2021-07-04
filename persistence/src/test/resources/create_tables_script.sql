DROP TABLE IF EXISTS gift_certificate;

CREATE TABLE gift_certificate (
                                  id BIGINT NOT NULL PRIMARY KEY AUTO_INCREMENT,
                                  name VARCHAR(100) NOT NULL,
                                  description VARCHAR(1000) NOT NULL,
                                  price DECIMAL(10,2) NOT NULL,
                                  duration INT NOT NULL,
                                  create_date Timestamp NOT NULL,
                                  last_update_date Timestamp NOT NULL

);