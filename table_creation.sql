CREATE TABLE gift (
    Id  SERIAL PRIMARY KEY,
    Name VARCHAR(32) NOT NULL
);

CREATE TABLE production (
    Id  SERIAL PRIMARY KEY,
    IdGift INTEGER REFERENCES gift,
        WorkStatus VARCHAR(32) CHECK (WorkStatus IN ('not started', 'materials selection',
                                                  'assembly', 'packaging', 'completed'))
);

CREATE TABLE elf (
    Id  SERIAL PRIMARY KEY,
    Name VARCHAR(32) NOT NULL
);

CREATE TABLE elf_production (
    Id  SERIAL PRIMARY KEY,
    IdElf INTEGER REFERENCES elf,
    IdProduction INTEGER REFERENCES production
);

CREATE TABLE child (
    Id  SERIAL PRIMARY KEY,
    Name VARCHAR(32) NOT NULL,
    Surname VARCHAR(32) NOT NULL
);

CREATE TABLE address (
    Id  SERIAL PRIMARY KEY,
    Country VARCHAR(32) NOT NULL,
    Region VARCHAR(32) NOT NULL,
    City VARCHAR(32) NOT NULL,
    Street VARCHAR(32) NOT NULL,
    House VARCHAR(32) NOT NULL,
    Room INTEGER
);

CREATE TABLE letter (
    Id  SERIAL PRIMARY KEY,
    IdChild INTEGER REFERENCES child,
    IdGift INTEGER REFERENCES gift,
    IdAddress INTEGER REFERENCES address,
    Truth BOOLEAN NOT NULL
);

CREATE TABLE action (
    Id  SERIAL PRIMARY KEY,
    IdLetter INTEGER REFERENCES letter NOT NULL,
    Name VARCHAR(32) NOT NULL,
    Description VARCHAR(64),
    Positivity BOOLEAN NOT NULL
);


CREATE TABLE elf_status (
    Id  SERIAL PRIMARY KEY,
    IdElf INTEGER REFERENCES elf,
    Time timestamp NOT NULL,
    Rest BOOLEAN NOT NULL
);

CREATE TABLE gift_status (
    Id  SERIAL PRIMARY KEY,
    IdGift INTEGER REFERENCES gift,
    Time timestamp NOT NULL,
    Approval BOOLEAN NOT NULL,
    Status_location VARCHAR(16) CHECK ( Status_location IN ('in letter', 'in storage',
                                                            'in production', 'in delivery') )
);

CREATE TABLE storage (
  CellNumber SERIAL PRIMARY KEY,
  IdGift INTEGER REFERENCES gift
);

CREATE TABLE delivery (
    Id  SERIAL PRIMARY KEY,
    IdGift INTEGER REFERENCES gift,
    IdChild INTEGER REFERENCES child,
    IdAddress INTEGER REFERENCES address,
    Place VARCHAR(32) CHECK ( Place IN ('under the tree', 'in socks', 'on the windowsill',
                                        'near the fireplace', 'under the pillow') )
);


