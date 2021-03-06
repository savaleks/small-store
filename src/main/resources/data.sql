INSERT INTO user(email, is_enabled, first_name, last_name, password, role, username, date, contact_number)
values ('admin@mail.com',1,'admin','admin', '$12$rsbXuaMWQMSUjMQlqTAay.uzYxzNTefrTos0LAd0pgMwAyMCnNfCu', 'ADMIN', 'admin', CURRENT_TIMESTAMP(),'123456789');
INSERT INTO user(email, is_enabled, first_name, last_name, password, role, username, date, contact_number)
values ('alex@mail.com',1,'alex','sky', '$12$rsbXuaMWQMSUjMQlqTAay.uzYxzNTefrTos0LAd0pgMwAyMCnNfCu', 'USER', 'alex', CURRENT_TIMESTAMP(),'11111111');
INSERT INTO user(email, is_enabled, first_name, last_name, password, role, username, date, contact_number)
values ('al@mail.com',0,'alex','johnson', '$12$rsbXuaMWQMSUjMQlqTAay.uzYxzNTefrTos0LAd0pgMwAyMCnNfCu', 'SUPPLIER', 'john', CURRENT_TIMESTAMP(),'132412412');

INSERT INTO product(is_active, brand, category_id, code, description, name, purchases, quantity, supplier_id, unit_price, views)
values (1,'sony', '1','wf2342','some text','sony Nano','3','55','2','400.87',0);
INSERT INTO product(is_active, brand, category_id, code, description, name, purchases, quantity, supplier_id, unit_price, views)
values (1,'mitsubishi', '1','wf42','some text','hitachi','0','51','2','410.87',0);
INSERT INTO product(is_active, brand, category_id, code, description, name, purchases, quantity, supplier_id, unit_price, views)
values (1,'LG', '2','er2342','some text','lg micro','0','17','3','250.87',0);
INSERT INTO product(is_active, brand, category_id, code, description, name, purchases, quantity, supplier_id, unit_price, views)
values (1,'sharp', '3','w342','some text','sharp kugo','0','15','2','440.87',0);
INSERT INTO product(is_active, brand, category_id, code, description, name, purchases, quantity, supplier_id, unit_price, views)
values (1,'nokia', '2','er2342','some text','nokia luna','0','5','3','500.87',0);
INSERT INTO product(is_active, brand, category_id, code, description, name, purchases, quantity, supplier_id, unit_price, views)
values (0,'samsung', '2','wf289','some text','samsung lg12','0','45','1','780.87',0);

INSERT INTO category(is_active, description, image_url, name)
values (1, 'some text','pic222','Phones');
INSERT INTO category(is_active, description, image_url, name)
values (1, 'some text','pic122','TV');
INSERT INTO category(is_active, description, image_url, name)
values (1, 'some text','pic232','Cameras');
INSERT INTO category(is_active, description, image_url, name)
values (0, 'some text','pic282','MicroCD');

INSERT INTO role(id, name)
values (1, 'ROLE_USER');
INSERT INTO role(id, name)
values (2, 'ROLE_SUPPLIER');
INSERT INTO role(id, name)
values (3, 'ROLE_ADMIN');

INSERT INTO user_roles(users_id, roles_id)
values (1, 3);

INSERT INTO cart_line(id, is_available, buying_price, cart_id, product_count, total, product_id)
values (1, 1, 250.12, 1, 2, 500.24, 3);
