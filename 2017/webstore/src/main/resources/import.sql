insert into product(id, name, price,  description) values (1, 'Carrots',100, 'Fresh and juicy carrots from outer space' )
insert into product(id, name, price, description) values (2, 'Tomatoes',123, 'Round and red, like sun - during blood bath sundown')

insert into cart(id, session_id) values (1, '543ASD')

insert into cart(id, session_id) values (2, 'QWE123')
insert into cart_position(id, fk_cart, fk_product, amount, unit_price) values (1, 2, 1, 3, 100)


insert into cart(id, session_id) values (3, 'ZXC321')
insert into cart_position(id, fk_cart, fk_product, amount, unit_price) values (3, 3, 1, 3, 100)
insert into cart_position(id, fk_cart, fk_product, amount, unit_price) values (4, 3, 2, 2, 123)