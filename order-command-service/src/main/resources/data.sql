insert into construction_sites (id, construction_number, name)
values (1, '18.23.1', 'Жилищна сграда В'),
       (2, '18.24.1', 'Кауфланд Горубляне'),
       (3, '1.11.5', 'Цех за преработка на метали'),
       (4, '18.25.2', 'Кауфланд Малинова Долина'),
       (5, '5.4.8', 'Къща Бояна'),
       (6, '2.25.4', 'Жилищна сграда SoHome'),
       (7, '101.54.8', 'Склад за храни'),
       (8, '2.11.9', 'Цех за панели');

insert into orders (id, delivery_date, order_date, order_description, order_number, order_status, username, construction_site_id)
values  (1, '2024-09-05 14:57:57.000000', '2024-07-05 14:58:04.000000', 'sdfgsdgsdfg', 1, 'CREATED', 'test@abv.bg', 1),
        (2, '2024-09-05 14:57:57.000000', '2024-07-05 14:58:04.000000', 'sdfgsdgsdfg', 2, 'CREATED', 'test@abv.bg', 2),
        (3, '2024-09-05 14:57:57.000000', '2024-07-05 14:58:04.000000', 'sdfgsdgsdfg', 34, 'CREATED', 'test@abv.bg', 2),
        (4, '2024-09-05 14:57:57.000000', '2024-07-05 14:58:04.000000', 'sdfgsdgsdfg', 4, 'CREATED', 'test@abv.bg', 3),
        (5, '2024-09-05 14:57:57.000000', '2024-07-05 14:58:04.000000', 'sdfgsdgsdfg', 5, 'CREATED', 'test@abv.bg', 2),
        (6, '2024-09-05 14:57:57.000000', '2024-07-05 14:58:04.000000', 'sdfgsdgsdfg', 6, 'CREATED', 'test@abv.bg', 2);

insert into services (id, description)
values (1, 'Армировка на стени'),
       (2, 'Къртене');

insert into transports (id, description)
values (1, 'Камион за превоз на материали'),
       (2, 'Доставка на панели'),
       (3, 'Извозване на отпадъци');


insert into fastener (id, description, quantity, specification_file_url, clazz, diameter, length_in_mm, model, type, order_id)
values  (2, null, 10, null, null, 'Сегментен анкер тип  аналог на  HILTI HST3', 120, null, 'Сегментен анкер тип  аналог на  HILTI HST3', 1),
        (3, null, 20, null, null, 'Сегментен анкер тип  аналог на  HILTI HST3', 140, null, 'Сегментен анкер тип  аналог на  HILTI HST3', 1),
        (4, null, 10, null, null, 'Сегментен анкер тип  аналог на  HILTI HST3', 120, null, 'Сегментен анкер тип  аналог на  HILTI HST3', 1),
        (5, null, 10, null, null, 'Сегментен анкер тип  аналог на  HILTI HST3', 100, null, 'Сегментен анкер тип  аналог на  HILTI HST3', 1),
        (6, null, 3, null, '8.8', 'Шпилка М20', 1000, 'DIN 976', 'Шпилка М20', 1),
        (7, null, 15, null, '8.8', 'Болт М12х55', 55, 'DIN 931', 'Болт М12х55', 1),
        (8, null, 30, null, '8.8', 'Болт М16х70', 70, 'DIN 931', 'Болт М16х70', 1),
        (9, null, 200, null, '8.8', 'Болт М20х70', 70, 'DIN 931', 'Болт М20х70', 1),
        (10, null, 10, null, '10.9', 'Болт М20х80', 80, 'DIN 931', 'Болт М20х80', 1),
        (11, null, 10, null, '8.8', 'Болт М30х90', 90, 'DIN 931', 'Болт М30х90', 1),
        (12, null, 30, null, null, 'Подложна шайба М12', null, 'ISO 7089', 'Подложна шайба М12', 1),
        (13, null, 15, null, '8', 'Гайка М12', null, 'ISO 4032', 'Гайка М12', 2),
        (14, null, 60, null, null, 'Подложна шайба М16', null, 'ISO 7089', 'Подложна шайба М16', 2),
        (15, null, 30, null, '8', 'Гайка М16', null, 'ISO 4032', 'Гайка М16', 2),
        (16, null, 20, null, null, 'Подложна шайба М20', null, 'ISO 7089', 'Подложна шайба М20', 2),
        (17, null, 20, null, '10', 'Гайка М20', null, 'ISO 4032', 'Гайка М20', 2),
        (18, null, 420, null, null, 'Подложна шайба М20', null, 'ISO 7089', 'Подложна шайба М20', 2),
        (19, null, 420, null, '8', 'Гайка М20', null, 'ISO 4032', 'Гайка М20', 2),
        (20, null, 20, null, null, 'Подложна шайба М30', null, 'ISO 7089', 'Подложна шайба М30', 2),
        (21, null, 20, null, '8', 'Гайка М30', null, 'ISO 4032', 'Гайка М30', 2);



