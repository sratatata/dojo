insert into product(id, name, description) values (1, 'Carrots', 'Fresh and juicy carrots from outer space' )
insert into product(id, name, description) values (2, 'Tomatoes', 'Round and red, like sun - during blood bath sundown')

insert into cart(id, session_id) values (1, '543ASD')

insert into cart(id, session_id) values (2, 'QWE123')
insert into cart_position(id, fk_cart, fk_product, amount) values (1, 1, 1, 3)