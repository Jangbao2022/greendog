package com.boob.greendog.model;

import java.util.Date;

public class Doctor {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column doctor.id
     *
     * @mbg.generated Sat Mar 21 21:55:56 CST 2020
     */
    private Long id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column doctor.name
     *
     * @mbg.generated Sat Mar 21 21:55:56 CST 2020
     */
    private String name;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column doctor.sex
     *
     * @mbg.generated Sat Mar 21 21:55:56 CST 2020
     */
    private Integer sex;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column doctor.age
     *
     * @mbg.generated Sat Mar 21 21:55:56 CST 2020
     */
    private Integer age;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column doctor.pic
     *
     * @mbg.generated Sat Mar 21 21:55:56 CST 2020
     */
    private String pic;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column doctor.address
     *
     * @mbg.generated Sat Mar 21 21:55:56 CST 2020
     */
    private String address;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column doctor.contact
     *
     * @mbg.generated Sat Mar 21 21:55:56 CST 2020
     */
    private String contact;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column doctor.gmt_created
     *
     * @mbg.generated Sat Mar 21 21:55:56 CST 2020
     */
    private Date gmtCreated;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column doctor.gmt_modified
     *
     * @mbg.generated Sat Mar 21 21:55:56 CST 2020
     */
    private Date gmtModified;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column doctor.price
     *
     * @mbg.generated Sat Mar 21 21:55:56 CST 2020
     */
    private Float price;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column doctor.customer_id
     *
     * @mbg.generated Sat Mar 21 21:55:56 CST 2020
     */
    private Long customerId;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column doctor.id
     *
     * @return the value of doctor.id
     *
     * @mbg.generated Sat Mar 21 21:55:56 CST 2020
     */
    public Long getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column doctor.id
     *
     * @param id the value for doctor.id
     *
     * @mbg.generated Sat Mar 21 21:55:56 CST 2020
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column doctor.name
     *
     * @return the value of doctor.name
     *
     * @mbg.generated Sat Mar 21 21:55:56 CST 2020
     */
    public String getName() {
        return name;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column doctor.name
     *
     * @param name the value for doctor.name
     *
     * @mbg.generated Sat Mar 21 21:55:56 CST 2020
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column doctor.sex
     *
     * @return the value of doctor.sex
     *
     * @mbg.generated Sat Mar 21 21:55:56 CST 2020
     */
    public Integer getSex() {
        return sex;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column doctor.sex
     *
     * @param sex the value for doctor.sex
     *
     * @mbg.generated Sat Mar 21 21:55:56 CST 2020
     */
    public void setSex(Integer sex) {
        this.sex = sex;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column doctor.age
     *
     * @return the value of doctor.age
     *
     * @mbg.generated Sat Mar 21 21:55:56 CST 2020
     */
    public Integer getAge() {
        return age;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column doctor.age
     *
     * @param age the value for doctor.age
     *
     * @mbg.generated Sat Mar 21 21:55:56 CST 2020
     */
    public void setAge(Integer age) {
        this.age = age;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column doctor.pic
     *
     * @return the value of doctor.pic
     *
     * @mbg.generated Sat Mar 21 21:55:56 CST 2020
     */
    public String getPic() {
        return pic;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column doctor.pic
     *
     * @param pic the value for doctor.pic
     *
     * @mbg.generated Sat Mar 21 21:55:56 CST 2020
     */
    public void setPic(String pic) {
        this.pic = pic == null ? null : pic.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column doctor.address
     *
     * @return the value of doctor.address
     *
     * @mbg.generated Sat Mar 21 21:55:56 CST 2020
     */
    public String getAddress() {
        return address;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column doctor.address
     *
     * @param address the value for doctor.address
     *
     * @mbg.generated Sat Mar 21 21:55:56 CST 2020
     */
    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column doctor.contact
     *
     * @return the value of doctor.contact
     *
     * @mbg.generated Sat Mar 21 21:55:56 CST 2020
     */
    public String getContact() {
        return contact;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column doctor.contact
     *
     * @param contact the value for doctor.contact
     *
     * @mbg.generated Sat Mar 21 21:55:56 CST 2020
     */
    public void setContact(String contact) {
        this.contact = contact == null ? null : contact.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column doctor.gmt_created
     *
     * @return the value of doctor.gmt_created
     *
     * @mbg.generated Sat Mar 21 21:55:56 CST 2020
     */
    public Date getGmtCreated() {
        return gmtCreated;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column doctor.gmt_created
     *
     * @param gmtCreated the value for doctor.gmt_created
     *
     * @mbg.generated Sat Mar 21 21:55:56 CST 2020
     */
    public void setGmtCreated(Date gmtCreated) {
        this.gmtCreated = gmtCreated;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column doctor.gmt_modified
     *
     * @return the value of doctor.gmt_modified
     *
     * @mbg.generated Sat Mar 21 21:55:56 CST 2020
     */
    public Date getGmtModified() {
        return gmtModified;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column doctor.gmt_modified
     *
     * @param gmtModified the value for doctor.gmt_modified
     *
     * @mbg.generated Sat Mar 21 21:55:56 CST 2020
     */
    public void setGmtModified(Date gmtModified) {
        this.gmtModified = gmtModified;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column doctor.price
     *
     * @return the value of doctor.price
     *
     * @mbg.generated Sat Mar 21 21:55:56 CST 2020
     */
    public Float getPrice() {
        return price;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column doctor.price
     *
     * @param price the value for doctor.price
     *
     * @mbg.generated Sat Mar 21 21:55:56 CST 2020
     */
    public void setPrice(Float price) {
        this.price = price;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column doctor.customer_id
     *
     * @return the value of doctor.customer_id
     *
     * @mbg.generated Sat Mar 21 21:55:56 CST 2020
     */
    public Long getCustomerId() {
        return customerId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column doctor.customer_id
     *
     * @param customerId the value for doctor.customer_id
     *
     * @mbg.generated Sat Mar 21 21:55:56 CST 2020
     */
    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }
}