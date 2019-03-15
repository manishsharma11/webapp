--
-- easycommuteQL database dump
--

-- Dumped from database version 9.3.7
-- Dumped by pg_dump version 9.4.0
-- Started on 2015-10-06 13:32:14 IST

SET statement_timeout = 0;
SET lock_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SET check_function_bodies = false;
SET client_min_messages = warning;

SET search_path = public, pg_catalog;

--
-- TOC entry 2279 (class 0 OID 64516)
-- Dependencies: 170
-- Data for Name: address; Type: TABLE DATA; Schema: public; Owner: easycommute
--

INSERT INTO address (id, subscriber_type, street, city, state, postal, country, subscriber_id) VALUES (8091, 'student', 'forum', 'Bangalore', 'Karnataka', '450001', 'India', 8089);
INSERT INTO address (id, subscriber_type, street, city, state, postal, country, subscriber_id) VALUES (8107, 'student', 'forum', 'Bangalore', 'Karnataka', '450001', 'India', 8105);
INSERT INTO address (id, subscriber_type, street, city, state, postal, country, subscriber_id) VALUES (8191, 'staff', '5 Mowbray Dr', 'Kanata', 'ON', 'K2K1X7', 'Canada', 8190);
INSERT INTO address (id, subscriber_type, street, city, state, postal, country, subscriber_id) VALUES (8197, 'staff', '191 Blackberry Way', 'Dunrobin', 'Ontario', 'K0A1T0', 'Canada', 8196);
INSERT INTO address (id, subscriber_type, street, city, state, postal, country, subscriber_id) VALUES (8203, 'staff', '72 Russell Dr', 'Ottawa', 'ON', 'K2L1B4', 'Canada', 8202);
INSERT INTO address (id, subscriber_type, street, city, state, postal, country, subscriber_id) VALUES (8209, 'staff', '868 Brock Side Rd', 'Kanata', 'ON', 'K2K3G7', 'Canada', 8208);
INSERT INTO address (id, subscriber_type, street, city, state, postal, country, subscriber_id) VALUES (8215, 'staff', '23 Ryan Rd', 'Ottawa', 'ON', 'K2K2L1', 'Canada', 8214);
INSERT INTO address (id, subscriber_type, street, city, state, postal, country, subscriber_id) VALUES (8221, 'staff', '15 Kearse Dr', 'Ottawa', 'ON', 'K2K0G2', 'Canada', 8220);
INSERT INTO address (id, subscriber_type, street, city, state, postal, country, subscriber_id) VALUES (8233, 'staff', '41 Maxwell Cres', 'Ottawa', 'ON', 'K2K0F7', 'Canada', 8232);
INSERT INTO address (id, subscriber_type, street, city, state, postal, country, subscriber_id) VALUES (8239, 'staff', '25 Sherman Dr', 'Ottawa', 'Ontario', 'K2K9V5', 'Canada', 8238);
INSERT INTO address (id, subscriber_type, street, city, state, postal, country, subscriber_id) VALUES (8251, 'staff', '56 April Crt', 'Ottawa', 'Ontario', 'K2L6T0', 'Canada', 8250);
INSERT INTO address (id, subscriber_type, street, city, state, postal, country, subscriber_id) VALUES (8257, 'staff', '72 Bennett Dr', 'Kanata', 'Ontario', 'K2K7N4', 'Canada', 8256);
INSERT INTO address (id, subscriber_type, street, city, state, postal, country, subscriber_id) VALUES (8227, 'staff', '65 Lewis Ln', 'Ottawa', 'Ontario', 'K2T4R1', 'Canada', 8226);
INSERT INTO address (id, subscriber_type, street, city, state, postal, country, subscriber_id) VALUES (8263, 'staff', '95 Dobbson Dr', 'Kanata', 'Ontario', 'K2L8H2', 'Canada', 8262);
INSERT INTO address (id, subscriber_type, street, city, state, postal, country, subscriber_id) VALUES (8245, 'staff', '27 Simon St', 'Ottawa', 'Ontario', 'K2K0L2', 'Canada', 8244);
INSERT INTO address (id, subscriber_type, street, city, state, postal, country, subscriber_id) VALUES (8269, 'staff', '93 Schofield Cres', 'Ottawa', 'Ontario', 'K2K6C0', 'Canada', 8268);
INSERT INTO address (id, subscriber_type, street, city, state, postal, country, subscriber_id) VALUES (8337, 'student', 'Princess Way Prudhoe', 'Northumberland', 'NE42 6NJ', 'NE426NJ', 'Canada', 8335);
INSERT INTO address (id, subscriber_type, street, city, state, postal, country, subscriber_id) VALUES (8374, 'student', 'March Networks', 'kanata', 'ON', 'kkkkks', 'Canada', 8372);
INSERT INTO address (id, subscriber_type, street, city, state, postal, country, subscriber_id) VALUES (8381, 'student', '800 Westchester Ave.', 'kanata', 'Ontario', '10573', 'Canada', 8379);
INSERT INTO address (id, subscriber_type, street, city, state, postal, country, subscriber_id) VALUES (8390, 'student', 's-labs,Dr. C.R.Rao road, #101, 102,vindhya C5,', 'IIIT Hyderabad, Gachibowli', 'telangana', '500032', 'India', 8388);
INSERT INTO address (id, subscriber_type, street, city, state, postal, country, subscriber_id) VALUES (8470, 'student', '161 Porcupine Trail', 'Dunrobin', 'ON', 'K0A1T0', 'Canada', 8468);
INSERT INTO address (id, subscriber_type, street, city, state, postal, country, subscriber_id) VALUES (8477, 'student', '103 Porcupine Trail', 'Dunrobin', 'ON', 'K0A1T0', 'Canada', 8475);
INSERT INTO address (id, subscriber_type, street, city, state, postal, country, subscriber_id) VALUES (8484, 'student', '2410 Dunrobin Rd', 'Dunrobin', 'ON', 'K0A1T0', 'Canada', 8482);
INSERT INTO address (id, subscriber_type, street, city, state, postal, country, subscriber_id) VALUES (8498, 'student', '9 Mowbray St', 'Kanata', 'Ontario', 'K2K1X7', 'Canada', 8496);
INSERT INTO address (id, subscriber_type, street, city, state, postal, country, subscriber_id) VALUES (8491, 'student', '9 Mowbray St', 'Kanata', 'Ontario', 'K2K1X7', 'Canada', 8489);
INSERT INTO address (id, subscriber_type, street, city, state, postal, country, subscriber_id) VALUES (8505, 'student', '1036 Kerwin Rd', 'Kanata', 'Ontario', 'K2K1X7', 'Canada', 8503);
INSERT INTO address (id, subscriber_type, street, city, state, postal, country, subscriber_id) VALUES (8512, 'student', '12 O''Hara Dr', 'Kanata', 'ON', 'K2K1X7', 'Canada', 8510);
INSERT INTO address (id, subscriber_type, street, city, state, postal, country, subscriber_id) VALUES (8526, 'student', '14 O''Hara Dr', 'Kanata', 'ON', 'K2K1X7', 'Canada', 8524);
INSERT INTO address (id, subscriber_type, street, city, state, postal, country, subscriber_id) VALUES (8519, 'student', '12 O''Hara Dr', 'Kanata', 'ON', 'K2K1X7', 'Canada', 8517);
INSERT INTO address (id, subscriber_type, street, city, state, postal, country, subscriber_id) VALUES (8533, 'student', '33 O''Hara Dr', 'Kanata', 'ON', 'K2K1X7', 'Canada', 8531);
INSERT INTO address (id, subscriber_type, street, city, state, postal, country, subscriber_id) VALUES (8540, 'student', '33 O''Hara Dr', 'Kanata', 'ON', 'K2K1X7', 'Canada', 8538);
INSERT INTO address (id, subscriber_type, street, city, state, postal, country, subscriber_id) VALUES (8551, 'student', '2071 Fifth Line', 'Dunrobin', 'ON', 'K0A1T0', 'Canada', 8549);
INSERT INTO address (id, subscriber_type, street, city, state, postal, country, subscriber_id) VALUES (8558, 'student', '2089 Sixth Line', 'Dunrobin', 'ON', 'K0A1T0', 'Canada', 8556);
INSERT INTO address (id, subscriber_type, street, city, state, postal, country, subscriber_id) VALUES (8565, 'student', '1616 Sixth Line', 'Dunrobin', 'ON', 'K0A1T0', 'Canada', 8563);
INSERT INTO address (id, subscriber_type, street, city, state, postal, country, subscriber_id) VALUES (8545, 'guardian', '800 Westchester Ave.', 'Northumberland', 'NY', '10573', 'Canada', 8548);
INSERT INTO address (id, subscriber_type, street, city, state, postal, country, subscriber_id) VALUES (8572, 'student', '1616 Sixth Line', 'Dunrobin', 'ON', 'K0A1T0', 'Canada', 8570);
INSERT INTO address (id, subscriber_type, street, city, state, postal, country, subscriber_id) VALUES (8579, 'student', '1244 Sixth Line', 'Dunrobin', 'ON', 'K0A1T0', 'Canada', 8577);
INSERT INTO address (id, subscriber_type, street, city, state, postal, country, subscriber_id) VALUES (8587, 'student', '1244 Sixth Line', 'Dunrobin', 'ON', 'K0A1T0', 'Canada', 8585);
INSERT INTO address (id, subscriber_type, street, city, state, postal, country, subscriber_id) VALUES (8594, 'student', '5 Moran St', 'Kanata', 'ON', 'K2W1B8', 'Canada', 8592);
INSERT INTO address (id, subscriber_type, street, city, state, postal, country, subscriber_id) VALUES (8601, 'student', '15 Bathurst St', 'Kanata', 'ON', 'K2W1A7', 'Canada', 8599);
INSERT INTO address (id, subscriber_type, street, city, state, postal, country, subscriber_id) VALUES (8435, 'student', '193 Blackberry Way', 'Dunrobin', 'Ontario', 'K0A1T0', 'Canada', 8433);
INSERT INTO address (id, subscriber_type, street, city, state, postal, country, subscriber_id) VALUES (8442, 'student', '193 Blackberry Way', 'Dunrobin', 'ON', 'K0A1T0', 'Canada', 8440);
INSERT INTO address (id, subscriber_type, street, city, state, postal, country, subscriber_id) VALUES (8449, 'student', '128 Constance Creek', 'Dunrobin', 'ON', 'K0A1T0', 'Canada', 8447);
INSERT INTO address (id, subscriber_type, street, city, state, postal, country, subscriber_id) VALUES (8456, 'student', '100 Constance Creek', 'Dunrobin', 'ON', 'K0A1T0', 'Canada', 8454);
INSERT INTO address (id, subscriber_type, street, city, state, postal, country, subscriber_id) VALUES (8463, 'student', '161 Porcupine Trail', 'Dunrobin', 'Ontario', 'K0A1T0', 'Canada', 8461);
INSERT INTO address (id, subscriber_type, street, city, state, postal, country, subscriber_id) VALUES (8608, 'student', '17 Bathurst St', 'Kanata', 'ON', 'K2W1A7', 'Canada', 8606);
INSERT INTO address (id, subscriber_type, street, city, state, postal, country, subscriber_id) VALUES (8615, 'student', '19 Bathurst St', 'Kanata', 'ON', 'K2W1A7', 'Canada', 8613);
INSERT INTO address (id, subscriber_type, street, city, state, postal, country, subscriber_id) VALUES (8622, 'student', '19 Bathurst St', 'Kanata', 'ON', 'K2W1A7', 'Canada', 8620);
INSERT INTO address (id, subscriber_type, street, city, state, postal, country, subscriber_id) VALUES (8629, 'student', '1619 Dunrobin Rd', 'Kanata', 'ON', 'K2K1X7', 'Canada', 8627);
INSERT INTO address (id, subscriber_type, street, city, state, postal, country, subscriber_id) VALUES (8636, 'student', '1450 Hedge Rd', 'Kanata', 'ON', 'K2W1B6', 'Canada', 8634);
INSERT INTO address (id, subscriber_type, street, city, state, postal, country, subscriber_id) VALUES (8643, 'student', '1450 Hedge Rd', 'Kanata', 'ON ', 'K2W1B6', 'Canada', 8641);
INSERT INTO address (id, subscriber_type, street, city, state, postal, country, subscriber_id) VALUES (8650, 'student', '1426 Houston Dr', 'Kanata', 'ON', 'K2W1B6', 'Canada', 8648);
INSERT INTO address (id, subscriber_type, street, city, state, postal, country, subscriber_id) VALUES (8657, 'student', '1426 Houston Dr', 'Kanata', 'ON', 'K2W1B6', 'Canada', 8655);
INSERT INTO address (id, subscriber_type, street, city, state, postal, country, subscriber_id) VALUES (1054, 'staff', 's-labs,Dr. C.R.Rao road, #101, 102,vindhya C5,', 'Bangalore', 'Karnataka', '500032', 'Algeria', 1053);
INSERT INTO address (id, subscriber_type, street, city, state, postal, country, subscriber_id) VALUES (1060, 'staff', 'forum', 'Bangalore', 'Karnataka', '450001', 'India', 1059);
INSERT INTO address (id, subscriber_type, street, city, state, postal, country, subscriber_id) VALUES (1008, 'student', 'forum', 'Bangalore', 'Karnataka', '450001', 'India', 1006);
INSERT INTO address (id, subscriber_type, street, city, state, postal, country, subscriber_id) VALUES (1048, 'student', 'forum', 'Bangalore', 'Karnataka', '500032', 'Algeria', 1046);
INSERT INTO address (id, subscriber_type, street, city, state, postal, country, subscriber_id) VALUES (1041, 'student', 's-labs,Dr. C.R.Rao road, #101, 102,vindhya C5,', 'IIIT Hyderabad, Gachibowli', 'telangana', '500032', 'India', 1039);
INSERT INTO address (id, subscriber_type, street, city, state, postal, country, subscriber_id) VALUES (1067, 'staff', 'forum', 'Bangalore', 'Karnataka', '450001', 'India', 1066);
INSERT INTO address (id, subscriber_type, street, city, state, postal, country, subscriber_id) VALUES (13900, 'guardian', 's-labs,Dr. C.R.Rao road, #101, 102,vindhya C5,', 'IIIT Hyderabad, Gachibowli', 'telangana', '500032', 'India', 13903);
INSERT INTO address (id, subscriber_type, street, city, state, postal, country, subscriber_id) VALUES (15873, 'student', 'A block', 'none', 'Ap', '500002', 'India', 15870);


--
-- TOC entry 2336 (class 0 OID 0)
-- Dependencies: 171
-- Name: address_id_seq; Type: SEQUENCE SET; Schema: public; Owner: easycommute
--

SELECT pg_catalog.setval('address_id_seq', 1, false);


--
-- TOC entry 2281 (class 0 OID 64524)
-- Dependencies: 172
-- Data for Name: admin_emails; Type: TABLE DATA; Schema: public; Owner: easycommute
--

INSERT INTO admin_emails (id, email) VALUES (87, 'plexustracker@gmail.com');


--
-- TOC entry 2282 (class 0 OID 64527)
-- Dependencies: 173
-- Data for Name: alerts; Type: TABLE DATA; Schema: public; Owner: easycommute
--

