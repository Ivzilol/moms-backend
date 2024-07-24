insert into construction_sites (id, construction_number, construction_name)
values (1, '18.23.1', 'Жилищна сграда В'),
       (2, '18.24.1', 'Кауфланд Горубляне'),
       (3, '1.11.5', 'Цех за преработка на метали'),
       (4, '18.25.2', 'Кауфланд Малинова Долина'),
       (5, '5.4.8', 'Къща Бояна'),
       (6, '2.25.4', 'Жилищна сграда SoHome'),
       (7, '101.54.8', 'Склад за храни'),
       (8, '2.11.9', 'Цех за панели');

insert into orders (id, delivery_date, email, material_type, order_date, order_description, order_number, order_status, specification_file_url, construction_site_id)
values  (1, '2024-09-05 14:57:57.000000', 'test@abv.bg', 'INSULATION', '2024-07-05 14:58:04.000000', 'Sample order description', 1, 'CREATED', 'https://test.dropbox.com', 1),
        (2, '2024-09-05 14:57:57.000000', 'test@abv.bg', 'METAL', '2024-07-05 14:58:04.000000', 'Sample order description', 2, 'CREATED', 'https://test.dropbox.com', 2),
        (3, '2024-09-05 14:57:57.000000', 'test@abv.bg', 'FASTENERS', '2024-07-05 14:58:04.000000', 'Sample order description', 34, 'CREATED', 'https://test.dropbox.com', 2),
        (4, '2024-09-05 14:57:57.000000', 'test@abv.bg', 'REBAR', '2024-07-05 14:58:04.000000', 'Sample order description', 4, 'CREATED', 'https://test.dropbox.com', 3),
        (5, '2024-09-05 14:57:57.000000', 'test@abv.bg', 'SERVICE', '2024-07-05 14:58:04.000000', 'Sample order description', 5, 'CREATED', 'https://test.dropbox.com', 2),
        (6, '2024-09-05 14:57:57.000000', 'test@abv.bg', 'TRANSPORT', '2024-07-05 14:58:04.000000', 'Sample order description', 6, 'CREATED', 'https://test.dropbox.com', 2),
        (7, '2024-09-05 14:57:57.000000', 'test@abv.bg', 'GALVANIZED_SHEET', '2024-07-05 14:58:04.000000', 'Sample order description', 22, 'CREATED', 'https://test.dropbox.com', 2),
        (8, '2024-09-05 14:57:57.000000', 'test@abv.bg', 'PANELS', '2024-07-05 14:58:04.000000', 'Sample order description', 8, 'CREATED', 'https://test.dropbox.com', 2);

insert into services (id, description, quantity, specification_file_url, order_id)
values  (1, 'Армировка на стени', 10, null, 5),
        (2, 'Къртене', 5, null, 5);

insert into transports (id, description, quantity, specification_file_url, truck, max_length_in_centimeters, weight_in_kg, order_id)
values  (1, 'Камион за превоз на материали', 5, null, null, 5000, 9800, 6),
        (2, 'Доставка на панели', 4, null, null, 11000, 11000, 6),
        (3, 'Извозване на отпадъци', 1, null, null, 15000, 18000, 6);


