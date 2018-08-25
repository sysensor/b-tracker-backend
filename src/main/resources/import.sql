 insert into user(uuid, name, address, phone, username, password, type, status) values('bf4cb437-4881-450b-b0fa-4cfe077ba541', 'Raju', 'Colombo', '0773005672','raju','$2a$10$5SM3OIksgYLL6LU8bb7Raeff2A1nwAuEsF.XoXQq6QxvJwRjh96Jq','bus owner', true);
 insert into user(uuid, name, address, phone, username, password, type, status) values('bf4cb437-4881-450b-b0fa-4cfe077ba542', 'Bandara', 'Gall', '0713005675','bandara','$2a$10$5SM3OIksgYLL6LU8bb7Raeff2A1nwAuEsF.XoXQq6QxvJwRjh96Jq','bus owner', true);
 insert into user(uuid, name, address, phone, username, password, type, status) values('bf4cb437-4881-450b-b0fa-4cfe077ba543', 'Selvam', 'Matara', '0793005675','selvam','$2a$10$5SM3OIksgYLL6LU8bb7Raeff2A1nwAuEsF.XoXQq6QxvJwRjh96Jq','bus owner', true);

 insert into bus_owner(uuid, user_uuid) values('bf4cb437-4881-450b-b0fa-4cfe077ba544','bf4cb437-4881-450b-b0fa-4cfe077ba541')
 insert into bus_owner(uuid, user_uuid) values('bf4cb437-4881-450b-b0fa-4cfe077ba545','bf4cb437-4881-450b-b0fa-4cfe077ba542')
 insert into bus_owner(uuid, user_uuid) values('bf4cb437-4881-450b-b0fa-4cfe077ba546','bf4cb437-4881-450b-b0fa-4cfe077ba543')

 insert into user(uuid, name, address, phone, username, password, type, status) values('bf4cb437-4881-450b-b0fa-4cfe077ba547', 'Sena', 'Piliyandala', '0773676240','sena','$2a$10$5SM3OIksgYLL6LU8bb7Raeff2A1nwAuEsF.XoXQq6QxvJwRjh96Jq','passenger', true);
 insert into user(uuid, name, address, phone, username, password, type, status) values('bf4cb437-4881-450b-b0fa-4cfe077ba548', 'Kusum', 'Panadura', '0702737000','kusum','$2a$10$5SM3OIksgYLL6LU8bb7Raeff2A1nwAuEsF.XoXQq6QxvJwRjh96Jq','passenger', true);
 insert into user(uuid, name, address, phone, username, password, type, status) values('bf4cb437-4881-450b-b0fa-4cfe077ba549', 'Thanuja', 'Kelaniya', '0702737004','thanuja','$2a$10$5SM3OIksgYLL6LU8bb7Raeff2A1nwAuEsF.XoXQq6QxvJwRjh96Jq','passenger', true);

 insert into passenger(uuid,user_uuid) values('bf4cb437-4881-450b-b0fa-4cfe077ba550','bf4cb437-4881-450b-b0fa-4cfe077ba547');
 insert into passenger(uuid,user_uuid) values('bf4cb437-4881-450b-b0fa-4cfe077ba551','bf4cb437-4881-450b-b0fa-4cfe077ba548');
 insert into passenger(uuid,user_uuid) values('bf4cb437-4881-450b-b0fa-4cfe077ba552','bf4cb437-4881-450b-b0fa-4cfe077ba549');

 insert into user(uuid, name, address, phone, username, password, type, status) values('bf4cb437-4881-450b-b0fa-4cfe077ba555', 'Yohan', 'Matale', '0702737004','yohan','$2a$10$5SM3OIksgYLL6LU8bb7Raeff2A1nwAuEsF.XoXQq6QxvJwRjh96Jq','time keeper', true);
 insert into user(uuid, name, address, phone, username, password, type, status) values('bf4cb437-4881-450b-b0fa-4cfe077ba556', 'Dinuka', 'Ames', '0702737004','dinuka','$2a$10$5SM3OIksgYLL6LU8bb7Raeff2A1nwAuEsF.XoXQq6QxvJwRjh96Jq','time keeper', true);
 insert into user(uuid, name, address, phone, username, password, type, status) values('bf4cb437-4881-450b-b0fa-4cfe077ba557', 'Damith', 'Piliyandala', '0702737004','damith','$2a$10$5SM3OIksgYLL6LU8bb7Raeff2A1nwAuEsF.XoXQq6QxvJwRjh96Jq','time keeper', true);

 insert into time_keeper(uuid,user_uuid) values('bf4cb437-4881-450b-b0fa-4cfe077ba558','bf4cb437-4881-450b-b0fa-4cfe077ba555');
 insert into time_keeper(uuid,user_uuid) values('bf4cb437-4881-450b-b0fa-4cfe077ba559','bf4cb437-4881-450b-b0fa-4cfe077ba556');
 insert into time_keeper(uuid,user_uuid) values('bf4cb437-4881-450b-b0fa-4cfe077ba560','bf4cb437-4881-450b-b0fa-4cfe077ba557');

 insert into bus(uuid, registration_no, bus_owner_uuid) values ('bf4cb437-4881-450b-b0fa-4cfe077b4008','EY3456','bf4cb437-4881-450b-b0fa-4cfe077ba546')
 insert into bus(uuid, registration_no, bus_owner_uuid) values ('bf4cb437-4881-450b-b0fa-4cfe077b4009','MK2345','bf4cb437-4881-450b-b0fa-4cfe077ba544')
 insert into bus(uuid, registration_no, bus_owner_uuid) values ('bf4cb437-4881-450b-b0fa-4cfe077b4010','DR5678','bf4cb437-4881-450b-b0fa-4cfe077ba544')

 insert into bus_route(uuid, name, start, destination) values ('bf4cb437-4881-450b-b0fa-4cfe077ba553', 'RT120', 'Piliyandala', 'Colombo')
 update bus set bus_route_uuid='bf4cb437-4881-450b-b0fa-4cfe077ba553' where uuid='bf4cb437-4881-450b-b0fa-4cfe077b4010'
 update time_keeper set bus_route_uuid='bf4cb437-4881-450b-b0fa-4cfe077ba553' where uuid='bf4cb437-4881-450b-b0fa-4cfe077ba558'

 insert into bus_route(uuid, name, start, destination) values ('bf4cb437-4881-450b-b0fa-4cfe077ba554', 'RT138', 'Pannipitiya', 'Colombo')
 update bus set bus_route_uuid='bf4cb437-4881-450b-b0fa-4cfe077ba554' where uuid='bf4cb437-4881-450b-b0fa-4cfe077b4009'
 update bus set bus_route_uuid='bf4cb437-4881-450b-b0fa-4cfe077ba554' where uuid='bf4cb437-4881-450b-b0fa-4cfe077b4008'
 update time_keeper set bus_route_uuid='bf4cb437-4881-450b-b0fa-4cfe077ba554' where uuid='bf4cb437-4881-450b-b0fa-4cfe077ba559'

 insert into ticket(uuid, price, start, destination, status, passenger_uuid) values ('77243dc3-d162-4aa4-bcb0-6eb741173a01',100.25,'Piliyandala','Colombo',true,'bf4cb437-4881-450b-b0fa-4cfe077ba550')
 insert into ticket(uuid, price, start, destination, status, passenger_uuid) values ('77243dc3-d162-4aa4-bcb0-6eb741173a02',200.25,'Colombo','Nugegoda',true, 'bf4cb437-4881-450b-b0fa-4cfe077ba551')