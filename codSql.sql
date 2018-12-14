create database dbescola;

use dbescola;

create table aluno(
	nome varchar(100), 
    matricula int, 
    cpf long,
    endereco varchar(100),
    telefone int,
    turma varchar(100),
    primary key(matricula)
);


create table professor(
	nome varchar(100),
    matricula int,
    cpf int,
    disciplina varchar(100),
    telefone int,
    remuneracao double,
    primary key(matricula)

);


create table turma(
	nome varchar(100),
    idTurma int,
    sala varchar(100),
    turno varchar(100),
    primary key(idTurma)
);