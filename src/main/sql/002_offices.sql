delete from office;
delete from employee;
delete from car;

insert into office (city) values ('Praha');
insert into office (city) values ('Hradec Kr�lov�');
insert into office (city) values ('Ji��n');
insert into office (city) values ('Nov� Paka');
insert into office (city) values ('Jilemnice');
insert into office (city) values ('B�lohrad');
insert into office (city) values ('Ho�ice');

insert into employee (firstname, lastname) values ('Kermit', '��ba'); 
insert into employee (firstname, lastname) values ('Miss', 'Pras�tko'); 
insert into employee (firstname, lastname) values ('Fozzie', 'Medv�d'); 
insert into employee (firstname, lastname) values ('Rowlf', 'Pes'); 
insert into employee (firstname, lastname) values ('Rizzo', 'Krysa'); 
insert into employee (firstname, lastname) values ('Pepe', 'Kreveta');
insert into employee (firstname, lastname) values ('Bunsen', 'Melasa'); 
insert into employee (firstname, lastname) values ('Sam', 'Orel');

insert into car (numberplate, id_owner) select 'SHADY-001', id_employee from employee where lastname = '��ba';
insert into car (numberplate, id_owner) select 'SHADY-002', id_employee from employee where lastname = 'Krysa';
insert into car (numberplate, id_owner) select 'SHADY-003', id_employee from employee where lastname = 'Melasa';
insert into car (numberplate, id_owner) select 'SHADY-004', id_employee from employee where lastname = 'Orel';