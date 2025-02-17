# Chatop-app - Backend

Ce projet est le backend de l'application Chatop, une plateforme de location entre particuliers. Il est développé en **Java Spring Boot** et utilise **JWT pour l'authentification**, **Cloudinary pour le stockage des images**, et **MySQL comme base de données**.

## Prérequis
Avant de commencer, assurez-vous d'avoir installé :
- **Java 17**
- **Maven**
- **MySQL**
- **Git**

## Installation et Configuration

### 1️⃣ Cloner le projet
```sh
git clone https://github.com/samiraAKO17/Chatop-app.git
cd Chatop-app
```

### 2️⃣ Configurer les variables d'environnement
Créez un fichier `.env` à la racine du projet et ajoutez :
```env
JWT_SECRET=votre_clé_secrète
CLOUDINARY_CLOUD_NAME=votre_cloud_name
CLOUDINARY_API_KEY=votre_api_key
CLOUDINARY_API_SECRET=votre_api_secret
SPRING_DATASOURCE_URL=jdbc:mysql://localhost:3306/chatopdb
SPRING_DATASOURCE_USERNAME=root
SPRING_DATASOURCE_PASSWORD=
```
⚠️ Remplacez les valeurs par vos propres informations.

### 3️⃣ Installer les dépendances
```sh
mvn clean install
```

### 4️⃣ Démarrer le serveur
```sh
mvn spring-boot:run
```
L'API sera accessible sur `http://localhost:3001`

##  Documentation API
Une fois l'application lancée, ouvrez **Swagger UI** pour explorer l'API :
```
http://localhost:3001/swagger-ui/index.html
```

## 🖥️ Frontend associé
Le frontend du projet est disponible ici :
👉 [OpenClassrooms Chatop Frontend](https://github.com/OpenClassrooms-Student-Center/Developpez-le-back-end-en-utilisant-Java-et-Spring)

## 📜 License
Ce projet est sous licence MIT.

---
🚀 Bon développement !

