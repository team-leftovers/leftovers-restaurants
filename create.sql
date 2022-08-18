/* Base Tables */
/* =========== */
CREATE TABLE IF NOT EXISTS account(
    id INT AUTO_INCREMENT NOT NULL,
    first_name VARCHAR(255), /* These two are nullible for admin accounts */
    last_name VARCHAR(255),
    email VARCHAR(255) NOT NULL,
    phone_no VARCHAR(15 /* Max Phone Number Length */),
    hashed_password VARCHAR(255) NOT NULL,
    type ENUM('R', 'D', 'C', 'S') NOT NULL, /* R: Restaurant Admin, D: Driver, C: Customer, S: Site Admin */
    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS address(
    id INT AUTO_INCREMENT NOT NULL,
    latitude DOUBLE NOT NULL,
    longitude DOUBLE NOT NULL,
    zip_code INT NOT NULL,
    country VARCHAR(255) NOT NULL,
	state VARCHAR(2) NOT NULL,
    street_address VARCHAR(255) NOT NULL,
    house_number VARCHAR(5),
    unit_number VARCHAR(5),
    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS discount(
    id INT AUTO_INCREMENT NOT NULL,
    code VARCHAR(255) NOT NULL,
    percent DECIMAL NOT NULL,
    value DECIMAL NOT NULL,
    PRIMARY KEY(id)
);

CREATE TABLE IF NOT EXISTS tag(
    id INT AUTO_INCREMENT NOT NULL,
    name VARCHAR(255) NOT NULL,
    PRIMARY KEY(id)
);

/* Restaurant Tables */
/* ================= */
CREATE TABLE IF NOT EXISTS restaurant(
    id INT AUTO_INCREMENT NOT NULL,
    name VARCHAR(255) NOT NULL,
    address_id INT NOT NULL,
    phone_no VARCHAR(15) NOT NULL,
    website VARCHAR(255),
    open_time TIME NOT NULL,
    close_time TIME NOT NULL,
    rating DECIMAL,
    rating_count INT,
    PRIMARY KEY(id),
    FOREIGN KEY (address_id) REFERENCES address(id)
);

CREATE TABLE IF NOT EXISTS restaurant_tag(
    restaurant_id INT NOT NULL,
    tag_id INT NOT NULL,
    CONSTRAINT pk_rtag PRIMARY KEY (restaurant_id, tag_id),
    FOREIGN KEY (restaurant_id) REFERENCES restaurant(id)
		ON DELETE CASCADE
		ON UPDATE CASCADE,
    FOREIGN KEY (tag_id) REFERENCES tag(id)
);

CREATE TABLE IF NOT EXISTS food(
    id INT AUTO_INCREMENT NOT NULL,
    name VARCHAR(255) NOT NULL,
    restaurant_id INT NOT NULL,
    price DECIMAL NOT NULL,
    description TEXT,
    PRIMARY KEY (id),
    FOREIGN KEY (restaurant_id) REFERENCES restaurant(id)
		ON DELETE CASCADE
		ON UPDATE CASCADE
);

CREATE TABLE IF NOT EXISTS food_tag(
    food_id INT NOT NULL,
    tag_id INT NOT NULL,
    CONSTRAINT pk_ptag PRIMARY KEY (food_id, tag_id),
    FOREIGN KEY (food_id) REFERENCES food(id)
		ON DELETE CASCADE
		ON UPDATE CASCADE,
    FOREIGN KEY (tag_id) REFERENCES tag(id)
);

/* Account Tables */
/* ============== */

CREATE TABLE IF NOT EXISTS restaurant_admin(
    account_id INT NOT NULL,
    restaurant_id INT NOT NULL,
    PRIMARY KEY (account_id),
    FOREIGN KEY (account_id) REFERENCES account(id),
    FOREIGN KEY (restaurant_id) REFERENCES restaurant(id)
);

CREATE TABLE IF NOT EXISTS driver(
    account_id INT NOT NULL,
    license_plate VARCHAR(255) NOT NULL,
    rating DECIMAL NOT NULL,
    PRIMARY KEY (account_id),
    FOREIGN KEY (account_id) REFERENCES account(id)
);

CREATE TABLE IF NOT EXISTS customer(
    account_id INT NOT NULL,
    address_id INT NOT NULL,
    PRIMARY KEY (account_id),
    FOREIGN KEY (account_id) REFERENCES account(id),
    FOREIGN KEY (address_id) REFERENCES address(id)
);

CREATE TABLE IF NOT EXISTS site_admin(
    account_id INT NOT NULL,
    PRIMARY KEY (account_id),
    FOREIGN KEY (account_id) REFERENCES account(id)
);

CREATE TABLE IF NOT EXISTS payment_method(
    id INT AUTO_INCREMENT NOT NULL,
    customer_id INT NOT NULL,
    card_no VARCHAR(255) NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (customer_id) REFERENCES customer(account_id)
);

/* Order Tables */
/* ============ */

CREATE TABLE IF NOT EXISTS order(
    id INT AUTO_INCREMENT NOT NULL,
    driver_id INT NOT NULL,
    customer_id INT NOT NULL,
    restaurant_id INT NOT NULL,
    discount_id INT,
    status ENUM('pending', 'accepted', 'working', 'waiting', 'delivery', 'delivered', 'cancelled', 'error') NOT NULL,
    total_price DECIMAL NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (driver_id) REFERENCES driver(account_id),
    FOREIGN KEY (customer_id) REFERENCES customer(account_id),
    FOREIGN KEY (restaurant_id) REFERENCES restaurant(id),
    FOREIGN KEY (discount_id) REFERENCES discount(id)
);

CREATE TABLE IF NOT EXISTS order_item(
    order_id INT NOT NULL,
    food_id INT NOT NULL,
    quantity INT NOT NULL,
    price DECIMAL NOT NULL,
    additional_instructions TEXT,
    driver_rating INT,
    food_rating INT,
    CONSTRAINT pk_order_item PRIMARY KEY (order_id, food_id),
    FOREIGN KEY (order_Id) REFERENCES order(id),
    FOREIGN KEY (food_id) REFERENCES food(id)
);

CREATE TABLE IF NOT EXISTS transaction(
    id INT AUTO_INCREMENT NOT NULL,
    order_id INT NOT NULL,
    payment_id INT NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (order_id) REFERENCES order(id),
    FOREIGN KEY (payment_id) REFERENCES payment_method(id)
);