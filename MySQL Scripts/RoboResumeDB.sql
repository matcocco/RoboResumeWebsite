create database `RoboResumeDB`;
use `RoboResumeDB`;

create table Person (
	PersonId int not null auto_increment,
    FullName varchar(255),
    Email varchar(200),
    primary key (PersonId)
);

create table Education (
	EducationId int not null auto_increment,
    Degree varchar (2000),
    PersonId int REFERENCES Person(PersonId),
    primary key(EducationId)
);

create table Experience (
	ExperienceId int not null auto_increment,
    Experience varchar(2000),
    PersonId int REFERENCES Person(PersonId),
    primary key(ExperienceId)
);

create table Skills (
	SkillId int not null auto_increment,
    Skill varchar (2000),
    PersonId int REFERENCES Person(PersonId),
    primary key (SkillId)
);
select * from Skills;
-- Selects all Skills columns from Skills table in RoboResumeDB