create schema fishingProject;
use fishingProject;

create table catch(
	t_code varchar(10),
    t_name	varchar(255),
    t_loc	varchar(255),
    t_date	date,
    a_id	integer,
    a_name	varchar(255),
    a_add	varchar(255),
    a_phone	varchar(255),
    fish_species	varchar(255),
    fish_class	varchar(255),
    weight	integer,
    length	integer
);

insert into catch VALUES
("BGHFE", "Big Game Hunters - Fish Edition - 2023", "Jacksonville", '2023-01-01', 1, "Aaron Aaronson", "123 Place Circle", "111-111-1111", "Blacktip Shark", "Shark", 24, 30),
("BGHFE", "Big Game Hunters - Fish Edition - 2023", "Jacksonville", '2023-01-01', 1, "Aaron Aaronson", "123 Place Circle", "111-111-1111", "Great White", "Shark", 48, 100),
("BGHFE", "Big Game Hunters - Fish Edition - 2023", "Jacksonville", '2023-01-01', 2, "Bob Bert", "321 Other Place", "222-222-2222", "Hammerhead", "Shark", 40, 75),
("BGHFE", "Big Game Hunters - Fish Edition - 2023", "Jacksonville", '2023-01-01', 2, "Bob Bert", "321 Other Place", "222-222-2222", "Blacktip Shark", "Shark", 26, 31),
("BGHFE", "Big Game Hunters - Fish Edition - 2023", "Jacksonville", '2023-01-01', 3, "Chris Candy", "555 Lane Ln", "555-555-5555", "Mako", "Shark", 48, 95),
("BBTRN", "Bass Buddies", "Orlando", '2023-03-03', 1, "Aaron Aaronson", "123 Place Circle", "111-111-1111", "Striped Bass", "Bass", 18, 10),
("BBTRN", "Bass Buddies", "Orlando", '2023-03-03', 1, "Aaron Aaronson", "123 Place Circle", "111-111-1111", "Largemouth Bass", "Bass", 19, 15),
("BBTRN", "Bass Buddies", "Orlando", '2023-03-03', 2, "Bob Bert", "321 Other Place", "222-222-2222", "Smallmouth Bass", "Bass", 15, 9),
("BBTRN", "Bass Buddies", "Orlando", '2023-03-03', 3, "Chris Candy", "555 Lane Ln", "555-555-5555", "Striped Bass", "Bass", 18, 11),
("PFENT", "Panfish Enthusiasts April '23", "Jacksonville", '2023-04-04', 1, "Aaron Aaronson", "123 Place Circle", "111-111-1111", "Bluegill", "Panfish", 9, 5),
("PFENT", "Panfish Enthusiasts April '23", "Jacksonville", '2023-04-04', 2, "Bob Bert", "321 Other Place", "222-222-2222", "Crappie", "Panfish", 8, 4),
("PFENT", "Panfish Enthusiasts April '23", "Jacksonville", '2023-04-04', 3, "Chris Candy", "555 Lane Ln", "555-555-5555", "Pumpkinseed", "Panfish", 8, 5),
("PFENT", "Panfish Enthusiasts April '23", "Jacksonville", '2023-04-04', 3, "Chris Candy", "555 Lane Ln", "555-555-5555", "Striped Bass", "Bass", 17, 11)
;



create table tournament(
t_id varchar(10) unique not null,
t_name	varchar(255) not null,
t_loc	varchar(255) not null,
t_date	date,
primary key (t_id)
);

create table angler(
a_id	integer AUTO_INCREMENT not null,
a_name	varchar(255) not null,
a_add	varchar(255) not null unique,
a_phone	varchar(255) not null,
primary key (a_id)
);


create table fish(
fish_species varchar(255) not null,
fish_class	varchar(255) not null,
primary key (fish_species)
);

create table newCatch(
catch_id integer not null auto_increment,
weight	integer not null,
length	integer not null,
fish_species varchar(255) not null,
primary key(catch_id),
foreign key (fish_species) references fish(fish_species)
);

insert into tournament
select distinct t_code ,t_name, t_loc, t_date from catch;

insert into angler
select distinct a_id, a_name, a_add, a_phone from catch;

insert into fish
select distinct fish_species, fish_class from catch;

insert into newCatch (weight, length, fish_species)
select distinct weight, length, fish_species from catch;
