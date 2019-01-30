drop table AUTHOR_2_ARTICLE;
drop table AUTHOR;
drop table INSTITUTE;
drop table ARTICLE;

drop sequence INSTITUTE_SEQ;
drop sequence AUTHOR_SEQ;
drop sequence ARTICLE_SEQ;

create sequence INSTITUTE_SEQ;
create sequence AUTHOR_SEQ;
create sequence ARTICLE_SEQ;

create table INSTITUTE (
	INSTITUTE_ID integer default INSTITUTE_SEQ.nextval,
	INAME varchar(50),
	STREET varchar(50),
	CITY varchar(30),
	COUNTRY varchar(30),
	constraint INSTITUTE_PK primary key(INSTITUTE_ID)
);

create table AUTHOR (
	AUTHOR_ID integer default AUTHOR_SEQ.nextval,
	ANAME varchar(30),
	SURNAME varchar(50),
	INSTITUTE_ID integer not null references INSTITUTE,
	constraint AUTHOR_PK primary key(AUTHOR_ID)
);

create table ARTICLE (
	ARTICLE_ID integer default ARTICLE_SEQ.nextval,
	TITLE varchar(200),
	ISSUE integer,
	PAGE integer,
	PUBLISHED timestamp default current_timestamp,
	constraint ARTICLE_PK primary key(ARTICLE_ID)
);

create table AUTHOR_2_ARTICLE (
	AUTHOR_ID integer not null references AUTHOR,
	ARTICLE_ID integer not null references ARTICLE,
	constraint A2A_PK primary key(AUTHOR_ID, ARTICLE_ID)
);

insert into INSTITUTE(INAME, STREET, CITY, COUNTRY) values (
	'Vysoká škola chemicko-technologická', 'Technická 5', 'Praha', 'Czech Republic');
insert into INSTITUTE(INAME, STREET, CITY, COUNTRY) values (
	'Leibniz Universität Hannover', 'Welfengarten 1', 'Hannover', 'Germany');
insert into INSTITUTE(INAME, STREET, CITY, COUNTRY) values (
	'Universidad de Valladolid', 'Plaza de Santa Cruz 8', 'Valladolid', 'Spain');



insert into AUTHOR(ANAME, SURNAME, INSTITUTE_ID) values (
	'Štěpán', 'Ullmann', (select INSTITUTE_ID from INSTITUTE i where i.INAME = 'Vysoká škola chemicko-technologická'));
insert into AUTHOR(ANAME, SURNAME, INSTITUTE_ID) values (
	'Patrik', 'Krámský', (select INSTITUTE_ID from INSTITUTE i where i.INAME = 'Vysoká škola chemicko-technologická'));
insert into AUTHOR(ANAME, SURNAME, INSTITUTE_ID) values (
	'Jan', 'Klír', (select INSTITUTE_ID from INSTITUTE i where i.INAME = 'Vysoká škola chemicko-technologická'));
insert into AUTHOR(ANAME, SURNAME, INSTITUTE_ID) values (
	'Jens-Uwe', 'Grossmann', (select INSTITUTE_ID from INSTITUTE i where i.INAME = 'Leibniz Universität Hannover'));
insert into AUTHOR(ANAME, SURNAME, INSTITUTE_ID) values (
	'Michaela', 'Jäger', (select INSTITUTE_ID from INSTITUTE i where i.INAME = 'Leibniz Universität Hannover'));
insert into AUTHOR(ANAME, SURNAME, INSTITUTE_ID) values (
	'Dennis', 'Werner', (select INSTITUTE_ID from INSTITUTE i where i.INAME = 'Leibniz Universität Hannover'));
insert into AUTHOR(ANAME, SURNAME, INSTITUTE_ID) values (
	'José', 'Alvárez', (select INSTITUTE_ID from INSTITUTE i where i.INAME = 'Universidad de Valladolid'));



