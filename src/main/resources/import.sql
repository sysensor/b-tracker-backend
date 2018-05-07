insert into bus_owner(uuid, name, address, phone, username, password) values('bf4cb437-4881-450b-b0fa-4cfe077ba541', 'Raju', 'Colombo', '0773005672','raju','$2a$10$5SM3OIksgYLL6LU8bb7Raeff2A1nwAuEsF.XoXQq6QxvJwRjh96Jq');
insert into bus_owner(uuid, name, address, phone, username, password) values('bf4cb437-4881-450b-b0fa-4cfe077ba542', 'Bandara', 'Gall', '0713005675','bandara','$2a$10$5SM3OIksgYLL6LU8bb7Raeff2A1nwAuEsF.XoXQq6QxvJwRjh96Jq');
insert into bus_owner(uuid, name, address, phone, username, password) values('bf4cb437-4881-450b-b0fa-4cfe077ba543', 'Selvam', 'Matara', '0793005675','selvam','$2a$10$5SM3OIksgYLL6LU8bb7Raeff2A1nwAuEsF.XoXQq6QxvJwRjh96Jq');

insert into bus(uuid, registration_no, bus_owner_uuid) values ('bf4cb437-4881-450b-b0fa-4cfe077b4008','EY3456','bf4cb437-4881-450b-b0fa-4cfe077ba543')
insert into bus(uuid, registration_no, bus_owner_uuid) values ('bf4cb437-4881-450b-b0fa-4cfe077b4009','MK2345','bf4cb437-4881-450b-b0fa-4cfe077ba541')
insert into bus(uuid, registration_no, bus_owner_uuid) values ('bf4cb437-4881-450b-b0fa-4cfe077b4010','DR5678','bf4cb437-4881-450b-b0fa-4cfe077ba541')

insert into bus_route(uuid, name, start, destination) values ('bf4cb437-4881-450b-b0fa-4cfe077ba547', 'RT120', 'Piliyandala', 'Colombo')
update bus set bus_route_uuid='bf4cb437-4881-450b-b0fa-4cfe077ba547' where uuid='bf4cb437-4881-450b-b0fa-4cfe077b4010'

insert into bus_route(uuid, name, start, destination) values ('bf4cb437-4881-450b-b0fa-4cfe077ba548', 'RT138', 'Pannipitiya', 'Colombo')
update bus set bus_route_uuid='bf4cb437-4881-450b-b0fa-4cfe077ba548' where uuid='bf4cb437-4881-450b-b0fa-4cfe077b4009'
update bus set bus_route_uuid='bf4cb437-4881-450b-b0fa-4cfe077ba548' where uuid='bf4cb437-4881-450b-b0fa-4cfe077b4008'