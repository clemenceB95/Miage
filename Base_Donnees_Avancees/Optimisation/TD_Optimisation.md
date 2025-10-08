# TD Optimisation Algébrique

## Schéma Relationnel

On considère le schéma relationnel suivant :

**ÉTUDIANT**(Et_Id, Et_Nom, Et_Pren, Et_DateNais, Et_LieuNais)  
**ENSEIGNANT**(En_Id, En_Nom, En_Pren, En_DateNais, En_LieuNais)  
**UE**(UE_Id, UE_Libelle, UE_CM, UE_TD, UE_NbGrp, En_Id)  
**ENS_TD**(UE_Id, En_Id, ETD_Grp)  
**INSCRIPTION**(Et_Id, UE_Id, I_Grp, I_NoteF)

- Les clés primaires sont en **gras**.
- Les clés étrangères sont **soulignées**.

> UE signifie « Unité d’Enseignement ».  
> Tous les attributs sont des chaînes de caractères, sauf :
> - `UE_CM`, `UE_TD`, `UE_NbGrp`, `ETD_Grp` et `I_Grp` : entiers.

---

## Rappel Exercice 1 : Représentation Entité/Association

### Schéma Entité/Association (E/A)

![Schéma E/A](Asset/Img/Rappel_Exercice1.png.jpg)

### Description des entités et associations

1. **Entités** :
    - **ÉTUDIANT**: Et_Id, Et_Nom, Et_Pren, Et_DateNais, Et_LieuNais
    - **ENSEIGNANT**: En_Id, En_Nom, En_Pren, En_DateNais, En_LieuNais
    - **UE** : UE_Id, UE_Libelle, UE_CM, UE_TD, UE_NbGrp, En_Id

2. **Associations** :
    - **INSCRIPTION** entre ÉTUDIANT et UE : relie un étudiant à l’UE où il est inscrit (attributs : I_Grp, I_NoteF)
    - **ENS_TD** entre ENSEIGNANT et UE : relie un enseignant à un groupe de TD (attribut : ETD_Grp)
    - L’enseignant principal de l’UE est indiqué par l’attribut `En_Id` d'**UE**.

---

## Modèle Logique de Données (MLD)

![Schéma MLD](Asset/Img/Rappel_Exercice1_MLD.png)

### Remarques

- Le MLD montre le schéma relationnel transformé depuis le modèle E/A.
- Les relations deviennent des tables avec leurs clés primaires et étrangères.
- Chaque association avec attributs devient une table à part (ex. INSCRIPTION et ENS_TD).  

## Rappel Exercice 2

### Contexte

Donnez la forme SQL et la forme algébrique des requêtes suivantes (sous forme linéaire et arborescente) :

a. Quels sont les noms des étudiants qui ne suivent aucune UE ?  
b. Quels sont les noms des enseignants qui enseignent la même matière en TD et en Cours ?

### Symboles utilisés

Pour les formes algébriques relationnelles, on utilise les symboles suivants :

- π = projection
- σ = sélection
- ⨝ = jointure naturelle
- ∪ = union
- ∩ = intersection
- − = différence


### Forme SQL (Version NOT EXISTS)

```sql

SELECT Et_Nom
FROM ETUDIANT e
WHERE NOT EXISTS (
    SELECT *
    FROM INSCRIPTION i
    WHERE e.Et_Id = i.Et_Id
);

```
### Forme SQL (version NOT IN)

```sql

SELECT Et_Nom
FROM ÉTUDIANT
WHERE Et_Id NOT IN (
    SELECT Et_Id
    FROM INSCRIPTION
);

```

### Requête SQL – Étudiants non inscrits (version avec `MINUS` dans `IN`)

```sql
SELECT Et_Id, Et_Nom
FROM Etudiant
WHERE Et_Id IN (
    SELECT Et_Id
    FROM Etudiant
    MINUS
    SELECT Et_Id
    FROM Inscription
);

```
Les requêtes ont été testées sur la plateforme en ligne https://www.db-fiddle.com

### Forme algébrique linéaire

