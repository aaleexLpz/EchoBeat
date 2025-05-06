drop database if exists echoBeat;

create database echoBeat;

use echoBeat;

create table usuario(
	idUsuario int auto_increment,
    nombreUsuario varchar(20) unique not null,
    clave varchar(30) not null,
    tipoUsuario enum('ADMIN', 'CLIENTE'),
    deBaja boolean default false,
    nombre varchar(60),
    email varchar(40),
    fechaNac date,
    constraint PK_USUARIO primary key(idUsuario)
);

create table interprete(
	nombre varchar(30) not null,
    fechaNac date,
    comentario text,
    constraint PK_INTERPRETE primary key(nombre)
);

create table album(
	idAlbum int auto_increment,
    nombre varchar(50) not null,
    fechaPublicacion date,
    comentario text,
    interprete varchar(30),
    constraint PK_ALBUM primary key(idAlbum),
    constraint FK_ALBUM_INTERPRETE foreign key(interprete)
		references interprete(nombre)
        on delete cascade
        on update cascade
);

create table cancion(
	idCancion int auto_increment,
    titulo varchar(50) not null, 
    cancion varchar(60) not null,
    duracion int,
    genero varchar(20),
    anho year,
    publica boolean default false,
    idUsuario int, 
    idAlbum int,
    constraint PK_CANCION primary key(idCancion),
    constraint FK_CANCION_USUARIO foreign key(idUsuario)
		references usuario(idUsuario)
        on delete cascade
        on update cascade,
	constraint FK_CANCION_ALBUM foreign key (idAlbum)
		references album(idAlbum)
        on delete cascade
        on update cascade
);

create table cantar(
	idCantar int auto_increment,
	nombre varchar(30),
    idCancion int,
    constraint PK_CANTAR primary key(idCantar),
    constraint FK_CANTAR_INTERPRETE foreign key(nombre)
		references interprete(nombre)
        on delete cascade
        on update cascade,
	constraint FK_CANTAR_CANCION foreign key(idCancion)
		references cancion(idCancion)
        on delete cascade
        on update cascade
);

create table playlist(
	idPlaylist int auto_increment,
    nombre varchar(25),
    portada varchar(60),
    publica boolean default false,
    idUsuario int,
    constraint PK_PLAYLIST primary key(idPlaylist),
    constraint FK_PLAYLIST_USUARIO foreign key(idUsuario)
		references usuario(idUsuario)
        on delete cascade
        on update cascade
);

create table playlistCancion(
	idPlayListCancion int auto_increment,
    idPlayList int,
    idCancion int,
    posicion int,
    constraint PK_PLAYLISTCANCION primary key(idPlayListCancion),
    constraint FK_PLAYLISTCANCION_PLAYLIST foreign key(idPlaylist)
		references playlist(idPlaylist)
        on delete cascade
        on update cascade,
	constraint FK_PLAYLISTCANCION_CANCION foreign key(idCancion)
		references cancion(idCancion)
        on delete cascade
        on update cascade,
	constraint POSICION_UNICA unique(idPlayList, posicion)
);
    
create table seguidor(
	idUsuario int,
    idPlayList int,
    constraint PK_SEGUIDOR primary key(idUsuario, idPlayList),
    constraint FK_SEGUIDOR_PLAYLIST foreign key(idPlayList)
		references playList(idPlayList)
        on delete cascade
        on update cascade,
	constraint FK_SEGUIDOR_USUARIO foreign key(idUsuario)
		references usuario(idUsuario)
        on delete cascade
        on update cascade 
);    
    

    
    
    
    
    
    