create database CrimeManagement;
use crimemanagement;

create table crime(
crimeid int primary key,
incidentType varchar(255),
incidentDate date,
Location varchar(255),
Description text,
status varchar(20)
);

create table victim(
victimid int primary key,
crimeid int,
name varchar(255),
contactinfo varchar(255),
injuries varchar(255),
foreign key(crimeid) references crime(crimeid)
);

create table suspect(
suspectid int primary key,
crimeid int,
name varchar(255),
description text,
criminalhistory text,
foreign key(crimeid) references crime(crimeid)
);

INSERT INTO Crime (CrimeID, IncidentType, IncidentDate, Location, Description, Status)
VALUES
(1, 'Robbery', '2023-09-15', '123 Main St, Cityville', 'Armed robbery at a convenience store', 'Open'),
(2, 'Homicide', '2023-09-20', '456 Elm St, Townsville', 'Investigation into a murder case', 'Under
Investigation'),
(3, 'Theft', '2023-09-10', '789 Oak St, Villagetown', 'Shoplifting incident at a mall', 'Closed');

INSERT INTO Victim (VictimID, CrimeID, Name, ContactInfo, Injuries)
VALUES
(1, 1, 'John Doe', 'johndoe@example.com', 'Minor injuries'),
(2, 2, 'Jane Smith', 'janesmith@example.com', 'Deceased'),
(3, 3, 'Alice Johnson', 'alicejohnson@example.com', 'None');

INSERT INTO Suspect (SuspectID, CrimeID, Name, Description, CriminalHistory)
VALUES
(1, 1, 'Robber 1', 'Armed and masked robber', 'Previous robbery convictions'),
(2, 2, 'Unknown', 'Investigation ongoing', NULL),
(3, 3, 'Suspect 1', 'Shoplifting suspect', 'Prior shoplifting arrests');

select * from crime where status='open';

Select count(*) as total_incident from crime;

select distinct incidentType from crime;

select * from crime where incidentDate between '2023-09-01' and '2023-09-10';

Alter table victim add Age int;
Alter table suspect add Age int;
UPDATE Victim SET Age = 35 WHERE VictimID = 1;
UPDATE Victim SET Age = 28 WHERE VictimID = 2;
UPDATE Victim SET Age = 40 WHERE VictimID = 3;
UPDATE Suspect SET Age = 45 WHERE SuspectID = 1;
UPDATE Suspect SET Age = 32 WHERE SuspectID = 2;
UPDATE Suspect SET Age = 38 WHERE SuspectID = 3;

select name,age,'victim' as role from victim
union
select name,age,'suspect' as role from suspect
order by age desc;

select avg(age) as Avg_Age from(
select age from victim
union all
select age from suspect) as AllPersons;

select incidentType,count(*) as IncidentCount from crime
where status='open'
group by incidentType;

select name from victim where name like '%Doe%'
union
select name from suspect where name like '%Doe%';

select v.name,c.Status from victim v join crime c on v.crimeid=c.crimeid
union
select s.name,c.Status from suspect s join crime c on s.crimeid=c.crimeid;

select distinct c.incidentType from crime c join victim v on c.crimeid=v.crimeid where v.age in(30,35)
union
select distinct c.incidentType from crime c join suspect s on c.crimeid=s.crimeid where s.age in(30,35);

select v.name from victim v join crime c on c.crimeid=v.crimeid where c.incidentType='Robbery'
union
select s.name from suspect s join crime c on s.crimeid=c.crimeid where c.incidentType='Robbery';

INSERT INTO Crime (CrimeID, IncidentType, IncidentDate, Location, Description, Status)
VALUES 
    (4, 'Robbery', '2023-09-25', '111 Pine St, Cityville', 'Bank robbery', 'Open'),
    (5, 'Robbery', '2023-09-26', '222 Maple St, Cityville', 'Jewelry store robbery', 'Open'),
    (6, 'Theft', '2023-09-27', '333 Cedar St, Townsville', 'Pickpocketing incident', 'Open'),
    (7, 'Theft', '2023-09-28', '444 Birch St, Townsville', 'Bicycle stolen', 'Open');

INSERT INTO Victim (VictimID, CrimeID, Name, ContactInfo, Injuries, Age)
VALUES 
    (4, 4, 'Michael Brown', 'michaelbrown@example.com', 'Gunshot wound', 40),
    (5, 5, 'Emily Davis', 'emilydavis@example.com', 'None', 30),
    (6, 6, 'Daniel Wilson', 'danielwilson@example.com', 'Minor bruises', 35),
    (7, 7, 'Sophia Martinez', 'sophiamartinez@example.com', 'None', 28);
    
    INSERT INTO Suspect (SuspectID, CrimeID, Name, Description, CriminalHistory, Age)
VALUES 
    (4, 4, 'Robber 2', 'Masked individual with a firearm', 'Previous burglary charges', 45),
    (5, 5, 'Robber 3', 'Suspect wearing black hoodie', 'Shoplifting history', 30),
    (6, 6, 'Thief 1', 'Pickpocketing suspect', 'Multiple theft convictions', 32),
    (7, 7, 'Thief 2', 'Caught on CCTV stealing a bike', 'No previous record', 29);


select incidentType from crime where status='open' group by incidentType
Having count(*)>1;

INSERT INTO Crime (CrimeID, IncidentType, IncidentDate, Location, Description, Status)
VALUES 
    (8, 'Assault', '2023-09-30', '555 Walnut St, Cityville', 'Physical altercation reported', 'Open'),
    (9, 'Fraud', '2023-10-01', '666 Pine St, Townsville', 'Financial scam investigation', 'Under Investigation');

INSERT INTO Victim (VictimID, CrimeID, Name, ContactInfo, Injuries, Age)
VALUES 
    (8, 8, 'Michael Brown', 'michaelbrown@example.com', 'Minor injuries', 40);
INSERT INTO Suspect (SuspectID, CrimeID, Name, Description, CriminalHistory, Age)
VALUES 
    (8, 9, 'Michael Brown', 'Involved in another case', 'Previous minor offenses', 40);

select distinct c.* from crime c
join suspect s on c.crimeid=s.crimeid
join victim v on s.name=v.name;

select c.*,v.name as Victim_Name,v.contactinfo,v.injuries,s.name as Suspect_Name,s.description,
s.criminalhistory
from crime c
left join victim v on c.crimeid=v.crimeid
left join suspect s on s.crimeid=c.crimeid;

select distinct c.*
from crime c join suspect s on c.crimeid=s.crimeid
join victim v on c.crimeid=v.crimeid where
s.age>(select min(age) from victim where crimeid=c.crimeid);

INSERT INTO Crime (CrimeID, IncidentType, IncidentDate, Location, Description, Status)
VALUES 
    (10, 'Robbery', '2024-01-10', '101 Market St, Cityville', 'Bank robbery', 'Open'),
    (11, 'Robbery', '2024-01-12', '102 Mall St, Townsville', 'Jewelry store robbery', 'Open'),
    (12, 'Theft', '2024-01-15', '103 Park Ave, Villagetown', 'Shoplifting', 'Closed'),
    (13, 'Theft', '2024-01-18', '104 Oak St, Townsville', 'Phone theft at metro station', 'Open');
INSERT INTO Victim (VictimID, CrimeID, Name, ContactInfo, Injuries, Age)
VALUES 
    (10, 10, 'Ethan Carter', 'ethan@example.com', 'None', 29),
    (11, 11, 'Sophia Green', 'sophia@example.com', 'Minor bruises', 35),
    (12, 12, 'Olivia Brown', 'olivia@example.com', 'None', 31),
    (13, 13, 'Daniel White', 'daniel@example.com', 'Fractured arm', 40);
INSERT INTO Suspect (SuspectID, CrimeID, Name, Description, CriminalHistory, Age)
VALUES 
    (10, 10, 'Robber X', 'Masked individual with a firearm', 'Previous burglary cases', 42),
    (11, 11, 'Robber X', 'Repeat offender', 'Multiple robbery charges', 42),  -- Same suspect in multiple cases
    (12, 12, 'Thief Y', 'Caught shoplifting at mall', 'Prior theft records', 37),
    (13, 13, 'Thief Y', 'Pickpocketing suspect', 'Repeat offenses', 37);  -- Same thief in multiple cases

select name,count(*) as Incident_Count from suspect
group by name
having count(*)>1;

select crimeid from crime where crimeid not in(select distinct crimeid from suspect);

select * from crime where incidentType='Homicide' or incidentType='Robbery';

select c.crimeid,c.incidentType,coalesce(s.name,'No Suspect') as suspect_name from crime c
left join suspect s on c.crimeid=s.crimeid;

select distinct s.* from suspect s join crime c on c.crimeid=s.crimeid 
where c.incidentType in('Robbery', 'Assault');