π Et_Nom ( Etudiant ⨝ ( π_Et_Id(Etudiant) − π_Et_Id(Inscription) ) )

### Forme algébrique arborescente

#### a) Étudiants qui ne suivent aucune UE

           π(EtNom)
               │
               ⨝
           ┌───┴────────────┐
           │                │
        ÉTUDIANT          ( − )
                       ┌────┴─────┐
                    π(EtId)      π(EtId)
                 (ÉTUDIANT)   (INSCRIPTION)

## Rappel Exercice 2

b. Quels sont les noms des enseignants qui enseignent la même matière en TD et en Cours ?

### Forme SQL

### Requête SQL – Enseignants en TD et Cours (version avec jointure classique)
```sql

SELECT e.En_Nom
FROM ENSEIGNANT e, ENS_TD E, UE u
WHERE u.En_Id = e.En_Id
  AND u.UE_Id = E.UE_Id
  AND E.En_Id = e.En_Id;

```

### Requête SQL – Enseignants en TD et Cours (version avec table temporaire T et INTERSECT)
```sql

SELECT e.En_Nom
FROM Enseignant e,
(SELECT En_Id, UE_Id
FROM ENS_TD
INTERSECT
SELECT En_Id, UE_Id
FROM UE) T
WHERE T.En_Id = e.En_Id;

```

> **Remarque :**  
> Dans la deuxième requête, la sous-requête `(SELECT… INTERSECT ...) T` crée une table temporaire virtuelle T, contenant les enseignants ayant la même UE en TD et en cours.  
> On relie ensuite T à la table ENSEIGNANT pour récupérer les noms (`En_Nom`).


### Forme algébrique arborescente

### Forme algébrique arborescente – Version avec intersection (∩)

           π(En_Nom)
               │
               ⨝
        ┌──────┴──────┐
        │             │
    ENSEIGNANT      ( ∩ )
                  ┌───┴───┐
                  UE    ENS_TD
               (En_Id)   (En_Id)

### Forme algébrique arborescente – Version avec les deux relations jointes (UE ⨝ ENS_TD)

           π(En_Nom)
               │
               ⨝
        ┌──────┴──────┐
        │             ⨝
    ENSEIGNANT   ┌────┴────┐
                 UE      ENS_TD

### Forme algébrique linéaire

π En_Nom ( ENSEIGNANT ⨝ (UE ⨝ ENS_TD) )

Avec intersection explicite :

π En_Nom ( ENSEIGNANT ⨝ (UE ∩ ENS_TD) )

# Exercice 1

Soit l’expression algébrique suivante :

σ En_Id='E35' ( π UE_Id, UE_Libelle ( σ UE_NbGrp ≤ 1 ( UE ) ) )

---

### 1. Pourquoi cette expression ne fonctionne pas ?

Cette expression ne fonctionne pas, car on fait une **restriction sur `En_Id`**
alors que cet attribut **disparaît à cause de la projection** `π UE_Id, UE_Libelle`.  
La sélection ne peut donc pas s'appliquer.

---

### Expression corrigée

π UE_Id, UE_Libelle ( σ UE_NbGrp ≤ 1 ∧ En_Id='E35' ( UE ) )

---

### 2. Question correspondante

Quels sont les **identifiants** et **libellés** des unités d’enseignement
ayant un **nombre de groupes inférieur ou égal à un** et **enseignées par l’enseignant E35** ?

---

### 3. Forme arborescente


            π UE_Libellé
                  │
                  ▼
    σ (UE_NbGrp ≤ 1 ∧ En_Id = 'E35')
                  │
                  ▼
                  UE

### 4. Arbre optimisé (algèbre relationnelle)

           π_{UE_Id, UE_Libelle}
                   │
        σ_{UE_NbGrp ≤ 1 ∧ En_Id = 'E35'}
                   │
     π_{UE_Id, UE_Libelle, UE_NbGrp, En_Id}
                   │
                  UE

### 5. Interprétation SQL efficace

```sql

SELECT UE_Id, UE_Libelle
FROM UE u
WHERE u.UE_NbGrp <= 1
  AND u.En_Id = 'E35';

```