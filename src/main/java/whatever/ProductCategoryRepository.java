package whatever;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;

@Repository
public class ProductCategoryRepository {

    @Autowired
    JdbcTemplate jdbcTemplate;

    public ProductCategory findById(int id) {
        return jdbcTemplate.queryForObject("select * from productCategory where id=?", new Object[] {id},
                new BeanPropertyRowMapper<ProductCategory>(ProductCategory.class));
    }

    class ProductCategoryRowMapper implements RowMapper<ProductCategory>{

        @Override
        public ProductCategory mapRow(ResultSet resultSet, int i) throws SQLException {
            return null;
        }
    }
}
