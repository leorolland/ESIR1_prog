package tp5;

import types.ABinHuffman;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import outilsHuffman.OutilsHuffman;
import types.Couple;
/**
 * Réalisation du décodage d'un texte par la méthode de Huffman
 */

public class DecodageHuffman
{
  public static void main (String[] args)
  {
    //------------------------------------------------------------------------
    // 0. Saisir le nom du fichier à décoder (À FAIRE)
    //------------------------------------------------------------------------
    String nomFichier = "ivre.txt.code";

    //------------------------------------------------------------------------
    // 1. Lire et construire la table de fréquences (DONNÉ)
    //------------------------------------------------------------------------
    int [] tableFrequences = OutilsHuffman.lireTableFrequences(nomFichier);

    //------------------------------------------------------------------------
    // 2. Construire l'arbre de Huffman (DONNÉ)
    //------------------------------------------------------------------------
    ABinHuffman arbreHuffman =
      OutilsHuffman.construireArbreHuffman(tableFrequences);
//    		CodageHuffman.construireArbreHuffman(tableFrequences);

    //------------------------------------------------------------------------
    // 2.1 afficher l'arbre de Huffman (À FAIRE)
    //------------------------------------------------------------------------
    System.out.println("Arbre de Huffman associé au texte " + nomFichier);
    afficherHuffman(arbreHuffman);

    //------------------------------------------------------------------------
    // 3. Lire le texte codé (DONNÉ)
    //------------------------------------------------------------------------
    String texteCode = OutilsHuffman.lireTexteCode(nomFichier);

    //------------------------------------------------------------------------
    // 4. Décoder le texte (À FAIRE)
    //------------------------------------------------------------------------
    StringBuilder texteDecode = decoderTexte(texteCode, arbreHuffman);

    //------------------------------------------------------------------------
    // 5. Enregistrer le texte décode (DONNÉ)
    //------------------------------------------------------------------------
    System.out.println("texte décodé:\n\n" + texteDecode);
    OutilsHuffman.enregistrerTexte(texteDecode, nomFichier + ".decode");
  }

  /**
   * 4. décoder une chaîne (non vide) encodée par le codage de Huffman
   * @param texteCode    : chaîne de "0/1" à décoder
   * @param arbreHuffman : arbre de (dé)codage des caractères
   */
  public static StringBuilder decoderTexte(String texteCode, ABinHuffman arbreHuffman)
  {
	  List<Couple<Character, String>> listeChar = getAssocChar(arbreHuffman);
	  // On extrait une liste des mots binaires encodés pour accélérer la recherche
	  List<String> mots = listeChar.stream().map(c->c.deuxieme()).collect(Collectors.toList()); 
	  
	  StringBuilder sb = new StringBuilder();
	  
	  int iDebut = 0;
	  int iFin = 1;
	  while (iFin<=texteCode.length()) {
		  String mot = texteCode.substring(iDebut, iFin);
		  if (mots.contains(mot)) {
			  // On cherche le couple correspondant et on renvoie le char associé.
			  Character c = listeChar.stream().filter(couple->couple.deuxieme().equals(mot)).findFirst().get().premier(); 
			  sb.append(c);  
			  iDebut = iFin;
		  }
		  ++iFin;
	  }
	  return sb;
  }

  public static List<Couple<Character, String>> getAssocChar(ABinHuffman a) {
	  return getAssocChar(a, "", new LinkedList<Couple<Character, String>>());
  }
  private static List<Couple<Character, String>> getAssocChar(ABinHuffman a, String chemin, List<Couple<Character, String>> listeChar) {
	  if (a.estFeuille()) {
		  Couple<Character, String> c = new Couple<Character, String>(a.getValeur().premier(), chemin);
		  listeChar.add(c);
	  } else {
		  // le noeud possède deux fils
		  getAssocChar(a.filsGauche(), chemin+'0', listeChar);
		  getAssocChar(a.filsDroit(), chemin+'1', listeChar);
	  }
	  return listeChar;
  }
  
  /**
   * 2.1 afficher un arbre de Huffman
   * @param a : arbre binaire de Huffman
   */
  public static void afficherHuffman(ABinHuffman a)
  {
	  List<Couple<Character, String>> listeChar = getAssocChar(a);
	  listeChar.forEach(e -> System.out.println(e.premier() + " " + e.deuxieme()));
  }
} // DecodageHuffman