INSERT INTO alerts (id, subscriber_type, alert_type, all_alerts, no_show, late, irregularities, regularities, subscriber_id) VALUES (8092, 'student', 'sms', 'on', 'on', 'on', 'on', 'on', 8089);
INSERT INTO alerts (id, subscriber_type, alert_type, all_alerts, no_show, late, irregularities, regularities, subscriber_id) VALUES (8093, 'student', 'email', 'on', 'on', 'on', 'on', 'on', 8089);
INSERT INTO alerts (id, subscriber_type, alert_type, all_alerts, no_show, late, irregularities, regularities, subscriber_id) VALUES (8108, 'student', 'sms', 'on', 'on', 'on', 'on', 'on', 8105);
INSERT INTO alerts (id, subscriber_type, alert_type, all_alerts, no_show, late, irregularities, regularities, subscriber_id) VALUES (8109, 'student', 'email', 'on', 'on', 'on', 'on', 'on', 8105);
INSERT INTO alerts (id, subscriber_type, alert_type, all_alerts, no_show, late, irregularities, regularities, subscriber_id) VALUES (8192, 'staff', 'sms', 'off', 'off', 'off', 'off', 'off', 8190);
INSERT INTO alerts (id, subscriber_type, alert_type, all_alerts, no_show, late, irregularities, regularities, subscriber_id) VALUES (8193, 'staff', 'email', 'off', 'off', 'off', 'off', 'off', 8190);
INSERT INTO alerts (id, subscriber_type, alert_type, all_alerts, no_show, late, irregularities, regularities, subscriber_id) VALUES (8198, 'staff', 'sms', 'off', 'off', 'off', 'off', 'off', 8196);
INSERT INTO alerts (id, subscriber_type, alert_type, all_alerts, no_show, late, irregularities, regularities, subscriber_id) VALUES (8199, 'staff', 'email', 'off', 'off', 'off', 'off', 'off', 8196);
INSERT INTO alerts (id, subscriber_type, alert_type, all_alerts, no_show, late, irregularities, regularities, subscriber_id) VALUES (8204, 'staff', 'sms', 'off', 'off', 'off', 'off', 'off', 8202);
INSERT INTO alerts (id, subscriber_type, alert_type, all_alerts, no_show, late, irregularities, regularities, subscriber_id) VALUES (8205, 'staff', 'email', 'off', 'off', 'off', 'off', 'off', 8202);
INSERT INTO alerts (id, subscriber_type, alert_type, all_alerts, no_show, late, irregularities, regularities, subscriber_id) VALUES (8210, 'staff', 'sms', 'off', 'off', 'off', 'off', 'off', 8208);
INSERT INTO alerts (id, subscriber_type, alert_type, all_alerts, no_show, late, irregularities, regularities, subscriber_id) VALUES (8211, 'staff', 'email', 'off', 'off', 'off', 'off', 'off', 8208);
INSERT INTO alerts (id, subscriber_type, alert_type, all_alerts, no_show, late, irregularities, regularities, subscriber_id) VALUES (8216, 'staff', 'sms', 'off', 'off', 'off', 'off', 'off', 8214);
INSERT INTO alerts (id, subscriber_type, alert_type, all_alerts, no_show, late, irregularities, regularities, subscriber_id) VALUES (8217, 'staff', 'email', 'off', 'off', 'off', 'off', 'off', 8214);
INSERT INTO alerts (id, subscriber_type, alert_type, all_alerts, no_show, late, irregularities, regularities, subscriber_id) VALUES (8223, 'staff', 'email', 'off', 'off', 'off', 'off', 'off', 8220);
INSERT INTO alerts (id, subscriber_type, alert_type, all_alerts, no_show, late, irregularities, regularities, subscriber_id) VALUES (8222, 'staff', 'sms', 'off', 'off', 'off', 'off', 'off', 8220);
INSERT INTO alerts (id, subscriber_type, alert_type, all_alerts, no_show, late, irregularities, regularities, subscriber_id) VALUES (8234, 'staff', 'sms', 'off', 'off', 'off', 'off', 'off', 8232);
INSERT INTO alerts (id, subscriber_type, alert_type, all_alerts, no_show, late, irregularities, regularities, subscriber_id) VALUES (8235, 'staff', 'email', 'off', 'off', 'off', 'off', 'off', 8232);
INSERT INTO alerts (id, subscriber_type, alert_type, all_alerts, no_show, late, irregularities, regularities, subscriber_id) VALUES (8240, 'staff', 'sms', 'off', 'off', 'off', 'off', 'off', 8238);
INSERT INTO alerts (id, subscriber_type, alert_type, all_alerts, no_show, late, irregularities, regularities, subscriber_id) VALUES (8241, 'staff', 'email', 'off', 'off', 'off', 'off', 'off', 8238);
INSERT INTO alerts (id, subscriber_type, alert_type, all_alerts, no_show, late, irregularities, regularities, subscriber_id) VALUES (8252, 'staff', 'sms', 'off', 'off', 'off', 'off', 'off', 8250);
INSERT INTO alerts (id, subscriber_type, alert_type, all_alerts, no_show, late, irregularities, regularities, subscriber_id) VALUES (8253, 'staff', 'email', 'off', 'off', 'off', 'off', 'off', 8250);
INSERT INTO alerts (id, subscriber_type, alert_type, all_alerts, no_show, late, irregularities, regularities, subscriber_id) VALUES (8258, 'staff', 'sms', 'off', 'off', 'off', 'off', 'off', 8256);
INSERT INTO alerts (id, subscriber_type, alert_type, all_alerts, no_show, late, irregularities, regularities, subscriber_id) VALUES (8259, 'staff', 'email', 'off', 'off', 'off', 'off', 'off', 8256);
INSERT INTO alerts (id, subscriber_type, alert_type, all_alerts, no_show, late, irregularities, regularities, subscriber_id) VALUES (8229, 'staff', 'email', 'off', 'off', 'off', 'off', 'off', 8226);
INSERT INTO alerts (id, subscriber_type, alert_type, all_alerts, no_show, late, irregularities, regularities, subscriber_id) VALUES (8228, 'staff', 'sms', 'off', 'off', 'off', 'off', 'off', 8226);
INSERT INTO alerts (id, subscriber_type, alert_type, all_alerts, no_show, late, irregularities, regularities, subscriber_id) VALUES (8264, 'staff', 'sms', 'off', 'off', 'off', 'off', 'off', 8262);
INSERT INTO alerts (id, subscriber_type, alert_type, all_alerts, no_show, late, irregularities, regularities, subscriber_id) VALUES (8265, 'staff', 'email', 'off', 'off', 'off', 'off', 'off', 8262);
INSERT INTO alerts (id, subscriber_type, alert_type, all_alerts, no_show, late, irregularities, regularities, subscriber_id) VALUES (8247, 'staff', 'email', 'off', 'off', 'off', 'off', 'off', 8244);
INSERT INTO alerts (id, subscriber_type, alert_type, all_alerts, no_show, late, irregularities, regularities, subscriber_id) VALUES (8246, 'staff', 'sms', 'off', 'off', 'off', 'off', 'off', 8244);
INSERT INTO alerts (id, subscriber_type, alert_type, all_alerts, no_show, late, irregularities, regularities, subscriber_id) VALUES (8270, 'staff', 'sms', 'off', 'off', 'off', 'off', 'off', 8268);
INSERT INTO alerts (id, subscriber_type, alert_type, all_alerts, no_show, late, irregularities, regularities, subscriber_id) VALUES (8271, 'staff', 'email', 'off', 'off', 'off', 'off', 'off', 8268);
INSERT INTO alerts (id, subscriber_type, alert_type, all_alerts, no_show, late, irregularities, regularities, subscriber_id) VALUES (8338, 'student', 'sms', 'off', 'off', 'off', 'off', 'off', 8335);
INSERT INTO alerts (id, subscriber_type, alert_type, all_alerts, no_show, late, irregularities, regularities, subscriber_id) VALUES (8339, 'student', 'email', 'off', 'off', 'off', 'off', 'off', 8335);
INSERT INTO alerts (id, subscriber_type, alert_type, all_alerts, no_show, late, irregularities, regularities, subscriber_id) VALUES (8375, 'student', 'sms', 'off', 'off', 'off', 'off', 'off', 8372);
INSERT INTO alerts (id, subscriber_type, alert_type, all_alerts, no_show, late, irregularities, regularities, subscriber_id) VALUES (8376, 'student', 'email', 'off', 'off', 'off', 'off', 'off', 8372);
INSERT INTO alerts (id, subscriber_type, alert_type, all_alerts, no_show, late, irregularities, regularities, subscriber_id) VALUES (8382, 'student', 'sms', 'off', 'off', 'off', 'off', 'off', 8379);
INSERT INTO alerts (id, subscriber_type, alert_type, all_alerts, no_show, late, irregularities, regularities, subscriber_id) VALUES (8383, 'student', 'email', 'off', 'off', 'off', 'off', 'off', 8379);
INSERT INTO alerts (id, subscriber_type, alert_type, all_alerts, no_show, late, irregularities, regularities, subscriber_id) VALUES (8391, 'student', 'sms', 'off', 'off', 'off', 'off', 'off', 8388);
INSERT INTO alerts (id, subscriber_type, alert_type, all_alerts, no_show, late, irregularities, regularities, subscriber_id) VALUES (8392, 'student', 'email', 'off', 'off', 'off', 'off', 'off', 8388);
INSERT INTO alerts (id, subscriber_type, alert_type, all_alerts, no_show, late, irregularities, regularities, subscriber_id) VALUES (8553, 'student', 'email', 'on', 'on', 'on', 'on', 'on', 8549);
INSERT INTO alerts (id, subscriber_type, alert_type, all_alerts, no_show, late, irregularities, regularities, subscriber_id) VALUES (8552, 'student', 'sms', 'on', 'on', 'on', 'on', 'on', 8549);
INSERT INTO alerts (id, subscriber_type, alert_type, all_alerts, no_show, late, irregularities, regularities, subscriber_id) VALUES (8559, 'student', 'sms', 'on', 'on', 'on', 'on', 'on', 8556);
INSERT INTO alerts (id, subscriber_type, alert_type, all_alerts, no_show, late, irregularities, regularities, subscriber_id) VALUES (8560, 'student', 'email', 'on', 'on', 'on', 'on', 'on', 8556);
INSERT INTO alerts (id, subscriber_type, alert_type, all_alerts, no_show, late, irregularities, regularities, subscriber_id) VALUES (8566, 'student', 'sms', 'on', 'on', 'on', 'on', 'on', 8563);
INSERT INTO alerts (id, subscriber_type, alert_type, all_alerts, no_show, late, irregularities, regularities, subscriber_id) VALUES (8567, 'student', 'email', 'on', 'on', 'on', 'on', 'on', 8563);
INSERT INTO alerts (id, subscriber_type, alert_type, all_alerts, no_show, late, irregularities, regularities, subscriber_id) VALUES (8547, 'guardian', 'email', 'on', 'on', 'on', 'on', 'on', 8548);
INSERT INTO alerts (id, subscriber_type, alert_type, all_alerts, no_show, late, irregularities, regularities, subscriber_id) VALUES (8444, 'student', 'email', 'on', 'on', 'on', 'on', 'on', 8440);
INSERT INTO alerts (id, subscriber_type, alert_type, all_alerts, no_show, late, irregularities, regularities, subscriber_id) VALUES (8443, 'student', 'sms', 'on', 'on', 'on', 'on', 'on', 8440);
INSERT INTO alerts (id, subscriber_type, alert_type, all_alerts, no_show, late, irregularities, regularities, subscriber_id) VALUES (8451, 'student', 'email', 'on', 'on', 'on', 'on', 'on', 8447);
INSERT INTO alerts (id, subscriber_type, alert_type, all_alerts, no_show, late, irregularities, regularities, subscriber_id) VALUES (8450, 'student', 'sms', 'on', 'on', 'on', 'on', 'on', 8447);
INSERT INTO alerts (id, subscriber_type, alert_type, all_alerts, no_show, late, irregularities, regularities, subscriber_id) VALUES (8458, 'student', 'email', 'on', 'on', 'on', 'on', 'on', 8454);
INSERT INTO alerts (id, subscriber_type, alert_type, all_alerts, no_show, late, irregularities, regularities, subscriber_id) VALUES (8457, 'student', 'sms', 'on', 'on', 'on', 'on', 'on', 8454);
INSERT INTO alerts (id, subscriber_type, alert_type, all_alerts, no_show, late, irregularities, regularities, subscriber_id) VALUES (8465, 'student', 'email', 'on', 'on', 'on', 'on', 'on', 8461);
INSERT INTO alerts (id, subscriber_type, alert_type, all_alerts, no_show, late, irregularities, regularities, subscriber_id) VALUES (8464, 'student', 'sms', 'on', 'on', 'on', 'on', 'on', 8461);
INSERT INTO alerts (id, subscriber_type, alert_type, all_alerts, no_show, late, irregularities, regularities, subscriber_id) VALUES (8472, 'student', 'email', 'on', 'on', 'on', 'on', 'on', 8468);
INSERT INTO alerts (id, subscriber_type, alert_type, all_alerts, no_show, late, irregularities, regularities, subscriber_id) VALUES (8471, 'student', 'sms', 'on', 'on', 'on', 'on', 'on', 8468);
INSERT INTO alerts (id, subscriber_type, alert_type, all_alerts, no_show, late, irregularities, regularities, subscriber_id) VALUES (8479, 'student', 'email', 'on', 'on', 'on', 'on', 'on', 8475);
INSERT INTO alerts (id, subscriber_type, alert_type, all_alerts, no_show, late, irregularities, regularities, subscriber_id) VALUES (8478, 'student', 'sms', 'on', 'on', 'on', 'on', 'on', 8475);
INSERT INTO alerts (id, subscriber_type, alert_type, all_alerts, no_show, late, irregularities, regularities, subscriber_id) VALUES (8486, 'student', 'email', 'on', 'on', 'on', 'on', 'on', 8482);
INSERT INTO alerts (id, subscriber_type, alert_type, all_alerts, no_show, late, irregularities, regularities, subscriber_id) VALUES (8546, 'guardian', 'sms', 'on', 'on', 'on', 'on', 'on', 8548);
INSERT INTO alerts (id, subscriber_type, alert_type, all_alerts, no_show, late, irregularities, regularities, subscriber_id) VALUES (8485, 'student', 'sms', 'on', 'on', 'on', 'on', 'on', 8482);
INSERT INTO alerts (id, subscriber_type, alert_type, all_alerts, no_show, late, irregularities, regularities, subscriber_id) VALUES (8500, 'student', 'email', 'on', 'on', 'on', 'on', 'on', 8496);
INSERT INTO alerts (id, subscriber_type, alert_type, all_alerts, no_show, late, irregularities, regularities, subscriber_id) VALUES (8499, 'student', 'sms', 'on', 'on', 'on', 'on', 'on', 8496);
INSERT INTO alerts (id, subscriber_type, alert_type, all_alerts, no_show, late, irregularities, regularities, subscriber_id) VALUES (8493, 'student', 'email', 'on', 'on', 'on', 'on', 'on', 8489);
INSERT INTO alerts (id, subscriber_type, alert_type, all_alerts, no_show, late, irregularities, regularities, subscriber_id) VALUES (8492, 'student', 'sms', 'on', 'on', 'on', 'on', 'on', 8489);
INSERT INTO alerts (id, subscriber_type, alert_type, all_alerts, no_show, late, irregularities, regularities, subscriber_id) VALUES (8507, 'student', 'email', 'on', 'on', 'on', 'on', 'on', 8503);
INSERT INTO alerts (id, subscriber_type, alert_type, all_alerts, no_show, late, irregularities, regularities, subscriber_id) VALUES (8574, 'student', 'email', 'on', 'on', 'on', 'on', 'on', 8570);
INSERT INTO alerts (id, subscriber_type, alert_type, all_alerts, no_show, late, irregularities, regularities, subscriber_id) VALUES (8573, 'student', 'sms', 'on', 'on', 'on', 'on', 'on', 8570);
INSERT INTO alerts (id, subscriber_type, alert_type, all_alerts, no_show, late, irregularities, regularities, subscriber_id) VALUES (8581, 'student', 'email', 'on', 'on', 'on', 'on', 'on', 8577);
INSERT INTO alerts (id, subscriber_type, alert_type, all_alerts, no_show, late, irregularities, regularities, subscriber_id) VALUES (8580, 'student', 'sms', 'on', 'on', 'on', 'on', 'on', 8577);
INSERT INTO alerts (id, subscriber_type, alert_type, all_alerts, no_show, late, irregularities, regularities, subscriber_id) VALUES (8506, 'student', 'sms', 'on', 'on', 'on', 'on', 'on', 8503);
INSERT INTO alerts (id, subscriber_type, alert_type, all_alerts, no_show, late, irregularities, regularities, subscriber_id) VALUES (8514, 'student', 'email', 'on', 'on', 'on', 'on', 'on', 8510);
INSERT INTO alerts (id, subscriber_type, alert_type, all_alerts, no_show, late, irregularities, regularities, subscriber_id) VALUES (8513, 'student', 'sms', 'on', 'on', 'on', 'on', 'on', 8510);
INSERT INTO alerts (id, subscriber_type, alert_type, all_alerts, no_show, late, irregularities, regularities, subscriber_id) VALUES (8528, 'student', 'email', 'on', 'on', 'on', 'on', 'on', 8524);
INSERT INTO alerts (id, subscriber_type, alert_type, all_alerts, no_show, late, irregularities, regularities, subscriber_id) VALUES (8588, 'student', 'sms', 'on', 'on', 'on', 'on', 'on', 8585);
INSERT INTO alerts (id, subscriber_type, alert_type, all_alerts, no_show, late, irregularities, regularities, subscriber_id) VALUES (8589, 'student', 'email', 'on', 'on', 'on', 'on', 'on', 8585);
INSERT INTO alerts (id, subscriber_type, alert_type, all_alerts, no_show, late, irregularities, regularities, subscriber_id) VALUES (8595, 'student', 'sms', 'on', 'on', 'on', 'on', 'on', 8592);
INSERT INTO alerts (id, subscriber_type, alert_type, all_alerts, no_show, late, irregularities, regularities, subscriber_id) VALUES (8596, 'student', 'email', 'on', 'on', 'on', 'on', 'on', 8592);
INSERT INTO alerts (id, subscriber_type, alert_type, all_alerts, no_show, late, irregularities, regularities, subscriber_id) VALUES (8602, 'student', 'sms', 'on', 'on', 'on', 'on', 'on', 8599);
INSERT INTO alerts (id, subscriber_type, alert_type, all_alerts, no_show, late, irregularities, regularities, subscriber_id) VALUES (8603, 'student', 'email', 'on', 'on', 'on', 'on', 'on', 8599);
INSERT INTO alerts (id, subscriber_type, alert_type, all_alerts, no_show, late, irregularities, regularities, subscriber_id) VALUES (8609, 'student', 'sms', 'on', 'on', 'on', 'on', 'on', 8606);
INSERT INTO alerts (id, subscriber_type, alert_type, all_alerts, no_show, late, irregularities, regularities, subscriber_id) VALUES (8610, 'student', 'email', 'on', 'on', 'on', 'on', 'on', 8606);
INSERT INTO alerts (id, subscriber_type, alert_type, all_alerts, no_show, late, irregularities, regularities, subscriber_id) VALUES (8437, 'student', 'email', 'on', 'on', 'on', 'on', 'on', 8433);
INSERT INTO alerts (id, subscriber_type, alert_type, all_alerts, no_show, late, irregularities, regularities, subscriber_id) VALUES (8436, 'student', 'sms', 'on', 'on', 'on', 'on', 'on', 8433);
INSERT INTO alerts (id, subscriber_type, alert_type, all_alerts, no_show, late, irregularities, regularities, subscriber_id) VALUES (8527, 'student', 'sms', 'on', 'on', 'on', 'on', 'on', 8524);
INSERT INTO alerts (id, subscriber_type, alert_type, all_alerts, no_show, late, irregularities, regularities, subscriber_id) VALUES (8521, 'student', 'email', 'on', 'on', 'on', 'on', 'on', 8517);
INSERT INTO alerts (id, subscriber_type, alert_type, all_alerts, no_show, late, irregularities, regularities, subscriber_id) VALUES (8520, 'student', 'sms', 'on', 'on', 'on', 'on', 'on', 8517);
INSERT INTO alerts (id, subscriber_type, alert_type, all_alerts, no_show, late, irregularities, regularities, subscriber_id) VALUES (8535, 'student', 'email', 'on', 'on', 'on', 'on', 'on', 8531);
INSERT INTO alerts (id, subscriber_type, alert_type, all_alerts, no_show, late, irregularities, regularities, subscriber_id) VALUES (8534, 'student', 'sms', 'on', 'on', 'on', 'on', 'on', 8531);
INSERT INTO alerts (id, subscriber_type, alert_type, all_alerts, no_show, late, irregularities, regularities, subscriber_id) VALUES (8542, 'student', 'email', 'on', 'on', 'on', 'on', 'on', 8538);
INSERT INTO alerts (id, subscriber_type, alert_type, all_alerts, no_show, late, irregularities, regularities, subscriber_id) VALUES (8541, 'student', 'sms', 'on', 'on', 'on', 'on', 'on', 8538);
INSERT INTO alerts (id, subscriber_type, alert_type, all_alerts, no_show, late, irregularities, regularities, subscriber_id) VALUES (8616, 'student', 'sms', 'on', 'on', 'on', 'on', 'on', 8613);
INSERT INTO alerts (id, subscriber_type, alert_type, all_alerts, no_show, late, irregularities, regularities, subscriber_id) VALUES (8617, 'student', 'email', 'on', 'on', 'on', 'on', 'on', 8613);
INSERT INTO alerts (id, subscriber_type, alert_type, all_alerts, no_show, late, irregularities, regularities, subscriber_id) VALUES (8623, 'student', 'sms', 'on', 'on', 'on', 'on', 'on', 8620);
INSERT INTO alerts (id, subscriber_type, alert_type, all_alerts, no_show, late, irregularities, regularities, subscriber_id) VALUES (8624, 'student', 'email', 'on', 'on', 'on', 'on', 'on', 8620);
INSERT INTO alerts (id, subscriber_type, alert_type, all_alerts, no_show, late, irregularities, regularities, subscriber_id) VALUES (8630, 'student', 'sms', 'on', 'on', 'on', 'on', 'on', 8627);
INSERT INTO alerts (id, subscriber_type, alert_type, all_alerts, no_show, late, irregularities, regularities, subscriber_id) VALUES (8631, 'student', 'email', 'on', 'on', 'on', 'on', 'on', 8627);
INSERT INTO alerts (id, subscriber_type, alert_type, all_alerts, no_show, late, irregularities, regularities, subscriber_id) VALUES (8637, 'student', 'sms', 'on', 'on', 'on', 'on', 'on', 8634);
INSERT INTO alerts (id, subscriber_type, alert_type, all_alerts, no_show, late, irregularities, regularities, subscriber_id) VALUES (8638, 'student', 'email', 'on', 'on', 'on', 'on', 'on', 8634);
INSERT INTO alerts (id, subscriber_type, alert_type, all_alerts, no_show, late, irregularities, regularities, subscriber_id) VALUES (8644, 'student', 'sms', 'on', 'on', 'on', 'on', 'on', 8641);
INSERT INTO alerts (id, subscriber_type, alert_type, all_alerts, no_show, late, irregularities, regularities, subscriber_id) VALUES (8645, 'student', 'email', 'on', 'on', 'on', 'on', 'on', 8641);
INSERT INTO alerts (id, subscriber_type, alert_type, all_alerts, no_show, late, irregularities, regularities, subscriber_id) VALUES (8651, 'student', 'sms', 'on', 'on', 'on', 'on', 'on', 8648);
INSERT INTO alerts (id, subscriber_type, alert_type, all_alerts, no_show, late, irregularities, regularities, subscriber_id) VALUES (8652, 'student', 'email', 'on', 'on', 'on', 'on', 'on', 8648);
INSERT INTO alerts (id, subscriber_type, alert_type, all_alerts, no_show, late, irregularities, regularities, subscriber_id) VALUES (8658, 'student', 'sms', 'on', 'on', 'on', 'on', 'on', 8655);
INSERT INTO alerts (id, subscriber_type, alert_type, all_alerts, no_show, late, irregularities, regularities, subscriber_id) VALUES (8659, 'student', 'email', 'on', 'on', 'on', 'on', 'on', 8655);
INSERT INTO alerts (id, subscriber_type, alert_type, all_alerts, no_show, late, irregularities, regularities, subscriber_id) VALUES (1055, 'staff', 'sms', 'on', 'on', 'on', 'on', 'on', 1053);
INSERT INTO alerts (id, subscriber_type, alert_type, all_alerts, no_show, late, irregularities, regularities, subscriber_id) VALUES (1056, 'staff', 'email', 'on', 'on', 'on', 'on', 'on', 1053);
INSERT INTO alerts (id, subscriber_type, alert_type, all_alerts, no_show, late, irregularities, regularities, subscriber_id) VALUES (1061, 'staff', 'sms', 'on', 'on', 'on', 'on', 'on', 1059);
INSERT INTO alerts (id, subscriber_type, alert_type, all_alerts, no_show, late, irregularities, regularities, subscriber_id) VALUES (1062, 'staff', 'email', 'on', 'on', 'on', 'on', 'on', 1059);
INSERT INTO alerts (id, subscriber_type, alert_type, all_alerts, no_show, late, irregularities, regularities, subscriber_id) VALUES (1010, 'student', 'email', 'on', 'on', 'on', 'on', 'on', 1006);
INSERT INTO alerts (id, subscriber_type, alert_type, all_alerts, no_show, late, irregularities, regularities, subscriber_id) VALUES (1009, 'student', 'sms', 'on', 'on', 'on', 'on', 'on', 1006);
INSERT INTO alerts (id, subscriber_type, alert_type, all_alerts, no_show, late, irregularities, regularities, subscriber_id) VALUES (1042, 'student', 'sms', 'on', 'on', 'on', 'on', 'on', 1039);
INSERT INTO alerts (id, subscriber_type, alert_type, all_alerts, no_show, late, irregularities, regularities, subscriber_id) VALUES (1069, 'staff', 'email', 'on', 'on', 'on', 'on', 'on', 1066);
INSERT INTO alerts (id, subscriber_type, alert_type, all_alerts, no_show, late, irregularities, regularities, subscriber_id) VALUES (1050, 'student', 'email', 'on', 'on', 'on', 'on', 'on', 1046);
INSERT INTO alerts (id, subscriber_type, alert_type, all_alerts, no_show, late, irregularities, regularities, subscriber_id) VALUES (1049, 'student', 'sms', 'on', 'on', 'on', 'on', 'on', 1046);
INSERT INTO alerts (id, subscriber_type, alert_type, all_alerts, no_show, late, irregularities, regularities, subscriber_id) VALUES (1043, 'student', 'email', 'on', 'on', 'on', 'on', 'on', 1039);
INSERT INTO alerts (id, subscriber_type, alert_type, all_alerts, no_show, late, irregularities, regularities, subscriber_id) VALUES (1068, 'staff', 'sms', 'on', 'on', 'on', 'on', 'on', 1066);
INSERT INTO alerts (id, subscriber_type, alert_type, all_alerts, no_show, late, irregularities, regularities, subscriber_id) VALUES (13902, 'guardian', 'email', 'on', 'on', 'on', 'on', 'on', 13903);
INSERT INTO alerts (id, subscriber_type, alert_type, all_alerts, no_show, late, irregularities, regularities, subscriber_id) VALUES (13901, 'guardian', 'sms', 'off', 'off', 'off', 'off', 'off', 13903);
INSERT INTO alerts (id, subscriber_type, alert_type, all_alerts, no_show, late, irregularities, regularities, subscriber_id) VALUES (15875, 'student', 'email', 'on', 'on', 'on', 'on', 'on', 15870);
INSERT INTO alerts (id, subscriber_type, alert_type, all_alerts, no_show, late, irregularities, regularities, subscriber_id) VALUES (15876, 'student', 'sms', 'on', 'on', 'on', 'on', 'on', 15870);


--
-- TOC entry 2337 (class 0 OID 0)
-- Dependencies: 174
-- Name: alerts_id_seq; Type: SEQUENCE SET; Schema: public; Owner: easycommute
--

SELECT pg_catalog.setval('alerts_id_seq', 1, false);


--
-- TOC entry 2323 (class 0 OID 97149)
-- Dependencies: 214
-- Data for Name: attendance; Type: TABLE DATA; Schema: public; Owner: easycommute
--



--
-- TOC entry 2328 (class 0 OID 97437)
-- Dependencies: 219
-- Data for Name: bus_gps_data; Type: TABLE DATA; Schema: public; Owner: easycommute
--



--
-- TOC entry 2292 (class 0 OID 64589)
-- Dependencies: 183
-- Data for Name: drivers; Type: TABLE DATA; Schema: public; Owner: easycommute
--

