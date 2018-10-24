# RoomDatabase_sample

Here we using Room DB for android to store data locally to SQLite database 

The three major components are DAO interface class, Appdatabase class and Entity model class which is specified in the project above. 

The Room persistence library provides an abstraction layer over SQLite to allow for more robust database access while harnessing the full power of SQLite.
The library helps you create a cache of your app's data on a device that's running your app. This cache, which serves as your app's single source of truth, allows users to view a consistent copy of key information within your app, regardless of whether users have an internet connection.

The following code snippet contains a sample database configuration with one entity and one DAO:

User.java



```java
    @Entity

    public class User {

    @PrimaryKey
    private int uid;

    @ColumnInfo(name = "first_name")
    private String firstName;

    @ColumnInfo(name = "last_name")
    private String lastName;
    // Getters and setters are ignored for brevity,
    // but they're required for Room to work.
    }

```

UserDao.java

```java
    @Dao
    public interface UserDao {
    @Query("SELECT * FROM user")
    List<User> getAll();

    @Query("SELECT * FROM user WHERE uid IN (:userIds)")
    List<User> loadAllByIds(int[] userIds);

    @Query("SELECT * FROM user WHERE first_name LIKE :first AND "
           + "last_name LIKE :last LIMIT 1")
    User findByName(String first, String last);

    @Insert
    void insertAll(User... users);

    @Delete
    void delete(User user);
    }

```


AppDatabase.java


```java
   @Database(entities = {User.class}, version = 1)
   public abstract class AppDatabase extends RoomDatabase {
   public abstract UserDao userDao();
   }  
```

   
After creating the files above, you get an instance of the created database using the following code:


```java
   AppDatabase db = Room.databaseBuilder(getApplicationContext(),
   AppDatabase.class, "database-name").build();
```
   
