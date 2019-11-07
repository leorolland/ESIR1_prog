package types;

/**
 * spécification du TA Rationnel
 */

public interface Rationnel extends Comparable<Rationnel>
{

  /**
   * initialiser un rationnel à partir d'un entier : nb/1
   * @param nb : valeur du numérateur
   */
  // public Rationnel(int nb);

  /**
   * initialiser un rationnel avec numerateur et dénominateur
   * @param num : valeur du numérateur
   * @param den : valeur du dénominateur
   * @pre den != 0
   * @post fraction irréductible et dénominateur > 0
   */
  // public Rationnel(int num, int den):

  /**
   * initialiser un rationnel à partir d'un autre
   * @param r : rationnel à dupliquer
   */
  // public Rationnel(Rationnel r);

  /**
   * égalité de deux rationnels
   * @param r : rationnel à comparer au rationnel this
   * @return vrai si le rationnel this est égal au rationnel paramètre
   */
  public boolean equals(Rationnel r);

  /**
   * additionner deux rationnels
   * @param r : rationnel à additionner avec le rationnel this
   * @return nouveau rationnel somme du rationnel this et du rationnel paramètre
   */
  public Rationnel somme(Rationnel r);

  /**
   * calculer l'inverse du rationnel this
   * @return nouveau rationnel inverse du rationnel this
   * @pre numérateur != 0
   */
  public Rationnel inverse();

  /**
   * calculer la valeur réelle du rationnel this
   * @return valeur réelle du rationnel this
   */
  public double valeur();

  /**
   *  @return représentation affichable d'un rationnel
   * numerateur / denominateur
   * ou numerateur (si denominateur = 1)
   */
  public String toString();

  // accesseurs
  public int getNumerateur();   // consulter le numérateur
  public int getDenominateur(); // consulter le dénominateur

  // méthode de l'interface Comparable<Rationnel>
  // comparaison ordonnée du rationnel this et du rationnel autre
  public int compareTo(Rationnel autre);
}// Rationnel