INSERT INTO drivers (id, driver_id, rfid_number, driver_name, contact_number, available, active, street, city, state, zip, country) VALUES (8097, 'KLWG490', 'KD035635263490WG', 'Walter Gabbert', '6135848291', 1, 0, '120 Stubble Rd', 'Fitzroy Harbour', 'ON', 'K0A1X0', 'Canada');
INSERT INTO drivers (id, driver_id, rfid_number, driver_name, contact_number, available, active, street, city, state, zip, country) VALUES (8130, 'KLKF167', 'KD024576507167KF', 'Kevin Fields', '6137957617', 1, 0, '290 Old Quarry Rd', 'Woodlawn', 'ON', 'K0A3M0', 'Canada');
INSERT INTO drivers (id, driver_id, rfid_number, driver_name, contact_number, available, active, street, city, state, zip, country) VALUES (8128, 'KLJJ929', 'KD027240922929JJ', 'James Johnson', '6137373351', 1, 0, '1267 Bayview Dr', 'Woodlawn', 'ON', 'K0A3M0', 'Canada');
INSERT INTO drivers (id, driver_id, rfid_number, driver_name, contact_number, available, active, street, city, state, zip, country) VALUES (8131, 'KLAL226', 'KD043330874226AL', 'Al Leeman', '6135391895', 1, 0, '226 Riddledale Rd', 'Kinburn', 'ON', 'K0A2H0', 'Canada');
INSERT INTO drivers (id, driver_id, rfid_number, driver_name, contact_number, available, active, street, city, state, zip, country) VALUES (8132, 'KLCK490', 'KD057502991490CK', 'Chris Kaepernick', '6137158719', 1, 0, '3911 Carp Rd', 'Ottawa', 'ON', 'K0A1L0', 'Canada');
INSERT INTO drivers (id, driver_id, rfid_number, driver_name, contact_number, available, active, street, city, state, zip, country) VALUES (8133, 'KLPD382', 'KD061371821382PD', 'Peter Dawson', '6135209660', 1, 0, '8498 McArton Rd', 'Ottawa', 'ON', 'K0A1B0', 'Canada');
INSERT INTO drivers (id, driver_id, rfid_number, driver_name, contact_number, available, active, street, city, state, zip, country) VALUES (8134, 'KLBE574', 'KD073745442574BE', 'Bart Ellington', '6133665653', 1, 0, '577 Ski Hill Rd', 'Pakenham', 'ON', 'K0A2X0', 'Canada');
INSERT INTO drivers (id, driver_id, rfid_number, driver_name, contact_number, available, active, street, city, state, zip, country) VALUES (8137, 'KLJP265', 'KD087831081265JP', 'John Patton', '6132770610', 1, 0, '2500 Burnt Wood Rd', 'Ottawa', 'ON', 'K0A1L0', 'Canada');
INSERT INTO drivers (id, driver_id, rfid_number, driver_name, contact_number, available, active, street, city, state, zip, country) VALUES (8138, 'KLKO209', 'KD099924019209KO', 'Kory Osgood', '6133612396', 1, 0, '4058 Cedarcrest Rd', 'Ottawa', 'ON', 'K0A3M0', 'Canada');
INSERT INTO drivers (id, driver_id, rfid_number, driver_name, contact_number, available, active, street, city, state, zip, country) VALUES (8139, 'KLMC696', 'KD109648481696MC', 'Marty Crabtree', '6139665467', 1, 0, '386 Dacosta St', 'Braeside', 'ON', 'K0A1G0', 'Canada');
INSERT INTO drivers (id, driver_id, rfid_number, driver_name, contact_number, available, active, street, city, state, zip, country) VALUES (8140, 'KLPC525', 'KD110514702525PC', 'Phillip Cox', '6132257862', 1, 0, '4569 Fern Acres Rd', 'Arnprior', 'ON', 'K7S3G7', 'Canada');
INSERT INTO drivers (id, driver_id, rfid_number, driver_name, contact_number, available, active, street, city, state, zip, country) VALUES (8141, 'KLFG936', 'KD123492364936FG', 'Fred Gore', '6136375718', 1, 0, '3811 Lowland Rd', 'White Lake', 'ON', 'K0A3L0', 'Canada');
INSERT INTO drivers (id, driver_id, rfid_number, driver_name, contact_number, available, active, street, city, state, zip, country) VALUES (8142, 'KLBV711', 'KD131449981711BV', 'Brian Ventrone', '6136494085', 1, 0, '231 Naismith Way', 'Mississippi Mills', 'ON', 'K0A1A0', 'Canada');
INSERT INTO drivers (id, driver_id, rfid_number, driver_name, contact_number, available, active, street, city, state, zip, country) VALUES (8143, 'KLTB602', 'KD147132449602TB', 'Tim Brock', '6137911824', 1, 0, '1807 Almonte Rd', 'Ottawa', 'ON ', 'K0A1L0', 'Canada');
INSERT INTO drivers (id, driver_id, rfid_number, driver_name, contact_number, available, active, street, city, state, zip, country) VALUES (8144, 'KLCH623', 'KD151172898623CH', 'Charles Hyde', '6137894202', 1, 0, '527 Beavertail Side Rd', 'Ottawa', 'ON ', 'K0A1L0', 'Canada');
INSERT INTO drivers (id, driver_id, rfid_number, driver_name, contact_number, available, active, street, city, state, zip, country) VALUES (738, '001', '0001826711', 'Micheal', '08686100041', 1, 0, 'forum', 'Bangalore', 'Karnataka', '500032', 'Anguilla');
INSERT INTO drivers (id, driver_id, rfid_number, driver_name, contact_number, available, active, street, city, state, zip, country) VALUES (8127, 'KLRC507', 'KD014576322507RC', 'Robert Carpenter', '6132255763', 0, 0, '7 Windigo Way', 'Dunrobin', 'ON', 'K0A1T0', 'Canada');


--
-- TOC entry 2329 (class 0 OID 97445)
-- Dependencies: 220
-- Data for Name: buses; Type: TABLE DATA; Schema: public; Owner: easycommute
--



--
-- TOC entry 2284 (class 0 OID 64541)
-- Dependencies: 175
-- Data for Name: daily_bus_coordinates; Type: TABLE DATA; Schema: public; Owner: easycommute
--

INSERT INTO daily_bus_coordinates (id, bus_speed, date, latitude, longitude, "time", trip_id) VALUES (16027, 2, '2015-09-05', 17.4438666666666684, 78.3486666666666736, '19:37', 752);
INSERT INTO daily_bus_coordinates (id, bus_speed, date, latitude, longitude, "time", trip_id) VALUES (16032, 2, '2015-09-05', 17.4438666666666684, 78.3486666666666736, '19:37', 752);
INSERT INTO daily_bus_coordinates (id, bus_speed, date, latitude, longitude, "time", trip_id) VALUES (16036, 20, '2015-09-05', 17.4438666666666684, 78.3486666666666736, '19:37', 752);
INSERT INTO daily_bus_coordinates (id, bus_speed, date, latitude, longitude, "time", trip_id) VALUES (16038, 20, '2015-09-05', 17.4438666666666684, 78.3486666666666736, '19:38', 752);
INSERT INTO daily_bus_coordinates (id, bus_speed, date, latitude, longitude, "time", trip_id) VALUES (16039, 20, '2015-09-05', 17.4451666666666654, 78.3526666666666642, '19:38', 752);
INSERT INTO daily_bus_coordinates (id, bus_speed, date, latitude, longitude, "time", trip_id) VALUES (16040, 20, '2015-09-05', 17.4451666666666654, 78.3526666666666642, '19:38', 752);
INSERT INTO daily_bus_coordinates (id, bus_speed, date, latitude, longitude, "time", trip_id) VALUES (16041, 20, '2015-09-05', 17.4451666666666654, 78.3526666666666642, '19:38', 752);
INSERT INTO daily_bus_coordinates (id, bus_speed, date, latitude, longitude, "time", trip_id) VALUES (16042, 20, '2015-09-05', 17.4451666666666654, 78.3526666666666642, '19:38', 752);
INSERT INTO daily_bus_coordinates (id, bus_speed, date, latitude, longitude, "time", trip_id) VALUES (16044, 20, '2015-09-05', 17.4451666666666654, 78.3526666666666642, '19:38', 752);
INSERT INTO daily_bus_coordinates (id, bus_speed, date, latitude, longitude, "time", trip_id) VALUES (16045, 20, '2015-09-05', 17.4451666666666654, 78.3526666666666642, '19:38', 752);
INSERT INTO daily_bus_coordinates (id, bus_speed, date, latitude, longitude, "time", trip_id) VALUES (16047, 20, '2015-09-05', 17.4449000000000005, 78.3528333333333364, '19:38', 752);
INSERT INTO daily_bus_coordinates (id, bus_speed, date, latitude, longitude, "time", trip_id) VALUES (16050, 10, '2015-09-05', 17.4464166666666678, 78.3499999999999943, '19:38', 752);
INSERT INTO daily_bus_coordinates (id, bus_speed, date, latitude, longitude, "time", trip_id) VALUES (16051, 10, '2015-09-05', 17.4438666666666684, 78.3541666666666714, '19:38', 752);
INSERT INTO daily_bus_coordinates (id, bus_speed, date, latitude, longitude, "time", trip_id) VALUES (16052, 10, '2015-09-05', 17.4438666666666684, 78.3541666666666714, '19:38', 752);
INSERT INTO daily_bus_coordinates (id, bus_speed, date, latitude, longitude, "time", trip_id) VALUES (16053, 10, '2015-09-05', 17.4438666666666684, 78.3541666666666714, '19:38', 752);
INSERT INTO daily_bus_coordinates (id, bus_speed, date, latitude, longitude, "time", trip_id) VALUES (16054, 85, '2015-09-05', 17.4438666666666684, 78.3541666666666714, '19:38', 752);
INSERT INTO daily_bus_coordinates (id, bus_speed, date, latitude, longitude, "time", trip_id) VALUES (16057, 10, '2015-09-05', 17.4438666666666684, 78.3541666666666714, '19:38', 752);
INSERT INTO daily_bus_coordinates (id, bus_speed, date, latitude, longitude, "time", trip_id) VALUES (16059, 10, '2015-09-05', 17.4438666666666684, 78.3541666666666714, '19:38', 752);
INSERT INTO daily_bus_coordinates (id, bus_speed, date, latitude, longitude, "time", trip_id) VALUES (16060, 20, '2015-09-05', 17.4448499999999989, 78.3529999999999944, '19:38', 752);
INSERT INTO daily_bus_coordinates (id, bus_speed, date, latitude, longitude, "time", trip_id) VALUES (16061, 15, '2015-09-05', 17.442733333333333, 78.3564999999999969, '19:38', 752);
INSERT INTO daily_bus_coordinates (id, bus_speed, date, latitude, longitude, "time", trip_id) VALUES (16062, 10, '2015-09-05', 17.4416666666666664, 78.3589999999999947, '19:38', 752);
INSERT INTO daily_bus_coordinates (id, bus_speed, date, latitude, longitude, "time", trip_id) VALUES (16063, 20, '2015-09-05', 17.4416666666666664, 78.3589999999999947, '19:38', 752);
INSERT INTO daily_bus_coordinates (id, bus_speed, date, latitude, longitude, "time", trip_id) VALUES (16064, 20, '2015-09-05', 17.4416666666666664, 78.3589999999999947, '19:39', 752);
INSERT INTO daily_bus_coordinates (id, bus_speed, date, latitude, longitude, "time", trip_id) VALUES (16065, 85, '2015-09-05', 17.4416666666666664, 78.3589999999999947, '19:39', 752);
INSERT INTO daily_bus_coordinates (id, bus_speed, date, latitude, longitude, "time", trip_id) VALUES (16067, 20, '2015-09-05', 17.4416666666666664, 78.3589999999999947, '19:39', 752);
INSERT INTO daily_bus_coordinates (id, bus_speed, date, latitude, longitude, "time", trip_id) VALUES (16068, 20, '2015-09-05', 17.4416666666666664, 78.3589999999999947, '19:39', 752);
INSERT INTO daily_bus_coordinates (id, bus_speed, date, latitude, longitude, "time", trip_id) VALUES (16070, 10, '2015-09-05', 17.4416666666666664, 78.3589999999999947, '19:39', 752);
INSERT INTO daily_bus_coordinates (id, bus_speed, date, latitude, longitude, "time", trip_id) VALUES (16072, 10, '2015-09-05', 17.4407999999999994, 78.3605000000000018, '19:39', 752);
INSERT INTO daily_bus_coordinates (id, bus_speed, date, latitude, longitude, "time", trip_id) VALUES (16073, 20, '2015-09-05', 17.4404000000000003, 78.3610000000000042, '19:39', 752);
INSERT INTO daily_bus_coordinates (id, bus_speed, date, latitude, longitude, "time", trip_id) VALUES (16075, 15, '2015-09-05', 17.4399833333333341, 78.3618333333333368, '19:39', 752);
INSERT INTO daily_bus_coordinates (id, bus_speed, date, latitude, longitude, "time", trip_id) VALUES (16076, 20, '2015-09-05', 17.4399833333333341, 78.3618333333333368, '19:39', 752);
INSERT INTO daily_bus_coordinates (id, bus_speed, date, latitude, longitude, "time", trip_id) VALUES (16077, 85, '2015-09-05', 17.4399833333333341, 78.3618333333333368, '19:39', 752);
INSERT INTO daily_bus_coordinates (id, bus_speed, date, latitude, longitude, "time", trip_id) VALUES (16079, 20, '2015-09-05', 17.4399833333333341, 78.3618333333333368, '19:39', 752);
INSERT INTO daily_bus_coordinates (id, bus_speed, date, latitude, longitude, "time", trip_id) VALUES (16080, 25, '2015-09-05', 17.4399833333333341, 78.3618333333333368, '19:39', 752);
INSERT INTO daily_bus_coordinates (id, bus_speed, date, latitude, longitude, "time", trip_id) VALUES (16081, 22, '2015-09-05', 17.4350999999999985, 78.367999999999995, '19:39', 752);
INSERT INTO daily_bus_coordinates (id, bus_speed, date, latitude, longitude, "time", trip_id) VALUES (16082, 40, '2015-09-05', 17.4342666666666659, 78.3684999999999974, '19:39', 752);
INSERT INTO daily_bus_coordinates (id, bus_speed, date, latitude, longitude, "time", trip_id) VALUES (16085, 10, '2015-09-05', 17.4334333333333333, 78.3695000000000022, '19:39', 752);
INSERT INTO daily_bus_coordinates (id, bus_speed, date, latitude, longitude, "time", trip_id) VALUES (16087, 20, '2015-09-05', 17.4327833333333331, 78.3701666666666625, '19:39', 752);
INSERT INTO daily_bus_coordinates (id, bus_speed, date, latitude, longitude, "time", trip_id) VALUES (16088, 25, '2015-09-05', 17.4306333333333328, 78.3728333333333325, '19:39', 752);
INSERT INTO daily_bus_coordinates (id, bus_speed, date, latitude, longitude, "time", trip_id) VALUES (16089, 20, '2015-09-05', 17.4306333333333328, 78.3728333333333325, '19:39', 752);
INSERT INTO daily_bus_coordinates (id, bus_speed, date, latitude, longitude, "time", trip_id) VALUES (16090, 30, '2015-09-05', 17.4306333333333328, 78.3728333333333325, '19:39', 752);
INSERT INTO daily_bus_coordinates (id, bus_speed, date, latitude, longitude, "time", trip_id) VALUES (16091, 20, '2015-09-05', 17.4306333333333328, 78.3728333333333325, '19:39', 752);
INSERT INTO daily_bus_coordinates (id, bus_speed, date, latitude, longitude, "time", trip_id) VALUES (16092, 85, '2015-09-05', 17.4306333333333328, 78.3728333333333325, '19:39', 752);
INSERT INTO daily_bus_coordinates (id, bus_speed, date, latitude, longitude, "time", trip_id) VALUES (16094, 15, '2015-09-05', 17.4306333333333328, 78.3728333333333325, '19:39', 752);
INSERT INTO daily_bus_coordinates (id, bus_speed, date, latitude, longitude, "time", trip_id) VALUES (16095, 20, '2015-09-05', 17.4306333333333328, 78.3728333333333325, '19:39', 752);
INSERT INTO daily_bus_coordinates (id, bus_speed, date, latitude, longitude, "time", trip_id) VALUES (16097, 12, '2015-09-05', 17.429783333333333, 78.3738333333333372, '19:39', 752);
INSERT INTO daily_bus_coordinates (id, bus_speed, date, latitude, longitude, "time", trip_id) VALUES (16099, 12, '2015-09-05', 17.429783333333333, 78.3738333333333372, '19:39', 752);


--
-- TOC entry 2285 (class 0 OID 64547)
-- Dependencies: 176
-- Data for Name: daily_bus_notification; Type: TABLE DATA; Schema: public; Owner: easycommute
--

INSERT INTO daily_bus_notification (id, bus_id, bus_licence_number, date, message_type, notification, status, "time", trip_id) VALUES (16028, 740, '99', '2015-09-05', 'bus_verylate', 'VeryLate:Bus [ 99 ] is VeryLate at Stop [ Ratnadeep ]', 'verylate', '19:37', 752);


--
-- TOC entry 2286 (class 0 OID 64553)
-- Dependencies: 177
-- Data for Name: daily_bus_stops; Type: TABLE DATA; Schema: public; Owner: easycommute
--

INSERT INTO daily_bus_stops (id, arrived_time, date, expected_time, is_eta_sent, is_stop_out_of_range, latitude, longitude, routestop_id, trip_id) VALUES (16046, '19:38', '2015-09-05', '18:42', false, false, 17.4449000000000005, 78.3528333333333364, 747, 752);
INSERT INTO daily_bus_stops (id, arrived_time, date, expected_time, is_eta_sent, is_stop_out_of_range, latitude, longitude, routestop_id, trip_id) VALUES (16049, '19:38', '2015-09-05', '18:41', false, false, 17.4464166666666678, 78.3499999999999943, 745, 752);
INSERT INTO daily_bus_stops (id, arrived_time, date, expected_time, is_eta_sent, is_stop_out_of_range, latitude, longitude, routestop_id, trip_id) VALUES (16071, '19:39', '2015-09-05', '18:43', false, true, 17.4407999999999994, 78.3605000000000018, 748, 752);
INSERT INTO daily_bus_stops (id, arrived_time, date, expected_time, is_eta_sent, is_stop_out_of_range, latitude, longitude, routestop_id, trip_id) VALUES (16084, '19:39', '2015-09-05', '18:44', false, true, 17.4334333333333333, 78.3695000000000022, 749, 752);
INSERT INTO daily_bus_stops (id, arrived_time, date, expected_time, is_eta_sent, is_stop_out_of_range, latitude, longitude, routestop_id, trip_id) VALUES (16096, '19:39', '2015-09-05', '18:45', false, false, 17.429783333333333, 78.3738333333333372, 750, 752);


--
-- TOC entry 2287 (class 0 OID 64559)
-- Dependencies: 178
-- Data for Name: daily_driver_data; Type: TABLE DATA; Schema: public; Owner: easycommute
--

INSERT INTO daily_driver_data (id, date, in_driver_id, in_latitude, in_longitude, in_time, is_session_closed_by_system, out_driver_id, out_latitude, out_longitude, out_time, trip_id, in_driver_name, out_driver_name) VALUES (16021, '2015-09-05', 738, 17.4438666666666684, 78.3486666666666736, '19:37', false, 738, 17.429783333333333, 78.3738333333333372, '19:39', 752, 'Micheal', 'Micheal');


--
-- TOC entry 2288 (class 0 OID 64565)
-- Dependencies: 179
-- Data for Name: daily_driver_speed; Type: TABLE DATA; Schema: public; Owner: easycommute
--

INSERT INTO daily_driver_speed (id, bus_licence_number, date, driver_id, driver_name, end_latitude, end_longitude, end_time, highest_speed, start_longitude, start_time, stat_latitude, trip_name) VALUES (16055, '99', '2015-09-05', 738, 'Micheal', 17.4438666666666684, 78.3541666666666714, '19:38:48', 85, 78.3541666666666714, '19:38:48', 17.4438666666666684, 'Drop Off one');
INSERT INTO daily_driver_speed (id, bus_licence_number, date, driver_id, driver_name, end_latitude, end_longitude, end_time, highest_speed, start_longitude, start_time, stat_latitude, trip_name) VALUES (16066, '99', '2015-09-05', 738, 'Micheal', 17.4416666666666664, 78.3589999999999947, '19:39:01', 85, 78.3589999999999947, '19:39:01', 17.4416666666666664, 'Drop Off one');
INSERT INTO daily_driver_speed (id, bus_licence_number, date, driver_id, driver_name, end_latitude, end_longitude, end_time, highest_speed, start_longitude, start_time, stat_latitude, trip_name) VALUES (16078, '99', '2015-09-05', 738, 'Micheal', 17.4399833333333341, 78.3618333333333368, '19:39:15', 85, 78.3618333333333368, '19:39:15', 17.4399833333333341, 'Drop Off one');
INSERT INTO daily_driver_speed (id, bus_licence_number, date, driver_id, driver_name, end_latitude, end_longitude, end_time, highest_speed, start_longitude, start_time, stat_latitude, trip_name) VALUES (16093, '99', '2015-09-05', 738, 'Micheal', 17.4306333333333328, 78.3728333333333325, '19:39:31', 85, 78.3728333333333325, '19:39:31', 17.4306333333333328, 'Drop Off one');


--
-- TOC entry 2289 (class 0 OID 64571)
-- Dependencies: 180
-- Data for Name: daily_running_busses; Type: TABLE DATA; Schema: public; Owner: easycommute
--

INSERT INTO daily_running_busses (id, arrived_time, bus_status, current_stop, date, driver_id, is_bus_arrived_to_school, is_bus_out_of_school, is_message_sent_to_first_stop, latitude, longitude, students_in_bus, trip_end_time, trip_id, trip_start_time, trip_status) VALUES (16020, '19:39', 'verylate', 'Ratnadeep', '2015-09-05', 738, false, false, false, '17.44386666666667', '78.34866666666667', 1, '19:48', 752, '11:12', 'over');


--
-- TOC entry 2290 (class 0 OID 64577)
-- Dependencies: 181
-- Data for Name: daily_subscriber_data; Type: TABLE DATA; Schema: public; Owner: easycommute
--

