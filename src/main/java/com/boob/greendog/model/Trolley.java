package com.boob.greendog.model;

import java.util.Date;

public class Trolley {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column trolley.id
     *
     * @mbg.generated Sat Mar 21 21:55:56 CST 2020
     */
    private Long id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column trolley.customer_id
     *
     * @mbg.generated Sat Mar 21 21:55:56 CST 2020
     */
    private Long customerId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column trolley.medicine_id
     *
     * @mbg.generated Sat Mar 21 21:55:56 CST 2020
     */
    private Long medicineId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column trolley.gmt_created
     *
     * @mbg.generated Sat Mar 21 21:55:56 CST 2020
     */
    private Date gmtCreated;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column trolley.gmt_modified
     *
     * @mbg.generated Sat Mar 21 21:55:56 CST 2020
     */
    private Date gmtModified;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column trolley.count
     *
     * @mbg.generated Sat Mar 21 21:55:56 CST 2020
     */
    private Integer count;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column trolley.id
     *
     * @return the value of trolley.id
     *
     * @mbg.generated Sat Mar 21 21:55:56 CST 2020
     */
    public Long getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column trolley.id
     *
     * @param id the value for trolley.id
     *
     * @mbg.generated Sat Mar 21 21:55:56 CST 2020
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column trolley.customer_id
     *
     * @return the value of trolley.customer_id
     *
     * @mbg.generated Sat Mar 21 21:55:56 CST 2020
     */
    public Long getCustomerId() {
        return customerId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column trolley.customer_id
     *
     * @param customerId the value for trolley.customer_id
     *
     * @mbg.generated Sat Mar 21 21:55:56 CST 2020
     */
    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column trolley.medicine_id
     *
     * @return the value of trolley.medicine_id
     *
     * @mbg.generated Sat Mar 21 21:55:56 CST 2020
     */
    public Long getMedicineId() {
        return medicineId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column trolley.medicine_id
     *
     * @param medicineId the value for trolley.medicine_id
     *
     * @mbg.generated Sat Mar 21 21:55:56 CST 2020
     */
    public void setMedicineId(Long medicineId) {
        this.medicineId = medicineId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column trolley.gmt_created
     *
     * @return the value of trolley.gmt_created
     *
     * @mbg.generated Sat Mar 21 21:55:56 CST 2020
     */
    public Date getGmtCreated() {
        return gmtCreated;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column trolley.gmt_created
     *
     * @param gmtCreated the value for trolley.gmt_created
     *
     * @mbg.generated Sat Mar 21 21:55:56 CST 2020
     */
    public void setGmtCreated(Date gmtCreated) {
        this.gmtCreated = gmtCreated;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column trolley.gmt_modified
     *
     * @return the value of trolley.gmt_modified
     *
     * @mbg.generated Sat Mar 21 21:55:56 CST 2020
     */
    public Date getGmtModified() {
        return gmtModified;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column trolley.gmt_modified
     *
     * @param gmtModified the value for trolley.gmt_modified
     *
     * @mbg.generated Sat Mar 21 21:55:56 CST 2020
     */
    public void setGmtModified(Date gmtModified) {
        this.gmtModified = gmtModified;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column trolley.count
     *
     * @return the value of trolley.count
     *
     * @mbg.generated Sat Mar 21 21:55:56 CST 2020
     */
    public Integer getCount() {
        return count;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column trolley.count
     *
     * @param count the value for trolley.count
     *
     * @mbg.generated Sat Mar 21 21:55:56 CST 2020
     */
    public void setCount(Integer count) {
        this.count = count;
    }
}