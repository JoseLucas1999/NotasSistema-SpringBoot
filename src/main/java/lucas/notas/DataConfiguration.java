package lucas.notas;

import javax.sql.DataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

@Configuration	
public class DataConfiguration {
	@Bean
	public DataSource dataSource() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();	
		dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
		//"com.mysql.jdbc.Driver"
		dataSource.setUrl("jdbc:mysql://localhost:3306/notaspring");
		dataSource.setUsername("root");
		dataSource.setPassword("12345");
		return dataSource;
	}
	
	//conexão com hibernate
	@Bean
	public JpaVendorAdapter jpaVendorAdapter () {
		HibernateJpaVendorAdapter adapter = new HibernateJpaVendorAdapter(); 
		adapter.setDatabase(Database.MYSQL);
		adapter.setShowSql(true);
		adapter.setGenerateDdl(true);
		adapter.setDatabasePlatform("org.hibernate.dialect.MySQL8Dialect");
		//"org.hibernate.dialect.MySQLDialect"
		adapter.setPrepareConnection(true);
		return adapter;
	}
}
