package com.boob.greendog.mapper;

import com.boob.greendog.model.Instance;
import com.boob.greendog.model.InstanceExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface InstanceMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table instance
     *
     * @mbg.generated Sat Mar 21 21:55:56 CST 2020
     */
    long countByExample(InstanceExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table instance
     *
     * @mbg.generated Sat Mar 21 21:55:56 CST 2020
     */
    int deleteByExample(InstanceExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table instance
     *
     * @mbg.generated Sat Mar 21 21:55:56 CST 2020
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table instance
     *
     * @mbg.generated Sat Mar 21 21:55:56 CST 2020
     */
    int insert(Instance record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table instance
     *
     * @mbg.generated Sat Mar 21 21:55:56 CST 2020
     */
    int insertSelective(Instance record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table instance
     *
     * @mbg.generated Sat Mar 21 21:55:56 CST 2020
     */
    List<Instance> selectByExampleWithRowbounds(InstanceExample example, RowBounds rowBounds);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table instance
     *
     * @mbg.generated Sat Mar 21 21:55:56 CST 2020
     */
    List<Instance> selectByExample(InstanceExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table instance
     *
     * @mbg.generated Sat Mar 21 21:55:56 CST 2020
     */
    Instance selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table instance
     *
     * @mbg.generated Sat Mar 21 21:55:56 CST 2020
     */
    int updateByExampleSelective(@Param("record") Instance record, @Param("example") InstanceExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table instance
     *
     * @mbg.generated Sat Mar 21 21:55:56 CST 2020
     */
    int updateByExample(@Param("record") Instance record, @Param("example") InstanceExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table instance
     *
     * @mbg.generated Sat Mar 21 21:55:56 CST 2020
     */
    int updateByPrimaryKeySelective(Instance record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table instance
     *
     * @mbg.generated Sat Mar 21 21:55:56 CST 2020
     */
    int updateByPrimaryKey(Instance record);
}