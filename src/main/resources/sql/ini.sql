create table start_table(
      id int,
      name String
);

insert into start_table (id, name) values ('1', 'Alex');

select * from start_table;

select (id, name) from start_table;

select (id, name) from start_table where name = 'Alex' and id = '1';