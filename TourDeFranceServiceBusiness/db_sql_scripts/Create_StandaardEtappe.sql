CREATE TABLE TOUR.STANDAARDETAPPE (
  etappenummer INTEGER, 
  PRIMARY KEY(etappenummer),
  FOREIGN KEY(etappenummer) REFERENCES TOUR.ETAPPE(etappenummer));