INSERT INTO daily_subscriber_data (id, date, in_latitude, in_longitude, in_stop, in_time, out_latitude, out_longitude, out_stop, out_time, subscriber_boarded_wrong_bus, subscriber_boarded_wrong_stop_inbound, subscriber_boarded_wrong_stop_outbound, subscriber_boarded_wrong_trip, subscriber_id, subscriber_name, subscriber_type, trip_id) VALUES (16030, '2015-09-05', 17.4438666666666684, 78.3486666666666736, 'school', '19:37', 0, 0, 'none', 'none', 'none', 'none', NULL, 'none', 1039, 'Syed Samiuddin', 'student', 752);
INSERT INTO daily_subscriber_data (id, date, in_latitude, in_longitude, in_stop, in_time, out_latitude, out_longitude, out_stop, out_time, subscriber_boarded_wrong_bus, subscriber_boarded_wrong_stop_inbound, subscriber_boarded_wrong_stop_outbound, subscriber_boarded_wrong_trip, subscriber_id, subscriber_name, subscriber_type, trip_id) VALUES (16034, '2015-09-05', 17.4438666666666684, 78.3486666666666736, 'school', '19:37', 17.4334333333333333, 78.3695000000000022, 'Al Saba', '19:39', 'none', 'none', 'none', 'none', 1046, 'kranthi kumar', 'student', 752);
INSERT INTO daily_subscriber_data (id, date, in_latitude, in_longitude, in_stop, in_time, out_latitude, out_longitude, out_stop, out_time, subscriber_boarded_wrong_bus, subscriber_boarded_wrong_stop_inbound, subscriber_boarded_wrong_stop_outbound, subscriber_boarded_wrong_trip, subscriber_id, subscriber_name, subscriber_type, trip_id) VALUES (16037, '2015-09-05', 17.4438666666666684, 78.3486666666666736, 'school', '19:38', 17.429783333333333, 78.3738333333333372, 'Ratnadeep', '19:39', 'none', 'none', 'Ratnadeep*udipi', 'none', 1053, 'Sachin', 'staff', 752);


--
-- TOC entry 2291 (class 0 OID 64583)
-- Dependencies: 182
-- Data for Name: dailyrunningbusesjspentity; Type: TABLE DATA; Schema: public; Owner: easycommute
--



--
-- TOC entry 2338 (class 0 OID 0)
-- Dependencies: 184
-- Name: drivers_id_seq; Type: SEQUENCE SET; Schema: public; Owner: easycommute
--

SELECT pg_catalog.setval('drivers_id_seq', 1, false);


--
-- TOC entry 2294 (class 0 OID 64597)
-- Dependencies: 185
-- Data for Name: eta_process; Type: TABLE DATA; Schema: public; Owner: easycommute
--

INSERT INTO eta_process (id, actual_time, bus_id, bus_licence_number, count, date, expected_time, is_eta_sent, route_id, stop_id, stop_name, stop_number, trip_id, trip_type) VALUES (16025, '18:42', 740, '99', 2, '2015-09-05', '19:38', true, 744, 89, 'iiit bus stop', 2, 752, 'Drop Off');
INSERT INTO eta_process (id, actual_time, bus_id, bus_licence_number, count, date, expected_time, is_eta_sent, route_id, stop_id, stop_name, stop_number, trip_id, trip_type) VALUES (16026, '18:41', 740, '99', 2, '2015-09-05', '19:38', true, 744, 88, 'slabs', 1, 752, 'Drop Off');
INSERT INTO eta_process (id, actual_time, bus_id, bus_licence_number, count, date, expected_time, is_eta_sent, route_id, stop_id, stop_name, stop_number, trip_id, trip_type) VALUES (16022, '18:45', 740, '99', 3, '2015-09-05', '19:39', true, 744, 742, 'Ratnadeep', 5, 752, 'Drop Off');
INSERT INTO eta_process (id, actual_time, bus_id, bus_licence_number, count, date, expected_time, is_eta_sent, route_id, stop_id, stop_name, stop_number, trip_id, trip_type) VALUES (16023, '18:44', 740, '99', 3, '2015-09-05', '19:39', true, 744, 741, 'Al Saba', 4, 752, 'Drop Off');
INSERT INTO eta_process (id, actual_time, bus_id, bus_licence_number, count, date, expected_time, is_eta_sent, route_id, stop_id, stop_name, stop_number, trip_id, trip_type) VALUES (16024, '18:43', 740, '99', 3, '2015-09-05', '19:39', true, 744, 633, 'udipi', 3, 752, 'Drop Off');


--
-- TOC entry 2327 (class 0 OID 97322)
-- Dependencies: 218
-- Data for Name: gps_data; Type: TABLE DATA; Schema: public; Owner: easycommute
--

INSERT INTO gps_data (id, busid, gpslat, gpslong, tripid) VALUES (1, '1', '20.0', '24.0', '20');
INSERT INTO gps_data (id, busid, gpslat, gpslong, tripid) VALUES (2, '1', '20.0', '24.0', '20');


--
-- TOC entry 2295 (class 0 OID 64603)
-- Dependencies: 186
-- Data for Name: guardians; Type: TABLE DATA; Schema: public; Owner: easycommute
--

INSERT INTO guardians (id, email, email_alert_id, first_name, last_name, mobile_number, relation, sms_alert_id, student_id) VALUES (8548, 'plexustracker@gmail.com', 8547, 'Steve', 'Doe', '6133175639', 'Uncle', 8546, 8433);
INSERT INTO guardians (id, email, email_alert_id, first_name, last_name, mobile_number, relation, sms_alert_id, student_id) VALUES (13903, 'john@gmail.com', 13902, 'Syed', 'Samiuddin', '08686178648', 'Uncle', 13901, 1039);


--
-- TOC entry 2339 (class 0 OID 0)
-- Dependencies: 222
-- Name: hibernate_sequence; Type: SEQUENCE SET; Schema: public; Owner: easycommute
--

SELECT pg_catalog.setval('hibernate_sequence', 4, true);


--
-- TOC entry 2296 (class 0 OID 64611)
-- Dependencies: 187
-- Data for Name: parents; Type: TABLE DATA; Schema: public; Owner: easycommute
--

INSERT INTO parents (id, first_name, last_name, email, student_id, mobile) VALUES (8434, 'Jennifer', 'Manuel', 'plexustracker@gmail.com', 8433, '6133175639');
INSERT INTO parents (id, first_name, last_name, email, student_id, mobile) VALUES (8441, 'Jennifer', 'Manuel', 'plexustracker@gmail.com', 8440, '6133175639');
INSERT INTO parents (id, first_name, last_name, email, student_id, mobile) VALUES (8448, 'Kyle', 'Schmidt', 'plexustracker@gmail.com', 8447, '6133175639');
INSERT INTO parents (id, first_name, last_name, email, student_id, mobile) VALUES (8455, 'Lena', 'Woods', 'plexustracker@gmail.com', 8454, '6133175639');
INSERT INTO parents (id, first_name, last_name, email, student_id, mobile) VALUES (8462, 'Stephen', 'Watkins', 'plexustracker@gmail.com', 8461, '6133175639');
INSERT INTO parents (id, first_name, last_name, email, student_id, mobile) VALUES (8469, 'Stephen', 'Watkins', 'plexustracker@gmail.com', 8468, '6133175639');
INSERT INTO parents (id, first_name, last_name, email, student_id, mobile) VALUES (8476, 'Joyce', 'Orton Hogan', 'plexustracker@gmail.com', 8475, '6133175639');
INSERT INTO parents (id, first_name, last_name, email, student_id, mobile) VALUES (8483, 'Wendy', 'Graham', 'plexustracker@gmail.com', 8482, '6133175639');
INSERT INTO parents (id, first_name, last_name, email, student_id, mobile) VALUES (8497, 'Frank', 'Jackson', 'plexustracker@gmail.com', 8496, '6133175639');
INSERT INTO parents (id, first_name, last_name, email, student_id, mobile) VALUES (8490, 'Frank', 'Jackson', 'plexustracker@gmail.com', 8489, '6133175639');
INSERT INTO parents (id, first_name, last_name, email, student_id, mobile) VALUES (8504, 'Dee', 'Wilson', 'plexustracker@gmail.com', 8503, '6133175639');
INSERT INTO parents (id, first_name, last_name, email, student_id, mobile) VALUES (8511, 'Margaret', 'Gilmour', 'plexustracker@gmail.com', 8510, '6133175639');
INSERT INTO parents (id, first_name, last_name, email, student_id, mobile) VALUES (8525, 'Irene', 'Brooks', 'plexustracker@gmail.com', 8524, '6133175639');
INSERT INTO parents (id, first_name, last_name, email, student_id, mobile) VALUES (8518, 'Margaret', 'Gilmour', 'plexustracker@gmail.com', 8517, '6133175639');
INSERT INTO parents (id, first_name, last_name, email, student_id, mobile) VALUES (8532, 'Keith', 'Brown', 'plexustracker@gmail.com', 8531, '6133175639');
INSERT INTO parents (id, first_name, last_name, email, student_id, mobile) VALUES (8539, 'Keith', 'Brown', 'plexustracker@gmail.com', 8538, '6133175639');
INSERT INTO parents (id, first_name, last_name, email, student_id, mobile) VALUES (8550, 'Kate', 'Summers Robey', 'plexustracker@gmail.com', 8549, '6133175639');
INSERT INTO parents (id, first_name, last_name, email, student_id, mobile) VALUES (8557, 'Troy', 'Dean', 'plexustracker@gmail.com', 8556, '6133175639');
INSERT INTO parents (id, first_name, last_name, email, student_id, mobile) VALUES (8564, 'Darrell', 'Hughes', 'plexustracker@gmail.com', 8563, '6133175639');
INSERT INTO parents (id, first_name, last_name, email, student_id, mobile) VALUES (8571, 'Darrell', 'Hughes', 'plexustracker@gmail.com', 8570, '6133175639');
INSERT INTO parents (id, first_name, last_name, email, student_id, mobile) VALUES (8578, 'Laurie', 'Rivers', 'plexustracker@gmail.com', 8577, '6133175639');
INSERT INTO parents (id, first_name, last_name, email, student_id, mobile) VALUES (8586, 'Laurie', 'Rivers', 'plexustracker@gmail.com', 8585, '6133175639');
INSERT INTO parents (id, first_name, last_name, email, student_id, mobile) VALUES (8593, 'Marie', 'Powell', 'plexustracker@gmail.com', 8592, '6133175639');
INSERT INTO parents (id, first_name, last_name, email, student_id, mobile) VALUES (8600, 'Vicki', 'Ty Johnson', 'plexustracker@gmail.com', 8599, '6133175639');
INSERT INTO parents (id, first_name, last_name, email, student_id, mobile) VALUES (8607, 'Natalie', 'Sandborn', 'plexustracker@gmail.com', 8606, '6133175639');
INSERT INTO parents (id, first_name, last_name, email, student_id, mobile) VALUES (8614, 'Joelle', 'Henderson', 'plexustracker@gmail.com', 8613, '6133175639');
INSERT INTO parents (id, first_name, last_name, email, student_id, mobile) VALUES (8621, 'Joelle', 'Henderson', 'plexustracker@gmail.com', 8620, '6133175639');
INSERT INTO parents (id, first_name, last_name, email, student_id, mobile) VALUES (8628, 'Hannah', 'Richardson', 'plexustracker@gmail.com', 8627, '6133175639');
INSERT INTO parents (id, first_name, last_name, email, student_id, mobile) VALUES (8635, 'Scott', 'Glenn', 'plexustracker@gmail.com', 8634, '6133175639');
INSERT INTO parents (id, first_name, last_name, email, student_id, mobile) VALUES (8642, 'Scott', 'Glenn', 'plexustracker@gmail.com', 8641, '6133175639');
INSERT INTO parents (id, first_name, last_name, email, student_id, mobile) VALUES (8649, 'Colleen', 'Eady', 'plexustracker@gmail.com', 8648, '6133175639');
INSERT INTO parents (id, first_name, last_name, email, student_id, mobile) VALUES (8656, 'Colleen', 'Eady', 'plexustracker@gmail.com', 8655, '6133175639');
INSERT INTO parents (id, first_name, last_name, email, student_id, mobile) VALUES (1007, 'John', 'DFJSK', 'syedsami4u786@gmail.com', 1006, '08099927902');
INSERT INTO parents (id, first_name, last_name, email, student_id, mobile) VALUES (1040, 'Syed', 'Samiuddin', 'syedsami4u786@gmail.com', 1039, '08099927902');
INSERT INTO parents (id, first_name, last_name, email, student_id, mobile) VALUES (1047, 'Syed', 'Samiuddin', 'syedsami4u786@gmail.com', 1046, '08099927902');
INSERT INTO parents (id, first_name, last_name, email, student_id, mobile) VALUES (15874, 'Hussey', 'None', 'abc@xyz.com', 15870, '09703276114');


--
-- TOC entry 2340 (class 0 OID 0)
-- Dependencies: 188
-- Name: parents_id_seq; Type: SEQUENCE SET; Schema: public; Owner: easycommute
--

SELECT pg_catalog.setval('parents_id_seq', 1, false);


--
-- TOC entry 2298 (class 0 OID 64619)
-- Dependencies: 189
-- Data for Name: plexus_mediator_record; Type: TABLE DATA; Schema: public; Owner: easycommute
--

INSERT INTO plexus_mediator_record (id, date, device_type, message, mobile_number, notification_id, person_name, status, student_id, "time", trip_name, trip_type) VALUES (16029, '2015-09-05', '"mobile"', 'Bus 99 will be late at stop Ratnadeep  and expected time to reach is  19:37', '08099927902', '55eaf6f75bb6d35c1ac4e7c3', 'Syed Samiuddin', '"failed"', 1039, '4:06:47', 'Drop Off one', 'Drop Off');
INSERT INTO plexus_mediator_record (id, date, device_type, message, mobile_number, notification_id, person_name, status, student_id, "time", trip_name, trip_type) VALUES (16031, '2015-09-05', '"mobile"', 'Bus 99 will be late at stop Al Saba  and expected time to reach is  19:37', '08099927902', '55eaf6fe5bb6d35c1ac4e7c4', 'Syed Samiuddin', '"failed"', 1046, '4:06:54', 'Drop Off one', 'Drop Off');
INSERT INTO plexus_mediator_record (id, date, device_type, message, mobile_number, notification_id, person_name, status, student_id, "time", trip_name, trip_type) VALUES (16033, '2015-09-05', '"mobile"', 'Bus 99 will be late at stop udipi  and expected time to reach is  19:37', '08099927902', '55eaf7075bb6d35c1ac4e7c5', 'John DFJSK', '"failed"', 1006, '4:07:03', 'Drop Off one', 'Drop Off');
INSERT INTO plexus_mediator_record (id, date, device_type, message, mobile_number, notification_id, person_name, status, student_id, "time", trip_name, trip_type) VALUES (16035, '2015-09-05', '"mobile"', 'Bus 99 will be late at stop udipi  and expected time to reach is  19:37', '09703276114', '55eaf7085bb6d35c1ac4e7c6', 'Hussey None', '"failed"', 15870, '4:07:04', 'Drop Off one', 'Drop Off');
INSERT INTO plexus_mediator_record (id, date, device_type, message, mobile_number, notification_id, person_name, status, student_id, "time", trip_name, trip_type) VALUES (16043, '2015-09-05', '"mobile"', 'Bus 99 will be late at stop Ratnadeep  and expected time to reach is  19:38', '08099927902', '55eaf7265bb6d35c1ac4e7cb', 'Syed Samiuddin', '"failed"', 1039, '4:07:34', 'Drop Off one', 'Drop Off');
INSERT INTO plexus_mediator_record (id, date, device_type, message, mobile_number, notification_id, person_name, status, student_id, "time", trip_name, trip_type) VALUES (16048, '2015-09-05', '"mobile"', 'Bus 99 will be late at stop Al Saba  and expected time to reach is  19:38', '08099927902', '55eaf72b5bb6d35c1ac4e7cc', 'Syed Samiuddin', '"failed"', 1046, '4:07:39', 'Drop Off one', 'Drop Off');
INSERT INTO plexus_mediator_record (id, date, device_type, message, mobile_number, notification_id, person_name, status, student_id, "time", trip_name, trip_type) VALUES (16056, '2015-09-05', '"mobile"', 'Bus 99 will be late at stop udipi  and expected time to reach is  19:38', '08099927902', '55eaf7345bb6d35c1ac4e7cd', 'John DFJSK', '"failed"', 1006, '4:07:48', 'Drop Off one', 'Drop Off');
INSERT INTO plexus_mediator_record (id, date, device_type, message, mobile_number, notification_id, person_name, status, student_id, "time", trip_name, trip_type) VALUES (16058, '2015-09-05', '"mobile"', 'Bus 99 will be late at stop udipi  and expected time to reach is  19:38', '09703276114', '55eaf7355bb6d35c1ac4e7ce', 'Hussey None', '"failed"', 15870, '4:07:49', 'Drop Off one', 'Drop Off');
INSERT INTO plexus_mediator_record (id, date, device_type, message, mobile_number, notification_id, person_name, status, student_id, "time", trip_name, trip_type) VALUES (16069, '2015-09-05', '"mobile"', 'Bus 99 will be late at stop Ratnadeep  and expected time to reach is  19:39', '08099927902', '55eaf7455bb6d35c1ac4e7cf', 'Syed Samiuddin', '"failed"', 1039, '4:08:05', 'Drop Off one', 'Drop Off');
INSERT INTO plexus_mediator_record (id, date, device_type, message, mobile_number, notification_id, person_name, status, student_id, "time", trip_name, trip_type) VALUES (16074, '2015-09-05', '"mobile"', 'Bus 99 will be late at stop Al Saba  and expected time to reach is  19:39', '08099927902', '55eaf74a5bb6d35c1ac4e7d0', 'Syed Samiuddin', '"failed"', 1046, '4:08:10', 'Drop Off one', 'Drop Off');
INSERT INTO plexus_mediator_record (id, date, device_type, message, mobile_number, notification_id, person_name, status, student_id, "time", trip_name, trip_type) VALUES (16083, '2015-09-05', '"mobile"', 'Bus 99 will be late at stop udipi  and expected time to reach is  19:39', '08099927902', '55eaf7545bb6d35c1ac4e7d1', 'John DFJSK', '"failed"', 1006, '4:08:20', 'Drop Off one', 'Drop Off');
INSERT INTO plexus_mediator_record (id, date, device_type, message, mobile_number, notification_id, person_name, status, student_id, "time", trip_name, trip_type) VALUES (16086, '2015-09-05', '"mobile"', 'Bus 99 will be late at stop udipi  and expected time to reach is  19:39', '09703276114', '55eaf7555bb6d35c1ac4e7d2', 'Hussey None', '"failed"', 15870, '4:08:21', 'Drop Off one', 'Drop Off');


--
-- TOC entry 2326 (class 0 OID 97167)
-- Dependencies: 217
-- Data for Name: price_fare; Type: TABLE DATA; Schema: public; Owner: easycommute
--

INSERT INTO price_fare (fare_id, source_stop_id, dest_stop_id, fare, auto_fare, bus_fare) VALUES (13, 8312, 8312, 12, 12, 12);
INSERT INTO price_fare (fare_id, source_stop_id, dest_stop_id, fare, auto_fare, bus_fare) VALUES (14, 8312, 8311, 3, 2, 1);


--
-- TOC entry 2341 (class 0 OID 0)
-- Dependencies: 216
-- Name: price_fare_fare_id_seq; Type: SEQUENCE SET; Schema: public; Owner: easycommute
--

SELECT pg_catalog.setval('price_fare_fare_id_seq', 10, true);


--
-- TOC entry 2299 (class 0 OID 64625)
-- Dependencies: 190
-- Data for Name: rfid_cards; Type: TABLE DATA; Schema: public; Owner: easycommute
--

