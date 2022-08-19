INSERT INTO tbl_discount (id, code, percent, value)
VALUES (1, "code1", 20, 5);
INSERT INTO tbl_discount (id, code, percent, value)
VALUES (2, "code2", 00, 50);
INSERT INTO tbl_discount (id, code, percent, value)
VALUES (3, "code3", 17, 5);
INSERT INTO tbl_discount (id, code, percent, value)
VALUES (4, "code4", 75, 0);
INSERT INTO tbl_discount (id, code, percent, value)
VALUES (5, "codecheat", 100, 1000);


INSERT INTO tbl_address (id, latitude, longitude, zip_code, country, city, state, street_address, unit_number)
VALUES (1, 77.77, 44.44, 98125, "NOT USA", "citytown", "TX", "Peasant Lane", "UN02");
INSERT INTO tbl_address (id, latitude, longitude, zip_code, country, city, state, street_address, unit_number)
VALUES (2, 22.2, 33.33, 98012, "USA", "cityplace", "OR", "6534 some St", "unit#");
INSERT INTO tbl_address (id, latitude, longitude, zip_code, country, city, state, street_address, unit_number)
VALUES (3, 5.5, 7.14, 97701, "Fakeland", "towncity", "CO", "FakeStreet", "Un55");
INSERT INTO tbl_address (id, latitude, longitude, zip_code, country, city, state, street_address, unit_number)
VALUES (4, 745, 9476, 97702, "Dumpland", "citiesburg", "PA", "DumpStreet", "Un32");
INSERT INTO tbl_address (id, latitude, longitude, zip_code, country, city, state, street_address, unit_number)
VALUES (5, 235, 56.2, 98881, "Denmark", "townsburg", "NY", "Filbert", "UN07");
INSERT INTO tbl_address (id, latitude, longitude, zip_code, country, city, state, street_address, unit_number)
VALUES (6, 27, 55, 92221, "FakeUSA", "townsplace", "UT", "232 Golden", "UN06");
INSERT INTO tbl_address (id, latitude, longitude, zip_code, country, city, state, street_address, unit_number)
VALUES (7, 623, 856.2, 93333, "FakeUSA", "townsplat", "RI", "444 Granny", "UN07");
INSERT INTO tbl_address (id, latitude, longitude, zip_code, country, city, state, street_address, unit_number)
VALUES (8, 623, 856.2, 93333, "FakeUSA", "townsplat", "RI", "444 Granny", "UN08");
INSERT INTO tbl_address (id, latitude, longitude, zip_code, country, city, state, street_address, unit_number)
VALUES (9, 623, 856.2, 93333, "FakeUSA", "townsplat", "RI", "444 Granny", "UN09");
INSERT INTO tbl_address (id, latitude, longitude, zip_code, country, city, state, street_address, unit_number)
VALUES (10, 623, 856.2, 93333, "FakeUSA", "townsplat", "RI", "444 Granny", "UN10");
INSERT INTO tbl_address (id, latitude, longitude, zip_code, country, city, state, street_address, unit_number)
VALUES (11, 623, 856.2, 93333, "FakeUSA", "townsplat", "RI", "444 Granny", "UN11");



INSERT INTO tbl_restaurant (name, address_id, phone_no, website, open_time, close_time, rating, rating_count)
VALUES ("This Garbage Heap", (SELECT id FROM tbl_address WHERE id = 1 LIMIT 1), "444-444-4444", "garbage@here.com",
        "21:00", "8:00", 2, 55);
INSERT INTO tbl_restaurant (name, address_id, phone_no, website, open_time, close_time, rating, rating_count)
VALUES ("Some Dumpster", (SELECT id FROM tbl_address WHERE id = 2 LIMIT 1), "555-666-5555", "dumpster@backalley.com",
        "9:00", "23:59", 5, 277);
INSERT INTO tbl_restaurant (name, address_id, phone_no, website, open_time, close_time, rating, rating_count)
VALUES ("Rat BBQ", (SELECT id FROM tbl_address WHERE id = 3 LIMIT 1), "333-555-8888", "Denmark@elsewhere.com", "2:55",
        "3:00", 6, 666);
INSERT INTO tbl_restaurant (name, address_id, phone_no, website, open_time, close_time, rating, rating_count)
VALUES ("Old Milk Cheese Factory", (SELECT id FROM tbl_address WHERE id = 4 LIMIT 1), "555-525-8888",
        "stank@cheese.com", "2:56", "19:00", 2, 41);
