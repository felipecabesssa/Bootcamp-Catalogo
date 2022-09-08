CREATE TABLE empregados (
  cpf VARCHAR(15) PRIMARY KEY,
  enome CHARACTER VARYING (255),
  salary FLOAT,
  cpf_supervisor VARCHAR(15),
  dnumero NUMERIC
);

CREATE TABLE departamentos (
  dnumero NUMERIC PRIMARY KEY,
  dnome CHARACTER VARYING (60),
  cpf_gerente VARCHAR(15) REFERENCES empregados (cpf)
);

alter table departamentos add foreign key(cpf_gerente) references empregados (cpf);

CREATE TABLE trabalha (
  cpf_emp VARCHAR(15) REFERENCES empregados(cpf),
  pnumero numeric
);

alter table trabalha add foreign key(cpf_emp) references empregados(cpf);

CREATE TABLE projetos (
  pnumero NUMERIC PRIMARY KEY,
  pnome VARCHAR(45),
  dnumero NUMERIC REFERENCES departamentos (dnumero)
);

alter table projetos add foreign key(dnumero) references departamentos(dnumero);

INSERT INTO empregados(cpf, enome, salary, cpf_supervisor, dnumero)
VALUES 
      ('049382234322', 'João Silva', 2350, '2434332222', 1010),
      ('586733922290', 'Mario Silveira', 3500, '2434332222', 1010),
      ('2434332222', 'Aline Barros', 2350, (null), 1010),
      ('1733332162', 'Tulio Vidal', 8350, (null), 1020),
      ('4244435272', 'Juliana Rodrigues', 3310, (null), 1020),
      ('1014332672', 'Natalia Marques', 2900, (null), 1010);

INSERT INTO departamentos(dnumero, dnome, cpf_gerente)
VALUES
      (1010, 'Pesquisa', '049382234322'),
      (1020, 'Ensino', '2434332222');

INSERT INTO trabalha (cpf_emp, pnumero)
VALUES 
  ('049382234322', 2010),
  ('586733922290', 2020),
  ('049382234322', 2020);

INSERT INTO projetos (pnumero, pnome, dnumero)
VALUES 
  (2010, 'Alpha', 1010),
  (2020, 'Beta', 1020);
  
-- ----------------------------------------------------- --

SELECT empregados.cpf, empregados.enome, departamentos.dnome 
FROM empregados
INNER JOIN departamentos ON empregados.dnumero = departamentos.dnumero

-- -------- Funcionarios que TRABALHAM em algum projeto --------- --

SELECT empregados.cpf, empregados.enome, departamentos.dnome 
FROM empregados
INNER JOIN departamentos ON empregados.dnumero = departamentos.dnumero
INNER JOIN trabalha ON trabalha.cpf_emp = empregados.cpf
INNER JOIN projetos ON trabalha.pnumero  = projetos.pnumero
 
 
-- -------- Funcionarios que NÃO TRABALHAM em algum projeto --------- -- 
 
SELECT empregados.cpf, empregados.enome, departamentos.dnome 
FROM empregados
INNER JOIN departamentos ON empregados.dnumero = departamentos.dnumero
WHERE empregados.cpf NOT IN (
	SELECT empregados.cpf 
	FROM empregados
	INNER JOIN trabalha ON trabalha.cpf_emp = empregados.cpf
) 
ORDER BY empregados.cpf
 
-- --------------- Solução alternativa com LEFT JOIN ----------------- --

SELECT empregados.cpf, empregados.enome, departamentos.dnome, trabalha.* 
FROM empregados
INNER JOIN departamentos ON empregados.dnumero = departamentos.dnumero
LEFT JOIN trabalha ON trabalha.cpf_emp = empregados.cpf
WHERE trabalha.cpf_emp IS NULL
ORDER BY empregados.cpf
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
