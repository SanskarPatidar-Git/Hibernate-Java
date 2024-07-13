INTEGRATION OF HIBERNATE - 

  1. Add a dependency of Hibernate , JPA (If using) and MySQL Connector and build the project.
      
	   <!-- Hibernate ORM with JPA API -->
    <dependency>
        <groupId>org.hibernate</groupId>
        <artifactId>hibernate-core</artifactId>
        <version>5.6.1.Final</version> <!-- Replace with the latest Hibernate version -->
    </dependency>
    
    <!-- MySQL Connector/J -->
    <dependency>
        <groupId>mysql</groupId>
        <artifactId>mysql-connector-java</artifactId>
        <version>8.0.28</version> <!-- Replace with the latest version -->
    </dependency>

  2. Create a hibernate config file.
     
     <?xml version='1.0' encoding='utf-8'?>
     <!DOCTYPE hibernate-configuration PUBLIC "-//Hibernate/Hibernate Configuration DTD//EN"
            "http://www.hibernate.org/dtd/hibernate-configuration-3.0.dtd">

    <hibernate-configuration>
        <session-factory>
            <!-- Database connection settings -->
            <property name="hibernate.connection.driver_class">com.mysql.cj.jdbc.Driver</property>
            <property name="hibernate.connection.url">jdbc:mysql://localhost:3306/hibernate_tutorial</property>
            <property name="hibernate.connection.username">root</property>
            <property name="hibernate.connection.password">root</property>
    
    		
            <!-- JDBC connection pool settings -->
            <property name="hibernate.c3p0.min_size">5</property>
            <property name="hibernate.c3p0.max_size">20</property>
            <property name="hibernate.c3p0.timeout">300</property>
            <property name="hibernate.c3p0.max_statements">50</property>
            <property name="hibernate.c3p0.idle_test_period">3000</property>
            <!-- SQL dialect -->
            <property name="hibernate.dialect">org.hibernate.dialect.MySQL8Dialect</property>
    
            <!-- Echo all executed SQL to stdout -->
            <property name="hibernate.show_sql">true</property>
    
            <!-- Drop and re-create the database schema on startup -->
            <property name="hibernate.hbm2ddl.auto">update</property>
    
            <!-- Enable Hibernate's automatic session context management -->
            <property name="hibernate.current_session_context_class">thread</property>
    
            <!-- Specify package to scan for annotated entities 
            <mapping class="com.example.YourEntity"/> --> <!-- Replace with your entity class package -->
            
        </session-factory>
    </hibernate-configuration>

  3. Create a class HibernateUtil and provide this configuration to hibernate.

    public class HibernateUtil {
	      private static SessionFactory sessionFactory;
        static {
            try {
                // Create the SessionFactory from hibernate.cfg.xml
                Configuration configuration = new Configuration().configure();
                ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                        .applySettings(configuration.getProperties()).build();
                
                sessionFactory = configuration
                		.addAnnotatedClass(Emp.class)
                		.addAnnotatedClass(User.class)
                		.addAnnotatedClass(Order.class)
                		.buildSessionFactory(serviceRegistry);
                
            } catch (Throwable ex) {
                // Make sure you log the exception, as it might be swallowed
                System.err.println("Initial SessionFactory creation failed." + ex);
                throw new ExceptionInInitializerError(ex);
            }
        }
    
        public static SessionFactory getSessionFactory() {
            return sessionFactory;
        }
        
        public static Session getSession() {
    		return sessionFactory.openSession();
    	}
        
        public static void closeSession(Session session) {
    		if(session != null) {
    			session.close();
    		}
    	}
    }

4. Setup for hibernate is done. Now create a Entity class , Repository and perform database operations through Hibernate.