INSERT INTO rfid_cards (id, rfid_number, type, available, allocated_to, allocated_time, allocated_person_name, active) VALUES (8176, 'KL0045764518665A', 'staff', 1, 8190, 'Fri May 15 14:44:21 EDT 2015', 'Helen Chandler', 0);
INSERT INTO rfid_cards (id, rfid_number, type, available, allocated_to, allocated_time, allocated_person_name, active) VALUES (8151, 'KD014576606235EH', 'student', 1, 8475, 'Thu May 21 15:36:11 EDT 2015', 'Emily Hogan', 0);
INSERT INTO rfid_cards (id, rfid_number, type, available, allocated_to, allocated_time, allocated_person_name, active) VALUES (8112, 'KD014576322507RC', 'driver', 1, 8127, 'Mon May 11 14:25:54 EDT 2015', 'Robert Carpenter', 0);
INSERT INTO rfid_cards (id, rfid_number, type, available, allocated_to, allocated_time, allocated_person_name, active) VALUES (8113, 'KD027240922929JJ', 'driver', 1, 8128, 'Mon May 11 15:03:32 EDT 2015', 'James Johnson', 0);
INSERT INTO rfid_cards (id, rfid_number, type, available, allocated_to, allocated_time, allocated_person_name, active) VALUES (8129, 'KD024576507167KF', 'driver', 1, 8130, 'Mon May 11 15:20:36 EDT 2015', 'Kevin Fields', 0);
INSERT INTO rfid_cards (id, rfid_number, type, available, allocated_to, allocated_time, allocated_person_name, active) VALUES (8114, 'KD035635263490WG', 'driver', 1, 8097, 'Mon May 11 15:39:19 EDT 2015', 'Walter Gabbert', 0);
INSERT INTO rfid_cards (id, rfid_number, type, available, allocated_to, allocated_time, allocated_person_name, active) VALUES (8177, 'KL0045764518664C', 'staff', 1, 8196, 'Fri May 15 15:21:21 EDT 2015', 'Michael Smith', 0);
INSERT INTO rfid_cards (id, rfid_number, type, available, allocated_to, allocated_time, allocated_person_name, active) VALUES (8115, 'KD043330874226AL', 'driver', 1, 8131, 'Wed May 13 14:47:00 EDT 2015', 'Al Leeman', 0);
INSERT INTO rfid_cards (id, rfid_number, type, available, allocated_to, allocated_time, allocated_person_name, active) VALUES (8116, 'KD057502991490CK', 'driver', 1, 8132, 'Wed May 13 16:45:33 EDT 2015', 'Chris Kaepernick', 0);
INSERT INTO rfid_cards (id, rfid_number, type, available, allocated_to, allocated_time, allocated_person_name, active) VALUES (8117, 'KD061371821382PD', 'driver', 1, 8133, 'Wed May 13 16:46:47 EDT 2015', 'Peter Dawson', 0);
INSERT INTO rfid_cards (id, rfid_number, type, available, allocated_to, allocated_time, allocated_person_name, active) VALUES (8118, 'KD073745442574BE', 'driver', 1, 8134, 'Wed May 13 16:51:05 EDT 2015', 'Bart Ellington', 0);
INSERT INTO rfid_cards (id, rfid_number, type, available, allocated_to, allocated_time, allocated_person_name, active) VALUES (8119, 'KD087831081265JP', 'driver', 1, 8137, 'Fri May 15 13:10:50 EDT 2015', 'John Patton', 0);
INSERT INTO rfid_cards (id, rfid_number, type, available, allocated_to, allocated_time, allocated_person_name, active) VALUES (8120, 'KD099924019209KO', 'driver', 1, 8138, 'Fri May 15 13:12:31 EDT 2015', 'Kory Osgood', 0);
INSERT INTO rfid_cards (id, rfid_number, type, available, allocated_to, allocated_time, allocated_person_name, active) VALUES (8121, 'KD109648481696MC', 'driver', 1, 8139, 'Fri May 15 13:30:06 EDT 2015', 'Marty Crabtree', 0);
INSERT INTO rfid_cards (id, rfid_number, type, available, allocated_to, allocated_time, allocated_person_name, active) VALUES (8122, 'KD110514702525PC', 'driver', 1, 8140, 'Fri May 15 13:31:59 EDT 2015', 'Phillip Cox', 0);
INSERT INTO rfid_cards (id, rfid_number, type, available, allocated_to, allocated_time, allocated_person_name, active) VALUES (8123, 'KD123492364936FG', 'driver', 1, 8141, 'Fri May 15 13:33:08 EDT 2015', 'Fred Gore', 0);
INSERT INTO rfid_cards (id, rfid_number, type, available, allocated_to, allocated_time, allocated_person_name, active) VALUES (8124, 'KD131449981711BV', 'driver', 1, 8142, 'Fri May 15 13:34:17 EDT 2015', 'Brian Ventrone', 0);
INSERT INTO rfid_cards (id, rfid_number, type, available, allocated_to, allocated_time, allocated_person_name, active) VALUES (8125, 'KD147132449602TB', 'driver', 1, 8143, 'Fri May 15 13:37:00 EDT 2015', 'Tim Brock', 0);
INSERT INTO rfid_cards (id, rfid_number, type, available, allocated_to, allocated_time, allocated_person_name, active) VALUES (8126, 'KD151172898623CH', 'driver', 1, 8144, 'Fri May 15 13:38:31 EDT 2015', 'Charles Hyde', 0);
INSERT INTO rfid_cards (id, rfid_number, type, available, allocated_to, allocated_time, allocated_person_name, active) VALUES (8178, 'KL0045764518667F', 'staff', 1, 8202, 'Fri May 15 15:23:44 EDT 2015', 'Marjorie Goodwin', 0);
INSERT INTO rfid_cards (id, rfid_number, type, available, allocated_to, allocated_time, allocated_person_name, active) VALUES (8179, 'KL0045764518667E', 'staff', 1, 8208, 'Fri May 15 15:25:28 EDT 2015', 'Daniel Lawson', 0);
INSERT INTO rfid_cards (id, rfid_number, type, available, allocated_to, allocated_time, allocated_person_name, active) VALUES (8187, 'KL0045764518665C', 'staff', 1, 8214, 'Fri May 15 15:27:46 EDT 2015', 'Brett Charles', 0);
INSERT INTO rfid_cards (id, rfid_number, type, available, allocated_to, allocated_time, allocated_person_name, active) VALUES (8181, 'KL0045764518666C', 'staff', 1, 8220, 'Fri May 15 15:30:00 EDT 2015', 'Janet Bryant', 0);
INSERT INTO rfid_cards (id, rfid_number, type, available, allocated_to, allocated_time, allocated_person_name, active) VALUES (8188, 'KL0045764518662B', 'staff', 1, 8262, 'Fri May 15 15:38:20 EDT 2015', 'Libbey Pryor', 0);
INSERT INTO rfid_cards (id, rfid_number, type, available, allocated_to, allocated_time, allocated_person_name, active) VALUES (8183, 'KL0045764518662A', 'staff', 1, 8232, 'Fri May 15 15:33:21 EDT 2015', 'Catherine Sylvester', 0);
INSERT INTO rfid_cards (id, rfid_number, type, available, allocated_to, allocated_time, allocated_person_name, active) VALUES (8184, 'KL0045764518663C', 'staff', 1, 8238, 'Fri May 15 15:34:18 EDT 2015', 'Monique Gainey', 0);
INSERT INTO rfid_cards (id, rfid_number, type, available, allocated_to, allocated_time, allocated_person_name, active) VALUES (8150, 'KD014576048052SW', 'student', 1, 8468, 'Thu May 21 15:31:33 EDT 2015', 'Sam Watkins', 0);
INSERT INTO rfid_cards (id, rfid_number, type, available, allocated_to, allocated_time, allocated_person_name, active) VALUES (8186, 'KL0045764518664A', 'staff', 1, 8250, 'Fri May 15 15:35:32 EDT 2015', 'Carla Ladler', 0);
INSERT INTO rfid_cards (id, rfid_number, type, available, allocated_to, allocated_time, allocated_person_name, active) VALUES (8180, 'KL0045764518665B', 'staff', 1, 8256, 'Fri May 15 15:36:18 EDT 2015', 'Dianne Morrell', 0);
INSERT INTO rfid_cards (id, rfid_number, type, available, allocated_to, allocated_time, allocated_person_name, active) VALUES (8152, 'KD014576183658AG', 'student', 1, 8482, 'Thu May 21 15:38:15 EDT 2015', 'Ashley Graham', 0);
INSERT INTO rfid_cards (id, rfid_number, type, available, allocated_to, allocated_time, allocated_person_name, active) VALUES (8182, 'KL0045764518664B', 'staff', 1, 8226, 'Fri May 15 15:37:50 EDT 2015', 'Miles Butler', 0);
INSERT INTO rfid_cards (id, rfid_number, type, available, allocated_to, allocated_time, allocated_person_name, active) VALUES (8153, 'KD014576614058TJ', 'student', 1, 8489, 'Thu May 21 16:07:27 EDT 2015', 'Tyler Jackson', 0);
INSERT INTO rfid_cards (id, rfid_number, type, available, allocated_to, allocated_time, allocated_person_name, active) VALUES (8185, 'KL0045764518662C', 'staff', 1, 8244, 'Fri May 15 15:39:00 EDT 2015', 'Jason Broomfield', 0);
INSERT INTO rfid_cards (id, rfid_number, type, available, allocated_to, allocated_time, allocated_person_name, active) VALUES (8189, 'KL0045764518667A', 'staff', 1, 8268, 'Fri May 15 15:39:40 EDT 2015', 'Anna Weeks', 0);
INSERT INTO rfid_cards (id, rfid_number, type, available, allocated_to, allocated_time, allocated_person_name, active) VALUES (8154, 'KD014576614058BJ', 'student', 1, 8496, 'Thu May 21 16:09:02 EDT 2015', 'Bryden Jackson', 0);
INSERT INTO rfid_cards (id, rfid_number, type, available, allocated_to, allocated_time, allocated_person_name, active) VALUES (8147, 'KD014576546339NS', 'student', 1, 8447, 'Thu May 21 15:32:17 EDT 2015', 'Noah Schmidt', 0);
INSERT INTO rfid_cards (id, rfid_number, type, available, allocated_to, allocated_time, allocated_person_name, active) VALUES (8155, 'KD014576689449JW', 'student', 1, 8503, 'Thu May 21 16:18:05 EDT 2015', 'Jacob Wilson', 0);
INSERT INTO rfid_cards (id, rfid_number, type, available, allocated_to, allocated_time, allocated_person_name, active) VALUES (8145, 'KD014576265996SM', 'student', 1, 8433, 'Thu May 21 14:50:24 EDT 2015', 'Skylar Manuel', 0);
INSERT INTO rfid_cards (id, rfid_number, type, available, allocated_to, allocated_time, allocated_person_name, active) VALUES (8148, 'KD014576237234KW', 'student', 1, 8454, 'Thu May 21 15:32:53 EDT 2015', 'Katlyn Woods', 0);
INSERT INTO rfid_cards (id, rfid_number, type, available, allocated_to, allocated_time, allocated_person_name, active) VALUES (8156, 'KD014576778815OG', 'student', 1, 8510, 'Thu May 21 16:33:50 EDT 2015', 'Olivia Gilmour', 0);
INSERT INTO rfid_cards (id, rfid_number, type, available, allocated_to, allocated_time, allocated_person_name, active) VALUES (8159, 'KD014576022943MB', 'student', 1, 8531, 'Thu May 21 16:43:01 EDT 2015', 'Manny Brown', 0);
INSERT INTO rfid_cards (id, rfid_number, type, available, allocated_to, allocated_time, allocated_person_name, active) VALUES (8146, 'KD014576265996CM', 'student', 1, 8440, 'Thu May 21 15:23:23 EDT 2015', 'Chris Manuel', 0);
INSERT INTO rfid_cards (id, rfid_number, type, available, allocated_to, allocated_time, allocated_person_name, active) VALUES (8157, 'KD014576778815BG', 'student', 1, 8517, 'Thu May 21 16:36:58 EDT 2015', 'Bryan Gilmor', 0);
INSERT INTO rfid_cards (id, rfid_number, type, available, allocated_to, allocated_time, allocated_person_name, active) VALUES (8158, 'KD014576778815FB', 'student', 1, 8524, 'Thu May 21 16:40:17 EDT 2015', 'Fred Brooks', 0);
INSERT INTO rfid_cards (id, rfid_number, type, available, allocated_to, allocated_time, allocated_person_name, active) VALUES (8149, 'KD014576048052JW', 'student', 1, 8461, 'Thu May 21 15:33:19 EDT 2015', 'Jack Watkins', 0);
INSERT INTO rfid_cards (id, rfid_number, type, available, allocated_to, allocated_time, allocated_person_name, active) VALUES (8088, '0001832016', 'student', 0, 0, 'none', 'none', 0);
INSERT INTO rfid_cards (id, rfid_number, type, available, allocated_to, allocated_time, allocated_person_name, active) VALUES (8160, 'KD014576022943QB', 'student', 1, 8538, 'Thu May 21 16:44:59 EDT 2015', 'Quincy Brown', 0);
INSERT INTO rfid_cards (id, rfid_number, type, available, allocated_to, allocated_time, allocated_person_name, active) VALUES (8161, 'KD014576493707NR', 'student', 1, 8549, 'Fri May 22 15:19:56 EDT 2015', 'Nathan Robey', 0);
INSERT INTO rfid_cards (id, rfid_number, type, available, allocated_to, allocated_time, allocated_person_name, active) VALUES (8162, 'KD014576671912KD', 'student', 1, 8556, 'Fri May 22 15:23:38 EDT 2015', 'Kayden Dean', 0);
INSERT INTO rfid_cards (id, rfid_number, type, available, allocated_to, allocated_time, allocated_person_name, active) VALUES (8163, 'KD014576698443LH', 'student', 1, 8563, 'Fri May 22 15:59:16 EDT 2015', 'Lindsay Hughes', 0);
INSERT INTO rfid_cards (id, rfid_number, type, available, allocated_to, allocated_time, allocated_person_name, active) VALUES (8584, 'KD014576698443RH', 'student', 1, 8570, 'Mon May 25 15:16:53 EDT 2015', 'Rose Hughes', 0);
INSERT INTO rfid_cards (id, rfid_number, type, available, allocated_to, allocated_time, allocated_person_name, active) VALUES (8165, 'KD014576431179ER', 'student', 1, 8585, 'Mon May 25 15:24:53 EDT 2015', 'Erika Rivers', 0);
INSERT INTO rfid_cards (id, rfid_number, type, available, allocated_to, allocated_time, allocated_person_name, active) VALUES (8166, 'KD014576693971AP', 'student', 1, 8592, 'Tue May 26 14:48:36 EDT 2015', 'Andrea Powell', 0);
INSERT INTO rfid_cards (id, rfid_number, type, available, allocated_to, allocated_time, allocated_person_name, active) VALUES (8168, 'KD014576382796JS', 'student', 1, 8606, 'Wed May 27 13:58:09 EDT 2015', 'Jayme Sandborn', 0);
INSERT INTO rfid_cards (id, rfid_number, type, available, allocated_to, allocated_time, allocated_person_name, active) VALUES (8164, 'KD014576431179MR', 'student', 1, 8577, 'Mon May 25 15:18:23 EDT 2015', 'Malcolm Rivers', 0);
INSERT INTO rfid_cards (id, rfid_number, type, available, allocated_to, allocated_time, allocated_person_name, active) VALUES (8167, 'KD014576382796MJ', 'student', 1, 8599, 'Wed May 27 13:56:05 EDT 2015', 'Mark Johnson', 0);
INSERT INTO rfid_cards (id, rfid_number, type, available, allocated_to, allocated_time, allocated_person_name, active) VALUES (8169, 'KD014576382796CH', 'student', 1, 8613, 'Wed May 27 14:00:19 EDT 2015', 'Casey Henderson', 0);
INSERT INTO rfid_cards (id, rfid_number, type, available, allocated_to, allocated_time, allocated_person_name, active) VALUES (8170, 'KD014576382796MH', 'student', 1, 8620, 'Wed May 27 14:25:14 EDT 2015', 'Mila Henderson', 0);
INSERT INTO rfid_cards (id, rfid_number, type, available, allocated_to, allocated_time, allocated_person_name, active) VALUES (8171, 'KD014576547151BR', 'student', 1, 8627, 'Wed May 27 14:27:14 EDT 2015', 'Bella Richardson', 0);
INSERT INTO rfid_cards (id, rfid_number, type, available, allocated_to, allocated_time, allocated_person_name, active) VALUES (8172, 'KD014576559439AG', 'student', 1, 8634, 'Wed May 27 14:28:52 EDT 2015', 'Abel Glenn', 0);
INSERT INTO rfid_cards (id, rfid_number, type, available, allocated_to, allocated_time, allocated_person_name, active) VALUES (8173, 'KD014576559439JG', 'student', 1, 8641, 'Wed May 27 14:30:13 EDT 2015', 'Jessica Glenn', 0);
INSERT INTO rfid_cards (id, rfid_number, type, available, allocated_to, allocated_time, allocated_person_name, active) VALUES (8174, 'KD014576279667XE', 'student', 1, 8648, 'Wed May 27 14:31:53 EDT 2015', 'Xavier Eady', 0);
INSERT INTO rfid_cards (id, rfid_number, type, available, allocated_to, allocated_time, allocated_person_name, active) VALUES (8175, 'KD014576279667GE', 'student', 1, 8655, 'Wed May 27 14:33:14 EDT 2015', 'Ginger Eady', 0);
INSERT INTO rfid_cards (id, rfid_number, type, available, allocated_to, allocated_time, allocated_person_name, active) VALUES (226, '0005219345', 'staff', 1, 1053, 'Mon Mar 23 15:08:57 IST 2015', 'Sachin', 0);
INSERT INTO rfid_cards (id, rfid_number, type, available, allocated_to, allocated_time, allocated_person_name, active) VALUES (385, '0005219357', 'staff', 1, 1059, 'Mon Mar 23 15:10:05 IST 2015', 'Johny', 0);
INSERT INTO rfid_cards (id, rfid_number, type, available, allocated_to, allocated_time, allocated_person_name, active) VALUES (1065, '0001845970', 'staff', 1, 1066, 'Mon Mar 23 15:11:58 IST 2015', 'Chunkey', 0);
INSERT INTO rfid_cards (id, rfid_number, type, available, allocated_to, allocated_time, allocated_person_name, active) VALUES (735, '0001839766', 'student', 0, 0, 'none', 'none', 0);
INSERT INTO rfid_cards (id, rfid_number, type, available, allocated_to, allocated_time, allocated_person_name, active) VALUES (736, '0001842378', 'student', 0, 0, 'none', 'none', 0);
INSERT INTO rfid_cards (id, rfid_number, type, available, allocated_to, allocated_time, allocated_person_name, active) VALUES (737, '0001837910', 'student', 0, 0, 'none', 'none', 0);
INSERT INTO rfid_cards (id, rfid_number, type, available, allocated_to, allocated_time, allocated_person_name, active) VALUES (15532, '0006698916', 'student', 1, 1006, 'Tue Jul 07 15:16:27 IST 2015', 'John STUDENT', 0);
INSERT INTO rfid_cards (id, rfid_number, type, available, allocated_to, allocated_time, allocated_person_name, active) VALUES (15530, '0006694514', 'student', 1, 1046, 'Tue Jul 07 15:16:51 IST 2015', 'kranthi kumar', 0);
INSERT INTO rfid_cards (id, rfid_number, type, available, allocated_to, allocated_time, allocated_person_name, active) VALUES (15531, '0001834184', 'student', 1, 1039, 'Tue Jul 07 15:17:08 IST 2015', 'Syed Samiuddin', 0);
INSERT INTO rfid_cards (id, rfid_number, type, available, allocated_to, allocated_time, allocated_person_name, active) VALUES (15529, '0001826711', 'driver', 1, 738, 'Tue Jul 07 15:46:39 IST 2015', 'Micheal', 0);
INSERT INTO rfid_cards (id, rfid_number, type, available, allocated_to, allocated_time, allocated_person_name, active) VALUES (15679, '0001840742', 'driver', 0, 0, 'none', 'none', 0);
INSERT INTO rfid_cards (id, rfid_number, type, available, allocated_to, allocated_time, allocated_person_name, active) VALUES (15847, 'qqqqqqqqqqq', 'driver', 0, 0, 'none', 'none', 0);


--
-- TOC entry 2342 (class 0 OID 0)
-- Dependencies: 191
-- Name: rfid_cards_id_seq; Type: SEQUENCE SET; Schema: public; Owner: easycommute
--

SELECT pg_catalog.setval('rfid_cards_id_seq', 1, false);


--
-- TOC entry 2301 (class 0 OID 64633)
-- Dependencies: 192
-- Data for Name: roles; Type: TABLE DATA; Schema: public; Owner: easycommute
--

INSERT INTO roles (role_id, role_name) VALUES (1, 'ROLE_ADMIN');
INSERT INTO roles (role_id, role_name) VALUES (15848, 'ROLE_CUSTOMER_SUPPORT');
INSERT INTO roles (role_id, role_name) VALUES (8136, 'ROLE_OPERATOR');


--
-- TOC entry 2343 (class 0 OID 0)
-- Dependencies: 193
-- Name: roles_role_id_seq; Type: SEQUENCE SET; Schema: public; Owner: easycommute
--

SELECT pg_catalog.setval('roles_role_id_seq', 1, false);


--
-- TOC entry 2305 (class 0 OID 64649)
-- Dependencies: 196
-- Data for Name: routes; Type: TABLE DATA; Schema: public; Owner: easycommute
--

INSERT INTO routes (id, isassigned, route_name, route_type) VALUES (8101, '1', 'Drop Off', 'Drop Off');
INSERT INTO routes (id, isassigned, route_name, route_type) VALUES (8293, '0', 'Klondike2-Constance Bay - Pick Up', 'Pick Up');
INSERT INTO routes (id, isassigned, route_name, route_type) VALUES (8294, '0', 'Klondike2-Constance Bay - Drop Off', 'Drop Off');
INSERT INTO routes (id, isassigned, route_name, route_type) VALUES (8291, '0', 'Klondike1-Dunrobin - Pick Up', 'Pick Up');
INSERT INTO routes (id, isassigned, route_name, route_type) VALUES (8350, '0', 'Klondike1-Dunrobin - Drop Off A', 'Drop Off');
INSERT INTO routes (id, isassigned, route_name, route_type) VALUES (8292, '1', 'Klondike1-Dunrobin - Drop Off', 'Drop Off');
INSERT INTO routes (id, isassigned, route_name, route_type) VALUES (2939, '1', 'Pickup ratnadeep-IIIT', 'Pick Up');
INSERT INTO routes (id, isassigned, route_name, route_type) VALUES (744, '1', 'Drop Off IIIt-Mindspace', 'Drop Off');
INSERT INTO routes (id, isassigned, route_name, route_type) VALUES (12662, '1', 'Basheerbagh Route Pickup', 'Pick Up');
INSERT INTO routes (id, isassigned, route_name, route_type) VALUES (12656, '1', 'Basheerbagh Route', 'Drop Off');


--
-- TOC entry 2310 (class 0 OID 64675)
-- Dependencies: 201
-- Data for Name: stops; Type: TABLE DATA; Schema: public; Owner: easycommute
--

