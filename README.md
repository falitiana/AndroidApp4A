# AndroidApp4A
Cette application concerne l'univers de la série américaine Rick et Morty. Elle a pour but d'afficher les personnages présents dans la série après avoir donner un login et un mot de passe

Pour cela, j'ai d'abord commencé par apprendre à utiliser un Recyclerview qui permet d'afficher les données dans une liste. Les données que je voulais afficher sont les personnages: ces personnages ont un nom, un ID, un statut spécifiant s'ils sont morts ou vifs, l'espèce à laquelle ils appartiennent (Humain, alien), une origine (Terre ou univers parallèle...), une location (la derninère à laquelle ils ont été vus) une image, un genre (mâle, femelle), la liste des épisodes dans laquelles ils appartiennent.

Toutes ces données sont sur un serveur, il a donc fallu faire appel au serveur. Tout d'abord j'ai défini l'API ainsi que le Retrofit adapter. Mon objet de retour est une pagination des réponses de l'API: Info (la longueur de la réponse, le nombre de pages, lien pour la page précédente/suivante) et le résultat ( le personnage avec les attributs cités auparavant). J'ai créé une interface Java pour faire un "GET" afin de recevoir la réponses du serveur. J'en ai donc fait 3 afin d'obtenir les personnages, les épisodes ainsi que "location".

Principes utilisés dans cette application:

Recyclerview
Appel serveur
Stockage de données
Architecture MVVM
Injection de dépendences
Clean Architecture
Room

L'application n'est pas fonctionnelle à cause de problèmes lié à mon pc, et des erreurs que je n'arrive pas à corriger 
