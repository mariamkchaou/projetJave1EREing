package service;

public interface Service<T> {
    public void creat(Object... o);

    public void affiche(Object... o);

    public T cherche(Object... o);
}
