INSERT INTO airport(code,country,city) VALUES('WAW','POLAND','Gdansk');
INSERT INTO airport(code,country,city) VALUES('KRK','POLAND','Krakow');
INSERT INTO airport(code,country,city) VALUES('KTW','POLAND','Katowice');
INSERT INTO airport(code,country,city) VALUES('RDO','POLAND','Radom');




INSERT INTO flight(id,number_seats,origin,date_departure,destination,date_arrival,price)VALUES(1,30,'KRK',TO_DATE('17/12/2020', 'DD/MM/YYYY'),'WAW',TO_DATE('20/12/2020', 'DD/MM/YYYY'),250);
INSERT INTO flight(id,number_seats,origin,date_departure,destination,date_arrival,price)VALUES(2,1,'RDO',TO_DATE('02/06/2020', 'DD/MM/YYYY'),'WAW',TO_DATE('03/06/2020', 'DD/MM/YYYY'),250);
INSERT INTO flight(id,number_seats,origin,date_departure,destination,date_arrival,price)VALUES(3,10,'WAW',TO_DATE('05/09/2020', 'DD/MM/YYYY'),'KTW',TO_DATE('07/09/2020', 'DD/MM/YYYY'),250);
INSERT INTO flight(id,number_seats,origin,date_departure,destination,date_arrival,price)VALUES(4,3,'KRK',TO_DATE('17/08/2020', 'DD/MM/YYYY'),'RDO',TO_DATE('20/08/2020', 'DD/MM/YYYY'),250);
INSERT INTO flight(id,number_seats,origin,date_departure,destination,date_arrival,price)VALUES(5,2,'KTW',TO_DATE('17/12/2020', 'DD/MM/YYYY'),'KRK',TO_DATE('20/12/2020', 'DD/MM/YYYY'),250);