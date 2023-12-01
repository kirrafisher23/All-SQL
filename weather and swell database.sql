create schema weathermodel; 

create table weathermodel.location(
locationID integer unique NOT NULL auto_increment,
CityName varchar(255) not null,
Country varchar(255) unique not null, 
Region  varchar(255) not null,
PRIMARY KEY (locationID)
);

CREATE TABLE weathermodel.windspeeddata (
    WindSpeedID INTEGER UNIQUE NOT NULL AUTO_INCREMENT,
    WindSpeedKT INTEGER NOT NULL,
    PRIMARY KEY (WindSpeedID)
);

CREATE TABLE weathermodel.datedata (
    datekey INTEGER UNIQUE NOT NULL AUTO_INCREMENT,
    dateID date NOT NULL,
    PRIMARY KEY (datekey)
);

create table weathermodel.weatherdata (
    WeatherDataID INTEGER UNIQUE NOT NULL AUTO_INCREMENT,
    TemperatureFahrenheit FLOAT NOT NULL,
    RainfallInch FLOAT,
    Humidity FLOAT,
    WindSpeedMPH INTEGER NOT NULL,
    DomWindDirection VARCHAR(30) NOT NULL,
    LocationID INTEGER,
    DateKey INTEGER, 
    PRIMARY KEY (WeatherDataID),
    FOREIGN KEY (LocationID) REFERENCES location(LocationID),
    FOREIGN KEY (WindSpeedKT) REFERENCES windspeeddata(WindSpeedID),
    FOREIGN KEY (DateKey) REFERENCES datedata(DateKey)
);

create table weathermodel.swelldata (
    SwellDataID INTEGER UNIQUE NOT NULL AUTO_INCREMENT,
    WaveHeight FLOAT NOT NULL, -- average 
    WavePeriodSec FLOAT, -- use of average 
    SwellConditions VARCHAR(30), 
    WindSpeedKT INTEGER,
    WaveDirection VARCHAR(30) NOT NULL,
    LocationID INTEGER,
    PRIMARY KEY (SwellDataID),
    FOREIGN KEY (LocationID) REFERENCES location(LocationID),
    FOREIGN KEY (WindSpeedKT) REFERENCES windspeeddata(WindSpeedID)
);

-- get weather api for data retrival 