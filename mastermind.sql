
CREATE TABLE Joueur(
	id INT AUTO_INCREMENT PRIMARY KEY,
	identifiant VARCHAR(20) not null,
	mdp VARCHAR(20) not null,
	avatar VARCHAR(20),
	malus INT not null
);

CREATE TABLE Score(
	id INT AUTO_INCREMENT PRIMARY KEY,
	mode_solo_multi VARCHAR(10) not null,
	coups INT not null,
	victoire INT(1),
	tours INT,
	niveau VARCHAR(20),
	CHECK ( mode_solo_multi = 'solo' OR mode_solo_multi = 'multi' ),
	CHECK ( niveau = 'tres_facile' OR niveau = 'facile' OR niveau = 'normal' OR niveau = 'difficile' OR niveau = 'tres_difficile' )
);

CREATE TABLE Joueur_Score(
	id_joueur INT,
	id_score INT,
	FOREIGN KEY (id_joueur) REFERENCES Joueur(id),
	FOREIGN KEY (id_score) REFERENCES Score(id)
);

CREATE TABLE Partie(
	id_Partie INT AUTO_INCREMENT PRIMARY KEY,
	id_Joueur INT,
	nom VARCHAR(20),
	niveau VARCHAR(20) NOT NULL,
	FOREIGN KEY (id_joueur) REFERENCES Joueur(id),
	CHECK ( niveau = 'tres_facile' OR niveau = 'facile' OR niveau = 'normal' OR niveau = 'difficile' OR niveau = 'tres_difficile' )	
);

CREATE TABLE Essais(
	id INT PRIMARY KEY AUTO_INCREMENT
);

CREATE TABLE Combinaison(
	id INT PRIMARY KEY AUTO_INCREMENT
);

CREATE TABLE Tour(
	id INT AUTO_INCREMENT PRIMARY KEY,
	coups INT NOT NULL,
	combinaison INT,
	essais INT,
	FOREIGN KEY (combinaison) REFERENCES Combinaison(id),
	FOREIGN KEY (essais) REFERENCES Essais(id)
);

CREATE TABLE Solo(
	id_Partie INT,
	coups INT NOT NULL,
	nbTours INT NOT NULL,
	tour INT NOT NULL,
	FOREIGN KEY (id_Partie) REFERENCES Partie(id_Partie),
	FOREIGN KEY (tour) REFERENCES Tour(id)
);

CREATE TABLE Essai(
	id_essais INT,
	id_combinaison INT,
	place INT,
	FOREIGN KEY (id_essais) REFERENCES Essais(id),
	FOREIGN KEY (id_combinaison) REFERENCES Combinaison(id)
);

CREATE TABLE Couleur(
	nom VARCHAR(20) PRIMARY KEY
);

CREATE TABLE Pions(
	id_combinaison INT,
	id_couleur VARCHAR(20),
	place INT NOT NULL,
	FOREIGN KEY (id_combinaison) REFERENCES Combinaison(id),
	FOREIGN KEY (id_couleur) REFERENCES Couleur(nom)
);

