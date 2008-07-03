CREATE TABLE TOUR.TEAM_RENNER (
  rennernummer INTEGER,
  teamnummer INTEGER,
  PRIMARY KEY(rennernummer),
  FOREIGN KEY (teamnummer) REFERENCES TOUR.TEAM(nummer),
  FOREIGN KEY (rennernummer) REFERENCES TOUR.RENNER(nummer));
  