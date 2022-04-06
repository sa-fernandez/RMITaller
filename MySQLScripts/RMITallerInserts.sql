use test;

insert into Estudiante values ('10029327', 'siuuuUuwuU');
insert into Estudiante values ('10087700', 'e0asjc8sa');
insert into Estudiante values ('10092887', 'flexin090');
insert into Estudiante values ('10027210', 'larosalia');
insert into Estudiante values ('10077721', 'bichomidios');

insert into Profesor values ('990231', 'socratesXD');
insert into Profesor values ('990103', 'fibonacciA');
insert into Profesor values ('990237', 'cristoRey');
insert into Profesor values ('990832', 'beyonce:v');

insert into Asignatura values ('Filosofia', '990231');
insert into Asignatura values ('Calculo Vectorial', '990103');
insert into Asignatura values ('Base de Datos', '990237');
insert into Asignatura values ('Comunicaciones y Redes', '990832');

insert into EstudianteXAsignatura values ('10087700', 'Filosofia');
insert into EstudianteXAsignatura values ('10087700', 'Calculo Vectorial');
insert into EstudianteXAsignatura values ('10087700', 'Base de datos');
insert into EstudianteXAsignatura values ('10087700', 'Comunicaciones y Redes');
insert into EstudianteXAsignatura values ('10029327', 'Filosofia');
insert into EstudianteXAsignatura values ('10029327', 'Calculo Vectorial');
insert into EstudianteXAsignatura values ('10029327', 'Base de datos');
insert into EstudianteXAsignatura values ('10029327', 'Comunicaciones y Redes');
insert into EstudianteXAsignatura values ('10092887', 'Filosofia');
insert into EstudianteXAsignatura values ('10092887', 'Calculo Vectorial');
insert into EstudianteXAsignatura values ('10092887', 'Base de datos');
insert into EstudianteXAsignatura values ('10092887', 'Comunicaciones y Redes');
insert into EstudianteXAsignatura values ('10027210', 'Filosofia');
insert into EstudianteXAsignatura values ('10027210', 'Calculo Vectorial');
insert into EstudianteXAsignatura values ('10027210', 'Base de datos');
insert into EstudianteXAsignatura values ('10027210', 'Comunicaciones y Redes');
insert into EstudianteXAsignatura values ('10077721', 'Filosofia');
insert into EstudianteXAsignatura values ('10077721', 'Calculo Vectorial');
insert into EstudianteXAsignatura values ('10077721', 'Base de datos');
insert into EstudianteXAsignatura values ('10077721', 'Comunicaciones y Redes');

select * from EstudianteXAsignatura;

insert into Nota values('Parcial1', 4.6, '10077721', 'Calculo Vectorial');
insert into Nota values('Parcial2', 4.4, '10077721', 'Calculo Vectorial');
insert into Nota values('Parcial1', 3.8, '10077721', 'Base de datos');

select * from Nota;