CREATE TABLE TOUR.PLOEGENTIJDRIT (
  etappenummer INTEGER, 
  PRIMARY KEY(etappenummer),
  FOREIGN KEY(etappenummer) REFERENCES TOUR.ETAPPE(etappenummer));