insert into fastener (id, admin_note, description, material_status, quantity, specification_file_url, clazz, diameter, length, model, type, order_id)
values  (2, 'Sample note', 'Цолска резба', 'APPROVED', 10, 'https://testURL.com', '10.9', 'Сегментен анкер тип  аналог на  HILTI HST3', 120, 'ISO 7089', 'Сегментен анкер тип  аналог на  HILTI HST3', 3),
        (3, 'Не могат да се доставят до края на седмицата', 'Иноксови да са', 'NOT_APPROVED', 20, 'https://testURL.com', '10.9', 'Сегментен анкер тип  аналог на  HILTI HST3', 140, 'ISO 4032', 'Сегментен анкер тип  аналог на  HILTI HST3', 3),
        (4, 'Изчакваме отговор от Мултиком', 'да са на HILTI', 'PENDING', 10, 'https://testURL.com', '10.9', 'Сегментен анкер тип  аналог на  HILTI HST3', 120, 'ISO 7089', 'Сегментен анкер тип  аналог на  HILTI HST3', 3),
        (5, 'Sample note', 'Цолска резба', 'APPROVED', 10, 'https://testURL.com', '10.9', 'Сегментен анкер тип  аналог на  HILTI HST3', 100, 'ISO 4032', 'Сегментен анкер тип  аналог на  HILTI HST3', 3),
        (6, 'Sample note', 'Иноксови да са', 'NOT_APPROVED', 3, 'https://testURL.com', '8.8', 'Шпилка М20', 1000, 'DIN 976', 'Шпилка М20', 3),
        (7, 'Sample note', 'Цолска резба', 'PENDING', 15, 'https://testURL.com', '8.8', 'Болт М12х55', 55, 'DIN 931', 'Болт М12х55', 3),
        (8, 'Sample note', 'Иноксови да са', 'APPROVED', 30, 'https://testURL.com', '8.8', 'Болт М16х70', 70, 'DIN 931', 'Болт М16х70', 3),
        (9, 'Sample note', 'да са на HILTI', 'NOT_APPROVED', 200, 'https://testURL.com', '8.8', 'Болт М20х70', 70, 'DIN 931', 'Болт М20х70', 3),
        (10, 'Sample note', 'Цолска резба', 'PENDING', 10, 'https://testURL.com', '10.9', 'Болт М20х80', 80, 'DIN 931', 'Болт М20х80', 3),
        (11, 'Sample note', 'Иноксови да са', 'APPROVED', 10, 'https://testURL.com', '8.8', 'Болт М30х90', 90, 'DIN 931', 'Болт М30х90', 3),
        (12, 'Sample note', 'да са на HILTI', 'NOT_APPROVED', 30, 'https://testURL.com', '10.9', 'Подложна шайба М12', 55, 'ISO 7089', 'Подложна шайба М12', 3),
        (13, 'Sample note', 'Цолска резба', 'PENDING', 15, 'https://testURL.com', '8', 'Гайка М12', 70, 'ISO 4032', 'Гайка М12', 3),
        (14, 'Sample note', 'Иноксови да са', 'APPROVED', 60, 'https://testURL.com', '10.9', 'Подложна шайба М16', 70, 'ISO 7089', 'Подложна шайба М16', 3),
        (15, 'Sample note', 'да са на HILTI', 'NOT_APPROVED', 30, 'https://testURL.com', '8', 'Гайка М16', 80, 'ISO 4032', 'Гайка М16', 3),
        (16, 'Sample note', 'Цолска резба', 'PENDING', 20, 'https://testURL.com', '10.9', 'Подложна шайба М20', 90, 'ISO 7089', 'Подложна шайба М20', 3),
        (17, 'Sample note', 'Иноксови да са', 'APPROVED', 20, 'https://testURL.com', '10', 'Гайка М20', 55, 'ISO 4032', 'Гайка М20', 3),
        (18, 'Sample note', 'да са на HILTI', 'NOT_APPROVED', 420, 'https://testURL.com', '10.9', 'Подложна шайба М20', 70, 'ISO 7089', 'Подложна шайба М20', 3),
        (19, 'Sample note', 'Цолска резба', 'PENDING', 420, 'https://testURL.com', '8', 'Гайка М20', 70, 'ISO 4032', 'Гайка М20', 3),
        (20, 'Sample note', 'Иноксови да са', 'APPROVED', 20, 'https://testURL.com', '10.9', 'Подложна шайба М30', 80, 'ISO 7089', 'Подложна шайба М30', 3),
        (21, 'Sample note', 'да са на HILTI', 'NOT_APPROVED', 20, 'https://testURL.com', '8', 'Гайка М30', 90, 'ISO 4032', 'Гайка М30', 3);



