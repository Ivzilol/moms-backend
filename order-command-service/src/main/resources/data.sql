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



insert into galvanised_sheets (id, admin_note, description, material_status, quantity, specification_file_url, area, max_length, type, order_id)
values  (1, 'sample admin note', 'покрив', 'NOT_APPROVED', 1, null, '89 M2', '15000 M', 'TR 153x0.75', 7),
        (2, null, 'междинно ниво +5.70 м', 'APPROVED', 13, null, '89 M2', '15000 M', 'TR 153x0.75', 7),
        (3, null, 'междинно ниво +5.70 м', 'APPROVED', 13, null, '89 M2', '15000 M', 'TR 153x0.75', 7),
        (4, 'sample admin note', 'покрив', 'NOT_APPROVED', 1, null, '89 M2', '15000 M', 'TR 153x0.75', 7),
        (5, 'sample admin note', 'покрив', 'NOT_APPROVED', 1, null, '89 M2', '15000 M', 'TR 153x0.75', 7),
        (6, null, 'междинно ниво +5.70 м', 'APPROVED', 13, null, '89 M2', '15000 M', 'TR 153x0.75', 7),
        (7, 'sample admin note', 'покрив', 'NOT_APPROVED', 1, null, '89 M2', '15000 M', 'TR 153x0.75', 7),
        (8, null, 'междинно ниво +5.70 м', 'APPROVED', 13, null, '89 M2', '15000 M', 'TR 153x0.75', 7),
        (9, null, 'междинно ниво +5.70 м', 'APPROVED', 13, null, '89 M2', '15000 M', 'TR 153x0.75', 7),
        (10, 'sample admin note', 'покрив', 'NOT_APPROVED', 1, null, '89 M2', '15000 M', 'TR 153x0.75', 7),
        (11, 'sample admin note', 'покрив', 'NOT_APPROVED', 1, null, '89 M2', '15000 M', 'TR 153x0.75', 7),
        (12, null, 'междинно ниво +5.70 м', 'APPROVED', 13, null, '89 M2', '15000 M', 'TR 153x0.75', 7),
        (13, 'sample admin note', 'покрив', 'NOT_APPROVED', 1, null, '89 M2', '15000 M', 'TR 153x0.75', 7),
        (14, null, 'междинно ниво +5.70 м', 'APPROVED', 13, null, '89 M2', '15000 M', 'TR 153x0.75', 7),
        (15, null, 'междинно ниво +5.70 м', 'APPROVED', 13, null, '89 M2', '15000 M', 'TR 153x0.75', 7),
        (16, 'sample admin note', 'покрив', 'NOT_APPROVED', 1, null, '89 M2', '15000 M', 'TR 153x0.75', 7),
        (17, 'sample admin note', 'покрив', 'NOT_APPROVED', 1, null, '89 M2', '15000 M', 'TR 153x0.75', 7),
        (18, null, 'междинно ниво +5.70 м', 'APPROVED', 13, null, '89 M2', '15000 M', 'TR 153x0.75', 7);


insert into metals (id, admin_note, description, material_status, quantity, specification_file_url, total_weight, order_id)
values  (1, 'не може да се достави', 'Ъглов профил 200 х 200 х 16', 'NOT_APPROVED', 2, null, '5 T', 2),
        (2, 'не може да се достави', 'Ъглов профил 200 х 200 х 16', 'NOT_APPROVED', 33, null, '5 T', 2),
        (3, 'не може да се достави', 'Ъглов профил 200 х 200 х 16', 'NOT_APPROVED', 25, null, '55 T', 2),
        (4, 'не може да се достави', 'Ъглов профил 200 х 200 х 16', 'NOT_APPROVED', 33, null, '55 T', 2),
        (5, null, 'Ъглов профил 200 х 200 х 16', 'APPROVED', 11, null, '4 T', 2),
        (6, null, 'Ъглов профил 200 х 200 х 16', 'APPROVED', 1, null, '1000 KG', 2);



