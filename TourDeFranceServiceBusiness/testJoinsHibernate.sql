select standaarde0.ETAPPENUMMER as ETAPPENU1, 
		geletruiui1.ETAPPENUMMER as ETAPPENU1, 
		geletruiui1.POSITIE as POSITIE6, 
		renner2.NUMMER as NUMMER0, 
		groenetrui3.ETAPPENUMMER as ETAPPENU1, 
		groenetrui3.POSITIE as POSITIE7, 
		renner4.NUMMER as NUMMER0, 
		standaarde01.DATUM as DATUM3, 
		standaarde01.STARTPLAATS as STARTPLA3, 
		standaarde01.FINISHPLAATS as FINISHPL4, 
		geletruiui1.RENNERNUMMER as RENNERNU3, 
		geletruiui1.ETAPPENUMMER as ETAPPENU1, 
		geletruiui1.POSITIE as POSITIE0, 
		renner2.VOORNAAM as VOORNAAM0, 
		renner2.ACHTERNAAM as ACHTERNAAM0, 
		groenetrui3.RENNERNUMMER as RENNERNU3, 
		groenetrui3.ETAPPENUMMER as ETAPPENU1, 
		groenetrui3.POSITIE as POSITIE1, 
		renner4.VOORNAAM as VOORNAAM0, 
		renner4.ACHTERNAAM as ACHTERNAAM0 
from STANDAARDETAPPE standaarde0 
		inner join ETAPPE standaarde01 on standaarde01.ETAPPENUMMER=standaarde01.ETAPPENUMMER 
		left outer join GELETRUIUITSLAG geletruiui1 on standaarde0.ETAPPENUMMER=geletruiui1.ETAPPENUMMER 
		left outer join RENNER renner2 on geletruiui1.RENNERNUMMER=renner2.NUMMER 
		left outer join GROENETRUIUITSLAG groenetrui3 on standaarde0.ETAPPENUMMER=groenetrui3.ETAPPENUMMER 
		left outer join RENNER renner4 on groenetrui3.RENNERNUMMER=renner4.NUMMER 
where standaarde0.ETAPPENUMMER=1;