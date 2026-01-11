package JDBC;

public interface ICRUD {
    public Object add();

    public int update(long id);

    public int delete(long id);
}
