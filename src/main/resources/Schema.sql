-- Create Airline table
CREATE TABLE Airline (
    airlineId BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255),
    address VARCHAR(255),
    city VARCHAR (255),
    state VARCHAR (50),
    zip INT,
    phone VARCHAR(20)
);

-- Create Aircraft table
CREATE TABLE Aircraft (
    aircraftId BIGINT AUTO_INCREMENT PRIMARY KEY,
    manufacturer VARCHAR(255),
    model VARCHAR(255),
    tailNumber VARCHAR(255),
    numberOfEngines INT,
    propulsionType VARCHAR(255),
    airline_id BIGINT,
    route_id BIGINT,
    FOREIGN KEY (airline_id) REFERENCES Airline(airlineId),
    FOREIGN KEY (route_id) REFERENCES Route(routeId)
);

-- Create Customer table
CREATE TABLE Customer (
    customerId BIGINT AUTO_INCREMENT PRIMARY KEY,
    firstName VARCHAR(255),
    lastName VARCHAR(255),
    email VARCHAR(255),
    phone VARCHAR(20),
    airline_id BIGINT,
    FOREIGN KEY (airline_id) REFERENCES Airline(airlineId)
);

-- Create Employee table
CREATE TABLE Employee (
    employeeId BIGINT AUTO_INCREMENT PRIMARY KEY,
    firstName VARCHAR(255),
    lastName VARCHAR(255),
    email VARCHAR(255),
    jobTitle VARCHAR(255),
    dateOfHire VARCHAR(20),
    airline_id BIGINT,
    station_id BIGINT,
    FOREIGN KEY (airline_id) REFERENCES Airline(airlineId),
    FOREIGN KEY (station_id) REFERENCES Station(stationId)
);
    -- Alter Employee table to add station_id column
	ALTER TABLE Employee ADD COLUMN station_id BIGINT;
	ALTER TABLE Employee ADD FOREIGN KEY (station_id) REFERENCES Station(stationId);
);

-- Create Route table
-- Create Route table
CREATE TABLE Route (
    routeId BIGINT AUTO_INCREMENT PRIMARY KEY,
    arrivalAirport VARCHAR(255),
    departureAirport VARCHAR(255)
);

-- Create Station table
CREATE TABLE Station (
    stationId BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255),
    city VARCHAR(255),
    state VARCHAR(255),
    zip VARCHAR(10),
    phone VARCHAR(20),
    airline_id BIGINT, -- Foreign key referencing Airline table
    FOREIGN KEY (airline_id) REFERENCES Airline(airlineId)
);

-- Create Crew_Routes join table
CREATE TABLE Crew_Routes (
    employee_id BIGINT,
    route_id BIGINT,
    FOREIGN KEY (employee_id) REFERENCES Employee(employeeId),
    FOREIGN KEY (route_id) REFERENCES Route(routeId),
    PRIMARY KEY (employee_id, route_id)
);

-- Create Customer_Routes join table
CREATE TABLE Customer_Routes (
    customer_id BIGINT,
    route_id BIGINT,
    FOREIGN KEY (customer_id) REFERENCES Customer(customerId),
    FOREIGN KEY (route_id) REFERENCES Route(routeId),
    PRIMARY KEY (customer_id, route_id)
);

-- Create Route_Aircraft join table
CREATE TABLE Aircraft_Routes (
    routeId BIGINT,
    aircraftId BIGINT,
    FOREIGN KEY (routeId) REFERENCES Route(routeId),
    FOREIGN KEY (aircraftId) REFERENCES Aircraft(aircraftId),
    PRIMARY KEY (routeId, aircraftId)
);


