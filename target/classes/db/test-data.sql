DELETE FROM customers;
INSERT INTO customers(CUSTOMER_ID, FIRST_NAME, LAST_NAME, EMAIL, PHONE) VALUES
  (1, 'Rraaai', 'Ferrão', 'mail@gmail.com', '777888999'),
  (2, 'aaSergio', 'Gouveia', 'mail@gmail.com', '777888990'),
  (3, 'Baaruno', 'Ferreira', 'mail@gmail.com', '777888909'),
  (4, 'CaACHE', 'alots', 'mail@gmail.com', '777888099');
DELETE FROM reservations;
INSERT INTO reservations(RESERVATION_ID, ACTIVITY, BOOKED_FOR, NUMBER_OF_PEOPLE, CUSTOMER_ID, CUSTOMER_PHONE) VALUES
  (1, 'SNORKEL', '2021-05-10', 4, 1, '777888999'),
  (2, 'TREKKING', '2021-05-10', 2, 2, '777888990'),
  (3, 'MTB', '2021-05-11', 4, 1, '777888999'),
  (4, 'SKYDIVING', '2021-05-11', 21, 4, '777888099'),
  (1, 'SNORKEL', '2021-05-12', 2, 3, '777888909');