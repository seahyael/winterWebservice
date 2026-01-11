package JDBC;

public interface ICRUD {
    public int add(String title);

    public int update(long id);

    public int delete(long id);
}
