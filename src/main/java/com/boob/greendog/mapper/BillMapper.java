package com.boob.greendog.mapper;

import com.boob.greendog.model.Bill;
import com.boob.greendog.model.BillExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface BillMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table bill
     *
     * @mbg.generated Sat Mar 21 21:55:56 CST 2020
     */
    long countByExample(BillExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table bill
     *
     * @mbg.generated Sat Mar 21 21:55:56 CST 2020
     */
    int deleteByExample(BillExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table bill
     *
     * @mbg.generated Sat Mar 21 21:55:56 CST 2020
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table bill
     *
     * @mbg.generated Sat Mar 21 21:55:56 CST 2020
     */
    int insert(Bill record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table bill
     *
     * @mbg.generated Sat Mar 21 21:55:56 CST 2020
     */
    int insertSelective(Bill record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table bill
     *
     * @mbg.generated Sat Mar 21 21:55:56 CST 2020
     */
    List<Bill> selectByExampleWithRowbounds(BillExample example, RowBounds rowBounds);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table bill
     *
     * @mbg.generated Sat Mar 21 21:55:56 CST 2020
     */
    List<Bill> selectByExample(BillExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table bill
     *
     * @mbg.generated Sat Mar 21 21:55:56 CST 2020
     */
    Bill selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table bill
     *
     * @mbg.generated Sat Mar 21 21:55:56 CST 2020
     */
    int updateByExampleSelective(@Param("record") Bill record, @Param("example") BillExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table bill
     *
     * @mbg.generated Sat Mar 21 21:55:56 CST 2020
     */
    int updateByExample(@Param("record") Bill record, @Param("example") BillExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table bill
     *
     * @mbg.generated Sat Mar 21 21:55:56 CST 2020
     */
    int updateByPrimaryKeySelective(Bill record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table bill
     *
     * @mbg.generated Sat Mar 21 21:55:56 CST 2020
     */
    int updateByPrimaryKey(Bill record);
}