insert into galvanised_sheets (id, admin_note, description, material_status, quantity, specification_file_url, area_in_square_meters, max_length_in_centimeters, type, order_id, area, max_length)
values  (1, null, 'междинно ниво +5.70 м', 'APPROVED', 4, 'https://uploadFileHere.com', 41, 1155, 'TR 153x0.75', 7, '89 M2', '15000 M'),
        (2, null, 'междинно ниво +5.70 м', 'APPROVED', 3, 'https://uploadFileHere.com', 28, 1065, 'TR 153x0.75', 7, '89 M2', '15000 M'),
        (3, null, 'междинно ниво +5.70 м', 'APPROVED', 7, 'https://uploadFileHere.com', 60, 980, 'TR 153x0.75', 7, '89 M2', '15000 M'),
        (4, null, 'междинно ниво +5.70 м', 'APPROVED', 13, 'https://uploadFileHere.com', 133, 1175, 'TR 153x0.75', 7, '89 M2', '15000 M'),
        (5, null, 'междинно ниво +5.70 м', 'APPROVED', 6, 'https://uploadFileHere.com', 31, 575, 'TR 153x0.75', 7, '89 M2', '15000 M'),
        (6, null, 'междинно ниво +5.70 м', 'APPROVED', 5, 'https://uploadFileHere.com', 29, 665, 'TR 153x0.75', 7, '89 M2', '15000 M'),
        (7, null, 'междинно ниво +5.70 м', 'APPROVED', 2, 'https://uploadFileHere.com', 23, 1305, 'TR 153x0.75', 7, '89 M2', '15000 M'),
        (8, null, 'междинно ниво +5.70 м', 'APPROVED', 5, 'https://uploadFileHere.com', 56, 1285, 'TR 153x0.75', 7, '89 M2', '15000 M'),
        (9, null, 'междинно ниво +5.70 м', 'APPROVED', 9, 'https://uploadFileHere.com', 80, 1020, 'TR 153x0.75', 7, '89 M2', '15000 M'),
        (10, null, 'междинно ниво +5.70 м', 'APPROVED', 3, 'https://uploadFileHere.com', 14, 510, 'TR 153x0.75', 7, '89 M2', '15000 M'),
        (11, null, 'междинно ниво +5.70 м', 'APPROVED', 2, 'https://uploadFileHere.com', 9, 470, 'TR 153x0.75', 7, '89 M2', '15000 M'),
        (12, null, 'междинно ниво +5.70 м', 'APPROVED', 3, 'https://uploadFileHere.com', 20, 740, 'TR 153x0.75', 7, '89 M2', '15000 M'),
        (13, null, 'междинно ниво +5.70 м', 'APPROVED', 4, 'https://uploadFileHere.com', 10, 270, 'TR 153x0.75', 7, '89 M2', '15000 M'),
        (14, 'sample admin note', 'покрив', 'NOT_APPROVED', 11, 'https://uploadFileHere.com', 77, 800, 'TR 153x0.75', 7, '89 M2', '15000 M'),
        (15, 'sample admin note', 'покрив', 'NOT_APPROVED', 6, 'https://uploadFileHere.com', 40, 760, 'TR 153x0.75', 7, '89 M2', '15000 M'),
        (16, 'sample admin note', 'покрив', 'NOT_APPROVED', 1, 'https://uploadFileHere.com', 11, 1175, 'TR 153x0.75', 7, '89 M2', '15000 M'),
        (17, 'sample admin note', 'покрив', 'NOT_APPROVED', 1, 'https://uploadFileHere.com', 11, 1250, 'TR 153x0.75', 7, '89 M2', '15000 M'),
        (18, 'sample admin note', 'покрив', 'NOT_APPROVED', 2, 'https://uploadFileHere.com', 20, 1100, 'TR 153x0.75', 7, '89 M2', '15000 M'),
        (19, 'sample admin note', 'покрив', 'NOT_APPROVED', 2, 'https://uploadFileHere.com', 15, 860, 'TR 153x0.75', 7, '89 M2', '15000 M'),
        (20, 'sample admin note', 'покрив', 'NOT_APPROVED', 1, 'https://uploadFileHere.com', 6, 620, 'TR 153x0.75', 7, '89 M2', '15000 M'),
        (21, 'sample admin note', 'покрив', 'NOT_APPROVED', 3, 'https://uploadFileHere.com', 15, 570, 'TR 153x0.75', 7, '89 M2', '15000 M'),
        (22, 'sample admin note', 'покрив', 'NOT_APPROVED', 2, 'https://uploadFileHere.com', 7, 370, 'TR 153x0.75', 7, '89 M2', '15000 M'),
        (23, 'sample admin note', 'покрив', 'NOT_APPROVED', 2, 'https://uploadFileHere.com', 6, 330, 'TR 153x0.75', 7, '89 M2', '15000 M');



