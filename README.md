Voici un exemple de `README.md` amélioré avec des emojis pour rendre le document plus visuellement attractif :

```markdown
# 📊 Calculatrice Backend

Bienvenue dans la partie backend de notre application de calculatrice ! Ce projet, développé en Java avec Spring Boot, permet de réaliser des opérations mathématiques via une API REST.

## 🚀 Fonctionnalités

- ➕ **Opérations de base** : Addition, soustraction, multiplication, division.
- 📐 **Fonctions trigonométriques** : Sinus, cosinus, tangente.
- 📈 **Fonctions exponentielles et logarithmiques** : Exponentielle, logarithme.
- 🧮 **Prise en charge de plusieurs opérandes** : Calculs sur plusieurs nombres.

## 🛠️ Prérequis

- ☕ **Java 21** : Assurez-vous que Java 21 est installé sur votre machine.
- 📦 **Maven** : Pour gérer les dépendances et les builds.
- 🌱 **Spring Boot** : Framework utilisé pour développer l'application.

## 📥 Installation

1. Clonez le dépôt du backend :

   ```bash
   git clone https://github.com/votre-utilisateur/calculatrice-backend.git
   ```

2. Accédez au répertoire du projet :

   ```bash
   cd calculatrice-backend
   ```

3. Compilez et lancez le projet avec Maven :

   ```bash
   mvn clean install
   mvn spring-boot:run
   ```

## 🧑‍💻 Utilisation

### 🔗 Endpoints de l'API

L'application expose une API REST pour effectuer des calculs. Voici les principaux endpoints disponibles :

- **POST `/api/calculate`** : Effectue un calcul basé sur les données fournies.

### 📋 Exemple de requête

Pour effectuer une addition :

```bash
curl -X POST http://localhost:8080/api/calculate \
  -H "Content-Type: application/json" \
  -d '{
    "type": "addition",
    "operands": [1.5, 2.5, 3.5]
  }'
```

### 📝 Structure de la requête

- **type** : Type d'opération (`addition`, `soustraction`, `multiplication`, `division`, `sin`, `cos`, `tan`, `exp`, `log`).
- **operands** : Un tableau de nombres sur lesquels l'opération sera effectuée.

### 📊 Réponse

La réponse est un nombre représentant le résultat de l'opération demandée.

## 🧪 Tests

Les tests unitaires sont intégrés dans le projet. Pour les exécuter, utilisez la commande :

```bash
mvn test
```

## 🤝 Contribuer

Les contributions sont les bienvenues ! Suivez les étapes ci-dessous pour participer :

1. 🍴 Forkez le projet.
2. 🌿 Créez une nouvelle branche (`git checkout -b feature/nouvelle-fonctionnalite`).
3. 💻 Commitez vos changements (`git commit -m 'Ajout d'une nouvelle fonctionnalité'`).
4. ⬆️ Poussez sur la branche (`git push origin feature/nouvelle-fonctionnalite`).
5. 📬 Ouvrez une Pull Request.

## 📜 License

Ce projet est sous licence MIT - voir le fichier [LICENSE](LICENSE) pour plus de détails.

---

🎉 Merci d'avoir choisi notre projet de calculatrice ! Nous espérons que vous apprécierez l'utiliser autant que nous avons apprécié le développer.
```