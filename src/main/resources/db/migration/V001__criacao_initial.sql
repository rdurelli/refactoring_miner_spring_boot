create table refactorings
(
    id                  bigint auto_increment,
    name                varchar(150) null,
    refactoring_type    varchar(200) null,
    json_representation mediumtext   null,
    project             varchar(200) null,
    project_with_tag    varchar(200) null,
    project_url         varchar(500) null,
    constraint refactorings_pk
        primary key (id)
);