INSERT INTO stops (id, geofence, latitude, longitude, stop_name, isassigned) VALUES (8099, '1', '45.490507	', '-76.091167', 'KLON02CBAY Stop 0: Cbay1', '1');
INSERT INTO stops (id, geofence, latitude, longitude, stop_name, isassigned) VALUES (8312, '1', '45.373279', '-75.942667', 'KLON01DUNR Stop 19: Houston', '1');
INSERT INTO stops (id, geofence, latitude, longitude, stop_name, isassigned) VALUES (8311, '1', '45.368559', '-75.947439', 'KLON01DUNR Stop 18: Hedge', '1');
INSERT INTO stops (id, geofence, latitude, longitude, stop_name, isassigned) VALUES (8310, '1', '45.375470', '-75.959151', 'KLON01DUNR Stop 17: Dunrobin 2', '1');
INSERT INTO stops (id, geofence, latitude, longitude, stop_name, isassigned) VALUES (8309, '1', '45.378382', '-75.960796', 'KLON01DUNR Stop 16: Bathurst', '1');
INSERT INTO stops (id, geofence, latitude, longitude, stop_name, isassigned) VALUES (8308, '1', '45.379693', '-75.955971', 'KLON01DUNR Stop 15: Moran', '1');
INSERT INTO stops (id, geofence, latitude, longitude, stop_name, isassigned) VALUES (8307, '1', '45.402431', '-75.923179', 'KLON01DUNR Stop 14: Sixth Line 3', '1');
INSERT INTO stops (id, geofence, latitude, longitude, stop_name, isassigned) VALUES (8306, '1', '45.410698', '-75.933443', 'KLON01DUNR Stop 13: Sixth Line 2', '1');
INSERT INTO stops (id, geofence, latitude, longitude, stop_name, isassigned) VALUES (8305, '1', '45.420671', '-75.946912', 'KLON01DUNR Stop 12: Sixth Line 1', '1');
INSERT INTO stops (id, geofence, latitude, longitude, stop_name, isassigned) VALUES (8304, '1', '45.412493', '-75.959707', 'KLON01DUNR Stop 11: Fifth Line ', '1');
INSERT INTO stops (id, geofence, latitude, longitude, stop_name, isassigned) VALUES (8303, '1', '45.385022', '-75.959430', 'KLON01DUNR Stop 10: O''Hara 2', '1');
INSERT INTO stops (id, geofence, latitude, longitude, stop_name, isassigned) VALUES (8302, '1', '45.382778', '-75.962815', 'KLON01DUNR Stop 9: O''Hara 1', '1');
INSERT INTO stops (id, geofence, latitude, longitude, stop_name, isassigned) VALUES (8301, '1', ' 45.385689', '-75.966449', 'KLON01DUNR Stop 8: Kerwin', '1');
INSERT INTO stops (id, geofence, latitude, longitude, stop_name, isassigned) VALUES (8300, '1', '45.388614', '-75.970058', 'KLON01DUNR Stop 7: Mowbray', '1');
INSERT INTO stops (id, geofence, latitude, longitude, stop_name, isassigned) VALUES (8299, '1', '45.401830', '-75.995658', 'KLON01DUNR Stop 6: Dunrobin 1', '1');
INSERT INTO stops (id, geofence, latitude, longitude, stop_name, isassigned) VALUES (8298, '1', '45.426060', '-76.025235', 'KLON01DUNR Stop 5: Porcupine Trail 2', '1');
INSERT INTO stops (id, geofence, latitude, longitude, stop_name, isassigned) VALUES (8297, '1', '45.423048', '-76.019052', 'KLON01DUNR Stop 4: Porcupine Trail 1', '1');
INSERT INTO stops (id, geofence, latitude, longitude, stop_name, isassigned) VALUES (8296, '1', '45.428237', '-76.010234', 'KLON01DUNR Stop 3: Constance Creek 2', '1');
INSERT INTO stops (id, geofence, latitude, longitude, stop_name, isassigned) VALUES (8295, '1', '45.434546', '-76.014339', 'KLON01DUNR Stop 2: Constance Creek 1', '0');
INSERT INTO stops (id, geofence, latitude, longitude, stop_name, isassigned) VALUES (8315, '1', '45.436265', '-76.018996', 'KLON01DUNR Stop 1: Blackberry', '3');
INSERT INTO stops (id, geofence, latitude, longitude, stop_name, isassigned) VALUES (8100, '1', '45.496367	', '-76.098864', 'KLON02CBAY Stop 1: Cbay2', '0');
INSERT INTO stops (id, geofence, latitude, longitude, stop_name, isassigned) VALUES (8313, '1', '45.3580451', '-75.927866', 'KLON01DUNR Stop 20: Klondike', '0');
INSERT INTO stops (id, geofence, latitude, longitude, stop_name, isassigned) VALUES (8314, '1', '45.435322', '-76.021507', 'KLON01DUNR Stop 0: Windigo', '0');
INSERT INTO stops (id, geofence, latitude, longitude, stop_name, isassigned) VALUES (88, '1', '17.446418', '78.350143', 'slabs', '3');
INSERT INTO stops (id, geofence, latitude, longitude, stop_name, isassigned) VALUES (89, '1', '17.444852', '78.353007', 'iiit bus stop', '3');
INSERT INTO stops (id, geofence, latitude, longitude, stop_name, isassigned) VALUES (633, '3', '17.440814', '78.360576', 'udipi', '7');
INSERT INTO stops (id, geofence, latitude, longitude, stop_name, isassigned) VALUES (741, '4', '17.433444', '78.369591', 'Al Saba', '2');
INSERT INTO stops (id, geofence, latitude, longitude, stop_name, isassigned) VALUES (12655, '4', '17.401197', ' 78.475455', 'BasheerBagh', '2');
INSERT INTO stops (id, geofence, latitude, longitude, stop_name, isassigned) VALUES (2503, '2', '17.403359', '78.456549', 'Mahaveer Hosp', '4');
INSERT INTO stops (id, geofence, latitude, longitude, stop_name, isassigned) VALUES (742, '2', '17.429791', '78.373892', 'Ratnadeep', '4');
INSERT INTO stops (id, geofence, latitude, longitude, stop_name, isassigned) VALUES (12654, '3', '17.402953', '78.460673', 'Lakdi ka Pool', '2');
INSERT INTO stops (id, geofence, latitude, longitude, stop_name, isassigned) VALUES (2502, '4', '17.402666', '78.410671', 'Galaxy Theatre', '4');
INSERT INTO stops (id, geofence, latitude, longitude, stop_name, isassigned) VALUES (15525, '1', '17.469723', '78.385148', 'Hitech Railway Station', '0');
INSERT INTO stops (id, geofence, latitude, longitude, stop_name, isassigned) VALUES (15526, '3', '17.475494', '78.426703', 'Sub Registrar office', '0');


--
-- TOC entry 2303 (class 0 OID 64641)
-- Dependencies: 194
-- Data for Name: route_stops_list; Type: TABLE DATA; Schema: public; Owner: easycommute
--

INSERT INTO route_stops_list (id, stop_number, stop_time, route_id, stop_id) VALUES (8316, 1, '07:00', 8291, 8314);
INSERT INTO route_stops_list (id, stop_number, stop_time, route_id, stop_id) VALUES (8319, 2, '07:02', 8291, 8315);
INSERT INTO route_stops_list (id, stop_number, stop_time, route_id, stop_id) VALUES (8320, 3, '07:03', 8291, 8295);
INSERT INTO route_stops_list (id, stop_number, stop_time, route_id, stop_id) VALUES (8321, 4, '07:04', 8291, 8296);
INSERT INTO route_stops_list (id, stop_number, stop_time, route_id, stop_id) VALUES (8322, 5, '07:05', 8291, 8297);
INSERT INTO route_stops_list (id, stop_number, stop_time, route_id, stop_id) VALUES (8323, 6, '07:09', 8291, 8298);
INSERT INTO route_stops_list (id, stop_number, stop_time, route_id, stop_id) VALUES (8324, 7, '07:12', 8291, 8299);
INSERT INTO route_stops_list (id, stop_number, stop_time, route_id, stop_id) VALUES (8325, 8, '07:13', 8291, 8300);
INSERT INTO route_stops_list (id, stop_number, stop_time, route_id, stop_id) VALUES (8326, 9, '07:14', 8291, 8301);
INSERT INTO route_stops_list (id, stop_number, stop_time, route_id, stop_id) VALUES (8327, 10, '07:15', 8291, 8302);
INSERT INTO route_stops_list (id, stop_number, stop_time, route_id, stop_id) VALUES (8328, 11, '07:16', 8291, 8303);
INSERT INTO route_stops_list (id, stop_number, stop_time, route_id, stop_id) VALUES (8329, 12, '07:20', 8291, 8304);
INSERT INTO route_stops_list (id, stop_number, stop_time, route_id, stop_id) VALUES (8330, 13, '07:22', 8291, 8305);
INSERT INTO route_stops_list (id, stop_number, stop_time, route_id, stop_id) VALUES (8331, 14, '07:23', 8291, 8306);
INSERT INTO route_stops_list (id, stop_number, stop_time, route_id, stop_id) VALUES (8332, 15, '07:24', 8291, 8307);
INSERT INTO route_stops_list (id, stop_number, stop_time, route_id, stop_id) VALUES (8333, 16, '07:27', 8291, 8308);
INSERT INTO route_stops_list (id, stop_number, stop_time, route_id, stop_id) VALUES (8334, 17, '07:28', 8291, 8309);
INSERT INTO route_stops_list (id, stop_number, stop_time, route_id, stop_id) VALUES (8342, 1, '00:00', 8294, 8306);
INSERT INTO route_stops_list (id, stop_number, stop_time, route_id, stop_id) VALUES (8343, 18, '07:30', 8291, 8310);
INSERT INTO route_stops_list (id, stop_number, stop_time, route_id, stop_id) VALUES (8344, 19, '07:32', 8291, 8311);
INSERT INTO route_stops_list (id, stop_number, stop_time, route_id, stop_id) VALUES (8345, 20, '07:33', 8291, 8312);
INSERT INTO route_stops_list (id, stop_number, stop_time, route_id, stop_id) VALUES (8346, 21, '07:39', 8291, 8313);
INSERT INTO route_stops_list (id, stop_number, stop_time, route_id, stop_id) VALUES (8351, 1, '15:59', 8350, 8313);
INSERT INTO route_stops_list (id, stop_number, stop_time, route_id, stop_id) VALUES (8352, 2, '16:05', 8350, 8312);
INSERT INTO route_stops_list (id, stop_number, stop_time, route_id, stop_id) VALUES (8353, 3, '16:06', 8350, 8311);
INSERT INTO route_stops_list (id, stop_number, stop_time, route_id, stop_id) VALUES (8354, 4, '16:08', 8350, 8310);
INSERT INTO route_stops_list (id, stop_number, stop_time, route_id, stop_id) VALUES (8355, 5, '16:10', 8350, 8309);
INSERT INTO route_stops_list (id, stop_number, stop_time, route_id, stop_id) VALUES (8356, 6, '16:11', 8350, 8308);
INSERT INTO route_stops_list (id, stop_number, stop_time, route_id, stop_id) VALUES (8357, 7, '16:14', 8350, 8307);
INSERT INTO route_stops_list (id, stop_number, stop_time, route_id, stop_id) VALUES (8358, 8, '16:17', 8350, 8306);
INSERT INTO route_stops_list (id, stop_number, stop_time, route_id, stop_id) VALUES (8359, 9, '16:18', 8350, 8305);
INSERT INTO route_stops_list (id, stop_number, stop_time, route_id, stop_id) VALUES (8360, 10, '16:20', 8350, 8304);
INSERT INTO route_stops_list (id, stop_number, stop_time, route_id, stop_id) VALUES (8361, 11, '16:24', 8350, 8303);
INSERT INTO route_stops_list (id, stop_number, stop_time, route_id, stop_id) VALUES (8362, 12, '16:25', 8350, 8302);
INSERT INTO route_stops_list (id, stop_number, stop_time, route_id, stop_id) VALUES (8363, 13, '16:26', 8350, 8301);
INSERT INTO route_stops_list (id, stop_number, stop_time, route_id, stop_id) VALUES (8364, 14, '16:27', 8350, 8300);
INSERT INTO route_stops_list (id, stop_number, stop_time, route_id, stop_id) VALUES (8365, 15, '16:28', 8350, 8299);
INSERT INTO route_stops_list (id, stop_number, stop_time, route_id, stop_id) VALUES (8366, 16, '16:31', 8350, 8298);
INSERT INTO route_stops_list (id, stop_number, stop_time, route_id, stop_id) VALUES (8367, 17, '16:35', 8350, 8297);
INSERT INTO route_stops_list (id, stop_number, stop_time, route_id, stop_id) VALUES (8368, 18, '16:36', 8350, 8296);
INSERT INTO route_stops_list (id, stop_number, stop_time, route_id, stop_id) VALUES (8369, 19, '16:37', 8350, 8295);
INSERT INTO route_stops_list (id, stop_number, stop_time, route_id, stop_id) VALUES (8370, 20, '16:38', 8350, 8315);
INSERT INTO route_stops_list (id, stop_number, stop_time, route_id, stop_id) VALUES (8371, 21, '16:40', 8350, 8314);
INSERT INTO route_stops_list (id, stop_number, stop_time, route_id, stop_id) VALUES (8415, 3, '19:08', 8292, 8310);
INSERT INTO route_stops_list (id, stop_number, stop_time, route_id, stop_id) VALUES (8414, 2, '19:06', 8292, 8311);
INSERT INTO route_stops_list (id, stop_number, stop_time, route_id, stop_id) VALUES (8413, 1, '19:05', 8292, 8312);
INSERT INTO route_stops_list (id, stop_number, stop_time, route_id, stop_id) VALUES (2942, 1, '08:15', 2939, 742);
INSERT INTO route_stops_list (id, stop_number, stop_time, route_id, stop_id) VALUES (2955, 2, '08:16', 2939, 741);
INSERT INTO route_stops_list (id, stop_number, stop_time, route_id, stop_id) VALUES (2959, 3, '08:17', 2939, 633);
INSERT INTO route_stops_list (id, stop_number, stop_time, route_id, stop_id) VALUES (2966, 4, '08:20', 2939, 88);
INSERT INTO route_stops_list (id, stop_number, stop_time, route_id, stop_id) VALUES (12657, 1, '20:25', 12656, 742);
INSERT INTO route_stops_list (id, stop_number, stop_time, route_id, stop_id) VALUES (12658, 2, '20:35', 12656, 2502);
INSERT INTO route_stops_list (id, stop_number, stop_time, route_id, stop_id) VALUES (12659, 3, '20:45', 12656, 2503);
INSERT INTO route_stops_list (id, stop_number, stop_time, route_id, stop_id) VALUES (12660, 4, '20:47', 12656, 12654);
INSERT INTO route_stops_list (id, stop_number, stop_time, route_id, stop_id) VALUES (12661, 5, '20:49', 12656, 12655);
INSERT INTO route_stops_list (id, stop_number, stop_time, route_id, stop_id) VALUES (12667, 5, '17:05', 12662, 742);
INSERT INTO route_stops_list (id, stop_number, stop_time, route_id, stop_id) VALUES (12666, 4, '17:00', 12662, 2502);
INSERT INTO route_stops_list (id, stop_number, stop_time, route_id, stop_id) VALUES (12665, 3, '16:55', 12662, 2503);
INSERT INTO route_stops_list (id, stop_number, stop_time, route_id, stop_id) VALUES (12664, 2, '16:53', 12662, 12654);
INSERT INTO route_stops_list (id, stop_number, stop_time, route_id, stop_id) VALUES (12663, 1, '16:45', 12662, 12655);
INSERT INTO route_stops_list (id, stop_number, stop_time, route_id, stop_id) VALUES (750, 5, '18:45', 744, 742);
INSERT INTO route_stops_list (id, stop_number, stop_time, route_id, stop_id) VALUES (749, 4, '18:44', 744, 741);
INSERT INTO route_stops_list (id, stop_number, stop_time, route_id, stop_id) VALUES (748, 3, '18:43', 744, 633);
INSERT INTO route_stops_list (id, stop_number, stop_time, route_id, stop_id) VALUES (747, 2, '18:42', 744, 89);
INSERT INTO route_stops_list (id, stop_number, stop_time, route_id, stop_id) VALUES (745, 1, '18:41', 744, 88);
INSERT INTO route_stops_list (id, stop_number, stop_time, route_id, stop_id) VALUES (8289, 2, '13:05', 8101, 8099);
INSERT INTO route_stops_list (id, stop_number, stop_time, route_id, stop_id) VALUES (8431, 19, '19:36', 8292, 8315);
INSERT INTO route_stops_list (id, stop_number, stop_time, route_id, stop_id) VALUES (8430, 18, '19:35', 8292, 8295);
INSERT INTO route_stops_list (id, stop_number, stop_time, route_id, stop_id) VALUES (8429, 17, '19:34', 8292, 8296);
INSERT INTO route_stops_list (id, stop_number, stop_time, route_id, stop_id) VALUES (8428, 16, '19:33', 8292, 8297);
INSERT INTO route_stops_list (id, stop_number, stop_time, route_id, stop_id) VALUES (8427, 15, '19:29', 8292, 8298);
INSERT INTO route_stops_list (id, stop_number, stop_time, route_id, stop_id) VALUES (8426, 14, '19:26', 8292, 8299);
INSERT INTO route_stops_list (id, stop_number, stop_time, route_id, stop_id) VALUES (8425, 13, '19:25', 8292, 8300);
INSERT INTO route_stops_list (id, stop_number, stop_time, route_id, stop_id) VALUES (8424, 12, '19:24', 8292, 8301);
INSERT INTO route_stops_list (id, stop_number, stop_time, route_id, stop_id) VALUES (8423, 11, '19:23', 8292, 8302);
INSERT INTO route_stops_list (id, stop_number, stop_time, route_id, stop_id) VALUES (8422, 10, '19:22', 8292, 8303);
INSERT INTO route_stops_list (id, stop_number, stop_time, route_id, stop_id) VALUES (8421, 9, '19:18', 8292, 8304);
INSERT INTO route_stops_list (id, stop_number, stop_time, route_id, stop_id) VALUES (8420, 8, '19:16', 8292, 8305);
INSERT INTO route_stops_list (id, stop_number, stop_time, route_id, stop_id) VALUES (8419, 7, '19:15', 8292, 8306);
INSERT INTO route_stops_list (id, stop_number, stop_time, route_id, stop_id) VALUES (8418, 6, '19:14', 8292, 8307);
INSERT INTO route_stops_list (id, stop_number, stop_time, route_id, stop_id) VALUES (8417, 5, '19:11', 8292, 8308);
INSERT INTO route_stops_list (id, stop_number, stop_time, route_id, stop_id) VALUES (8416, 4, '19:10', 8292, 8309);
INSERT INTO route_stops_list (id, stop_number, stop_time, route_id, stop_id) VALUES (8290, 1, '02:14', 8101, 8100);


--
-- TOC entry 2344 (class 0 OID 0)
-- Dependencies: 195
-- Name: route_stops_list_id_seq; Type: SEQUENCE SET; Schema: public; Owner: easycommute
--

SELECT pg_catalog.setval('route_stops_list_id_seq', 1, false);


--
-- TOC entry 2345 (class 0 OID 0)
-- Dependencies: 197
-- Name: routes_id_seq; Type: SEQUENCE SET; Schema: public; Owner: easycommute
--

SELECT pg_catalog.setval('routes_id_seq', 1, false);


--
-- TOC entry 2324 (class 0 OID 97157)
-- Dependencies: 215
-- Data for Name: session; Type: TABLE DATA; Schema: public; Owner: easycommute
--

INSERT INTO session (id, end_time, session_name, session_type, starting_time, end_time_hours, end_time_minutes, starting_time_hours, starting_time_minutes) VALUES (16101, NULL, 'Morning Class', 'Morning', NULL, 11, 30, 9, 30);
INSERT INTO session (id, end_time, session_name, session_type, starting_time, end_time_hours, end_time_minutes, starting_time_hours, starting_time_minutes) VALUES (16107, NULL, 'Mo', 'Morning', NULL, 15, 10, 14, 10);


--
-- TOC entry 2307 (class 0 OID 64657)
-- Dependencies: 198
-- Data for Name: settings; Type: TABLE DATA; Schema: public; Owner: easycommute
--

INSERT INTO settings (id, from_email, school_latitude, school_longitude, smtp_password, smtp_port, smtp_server_host, phone_number_alerts, school_name) VALUES (1, 'notifications@easycommute.com', 17.443878999999999, 78.3487030000000004, '4P05j/sHa0l87DdumOZyt8D4tC2sjwTX', '465', 'smtp.netfirms.com', '6137930529', 'easycommute');


--
-- TOC entry 2308 (class 0 OID 64663)
-- Dependencies: 199
-- Data for Name: staff; Type: TABLE DATA; Schema: public; Owner: easycommute
--

INSERT INTO staff (id, email, full_name, gender, mobile_number, rfid_number, staff_id) VALUES (8190, 'hchandler@odsb.ca', 'Helen Chandler', 'female', '6134066119', 'KL0045764518665A', 'KLHC5A');
INSERT INTO staff (id, email, full_name, gender, mobile_number, rfid_number, staff_id) VALUES (8196, 'mlsmith@odsb.ca', 'Michael Smith', 'male', '6137427331', 'KL0045764518664C', 'KLMS4C');
INSERT INTO staff (id, email, full_name, gender, mobile_number, rfid_number, staff_id) VALUES (8202, 'mgoodwin@odsb.ca', 'Marjorie Goodwin', 'female', '6137698645', 'KL0045764518667F', 'KLMG7F');
INSERT INTO staff (id, email, full_name, gender, mobile_number, rfid_number, staff_id) VALUES (8208, 'dlawson@odsb.ca', 'Daniel Lawson', 'male', '6134653630', 'KL0045764518667E', 'KLDL7E');
INSERT INTO staff (id, email, full_name, gender, mobile_number, rfid_number, staff_id) VALUES (8214, 'bcharles@odsb.ca', 'Brett Charles', 'male', '6139973658', 'KL0045764518665C', 'KLBC5B');
INSERT INTO staff (id, email, full_name, gender, mobile_number, rfid_number, staff_id) VALUES (8220, 'jbryant@odsb.ca', 'Janet Bryant', 'female', '6137504273', 'KL0045764518666C', 'KLJB6C');
INSERT INTO staff (id, email, full_name, gender, mobile_number, rfid_number, staff_id) VALUES (8232, 'csylvester@odsb.ca', 'Catherine Sylvester', 'female', '6132849629', 'KL0045764518662A', 'KLCS2A');
INSERT INTO staff (id, email, full_name, gender, mobile_number, rfid_number, staff_id) VALUES (8238, 'mgainey@odsb.ca', 'Monique Gainey', 'female', '6139952681', 'KL0045764518663C', 'KLMG3C');
INSERT INTO staff (id, email, full_name, gender, mobile_number, rfid_number, staff_id) VALUES (8250, 'cladler@odsb.ca', 'Carla Ladler', 'female', '6138927566', 'KL0045764518664A', 'KLCL4A');
INSERT INTO staff (id, email, full_name, gender, mobile_number, rfid_number, staff_id) VALUES (8256, 'dmorrell@odsb.ca', 'Dianne Morrell', 'female', '6135512258', 'KL0045764518665B', 'KLDM5C');
INSERT INTO staff (id, email, full_name, gender, mobile_number, rfid_number, staff_id) VALUES (8226, 'mbutler@odsb.ca', 'Miles Butler', 'male', '6135333552', 'KL0045764518664B', 'KLMB4B');
INSERT INTO staff (id, email, full_name, gender, mobile_number, rfid_number, staff_id) VALUES (8262, 'lprior@odsb.ca', 'Libbey Pryor', 'female', '6137192477', 'KL0045764518662B', 'KLLP2B');
INSERT INTO staff (id, email, full_name, gender, mobile_number, rfid_number, staff_id) VALUES (8244, 'jbroomfield@odsb.ca', 'Jason Broomfield', 'male', '6134247788', 'KL0045764518662C', 'KLJB2C');
INSERT INTO staff (id, email, full_name, gender, mobile_number, rfid_number, staff_id) VALUES (8268, 'aweeks@odsb.ca', 'Anna Weeks', 'female', '6133211879', 'KL0045764518667A', 'KLAW7A');
INSERT INTO staff (id, email, full_name, gender, mobile_number, rfid_number, staff_id) VALUES (1053, 'syed123_sami@yahoo.com', 'Sachin', 'male', '08099927902', '0005219345', 'staff_001');
INSERT INTO staff (id, email, full_name, gender, mobile_number, rfid_number, staff_id) VALUES (1059, 'syedsami4u786@gmail.com', 'Johny', 'female', '08099927902', '0005219357', '002');
INSERT INTO staff (id, email, full_name, gender, mobile_number, rfid_number, staff_id) VALUES (1066, 'syedsami4u786@gmail.com', 'Chunkey', 'male', '08099927902', '0001845970', '003');


