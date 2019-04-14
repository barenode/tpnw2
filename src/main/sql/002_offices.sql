update employee set id_office = null;

delete from order_table;
delete from contract;
delete from company;
delete from individual;
delete from client;
delete from drives;
delete from car;
delete from office;
delete from employee;

insert into office (city) values ('Jièín');
insert into office (city) values ('Nová Paka');
insert into office (city) values ('Bìlohrad');

insert into employee (firstname, lastname, id_office) select 'Kermit', 'Žába', id_office from office where city = 'Nová Paka';
insert into employee (firstname, lastname, id_office) select 'Miss', 'Prasátko', id_office from office where city = 'Jièín';
insert into employee (firstname, lastname, id_office) select 'Fozzie', 'Medvìd', id_office from office where city = 'Jièín';
insert into employee (firstname, lastname, id_office) select 'Rowlf', 'Pes', id_office from office where city = 'Jièín'; 
insert into employee (firstname, lastname, id_office) select 'Rizzo', 'Krysa', id_office from office where city = 'Nová Paka';
insert into employee (firstname, lastname, id_office) select 'Bunsen', 'Melasa', id_office from office where city = 'Nová Paka';
insert into employee (firstname, lastname, id_office) select 'Pepe', 'Kreveta', id_office from office where city = 'Bìlohrad';  
insert into employee (firstname, lastname, id_office) select 'Sam', 'Orel', id_office from office where city = 'Bìlohrad';

update office set id_manager = (select id_employee from employee where lastname = 'Prasátko') where city = 'Jièín';
update office set id_manager = (select id_employee from employee where lastname = 'Krysa') where city = 'Nová Paka';
update office set id_manager = (select id_employee from employee where lastname = 'Kreveta') where city = 'Bìlohrad';

insert into car (numberplate, id_owner) select 'SHADY-JI1', id_employee from employee where lastname = 'Prasátko';
insert into car (numberplate, id_owner) select 'SHADY-JI2', id_employee from employee where lastname = 'Prasátko';
insert into car (numberplate, id_owner) select 'SHADY-JI3', id_employee from employee where lastname = 'Medvìd';
insert into car (numberplate, id_owner) select 'SHADY-JI4', id_employee from employee where lastname = 'Pes';

insert into car (numberplate, id_owner) select 'SHADY-NP1', id_employee from employee where lastname = 'Krysa';
insert into car (numberplate, id_owner) select 'SHADY-NP2', id_employee from employee where lastname = 'Krysa';
insert into car (numberplate, id_owner) select 'SHADY-NP3', id_employee from employee where lastname = 'Krysa';

insert into car (numberplate, id_owner) select 'SHADY-BE1', id_employee from employee where lastname = 'Kreveta';
insert into car (numberplate, id_owner) select 'SHADY-BE2', id_employee from employee where lastname = 'Kreveta';
insert into car (numberplate, id_owner) select 'SHADY-BE3', id_employee from employee where lastname = 'Orel';

insert into drives (id_employee, id_car) values (
    (select id_employee from employee where lastname = 'Prasátko'),
    (select id_car from car where numberplate = 'SHADY-JI1'));
insert into drives (id_employee, id_car) values (
    (select id_employee from employee where lastname = 'Prasátko'),
    (select id_car from car where numberplate = 'SHADY-JI2'));
insert into drives (id_employee, id_car) values (
    (select id_employee from employee where lastname = 'Prasátko'),
    (select id_car from car where numberplate = 'SHADY-JI3'));
insert into drives (id_employee, id_car) values (
    (select id_employee from employee where lastname = 'Prasátko'),
    (select id_car from car where numberplate = 'SHADY-JI4'));
insert into drives (id_employee, id_car) values (
    (select id_employee from employee where lastname = 'Medvìd'),
    (select id_car from car where numberplate = 'SHADY-JI1'));
insert into drives (id_employee, id_car) values (
    (select id_employee from employee where lastname = 'Medvìd'),
    (select id_car from car where numberplate = 'SHADY-JI2'));
insert into drives (id_employee, id_car) values (
    (select id_employee from employee where lastname = 'Medvìd'),
    (select id_car from car where numberplate = 'SHADY-JI3'));
insert into drives (id_employee, id_car) values (
    (select id_employee from employee where lastname = 'Medvìd'),
    (select id_car from car where numberplate = 'SHADY-JI4'));
insert into drives (id_employee, id_car) values (
    (select id_employee from employee where lastname = 'Pes'),
    (select id_car from car where numberplate = 'SHADY-JI1'));
insert into drives (id_employee, id_car) values (
    (select id_employee from employee where lastname = 'Pes'),
    (select id_car from car where numberplate = 'SHADY-JI2'));
insert into drives (id_employee, id_car) values (
    (select id_employee from employee where lastname = 'Pes'),
    (select id_car from car where numberplate = 'SHADY-JI3'));
