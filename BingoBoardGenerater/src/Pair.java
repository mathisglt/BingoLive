public class Pair<T, U> {
    private T first;
    private U second;

    // Constructeur
    public Pair(T first, U second) {
        this.first = first;
        this.second = second;
    }

    // Getter pour le premier élément
    public T getFirst() {
        return first;
    }

    // Getter pour le second élément
    public U getSecond() {
        return second;
    }
}
