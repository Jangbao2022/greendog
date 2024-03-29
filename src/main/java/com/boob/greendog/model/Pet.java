package com.boob.greendog.model;

import java.util.Date;

public class Pet {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column pet.id
     *
     * @mbg.generated Sat Mar 21 21:55:55 CST 2020
     */
    private Long id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column pet.name
     *
     * @mbg.generated Sat Mar 21 21:55:55 CST 2020
     */
    private String name;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column pet.sex
     *
     * @mbg.generated Sat Mar 21 21:55:55 CST 2020
     */
    private Integer sex;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column pet.age
     *
     * @mbg.generated Sat Mar 21 21:55:55 CST 2020
     */
    private Integer age;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column pet.breed
     *
     * @mbg.generated Sat Mar 21 21:55:55 CST 2020
     */
    private String breed;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column pet.master_id
     *
     * @mbg.generated Sat Mar 21 21:55:55 CST 2020
     */
    private Long masterId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column pet.status
     *
     * @mbg.generated Sat Mar 21 21:55:55 CST 2020
     */
    private Integer status;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column pet.picture
     *
     * @mbg.generated Sat Mar 21 21:55:55 CST 2020
     */
    private String picture;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column pet.master_nickname
     *
     * @mbg.generated Sat Mar 21 21:55:55 CST 2020
     */
    private String masterNickname;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column pet.gmt_created
     *
     * @mbg.generated Sat Mar 21 21:55:55 CST 2020
     */
    private Date gmtCreated;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column pet.gmt_modified
     *
     * @mbg.generated Sat Mar 21 21:55:55 CST 2020
     */
    private Date gmtModified;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column pet.id
     *
     * @return the value of pet.id
     *
     * @mbg.generated Sat Mar 21 21:55:55 CST 2020
     */
    public Long getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column pet.id
     *
     * @param id the value for pet.id
     *
     * @mbg.generated Sat Mar 21 21:55:55 CST 2020
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column pet.name
     *
     * @return the value of pet.name
     *
     * @mbg.generated Sat Mar 21 21:55:55 CST 2020
     */
    public String getName() {
        return name;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column pet.name
     *
     * @param name the value for pet.name
     *
     * @mbg.generated Sat Mar 21 21:55:55 CST 2020
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column pet.sex
     *
     * @return the value of pet.sex
     *
     * @mbg.generated Sat Mar 21 21:55:55 CST 2020
     */
    public Integer getSex() {
        return sex;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column pet.sex
     *
     * @param sex the value for pet.sex
     *
     * @mbg.generated Sat Mar 21 21:55:55 CST 2020
     */
    public void setSex(Integer sex) {
        this.sex = sex;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column pet.age
     *
     * @return the value of pet.age
     *
     * @mbg.generated Sat Mar 21 21:55:55 CST 2020
     */
    public Integer getAge() {
        return age;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column pet.age
     *
     * @param age the value for pet.age
     *
     * @mbg.generated Sat Mar 21 21:55:55 CST 2020
     */
    public void setAge(Integer age) {
        this.age = age;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column pet.breed
     *
     * @return the value of pet.breed
     *
     * @mbg.generated Sat Mar 21 21:55:55 CST 2020
     */
    public String getBreed() {
        return breed;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column pet.breed
     *
     * @param breed the value for pet.breed
     *
     * @mbg.generated Sat Mar 21 21:55:55 CST 2020
     */
    public void setBreed(String breed) {
        this.breed = breed == null ? null : breed.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column pet.master_id
     *
     * @return the value of pet.master_id
     *
     * @mbg.generated Sat Mar 21 21:55:55 CST 2020
     */
    public Long getMasterId() {
        return masterId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column pet.master_id
     *
     * @param masterId the value for pet.master_id
     *
     * @mbg.generated Sat Mar 21 21:55:55 CST 2020
     */
    public void setMasterId(Long masterId) {
        this.masterId = masterId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column pet.status
     *
     * @return the value of pet.status
     *
     * @mbg.generated Sat Mar 21 21:55:55 CST 2020
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column pet.status
     *
     * @param status the value for pet.status
     *
     * @mbg.generated Sat Mar 21 21:55:55 CST 2020
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column pet.picture
     *
     * @return the value of pet.picture
     *
     * @mbg.generated Sat Mar 21 21:55:55 CST 2020
     */
    public String getPicture() {
        return picture;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column pet.picture
     *
     * @param picture the value for pet.picture
     *
     * @mbg.generated Sat Mar 21 21:55:55 CST 2020
     */
    public void setPicture(String picture) {
        this.picture = picture == null ? null : picture.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column pet.master_nickname
     *
     * @return the value of pet.master_nickname
     *
     * @mbg.generated Sat Mar 21 21:55:55 CST 2020
     */
    public String getMasterNickname() {
        return masterNickname;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column pet.master_nickname
     *
     * @param masterNickname the value for pet.master_nickname
     *
     * @mbg.generated Sat Mar 21 21:55:55 CST 2020
     */
    public void setMasterNickname(String masterNickname) {
        this.masterNickname = masterNickname == null ? null : masterNickname.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column pet.gmt_created
     *
     * @return the value of pet.gmt_created
     *
     * @mbg.generated Sat Mar 21 21:55:55 CST 2020
     */
    public Date getGmtCreated() {
        return gmtCreated;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column pet.gmt_created
     *
     * @param gmtCreated the value for pet.gmt_created
     *
     * @mbg.generated Sat Mar 21 21:55:55 CST 2020
     */
    public void setGmtCreated(Date gmtCreated) {
        this.gmtCreated = gmtCreated;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column pet.gmt_modified
     *
     * @return the value of pet.gmt_modified
     *
     * @mbg.generated Sat Mar 21 21:55:55 CST 2020
     */
    public Date getGmtModified() {
        return gmtModified;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column pet.gmt_modified
     *
     * @param gmtModified the value for pet.gmt_modified
     *
     * @mbg.generated Sat Mar 21 21:55:55 CST 2020
     */
    public void setGmtModified(Date gmtModified) {
        this.gmtModified = gmtModified;
    }
}