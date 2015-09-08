insert into attendee (id, firstname, lastname, address, city, state, zipcode, phonenumber, emailaddress) values (5001, 'John', 'Smith', '123 Main St', 'Akron', 'OH', '44321', '330-123-4567', 'jsmith@gopivotal.com');
insert into attendee (id, firstname, lastname, address, city, state, zipcode, phonenumber, emailaddress) values (5002, 'Sally', 'Struthers', '321 Oak St', 'Akron', 'OH', '44321', '614-123-4567', 'sstruthers@gopivotal.com');
insert into attendee (id, firstname, lastname, address, city, state, zipcode, phonenumber, emailaddress) values (5003, 'Jane', 'Doe', '4444 High St', 'Columbus', 'OH', '43333', '330-123-4567', 'jdoe@gopivotal.com');

insert into session (id, attendee_id, name, date, completed) values (100, 5002, 'Session 1', '2013-12-01', false);
insert into session (id, attendee_id, name, date, completed) values (101, 5002, 'Session 2', '2013-12-01', true);

