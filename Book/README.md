# BookCloud


DROP TABLE IF EXISTS books CASCADE;

-- Criação da tabela books
CREATE TABLE books (
    id SERIAL PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    author VARCHAR(255) NOT NULL,
    publisher VARCHAR(255),
    pages INT,
    category VARCHAR(100),
    lido BOOLEAN DEFAULT FALSE,
    quero_ler BOOLEAN DEFAULT FALSE,
    created_at TIMESTAMP WITH TIME ZONE DEFAULT NOW(),
    updated_at TIMESTAMP WITH TIME ZONE DEFAULT NOW()
);

INSERT INTO public.books (title, author, publisher, pages, category, lido, quero_ler)
VALUES
  ('Divergent', 'Veronica Roth', 'Rocco', 487, 'Distopia', FALSE, FALSE),
  ('Fourth Wing', 'Rebecca Yarros', 'Galera Record', 800, 'Fantasia/Romance', FALSE, FALSE),
  ('Divinos Rivais', 'Erin Watt', 'Editora Seguinte', 438, 'Young Adult/Romance', FALSE, FALSE),
  ('Powerless', 'Lauren Roberts', 'Rocco', 560, 'Fantasia/Romance',FALSE, FALSE);