## Primer Parcial Desarrollo de Software 
#### Germán Sari | 3K10

### Introduccion
Se solicito realizar un codigo en donde podamos comprobar, al ingresar un adn, si se trataba de un mutante o no.<br>
<br>
![image](https://github.com/user-attachments/assets/4f1ca14a-158f-47f7-9bde-c2c78cda618d)

Como parametro recibimos un array de Strings.

### Pasos a seguir: <br>

Podremos acceder al proyecto mediante el siguiente link: 
[Proyecto Mutantes](https://parcialmutantes-1o7z.onrender.com)<br>

Otra manera de acceder al proyecto es Decargar el zip, extraer, abrir con intelliJ y ejecutar el main, una vez que la consola devuelva el mensaje "Funcionando", ademas podemos utilizar tanto la base de datos H2, como tambien Postman.<br>

En Postman: <br>
POST https://parcialmutantes-1o7z.onrender.com/mutant <br> 
GET https://parcialmutantes-1o7z.onrender.com/stats <br>
<br>
De manera local:<br>
POST localhost8080/mutant <br>
GET localhost8080/stats <br>
<br>
### Ejemplos: <br>

Mutante en Filas: {
  "dna": ["AAAATG","TGCAGT","GCTTCC","CCCCTG","GTAGTC","AGTCAC"]
  }<br>

Mutante en Diagonal: { "dna": ["AGAATG","TACAGT","GCAGCC","TTGATG","GTAGTC","AGTCAA"]
        }; }<br>

No mutante: { "dna": ["AATATG","TGCAGT","GCTTCG","CGCCGG","GTAGTC","AGTGAC"] 
}<br>


