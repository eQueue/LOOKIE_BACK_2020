package kr.or.connect.reservation.dao;

import static kr.or.connect.reservation.sqls.DisplayInfoSqls.SELECT_PRODUCT_IMAGE_BY_ID;

import java.util.*;

import javax.sql.*;

import org.springframework.dao.*;
import org.springframework.jdbc.core.*;
import org.springframework.jdbc.core.namedparam.*;
import org.springframework.jdbc.core.simple.*;
import org.springframework.stereotype.*;

import kr.or.connect.reservation.dto.*;

@Repository
public class ProductImageDao {

	private NamedParameterJdbcTemplate jdbc;
    private SimpleJdbcInsert insertAction;
    private RowMapper<ProductImageDto> rowMapper = BeanPropertyRowMapper.newInstance(ProductImageDto.class);

    public ProductImageDao(DataSource dataSource) {
        this.jdbc = new NamedParameterJdbcTemplate(dataSource);
        
    }
    
    public List<ProductImageDto> selectProductImageById(Integer productId) {
    	
    	try {
    		Map<String, Integer> params = new HashMap<>();
    		
    		params.put("id", productId);
    		
			
			return jdbc.query(SELECT_PRODUCT_IMAGE_BY_ID, params, rowMapper);
			
		}catch(EmptyResultDataAccessException e) {//해당 조건이 없을경우
			return null;
		}
    }
}
