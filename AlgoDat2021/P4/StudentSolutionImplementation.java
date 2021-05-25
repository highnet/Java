package main.java.exercise;

import main.java.exercise.helper.ChainElement;
import main.java.exercise.helper.HashTable;
import main.java.exercise.helper.HashTableWithChaining;
import main.java.exercise.helper.Probe;
import main.java.framework.StudentInformation;
import main.java.framework.StudentSolution;


public class StudentSolutionImplementation implements StudentSolution {
    @Override
    public StudentInformation provideStudentInformation() {
        return new StudentInformation(
                "Joaquin", // Vorname
                "Telleria", // Nachname
                "01408189" // Matrikelnummer
        );
    }

    // Implementieren Sie hier Ihre Lösung für Verkettung der Überläufer
    public void insertVerkettung(HashTableWithChaining t, ChainElement chainElement, int m) {
        int position = chainElement.getKey() % m; // Calculate the position using the hash function h_1(k) = k mod m

        if (!t.containsNoChainElement(position)){ // if the position contains an element
            chainElement.setNext(t.get(position)); // set the "next" of the new chain element to that element
            t.replaceChainElement(chainElement,position); // replace that element with the new chain element
        } else { // if the position does not contain an element
            t.insertChainElement(chainElement,position);  // insert the new chain element into the previously calculated position
        }

    }

    // Implementieren Sie hier Ihre Lösung für die lineare Sondierung
    public int linearesSondieren(int key, int i, int m) {
        return (key + i) % m;
    }

    // Implementieren Sie hier Ihre Lösung für die quadratische Sondierung
    public int quadratischesSondieren(int key, int i, int m) {
        return (int) (key + 0.5 * i + 0.5 * Math.pow(i,2)) % m;
    }

    // Implementieren Sie hier Ihre Lösung für Double Hashing
    public int doubleHashing(int key, int i, int m) {
        return ((key % m) + (i * (1 + key % 5))) % m;
    }

    // Implementieren Sie hier Ihre Lösung für die Insert-Methode
    public void insert(HashTable t, Probe p, int key, int m) {
        int i = 0;
        while (i <= m) {
            int position = p.evaluate(key, i);
            if (t.isFree(position)) {
                t.insert(key, position);
                break;
            } else {
                i++;
            }
        }
    }


    // Implementieren Sie hier Ihre Lösung für Verbesserung nach Brent
    public void insertVerbesserungNachBrent(HashTable t, int key, int m) {
        int j = key % m;

        while (!t.isFree(j)) {
            int k = t.get(j);
            int j1 = (j + (1 + (key % 5))) % m;
            int j2 = (j + (1 + (k % 5))) % m;
            if (t.isFree(j1) || !t.isFree(j2)) {
                j = j1;
            } else {
                t.replace(key, j);
                key = k;
                j = j2;
            }
        }
        t.insert(key,j);
    }
}
