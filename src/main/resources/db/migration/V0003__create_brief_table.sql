create table brief_between_tags
(
    id                       int auto_increment,
    project_name             varchar(150) null,
    tag_source               varchar(150) null,
    tag_target               varchar(150) null,
    refactoring_between_tags int          null,
    commit_between_tags      int          null,
    errors_between_tags      int          null,
    constraint brief_between_tags_pk
        primary key (id)
);