insert into panels (id, admin_note, description, material_status, quantity, specification_file_url, back_sheet_thickness, color, front_sheet_thickness, length, total_thickness, type, width, order_id)
values  (1, 'Няма да са готови за края на седмицата', null, 'NOT_APPROVED', 4, 'https://testUrl.com', '1 MM', 'RAL 3000', '1.5 MM', '1200 CM', '100 MM', null, '90 MM', 8),
        (2, 'Няма да са готови за края на седмицата', 'скрит монтаж', 'NOT_APPROVED', 5, 'https://testUrl.com', '1 MM', 'RAL 3000', '1.5 MM', '1200 CM', '50 MM', 'скрит монтаж', '90 MM', 8),
        (3, 'Няма да са готови за края на седмицата', null, 'NOT_APPROVED', 10, 'https://testUrl.com', '1 MM', 'RAL 3000', '1.5 MM', '1200 CM', '70 MM', null, '90 MM', 8),
        (4, null, 'oткрит монтаж', 'APPROVED', 190, 'https://testUrl.com', '1 MM', 'RAL 3000', '1.5 MM', '1200 CM', '100 MM', 'oткрит монтаж', '90 MM', 8),
        (5, null, 'открит монтаж/ хладилен', 'APPROVED', 18, 'https://testUrl.com', '1 MM', 'RAL 3000', '1.5 MM', '1200 CM', '80 MM', 'открит монтаж/ хладилен', '90 MM', 8),
        (6, null, null, 'APPROVED', 1, 'https://testUrl.com', '1 MM', 'RAL 3000', '1.5 MM', '1200 CM', '90 MM', null, '90 MM', 8);


insert into insulation (id, admin_note, description, material_status, quantity, specification_file_url, thickness, type, order_id)
values  (1, 'sample note from admin', 'черна', 'NOT_APPROVED', 1, null, '8 MM', 'Тръбна изолация 6 x 08', 1),
        (2, 'sample note from admin', 'черна', 'NOT_APPROVED', 3, null, '10 MM', 'Тръбна изолация 6 x 10', 1),
        (3, 'sample note from admin', 'черна', 'NOT_APPROVED', 4, null, '12 MM', 'Тръбна изолация 6 x 12', 1),
        (4, 'sample note from admin', 'черна', 'NOT_APPROVED', 6, null, '15 MM', 'Тръбна изолация 6 x 15', 1),
        (5, 'sample note from admin', 'черна', 'NOT_APPROVED', 5, null, '18 MM', 'Тръбна изолация 6 x 18', 1),
        (6, null, 'черна', 'APPROVED', 8, null, '20 MM', 'Тръбна изолация 6 x 20', 1),
        (7, null, 'черна', 'APPROVED', 7, null, '22 MM', 'Тръбна изолация 6 x 22', 1),
        (8, null, 'черна', 'APPROVED', 8, null, '25 MM', 'Тръбна изолация 6 x 25', 1);


insert into rebars (id, admin_note, description, material_status, quantity, specification_file_url, max_length, weight, order_id)
values  (1, null, 'sample rebar description', 'APPROVED', 528, 'https://sampleURL.com', '67 CM', '1.208 T', 4),
        (2, null, 'sample rebar description', 'APPROVED', 440, 'https://sampleURL.com', '120 CM', '1.578 T', 4),
        (3, 'Ще дойдат другата седмица', 'sample rebar description', 'NOT_APPROVED', 440, 'https://sampleURL.com', '15 M', '0.395 T', 4),
        (4, 'Ще дойдат другата седмица', 'sample rebar description', 'NOT_APPROVED', 935, 'https://sampleURL.com', '450 CM', '0.395 T', 4),
        (5, 'Ще дойдат другата седмица', 'sample rebar description', 'NOT_APPROVED', 440, 'https://sampleURL.com', '150 CM', '1.208 T', 4);


