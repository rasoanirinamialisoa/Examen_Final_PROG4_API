private UserRepositoryImpl userRepository;
private ResultSet resultSet;

@BeforeEach
public void setup() throws SQLException {
    userRepository = new UserRepositoryImpl();

    Connection connection = mock(Connection.class);
    PreparedStatement preparedStatement = mock(PreparedStatement.class);
    resultSet = mock(ResultSet.class);

    when(connection.prepareStatement(any(String.class))).thenReturn(preparedStatement);
    when(preparedStatement.executeQuery()).thenReturn(resultSet);

//    when(preparedStatement.executeUpdate()).thenReturn(1);
}

@Test
public void testGetAllUsers(){
    try {
    List<Users> expectedUsers = new ArrayList<>();
    Users user1 = new Users();
    user1.setId(1);
    user1.setName("John");
    user1.setUsername("john_doe");
    user1.setBirthday(LocalDate.of(1995, 5, 25));
    user1.setEmail("john@example.com");
    user1.setPassword("john0147");
    expectedUsers.add(user1);

    Users user2 = new Users();
    user2.setId(2);
    user2.setName("Alice");
    user2.setUsername("alice_smith");
    user2.setBirthday(LocalDate.of(2000, 10, 20));
    user2.setEmail("alice@example.com");
    user2.setPassword("alice582");
    expectedUsers.add(user2);

    when(resultSet.next()).thenReturn(true, true, false);
    when(resultSet.getInt("id")).thenReturn(1, 2);
    when(resultSet.getString("name")).thenReturn("John", "Alice");
    when(resultSet.getString("username")).thenReturn("john_doe", "alice_smith");
    when(resultSet.getDate("birthday")).thenReturn(Date.valueOf(LocalDate.of(1995, 5, 25)),
            Date.valueOf(LocalDate.of(2000, 10, 20)));
    when(resultSet.getString("email")).thenReturn("john@example.com", "alice@example.com");
    when(resultSet.getString("password")).thenReturn("john0147", "alice582");

    List<Users> actualUsers = userRepository.getAllUsers();

    assertEquals(expectedUsers.size(), actualUsers.size());
    for (int i = 0; i < expectedUsers.size(); i++) {
        assertEquals(expectedUsers.get(i), actualUsers.get(i));
    }
    } catch (SQLException e) {
        e.printStackTrace();
    }
}

@Test
public void testGetUserById() {
    try {
        int userId = 17;
        Users expectedUser = new Users();
        expectedUser.setId(userId);
        expectedUser.setName("Veronica Lodge");
        expectedUser.setUsername("veronica");
        expectedUser.setBirthday(LocalDate.of(1997, 11, 18));
        expectedUser.setEmail("lodgevr@example.com");
        expectedUser.setPassword("veronica1032");

        when(resultSet.next()).thenReturn(true);
        when(resultSet.getInt("id")).thenReturn(userId);
        when(resultSet.getString("name")).thenReturn("John");
        when(resultSet.getString("username")).thenReturn("john_doe");
        when(resultSet.getDate("birthday")).thenReturn(Date.valueOf(LocalDate.of(1990, 5, 15)));
        when(resultSet.getString("email")).thenReturn("john@example.com");
        when(resultSet.getString("password")).thenReturn("password");

        Users actualUser = userRepository.getUserById(userId);

        assertEquals(expectedUser, actualUser);
    } catch (SQLException e) {
        e.printStackTrace();

    }
}