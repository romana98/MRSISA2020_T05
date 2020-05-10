INSERT INTO USERS (email, password, name, surname, enabled, last_password_reset_date) VALUES ('cadmin@example.com', '$2y$12$mqtpfiMpMZ9m/n0N8gDWE./ThGjArCwkPJLFxO07D1IESbvzJSEQ6', 'Admin', 'Admin', true, '2019-10-01 18:57:58.508-07');
INSERT INTO USERS (email, password, name, surname, enabled, last_password_reset_date) VALUES ('ccadmin@example.com', '$2y$12$mqtpfiMpMZ9m/n0N8gDWE./ThGjArCwkPJLFxO07D1IESbvzJSEQ6', 'Admin', 'Admin', true, '2019-10-01 18:57:58.508-07');
--INSERT INTO USERS (email, password, name, surname, enabled, last_password_reset_date) VALUES ('doctor@example.com', '$2y$12$mqtpfiMpMZ9m/n0N8gDWE./ThGjArCwkPJLFxO07D1IESbvzJSEQ6', 'Admin', 'Admin', true, '2019-10-01 18:57:58.508-07');
--INSERT INTO USERS (email, password, name, surname, enabled, last_password_reset_date) VALUES ('nurse@example.com', '$2y$12$mqtpfiMpMZ9m/n0N8gDWE./ThGjArCwkPJLFxO07D1IESbvzJSEQ6', 'Admin', 'Admin', true, '2019-10-01 18:57:58.508-07');
--INSERT INTO USERS (email, password, name, surname, enabled, last_password_reset_date) VALUES ('patient@example.com', '$2y$12$mqtpfiMpMZ9m/n0N8gDWE./ThGjArCwkPJLFxO07D1IESbvzJSEQ6', 'Admin', 'Admin', true, '2019-10-01 18:57:58.508-07');

INSERT INTO CLINICS (name, address, description) VALUES('Clinic', 'Address 1', 'Des');

--INSERT INTO MEDICAL_RECORDS (medical_record_id) VALUES (1);

INSERT INTO CLINIC_CENTER_ADMINS(user_id) VALUES (2);
INSERT INTO CLINIC_ADMINS(user_id, clinic) VALUES (1, 1);
--INSERT INTO DOCTORS(user_id) VALUES (3);
--INSERT INTO NURSE(user_id) VALUES (4);
--INSERT INTO PATIENTS(user_id, address, city, country, insurance_number, phone_number, doctor, medical_record, nurse) VALUES (5, 'Adresa1', 'Grad', 'Drzava', '123A', '100-100-100', 3, 1, 4);



INSERT INTO AUTHORITIES (name) VALUES ('ROLE_PATIENT');
INSERT INTO AUTHORITIES (name) VALUES ('ROLE_CLINIC_ADMIN');
INSERT INTO AUTHORITIES (name) VALUES ('ROLE_CLINIC_CENTER_ADMIN');
INSERT INTO AUTHORITIES (name) VALUES ('ROLE_DOCTOR');
INSERT INTO AUTHORITIES (name) VALUES ('ROLE_NURSE');

INSERT INTO USER_AUTHORITY (user_id, authority_id) VALUES (1, 2);
INSERT INTO USER_AUTHORITY (user_id, authority_id) VALUES (2, 3);
--INSERT INTO USER_AUTHORITY (user_id, authority_id) VALUES (3, 4);
--INSERT INTO USER_AUTHORITY (user_id, authority_id) VALUES (4, 5);
--INSERT INTO USER_AUTHORITY (user_id, authority_id) VALUES (5, 1);