insert into galvanised_sheets (id, description, quantity, specification_file_url, area_in_square_meters, max_length_in_centimeters, type, order_id)
values  (1, 'междинно ниво +5.70 м', 4, null, 41, 1155, 'TR 153x0.75', 2),
        (2, 'междинно ниво +5.70 м', 3, null, 28, 1065, 'TR 153x0.75', 2),
        (3, 'междинно ниво +5.70 м', 7, null, 60, 980, 'TR 153x0.75', 2),
        (4, 'междинно ниво +5.70 м', 13, null, 133, 1175, 'TR 153x0.75', 2),
        (5, 'междинно ниво +5.70 м', 6, null, 31, 575, 'TR 153x0.75', 2),
        (6, 'междинно ниво +5.70 м', 5, null, 29, 665, 'TR 153x0.75', 2),
        (7, 'междинно ниво +5.70 м', 2, null, 23, 1305, 'TR 153x0.75', 2),
        (8, 'междинно ниво +5.70 м', 5, null, 56, 1285, 'TR 153x0.75', 2),
        (9, 'междинно ниво +5.70 м', 9, null, 80, 1020, 'TR 153x0.75', 2),
        (10, 'междинно ниво +5.70 м', 3, null, 14, 510, 'TR 153x0.75', 1),
        (11, 'междинно ниво +5.70 м', 2, null, 9, 470, 'TR 153x0.75', 1),
        (12, 'междинно ниво +5.70 м', 3, null, 20, 740, 'TR 153x0.75', 1),
        (13, 'междинно ниво +5.70 м', 4, null, 10, 270, 'TR 153x0.75', 1),
        (14, 'покрив', 11, null, 77, 800, 'TR 153x0.75', 1),
        (15, 'покрив', 6, null, 40, 760, 'TR 153x0.75', 1),
        (16, 'покрив', 1, null, 11, 1175, 'TR 153x0.75', 1),
        (17, 'покрив', 1, null, 11, 1250, 'TR 153x0.75', 3),
        (18, 'покрив', 2, null, 20, 1100, 'TR 153x0.75', 3),
        (19, 'покрив', 2, null, 15, 860, 'TR 153x0.75', 3),
        (20, 'покрив', 1, null, 6, 620, 'TR 153x0.75', 3),
        (21, 'покрив', 3, null, 15, 570, 'TR 153x0.75', 3),
        (22, 'покрив', 2, null, 7, 370, 'TR 153x0.75', 3),
        (23, 'покрив', 2, null, 6, 330, 'TR 153x0.75', 3);



insert into metals (id, description, quantity, specification_file_url, total_weight_in_kg, order_id)
values  (1, 'Ъглов профил 200 х 200 х 16', 2, null, 5, 1),
        (2, 'Ъглов профил 200 х 200 х 16', 33, null, 5, 2),
        (3, 'Ъглов профил 200 х 200 х 16', 25, null, 55, 2),
        (4, 'Ъглов профил 200 х 200 х 16', 33, null, 55, 1),
        (5, 'Ъглов профил 200 х 200 х 16', 11, null, 4, 1),
        (6, 'Ъглов профил 200 х 200 х 16', 1, null, 1, 2);



insert into panels (id, description, quantity, specification_file_url, back_sheet_thickness_in_mm, front_sheet_thickness_in_mm, color, length_in_centimeters, total_thickness_in_mm, type, width_in_centimeters, order_id)
values  (1, null, null, null, 1, 1.5, 'RAL 3000', 1200, 100, null, 90, 1),
        (2, null, null, 'скрит монтаж', 1, 1.5, 'RAL 3000', 1200, 50, 'скрит монтаж', 90, 1),
        (3, null, null, null, 1, 1.5, 'RAL 3000', 1200, 70, null, 90, 1),
        (4, null, null, 'oткрит монтаж', 1, 1.5, 'RAL 3000', 1200, 100, 'oткрит монтаж', 90, 1),
        (5, null, null, 'открит монтаж/ хладилен', 1, 1.5, 'RAL 3000', 1200, 80, 'открит монтаж/ хладилен', 90, 2),
        (6, null, null, null, 1, 1.5, 'RAL 3000', 1200, 100, null, 90, 3);


insert into insulation (id, description, quantity, specification_file_url, thickness_in_mm, type, order_id)
values  (1, 'черна', 1, null, 8, 'Тръбна изолация 6 x 08', 1),
        (2, 'черна', 3, null, 10, 'Тръбна изолация 6 x 10', 1),
        (3, 'черна', 4, null, 12, 'Тръбна изолация 6 x 12', 1),
        (4, 'черна', 6, null, 15, 'Тръбна изолация 6 x 15', 2),
        (5, 'черна', 5, null, 18, 'Тръбна изолация 6 x 18', 2),
        (6, 'черна', 8, null, 20, 'Тръбна изолация 6 x 20', 3),
        (7, 'черна', 7, null, 22, 'Тръбна изолация 6 x 22', 3),
        (8, 'черна', 8, null, 25, 'Тръбна изолация 6 x 25', 3);


insert into rebars (id, description, quantity, specification_file_url, max_length_in_centimeters, weight_in_kg, order_id)
values  (1, null, 528, null, 670, 1.208, 1),
        (2, null, 440, null, 395, 1.578, 1),
        (3, null, 440, null, 188, 0.395, 1),
        (4, null, 935, null, 59, 0.395, 1),
        (5, null, 440, null, 395, 1.208, 2);

