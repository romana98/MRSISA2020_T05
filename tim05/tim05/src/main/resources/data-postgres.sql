INSERT INTO USERS (email, password, name, surname, enabled, last_password_reset_date) VALUES ('admin@example.com', '$2a$04$Vbug2lwwJGrvUXTj6z7ff.97IzVBkrJ1XfApfGNl.Z695zqcnPYra', 'Admin', 'Admin', true, '2019-10-01 18:57:58.508-07');
INSERT INTO CLINIC_CENTER_ADMINS(user_id) VALUES (1);

INSERT INTO CLINICS (name, address, description) VALUES('Clinic', 'Address 1', 'Des');

INSERT INTO AUTHORITIES (name) VALUES ('ROLE_PATIENT');
INSERT INTO AUTHORITIES (name) VALUES ('ROLE_CLINIC_ADMIN');
INSERT INTO AUTHORITIES (name) VALUES ('ROLE_CLINIC_CENTER_ADMIN');
INSERT INTO AUTHORITIES (name) VALUES ('ROLE_DOCTOR');
INSERT INTO AUTHORITIES (name) VALUES ('ROLE_NURSE');

INSERT INTO USER_AUTHORITY (user_id, authority_id) VALUES (1, 3);
--INSERT INTO USER_AUTHORITY (user_id, authority_id) VALUES (2, 5);