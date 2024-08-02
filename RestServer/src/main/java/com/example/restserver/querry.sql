SELECT * FROM sensor;
SELECT * FROM measurement;

INSERT INTO Measurement(value, raining, sensor_id) VALUE (15,true,1);

CREATE TABLE Measurement (
     id INT PRIMARY KEY AUTO_INCREMENT,
     value FLOAT NOT NULL CHECK (value > -100 AND value < 100),
     raining TINYINT(1),
     sensor_id INT NOT NULL,
     FOREIGN KEY (sensor_id) REFERENCES Sensor(id) ON DELETE CASCADE
);
CREATE TABLE Sensor(
                         id int PRIMARY KEY AUTO_INCREMENT,
                         name varchar(30) NOT NULL UNIQUE
  );
ALTER TABLE Measurement CHANGE value value float NOT NULL  check ( value > -100 and value < 100 );
ALTER TABLE Measurement ADD COLUMN created_at TIMESTAMP ;