insert into metals (id, description, quantity, specification_file_url, total'_wei'ght_in_kg, order_id)
values  (1, 'Ъглов профил 200 х 200 х 16', 2, null, 5, 2),
        (2, 'Ъглов профил 200 х 200 х 16', 33, null, 5, 2),
        (3, 'Ъглов профил 200 х 200 х 16', 25, null, 55, 2),
        (4, 'Ъглов профил 200 х 200 х 16', 33, null, 55, 2),
        (5, 'Ъглов профил 200 х 200 х 16', 11, null, 4, 2),
        (6, 'Ъглов профил 200 х 200 х 16', 1, null, 1, 2);



insert into panels (id, description, quantity, specification_file_url, back_sheet_thickness_in_mm, front_sheet_thickness_in_mm, color, length_in_centimeters, total_thickness_in_mm, type, width_in_centimeters, order_id)
values  (1, null, null, null, 1, 1.5, 'RAL 3000', 1200, 100, null, 90, 8),
        (2, null, null, 'скрит монтаж', 1, 1.5, 'RAL 3000', 1200, 50, 'скрит монтаж', 90, 8),
        (3, null, null, null, 1, 1.5, 'RAL 3000', 1200, 70, null, 90, 8),
        (4, null, null, 'oткрит монтаж', 1, 1.5, 'RAL 3000', 1200, 100, 'oткрит монтаж', 90, 8),
        (5, null, null, 'открит монтаж/ хладилен', 1, 1.5, 'RAL 3000', 1200, 80, 'открит монтаж/ хладилен', 90, 8),
        (6, null, null, null, 1, 1.5, 'RAL 3000', 1200, 100, null, 90, 8);


insert into insulation (id, description, quantity, specification_file_url, thickness_in_mm, type, order_id)
values  (1, 'черна', 1, null, 8, 'Тръбна изолация 6 x 08', 1),
        (2, 'черна', 3, null, 10, 'Тръбна изолация 6 x 10', 1),
        (3, 'черна', 4, null, 12, 'Тръбна изолация 6 x 12', 1),
        (4, 'черна', 6, null, 15, 'Тръбна изолация 6 x 15', 1),
        (5, 'черна', 5, null, 18, 'Тръбна изолация 6 x 18', 1),
        (6, 'черна', 8, null, 20, 'Тръбна изолация 6 x 20', 1),
        (7, 'черна', 7, null, 22, 'Тръбна изолация 6 x 22', 1),
        (8, 'черна', 8, null, 25, 'Тръбна изолация 6 x 25', 1);


insert into rebars (id, description, quantity, specification_file_url, max_length_in_centimeters, weight_in_kg, order_id)
values  (1, null, 528, null, 670, 1.208, 4),
        (2, null, 440, null, 395, 1.578, 4),
        (3, null, 440, null, 188, 0.395, 4),
        (4, null, 935, null, 59, 0.395, 4),
        (5, null, 440, null, 395, 1.208, 4);

