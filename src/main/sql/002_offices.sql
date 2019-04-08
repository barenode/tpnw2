delete from office;
delete from employee;
delete from car;

insert into office (city) values ('Praha');
insert into office (city) values ('Hradec Králové');
insert into office (city) values ('Jièín');
insert into office (city) values ('Nová Paka');
insert into office (city) values ('Jilemnice');
insert into office (city) values ('Bìlohrad');
insert into office (city) values ('Hoøice');

insert into employee (firstname, lastname) values ('Kermit', 'Žába'); 
insert into employee (firstname, lastname) values ('Miss', 'Prasátko'); 
insert into employee (firstname, lastname) values ('Fozzie', 'Medvìd'); 
insert into employee (firstname, lastname) values ('Rowlf', 'Pes'); 
insert into employee (firstname, lastname) values ('Rizzo', 'Krysa'); 
insert into employee (firstname, lastname) values ('Pepe', 'Kreveta');
insert into employee (firstname, lastname) values ('Bunsen', 'Melasa'); 
insert into employee (firstname, lastname) values ('Sam', 'Orel');

insert into car (numberplate, id_owner) select 'SHADY-001', id_employee from employee where lastname = 'Žába';
insert into car (numberplate, id_owner) select 'SHADY-002', id_employee from employee where lastname = 'Krysa';
insert into car (numberplate, id_owner) select 'SHADY-003', id_employee from employee where lastname = 'Melasa';
insert into car (numberplate, id_owner) select 'SHADY-004', id_employee from employee where lastname = 'Orel';