insert into drives (id_employee, id_car) values (
    (select id_employee from employee where lastname = 'Pes'),
    (select id_car from car where numberplate = 'SHADY-JI4'));     
insert into drives (id_employee, id_car) values (
    (select id_employee from employee where lastname = 'Krysa'),
    (select id_car from car where numberplate = 'SHADY-NP1'));
insert into drives (id_employee, id_car) values (
    (select id_employee from employee where lastname = 'Krysa'),
    (select id_car from car where numberplate = 'SHADY-NP2'));
insert into drives (id_employee, id_car) values (
    (select id_employee from employee where lastname = 'Krysa'),
    (select id_car from car where numberplate = 'SHADY-NP3'));
insert into drives (id_employee, id_car) values (
    (select id_employee from employee where lastname = 'Melasa'),
    (select id_car from car where numberplate = 'SHADY-NP1'));
insert into drives (id_employee, id_car) values (
    (select id_employee from employee where lastname = 'Melasa'),
    (select id_car from car where numberplate = 'SHADY-NP2'));
insert into drives (id_employee, id_car) values (
    (select id_employee from employee where lastname = 'Melasa'),
    (select id_car from car where numberplate = 'SHADY-NP3'));      
insert into drives (id_employee, id_car) values (
    (select id_employee from employee where lastname = 'Kreveta'),
    (select id_car from car where numberplate = 'SHADY-BE1'));
insert into drives (id_employee, id_car) values (
    (select id_employee from employee where lastname = 'Kreveta'),
    (select id_car from car where numberplate = 'SHADY-BE2'));
insert into drives (id_employee, id_car) values (
    (select id_employee from employee where lastname = 'Kreveta'),
    (select id_car from car where numberplate = 'SHADY-BE3'));
insert into drives (id_employee, id_car) values (
    (select id_employee from employee where lastname = 'Orel'),
    (select id_car from car where numberplate = 'SHADY-BE1'));
insert into drives (id_employee, id_car) values (
    (select id_employee from employee where lastname = 'Orel'),
    (select id_car from car where numberplate = 'SHADY-BE2'));
insert into drives (id_employee, id_car) values (
    (select id_employee from employee where lastname = 'Orel'),
    (select id_car from car where numberplate = 'SHADY-BE3'));


insert into client (phone) values ('(822) 644-9041');
insert into client (phone) values ('(498) 972-1714');
insert into client (phone) values ('(471) 450-6696');
insert into client (phone) values ('(206) 221-6969');
insert into client (phone) values ('(860) 332-3471');
insert into client (phone) values ('(544) 842-3452');
insert into client (phone) values ('(375) 660-9730');
insert into client (phone) values ('(830) 428-7602');
insert into client (phone) values ('(626) 990-0453');
insert into client (phone) values ('(527) 290-9884');
insert into client (phone) values ('(370) 929-9963');
insert into client (phone) values ('(562) 484-3214');
insert into client (phone) values ('(666) 987-9833');
insert into client (phone) values ('(823) 691-7983');
insert into client (phone) values ('(639) 362-3883');
insert into client (phone) values ('(741) 819-3869');
insert into client (phone) values ('(508) 521-1183');
insert into client (phone) values ('(889) 684-7250');
insert into client (phone) values ('(754) 286-2367');
insert into client (phone) values ('(322) 461-9246');
insert into client (phone) values ('(348) 284-5456');
insert into client (phone) values ('(954) 263-7989');
insert into client (phone) values ('(340) 226-6456');
insert into client (phone) values ('(385) 227-0918');
insert into client (phone) values ('(981) 960-6271');
insert into client (phone) values ('(763) 606-9174');
insert into client (phone) values ('(870) 629-4773');
insert into client (phone) values ('(355) 934-8542');
insert into client (phone) values ('(596) 703-3688');
insert into client (phone) values ('(690) 608-0035');
insert into client (phone) values ('(738) 581-4270');
insert into client (phone) values ('(555) 283-1007');
insert into client (phone) values ('(971) 335-9076');
insert into client (phone) values ('(984) 718-4657');



