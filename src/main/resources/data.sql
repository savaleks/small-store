INSERT INTO user(email, is_enabled, first_name, last_name, password, role, username, date, contact_number)
values ('admin@mail.com',0,'admin','admin', '123', 'ADMIN', 'admin', CURRENT_TIMESTAMP(),'123456789');
INSERT INTO user(email, is_enabled, first_name, last_name, password, role, username, date, contact_number)
values ('alex@mail.com',0,'alex','sky', '123', 'USER', 'alex', CURRENT_TIMESTAMP(),'11111111');
INSERT INTO user(email, is_enabled, first_name, last_name, password, role, username, date, contact_number)
values ('al@mail.com',0,'alex','adam', '123', 'SUPPLIER', 'alex123', CURRENT_TIMESTAMP(),'132412412');

INSERT INTO product(is_active, brand, category_id, code, description, name, purchases, quantity, supplier_id, unit_price, views)
values (1,'sony', '1','wf2342','some text','sony Nano','3','55','2','400.87',0);
INSERT INTO product(is_active, brand, category_id, code, description, name, purchases, quantity, supplier_id, unit_price, views)
values (1,'mitsubishi', '1','wf2342','some text','hitachi','0','51','2','410.87',0);
INSERT INTO product(is_active, brand, category_id, code, description, name, purchases, quantity, supplier_id, unit_price, views)
values (1,'LG', '2','wf2ew42','some text','lg micro','0','17','3','250.87',0);
INSERT INTO product(is_active, brand, category_id, code, description, name, purchases, quantity, supplier_id, unit_price, views)
values (1,'sharp', '3','w342','some text','sharp kugo','0','15','2','440.87',0);
INSERT INTO product(is_active, brand, category_id, code, description, name, purchases, quantity, supplier_id, unit_price, views)
values (1,'nokia', '2','er2342','some text','nokia luna','0','5','3','500.87',0);
INSERT INTO product(is_active, brand, category_id, code, description, name, purchases, quantity, supplier_id, unit_price, views)
values (0,'samsung', '2','wf28942','some text','samsung lg12','0','45','1','780.87',0);

INSERT INTO category(is_active, description, image_url, name)
values (1, 'some text','pic222','Phones');
INSERT INTO category(is_active, description, image_url, name)
values (1, 'some text','pic122','TV');
INSERT INTO category(is_active, description, image_url, name)
values (1, 'some text','pic232','Cameras');
INSERT INTO category(is_active, description, image_url, name)
values (0, 'some text','pic282','MicroCD');
