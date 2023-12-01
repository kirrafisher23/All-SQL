create schema  FisherSubmission;

create table FisherSubmission.Director(
dir_id integer unique NOT NULL auto_increment,
dir_fname varchar(255) NOT NULL,
dir_lname varchar(255) NOT NULL,
dir_yearsActive integer NOT NULL,
PRIMARY KEY (dir_id)
);

create table FisherSubmission.Movie(
movie_id integer unique NOT NULL auto_increment,
movie_name varchar(255) NOT NULL,
movie_year integer NOT NULL,
dir_id integer NOT NULL,
PRIMARY KEY (movie_id),
foreign key(dir_id) references Director(dir_id)
);

create table FisherSubmission.Review(
rev_id integer unique NOT NULL auto_increment,
contents varchar(3000) NOT NULL,
rev_stars integer NOT NULL,
rev_date date not null,
movie_id integer,
PRIMARY KEY (rev_id,movie_id),
foreign key(movie_id) references Movie(movie_id)
);

create table FisherSubmission.Actor(
actor_id integer unique NOT NULL auto_increment,
actor_fname varchar(255) NOT NULL,
actor_lname varchar(255) NOT NULL,
actor_yearsActive integer NOT NULL,
PRIMARY KEY (actor_id)
);

CREATE TABLE FisherSubmission.Actor_Movie (
    movie_id INTEGER NOT NULL,
    actor_id INTEGER NOT NULL,
    character_name VARCHAR(255),
    PRIMARY KEY (movie_id, actor_id),
    FOREIGN KEY (movie_id) REFERENCES FisherSubmission.Movie (movie_id),
    FOREIGN KEY (actor_id) REFERENCES FisherSubmission.Actor (actor_id)
);

INSERT INTO FisherSubmission.Director (dir_fname, dir_lname, dir_yearsActive)
VALUES ('Edgar', 'Wright', 29);
INSERT INTO FisherSubmission.Director (dir_fname, dir_lname, dir_yearsActive)
VALUES ('David', 'Ellis', 38);
INSERT INTO FisherSubmission.Director (dir_fname, dir_lname, dir_yearsActive)
VALUES ('Stephen', 'Spielberg', 54);

INSERT INTO FisherSubmission.Movie (dir_id, movie_name, movie_year)
VALUES (1, 'Hot Fuzz', 2007);
INSERT INTO FisherSubmission.Movie (dir_id, movie_name, movie_year)
VALUES (1, 'Shaun of the Dead', 2004);
INSERT INTO FisherSubmission.Movie (dir_id, movie_name, movie_year)
VALUES (2, 'Snakes on a Plane', 2006);
INSERT INTO FisherSubmission.Movie (dir_id, movie_name, movie_year)
VALUES (3, 'Jurassic Park', 1993);

INSERT INTO FisherSubmission.Review (movie_id, contents, rev_stars, rev_date)
VALUES (1,'Bro. This movie slaps. Might be the best movie of all time.', 5, '2023-09-22');
INSERT INTO FisherSubmission.Review (movie_id, contents, rev_stars, rev_date)
VALUES (1,'Yoooo this movie is absolutely hilarious, I loved when he drop-kicked the old lady lol', 5, '2022-01-01');
INSERT INTO FisherSubmission.Review (movie_id, contents, rev_stars, rev_date)
VALUES (2,'A cautionary tale of a young man searching for love in a post-apocalyptic world. An instant classic.', 5, '2021-05-05');
INSERT INTO FisherSubmission.Review (movie_id, contents, rev_stars, rev_date)
VALUES (2,'IDK. Its ite. But not as good as Hot Fuzz', 3, '2010-12-01');
INSERT INTO FisherSubmission.Review (movie_id, contents, rev_stars, rev_date)
VALUES (3,'This movie sucks. It has snakes.... on a plane.', 1, '2009-02-02');
INSERT INTO FisherSubmission.Review (movie_id, contents, rev_stars, rev_date)
VALUES (3,'This movie rocks. It has snakes.... on a plane!', 5, '2008-04-04');
INSERT INTO FisherSubmission.Review (movie_id, contents, rev_stars, rev_date)
VALUES (4,'This is the best movie in the franchise. The newer stuff sucks', 5, '2011-07-07');
INSERT INTO FisherSubmission.Review (movie_id, contents, rev_stars, rev_date)
VALUES (4,'Lol the lawyer got eaten off the toilet :D', 5, '2023-09-01');

insert into fishersubmission.actor(actor_fname,actor_lname,actor_yearsActive)
values('Simon','Pegg',28);
insert into fishersubmission.actor(actor_fname,actor_lname,actor_yearsActive)
values('Nick','Frost',25);
insert into fishersubmission.actor(actor_fname,actor_lname,actor_yearsActive)
values('Samuel','Jackson',28);


insert into FisherSubmission.Actor_Movie(movie_id,actor_id,character_name)
values(1,1,'Nicholas Angel');
insert into FisherSubmission.Actor_Movie(movie_id,actor_id,character_name)
values(1,2,'Danny Butterman');
insert into FisherSubmission.Actor_Movie(movie_id,actor_id,character_name)
values(2,1,'Shaun');
insert into FisherSubmission.Actor_Movie(movie_id,actor_id,character_name)
values(2,2,'Ed');
insert into FisherSubmission.Actor_Movie(movie_id,actor_id,character_name)
values(3,3,'Neville Flynn');
insert into FisherSubmission.Actor_Movie(movie_id,actor_id,character_name)
values(4,3,'Ray Arnold');


