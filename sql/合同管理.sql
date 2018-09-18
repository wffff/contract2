CREATE TABLE `t_attachment` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`id`),
  KEY `id` (`id`),
  contract_id                  INTEGER not null  ,
  type integer ,
  content                  varchar(500)   ,
  url                  varchar(500)   ,
  del                           BOOLEAN        NOT NULL DEFAULT FALSE,
  last                         datetime             DEFAULT NULL,
  time                          datetime    NOT NULL DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB AUTO_INCREMENT=0 DEFAULT CHARSET=utf8;
CREATE TABLE `t_contract` (
 `id` int(11) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`id`),
  KEY `id` (`id`),
  content                   varchar(500)  ,
  company_name        varchar(500)     ,
  contact_id                        VARCHAR(500),
  phone                VARCHAR(500),
  fax                 VARCHAR(500),
  saleman_id                       VARCHAR(500),
  amount                        numeric (22,2),
    del                           BOOLEAN        NOT NULL DEFAULT FALSE,
	last                         datetime         DEFAULT NULL,
	time                         datetime  NOT NULL DEFAULT CURRENT_TIMESTAMP

)ENGINE=InnoDB AUTO_INCREMENT=0 DEFAULT CHARSET=utf8;
CREATE TABLE `t_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`id`),
  KEY `id` (`id`),
  role varchar (500),
  username varchar (500),
  password                  varchar(500)   ,
  fullname                  varchar(500)   ,
   del                           BOOLEAN        NOT NULL DEFAULT FALSE,
	last                        datetime          DEFAULT NULL,
	time                          datetime   NOT NULL DEFAULT CURRENT_TIMESTAMP
)ENGINE=InnoDB AUTO_INCREMENT=0 DEFAULT CHARSET=utf8;



DROP TABLE IF EXISTS t_role CASCADE;
create table `t_role`
(
   `id` int(11) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`id`),
  KEY `id` (`id`),
  name     varchar(50),
  description    varchar(50),
  del                           BOOLEAN        NOT NULL DEFAULT FALSE,
  last                          datetime           DEFAULT NULL,
  time                          datetime   NOT NULL DEFAULT CURRENT_TIMESTAMP
)ENGINE=InnoDB AUTO_INCREMENT=0 DEFAULT CHARSET=utf8;

--------用户角色表
DROP TABLE IF EXISTS t_user_role CASCADE;
create table `t_user_role`
(
  user_id        INTEGER NOT NULL ,
  role_id        INTEGER NOT NULL
)ENGINE=InnoDB AUTO_INCREMENT=0 DEFAULT CHARSET=utf8;


--------资源表

DROP TABLE IF EXISTS t_permission CASCADE;
create table `t_permission`
(
 `id` int(11) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`id`),
  KEY `id` (`id`),
  name     varchar(50),
  description    varchar(50),
  del                           BOOLEAN        NOT NULL DEFAULT FALSE,
  last                          datetime           DEFAULT NULL,
  time                          datetime   NOT NULL DEFAULT CURRENT_TIMESTAMP
)ENGINE=InnoDB AUTO_INCREMENT=0 DEFAULT CHARSET=utf8;

--------用户资源表
DROP TABLE IF EXISTS t_role_permission CASCADE;
create table `t_role_permission`
(
  role_id        INTEGER NOT NULL ,
  permission_id        INTEGER NOT NULL
)ENGINE=InnoDB AUTO_INCREMENT=0 DEFAULT CHARSET=utf8;

