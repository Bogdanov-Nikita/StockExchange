--liquibase formatted sql

--changeset Nbogdanov:1
/*==============================================================*/
/* Table: profile                                               */
/*==============================================================*/
create table public.profile
(
    id                   uuid not null
        constraint pk_profile
            primary key,
    bank_id              uuid,
    name                 varchar(255),
    family_name          varchar(255),
    middle_name          varchar(255),
    email                varchar(255),
    phone                varchar(255),
    date_of_birth        date,
    place_of_birth       varchar(255),
    passport_number      varchar(255),
    registration_address varchar(255),
    actual_address       varchar(255)
);

comment on table public.profile is 'Учетная запись клиента';

comment on column public.profile.id is 'Идентификатор клиента';

comment on column public.profile.bank_id is 'Идентификатор клиента в банке';

comment on column public.profile.name is 'Имя';

comment on column public.profile.family_name is 'Фамилия';

comment on column public.profile.middle_name is 'Отчество';

comment on column public.profile.email is 'e-mail';

comment on column public.profile.phone is 'Телефон';

comment on column public.profile.date_of_birth is 'Дата рождения';

comment on column public.profile.place_of_birth is 'Место рождения';

comment on column public.profile.passport_number is 'Номер паспорта вместе с серией';

comment on column public.profile.registration_address is 'Адрес регистрации';

comment on column public.profile.actual_address is 'Адрес проживания';


/*==============================================================*/
/* Index: profile                                               */
/*==============================================================*/

create index profile_email_index
    on profile (email);

create index profile_family_name_index
    on profile (family_name);

create index profile_middle_name_index
    on profile (middle_name);

create index profile_name_index
    on profile (name);

create index profile_phone_index
    on profile (phone);

