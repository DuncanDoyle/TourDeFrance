CREATE TABLE TOUR.ETAPPE_GROENETRUIUITSLAG (
	etappenummer INTEGER,
	positie INTEGER,
	PRIMARY KEY(etappenummer, positie),
  	FOREIGN KEY (etappenummer) REFERENCES TOUR.ETAPPE(etappenummer),
  	FOREIGN KEY (etappenummer,positie) REFERENCES TOUR.GROENETRUIUITSLAG(etappenummer,positie));