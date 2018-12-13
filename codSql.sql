create database dbescola;

use dbescola;

drop table aluno;

create table aluno(
	nome varchar(100), 
    matricula int, 
    cpf int,
    endereco varchar(100),
    telefone int,
    turma varchar(100),
    primary key(matricula)
);

alter table aluno 
modify column cpf long;


create table professor(
	nome varchar(100),
    matricula int,
    cpf int,
    disciplina varchar(100),
    telefone int,
    remuneracao double,
    primary key(matricula)

);


create table funcionario(
	nome varchar(100),
    idFunc int, 
    cpf long, 
    endereco varchar(100),
    telefone int,
    remuneracao double,
    tipo varchar(100),
    primary key(idFunc)
);

create table turma(
	nome varchar(100),
    idTurma int,
    sala varchar(100),
    turno varchar(100),
    primary key(idTurma)
);