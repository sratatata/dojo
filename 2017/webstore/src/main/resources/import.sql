insert into product(id, name, description) values (1, 'Carrots', 'Fresh and juicy carrots from outer space' )
insert into product(id, name, description) values (2, 'Tomatoes', 'Round and red, like sun - during blood bath sundown')

insert into cart(id, session_id) values (1, '543ASD')

insert into cart(id, session_id) values (2, 'QWE123')
insert into cart_position(id, fk_cart, fk_product, amount) values (1, 2, 1, 3)


insert into cart(id, session_id) values (3, 'ZXC321')
insert into cart_position(id, fk_cart, fk_product, amount) values (3, 3, 1, 3)
insert into cart_position(id, fk_cart, fk_product, amount) values (4, 3, 2, 2)