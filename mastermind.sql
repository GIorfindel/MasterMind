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
	coups INT NOT NULL,
	FOREIGN KEY (id_joueur) REFERENCES Joueur(id),
	CHECK ( niveau = 'tres_facile' OR niveau = 'facile' OR niveau = 'normal' OR niveau = 'difficile' OR niveau = 'tres_difficile' )	
);

CREATE TABLE Couleur(
	id_Partie INT,
	place INT NOT NULL,
	numero INT NOT NULL,
	PRIMARY KEY (id_Partie, place),
	FOREIGN KEY (id_Partie) REFERENCES Partie(id_Partie),
	CHECK (numero<10)
);
