INSERT INTO dealer (id, name, email, representative_name) VALUES
(1, 'BMW Center', 'bmw_center@mail.com', 'Ochen Glavniy Dyadya'),
(2, 'JDM Center', 'japan_domestic_market@mail.com', 'Hayao Miyazaki');


INSERT INTO owner (id, full_name, phone_number, email, dealer_id) VALUES
(1, 'Roman Zhul', '79608366601', 'romanzhul@hotmail.com', 1),
(2, 'Evgeny Onegin', '79999999999', 'golub@gmail.com', 2),
(3, 'Prosto Grazhdanin', '79603253167', 'baza@mail.ru', 2);


INSERT INTO car (id, number, manufacture_date, owner_id) VALUES
(1, '1111', '2022-01-01', 1),
(2, '2222', '2022-01-02', 3);
