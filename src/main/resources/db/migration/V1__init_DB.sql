drop table if exists overlay cascade;
create table overlay
(
    id       bigint not null,
    switcher boolean,
    primary key (id)
);

drop table if exists font_link cascade;
create table font_link
(
    id              bigint not null,
    font_address     varchar(255),
    font_name      varchar(255),
    color      varchar(255),
    primary key (id)
);

drop table if exists users cascade;
create table users
(
    id              bigint  not null,
    username        varchar(255) not null,
    password        varchar(255) not null,
    role            varchar(50),
    is_chosen_map   boolean,
    primary key (id)
);

drop table if exists players cascade;
create table players
(
    id              bigint  not null,
    nickname        varchar(255) not null,
    time            varchar(255) not null,
    primary key (id)
);

drop table if exists map_pictures cascade;
create sequence seq_users start with 1 increment by 1;

create table map_pictures
(
    id                          bigint not null,
    address_map_picture         varchar(255),
    name_map                    varchar(255),
    is_selected                 boolean,
    name_of_player_who_chose    varchar(255),
    primary key (id)
);
create sequence seq_players start with 1 increment by 1;
create sequence seq_map_pictures start with 1 increment by 1;
