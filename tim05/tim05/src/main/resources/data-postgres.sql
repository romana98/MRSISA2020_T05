INSERT INTO USERS (email, password, name, surname, enabled, last_password_reset_date) VALUES ('cadmin@example.com', '$2y$12$mqtpfiMpMZ9m/n0N8gDWE./ThGjArCwkPJLFxO07D1IESbvzJSEQ6', 'Admin', 'Admin', true, '2019-10-01 18:57:58.508-07');
INSERT INTO USERS (email, password, name, surname, enabled, last_password_reset_date) VALUES ('ccadmin@example.com', '$2y$12$mqtpfiMpMZ9m/n0N8gDWE./ThGjArCwkPJLFxO07D1IESbvzJSEQ6', 'Admin', 'Admin', true, '2019-10-01 18:57:58.508-07');
INSERT INTO USERS (email, password, name, surname, enabled, last_password_reset_date) VALUES ('doctor@example.com', '$2y$12$mqtpfiMpMZ9m/n0N8gDWE./ThGjArCwkPJLFxO07D1IESbvzJSEQ6', 'Doctor', 'First', true, '2019-10-01 18:57:58.508-07');
INSERT INTO USERS (email, password, name, surname, enabled, last_password_reset_date) VALUES ('nurse@example.com', '$2y$12$mqtpfiMpMZ9m/n0N8gDWE./ThGjArCwkPJLFxO07D1IESbvzJSEQ6', 'Admin', 'Admin', true, '2019-10-01 18:57:58.508-07');
INSERT INTO USERS (email, password, name, surname, enabled, last_password_reset_date) VALUES ('patient@example.com', '$2y$12$mqtpfiMpMZ9m/n0N8gDWE./ThGjArCwkPJLFxO07D1IESbvzJSEQ6', 'Admin', 'Admin', true, '2019-10-01 18:57:58.508-07');


INSERT INTO CLINICS (name, address, description) VALUES('Clinic', 'Address 1', 'Des');
INSERT INTO HALLS (name,number,clinic) VALUES ('Hall',2,1);

INSERT INTO APPOINTMENT_TYPES(name,clinic) VALUES ('Appname',1);
INSERT INTO APPOINTMENT_TYPES(name,clinic) VALUES ('App',1);

INSERT INTO MEDICAL_RECORDS (medical_record_id) VALUES (1);

INSERT INTO CLINIC_CENTER_ADMINS(user_id) VALUES (2);
INSERT INTO CLINIC_ADMINS(user_id, clinic) VALUES (1, 1);
INSERT INTO MEDICAL_STAFF(work_end,work_start,user_id) VALUES('14:00','12:00',3);
INSERT INTO MEDICAL_STAFF(work_end,work_start,user_id) VALUES('14:00','12:00',4);
INSERT INTO DOCTORS(user_id,active,appointment_type,clinic,clinic_admin,rate) VALUES (3, true , 1,1,1, 0);

INSERT INTO PATIENTS(address,city,country,insurance_number,phone_number,user_id,medical_record) VALUES ('Wada','Novi Sad','Srbija','1234444','123-123-123',5,1);

INSERT INTO DISEASES(name,value,medical_record) VALUES ('BloodType','A+',1);
INSERT INTO DISEASES(name,value,medical_record) VALUES ('Height','177',1);
INSERT INTO DISEASES(name,value,medical_record) VALUES ('Weight','64',1);
INSERT INTO DISEASES(name,value,medical_record) VALUES ('Allergy','Rats',1);

INSERT INTO NURSES(user_id, clinic) VALUES (4, 1);

INSERT INTO AUTHORITIES (name) VALUES ('ROLE_PATIENT');
INSERT INTO AUTHORITIES (name) VALUES ('ROLE_CLINIC_ADMIN');
INSERT INTO AUTHORITIES (name) VALUES ('ROLE_CLINIC_CENTER_ADMIN');
INSERT INTO AUTHORITIES (name) VALUES ('ROLE_DOCTOR');
INSERT INTO AUTHORITIES (name) VALUES ('ROLE_NURSE');

INSERT INTO USER_AUTHORITY (user_id, authority_id) VALUES (1, 2);
INSERT INTO USER_AUTHORITY (user_id, authority_id) VALUES (2, 3);
INSERT INTO USER_AUTHORITY (user_id, authority_id) VALUES (3, 4);
INSERT INTO USER_AUTHORITY (user_id, authority_id) VALUES (4, 5);
INSERT INTO USER_AUTHORITY (user_id, authority_id) VALUES (5, 1);
