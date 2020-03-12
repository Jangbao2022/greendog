package com.boob.greendog.model;

import java.util.Date;

public class Customer {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column customer.id
     *
     * @mbg.generated Thu Mar 12 15:46:41 CST 2020
     */
    private Long id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column customer.name
     *
     * @mbg.generated Thu Mar 12 15:46:41 CST 2020
     */
    private String name;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column customer.nickname
     *
     * @mbg.generated Thu Mar 12 15:46:41 CST 2020
     */
    private String nickname;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column customer.sex
     *
     * @mbg.generated Thu Mar 12 15:46:41 CST 2020
     */
    private Integer sex;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column customer.age
     *
     * @mbg.generated Thu Mar 12 15:46:41 CST 2020
     */
    private Integer age;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column customer.address
     *
     * @mbg.generated Thu Mar 12 15:46:41 CST 2020
     */
    private String address;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column customer.contact
     *
     * @mbg.generated Thu Mar 12 15:46:41 CST 2020
     */
    private String contact;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column customer.account
     *
     * @mbg.generated Thu Mar 12 15:46:41 CST 2020
     */
    private String account;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column customer.password
     *
     * @mbg.generated Thu Mar 12 15:46:41 CST 2020
     */
    private String password;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column customer.gmt_created
     *
     * @mbg.generated Thu Mar 12 15:46:41 CST 2020
     */
    private Date gmtCreated;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column customer.gmt_modified
     *
     * @mbg.generated Thu Mar 12 15:46:41 CST 2020
     */
    private Date gmtModified;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column customer.id
     *
     * @return the value of customer.id
     *
     * @mbg.generated Thu Mar 12 15:46:41 CST 2020
     */
    public Long getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column customer.id
     *
     * @param id the value for customer.id
     *
     * @mbg.generated Thu Mar 12 15:46:41 CST 2020
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column customer.name
     *
     * @return the value of customer.name
     *
     * @mbg.generated Thu Mar 12 15:46:41 CST 2020
     */
    public String getName() {
        return name;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column customer.name
     *
     * @param name the value for customer.name
     *
     * @mbg.generated Thu Mar 12 15:46:41 CST 2020
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column customer.nickname
     *
     * @return the value of customer.nickname
     *
     * @mbg.generated Thu Mar 12 15:46:41 CST 2020
     */
    public String getNickname() {
        return nickname;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column customer.nickname
     *
     * @param nickname the value for customer.nickname
     *
     * @mbg.generated Thu Mar 12 15:46:41 CST 2020
     */
    public void setNickname(String nickname) {
        this.nickname = nickname == null ? null : nickname.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column customer.sex
     *
     * @return the value of customer.sex
     *
     * @mbg.generated Thu Mar 12 15:46:41 CST 2020
     */
    public Integer getSex() {
        return sex;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column customer.sex
     *
     * @param sex the value for customer.sex
     *
     * @mbg.generated Thu Mar 12 15:46:41 CST 2020
     */
    public void setSex(Integer sex) {
        this.sex = sex;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column customer.age
     *
     * @return the value of customer.age
     *
     * @mbg.generated Thu Mar 12 15:46:41 CST 2020
     */
    public Integer getAge() {
        return age;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column customer.age
     *
     * @param age the value for customer.age
     *
     * @mbg.generated Thu Mar 12 15:46:41 CST 2020
     */
    public void setAge(Integer age) {
        this.age = age;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column customer.address
     *
     * @return the value of customer.address
     *
     * @mbg.generated Thu Mar 12 15:46:41 CST 2020
     */
    public String getAddress() {
        return address;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column customer.address
     *
     * @param address the value for customer.address
     *
     * @mbg.generated Thu Mar 12 15:46:41 CST 2020
     */
    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column customer.contact
     *
     * @return the value of customer.contact
     *
     * @mbg.generated Thu Mar 12 15:46:41 CST 2020
     */
    public String getContact() {
        return contact;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column customer.contact
     *
     * @param contact the value for customer.contact
     *
     * @mbg.generated Thu Mar 12 15:46:41 CST 2020
     */
    public void setContact(String contact) {
        this.contact = contact == null ? null : contact.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column customer.account
     *
     * @return the value of customer.account
     *
     * @mbg.generated Thu Mar 12 15:46:41 CST 2020
     */
    public String getAccount() {
        return account;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column customer.account
     *
     * @param account the value for customer.account
     *
     * @mbg.generated Thu Mar 12 15:46:41 CST 2020
     */
    public void setAccount(String account) {
        this.account = account == null ? null : account.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column customer.password
     *
     * @return the value of customer.password
     *
     * @mbg.generated Thu Mar 12 15:46:41 CST 2020
     */
    public String getPassword() {
        return password;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column customer.password
     *
     * @param password the value for customer.password
     *
     * @mbg.generated Thu Mar 12 15:46:41 CST 2020
     */
    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column customer.gmt_created
     *
     * @return the value of customer.gmt_created
     *
     * @mbg.generated Thu Mar 12 15:46:41 CST 2020
     */
    public Date getGmtCreated() {
        return gmtCreated;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column customer.gmt_created
     *
     * @param gmtCreated the value for customer.gmt_created
     *
     * @mbg.generated Thu Mar 12 15:46:41 CST 2020
     */
    public void setGmtCreated(Date gmtCreated) {
        this.gmtCreated = gmtCreated;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column customer.gmt_modified
     *
     * @return the value of customer.gmt_modified
     *
     * @mbg.generated Thu Mar 12 15:46:41 CST 2020
     */
    public Date getGmtModified() {
        return gmtModified;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column customer.gmt_modified
     *
     * @param gmtModified the value for customer.gmt_modified
     *
     * @mbg.generated Thu Mar 12 15:46:41 CST 2020
     */
    public void setGmtModified(Date gmtModified) {
        this.gmtModified = gmtModified;
    }
}