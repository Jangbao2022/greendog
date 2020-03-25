package com.boob.greendog.mapper;

import com.boob.greendog.model.Trolley;
import com.boob.greendog.model.TrolleyExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface TrolleyMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table trolley
     *
     * @mbg.generated Sat Mar 21 21:55:56 CST 2020
     */
    long countByExample(TrolleyExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table trolley
     *
     * @mbg.generated Sat Mar 21 21:55:56 CST 2020
     */
    int deleteByExample(TrolleyExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table trolley
     *
     * @mbg.generated Sat Mar 21 21:55:56 CST 2020
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table trolley
     *
     * @mbg.generated Sat Mar 21 21:55:56 CST 2020
     */
    int insert(Trolley record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table trolley
     *
     * @mbg.generated Sat Mar 21 21:55:56 CST 2020
     */
    int insertSelective(Trolley record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table trolley
     *
     * @mbg.generated Sat Mar 21 21:55:56 CST 2020
     */
    List<Trolley> selectByExampleWithRowbounds(TrolleyExample example, RowBounds rowBounds);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table trolley
     *
     * @mbg.generated Sat Mar 21 21:55:56 CST 2020
     */
    List<Trolley> selectByExample(TrolleyExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table trolley
     *
     * @mbg.generated Sat Mar 21 21:55:56 CST 2020
     */
    Trolley selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table trolley
     *
     * @mbg.generated Sat Mar 21 21:55:56 CST 2020
     */
    int updateByExampleSelective(@Param("record") Trolley record, @Param("example") TrolleyExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table trolley
     *
     * @mbg.generated Sat Mar 21 21:55:56 CST 2020
     */
    int updateByExample(@Param("record") Trolley record, @Param("example") TrolleyExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table trolley
     *
     * @mbg.generated Sat Mar 21 21:55:56 CST 2020
     */
    int updateByPrimaryKeySelective(Trolley record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table trolley
     *
     * @mbg.generated Sat Mar 21 21:55:56 CST 2020
     */
    int updateByPrimaryKey(Trolley record);
}