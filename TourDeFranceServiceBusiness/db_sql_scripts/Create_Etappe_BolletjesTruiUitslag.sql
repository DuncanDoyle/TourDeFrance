CREATE TABLE TOUR.ETAPPE_BOLLETJESTRUIUITSLAG (
	etappenummer INTEGER,
	positie INTEGER,
	PRIMARY KEY(etappenummer, positie),
  	FOREIGN KEY (etappenummer) REFERENCES TOUR.ETAPPE(etappenummer),
  	FOREIGN KEY (etappenummer,positie) REFERENCES TOUR.BOLLETJESTRUIUITSLAG(etappenummer,positie));