insert into ARTICLE(TITLE, ISSUE, PAGE, PUBLISHED) values (
	'2-20GHz FTMW spectrometer: construction and pilot experiments', 36, 785, '15.10.2018 00:00:00'
);
insert into ARTICLE(TITLE, ISSUE, PAGE, PUBLISHED) values (
	'Nuclear hyperfine structure of iodomethane and bromomethane', 35, 446, '15.1.2017 00:00:00'
);
insert into ARTICLE(TITLE, ISSUE, PAGE, PUBLISHED) values (
	'Newly built broadband chirped-pulse FTMW spectrometer', 35, 455, '15.1.2018 00:00:00'
);
insert into ARTICLE(TITLE, ISSUE, PAGE, PUBLISHED) values (
	'High resolution spectroscopy of D-Alanine', 36, 125, '15.1.2018 00:00:00'
);
insert into ARTICLE(TITLE, ISSUE, PAGE, PUBLISHED) values (
	'Rotational spectrum of indole in 2-10GHz spectral region', 35, 885, '15.1.2017 00:00:00'
);
insert into ARTICLE(TITLE, ISSUE, PAGE, PUBLISHED) values (
	'Quantitative analysis of bioactive molecules by microwave spectroscopy: Comparison with GC-MS and LC-MS', 36, 332, '15.1.2018 00:00:00'
);
insert into ARTICLE(TITLE, ISSUE, PAGE, PUBLISHED) values (
	'Rotational spectroscopy of FSO3 radical', 34, 515, '15.1.2016 00:00:00'
);



insert into AUTHOR_2_ARTICLE(AUTHOR_ID, ARTICLE_ID) 
	select AUTHOR_ID, ARTICLE_ID from AUTHOR au, ARTICLE ar
	where au.SURNAME = 'Klír' and ar.TITLE = '2-20GHz FTMW spectrometer: construction and pilot experiments';
insert into AUTHOR_2_ARTICLE(AUTHOR_ID, ARTICLE_ID) 
	select AUTHOR_ID, ARTICLE_ID from AUTHOR au, ARTICLE ar
	where au.SURNAME = 'Krámský' and ar.TITLE = '2-20GHz FTMW spectrometer: construction and pilot experiments';
insert into AUTHOR_2_ARTICLE(AUTHOR_ID, ARTICLE_ID) 
	select AUTHOR_ID, ARTICLE_ID from AUTHOR au, ARTICLE ar
	where au.SURNAME = 'Ullmann' and ar.TITLE = '2-20GHz FTMW spectrometer: construction and pilot experiments';
insert into AUTHOR_2_ARTICLE(AUTHOR_ID, ARTICLE_ID) 
	select AUTHOR_ID, ARTICLE_ID from AUTHOR au, ARTICLE ar
	where au.SURNAME = 'Grossmann' and ar.TITLE = '2-20GHz FTMW spectrometer: construction and pilot experiments';
	
insert into AUTHOR_2_ARTICLE(AUTHOR_ID, ARTICLE_ID) 
	select AUTHOR_ID, ARTICLE_ID from AUTHOR au, ARTICLE ar
	where au.SURNAME = 'Klír' and ar.TITLE = 'Nuclear hyperfine structure of iodomethane and bromomethane';
insert into AUTHOR_2_ARTICLE(AUTHOR_ID, ARTICLE_ID) 
	select AUTHOR_ID, ARTICLE_ID from AUTHOR au, ARTICLE ar
	where au.SURNAME = 'Ullmann' and ar.TITLE = 'Nuclear hyperfine structure of iodomethane and bromomethane';
	
insert into AUTHOR_2_ARTICLE(AUTHOR_ID, ARTICLE_ID) 
	select AUTHOR_ID, ARTICLE_ID from AUTHOR au, ARTICLE ar
	where au.SURNAME = 'Jäger' and ar.TITLE = 'Newly built broadband chirped-pulse FTMW spectrometer';
