DROP TYPE IF EXISTS activitytype;

/* CUSTOM TYPES */
CREATE TYPE activityType AS ENUM (
    'job_created',
    'job_status_changed',
    'new_file_added',
    'update'
    );
/* END CUSTOM TYPES */

CREATE TABLE IF NOT EXISTS Squad
(
    id   serial UNIQUE PRIMARY KEY,
    name varchar(255) UNIQUE NOT NULL
);

CREATE TABLE IF NOT EXISTS Role
(
    id   serial UNIQUE PRIMARY KEY,
    name varchar(255) UNIQUE NOT NULL
);

CREATE TABLE IF NOT EXISTS CustomerType
(
    id   serial UNIQUE PRIMARY KEY,
    name varchar(255) UNIQUE NOT NULL
);

CREATE TABLE IF NOT EXISTS Warehouse
(
    id   serial UNIQUE PRIMARY KEY,
    name varchar(255) NOT NULL
);

CREATE TABLE IF NOT EXISTS Address
(
    id         serial UNIQUE NOT NULL,
    title      varchar,
    context    varchar       NOT NULL,
    region     varchar       NOT NULL,
    district   varchar       NOT NULL,
    directions varchar,
    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS Status
(
    id    serial UNIQUE  NOT NULL,
    name  varchar UNIQUE NOT NULL,
    color varchar,
    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS Customer
(
    id                serial UNIQUE  NOT NULL,
    name              varchar,
    surname           varchar,
    customerTypeId     int            NOT NULL,
    phone             varchar UNIQUE NOT NULL,
    email             varchar UNIQUE NOT NULL,
    companyTitle      varchar,
    taxNumber         varchar(255),
    taxAdministration varchar(255),
    addressId         int,
    CONSTRAINT fk_address
        FOREIGN KEY (addressId)
            REFERENCES Address (id)
            ON UPDATE CASCADE
            ON DELETE CASCADE,
    CONSTRAINT fk_CustomerType
        FOREIGN KEY (customerTypeId)
            REFERENCES CustomerType (id)
            ON UPDATE CASCADE,
    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS Employee
(
    id       serial UNIQUE PRIMARY KEY,
    name     varchar        NOT NULL,
    surname  varchar        NOT NULL,
    phone    varchar UNIQUE NOT NULL,
    color    varchar,
    password varchar        NOT NULL,
    roleId   int            NOT NULL,
    FOREIGN KEY (roleId) REFERENCES Role (id)
);

CREATE TABLE IF NOT EXISTS Job
(
    id           serial UNIQUE NOT NULL,
    creationDate timestamp     NOT NULL DEFAULT NOW(),
    description  varchar       NOT NULL,
    date         timestamp     NOT NULL,
    statusId     int           NOT NULL DEFAULT 1,
    ownerId      int           NOT NULL DEFAULT 1,
    customerId   int           NOT NULL,
    FOREIGN KEY (statusId)
        REFERENCES Status (id)
        ON UPDATE CASCADE,
    FOREIGN KEY (ownerId)
        REFERENCES Employee (id)
        ON UPDATE CASCADE,
    FOREIGN KEY (customerId)
        REFERENCES Customer (id)
        ON UPDATE CASCADE,
    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS Product
(
    id            serial UNIQUE PRIMARY KEY,
    name          varchar NOT NULL,
    serialNumber  varchar UNIQUE,
    unit          varchar NOT NULL,
    purchasePrice int     NOT NULL,
    salePrice     int     NOT NULL,
    vat           int     NOT NULL,
    quantity      int,
    warehouseId   int,
    FOREIGN KEY (warehouseId) REFERENCES Warehouse (id)
);

CREATE TABLE IF NOT EXISTS Activity
(
    id      serial UNIQUE NOT NULL,
    date    timestamp     NOT NULL DEFAULT NOW(),
    msg     varchar       NOT NULL,
    info    varchar,
    type    activityType  NOT NULL,
    ownerId int           NOT NULL,
    jobId   int           NOT NULL,
    FOREIGN KEY (ownerId)
        REFERENCES Employee (id)
        ON UPDATE CASCADE,
    FOREIGN KEY (jobId)
        REFERENCES Job (id)
        ON UPDATE CASCADE,
    PRIMARY KEY (id)
);

/* MANY TO MANY TABLES */
CREATE TABLE IF NOT EXISTS JobXProduct
(
    jobId     INT NOT NULL,
    productId INT NOT NULL,
    FOREIGN KEY (jobId)
        REFERENCES Job (id)
        ON UPDATE CASCADE
        ON DELETE CASCADE,
    FOREIGN KEY (productId)
        REFERENCES Product (id)
        ON UPDATE CASCADE
        ON DELETE CASCADE,
    PRIMARY KEY (jobId, productId)
);

CREATE TABLE IF NOT EXISTS JobXEmployee
(
    jobId      INT NOT NULL,
    employeeId INT NOT NULL,
    FOREIGN KEY (jobId)
        REFERENCES Job (id)
        ON UPDATE CASCADE
        ON DELETE CASCADE,
    FOREIGN KEY (employeeId)
        REFERENCES Employee (id)
        ON UPDATE CASCADE
        ON DELETE CASCADE,
    PRIMARY KEY (jobId, employeeId)
);

CREATE TABLE IF NOT EXISTS EmployeeXSquad
(
    employeeId INT NOT NULL,
    squadId    INT NOT NULL,
    FOREIGN KEY (employeeId)
        REFERENCES Employee (id)
        ON UPDATE CASCADE
        ON DELETE CASCADE,
    FOREIGN KEY (squadId)
        REFERENCES Squad (id)
        ON UPDATE CASCADE
        ON DELETE CASCADE,
    PRIMARY KEY (employeeId, squadId)
);

CREATE TABLE IF NOT EXISTS EmployeeXWarehouse
(
    employeeId  INT NOT NULL,
    warehouseId INT NOT NULL,
    FOREIGN KEY (employeeId)
        REFERENCES Employee (id)
        ON UPDATE CASCADE
        ON DELETE CASCADE,
    FOREIGN KEY (warehouseId)
        REFERENCES Warehouse (id)
        ON UPDATE CASCADE
        ON DELETE CASCADE,
    PRIMARY KEY (employeeId, warehouseId)
);
/* END MANY TO MANY TABLES */


/* ACTIVITY FUNCTIONS
CREATE OR REPLACE FUNCTION add_activity()
    RETURNS TRIGGER
    LANGUAGE plpgsql
AS
$$
DECLARE
    owner         varchar;
    activity_type activityType;
    activity_msg  varchar;
    activity_info varchar;
BEGIN
    owner := (SELECT name
              FROM Employee
              WHERE Employee.id = new.ownerId
    );

    IF old IS NULL THEN
        activity_type := 'job_created';
        activity_msg := (SELECT FORMAT('%s was created a new job', owner));
    ELSEIF old.statusId <> new.statusId THEN
        activity_type := 'job_status_changed';
        activity_msg := (SELECT FORMAT('%s was changed the job status', owner));
        activity_info := (SELECT FORMAT('New Status: %s', (
            SELECT name
            FROM Status
            WHERE id = new.statusId
        )));
    ELSEIF old.files <> new.files THEN
        activity_type := 'new_file_added';
        activity_msg := (SELECT FORMAT('%s added a new file', owner));
        activity_info := '%', new.files[ARRAY_UPPER(new.files, 1)];
    ELSE
        activity_type := 'update';
        activity_msg := (SELECT FORMAT('%s made an update', owner));
    END IF;

    INSERT INTO Activity (date, msg, info, type, ownerId, jobId)
    VALUES (NOW(), activity_msg, activity_info, activity_type, new.ownerId, new.id);

    RETURN NEW;
END;
$$;
/* END ACTIVITY FUNCTIONS */


/* ACTIVITY TRIGGERS */
CREATE TRIGGER __add_activity__
    AFTER INSERT OR UPDATE
    ON Job
    FOR EACH ROW
EXECUTE PROCEDURE add_activity();
/* END ACTIVITY TRIGGERS */
*/