INSERT INTO tbl_restaurant (name, address_id, phone_no, website, open_time, close_time, rating, rating_count)
VALUES ("Key Depot", (SELECT id FROM tbl_address WHERE id = 5 LIMIT 1), "555-777-PLZ!", "key@foreign.com", "12:00",
        "0:00", 11, 1);
INSERT INTO tbl_restaurant (name, address_id, phone_no, website, open_time, close_time, rating, rating_count)
VALUES ("Other Garbage Heap", (SELECT id FROM tbl_address WHERE id = 6 LIMIT 1), "555-444-4444", "garbage@here.com",
        "21:00", "7:00", 2, 56);


select *
from tbl_account;
INSERT INTO tbl_account (first_name, last_name, email, phone_no, password, type)
VALUES ("Joe", "Guy", "JoeGuy@here.com", "555-444-2222", "this is a password", 3);
INSERT INTO tbl_account (first_name, last_name, email, phone_no, password, type)
VALUES ("Sally", "Guy", "SallyGuy@here.com", "555-444-2222", "SallyPassword", 2);
INSERT INTO tbl_account (first_name, last_name, email, phone_no, password, type)
VALUES ("Jim", "Guy", "JimGuy@there.com", "555-333-2222", "this is a bad pass", 2);
INSERT INTO tbl_account (first_name, last_name, email, phone_no, password, type)
VALUES ("Steve", "Smitty", "SS@notanazi.com", "555-555-2222", "ssssssssssss", 3);
INSERT INTO tbl_account (first_name, last_name, email, phone_no, password, type)
VALUES ("ONLY", "LIES", "LIES@nothere.com", "555-LIE-2222", "this is not a password", 3);

INSERT INTO tbl_customer(account_id, address_id)
VALUES ((SELECT id FROM tbl_account WHERE first_name = "Joe" LIMIT 1),
        (SELECT id FROM tbl_address WHERE unit_number = "UN08" LIMIT 1));
INSERT INTO tbl_customer(account_id, address_id)
VALUES ((SELECT id FROM tbl_account WHERE first_name = "ONLY" LIMIT 1),
        (SELECT id FROM tbl_address WHERE unit_number = "UN09" LIMIT 1));


INSERT INTO tbl_driver (account_id, license_plate, rating)
VALUES ((SELECT id FROM tbl_account WHERE id = 2 LIMIT 1), "BEE MAD", 7);
INSERT INTO tbl_driver (account_id, license_plate, rating)
VALUES ((SELECT id FROM tbl_account WHERE id = 3 LIMIT 1), "456 LDK", 2);


INSERT INTO tbl_food(id, name, restaurant_id, price, description)
VALUES (1, "Food-ish Stuff", (SELECT id FROM tbl_restaurant WHERE id = 1 LIMIT 1), 13.99, "This might be food. Maybe");
INSERT INTO tbl_food(id, name, restaurant_id, price, description)
VALUES (2, "Old Boot", (SELECT id FROM tbl_restaurant WHERE id = 1 LIMIT 1), 1.00, "Hard-boiled boot. Not tasty.");
INSERT INTO tbl_food(id, name, restaurant_id, price, description)
VALUES (3, "Skunk Cabbage", (SELECT id FROM tbl_restaurant WHERE id = 1 LIMIT 1), 5.75,
        "It''s supposed to smell that way. Really.");
INSERT INTO tbl_food(id, name, restaurant_id, price, description)
VALUES (4, "Stale Bread", (SELECT id FROM tbl_restaurant WHERE id = 2 LIMIT 1), 0.99, "At least it''s identifiable");


INSERT INTO tbl_order (driver_id, customer_id, restaurant_id, discount_id, status, total_price)
VALUES ((SELECT account_id FROM tbl_driver WHERE account_id = 2 LIMIT 1),
        (SELECT id FROM tbl_account WHERE id = 1 LIMIT 1), (SELECT id FROM tbl_restaurant WHERE id = 1 LIMIT 1),
        (SELECT id FROM tbl_discount WHERE id = 4 LIMIT 1), 4, 4.40);
INSERT INTO tbl_order (driver_id, customer_id, restaurant_id, discount_id, status, total_price)
VALUES ((SELECT account_id FROM tbl_driver WHERE account_id = 3 LIMIT 1),
        (SELECT id FROM tbl_account WHERE id = 5 LIMIT 1), (SELECT id FROM tbl_restaurant WHERE id = 2 LIMIT 1),
        (SELECT id FROM tbl_discount WHERE id = 1 LIMIT 1), 1, 15.69);