insert into individual (firstname, lastname, id_client) select 'Danuše', 'Cermak', id_client from client where phone ='(822) 644-9041'; 
insert into individual (firstname, lastname, id_client) select 'Stanislav', 'Blažek', id_client from client where phone ='(498) 972-1714'; 
insert into individual (firstname, lastname, id_client) select 'Jakub', 'Slovák', id_client from client where phone ='(471) 450-6696'; 
insert into individual (firstname, lastname, id_client) select 'Andrea', 'Stanek', id_client from client where phone ='(206) 221-6969'; 
insert into individual (firstname, lastname, id_client) select 'Tibor', 'Horáèek', id_client from client where phone ='(860) 332-3471'; 
insert into individual (firstname, lastname, id_client) select 'David', 'Hlaváè', id_client from client where phone ='(544) 842-3452'; 
insert into individual (firstname, lastname, id_client) select 'Valérie', 'Kozel', id_client from client where phone ='(375) 660-9730'; 
insert into individual (firstname, lastname, id_client) select 'Ivan', 'Nováèek', id_client from client where phone ='(830) 428-7602'; 
insert into individual (firstname, lastname, id_client) select 'Marcel', 'Jahoda', id_client from client where phone ='(626) 990-0453'; 
insert into individual (firstname, lastname, id_client) select 'Petra', 'Skála', id_client from client where phone ='(527) 290-9884';  
insert into individual (firstname, lastname, id_client) select 'Ivona', 'Èervená', id_client from client where phone ='(370) 929-9963'; 
insert into individual (firstname, lastname, id_client) select 'Erik', 'Vlasák', id_client from client where phone ='(562) 484-3214'; 
insert into individual (firstname, lastname, id_client) select 'Laura', 'Nováková', id_client from client where phone ='(666) 987-9833'; 
insert into individual (firstname, lastname, id_client) select 'Ignác', 'Koláø', id_client from client where phone ='(823) 691-7983'; 
insert into individual (firstname, lastname, id_client) select 'František', 'Novosad', id_client from client where phone ='(639) 362-3883'; 
insert into individual (firstname, lastname, id_client) select 'Michal', 'Šimek', id_client from client where phone ='(741) 819-3869'; 
insert into individual (firstname, lastname, id_client) select 'Tomáš', 'Ryba', id_client from client where phone ='(508) 521-1183'; 
insert into individual (firstname, lastname, id_client) select 'Ivanka', 'Horníková', id_client from client where phone ='(889) 684-7250';  
insert into individual (firstname, lastname, id_client) select 'Radoslava', 'Jedlièka', id_client from client where phone ='(754) 286-2367'; 
insert into individual (firstname, lastname, id_client) select 'Emílie', 'Luksková', id_client from client where phone ='(322) 461-9246'; 
insert into individual (firstname, lastname, id_client) select 'Ludmila', 'Hrabìtová', id_client from client where phone ='(348) 284-5456'; 
insert into individual (firstname, lastname, id_client) select 'Aleš', 'Hrubý', id_client from client where phone ='(954) 263-7989'; 
insert into individual (firstname, lastname, id_client) select 'Jakub', 'Kudrna', id_client from client where phone ='(340) 226-6456'; 

insert into company (name, id_client) select 'Rhodico', id_client from client where phone ='(385) 227-0918'; 
insert into company (name, id_client) select 'Platico', id_client from client where phone ='(981) 960-6271'; 
insert into company (name, id_client) select 'Goldico', id_client from client where phone ='(763) 606-9174'; 
insert into company (name, id_client) select 'Palladico', id_client from client where phone ='(870) 629-4773'; 
insert into company (name, id_client) select 'Iridico', id_client from client where phone ='(355) 934-8542'; 
insert into company (name, id_client) select 'Osmico', id_client from client where phone ='(596) 703-3688'; 	
insert into company (name, id_client) select 'Rhenico', id_client from client where phone ='(690) 608-0035'; 	
insert into company (name, id_client) select 'Ruthenico', id_client from client where phone ='(738) 581-4270'; 
insert into company (name, id_client) select 'Germanico', id_client from client where phone ='(555) 283-1007'; 		
insert into company (name, id_client) select 'Beryllico', id_client from client where phone ='(971) 335-9076'; 
insert into company (name, id_client) select 'Silverco', id_client from client where phone ='(984) 718-4657'; 

insert into contract (orders_allowed, price_per_km_in_czk, id_client) select 66, 12, id_client from company where name ='Rhodico';  
insert into contract (orders_allowed, price_per_km_in_czk, id_client) select 66, 22, id_client from company where name ='Platico'; 
insert into contract (orders_allowed, price_per_km_in_czk, id_client) select 66, 17, id_client from company where name ='Goldico'; 
insert into contract (orders_allowed, price_per_km_in_czk, id_client) select 66, 9, id_client from company where name ='Palladico'; 
insert into contract (orders_allowed, price_per_km_in_czk, id_client) select 66, 32, id_client from company where name ='Iridico'; 
insert into contract (orders_allowed, price_per_km_in_czk, id_client) select 66, 77, id_client from company where name ='Osmico'; 
insert into contract (orders_allowed, price_per_km_in_czk, id_client) select 66, 41, id_client from company where name ='Rhenico'; 
insert into contract (orders_allowed, price_per_km_in_czk, id_client) select 66, 5, id_client from company where name ='Ruthenico'; 
insert into contract (orders_allowed, price_per_km_in_czk, id_client) select 66, 43, id_client from company where name ='Germanico'; 
insert into contract (orders_allowed, price_per_km_in_czk, id_client) select 66, 27, id_client from company where name ='Beryllico'; 
insert into contract (orders_allowed, price_per_km_in_czk, id_client) select 66, 11, id_client from company where name ='Silverco'; 
