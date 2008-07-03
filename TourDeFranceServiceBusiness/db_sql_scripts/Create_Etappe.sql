CREATE TABLE TOUR.ETAPPE (
  etappenummer INTEGER, 
  datum DATE,
  startplaats INTEGER,
  finishplaats INTEGER,
  PRIMARY KEY(etappenummer),
  FOREIGN KEY (startplaats) REFERENCES TOUR.STAD(id),
  FOREIGN KEY (finishplaats) REFERENCES TOUR.STAD(id)
);
  
  