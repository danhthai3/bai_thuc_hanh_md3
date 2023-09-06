
    create database staffquiz;
    use  staffquiz;
    create table staff(
                          id int primary key auto_increment,
                          name nvarchar(50) not null ,
                          email nvarchar(50) unique not null,
                          address nvarchar(100) ,
                          salary double,
                          department_id int,
                          foreign key (department_id) references department(id)
    );
    create table department(
                               id int primary key  auto_increment,
                               name_department nvarchar(50)
    );
