CREATE TABLE TOUR.STANDAARDETAPPE_ETAPPEUITSLAG (
	etappenummer INTEGER,
	positie INTEGER,
	PRIMARY KEY(etappenummer, positie),
  	FOREIGN KEY (etappenummer) REFERENCES TOUR.STANDAARDETAPPE(etappenummer),
  	FOREIGN KEY (etappenummer,positie) REFERENCES TOUR.ETAPPEUITSLAG(etappenummer,positie));