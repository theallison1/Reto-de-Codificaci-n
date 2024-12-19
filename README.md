Autenticación y pruebas de la aplicación
1. Obtener el token JWT
Antes de realizar las pruebas, debes obtener un token de autenticación. Para ello, puedes utilizar el siguiente comando curl con el usuario "admin":

Método: POST
URL: /auth/v1/login
Descripción: Obtiene un token JWT para realizar las solicitudes autenticadas.

Ejemplo de solicitud con el usuario "admin":

bash
Copiar código
curl -X POST http://localhost:8080/auth/v1/login \
-H "Content-Type: application/json" \
-d '{"username": "admin", "password": "admin"}'
Desglose de la solicitud:

-X POST: Indica que estamos realizando una solicitud POST.
http://localhost:8080/auth/v1/login: Es la URL del endpoint donde se realiza la autenticación.
-H "Content-Type: application/json": Define el tipo de contenido de la solicitud, en este caso, application/json para enviar datos en formato JSON.
-d '{"username": "admin", "password": "admin123"}': Los datos en formato JSON que contienen las credenciales del usuario "admin".
Respuesta esperada (si el login es exitoso):

json
Copiar código
{
  "token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VyX3N1Y2t5b3U6YWRtaW4iOiJ7YWRtaW5vc3lkZXJfbmFtZX0i..."
}
2. Obtener todos los candidatos
Método: GET
URL: /candidates
Descripción: Obtiene todos los candidatos registrados en la base de datos.

Ejemplo de solicitud con Bearer Token:

bash
Copiar código
curl -X GET http://localhost:8080/candidates \
-H "Authorization: Bearer {tu_token_jwt}"
Respuesta esperada:

json
Copiar código
[
  {
    "id": 1,
    "name": "Juan Pérez",
    "email": "juan@example.com"
  },
  {
    "id": 2,
    "name": "Ana García",
    "email": "ana@example.com"
  }
]
3. Obtener un candidato por ID
Método: GET
URL: /candidates/{id}
Descripción: Obtiene los detalles de un candidato específico por su ID.

Ejemplo de solicitud con Bearer Token:

bash
Copiar código
curl -X GET http://localhost:8080/candidates/1 \
-H "Authorization: Bearer {tu_token_jwt}"
Respuesta esperada:

Si el candidato existe:

json
Copiar código
{
  "id": 1,
  "name": "Juan Pérez",
  "email": "juan@example.com"
}
Si el candidato no existe:

json
Copiar código
{
  "message": "Candidate not found"
}
4. Crear un nuevo candidato
Método: POST
URL: /candidates
Descripción: Crea un nuevo candidato. Se espera que se envíen los datos del candidato en el cuerpo de la solicitud en formato JSON.

Ejemplo de solicitud con Bearer Token:

bash
Copiar código
curl -X POST http://localhost:8080/candidates \
-H "Content-Type: application/json" \
-H "Authorization: Bearer {tu_token_jwt}" \
-d '{"name": "Pedro González", "email": "pedro@example.com"}'
Respuesta esperada:

Si el candidato se crea correctamente:

json
Copiar código
{
  "id": 3,
  "name": "Pedro González",
  "email": "pedro@example.com"
}
5. Actualizar un candidato existente
Método: PUT
URL: /candidates/{id}
Descripción: Actualiza la información de un candidato existente. Se deben enviar los datos actualizados en el cuerpo de la solicitud en formato JSON.

Ejemplo de solicitud con Bearer Token:

bash
Copiar código
curl -X PUT http://localhost:8080/candidates/1 \
-H "Content-Type: application/json" \
-H "Authorization: Bearer {tu_token_jwt}" \
-d '{"name": "Juan Pérez", "email": "juan_updated@example.com"}'
Respuesta esperada:

Si el candidato se actualiza correctamente:

json
Copiar código
{
  "id": 1,
  "name": "Juan Pérez",
  "email": "juan_updated@example.com"
}
Si el candidato no existe:

json
Copiar código
{
  "message": "Candidate not found"
}
6. Eliminar un candidato
Método: DELETE
URL: /candidates/{id}
Descripción: Elimina un candidato por su ID.

Ejemplo de solicitud con Bearer Token:

bash
Copiar código
curl -X DELETE http://localhost:8080/candidates/1 \
-H "Authorization: Bearer {tu_token_jwt}"
Respuesta esperada:

Si la eliminación es exitosa:

json
Copiar código
{}
Si el candidato no existe:

json
Copiar código
{
  "message": "Candidate not found"
}
Resumen de los endpoints con Bearer Token
Método	URL	Descripción	Autenticación
GET	/candidates	Obtiene todos los candidatos	Bearer {tu_token_jwt}
GET	/candidates/{id}	Obtiene un candidato por su ID	Bearer {tu_token_jwt}
POST	/candidates	Crea un nuevo candidato	Bearer {tu_token_jwt}
PUT	/candidates/{id}	Actualiza un candidato existente	Bearer {tu_token_jwt}
DELETE	/candidates/{id}	Elimina un candidato	Bearer {tu_token_jwt}
