/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;
import hospital.*;
import java.util.Scanner;
import java.sql.SQLException;
/**
 *
 * @author User
 */
//je suis entrain de réflechir à peut être en faire une classe qui hériterait de Search
public class Reporting {
    private Connexion con;
    private String select, from, where;
    
    public Reporting(Connexion con)
    {
        this.con=con;
    }
    
    public void SearchWithWhere() throws SQLException, ClassNotFoundException
    {
        System.out.println(con.remplirChampsRequete("SELECT "+select+" FROM "+from+" WHERE "+where));
    }
    
    public void Moyennes() throws SQLException, ClassNotFoundException
    {
        select ="";
        from= "";
        where= "";
        this.SearchWithWhere();
    }
    
    public void toutesMesRequetesSqlaSupprimierPlusTard() throws SQLException, ClassNotFoundException
    {
        System.out.println("Vous voulez quoi? \n 1 - salaire moyen d'un infirmier \n 2 - salaire moyen d'un infirmier par service \n 3 - salaire moyen et effectif d'infirmiers par service"
                + "\n 4 - salaire moyen | effcetif | nb rotation jour/nuit le tout par service"
                + "\n 5 - nb de rotation jour/nuit"
                + "\n 6 - service | salaire moyen | salaire moyen par rotation| effectif | rotation ");
        Scanner sc = new Scanner(System.in);
        int choice = sc.nextInt();
        switch(choice)
        {
            case 1 :
                System.out.print("Choix 1 : le salaire moyen d'un infirmier :");
                select = "avg(salaire)";
                from = "infirmier";
                where = "salaire like '%%'";
                break;
            case 2 : 
                System.out.print("Choix 2 : le salaire moyen d'un infirmier par service :");
                select = "code_service, avg(salaire)";
                from = "infirmier";
                where = "salaire like '%%' group by code_service";
                break;
            case 3 : 
                System.out.print("Choix 3 : Service | effectif infirmir | salaire moyen");
                select = "code_service, avg(salaire), count(code_service)";
                from = "infirmier";
                where = "salaire like '%%' group by code_service";
                break;
            case 4 : 
                System.out.print("Choix 4 : Service | effectif infirmier | salaire moyen | rotation");
                select = "cs.code_service, nb, moy,  rotation, count(rotation)";
                from = "infirmier inner join (SELECT code_service, avg(salaire) as moy, count(code_service) as nb from infirmier group by code_service) cs on cs.code_service=infirmier.code_service ";
                where = "salaire like '%%' group by cs.code_service, rotation";
                break;
              case 5 : 
                System.out.print("Choix 5 : rotation | salaire moyen rotation");
                select =  "rotation, count(rotation)";
                from = "infirmier";
                where = "salaire like '%%' group by rotation";
                break;
              case 6 : 
                System.out.print("Choix 6 : Service | effectif infirmier | salaire moyen | salaire moyen rotation service | rotation");
                select =  " cs.code_service, nb, moy, avg(salaire),  rotation, count(rotation)";
                from = "infirmier inner join (SELECT code_service, avg(salaire) as moy, count(code_service) as nb from infirmier group by code_service) cs on cs.code_service=infirmier.code_service";
                where = "salaire like '%%' group by code_service, rotation";
                break;
                
              case 7 :
                System.out.print("Choix 7 : nb de docteurs par spécialité");
                select =  " specialite, count(specialite)";
                from = "docteur";
                where = "specialite like '%%' group by specialite";
                break;
                
              case 8 :
                System.out.print("Choix 8 : nb de docteurs et de patients par spécialité");
                select =  "d.specialite, count(d.specialite), nb_malades";
                from = "docteur d, (select specialite, count(no_malade) as nb_malades from docteur, soigne where numero = no_docteur group by specialite) m ";
                where = "d.specialite = m.specialite group by d.specialite";
                break;
                
               case 9 :
                System.out.print("Choix 9 : moy patients par docteur");
                select =  "nom, prenom, count(no_malade)";
                from = "docteur d, employe e, soigne s";
                where = "e.numero = d.numero and no_docteur = d.numero group by d.numero";
                break;
                
               case 10 :
                System.out.print("Choix 10 : nombre de chambres par service");
                select =  "code_service, count(no_chambre)";
                from = "chambre";
                where = "no_chambre like '%%' code_service";
                break;
                
               case 11 :
                    System.out.print("Choix 11 : nombre de chambre par service par batiment (mdr la requete qui sert à rien)");
                    select =  "batiment, code_service, count(no_chambre) ";
                    from = "chambre, service";
                    where = "code = code_service group by batiment, code_service";
                    break;
                
                case 12 :
                    System.out.print("Choix 12 : nombre de lits par service");
                   select =  " batiment,  c.code_service, count(c.no_chambre), sum(nb_lits)";
                    from = "chambre c, service";
                    where = "code = c.code_service group by batiment, c.code_service";
                    break;
                  
                    
                case 13 :
                    System.out.print("Choix 13 : moyenne de lits par chambre par service");
                    select =  "batiment,  c.code_service, count(c.no_chambre), sum(nb_lits), sum(nb_lits)/count(c.no_chambre)";
                    from = "chambre c, service";
                    where = "code = c.code_service group by batiment, c.code_service";
                    break;
                    
                    
               case 14 :
                    System.out.print("Choix 14 : % chambres occupées par service");
                    select =  "batiment,  c.code_service, count(c.no_chambre), sum(nb_lits), ch_occupee, ch_occupee/count(c.no_chambre)*100";
                    from = "chambre c, service, (select code_service, no_chambre, count(DISTINCT no_chambre) as ch_occupee from hospitalisation group by code_service) h";
                    where = "code = c.code_service and (h.code_service = c.code_service) group by batiment, c.code_service";
                    break;
                    
               case 15 :
                    System.out.print("Choix 14 : % lits occupés par service");
                    select =  "batiment, c.code_service, count(c.no_chambre), sum(nb_lits), ch_occupee, lit_occupe, ch_occupee/count(c.no_chambre)*100, lit_occupe/sum(nb_lits)*100 ";
                    from = "chambre c, service, (select code_service, no_chambre, count(DISTINCT no_chambre) as ch_occupee, count(lit) as lit_occupe from hospitalisation group by code_service) h";
                    where = "code = c.code_service and (h.code_service = c.code_service) group by batiment, c.code_service";
                    break;
                    
              case 16 :
                    System.out.print("Choix 16 : % de médecins par patients");
                    select =  "count(DISTINCT no_malade), count(no_docteur) , count(no_docteur)/count(DISTINCT no_malade)";
                    from = "soigne";
                    where = "no_malade like '%%'";
                    break;
                    
                    
              case 17 :
                    System.out.print("Choix 17 : % de patients par médecins pour chaque spé");
                    select =  "d.specialite, count(d.specialite), nb_malades, nb_malades/count(d.specialite)";
                    from = "docteur d, (select specialite, count(no_malade) as nb_malades from docteur, soigne where numero = no_docteur group by specialite) m ";
                    where = "d.specialite = m.specialite group by d.specialite";
                    break;
                    
              case 18 :
                    System.out.print("Choix 18 : nombre de malades par service , nombre d'infirmiers par service et % d'infirmiers par patient pour chaque secteur");
                    select =  "h.code_service, count(DISTINCT no_malade), count(DISTINCT numero), count(DISTINCT numero) /count(DISTINCT no_malade) ";
                    from = "hospitalisation h, infirmier i";
                    where = "i.code_service = h.code_service group by h.code_service";
                    break;
              case 19 :
                    System.out.print("Choix 19 : % de malades hospitalisés");
                    select =  "count(DISTINCT numero), count(DISTINCT no_malade), count(DISTINCT no_malade)/ count(DISTINCT numero) * 100 ";
                    from = "hospitalisation, malade";
                    where = "numero like '%%'";
                    break;
                    
              case 20 :
                    System.out.print("Choix 20 : nombre de survaillants par chambre");
                    select =  "count(*), count(DISTINCT surveillant), count(DISTINCT surveillant)/count(*)";
                    from = "chambre";
                    where = "surveillant like '%%'";
                    break;
            default :;
        }
        this.SearchWithWhere();
        
    }
    
}
