CREATE TABLE TOUR.DEELNEMER_RENNER (
  rennernummer INTEGER, 
  deelnemernummer INTEGER,
  PRIMARY KEY(rennernummer),
  FOREIGN KEY(rennernummer) REFERENCES TOUR.RENNER(nummer),
  FOREIGN KEY(deelnemernummer) REFERENCES TOUR.DEELNEMER(nummer));