--
-- TOC entry 2309 (class 0 OID 64669)
-- Dependencies: 200
-- Data for Name: staffjspentity; Type: TABLE DATA; Schema: public; Owner: easycommute
--



--
-- TOC entry 2346 (class 0 OID 0)
-- Dependencies: 202
-- Name: stops_id_seq; Type: SEQUENCE SET; Schema: public; Owner: easycommute
--

SELECT pg_catalog.setval('stops_id_seq', 1, false);


--
-- TOC entry 2312 (class 0 OID 64683)
-- Dependencies: 203
-- Data for Name: students; Type: TABLE DATA; Schema: public; Owner: easycommute
--

INSERT INTO students (id, first_name, last_name, gr_number, gender, rfid_number, grade, student_grade) VALUES (1006, 'John', 'STUDENT', '34000', 'male', '0006698916', '1A', NULL);
INSERT INTO students (id, first_name, last_name, gr_number, gender, rfid_number, grade, student_grade) VALUES (1039, 'Syed', 'Samiuddin', '8888', 'male', '0001834184', '2B', NULL);
INSERT INTO students (id, first_name, last_name, gr_number, gender, rfid_number, grade, student_grade) VALUES (1046, 'kranthi', 'kumar', '001', 'male', '0006694514', '2B', NULL);
INSERT INTO students (id, first_name, last_name, gr_number, gender, rfid_number, grade, student_grade) VALUES (8433, 'Skylar', 'Manuel', '5', 'female', 'KD014576265996SM', '3C', NULL);
INSERT INTO students (id, first_name, last_name, gr_number, gender, rfid_number, grade, student_grade) VALUES (8440, 'Chris', 'Manuel', '4', 'male', 'KD014576265996CM', '3C', NULL);
INSERT INTO students (id, first_name, last_name, gr_number, gender, rfid_number, grade, student_grade) VALUES (8447, 'Noah', 'Schmidt', '6', 'male', 'KD014576546339NS', '3C', NULL);
INSERT INTO students (id, first_name, last_name, gr_number, gender, rfid_number, grade, student_grade) VALUES (8454, 'Katlyn', 'Woods', '3', 'female', 'KD014576237234KW', '4D', NULL);
INSERT INTO students (id, first_name, last_name, gr_number, gender, rfid_number, grade, student_grade) VALUES (8461, 'Jack', 'Watkins', '5', 'male', 'KD014576048052JW', '4D', NULL);
INSERT INTO students (id, first_name, last_name, gr_number, gender, rfid_number, grade, student_grade) VALUES (8468, 'Sam', 'Watkins', '6', 'male', 'KD014576048052SW', '4D', NULL);
INSERT INTO students (id, first_name, last_name, gr_number, gender, rfid_number, grade, student_grade) VALUES (8475, 'Emily', 'Hogan', '2', 'female', 'KD014576606235EH', '5E', NULL);
INSERT INTO students (id, first_name, last_name, gr_number, gender, rfid_number, grade, student_grade) VALUES (8482, 'Ashley', 'Graham', '7', 'female', 'KD014576183658AG', '5E', NULL);
INSERT INTO students (id, first_name, last_name, gr_number, gender, rfid_number, grade, student_grade) VALUES (8489, 'Tyler', 'Jackson', '3', 'male', 'KD014576614058TJ', '6A', NULL);
INSERT INTO students (id, first_name, last_name, gr_number, gender, rfid_number, grade, student_grade) VALUES (8496, 'Bryden', 'Jackson', '7', 'male', 'KD014576614058BJ', '6A', NULL);
INSERT INTO students (id, first_name, last_name, gr_number, gender, rfid_number, grade, student_grade) VALUES (8503, 'Jacob', 'Wilson', '2', 'male', 'KD014576689449JW', '7C', NULL);
INSERT INTO students (id, first_name, last_name, gr_number, gender, rfid_number, grade, student_grade) VALUES (8510, 'Olivia', 'Gilmour', '4', 'female', 'KD014576778815OG', '7C', NULL);
INSERT INTO students (id, first_name, last_name, gr_number, gender, rfid_number, grade, student_grade) VALUES (8517, 'Bryan', 'Gilmor', '5', 'male', 'KD014576778815BG', '8D', NULL);
INSERT INTO students (id, first_name, last_name, gr_number, gender, rfid_number, grade, student_grade) VALUES (8524, 'Fred', 'Brooks', '4', 'male', 'KD014576778815FB', '8D', NULL);
INSERT INTO students (id, first_name, last_name, gr_number, gender, rfid_number, grade, student_grade) VALUES (8531, 'Manny', 'Brown', '3', 'male', 'KD014576022943MB', '8D', NULL);
INSERT INTO students (id, first_name, last_name, gr_number, gender, rfid_number, grade, student_grade) VALUES (8538, 'Quincy', 'Brown', '8', 'male', 'KD014576022943QB', '9A', NULL);
INSERT INTO students (id, first_name, last_name, gr_number, gender, rfid_number, grade, student_grade) VALUES (8549, 'Nathan', 'Robey', '4', 'male', 'KD014576493707NR', '9A', NULL);
INSERT INTO students (id, first_name, last_name, gr_number, gender, rfid_number, grade, student_grade) VALUES (8556, 'Kayden', 'Dean', '1', 'male', 'KD014576671912KD', '9A', NULL);
INSERT INTO students (id, first_name, last_name, gr_number, gender, rfid_number, grade, student_grade) VALUES (8563, 'Lindsay', 'Hughes', '2', 'female', 'KD014576698443LH', '10C', NULL);
INSERT INTO students (id, first_name, last_name, gr_number, gender, rfid_number, grade, student_grade) VALUES (8570, 'Rose', 'Hughes', '1', 'female', 'KD014576698443RH', '10C', NULL);
INSERT INTO students (id, first_name, last_name, gr_number, gender, rfid_number, grade, student_grade) VALUES (8577, 'Malcolm', 'Rivers', '6', 'male', 'KD014576431179MR', '10C', NULL);
INSERT INTO students (id, first_name, last_name, gr_number, gender, rfid_number, grade, student_grade) VALUES (8585, 'Erika', 'Rivers', '5', 'female', 'KD014576431179ER', 'KG', NULL);
INSERT INTO students (id, first_name, last_name, gr_number, gender, rfid_number, grade, student_grade) VALUES (8592, 'Andrea', 'Powell', '8', 'female', 'KD014576693971AP', 'KG', NULL);
INSERT INTO students (id, first_name, last_name, gr_number, gender, rfid_number, grade, student_grade) VALUES (8599, 'Mark', 'Johnson', '7', 'male', 'KD014576382796MJ', 'KG', NULL);
INSERT INTO students (id, first_name, last_name, gr_number, gender, rfid_number, grade, student_grade) VALUES (8606, 'Jayme', 'Sandborn', '1', 'female', 'KD014576382796JS', '10A', NULL);
INSERT INTO students (id, first_name, last_name, gr_number, gender, rfid_number, grade, student_grade) VALUES (8613, 'Casey', 'Henderson', '2', 'male', 'KD014576382796CH', '10A', NULL);
INSERT INTO students (id, first_name, last_name, gr_number, gender, rfid_number, grade, student_grade) VALUES (8620, 'Mila', 'Henderson', '3', 'female', 'KD014576382796MH', '10A', NULL);
INSERT INTO students (id, first_name, last_name, gr_number, gender, rfid_number, grade, student_grade) VALUES (8627, 'Bella', 'Richardson', '1', 'female', 'KD014576547151BR', '9C', NULL);
INSERT INTO students (id, first_name, last_name, gr_number, gender, rfid_number, grade, student_grade) VALUES (8634, 'Abel', 'Glenn', '8', 'male', 'KD014576559439AG', '9C', NULL);
INSERT INTO students (id, first_name, last_name, gr_number, gender, rfid_number, grade, student_grade) VALUES (8641, 'Jessica', 'Glenn', '7', 'female', 'KD014576559439JG', '9C', NULL);
INSERT INTO students (id, first_name, last_name, gr_number, gender, rfid_number, grade, student_grade) VALUES (8648, 'Xavier', 'Eady', '2', 'male', 'KD014576279667XE', '7B', NULL);
INSERT INTO students (id, first_name, last_name, gr_number, gender, rfid_number, grade, student_grade) VALUES (8655, 'Ginger', 'Eady', '4', 'female', 'KD014576279667GE', '7B', NULL);
INSERT INTO students (id, first_name, last_name, gr_number, gender, rfid_number, grade, student_grade) VALUES (15870, 'abc', 'xyz', '101', 'male', '0001839766', '7B', NULL);


--
-- TOC entry 2347 (class 0 OID 0)
-- Dependencies: 204
-- Name: students_id_seq; Type: SEQUENCE SET; Schema: public; Owner: easycommute
--

SELECT pg_catalog.setval('students_id_seq', 1, false);


--
-- TOC entry 2314 (class 0 OID 64691)
-- Dependencies: 205
-- Data for Name: studentsjspentity; Type: TABLE DATA; Schema: public; Owner: easycommute
--



--
-- TOC entry 2315 (class 0 OID 64697)
-- Dependencies: 206
-- Data for Name: temp; Type: TABLE DATA; Schema: public; Owner: easycommute
--

INSERT INTO temp ("time") VALUES ('02:50');
INSERT INTO temp ("time") VALUES ('17:60');
INSERT INTO temp ("time") VALUES ('15:60');
INSERT INTO temp ("time") VALUES ('16:60');
INSERT INTO temp ("time") VALUES ('11:60');
INSERT INTO temp ("time") VALUES ('02:50');
INSERT INTO temp ("time") VALUES ('17:60');
INSERT INTO temp ("time") VALUES ('15:60');
INSERT INTO temp ("time") VALUES ('16:60');
INSERT INTO temp ("time") VALUES ('11:60');


--
-- TOC entry 2316 (class 0 OID 64703)
-- Dependencies: 207
-- Data for Name: transport; Type: TABLE DATA; Schema: public; Owner: easycommute
--

