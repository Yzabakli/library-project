INSERT INTO roles(insert_date_time, insert_user_id, is_deleted, last_update_date_time, last_update_user_id, description)
VALUES ('2022-01-05 00:00:00', 1, false, '2022-01-05 00:00:00', 1, 'Admin'),
       ('2022-01-05 00:00:00', 1, false, '2022-01-05 00:00:00', 1, 'Staff'),
       ('2022-01-05 00:00:00', 1, false, '2022-01-05 00:00:00', 1, 'Student');

INSERT INTO admins(insert_date_time, insert_user_id, is_deleted, last_update_date_time, last_update_user_id,
                   first_name, last_name, contact, role_id, password)
VALUES ('2022-01-05 00:00:00', 1, false, '2022-01-05 00:00:00', 1, 'admin', 'admin', 'admin@admin.com',
        1, '$2a$10$nAB5j9G1c3JHgg7qzhiIXO7cqqr5oJ3LXRNQJKssDUwHXzDGUztNK');
INSERT INTO students(insert_date_time, insert_user_id, is_deleted, last_update_date_time, last_update_user_id, contact,
                     first_name, last_name, password, age, birthdate, course, gender, year, role_id)
VALUES ('2022-01-05 00:00:00', 1, false, '2022-01-05 00:00:00', 1, '123', 'yusuf', 'abakli', '$2a$10$Nco3Zzg5o4xNiX4QBD5sVubOSrKkSVX6X012r7RG0ee.QRXsAO/Au', 21, '2001-05-21', 3, 0, 2022, 3)