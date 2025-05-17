package main.testCRUD;

public class TestDangKyMonHoc {

    EntityManagerFactory emf = Persistence.createEntityManagerFactory("ck_quanlysinhvien");
    EntityManager em = emf.createEntityManager();
    EntityTransaction tx = em.getTransaction();

    @BeforeEach
    public void setUp() {
        try {
            tx.begin();

//            viet datafaker


            //            Users user1 = new Users("Nguyen Van A", "123456", "nguyenA@gmail.com");
//            Users user2 = new Users("Nguyen Van B", "123433DCWE56", "nguyenB@gmail.com");
//            Users user3 = new Users("Nguyen Thi C", "33ED", "nguyenC@gmail.com");
//            Users user4 = new Users("Nguyen Van F", "DD2E23E", "nguyenF@gmail.com");
//            Users user5 = new Users("Nguyen Van D", "1111", "nguyenD@gmail.com");
//
//            em.persist(user1);
//            em.persist(user2);
//            em.persist(user3);
//            em.persist(user4);
//            em.persist(user5);
//
//            Groups group1 = new Groups("Group 1");
//            Groups group2 = new Groups("Group 2");
//            Groups group3 = new Groups("Group 3");
//
//            em.persist(group1);
//            em.persist(group2);
//            em.persist(group3);

//            Users user1 = em.find(Users.class, 1);
//            Users user2 = em.find(Users.class, 2);
//            Users user3 = em.find(Users.class, 3);
//            Users user4 = em.find(Users.class, 4);
//            Users user5 = em.find(Users.class, 5);
//
//
//            Groups group1 = em.find(Groups.class, 1);
//            Groups group2 = em.find(Groups.class, 2);
//            Groups group3 = em.find(Groups.class, 3);
//
//
//            Users_Groups users_groups1 = new Users_Groups(group1, user1);
//            Users_Groups users_groups2 = new Users_Groups(group1, user2);
//            Users_Groups users_groups3 = new Users_Groups(group2, user3);
//            Users_Groups users_groups4 = new Users_Groups(group2, user4);
//            Users_Groups users_groups5 = new Users_Groups(group3, user5);
//
//            em.persist(users_groups1);
//            em.persist(users_groups2);
//            em.persist(users_groups3);
//            em.persist(users_groups4);
//            em.persist(users_groups5);


//            String sql = "INSERT INTO [dbo].[users_groups] values(7,2)";
//            int n = em.createNativeQuery(sql).executeUpdate();
//            System.out.println(n);




            tx.commit();
        } catch (Exception e) {
            e.printStackTrace();
            tx.rollback();
        }

    }

    @Test
    public void testShowUesr() {
        String sql = "SELECT u FROM Users u";
        List<Users> users = em.createQuery(sql, Users.class)
                .getResultList();
        System.out.println(users);
    }
    @Test
    public void testShowGroup() {
        String sql = "SELECT u FROM Groups u";
        List<Groups> groups = em.createQuery(sql, Groups.class)
                .getResultList();
        System.out.println(groups);
    }

    @Test
    public void testFindUserName_JAVA() {
        String sql = "SELECT u FROM Users u WHERE u.username = :username";
        List<Users> users = em.createQuery(sql, Users.class)
                .setParameter("username", "Nguyen Van A")
                .getResultList();
        System.out.println(users);
    }
    @Test
    public void testFindUserName_MSSQL() {
        String sql = "SELECT * FROM Users u WHERE u.username = :x";
        List<Users> users = em.createNativeQuery(sql, Users.class)
                .setParameter("x", "Nguyen Van A")
                .getResultList();
        System.out.println(users);
    }

    @Test
    public void test_Insert_User_Group() {

    }

    @AfterEach
    public void tearDown() {
        emf.close();
    }
}
