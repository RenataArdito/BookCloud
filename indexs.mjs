import http from 'node:http';

export const handler = async (event) => {
  const url = 'http://54.90.208.80:8080/home';

  return new Promise((resolve, reject) => {
    http.get(url, (res) => {
      let data = '';

      res.on('data', chunk => {
        data += chunk;
      });

      res.on('end', () => {
        // Conta apenas os cards com botão "Apagar"
        const livros = data.match(/<button[^>]*>Apagar<\/button>/g) || [];

        resolve({
          statusCode: 200,
          headers: { "Content-Type": "application/json" },
          body: JSON.stringify({ quantidade_livros: livros.length })
        });
      });

    }).on('error', err => {
      reject({
        statusCode: 500,
        body: JSON.stringify({ error: err.message })
      });
    });
  });
};