insert into AUTHOR_2_ARTICLE(AUTHOR_ID, ARTICLE_ID) 
	select AUTHOR_ID, ARTICLE_ID from AUTHOR au, ARTICLE ar
	where au.SURNAME = 'Grossmann' and ar.TITLE = 'Newly built broadband chirped-pulse FTMW spectrometer';
	
insert into AUTHOR_2_ARTICLE(AUTHOR_ID, ARTICLE_ID) 
	select AUTHOR_ID, ARTICLE_ID from AUTHOR au, ARTICLE ar
	where au.SURNAME = 'Werner' and ar.TITLE = 'High resolution spectroscopy of D-Alanine';
insert into AUTHOR_2_ARTICLE(AUTHOR_ID, ARTICLE_ID) 
	select AUTHOR_ID, ARTICLE_ID from AUTHOR au, ARTICLE ar
	where au.SURNAME = 'Grossmann' and ar.TITLE = 'High resolution spectroscopy of D-Alanine';
insert into AUTHOR_2_ARTICLE(AUTHOR_ID, ARTICLE_ID) 
	select AUTHOR_ID, ARTICLE_ID from AUTHOR au, ARTICLE ar
	where au.SURNAME = 'Alvárez' and ar.TITLE = 'High resolution spectroscopy of D-Alanine';

insert into AUTHOR_2_ARTICLE(AUTHOR_ID, ARTICLE_ID) 
	select AUTHOR_ID, ARTICLE_ID from AUTHOR au, ARTICLE ar
	where au.SURNAME = 'Werner' and ar.TITLE = 'Rotational spectrum of indole in 2-10GHz spectral region';
insert into AUTHOR_2_ARTICLE(AUTHOR_ID, ARTICLE_ID) 
	select AUTHOR_ID, ARTICLE_ID from AUTHOR au, ARTICLE ar
	where au.SURNAME = 'Jäger' and ar.TITLE = 'Rotational spectrum of indole in 2-10GHz spectral region';
insert into AUTHOR_2_ARTICLE(AUTHOR_ID, ARTICLE_ID) 
	select AUTHOR_ID, ARTICLE_ID from AUTHOR au, ARTICLE ar
	where au.SURNAME = 'Grossmann' and ar.TITLE = 'Rotational spectrum of indole in 2-10GHz spectral region';
	
insert into AUTHOR_2_ARTICLE(AUTHOR_ID, ARTICLE_ID) 
	select AUTHOR_ID, ARTICLE_ID from AUTHOR au, ARTICLE ar
	where au.SURNAME = 'Krámský' and ar.TITLE = 'Quantitative analysis of bioactive molecules by microwave spectroscopy: Comparison with GC-MS and LC-MS';
insert into AUTHOR_2_ARTICLE(AUTHOR_ID, ARTICLE_ID) 
	select AUTHOR_ID, ARTICLE_ID from AUTHOR au, ARTICLE ar
	where au.SURNAME = 'Ullmann' and ar.TITLE = 'Quantitative analysis of bioactive molecules by microwave spectroscopy: Comparison with GC-MS and LC-MS';
	
insert into AUTHOR_2_ARTICLE(AUTHOR_ID, ARTICLE_ID) 
	select AUTHOR_ID, ARTICLE_ID from AUTHOR au, ARTICLE ar
	where au.SURNAME = 'Klír' and ar.TITLE = 'Rotational spectroscopy of FSO3 radical';
insert into AUTHOR_2_ARTICLE(AUTHOR_ID, ARTICLE_ID) 
	select AUTHOR_ID, ARTICLE_ID from AUTHOR au, ARTICLE ar
	where au.SURNAME = 'Krámský' and ar.TITLE = 'Rotational spectroscopy of FSO3 radical';
insert into AUTHOR_2_ARTICLE(AUTHOR_ID, ARTICLE_ID) 
	select AUTHOR_ID, ARTICLE_ID from AUTHOR au, ARTICLE ar
	where au.SURNAME = 'Ullmann' and ar.TITLE = 'Rotational spectroscopy of FSO3 radical';