INSERT INTO transport (id, subscriber_type, subscriber_id, bus_id, route_id, trip_id, stop_id, transport_type) VALUES (8094, 'student', 8089, 0, 0, 0, 0, 'Drop Off');
INSERT INTO transport (id, subscriber_type, subscriber_id, bus_id, route_id, trip_id, stop_id, transport_type) VALUES (8095, 'student', 8089, 0, 0, 0, 0, 'Pick Up');
INSERT INTO transport (id, subscriber_type, subscriber_id, bus_id, route_id, trip_id, stop_id, transport_type) VALUES (8110, 'student', 8105, 8098, 8101, 8104, 8099, 'Drop Off');
INSERT INTO transport (id, subscriber_type, subscriber_id, bus_id, route_id, trip_id, stop_id, transport_type) VALUES (8111, 'student', 8105, 0, 0, 0, 0, 'Pick Up');
INSERT INTO transport (id, subscriber_type, subscriber_id, bus_id, route_id, trip_id, stop_id, transport_type) VALUES (8194, 'staff', 8190, 0, 0, 0, 0, 'Pick Up');
INSERT INTO transport (id, subscriber_type, subscriber_id, bus_id, route_id, trip_id, stop_id, transport_type) VALUES (8195, 'staff', 8190, 0, 0, 0, 0, 'Drop Off');
INSERT INTO transport (id, subscriber_type, subscriber_id, bus_id, route_id, trip_id, stop_id, transport_type) VALUES (8200, 'staff', 8196, 0, 0, 0, 0, 'Pick Up');
INSERT INTO transport (id, subscriber_type, subscriber_id, bus_id, route_id, trip_id, stop_id, transport_type) VALUES (8201, 'staff', 8196, 0, 0, 0, 0, 'Drop Off');
INSERT INTO transport (id, subscriber_type, subscriber_id, bus_id, route_id, trip_id, stop_id, transport_type) VALUES (8206, 'staff', 8202, 0, 0, 0, 0, 'Pick Up');
INSERT INTO transport (id, subscriber_type, subscriber_id, bus_id, route_id, trip_id, stop_id, transport_type) VALUES (8207, 'staff', 8202, 0, 0, 0, 0, 'Drop Off');
INSERT INTO transport (id, subscriber_type, subscriber_id, bus_id, route_id, trip_id, stop_id, transport_type) VALUES (8212, 'staff', 8208, 0, 0, 0, 0, 'Pick Up');
INSERT INTO transport (id, subscriber_type, subscriber_id, bus_id, route_id, trip_id, stop_id, transport_type) VALUES (8213, 'staff', 8208, 0, 0, 0, 0, 'Drop Off');
INSERT INTO transport (id, subscriber_type, subscriber_id, bus_id, route_id, trip_id, stop_id, transport_type) VALUES (8218, 'staff', 8214, 0, 0, 0, 0, 'Pick Up');
INSERT INTO transport (id, subscriber_type, subscriber_id, bus_id, route_id, trip_id, stop_id, transport_type) VALUES (8219, 'staff', 8214, 0, 0, 0, 0, 'Drop Off');
INSERT INTO transport (id, subscriber_type, subscriber_id, bus_id, route_id, trip_id, stop_id, transport_type) VALUES (8224, 'staff', 8220, 0, 0, 0, 0, 'Pick Up');
INSERT INTO transport (id, subscriber_type, subscriber_id, bus_id, route_id, trip_id, stop_id, transport_type) VALUES (8225, 'staff', 8220, 0, 0, 0, 0, 'Drop Off');
INSERT INTO transport (id, subscriber_type, subscriber_id, bus_id, route_id, trip_id, stop_id, transport_type) VALUES (8230, 'staff', 8226, 0, 0, 0, 0, 'Pick Up');
INSERT INTO transport (id, subscriber_type, subscriber_id, bus_id, route_id, trip_id, stop_id, transport_type) VALUES (8231, 'staff', 8226, 0, 0, 0, 0, 'Drop Off');
INSERT INTO transport (id, subscriber_type, subscriber_id, bus_id, route_id, trip_id, stop_id, transport_type) VALUES (8236, 'staff', 8232, 0, 0, 0, 0, 'Pick Up');
INSERT INTO transport (id, subscriber_type, subscriber_id, bus_id, route_id, trip_id, stop_id, transport_type) VALUES (8237, 'staff', 8232, 0, 0, 0, 0, 'Drop Off');
INSERT INTO transport (id, subscriber_type, subscriber_id, bus_id, route_id, trip_id, stop_id, transport_type) VALUES (8242, 'staff', 8238, 0, 0, 0, 0, 'Pick Up');
INSERT INTO transport (id, subscriber_type, subscriber_id, bus_id, route_id, trip_id, stop_id, transport_type) VALUES (8243, 'staff', 8238, 0, 0, 0, 0, 'Drop Off');
INSERT INTO transport (id, subscriber_type, subscriber_id, bus_id, route_id, trip_id, stop_id, transport_type) VALUES (8248, 'staff', 8244, 0, 0, 0, 0, 'Pick Up');
INSERT INTO transport (id, subscriber_type, subscriber_id, bus_id, route_id, trip_id, stop_id, transport_type) VALUES (8249, 'staff', 8244, 0, 0, 0, 0, 'Drop Off');
INSERT INTO transport (id, subscriber_type, subscriber_id, bus_id, route_id, trip_id, stop_id, transport_type) VALUES (8254, 'staff', 8250, 0, 0, 0, 0, 'Pick Up');
INSERT INTO transport (id, subscriber_type, subscriber_id, bus_id, route_id, trip_id, stop_id, transport_type) VALUES (8255, 'staff', 8250, 0, 0, 0, 0, 'Drop Off');
INSERT INTO transport (id, subscriber_type, subscriber_id, bus_id, route_id, trip_id, stop_id, transport_type) VALUES (8260, 'staff', 8256, 0, 0, 0, 0, 'Pick Up');
INSERT INTO transport (id, subscriber_type, subscriber_id, bus_id, route_id, trip_id, stop_id, transport_type) VALUES (8261, 'staff', 8256, 0, 0, 0, 0, 'Drop Off');
INSERT INTO transport (id, subscriber_type, subscriber_id, bus_id, route_id, trip_id, stop_id, transport_type) VALUES (8266, 'staff', 8262, 0, 0, 0, 0, 'Pick Up');
INSERT INTO transport (id, subscriber_type, subscriber_id, bus_id, route_id, trip_id, stop_id, transport_type) VALUES (8267, 'staff', 8262, 0, 0, 0, 0, 'Drop Off');
INSERT INTO transport (id, subscriber_type, subscriber_id, bus_id, route_id, trip_id, stop_id, transport_type) VALUES (8272, 'staff', 8268, 0, 0, 0, 0, 'Pick Up');
INSERT INTO transport (id, subscriber_type, subscriber_id, bus_id, route_id, trip_id, stop_id, transport_type) VALUES (8273, 'staff', 8268, 0, 0, 0, 0, 'Drop Off');
INSERT INTO transport (id, subscriber_type, subscriber_id, bus_id, route_id, trip_id, stop_id, transport_type) VALUES (8340, 'student', 8335, 0, 0, 0, 0, 'Drop Off');
INSERT INTO transport (id, subscriber_type, subscriber_id, bus_id, route_id, trip_id, stop_id, transport_type) VALUES (8341, 'student', 8335, 0, 0, 0, 0, 'Pick Up');
INSERT INTO transport (id, subscriber_type, subscriber_id, bus_id, route_id, trip_id, stop_id, transport_type) VALUES (8377, 'student', 8372, 0, 0, 0, 0, 'Drop Off');
INSERT INTO transport (id, subscriber_type, subscriber_id, bus_id, route_id, trip_id, stop_id, transport_type) VALUES (8378, 'student', 8372, 0, 0, 0, 0, 'Pick Up');
INSERT INTO transport (id, subscriber_type, subscriber_id, bus_id, route_id, trip_id, stop_id, transport_type) VALUES (8384, 'student', 8379, 0, 0, 0, 0, 'Drop Off');
INSERT INTO transport (id, subscriber_type, subscriber_id, bus_id, route_id, trip_id, stop_id, transport_type) VALUES (8385, 'student', 8379, 0, 0, 0, 0, 'Pick Up');
INSERT INTO transport (id, subscriber_type, subscriber_id, bus_id, route_id, trip_id, stop_id, transport_type) VALUES (8393, 'student', 8388, 0, 0, 0, 0, 'Drop Off');
INSERT INTO transport (id, subscriber_type, subscriber_id, bus_id, route_id, trip_id, stop_id, transport_type) VALUES (8394, 'student', 8388, 0, 0, 0, 0, 'Pick Up');
INSERT INTO transport (id, subscriber_type, subscriber_id, bus_id, route_id, trip_id, stop_id, transport_type) VALUES (8400, 'student', 8395, 0, 0, 0, 0, 'Drop Off');
INSERT INTO transport (id, subscriber_type, subscriber_id, bus_id, route_id, trip_id, stop_id, transport_type) VALUES (8401, 'student', 8395, 0, 0, 0, 0, 'Pick Up');
INSERT INTO transport (id, subscriber_type, subscriber_id, bus_id, route_id, trip_id, stop_id, transport_type) VALUES (8411, 'student', 8406, 0, 0, 0, 0, 'Drop Off');
INSERT INTO transport (id, subscriber_type, subscriber_id, bus_id, route_id, trip_id, stop_id, transport_type) VALUES (8412, 'student', 8406, 0, 0, 0, 0, 'Pick Up');
INSERT INTO transport (id, subscriber_type, subscriber_id, bus_id, route_id, trip_id, stop_id, transport_type) VALUES (8438, 'student', 8433, 8274, 8292, 8349, 8315, 'Drop Off');
INSERT INTO transport (id, subscriber_type, subscriber_id, bus_id, route_id, trip_id, stop_id, transport_type) VALUES (8439, 'student', 8433, 8274, 8291, 8317, 8315, 'Pick Up');
INSERT INTO transport (id, subscriber_type, subscriber_id, bus_id, route_id, trip_id, stop_id, transport_type) VALUES (8445, 'student', 8440, 8274, 8292, 8349, 8315, 'Drop Off');
INSERT INTO transport (id, subscriber_type, subscriber_id, bus_id, route_id, trip_id, stop_id, transport_type) VALUES (8446, 'student', 8440, 8274, 8291, 8317, 8315, 'Pick Up');
INSERT INTO transport (id, subscriber_type, subscriber_id, bus_id, route_id, trip_id, stop_id, transport_type) VALUES (8452, 'student', 8447, 8274, 8292, 8349, 8295, 'Drop Off');
INSERT INTO transport (id, subscriber_type, subscriber_id, bus_id, route_id, trip_id, stop_id, transport_type) VALUES (8453, 'student', 8447, 8274, 8291, 8317, 8295, 'Pick Up');
INSERT INTO transport (id, subscriber_type, subscriber_id, bus_id, route_id, trip_id, stop_id, transport_type) VALUES (8459, 'student', 8454, 8274, 8292, 8349, 8296, 'Drop Off');
INSERT INTO transport (id, subscriber_type, subscriber_id, bus_id, route_id, trip_id, stop_id, transport_type) VALUES (8460, 'student', 8454, 8274, 8291, 8317, 8296, 'Pick Up');
INSERT INTO transport (id, subscriber_type, subscriber_id, bus_id, route_id, trip_id, stop_id, transport_type) VALUES (8466, 'student', 8461, 8274, 8292, 8349, 8297, 'Drop Off');
INSERT INTO transport (id, subscriber_type, subscriber_id, bus_id, route_id, trip_id, stop_id, transport_type) VALUES (8467, 'student', 8461, 8274, 8291, 8317, 8297, 'Pick Up');
INSERT INTO transport (id, subscriber_type, subscriber_id, bus_id, route_id, trip_id, stop_id, transport_type) VALUES (8473, 'student', 8468, 8274, 8292, 8349, 8297, 'Drop Off');
INSERT INTO transport (id, subscriber_type, subscriber_id, bus_id, route_id, trip_id, stop_id, transport_type) VALUES (8474, 'student', 8468, 8274, 8291, 8317, 8297, 'Pick Up');
INSERT INTO transport (id, subscriber_type, subscriber_id, bus_id, route_id, trip_id, stop_id, transport_type) VALUES (8480, 'student', 8475, 8274, 8292, 8349, 8298, 'Drop Off');
INSERT INTO transport (id, subscriber_type, subscriber_id, bus_id, route_id, trip_id, stop_id, transport_type) VALUES (8481, 'student', 8475, 8274, 8291, 8317, 8298, 'Pick Up');
INSERT INTO transport (id, subscriber_type, subscriber_id, bus_id, route_id, trip_id, stop_id, transport_type) VALUES (8487, 'student', 8482, 8274, 8292, 8349, 8299, 'Drop Off');
INSERT INTO transport (id, subscriber_type, subscriber_id, bus_id, route_id, trip_id, stop_id, transport_type) VALUES (8488, 'student', 8482, 8274, 8291, 8317, 8299, 'Pick Up');
INSERT INTO transport (id, subscriber_type, subscriber_id, bus_id, route_id, trip_id, stop_id, transport_type) VALUES (8502, 'student', 8496, 8274, 8291, 8317, 8300, 'Pick Up');
INSERT INTO transport (id, subscriber_type, subscriber_id, bus_id, route_id, trip_id, stop_id, transport_type) VALUES (8501, 'student', 8496, 8274, 8292, 8349, 8300, 'Drop Off');
INSERT INTO transport (id, subscriber_type, subscriber_id, bus_id, route_id, trip_id, stop_id, transport_type) VALUES (8495, 'student', 8489, 8274, 8291, 8317, 8300, 'Pick Up');
INSERT INTO transport (id, subscriber_type, subscriber_id, bus_id, route_id, trip_id, stop_id, transport_type) VALUES (8494, 'student', 8489, 8274, 8292, 8349, 8300, 'Drop Off');
INSERT INTO transport (id, subscriber_type, subscriber_id, bus_id, route_id, trip_id, stop_id, transport_type) VALUES (8509, 'student', 8503, 8274, 8291, 8317, 8301, 'Pick Up');
INSERT INTO transport (id, subscriber_type, subscriber_id, bus_id, route_id, trip_id, stop_id, transport_type) VALUES (8508, 'student', 8503, 8274, 8292, 8349, 8301, 'Drop Off');
INSERT INTO transport (id, subscriber_type, subscriber_id, bus_id, route_id, trip_id, stop_id, transport_type) VALUES (8515, 'student', 8510, 8274, 8292, 8349, 8302, 'Drop Off');
INSERT INTO transport (id, subscriber_type, subscriber_id, bus_id, route_id, trip_id, stop_id, transport_type) VALUES (8516, 'student', 8510, 8274, 8291, 8317, 8302, 'Pick Up');
INSERT INTO transport (id, subscriber_type, subscriber_id, bus_id, route_id, trip_id, stop_id, transport_type) VALUES (8529, 'student', 8524, 8274, 8292, 8349, 8302, 'Drop Off');
INSERT INTO transport (id, subscriber_type, subscriber_id, bus_id, route_id, trip_id, stop_id, transport_type) VALUES (8530, 'student', 8524, 8274, 8291, 8317, 8302, 'Pick Up');
INSERT INTO transport (id, subscriber_type, subscriber_id, bus_id, route_id, trip_id, stop_id, transport_type) VALUES (8523, 'student', 8517, 8274, 8291, 8317, 8302, 'Pick Up');
INSERT INTO transport (id, subscriber_type, subscriber_id, bus_id, route_id, trip_id, stop_id, transport_type) VALUES (8522, 'student', 8517, 8274, 8292, 8349, 8302, 'Drop Off');
INSERT INTO transport (id, subscriber_type, subscriber_id, bus_id, route_id, trip_id, stop_id, transport_type) VALUES (8536, 'student', 8531, 8274, 8292, 8349, 8303, 'Drop Off');
INSERT INTO transport (id, subscriber_type, subscriber_id, bus_id, route_id, trip_id, stop_id, transport_type) VALUES (8537, 'student', 8531, 8274, 8291, 8317, 8303, 'Pick Up');
INSERT INTO transport (id, subscriber_type, subscriber_id, bus_id, route_id, trip_id, stop_id, transport_type) VALUES (8544, 'student', 8538, 8274, 8291, 8317, 8303, 'Pick Up');
INSERT INTO transport (id, subscriber_type, subscriber_id, bus_id, route_id, trip_id, stop_id, transport_type) VALUES (8543, 'student', 8538, 8274, 8292, 8349, 8303, 'Drop Off');
INSERT INTO transport (id, subscriber_type, subscriber_id, bus_id, route_id, trip_id, stop_id, transport_type) VALUES (8554, 'student', 8549, 8274, 8292, 8349, 8305, 'Drop Off');
INSERT INTO transport (id, subscriber_type, subscriber_id, bus_id, route_id, trip_id, stop_id, transport_type) VALUES (8555, 'student', 8549, 8274, 8291, 8317, 8305, 'Pick Up');
INSERT INTO transport (id, subscriber_type, subscriber_id, bus_id, route_id, trip_id, stop_id, transport_type) VALUES (8561, 'student', 8556, 8274, 8292, 8349, 8305, 'Drop Off');
INSERT INTO transport (id, subscriber_type, subscriber_id, bus_id, route_id, trip_id, stop_id, transport_type) VALUES (8562, 'student', 8556, 8274, 8291, 8317, 8305, 'Pick Up');
INSERT INTO transport (id, subscriber_type, subscriber_id, bus_id, route_id, trip_id, stop_id, transport_type) VALUES (8568, 'student', 8563, 8274, 8292, 8349, 8306, 'Drop Off');
INSERT INTO transport (id, subscriber_type, subscriber_id, bus_id, route_id, trip_id, stop_id, transport_type) VALUES (8569, 'student', 8563, 8274, 8291, 8317, 8306, 'Pick Up');
INSERT INTO transport (id, subscriber_type, subscriber_id, bus_id, route_id, trip_id, stop_id, transport_type) VALUES (8575, 'student', 8570, 8274, 8292, 8349, 8306, 'Drop Off');
INSERT INTO transport (id, subscriber_type, subscriber_id, bus_id, route_id, trip_id, stop_id, transport_type) VALUES (8576, 'student', 8570, 8274, 8291, 8317, 8306, 'Pick Up');
INSERT INTO transport (id, subscriber_type, subscriber_id, bus_id, route_id, trip_id, stop_id, transport_type) VALUES (8582, 'student', 8577, 8274, 8292, 8349, 8307, 'Drop Off');
INSERT INTO transport (id, subscriber_type, subscriber_id, bus_id, route_id, trip_id, stop_id, transport_type) VALUES (8583, 'student', 8577, 8274, 8291, 8317, 8307, 'Pick Up');
INSERT INTO transport (id, subscriber_type, subscriber_id, bus_id, route_id, trip_id, stop_id, transport_type) VALUES (8590, 'student', 8585, 8274, 8292, 8349, 8307, 'Drop Off');
INSERT INTO transport (id, subscriber_type, subscriber_id, bus_id, route_id, trip_id, stop_id, transport_type) VALUES (8591, 'student', 8585, 8274, 8291, 8317, 8307, 'Pick Up');
INSERT INTO transport (id, subscriber_type, subscriber_id, bus_id, route_id, trip_id, stop_id, transport_type) VALUES (8597, 'student', 8592, 8274, 8292, 8349, 8308, 'Drop Off');
INSERT INTO transport (id, subscriber_type, subscriber_id, bus_id, route_id, trip_id, stop_id, transport_type) VALUES (8598, 'student', 8592, 8274, 8291, 8317, 8308, 'Pick Up');
INSERT INTO transport (id, subscriber_type, subscriber_id, bus_id, route_id, trip_id, stop_id, transport_type) VALUES (8604, 'student', 8599, 8274, 8292, 8349, 8309, 'Drop Off');
INSERT INTO transport (id, subscriber_type, subscriber_id, bus_id, route_id, trip_id, stop_id, transport_type) VALUES (8605, 'student', 8599, 8274, 8291, 8317, 8309, 'Pick Up');
INSERT INTO transport (id, subscriber_type, subscriber_id, bus_id, route_id, trip_id, stop_id, transport_type) VALUES (8611, 'student', 8606, 8274, 8292, 8349, 8309, 'Drop Off');
INSERT INTO transport (id, subscriber_type, subscriber_id, bus_id, route_id, trip_id, stop_id, transport_type) VALUES (8612, 'student', 8606, 8274, 8291, 8317, 8309, 'Pick Up');
INSERT INTO transport (id, subscriber_type, subscriber_id, bus_id, route_id, trip_id, stop_id, transport_type) VALUES (8618, 'student', 8613, 8274, 8292, 8349, 8309, 'Drop Off');
INSERT INTO transport (id, subscriber_type, subscriber_id, bus_id, route_id, trip_id, stop_id, transport_type) VALUES (8619, 'student', 8613, 8274, 8291, 8317, 8309, 'Pick Up');
INSERT INTO transport (id, subscriber_type, subscriber_id, bus_id, route_id, trip_id, stop_id, transport_type) VALUES (8625, 'student', 8620, 8274, 8292, 8349, 8309, 'Drop Off');
INSERT INTO transport (id, subscriber_type, subscriber_id, bus_id, route_id, trip_id, stop_id, transport_type) VALUES (8626, 'student', 8620, 8274, 8291, 8317, 8309, 'Pick Up');
INSERT INTO transport (id, subscriber_type, subscriber_id, bus_id, route_id, trip_id, stop_id, transport_type) VALUES (8632, 'student', 8627, 8274, 8292, 8349, 8310, 'Drop Off');
INSERT INTO transport (id, subscriber_type, subscriber_id, bus_id, route_id, trip_id, stop_id, transport_type) VALUES (8633, 'student', 8627, 8274, 8291, 8317, 8310, 'Pick Up');
INSERT INTO transport (id, subscriber_type, subscriber_id, bus_id, route_id, trip_id, stop_id, transport_type) VALUES (8639, 'student', 8634, 8274, 8292, 8349, 8311, 'Drop Off');
INSERT INTO transport (id, subscriber_type, subscriber_id, bus_id, route_id, trip_id, stop_id, transport_type) VALUES (8640, 'student', 8634, 8274, 8291, 8317, 8311, 'Pick Up');
INSERT INTO transport (id, subscriber_type, subscriber_id, bus_id, route_id, trip_id, stop_id, transport_type) VALUES (8646, 'student', 8641, 8274, 8292, 8349, 8311, 'Drop Off');
INSERT INTO transport (id, subscriber_type, subscriber_id, bus_id, route_id, trip_id, stop_id, transport_type) VALUES (8647, 'student', 8641, 8274, 8291, 8317, 8311, 'Pick Up');
INSERT INTO transport (id, subscriber_type, subscriber_id, bus_id, route_id, trip_id, stop_id, transport_type) VALUES (8653, 'student', 8648, 8274, 8292, 8349, 8312, 'Drop Off');
INSERT INTO transport (id, subscriber_type, subscriber_id, bus_id, route_id, trip_id, stop_id, transport_type) VALUES (8654, 'student', 8648, 8274, 8291, 8317, 8312, 'Pick Up');
INSERT INTO transport (id, subscriber_type, subscriber_id, bus_id, route_id, trip_id, stop_id, transport_type) VALUES (8660, 'student', 8655, 8274, 8292, 8349, 8312, 'Drop Off');
INSERT INTO transport (id, subscriber_type, subscriber_id, bus_id, route_id, trip_id, stop_id, transport_type) VALUES (8661, 'student', 8655, 8274, 8291, 8317, 8312, 'Pick Up');
INSERT INTO transport (id, subscriber_type, subscriber_id, bus_id, route_id, trip_id, stop_id, transport_type) VALUES (1011, 'student', 1006, 740, 744, 752, 633, 'Drop Off');
INSERT INTO transport (id, subscriber_type, subscriber_id, bus_id, route_id, trip_id, stop_id, transport_type) VALUES (1051, 'student', 1046, 740, 744, 752, 741, 'Drop Off');
INSERT INTO transport (id, subscriber_type, subscriber_id, bus_id, route_id, trip_id, stop_id, transport_type) VALUES (1045, 'student', 1039, 740, 12662, 12668, 2503, 'Pick Up');
INSERT INTO transport (id, subscriber_type, subscriber_id, bus_id, route_id, trip_id, stop_id, transport_type) VALUES (1044, 'student', 1039, 740, 744, 752, 742, 'Drop Off');
INSERT INTO transport (id, subscriber_type, subscriber_id, bus_id, route_id, trip_id, stop_id, transport_type) VALUES (1057, 'staff', 1053, 740, 2939, 6634, 633, 'Pick Up');
INSERT INTO transport (id, subscriber_type, subscriber_id, bus_id, route_id, trip_id, stop_id, transport_type) VALUES (1058, 'staff', 1053, 740, 744, 752, 633, 'Drop Off');
INSERT INTO transport (id, subscriber_type, subscriber_id, bus_id, route_id, trip_id, stop_id, transport_type) VALUES (1063, 'staff', 1059, 740, 2939, 6634, 741, 'Pick Up');
INSERT INTO transport (id, subscriber_type, subscriber_id, bus_id, route_id, trip_id, stop_id, transport_type) VALUES (1064, 'staff', 1059, 740, 744, 752, 741, 'Drop Off');
INSERT INTO transport (id, subscriber_type, subscriber_id, bus_id, route_id, trip_id, stop_id, transport_type) VALUES (1070, 'staff', 1066, 740, 2939, 6634, 742, 'Pick Up');
INSERT INTO transport (id, subscriber_type, subscriber_id, bus_id, route_id, trip_id, stop_id, transport_type) VALUES (1012, 'student', 1006, 740, 12662, 12668, 2502, 'Pick Up');
INSERT INTO transport (id, subscriber_type, subscriber_id, bus_id, route_id, trip_id, stop_id, transport_type) VALUES (1052, 'student', 1046, 740, 12662, 12668, 12654, 'Pick Up');
INSERT INTO transport (id, subscriber_type, subscriber_id, bus_id, route_id, trip_id, stop_id, transport_type) VALUES (1071, 'staff', 1066, 740, 12656, 13496, 2502, 'Drop Off');
INSERT INTO transport (id, subscriber_type, subscriber_id, bus_id, route_id, trip_id, stop_id, transport_type) VALUES (15856, 'student', 15854, 0, 0, 0, 0, 'Drop Off');
INSERT INTO transport (id, subscriber_type, subscriber_id, bus_id, route_id, trip_id, stop_id, transport_type) VALUES (15864, 'student', 15862, 0, 0, 0, 0, 'Drop Off');
INSERT INTO transport (id, subscriber_type, subscriber_id, bus_id, route_id, trip_id, stop_id, transport_type) VALUES (15871, 'student', 15870, 740, 744, 752, 0, 'Pick Up');
INSERT INTO transport (id, subscriber_type, subscriber_id, bus_id, route_id, trip_id, stop_id, transport_type) VALUES (15872, 'student', 15870, 740, 744, 752, 633, 'Drop Off');


--
-- TOC entry 2348 (class 0 OID 0)
-- Dependencies: 208
-- Name: transport_id_seq; Type: SEQUENCE SET; Schema: public; Owner: easycommute
--

SELECT pg_catalog.setval('transport_id_seq', 1, false);


--
-- TOC entry 2322 (class 0 OID 88957)
-- Dependencies: 213
-- Data for Name: trip_history; Type: TABLE DATA; Schema: public; Owner: easycommute
--

INSERT INTO trip_history (id, date, report_status, students_absent, students_present, trip_name, trip_type, bus_licence_number) VALUES (16018, '2015-09-05', 'Send', '4', '2', 'Drop Off one', 'Drop Off', '99');
INSERT INTO trip_history (id, date, report_status, students_absent, students_present, trip_name, trip_type, bus_licence_number) VALUES (16098, '2015-09-05', 'Send', '2', '2', 'Drop Off one', 'Drop Off', '99');


--
-- TOC entry 2318 (class 0 OID 64711)
-- Dependencies: 209
-- Data for Name: trips; Type: TABLE DATA; Schema: public; Owner: easycommute
--

INSERT INTO trips (id, busid, routeid, school_time_hours, school_time_minutes, seats_filled, trip_end_time_hours, trip_end_time_minutes, trip_name, trip_start_time_hours, trip_start_time_minutes, trip_type) VALUES (8104, 8098, 8101, 12, 59, 1, 15, 25, 'KLON24OTTVL: Drop Off', 12, 58, 'Drop Off');
INSERT INTO trips (id, busid, routeid, school_time_hours, school_time_minutes, seats_filled, trip_end_time_hours, trip_end_time_minutes, trip_name, trip_start_time_hours, trip_start_time_minutes, trip_type) VALUES (8317, 8274, 8291, 7, 59, 32, 8, 25, 'KLON01DUNR: PickUp', 6, 57, 'Pick Up');
INSERT INTO trips (id, busid, routeid, school_time_hours, school_time_minutes, seats_filled, trip_end_time_hours, trip_end_time_minutes, trip_name, trip_start_time_hours, trip_start_time_minutes, trip_type) VALUES (6634, 740, 2939, 8, 22, 3, 8, 25, 'Pick up Ratnadeep-IIIT', 8, 14, 'Pick Up');
INSERT INTO trips (id, busid, routeid, school_time_hours, school_time_minutes, seats_filled, trip_end_time_hours, trip_end_time_minutes, trip_name, trip_start_time_hours, trip_start_time_minutes, trip_type) VALUES (12668, 740, 12662, 17, 8, 3, 17, 10, 'Basheerbagh Pickup', 16, 35, 'Pick Up');
INSERT INTO trips (id, busid, routeid, school_time_hours, school_time_minutes, seats_filled, trip_end_time_hours, trip_end_time_minutes, trip_name, trip_start_time_hours, trip_start_time_minutes, trip_type) VALUES (13496, 740, 12656, 20, 17, 2, 20, 51, 'Basheerbagh Drop Off', 20, 15, 'Drop Off');
INSERT INTO trips (id, busid, routeid, school_time_hours, school_time_minutes, seats_filled, trip_end_time_hours, trip_end_time_minutes, trip_name, trip_start_time_hours, trip_start_time_minutes, trip_type) VALUES (8349, 8274, 8292, 15, 49, 32, 22, 25, 'KLON01DUNR: Drop Off A', 15, 47, 'Drop Off');
INSERT INTO trips (id, busid, routeid, school_time_hours, school_time_minutes, seats_filled, trip_end_time_hours, trip_end_time_minutes, trip_name, trip_start_time_hours, trip_start_time_minutes, trip_type) VALUES (752, 740, 744, 17, 13, 4, 19, 48, 'Drop Off one', 11, 12, 'Drop Off');


--
-- TOC entry 2330 (class 0 OID 97453)
-- Dependencies: 221
-- Data for Name: user_gps_data; Type: TABLE DATA; Schema: public; Owner: easycommute
--

INSERT INTO user_gps_data (id, booking_id, lat, long, user_id) VALUES (1, NULL, '20.0', '24.0', NULL);
INSERT INTO user_gps_data (id, booking_id, lat, long, user_id) VALUES (2, NULL, '20.0', '24.0', NULL);
INSERT INTO user_gps_data (id, booking_id, lat, long, user_id) VALUES (3, NULL, '20.0', '24.0', NULL);
INSERT INTO user_gps_data (id, booking_id, lat, long, user_id) VALUES (4, NULL, '20.0', '24.0', NULL);


--
-- TOC entry 2320 (class 0 OID 64720)
-- Dependencies: 211
-- Data for Name: users; Type: TABLE DATA; Schema: public; Owner: easycommute
--

INSERT INTO users (user_id, full_name, user_name, password, access_status, name) VALUES (28, 'school admin', 'admin', 'XZ2UYtlZHLU=', 'Enabled', NULL);
INSERT INTO users (user_id, full_name, user_name, password, access_status, name) VALUES (15850, 'Syed Samiuddin', 'parent', '3fpI99s620E=', 'Enabled', NULL);
INSERT INTO users (user_id, full_name, user_name, password, access_status, name) VALUES (15849, 'TEACHER', 'teacher', '6NNw5g+pgrQ=', 'Enabled', NULL);
INSERT INTO users (user_id, full_name, user_name, password, access_status, name) VALUES (16108, 'kranthi', 'kranthi', '75OohSmCumMzblTx0RGZeA==', 'Enabled', NULL);


--
-- TOC entry 2319 (class 0 OID 64717)
-- Dependencies: 210
-- Data for Name: user_roles; Type: TABLE DATA; Schema: public; Owner: easycommute
--

INSERT INTO user_roles (user_id, role_id) VALUES (28, 1);
INSERT INTO user_roles (user_id, role_id) VALUES (8135, 1);
INSERT INTO user_roles (user_id, role_id) VALUES (15849, 15848);
INSERT INTO user_roles (user_id, role_id) VALUES (15850, 8136);
INSERT INTO user_roles (user_id, role_id) VALUES (16108, 1);


--
-- TOC entry 2349 (class 0 OID 0)
-- Dependencies: 212
-- Name: users_user_id_seq; Type: SEQUENCE SET; Schema: public; Owner: easycommute
--

SELECT pg_catalog.setval('users_user_id_seq', 1, false);


-- Completed on 2015-10-06 13:32:15 IST

--
-- easycommuteQL database dump complete
--

