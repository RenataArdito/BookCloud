# BookCloud


-- Elimina tabelas caso já existam (ordem inversa de dependência)
DROP TABLE IF EXISTS fato_books CASCADE;
DROP TABLE IF EXISTS books CASCADE;
DROP TABLE IF EXISTS dim_category CASCADE;
DROP TABLE IF EXISTS dim_publisher CASCADE;
DROP TABLE IF EXISTS dim_author CASCADE;

-- --------------------------------------------------------------------
-- 1) Dimensão Author
-- --------------------------------------------------------------------
CREATE TABLE dim_author (
    author_id   SERIAL PRIMARY KEY,
    author_name VARCHAR(255) NOT NULL UNIQUE
);

-- --------------------------------------------------------------------
-- 2) Dimensão Publisher
-- --------------------------------------------------------------------
CREATE TABLE dim_publisher (
    publisher_id   SERIAL PRIMARY KEY,
    publisher_name VARCHAR(255) NOT NULL UNIQUE
);

-- --------------------------------------------------------------------
-- 3) Dimensão Category
-- --------------------------------------------------------------------
CREATE TABLE dim_category (
    category_id   SERIAL PRIMARY KEY,
    category_name VARCHAR(100) NOT NULL UNIQUE
);

-- --------------------------------------------------------------------
-- 4) Tabela books (fatos atômicos sobre cada livro) – tabela simples para CRUD
-- --------------------------------------------------------------------
CREATE TABLE books (
    id          SERIAL PRIMARY KEY,
    title       VARCHAR(255) NOT NULL,
    author      VARCHAR(255),
    publisher   VARCHAR(255),
    pages       INT,
    category    VARCHAR(100),
    lido        BOOLEAN    DEFAULT FALSE,
    quero_ler   BOOLEAN    DEFAULT FALSE,
    created_at  TIMESTAMP WITH TIME ZONE DEFAULT NOW(),
    updated_at  TIMESTAMP WITH TIME ZONE DEFAULT NOW()
);

-- --------------------------------------------------------------------
-- 5) Tabela fato_books (visão desnormalizada para relatórios)
--    faz join das dimensões pelos nomes textuais
-- --------------------------------------------------------------------
CREATE TABLE fato_books AS
SELECT
    b.id            AS num_livro,
    b.title         AS titulo,
    a.author_id     AS cod_autor,
    a.author_name   AS nom_autor,
    p.publisher_id  AS cod_editora,
    p.publisher_name AS nom_editora,
    b.pages         AS qtd_paginas,
    c.category_id   AS cod_categoria,
    c.category_name AS nom_categoria,
    b.lido,
    b.quero_ler,
    CASE
      WHEN b.lido THEN 'Lido'
      WHEN b.quero_ler THEN 'Quero Ler'
      ELSE '-'
    END AS status,
    b.created_at,
    b.updated_at
FROM books b
LEFT JOIN dim_author    a ON a.author_name    = b.author
LEFT JOIN dim_publisher p ON p.publisher_name = b.publisher
LEFT JOIN dim_category  c ON c.category_name  = b.category
WITH NO DATA;

ALTER TABLE fato_books
  ADD CONSTRAINT fato_books_pk PRIMARY KEY (num_livro);

-- --------------------------------------------------------------------
-- INSERTS DE EXEMPLO NAS DIMENSÕES
-- --------------------------------------------------------------------
INSERT INTO dim_author(author_name)
  VALUES
    ('Veronica Roth'),
    ('Rebecca Yarros'),
    ('Erin Watt'),
    ('Lauren Roberts')
ON CONFLICT DO NOTHING;

INSERT INTO dim_publisher(publisher_name)
  VALUES
    ('Rocco'),
    ('Galera Record'),
    ('Editora Seguinte')
ON CONFLICT DO NOTHING;

INSERT INTO dim_category(category_name)
  VALUES
    ('Distopia'),
    ('Fantasia/Romance'),
    ('Young Adult/Romance')
ON CONFLICT DO NOTHING;

-- --------------------------------------------------------------------
-- INSERTS DE EXEMPLO NA TAB. books
-- --------------------------------------------------------------------
INSERT INTO books (title, author, publisher, pages, category, lido, quero_ler)
VALUES
  ('Divergent',       'Veronica Roth',  'Rocco',            487, 'Distopia',          FALSE, FALSE),
  ('Fourth Wing',     'Rebecca Yarros', 'Galera Record',    800, 'Fantasia/Romance',  FALSE, FALSE),
  ('Divinos Rivais',  'Erin Watt',      'Editora Seguinte', 438, 'Young Adult/Romance',FALSE, FALSE),
  ('Powerless',       'Lauren Roberts', 'Rocco',            560, 'Distopia',          FALSE, FALSE)
ON CONFLICT DO NOTHING;

-- --------------------------------------------------------------------
-- Popula fato_books com base nos dados atuais
-- --------------------------------------------------------------------
TRUNCATE TABLE fato_books;
INSERT INTO fato_books
SELECT
    b.id,
    b.title,
    a.author_id,
    a.author_name,
    p.publisher_id,
    p.publisher_name,
    b.pages,
    c.category_id,
    c.category_name,
    b.lido,
    b.quero_ler,
    CASE
      WHEN b.lido THEN 'Lido'
      WHEN b.quero_ler THEN 'Quero Ler'
      ELSE '-'
    END,
    b.created_at,
    b.updated_at
FROM books b
LEFT JOIN dim_author    a ON a.author_name    = b.author
LEFT JOIN dim_publisher p ON p.publisher_name = b.publisher
LEFT JOIN dim_category  c ON c.category_name  = b.category;
