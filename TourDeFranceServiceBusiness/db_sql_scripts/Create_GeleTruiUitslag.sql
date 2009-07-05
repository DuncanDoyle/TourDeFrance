CREATE TABLE TOUR.GELETRUIUITSLAG (
  etappenummer INTEGER,
  positie INTEGER,
  rennernummer INTEGER NOT NULL, 
  PRIMARY KEY(etappenummer, positie),
  FOREIGN KEY (rennernummer) REFERENCES TOUR.RENNER(nummer));