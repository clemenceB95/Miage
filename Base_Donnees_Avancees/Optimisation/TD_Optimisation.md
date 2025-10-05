# TD Optimisation Algébrique

## Schéma Relationnel

On considère le schéma relationnel suivant :

**ETUDIANT**(Et_Id, Et_Nom, Et_Pren, Et_DateNais, Et_LieuNais)  
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

## Exercice 1 : Représentation Entité/Association

### Schéma Entité/Association (E/A)

![Schéma E/A](Optimisation/Asset/Img/Rappel_Exercice1.png.jpg)

### Description des entités et associations

1. **Entités** :
    - **ETUDIANT** : Et_Id, Et_Nom, Et_Pren, Et_DateNais, Et_LieuNais
    - **ENSEIGNANT** : En_Id, En_Nom, En_Pren, En_DateNais, En_LieuNais
    - **UE** : UE_Id, UE_Libelle, UE_CM, UE_TD, UE_NbGrp, En_Id

2. **Associations** :
    - **INSCRIPTION** entre ETUDIANT et UE : relie un étudiant à l’UE où il est inscrit (attributs : I_Grp, I_NoteF)
    - **ENS_TD** entre ENSEIGNANT et UE : relie un enseignant à un groupe de TD (attribut : ETD_Grp)
    - L’enseignant principal de l’UE est indiqué par l’attribut `En_Id` de **UE**.

---

## Modèle Logique de Données (MLD)

![Schéma MLD](Optimisation/Asset/Img/Rappel_Exercice1_MLD.png)

### Remarques

- Le MLD montre le schéma relationnel transformé depuis le modèle E/A.
- Les relations deviennent des tables avec leurs clés primaires et étrangères.
- Chaque association avec attributs devient une table à part (ex. INSCRIPTION et ENS_TD).  


