package exo4;

/**
 * Trie d'un tableau d'entiers multi-thread
 * Utilisation de wait() et notifyAll() au lieu de join()
 */
public class TrieurFaux extends Thread {

  private int[] t; // tableau à trier
  private int debut, fin; // tranche de ce tableau qu'il faut trier
  private TrieurFaux parent;

  public TrieurFaux(int[] t) {
    this(null, t, 0, t.length - 1);
  }
  
  public TrieurFaux(TrieurFaux parent, int[] t, int debut, int fin) {
    this.parent = parent;
    this.t = t;
    this.debut = debut;
    this.fin = fin;
    start();
  }

  public synchronized void notifier() {
    // Notifie tous les thread en attente sur "ce" (this) moniteur
    notifyAll();
  }

  public void run() {
    if (fin - debut < 2) {
      if (t[debut] > t[fin]) {
        echanger(debut, fin);
      }
    }
    else {
      int milieu = debut + (fin - debut) / 2;
      TrieurFaux trieur1 = new TrieurFaux(this, t, debut, milieu);
      TrieurFaux trieur2 = new TrieurFaux(this, t, milieu + 1, fin);
      // attend les 2 threads
      synchronized(this) {
        try {
          // Attendre une notification sur "ce" (this) moniteur
          System.out.println("Mise en attente de " 
                             + trieur1 + " et de " + trieur2); 
          wait();
          System.out.println("Notifié par un premier Thread");
          sleep(500) ; // pour favoriser l'arrivée des 2 notify
          // entre les 2 wait() (pour illustrer un problème du multi-tâche)
          wait();
          System.out.println("Notifié par un deuxième Thread");
        }
        catch(InterruptedException e) {}
      }
      triFusion(debut, fin);
    }
    if (parent != null) {
      System.out.println("Je notifie mon père " + parent);
      parent.notifier(); // indique à ceux qui l'attendent qu'il a fini
    }
  }

  /**
   * Echanger t[i] et t[j]
   */
  private void echanger(int i, int j) {
    int valeur = t[i];
    t[i] = t[j];
    t[j] = valeur;
  }

  /**
   * Fusionne 2 tranches déjà triées du tableau t.
   *   - 1ère tranche : de debut à milieu
   *   - 2ème tranche : de milieu + 1 à fin
   * @param milieu indique le dernier indice de la 1ère tranche
   */
  private void triFusion(int debut, int fin) {
    // tableau où va aller la fusion
    int[] tFusion = new int[fin - debut + 1];
    int milieu = (debut + fin) / 2;
    // Indices des éléments à comparer
    int i1 = debut, 
        i2 = milieu + 1;
    // indice de la prochaine case du tableau tFusion à remplir
    int iFusion = 0;
    while (i1 <= milieu && i2 <= fin) {
      if (t[i1] < t[i2]) {
        tFusion[iFusion++] = t[i1++];
      }
      else {
        tFusion[iFusion++] = t[i2++]; 
      }
    }
    if (i1 > milieu) {
      // la 1ère tranche est épuisée
      for (int i = i2; i <= fin; ) {
        tFusion[iFusion++] = t[i++];
      }
    }
    else {
      // la 2ème tranche est épuisée
      for (int i = i1; i <= milieu; ) {
        tFusion[iFusion++] = t[i++];
      }
    }
    // Copie tFusion dans t
    for (int i = 0, j = debut; i <= fin - debut; ) {
      t[j++] = tFusion[i++];
    }
  }
  public static void main(String[] args) {
    int[] t = {5, 8, 3, 2, 7, 10, 1};
    TrieurFaux trieur = new TrieurFaux(t);
    try {
      trieur.join();
    }
    catch(InterruptedException e) {}
    for (int i = 0; i <t.length; i++) {
      System.out.print(t[i] + " ; ");
    }
    System.out.println();
  }

}

