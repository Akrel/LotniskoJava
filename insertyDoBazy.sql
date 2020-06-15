INSERT INTO airport(code,country,city) VALUES('GDA','POLAND','Gdansk');
INSERT INTO airport(code,country,city) VALUES('KRK','POLAND','Krakow');
INSERT INTO airport(code,country,city) VALUES('KTW','POLAND','Katowice');
INSERT INTO airport(code,country,city) VALUES('RDO','POLAND','Radom');
INSERT INTO airport(code,country,city) VALUES('WAW','POLAND','Warsaw');
INSERT INTO airport(code,country,city) VALUES('LCJ','POLAND','Łódź');


INSERT INTO flight(id,number_seats,origin,date_departure,destination,date_arrival,price)VALUES(1,30,'KRK',TO_DATE('17/12/2020 13:00:00', 'DD/MM/YYYY HH24:MI:SS'),'GDA',TO_DATE('20/12/2020 09:00:00', 'DD/MM/YYYY HH24:MI:SS'),250);
INSERT INTO flight(id,number_seats,origin,date_departure,destination,date_arrival,price)VALUES(2,1,'RDO',TO_DATE('02/06/2020 05:40:00', 'DD/MM/YYYY HH24:MI:SS'),'GDA',TO_DATE('03/06/2020 12:00:00', 'DD/MM/YYYY HH24:MI:SS'),250);
INSERT INTO flight(id,number_seats,origin,date_departure,destination,date_arrival,price)VALUES(3,10,'GDA',TO_DATE('05/09/2020 07:00:00', 'DD/MM/YYYY HH24:MI:SS'),'KTW',TO_DATE('07/09/2020 15:00:00', 'DD/MM/YYYY HH24:MI:SS'),250);
INSERT INTO flight(id,number_seats,origin,date_departure,destination,date_arrival,price)VALUES(4,3,'KRK',TO_DATE('23/07/2020 11:40:00', 'DD/MM/YYYY HH24:MI:SS'),'RDO',TO_DATE('20/08/2020 17:30:00', 'DD/MM/YYYY HH24:MI:SS'),250);
INSERT INTO flight(id,number_seats,origin,date_departure,destination,date_arrival,price)VALUES(5,40,'KTW',TO_DATE('30/07/2020 18:00:00', 'DD/MM/YYYY HH24:MI:SS'),'KRK',TO_DATE('02/08/2020 13:00:00', 'DD/MM/YYYY HH24:MI:SS'),250);
INSERT INTO flight(id,number_seats,origin,date_departure,destination,date_arrival,price)VALUES(6,30,'KTW',TO_DATE('21/07/2020 19:00:00', 'DD/MM/YYYY HH24:MI:SS'),'KRK',TO_DATE('25/07/2020 15:00:00', 'DD/MM/YYYY HH24:MI:SS'),250);
INSERT INTO flight(id,number_seats,origin,date_departure,destination,date_arrival,price)VALUES(7,150,'KTW',TO_DATE('05/07/2020 23:00:00', 'DD/MM/YYYY HH24:MI:SS'),'KRK',TO_DATE('12/07/2020 08:00:00', 'DD/MM/YYYY HH24:MI:SS'),250);

