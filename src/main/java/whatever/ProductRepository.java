package whatever;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.*;
import java.util.List;
import java.util.Map;


@Repository
public class ProductRepository {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Transactional(readOnly = true)
    public List<Product> findAll() {
        return jdbcTemplate.query("select * from product", new ProductRowMapper());
    }
    @Transactional(readOnly = true)
    public Product findProductById(int id){
        return jdbcTemplate.queryForObject("select * from product where id=?", new Object[] {id},
                new BeanPropertyRowMapper<Product>(Product.class));
    }

    public Product create(final Product prod){
        final String sql = "insert into product (name, description) values(?,?)";
        KeyHolder holder = new GeneratedKeyHolder();
        jdbcTemplate.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                ps.setString(1, prod.getName());
                ps.setString(2, prod.getDescription());
                return ps;
            }
        }, holder);
        int newProdId = holder.getKey().intValue();
        prod.setId(newProdId);
        return prod;
    }

    class ProductRowMapper implements RowMapper<Product> {

        @Override
        public Product mapRow(ResultSet resultSet, int i) throws SQLException {
            return null;
        }
    }

}


