package com.sharity.itu.sharity;

/**
 * Created by Madhura on 8/23/2016.
 */
public class Details {

    private String email;
    private String name;
    private String password;
    private String course;
    private String expertise;
    private String interest;
    private String indicator;

    private String category;
    private String priority;
    private String title;
    private String desc;
    private  String hint;


    public Details(){

    }

    /**
     * Constructor
     * @param email
     * @param password
     */
    public Details(String email, String password){
        this.email = email;
        this.password = password;

    }

    /**
     * Constructor
     * @param name
     * @param course
     * @param expertise
     * @param interest
          */
    public Details(String name, String email, String course, String expertise, String interest, String hint, String password){

        this.name = name;
        this.email = email;
        this.course = course;
        this.expertise = expertise;
        this.interest = interest;
        this.hint = hint;
        this.password = password;

    }

    /**
    * Constructor
    * @param name
    * @param email
    * @param category
    * @param priority
    * @param desc
    */
    public Details(String name, String email, String category, String priority, String title, String desc){

        this.name = name;
        this.email = email;
        this.category = category;
        this.priority = priority;
        this.title = title;
        this.desc = desc;

    }
    /**
     * Getter method for email
     * @return
     */
    public String getEmail() {
        return email;
    }

    /**
     * Setter method for email
     * @param email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * getter for name
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * setter for name
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * getter for password
     * @return password
     */
    public String getPassword() {
        return password;
    }

    /**
     * setter for password
     * @param password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * getter for course
     * @return course
     */
    public String getCourse() {
        return course;
    }

    /**
     * setter for course
     * @param course
     */
    public void setCourse(String course) {
        this.course = course;
    }

    /**
     * getter for expertise
     * @return expertise
     */
    public String getExpertise() {
        return expertise;
    }

    /**
     * setter for expertise
     * @param expertise
     */
    public void setExpertise(String expertise) {
        this.expertise = expertise;
    }

    /**
     * getter for interest
     * @return interest
     */
    public String getInterest() {
        return interest;
    }

    /**
     * setter for interest
     * @param interest
     */
    public void setInterest(String interest) {
        this.interest = interest;
    }

    /**
     * getter for category
     * @return category
     */
    public String getCategory() {
        return category;
    }

    /**
     * setter for category
     * @param category
     */
    public void setCategory(String category) {
        this.category = category;
    }

    /**
     * getter for title
     * @return title
     */
    public String getTitle() {
        return title;
    }

    /**
     * setter for title
     * @param title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * getter for priority
     * @return priority
     */
    public String getPriority() {
        return priority;
    }

    /**
     * setter for priority
     * @param priority
     */
    public void setPriority(String priority) {
        this.priority = priority;
    }

    /**
     * getter for desc
     * @return desc
     */
    public String getDesc() {
        return desc;
    }

    /**
     * setter for desc
     * @param desc
     */
    public void setDesc(String desc) {
        this.desc = desc;
    }

    /**
     * getter for indicator
     * @return indicator
     */
    public String getIndicator() {
        return indicator;
    }

    /**
     * setter for indicator
     * @param indicator
     */
    public void setIndicator(String indicator) {
        this.indicator = indicator;
    }

    /**
     * getter for hint
     * @return hint
     */

    public String getHint() {
        return hint;
    }

    /**
     * setter for hint
     * @param hint
     */
    public void setHint(String hint) {
        this.hint = hint;
    }
}
