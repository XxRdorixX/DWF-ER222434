--
-- Tablas de la base de datos: `guia_jpa`
--

-- ----------------------------------------------------------------
--  TABLE POST
-- ----------------------------------------------------------------
DROP TABLE IF EXISTS POST_COMMENT;
DROP TABLE IF EXISTS POST;

CREATE TABLE POST (
                      ID INT NOT NULL AUTO_INCREMENT,
                      TITLE VARCHAR(1000) NOT NULL,
                      POST_DATE DATE NOT NULL,
                      PRIMARY KEY (ID)
) ENGINE=InnoDB;

CREATE TABLE POST_COMMENT (
                              ID INT NOT NULL AUTO_INCREMENT,
                              REVIEW VARCHAR(1000) DEFAULT NULL,
                              COMMENT_DATE DATE NOT NULL,
                              POST_ID INT DEFAULT NULL,
                              PRIMARY KEY (ID),
                              CONSTRAINT FK_POST_COMMENT_POST_ID
                                  FOREIGN KEY (POST_ID)
                                      REFERENCES POST(ID)
                                      ON DELETE CASCADE
) ENGINE=InnoDB;
